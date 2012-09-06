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
import com.google.gwt.user.client.Timer;
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

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#move_camera_with_timeouts">here</a>.
 * 
 * @author nick
 */
public class MoveCameraWithTimerDemo implements EntryPoint {
	private GoogleEarthWidget earth;
	private double oldFlyToSpeed;
	
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
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);
		
		 // move the camera on a timeout
		oldFlyToSpeed = ge.getOptions().getFlyToSpeed();
		ge.getOptions().setFlyToSpeed(ge.getFlyToSpeedTeleport());
		
		moveCamera(0);
	}

	private void moveCamera(final int count) {
		GEPlugin ge = earth.getGEPlugin();
		
		KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
	    lookAt.setLatitude(lookAt.getLatitude() + .1);
	    lookAt.setLongitude(lookAt.getLongitude() + 5);
	    ge.getView().setAbstractView(lookAt);

	    if (count < 40) {
	    	// if we haven't already moved the camera 40 times
	    	// schedule the next movement in 50ms
	    	Timer t = new Timer() {
				@Override
				public void run() {
			        moveCamera(count + 1);
				}
			};
			t.schedule(50);
	    } else {
	    	// Done moving the camera.
	    	// Restore old flyTo speed
	    	ge.getOptions().setFlyToSpeed(oldFlyToSpeed);
	    }
	}
}
