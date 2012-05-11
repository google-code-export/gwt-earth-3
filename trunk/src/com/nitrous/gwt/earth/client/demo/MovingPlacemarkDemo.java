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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GEHtmlStringBalloon;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;

/**
 * This sample plots a location on the map and then moves that placemark once every second
 */
public class MovingPlacemarkDemo implements EntryPoint {

    private GoogleEarthWidget earth;

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

        // add the widget to the DOM
        RootLayoutPanel.get().add(earth);

        // begin loading the Google Earth Plug-in
        earth.init();
    }

    /**
     * Display content on the map
     */
    private void loadMapContent() {
        // The GEPlugin is the core class and is a great place to start browsing the API
        GEPlugin ge = earth.getGEPlugin();
        ge.getWindow().setVisibility(true);
        
        // show some layers
        ge.enableLayer(GELayerId.LAYER_BUILDINGS, true);
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
        ge.enableLayer(GELayerId.LAYER_TERRAIN, true);
        ge.enableLayer(GELayerId.LAYER_TREES, true);

        // show an over-view pane
        ge.getOptions().setOverviewMapVisibility(true);

        // plot a placemark
    	final String placemarkId = "MyPlacemark1";
        final KmlPlacemark placemark = ge.createPlacemark(placemarkId);
		KmlPoint kmlPoint = ge.createPoint("");
		kmlPoint.setLatLng(34.73D, -86.59D);
		kmlPoint.setAltitudeMode(KmlAltitudeMode.ALTITUDE_CLAMP_TO_GROUND);
		placemark.setGeometry(kmlPoint);
		
		// configure a popup balloon for the placemark
		final GEHtmlStringBalloon balloon = ge.createHtmlStringBalloon("MyBalloon1");
		balloon.setContentString("This is a test");
		balloon.setFeature(placemark);
		ge.getFeatures().appendChild(placemark);
		
		// give the map 2 seconds to pan and then show the balloon
		Timer timer = new Timer(){
			@Override
			public void run() {
				earth.getGEPlugin().setBalloon(balloon);
			}
		};
		timer.schedule(2000);
		
		// look at the placemark
		KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
		lookAt.setLatitude(kmlPoint.getLatitude());
		lookAt.setLongitude(kmlPoint.getLongitude());
		lookAt.setRange(500000D);		
		ge.getView().setAbstractView(lookAt);
		
		// move the placemark once every second
		Timer t = new Timer() {
			@Override
			public void run() {
				KmlObject obj = earth.getGEPlugin().getElementById(placemarkId);
				KmlPlacemark placemark =  (KmlPlacemark)obj;
				KmlPoint point = (KmlPoint)placemark.getGeometry();
				point.setLatitude(point.getLatitude()+.1D);
			}
		};
		t.scheduleRepeating(1000);
	
    }

}
