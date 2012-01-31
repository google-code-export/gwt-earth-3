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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
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
import com.nitrous.gwt.earth.client.api.KmlEvent;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.event.KmlEventListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#retrieving_ground_altitude_data">here</a>.
 * 
 * @author nick
 * 
 */
public class GroundAltitudeDemo implements EntryPoint {
	
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

		HorizontalPanel messageRow = new HorizontalPanel();
		messageRow.add(new HTML("<p><em>Note: Zoom in to a spot with terrain to get ground altitudes.</em></p>" +
				"<p>Ground Altitude at Mouse: <span id=\"ground-altitude\" style=\"color:#ccc;\">N/A</span></p>"));

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(messageRow, 60D);
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
		ge.enableLayer(GELayerId.LAYER_TERRAIN, true);

		// Fly to the Grand Canyon
		KmlLookAt la = ge.createLookAt("");
		la.set(36.20839, -112.45803, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, -30, 40, 10000);
		ge.getView().setAbstractView(la);

		// listen to the click event on the globe and window
		GoogleEarth.addEventListener(ge.getWindow(), "mousemove",
			new KmlEventListener() {

				@Override
				public void onEvent(KmlEvent evt) {
					String statusHTML = "N/A";
					KmlMouseEvent event = (KmlMouseEvent) evt;
					if (event.getDidHitGlobe()) {
						double latitude = event.getLatitude();
						double longitude = event.getLongitude();

						double groundAltitude = ge.getGlobe()
								.getGroundAltitude(latitude, longitude);
						if (groundAltitude != 0) {
							statusHTML = "<span style=\"color:#000; font-weight:bold;\">"
									+ NumberFormat.getFormat("#,##0.0#").format(groundAltitude)
									+ " meters</span>";
						}
					}
					Document.get().getElementById("ground-altitude").setInnerHTML(statusHTML);
				}
			});
	}

}
