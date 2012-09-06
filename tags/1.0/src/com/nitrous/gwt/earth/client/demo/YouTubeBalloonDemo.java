/*
 * Copyright 2012 Nick Kerr
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nitrous.gwt.earth.client.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GEHtmlStringBalloon;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlFeature;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.MouseClickListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/?exp=earth#embedding_youtube_videos_in_balloons">here</a>.
 * 
 * This demo shows how to embed a YouTube video inside the popup balloon of a placemark.
 * 
 * @author nick
 */
public class YouTubeBalloonDemo implements EntryPoint {

	private GoogleEarthWidget earth;
	private KmlPlacemark placemark;
	
    public void onModuleLoad() {
    	// Load the Earth API
    	GoogleEarth.loadApi(new Runnable(){
			@Override
			public void run() {
				// start the application
				onApiLoaded();				
			}    		
    	});    	
    }
    
    /**
     * The Google earth API has loaded, start the application
     */
    private void onApiLoaded() {
		// construct the UI widget
		earth = new GoogleEarthWidget();

		// register a listener to be notified when the earth plug-in has loaded
		earth.addPluginReadyListener(new GEPluginReadyListener() {
			public void pluginReady(GEPlugin ge) {
				// show map content once the plugin has loaded
				loadMapContent();
			}

			public void pluginInitFailure() {
				// failure!
				Window.alert("Failed to initialize Google Earth Plug-in");
			}
		});

		// Add the earth widget to the DOM
		RootLayoutPanel.get().add(earth);

		// begin loading the Google Earth Plug-in
		earth.init();
	}

	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// create the placemark
		placemark = ge.createPlacemark("");
		
		KmlPoint point = ge.createPoint("");
		point.setLatitude(37);
		point.setLongitude(-122);
		placemark.setGeometry(point);

		// add the placemark to the earth DOM
		ge.getFeatures().appendChild(placemark);

		  // look at the placemark we created
		KmlLookAt la = ge.createLookAt("");
		la.set(37, -122,
		    0, // altitude
		    KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
		    0, // heading
		    0, // straight-down tilt
		    5000 // range (inverse of zoom)
		    );
		ge.getView().setAbstractView(la);		
        placemark.setName("Click for a YouTube video!");
        
		ge.getWindow().addMouseClickListener(new MouseClickListener() {
            @Override
            public void onClick(KmlMouseEvent event) {
                GEPlugin ge = earth.getGEPlugin();
                
                // prevent the default balloon from popping up
                event.preventDefault();

                GEHtmlStringBalloon balloon = ge.createHtmlStringBalloon("");
                balloon.setFeature((KmlFeature)event.getTarget()); //optional
                balloon.setMaxWidth(560);

                // YouTube video embed... the &nbsp; in the beginning is a fix for IE6
                balloon.setContentString("&nbsp;" +
                		"<iframe width=\"560\" height=\"345\" " +
                		"src=\"http://www.youtube.com/embed/vtAVeGOUEr0\" " +
                		"frameborder=\"0\" " +
                		"allowfullscreen>" +
                		"</iframe>");
                ge.setBalloon(balloon);                
            }
            
            @Override
            public void onMouseUp(KmlMouseEvent event) {
            }
            
            @Override
            public void onMouseDown(KmlMouseEvent event) {
            }
            
            @Override
            public void onDoubleClick(KmlMouseEvent event) {
            }            
        }); 
	}

}
