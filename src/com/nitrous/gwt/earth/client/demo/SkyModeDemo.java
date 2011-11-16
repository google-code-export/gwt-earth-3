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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEMapType;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#sky_mode">here</a>.
 * 
 * @author nick
 * 
 */
public class SkyModeDemo implements EntryPoint {

	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

	private GoogleEarthWidget earth;
	private KmlPlacemark placemark;
	
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

		Button skyButton = new Button("Switch to Sky");
		skyButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showSky();
			}
		});
		Button earthButton = new Button("Switch to Earth");
		earthButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showEarth();
			}
		});

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topPanel.add(skyButton);
		topPanel.add(earthButton);

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 30D);
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
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// create the placemark
		placemark = ge.createPlacemark("");

		KmlPoint point = ge.createPoint("");
		point.setLatitude(37);
		point.setLongitude(-122);
		placemark.setGeometry(point);

		// add the placemark to the earth DOM
		ge.getFeatures().appendChild(placemark);

		  // look at the placemark we created
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

	private void showSky() {
		final GEPlugin ge = earth.getGEPlugin();
		ge.getOptions().setMapType(GEMapType.MAP_TYPE_SKY);

		// Start the zoom-in after one second.
		new Timer() {
			
			@Override
			public void run() {
			    // Zoom in on a nebula.
			    double oldFlyToSpeed = ge.getOptions().getFlyToSpeed();
			    ge.getOptions().setFlyToSpeed(.2);  // Slow down the camera flyTo speed.
			    KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
			    lookAt.set(41.28509187215, -169.2448684551622, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 262.87, 0, 162401);
			    
			    // Also try:
			    // lookAt.set(-59.65189337195337, -18.799770300376053, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 0, 0, 36817);
			    ge.getView().setAbstractView(lookAt);
			    ge.getOptions().setFlyToSpeed(oldFlyToSpeed);
			}
		}.schedule(1000);
	}

	private void showEarth() {
		GEPlugin ge = earth.getGEPlugin();
		ge.getOptions().setMapType(GEMapType.MAP_TYPE_EARTH);
	}
}
