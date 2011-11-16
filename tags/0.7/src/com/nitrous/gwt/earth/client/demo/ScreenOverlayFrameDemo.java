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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlIcon;
import com.nitrous.gwt.earth.client.api.KmlScreenOverlay;
import com.nitrous.gwt.earth.client.api.KmlUnits;
import com.nitrous.gwt.earth.client.api.KmlVec2;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#screen_overlay_%28frame%29">here</a>.
 */
public class ScreenOverlayFrameDemo implements EntryPoint {

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
  
        // add some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
    
        KmlScreenOverlay screenOverlay = ge.createScreenOverlay("");

        // Set the visibility to false while we construct the overlay.
        screenOverlay.setVisibility(false);

        KmlIcon icon = ge.createIcon("");
        // Loads a gilded picture frame.
        icon.setHref("http://earth-api-samples.googlecode.com/svn/trunk/examples/static/frame.png");  
        screenOverlay.setIcon(icon);

        // Position the overlay.  ScreenXY(0,0) is mapped to OverlayXY(0,0)
        KmlVec2 screenXY = screenOverlay.getScreenXY();
        screenXY.setXUnits(KmlUnits.UNITS_PIXELS);
        screenXY.setYUnits(KmlUnits.UNITS_PIXELS);
        screenXY.setX(0);
        screenXY.setY(0);

        KmlVec2 overlayXY = screenOverlay.getOverlayXY();
        overlayXY.setXUnits(KmlUnits.UNITS_PIXELS);
        overlayXY.setYUnits(KmlUnits.UNITS_PIXELS);
        overlayXY.setX(0);
        overlayXY.setY(0);

        // Set object's size in fractions of the 3d view window.  By setting
        // to (1, 1), this image will cover the entire Earth window.
        KmlVec2 overlaySize = screenOverlay.getSize();
        overlaySize.setXUnits(KmlUnits.UNITS_FRACTION);
        overlaySize.setYUnits(KmlUnits.UNITS_FRACTION);
        overlaySize.setX(1);
        overlaySize.setY(1);

        screenOverlay.setVisibility(true);

        ge.getFeatures().appendChild(screenOverlay);        
    }
}
