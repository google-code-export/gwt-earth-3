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
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
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
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#fetch_bad_kml">here</a>.
 * 
 * @author Nick
 *
 */
public class FetchBadKmlDemo implements EntryPoint {

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

        // add the widget to the DOM
        RootLayoutPanel.get().add(earth);

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

        // in this sample we will purposely attempt
        // to fetch a bad KML file (one that doesnt exist)
        String url = "http://www.google.com/fakePlacemark.kml";
        ge.fetchKml(url, new KmlLoadCallback(){
            @Override
            public void onLoaded(KmlObject feature) {
                if (feature == null) {
                    // defer display of alert to prevent deadlock in some browsers
                    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                        @Override
                        public void execute() {
                            Window.alert("Bad or null KML.");
                        }
                    });
                    return;
                }
                
                GEPlugin ge = earth.getGEPlugin();
                ge.getFeatures().appendChild(feature);
                KmlLookAt la = ge.createLookAt("");
                la.set(37.77976, -122.418307, 25, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 180, 60, 500);
                ge.getView().setAbstractView(la);
            }
        });
    }
}
