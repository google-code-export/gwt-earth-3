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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlGeometry;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.MouseListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#draggable_placemark">here</a>.
 */
public class DraggablePlacemarkDemo implements EntryPoint {

    private GoogleEarthWidget earth;

    private DragInfo dragInfo;
    
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

        // Create the placemark
        KmlPlacemark placemark = ge.createPlacemark("");
        
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
        placemark.setName("Drag Me!");

        // listen for mousedown on the window (look specifically for point placemarks)
        ge.getWindow().addMouseListener(new MouseListener() {
            @Override
            public void onMouseDown(KmlMouseEvent event) {
                // listen for mousedown on the window (look specifically for point placemarks)
                 if ("KmlPlacemark".equals(event.getTarget().getType())) {  
                     KmlPlacemark placemark = (KmlPlacemark)event.getTarget();
                     KmlGeometry geo = placemark.getGeometry();
                     if ("KmlPoint".equals(geo.getType())) {
                         dragInfo = new DragInfo(placemark, false);
                     }
                 }
            }
            
            @Override
            public void onMouseMove(KmlMouseEvent event) {
                // listen for mousemove on the globe
                if (dragInfo != null) {
                    event.preventDefault();
                    KmlPoint point = (KmlPoint)dragInfo.placemark.getGeometry();
                    point.setLatitude(event.getLatitude());
                    point.setLongitude(event.getLongitude());
                    dragInfo.dragged = true;
                }
            }
            
            @Override
            public void onMouseUp(KmlMouseEvent event) {
                // listen for mouseup on the window
                if (dragInfo != null) {
                    if (dragInfo.dragged) {
                        // if the placemark was dragged, prevent balloons from popping up
                        event.preventDefault();
                    }
                    dragInfo = null;
                }
            }
            
            @Override
            public void onMouseOver(KmlMouseEvent event) {
            }
            @Override
            public void onMouseOut(KmlMouseEvent event) {
            }
            @Override
            public void onDoubleClick(KmlMouseEvent event) {
            }
            @Override
            public void onClick(KmlMouseEvent event) {
            }
        });
    }

    private static class DragInfo {
        KmlPlacemark placemark;
        boolean dragged;
        DragInfo(KmlPlacemark placemark, boolean dragged) {
            this.placemark = placemark;
            this.dragged = dragged;
        }
    }
}
