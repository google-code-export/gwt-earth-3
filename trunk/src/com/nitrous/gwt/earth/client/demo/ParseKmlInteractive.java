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
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#parse_kml_(interactive)">here</a>.
 * 
 * @author Nick
 *
 */
public class ParseKmlInteractive implements EntryPoint {

	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

    private GoogleEarthWidget earth;
    private TextArea kmlBox;
    private KmlObject currentKmlObject;
	
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

        kmlBox = new TextArea();
        kmlBox.getElement().setAttribute("rows", "10");
        kmlBox.getElement().setAttribute("cols", "70");
        kmlBox.getElement().setAttribute("wrap", "off");
        kmlBox.getElement().setAttribute("style", "font-family: monospace; font-size: small");
        kmlBox.setValue("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
            "  <Placemark>\n" +
            "    <name>Test Placemark</name>\n" +
            "    <Point>\n" +
            "      <coordinates>\n" +
            "        -122,37,0\n" +
            "      </coordinates>\n" +
            "    </Point>\n" +
            "  </Placemark>\n" +
            "</kml>");
        
        Button parseKmlButton = new Button("Parse and Add this KML!");
        parseKmlButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                parseKmlFromTextArea();
            }
        });
        
        HorizontalPanel textPanel = new HorizontalPanel();
        textPanel.setSpacing(5);
        textPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        textPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        textPanel.add(kmlBox);
        textPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        textPanel.add(parseKmlButton);
                
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(textPanel, 220);
        layout.add(earth);
        // add the widget to the DOM
        RootLayoutPanel.get().add(layout);

        // begin loading the Google Earth Plug-in
        earth.init();
    }

    private void parseKmlFromTextArea() {
        GEPlugin ge = earth.getGEPlugin();
        
        // remove the old parsed KML object if one exists
        if (currentKmlObject != null) {
            ge.getFeatures().removeChild(currentKmlObject);
        }

        String kmlString = kmlBox.getValue();

        // parse the text in the box and add it to Earth
        try {
            currentKmlObject = ge.parseKml(kmlString);
            ge.getFeatures().appendChild(currentKmlObject);
        } catch (Exception ex) {
            Window.alert("Parse error");
        }
    }
                
    /**
     * Display content on the map
     */
    private void loadMapContent() {
        // The GEPlugin is the core class and is a great place to start browsing the API
        GEPlugin ge = earth.getGEPlugin();
        ge.getWindow().setVisibility(true);
        
        // add a navigation control
        ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
        
        // show some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
        
        // look at the San Francisco
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
}
