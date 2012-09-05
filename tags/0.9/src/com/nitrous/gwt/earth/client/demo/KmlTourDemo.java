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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlObject;
import com.nitrous.gwt.earth.client.api.KmlTour;
import com.nitrous.gwt.earth.client.api.event.KmlLoadCallback;

/**
 * A GWT Implementation of the simple tour demo found here: http://code.google.com/apis/earth/documentation/touring.html
 * 
 * Loads and executes a simple KML Tour. The sample kml file can be found here:
 * 
 * This demo loads the kml file found here: http://code.google.com/p/gwt-earth-3/source/browse/trunk/war/kml/bounce_example.kml
 * 
 * Simple tours, with the &lt;gx:Tour&gt; feature as the root-level feature of the KML, 
 * can be fetched and passed directly to GETourPlayer. The tour must be the only feature 
 * in the KML file. If your KML doesn't meet these requirements, refer to the Complex tour example:
 * http://code.google.com/p/gwt-earth-3/source/browse/trunk/src/com/nitrous/gwt/earth/client/demo/ComplexKmlTourDemo.java
 * 
 * @author nick
 */
public class KmlTourDemo implements EntryPoint {

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

		// load the KML
		String href = GWT.getHostPageBaseURL() + "kml/bounce_example.kml";
		GoogleEarth.fetchKml(ge, href, new KmlLoadCallback(){
			@Override
			public void onLoaded(KmlObject feature) {
				// play the tour
				ge.getTourPlayer().setTour((KmlTour)feature);
				ge.getTourPlayer().play();
			}
		});
	}

}
