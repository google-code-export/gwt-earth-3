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
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#showing_time_ui_by_parsing_time-aware_kml">here</a>.
 * 
 * @author Nick
 *
 */
public class TimeAwareKmlDemo implements EntryPoint {

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
        
        // add a navigation control
        ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
        
        // show some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);

        // Sample KML taken from
        // http://code.google.com/apis/kml/documentation/kml_tut.html#polygons
        KmlObject timeAwareDoc = ge.parseKml(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">" +
            "  <Document>" +
            "    <Placemark>" +
            "      <name>Placemark 1</name>" +
            "      <TimeSpan>" +
            "        <begin>2007-01-14T21:05:02Z</begin>" +
            "        <end>2007-01-14T21:05:20Z</end>" +
            "      </TimeSpan>" +
            "      <Point>" +
            "        <coordinates>-122.536226,37.86047,0</coordinates>" +
            "      </Point>" +
            "    </Placemark>" +
            "    <Placemark>" +
            "      <name>Placemark 2</name>" +
            "      <TimeSpan>" +
            "        <begin>2007-01-14T21:05:20Z</begin>" +
            "        <end>2007-01-14T21:05:43Z</end>" +
            "      </TimeSpan>" +
            "      <Point>" +
            "        <coordinates>-122.536422,37.860303,0</coordinates>" +
            "      </Point>" +
            "    </Placemark>" +
            "    <Placemark>" +
            "      <name>Placemark 3</name>" +
            "      <TimeSpan>" +
            "        <begin>2007-01-14T21:05:43Z</begin>" +
            "        <end>2007-01-14T21:06:04Z</end>" +
            "      </TimeSpan>" +
            "      <Point>" +
            "        <coordinates>-122.536688,37.860072,0</coordinates>" +
            "      </Point>" +
            "    </Placemark>" +
            "  </Document>" +
            "</kml>");

        ge.getFeatures().appendChild(timeAwareDoc);

        // Fly to the Pentagon
        KmlLookAt la = ge.createLookAt("");
        la.set(37.860303, -122.536422, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 0, 45, 75);
        ge.getView().setAbstractView(la);
    }
}
