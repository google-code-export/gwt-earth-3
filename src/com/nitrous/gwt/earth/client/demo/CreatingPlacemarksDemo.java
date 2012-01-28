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
import com.nitrous.gwt.earth.client.api.KmlIcon;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.KmlStyle;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#creating_placemarks">here</a>.
 */
public class CreatingPlacemarksDemo implements EntryPoint {
	
	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

    private GoogleEarthWidget earth;
    private int counter = 0;
	
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
        Button createButton = new Button("Create A placemark!");
        createButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                createPlacemark();
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
        lookAt.setRange(100000);       
        ge.getView().setAbstractView(lookAt);
        
        createPlacemark();
    }
    
    private void createPlacemark() {
        // plot a placemark
        GEPlugin ge = earth.getGEPlugin();
        KmlPlacemark placemark = ge.createPlacemark("");
        placemark.setName("placemark"+counter);
        ge.getFeatures().appendChild(placemark);
        
        // Create style map for placemark
        KmlIcon icon = ge.createIcon("");
        icon.setHref("http://maps.google.com/mapfiles/kml/paddle/red-circle.png");
        KmlStyle style = ge.createStyle("");
        style.getIconStyle().setIcon(icon);
        placemark.setStyleSelector(style);

        // Create point
        KmlLookAt la = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
        KmlPoint point = ge.createPoint("");
        point.setLatitude(la.getLatitude());
        point.setLongitude(la.getLongitude());
        placemark.setGeometry(point);

        counter++;	
    }
}
