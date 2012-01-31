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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
 * A demo to test the new features offered by version 1.009 of the Google Earth JavaScript API.
 * See 1.009 Release notes <a href="http://code.google.com/apis/earth/documentation/releasenotes.html">here</a>
 * @author nick
 */
public class Api1009FeatureTestDemo implements EntryPoint {

	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

	private GoogleEarthWidget earth;
	
	private CheckBox loopCheck;
	
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

		// tour visibility
		Button visibilityButton = new Button("Toggle Tour Control Visibility");
		visibilityButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GEPlugin ge = earth.getGEPlugin();
				boolean isVisible =	ge.getTourPlayer().getControl().isVisible();
				ge.getTourPlayer().getControl().setVisibile(!isVisible);
			}
		});
		
		// Get tour speed
		Button showSpeedButton = new Button("Get Tour Speed");
		showSpeedButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {				
				GEPlugin ge = earth.getGEPlugin();
				Window.alert("Tour speed = " + ge.getTourPlayer().getCurrentSpeed());
			}
		});
		
		// Increase tour speed
		Button speedIncreaseButton = new Button("Speed++");
		speedIncreaseButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {				
				GEPlugin ge = earth.getGEPlugin();
				ge.getTourPlayer().setCurrentSpeed(ge.getTourPlayer().getCurrentSpeed()+1);
			}
		});
		
		// Decrease tour speed
		Button speedDecreaseButton = new Button("Speed--");
		speedDecreaseButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {				
				GEPlugin ge = earth.getGEPlugin();
				float speed = ge.getTourPlayer().getCurrentSpeed();
				speed = speed -1;
				if (speed < 0) {
					speed = 0;
				}
				ge.getTourPlayer().setCurrentSpeed(speed);
			}
		});
		
		// Toggle loop
		loopCheck = new CheckBox("Loop");
		loopCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				GEPlugin ge = earth.getGEPlugin();
				ge.getTourPlayer().setLoop(event.getValue());
			}
			
		});
		
		HorizontalPanel topPanel = new HorizontalPanel();
		//topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		topPanel.add(visibilityButton);
		topPanel.add(showSpeedButton);
		topPanel.add(speedIncreaseButton);
		topPanel.add(speedDecreaseButton);
		topPanel.add(loopCheck);

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 40D);
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
				ge.getTourPlayer().setLoop(loopCheck.getValue());
			}
		});
	}

}
