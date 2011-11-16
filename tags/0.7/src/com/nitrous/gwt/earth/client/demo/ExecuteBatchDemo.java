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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.BatchFunction;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlCoordArray;
import com.nitrous.gwt.earth.client.api.KmlLineString;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;

/**
 * This demo illustrates the performance difference that can be obtained by taking advantage of batch execution.
 * 
 * A GWT implementation of the demo found <a href="http://code.google.com/p/earth-api-samples/source/browse/trunk/examples/batch.html?r=108">here</a>.
 * 
 * @author Nick
 *
 */
public class ExecuteBatchDemo implements EntryPoint {
	
	/** To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRhjoUoh2xAXb7lvbOvvJrsDayXvg";

    private GoogleEarthWidget earth;
    private KmlPlacemark placemark;
    private HTML batchedResult;
    private HTML unbatchedResult;
	
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

        Button runBatchedButton = new Button("Batched calls");
        runBatchedButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                runBatched();
            }
        });
        
        Button runUnbatchedButton = new Button("Unbatched calls");
        runUnbatchedButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                runUnbatched();
            }
        });

        Button clearButton = new Button("Clear map");
        clearButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                clear();
            }
        });
        
        batchedResult = new HTML("&nbsp;");
        unbatchedResult = new HTML("&nbsp;");
        FlexTable resultTable = new FlexTable();
        resultTable.setBorderWidth(1);
        for (int row = 0 ; row < 2; row++) {
            for (int col = 0 ; col < 2 ; col++) {
                resultTable.getCellFormatter().setHeight(row, col, "20px");
                resultTable.getCellFormatter().setHorizontalAlignment(row, col,  HasHorizontalAlignment.ALIGN_CENTER);
            }
        }
        resultTable.setWidget(0, 0, runUnbatchedButton);
        resultTable.setWidget(1, 0, unbatchedResult);
        resultTable.setWidget(0, 1, runBatchedButton);
        resultTable.setWidget(1, 1, batchedResult);
        
        HorizontalPanel topPanel = new HorizontalPanel();
        topPanel.setWidth("100%");
        topPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        topPanel.add(resultTable);
        topPanel.add(clearButton);
        
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(topPanel, 80D);
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

    }

    /**
     * Clear the map
     */
    private void clear() {
        if (placemark != null) {
            earth.getGEPlugin().getFeatures().removeChild(placemark);
            placemark = null;
        }
    }
    
    private void runUnbatched() {
        // clear the map
        clear();
        
        // Measure run time.
        long startTime = System.currentTimeMillis();
        
        // execute the batch 
        batch.run(earth.getGEPlugin());
        
        // display the resulting execution time
        long duration = System.currentTimeMillis() - startTime;
        unbatchedResult.setHTML(duration+"ms");
    }
    
    private void runBatched() {
        // clear the map
        clear();
        
        //  Measure run time.
        long startTime = System.currentTimeMillis();
        
        // Run the function batched
        // NOTE: google.earth.executeBatch is guaranteed to be synchronous.
        earth.getGEPlugin().executeBatch(batch);

        // display the resulting execution time
        long duration = System.currentTimeMillis() - startTime;
        batchedResult.setHTML(duration+"ms");
    }
    
    private BatchFunction batch = new BatchFunction() {
        @Override
        public void run(GEPlugin ge) {
            // Create a swirly line string with 1000 coordinates, all in
            // JavaScript.
            KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
            placemark = ge.createPlacemark("");
            KmlLineString lineString = ge.createLineString("");
            lineString.setTessellate(true);

            KmlCoordArray coords = lineString.getCoordinates();
            for (int i = 0; i < 1000; i++) {
                coords.pushLatLngAlt(lookAt.getLatitude() + Math.cos(i / 10) * i / 10,
                        lookAt.getLongitude() + Math.sin(i / 10) * i / 10, 0);
            }

            placemark.setGeometry(lineString);
            ge.getFeatures().appendChild(placemark);
        }
    };
}
