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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.event.ViewChangeListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/?exp=earth#viewchange_event_%28and_viewchangebegin,_viewchangeend%29">here</a>.
 * 
 * @author nick
 * 
 */
public class ViewChangeDemo implements EntryPoint {

	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

	private GoogleEarthWidget earth;
	
	// this can be used to remove the view change listener
	private HandlerRegistration eventListenerRegistration;

	private Label viewChangeEndCountLabel;
	private int viewChangeEndCount = 0;
	private Label viewChangedLabel;
	private static final String CHANGE_END_TEXT = "viewchangeend count: ";
		
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
		
		VerticalPanel labels = new VerticalPanel();
		viewChangeEndCountLabel = new Label(CHANGE_END_TEXT + 0);
		viewChangedLabel = new Label("View Changed!");
		labels.add(viewChangeEndCountLabel);
		labels.add(viewChangedLabel);

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topPanel.add(labels);
		
		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 40D);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);
		
		viewChangedLabel.getElement().setAttribute("style", "background-color: #a00; color: #fff; font-weight: bold;");
		viewChangedLabel.setVisible(false);

		// begin loading the Google Earth Plug-in
		earth.init();
	}

	private Timer vcTimeout = null;
	
	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// the timer that will be used to hid the label after 250ms of inactivity
		vcTimeout = new Timer(){
			@Override
			public void run() {
				viewChangedLabel.setVisible(false);
			}
		};
		
		// register the view change listener to hide/show the label and update the view change end count label
		eventListenerRegistration = ge.getView().addViewChangeListener(new ViewChangeListener(){

			@Override
			public void onViewChange() {
				viewChangedLabel.setVisible(true);
				//reschedule the timer to hide the 'view changed' message after 250ms of inactivity
				if (vcTimeout != null) {
					vcTimeout.cancel();
				}
				vcTimeout.schedule(250);
				
			}

			@Override
			public void onViewChangeBegin() {
			}

			@Override
			public void onViewChangeEnd() {
				viewChangeEndCount++;
				viewChangeEndCountLabel.setText(CHANGE_END_TEXT + viewChangeEndCount);
			}
		});
		
	}

}
