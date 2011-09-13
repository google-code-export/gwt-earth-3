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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlScreenOverlay;
import com.nitrous.gwt.earth.client.api.KmlUnits;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#screen_overlay_%28frame%29">here</a>.
 */
public class ScreenOverlayDemo implements EntryPoint {
    private GoogleEarthWidget earth;
    
    public void onModuleLoad() {
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

        HorizontalPanel header = new HorizontalPanel();
        Button createButton = new Button("Create a Screen Overlay");
        createButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	createScreenOverlay();
            }
        });
        header.add(createButton);
        
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(header, 30);
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
        GEPlugin ge = earth.getGEPlugin();
        ge.getWindow().setVisibility(true);
        
        // add a navigation control
        ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
  
        // add some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
    }
    
    private void createScreenOverlay() {
        // plot a placemark
        GEPlugin ge = earth.getGEPlugin();
        
        KmlScreenOverlay screenOverlay = ge.createScreenOverlay("");
        screenOverlay.setIcon(ge.createIcon(""));
        screenOverlay.getIcon().setHref("http://www.google.com/intl/en_ALL/images/logo.gif");

        // Set the point inside the overlay that is used as the positioning
        // anchor point.
        screenOverlay.getOverlayXY().setXUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getOverlayXY().setYUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getOverlayXY().setX(.5);
        screenOverlay.getOverlayXY().setY(.5);

        // Set screen position in fractions.
        screenOverlay.getScreenXY().setXUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getScreenXY().setYUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getScreenXY().setX(Math.random());  // Random x.
        screenOverlay.getScreenXY().setY(Math.random());  // Random y.

        // Rotate around object's center point.
        screenOverlay.getRotationXY().setXUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getRotationXY().setYUnits(KmlUnits.UNITS_FRACTION);
        screenOverlay.getRotationXY().setX(0.5);
        screenOverlay.getRotationXY().setY(0.5);

        // Set object's size in pixels.
        screenOverlay.getSize().setXUnits(KmlUnits.UNITS_PIXELS);
        screenOverlay.getSize().setYUnits(KmlUnits.UNITS_PIXELS);
        screenOverlay.getSize().setX(300);
        screenOverlay.getSize().setY(90);

        // Rotate by a random number of degrees.
        screenOverlay.setRotation(Math.random() * 360);

        ge.getFeatures().appendChild(screenOverlay);
    }
}

