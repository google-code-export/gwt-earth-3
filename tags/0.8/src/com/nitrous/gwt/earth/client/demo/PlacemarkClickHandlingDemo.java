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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
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
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.KmlEventListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#placemark_click_event_handling">here</a>.
 * 
 * @author nick
 * 
 */
public class PlacemarkClickHandlingDemo implements EntryPoint {

	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

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

		// Add the earth widget to the DOM
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
		  
		GoogleEarth.addEventListener(placemark, "click", new KmlEventListener() {
			@Override
			public void onEvent(final KmlEvent event) {
				// Prevent default balloon from popping up for marker placemarks
				event.preventDefault();
				
				// prevent deadlock in some browsers by deferring execution of the alert dialog
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						showEvent(event);
					}
				});
			}
		});
	}

	private void showEvent(KmlEvent event) {
		String text = "Click:";
		text = addToMessage(text, "target type", event.getTarget().getType());
		text = addToMessage(text, "currentTarget type", event.getCurrentTarget().getType());
		text = addToMessage(text, "timeStamp", String.valueOf(event.getTimeStamp()));
		
		KmlMouseEvent mouseEvent = (KmlMouseEvent)event;
		text = addToMessage(text, "button", String.valueOf(mouseEvent.getButton()));
		text = addToMessage(text, "clientX", String.valueOf(mouseEvent.getClientX()));
		text = addToMessage(text, "clientY", String.valueOf(mouseEvent.getClientY()));
		text = addToMessage(text, "screenX", String.valueOf(mouseEvent.getScreenX()));
		text = addToMessage(text, "screenY", String.valueOf(mouseEvent.getScreenY()));
		text = addToMessage(text, "latitude", String.valueOf(mouseEvent.getLatitude()));
		text = addToMessage(text, "longitude", String.valueOf(mouseEvent.getLongitude()));
		text = addToMessage(text, "altitude", String.valueOf(mouseEvent.getAltitude()));
		text = addToMessage(text, "didHitGlobe", String.valueOf(mouseEvent.getDidHitGlobe()));
		text = addToMessage(text, "altKey", String.valueOf(mouseEvent.getAltKey()));
		text = addToMessage(text, "ctrlKey", String.valueOf(mouseEvent.getCtrlKey()));
		text = addToMessage(text, "shiftKey", String.valueOf(mouseEvent.getShiftKey()));
		Window.alert(text);
	}
	
	private String addToMessage(String text, String append1, String append2) {
		return text + " " + append1 + ": " + append2 + "\n";
	}
}
