/*
 * Copyright 2011 Nick Kerr
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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlGroundOverlay;
import com.nitrous.gwt.earth.client.api.KmlLatLonBox;
import com.nitrous.gwt.earth.client.api.KmlLookAt;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#creating_ground_overlays">here</a>.
 */
public class GroundOverlayDemo implements EntryPoint {
	
	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

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

        HorizontalPanel header = new HorizontalPanel();
        Button createButton = new Button("Create a Ground Overlay");
        createButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	createGroundOverlay();
            }
        });
        header.add(createButton);
        
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(header, 30);
        layout.add(earth);
        RootLayoutPanel.get().add(layout);

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
  
        // add some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
        
        KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
        lookAt.setRange(200000);       
        lookAt.setHeading(45);
        lookAt.setTilt(45);
        ge.getView().setAbstractView(lookAt);
    }
    
    private void createGroundOverlay() {
        // plot a placemark
        GEPlugin ge = earth.getGEPlugin();
        
        KmlGroundOverlay groundOverlay = ge.createGroundOverlay("");
        groundOverlay.setIcon(ge.createIcon(""));
        groundOverlay.getIcon().setHref("http://www.google.com/intl/en_ALL/images/logo.gif");
        groundOverlay.setLatLonBox(ge.createLatLonBox(""));

        KmlLookAt center = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
        double north = center.getLatitude() + .35;
        double south = center.getLatitude() - .35;
        double east = center.getLongitude() + .55;
        double west = center.getLongitude() - .55;
        double rotation = 0;
        KmlLatLonBox latLonBox = groundOverlay.getLatLonBox();
        latLonBox.setBox(north, south, east, west, rotation);

        ge.getFeatures().appendChild(groundOverlay);
    }
}
