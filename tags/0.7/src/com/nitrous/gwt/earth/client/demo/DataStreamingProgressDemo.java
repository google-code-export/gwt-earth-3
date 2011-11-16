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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#data_streaming_progress">here</a>.
 * 
 * @author nick
 * 
 */
public class DataStreamingProgressDemo implements EntryPoint {
	
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

		HorizontalPanel messageRow = new HorizontalPanel();
		messageRow.add(new HTML(
				"<style type=\"text/css\">"
						+ "  #progress-container { width: 250px; height: 10px; border: 1px solid #ccc; }"
						+ "  #progress-bar { width: 0; height: 10px; }"
				+ "</style>" + "Data Streaming Progress:"
				+ "<div id=\"progress-container\">"
						+ "  <div id=\"progress-bar\"></div>"
				+ "</div><br/>"));

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(messageRow, 40D);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);

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

		// Update streaming progress every 100ms
		Timer t = new Timer() {
			@Override
			public void run() {
				float streamingPercent = ge.getStreamingPercent();
				Element progressBar = Document.get().getElementById("progress-bar");
				if (streamingPercent == 100) {
					// streaming complete, hide the progress bar
					progressBar.setAttribute("style", "background-color:#0a0; width:250px;");
				} else {
					// show the progress bar, max width is 250 as per the
					// stylesheet
					progressBar.setAttribute("style", "background-color:#00f; width:" + (250 * streamingPercent / 100) + "px;");
				}
			}
		};
		t.scheduleRepeating(100);
		
		// Fly to the Grand Canyon
		KmlLookAt la = ge.createLookAt("");
		la.set(36.291068, -112.4981896, 0, 
			KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 0, // heading
			80, 
			20000);
		ge.getView().setAbstractView(la);		
	}

}
