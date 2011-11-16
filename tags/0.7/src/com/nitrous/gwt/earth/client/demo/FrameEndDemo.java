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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlCamera;
import com.nitrous.gwt.earth.client.api.event.FrameEndListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#smooth_animation_with_frameend">here</a>.
 * 
 * @author nick
 * 
 */
public class FrameEndDemo implements EntryPoint {
	
	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

	private GoogleEarthWidget earth;
	private boolean animRunning = false;
	private int ANIM_ALTITUDE = 100;
	private HandlerRegistration frameEndRegistration;
	
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

		Button startButton = new Button("Start animation");
		startButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!animRunning) {
					GEPlugin ge = earth.getGEPlugin();
					ge.getOptions().setFlyToSpeed(ge.getFlyToSpeedTeleport());
					animRunning = true;
					frameEndRegistration = ge
							.addFrameEndListener(new FrameEndListener() {
								@Override
								public void onFrameEnd() {
									tickAnimation();
								}

							});
					// start it off
					tickAnimation();
				}
			}
		});
		Button stopButton = new Button("Stop animation");
		stopButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (animRunning) {
					frameEndRegistration.removeHandler();
					animRunning = false;
				}
			}
		});

		HorizontalPanel buttons = new HorizontalPanel();
		buttons.add(startButton);
		buttons.add(stopButton);

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topPanel.add(buttons);

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 40D);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);

		// begin loading the Google Earth Plug-in
		earth.init();
	}

	private void tickAnimation() {
		// an example of some camera manipulation that's possible w/ the Earth
		// API
		GEPlugin ge = earth.getGEPlugin();
		KmlCamera camera = ge.getView().copyAsCamera(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
		double[] dest = destination(
				camera.getLatitude(),
				camera.getLongitude(), 
				10, 
				camera.getHeading());

		camera.setAltitude(ANIM_ALTITUDE);
		camera.setLatitude(dest[0]);
		camera.setLongitude(dest[1]);

		ge.getView().setAbstractView(camera);
	}

	private double[] destination(double lat, double lng, double dist, double heading) {
		lat *= Math.PI / 180;
		lng *= Math.PI / 180;
		heading *= Math.PI / 180;
		dist /= 6371000; // angular dist

		double lat2 = Math.asin(Math.sin(lat) 
				* Math.cos(dist) + Math.cos(lat)
				* Math.sin(dist) 
				* Math.cos(heading));

		return new double[] {
				180 / Math.PI * lat2,
				180 / Math.PI * (lng + Math.atan2(Math.sin(heading) * Math.sin(dist) * Math.cos(lat2), 
				Math.cos(dist) - Math.sin(lat) * Math.sin(lat2))) 
		};
	}

	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		ge.getOptions().setMouseNavigationEnabled(false);

		KmlCamera camera = ge.createCamera("");
		camera.set(37, -122, ANIM_ALTITUDE, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 0, 80, 0);
		ge.getView().setAbstractView(camera);

		// show an over-view pane
		ge.getOptions().setOverviewMapVisibility(true);
	}

}
