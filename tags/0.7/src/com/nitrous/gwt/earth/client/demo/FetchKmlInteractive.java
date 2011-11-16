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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;
import com.nitrous.gwt.earth.client.api.event.KmlLoadCallback;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#fetch_kml_(interactive)">here</a>.
 * 
 * @author Nick
 *
 */
public class FetchKmlInteractive implements EntryPoint {
	
	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

    private GoogleEarthWidget earth;
    private TextBox kmlUrlBox;
    private KmlObject currentKmlObject;
	
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

        kmlUrlBox = new TextBox();
        kmlUrlBox.getElement().setAttribute("size", "70");
        kmlUrlBox.setValue("http://earth-api-samples.googlecode.com/svn/trunk/examples/static/red.kml");
        
        Button fetchKmlButton = new Button("Fetch KML!");
        fetchKmlButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                fetchKmlFromInput();
            }
        });
        
        HorizontalPanel urlPanel = new HorizontalPanel();
        urlPanel.setSpacing(5);
        urlPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        urlPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        urlPanel.add(new Label("Fetch KML at this URL:"));        
        urlPanel.add(kmlUrlBox);
        urlPanel.add(fetchKmlButton);
                
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(urlPanel, 40);
        layout.add(earth);
        // add the widget to the DOM
        RootLayoutPanel.get().add(layout);

        // begin loading the Google Earth Plug-in
        earth.init();
    }

    private void fetchKmlFromInput() {
        final GEPlugin ge = earth.getGEPlugin();
        
        // remove existing content from the map
        if (currentKmlObject != null) {
            ge.getFeatures().removeChild(currentKmlObject);
            currentKmlObject = null;
        }
        
        String kmlUrl = kmlUrlBox.getValue();
        ge.fetchKml(kmlUrl,  new KmlLoadCallback() {
            @Override
            public void onLoaded(KmlObject kmlObject) {
                // check if the KML was fetched properly
                if (kmlObject != null) {
                    currentKmlObject = kmlObject;
                    ge.getFeatures().appendChild(currentKmlObject);
                } else {
                    Window.alert("Bad or null KML");
                }
            }
        });
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
        
        // fly to Santa Cruz
        KmlLookAt la = ge.createLookAt("");
        la.set(37, -122, 
                0, // altitude                
                KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
                0, // heading
                0, // straight-down tilt
                5000 // range (inverse of zoom)
        );
        ge.getView().setAbstractView(la);        
    }
}
