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
import com.nitrous.gwt.earth.client.api.GEEventEmitter;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlEvent;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.KmlEventListener;

/**
 * A demo that displays the name of a clicked placemark
 * 
 * @author nick
 * 
 */
public class PlacemarkNameClickDemo implements EntryPoint {

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
		final GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);

		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);
		ge.enableLayer(GELayerId.LAYER_TERRAIN, true);

		// Fly to the Grand Canyon
		KmlLookAt la = ge.createLookAt("");
		la.set(36.20839, -112.45803, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, -30, 40, 75000);
		ge.getView().setAbstractView(la);

		// add some place marks
		for (int i = 0 ; i < 3; i++) {
            KmlPlacemark placemark = ge.createPlacemark("");
            placemark.setName("Placemark "+(i+1));
            KmlPoint point = ge.createPoint("");
            point.setLatitude(36.20839 + (i * 0.1D));
            point.setLongitude(-112.45803);
            placemark.setGeometry(point);
            ge.getFeatures().appendChild(placemark);
		}

		
		// listen for click events
		GoogleEarth.addEventListener(ge.getWindow(), "click",
			new KmlEventListener() {
				@Override
				public void onEvent(KmlEvent evt) {
					KmlMouseEvent event = (KmlMouseEvent) evt;
					if (event.getDidHitGlobe()) {
					    if ("KmlPlacemark".equals(event.getTarget().getType())) {
					        // prevent the default behavior (balloon popup)
					        evt.preventDefault();
					        
					        // identify the clicked balloon
					        GEEventEmitter target = event.getTarget();
					        KmlPlacemark placemark = (KmlPlacemark)target;
					        Window.alert("You Clicked "+placemark.getName());
					    }
					}
				}
			});
	}

}
