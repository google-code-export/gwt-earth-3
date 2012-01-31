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
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlIcon;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.KmlStyle;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#underwater_placemarks">here</a>.
 */
public class UnderwaterPlacemarkDemo implements EntryPoint {

	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

    private GoogleEarthWidget earth;

    public void onModuleLoad() {
    	// Load the Earth API
    	GoogleEarth.loadApi(EARTH_API_KEY, new Runnable(){
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
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);

        // show an over-view pane
        ge.getOptions().setOverviewMapVisibility(true);
		
		// look at the placemark
        KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_SEA_FLOOR);
        lookAt.setLatitude(26.5);
        lookAt.setLongitude(-44.1);
        lookAt.setTilt(70);
        lookAt.setRange(1500);
        lookAt.setHeading(0);
        ge.getView().setAbstractView(lookAt);

        // Create the placemark
        KmlPlacemark placemark = ge.createPlacemark("");
        placemark.setName("Underwater Placemark");
        ge.getFeatures().appendChild(placemark);

        // Create style for placemark
        KmlIcon icon = ge.createIcon("");
        icon.setHref("http://maps.google.com/mapfiles/kml/shapes/water.png");
        KmlStyle style = ge.createStyle("");
        style.getIconStyle().setIcon(icon);
        placemark.setStyleSelector(style);

        // Create point
        KmlPoint point = ge.createPoint("");
        point.setLatitude(lookAt.getLatitude());
        point.setLongitude(lookAt.getLongitude());
        point.setAltitude(100);
        point.setAltitudeMode(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_SEA_FLOOR);
        placemark.setGeometry(point);
    }

}
