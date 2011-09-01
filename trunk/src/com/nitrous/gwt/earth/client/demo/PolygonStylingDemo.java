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
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlColor;
import com.nitrous.gwt.earth.client.api.KmlCoordArray;
import com.nitrous.gwt.earth.client.api.KmlLineStyle;
import com.nitrous.gwt.earth.client.api.KmlLinearRing;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPolygon;
import com.nitrous.gwt.earth.client.api.KmlStyle;

/**
 * A GWT implementation of the demo found here: http://code.google.com/apis/ajax/playground/#polygon_styling
 */
public class PolygonStylingDemo implements EntryPoint {

    private GoogleEarthWidget earth;
    
    private KmlPlacemark polygonPlacemark;
    
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
        Button createButton = new Button("Stylize the Polygon!");
        createButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                stylizePolygon();
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
        
        polygonPlacemark = ge.createPlacemark("");
        KmlPolygon polygon = ge.createPolygon("");
        polygonPlacemark.setGeometry(polygon);
        KmlLinearRing outer = ge.createLinearRing("");
        polygon.setOuterBoundary(outer);

        // Square outer boundary.
        KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
        KmlCoordArray coords = outer.getCoordinates();
        double lat = lookAt.getLatitude();
        double lon = lookAt.getLongitude();
        coords.pushLatLngAlt(lat - .05, lon - .05, 0);
        coords.pushLatLngAlt(lat - .05, lon + .05, 0);
        coords.pushLatLngAlt(lat + .05, lon + .05, 0);
        coords.pushLatLngAlt(lat + .05, lon - .05, 0);

        // Another square as the inner boundary.  Note that we can create
        // any number of inner boundaries.
        KmlLinearRing innerBoundary = ge.createLinearRing("");
        polygon.getInnerBoundaries().appendChild(innerBoundary);
        coords = innerBoundary.getCoordinates();
        coords.pushLatLngAlt(lat - .02, lon - .02, 0);
        coords.pushLatLngAlt(lat - .02, lon + .02, 0);
        coords.pushLatLngAlt(lat + .02, lon + .02, 0);
        coords.pushLatLngAlt(lat + .02, lon - .02, 0);

        ge.getFeatures().appendChild(polygonPlacemark);

        // zoom in to the linestring we created
        lookAt.setRange(50000);
        ge.getView().setAbstractView(lookAt);
    }
    
	private void stylizePolygon() {
	    GEPlugin ge = earth.getGEPlugin();
	    
	    // If polygonPlacemark doesn't already have a Style associated
	    // with it, we create it now.
	    if (polygonPlacemark.getStyleSelector() == null) {
	        polygonPlacemark.setStyleSelector(ge.createStyle(""));
	    }

        // The Style of a Feature is retrieved as
        // feature.getStyleSelector().  The Style itself contains a
        // LineStyle and a PolyStyle, which are what we manipulate to change
        // the line color, line width, and inner color of the polygon.
	    KmlStyle style = (KmlStyle)polygonPlacemark.getStyleSelector();
	    KmlLineStyle lineStyle = style.getLineStyle();
	    lineStyle.setWidth(lineStyle.getWidth() + 2);
	    
	    // Color is specified in 'aabbggrr' format.
	    lineStyle.getColor().set("66ff0000");

	    // Color can also be specified by individual color components.
	    KmlColor polyColor = style.getPolyStyle().getColor();
	    polyColor.setA(200);
	    polyColor.setB(0);
	    polyColor.setG(255);
	    polyColor.setR(255);
	}	
	
}
