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

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;
import com.nitrous.gwt.earth.client.api.event.KmlLoadCallback;

/**
 * A GWT implementation of the demo found here: http://code.google.com/apis/ajax/playground/#fetch_kml_(interactive,_checkboxes)
 * 
 * @author Nick
 *
 */
public class FetchKmlInteractiveCheckboxes implements EntryPoint {

    private GoogleEarthWidget earth;
    
    // a lookup from color to the corresponding KmlObject.
    private Map<String, KmlObject> currentKmlObjects;
    
    // a lookup from color to the corresponding CheckBox
    private Map<String, CheckBox> checkBoxes;
    
    private CheckBox red;
    private CheckBox yellow;
    private CheckBox green;
    
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

        currentKmlObjects = new HashMap<String, KmlObject>();
        
        checkBoxes = new HashMap<String, CheckBox>();
        red = new CheckBox("Red Placemarks");
        red.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                toggleKml("red");
            }
        });
        checkBoxes.put("red", red);
        
        yellow = new CheckBox("Yellow Placemarks");
        yellow.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                toggleKml("yellow");
            }
        });
        checkBoxes.put("yellow", yellow);
        
        green = new CheckBox("Green Placemarks");
        green.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                toggleKml("green");
            }
        });
        checkBoxes.put("green", green);

        
        HorizontalPanel header = new HorizontalPanel();
        header.setSpacing(5);
        header.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        header.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        header.add(new HTML("<H1>Toggle KML Files:</H1>"));
        
        VerticalPanel checkBoxes = new VerticalPanel();
        checkBoxes.setSpacing(5);
        checkBoxes.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        checkBoxes.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        checkBoxes.add(red);
        checkBoxes.add(yellow);
        checkBoxes.add(green);
        header.add(checkBoxes);
                
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(header, 100);
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
        
        // show some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);
        
        // fly to Santa Cruz
        KmlLookAt la = ge.createLookAt("");
        la.set(37, -122, 
                0, // altitude                
                KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
                0, // heading
                0, // straight-down tilt
                5000 // range (inverse of zoom)
        );
        ge.getView().setAbstractView(la);       
        
         // if the page loaded with checkboxes checked, load the appropriate KML files
        if (Boolean.TRUE.equals(red.getValue())) {
            loadKml("red");
        }
        if (Boolean.TRUE.equals(green.getValue())) {
            loadKml("green");
        }
        if (Boolean.TRUE.equals(yellow.getValue())) {
            loadKml("yellow");
        }
    }
    
    private void toggleKml(String file) {
        GEPlugin ge = earth.getGEPlugin();
        
        // remove the old KML object if it exists
        KmlObject old = currentKmlObjects.get(file);
        if (old != null) {
            ge.getFeatures().removeChild(old);
            currentKmlObjects.remove(file);
        }
        
        // if the checkbox is checked, fetch the KML and show it on Earth
        CheckBox checkBox = checkBoxes.get(file);
        if (checkBox != null && Boolean.TRUE.equals(checkBox.getValue())) {
            loadKml(file);
        }
    }
    
    private void loadKml(final String file) {
        String kmlUrl = "http://earth-api-samples.googlecode.com/svn/trunk/examples/static/" + file + ".kml";

        // fetch the KML
        final GEPlugin ge = earth.getGEPlugin();
        ge.fetchKml(kmlUrl, new KmlLoadCallback() {
            @Override
            public void onLoaded(KmlObject kmlObject) {
                if (kmlObject != null) {
                    // show it on Earth
                    currentKmlObjects.put(file, kmlObject);
                    ge.getFeatures().appendChild(kmlObject);
                } else {
                    // bad KML
                    currentKmlObjects.remove(file);
                    
                    // defer display of alert to prevent deadlock in some browsers
                    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                        @Override
                        public void execute() {
                            Window.alert("Bad or null KML.");
                        }
                    });

                    // uncheck the box
                    CheckBox cb = checkBoxes.get(file);
                    if (cb != null) {
                        cb.setValue(false, false);
                    }
                }
            }
        });
    }
}
