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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
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
import com.nitrous.gwt.earth.client.api.KmlUnits;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#show/hide_navigation_controls">here</a>.
 * 
 * @author nick
 * 
 */
public class NavigationControlDemo implements EntryPoint {

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

		Button showButton = new Button("Show");
		showButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				visShow();
			}
		});

		Button autoShowButton = new Button("Auto Show/Hide");
		autoShowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				visAuto();
			}
		});

		Button hideButton = new Button("Hide");
		hideButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				visHide();
			}
		});

		Button topLeftButton = new Button("TL");
		topLeftButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				posTopLeft();
			}
		});

		Button topRightButton = new Button("TR");
		topRightButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				posTopRight();
			}
		});

		Button bottomLeftButton = new Button("BL");
		bottomLeftButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				posBottomLeft();
			}
		});

		Button bottomRightButton = new Button("BR");
		bottomRightButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				posBottomRight();
			}
		});

		VerticalPanel topPanel = new VerticalPanel();
		topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		HorizontalPanel navLabelPanel = new HorizontalPanel();
		navLabelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		navLabelPanel.add(new Label("Navigation Control Visibility"));
		topPanel.add(navLabelPanel);

		HorizontalPanel navButtonPanel = new HorizontalPanel();
		navButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		navButtonPanel.add(showButton);
		navButtonPanel.add(autoShowButton);
		navButtonPanel.add(hideButton);
		topPanel.add(navButtonPanel);

		HorizontalPanel positionLabelPanel = new HorizontalPanel();
		positionLabelPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		positionLabelPanel.add(new Label("Position"));
		topPanel.add(positionLabelPanel);

		HorizontalPanel topPositionButtonPanel = new HorizontalPanel();
		topPositionButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		topPositionButtonPanel.add(topLeftButton);
		topPositionButtonPanel.add(topRightButton);
		topPanel.add(topPositionButtonPanel);

		HorizontalPanel bottomPositionButtonPanel = new HorizontalPanel();
		bottomPositionButtonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		bottomPositionButtonPanel.add(bottomLeftButton);
		bottomPositionButtonPanel.add(bottomRightButton);
		topPanel.add(bottomPositionButtonPanel);

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 135D);
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

	}

	// visibility
	private void visShow() {
		earth.getGEPlugin().getNavigationControl().setVisibility(GEVisibility.VISIBILITY_SHOW);
	}

	private void visAuto() {
		earth.getGEPlugin().getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
	}

	private void visHide() {
		earth.getGEPlugin().getNavigationControl().setVisibility(GEVisibility.VISIBILITY_HIDE);
	}

	// position
	private void posTopLeft() {
		earth.getGEPlugin().getNavigationControl().getScreenXY().setXUnits(KmlUnits.UNITS_PIXELS);
		earth.getGEPlugin().getNavigationControl().getScreenXY().setYUnits(KmlUnits.UNITS_INSET_PIXELS);
	}

	private void posTopRight() {
		earth.getGEPlugin().getNavigationControl().getScreenXY().setXUnits(KmlUnits.UNITS_INSET_PIXELS);
		earth.getGEPlugin().getNavigationControl().getScreenXY().setYUnits(KmlUnits.UNITS_INSET_PIXELS);
	}

	private void posBottomLeft() {
		earth.getGEPlugin().getNavigationControl().getScreenXY().setXUnits(KmlUnits.UNITS_PIXELS);
		earth.getGEPlugin().getNavigationControl().getScreenXY().setYUnits(KmlUnits.UNITS_PIXELS);
	}

	private void posBottomRight() {
		earth.getGEPlugin().getNavigationControl().getScreenXY().setXUnits(KmlUnits.UNITS_INSET_PIXELS);
		earth.getGEPlugin().getNavigationControl().getScreenXY().setYUnits(KmlUnits.UNITS_PIXELS);
	}
}
