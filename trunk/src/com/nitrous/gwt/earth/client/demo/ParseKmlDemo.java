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
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlObject;

/**
 * This demo shows how to parse KML.
 * 
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#parse_kml">here</a>.
 * 
 * @author Nick
 *
 */
public class ParseKmlDemo implements EntryPoint {

	/** 
	 * TODO: Replace EARTH_API_KEY with a key generated for your domain.
	 * 
	 * To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html 
	 */
	private static final String EARTH_API_KEY = "ABQIAAAAfdPr40ksX4gg7ApZBtLBdBTJY-9JfItWUGQPJaUDtRwwITcegRRUqEyZAfd8MUbdcg1_osKHEIdPMg";

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

        // add the widget to the DOM
        RootLayoutPanel.get().add(earth);

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

		// Sample KML taken from
		//   http://code.google.com/apis/kml/documentation/kml_tut.html#polygons
		KmlObject pentagon = ge.parseKml(
		    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		    "<kml xmlns=\"http://www.opengis.net/kml/2.2\">" +
		    "  <Placemark>" +
		    "    <name>The Pentagon</name>" +
		    "    <Polygon>" +
		    "      <extrude>1</extrude>" +
		    "      <altitudeMode>relativeToGround</altitudeMode>" +
		    "      <outerBoundaryIs>" +
		    "        <LinearRing>" +
		    "          <coordinates>" +
		    "            -77.05788457660967,38.87253259892824,100 " +
		    "            -77.05465973756702,38.87291016281703,100 " +
		    "            -77.05315536854791,38.87053267794386,100 " +
		    "            -77.05552622493516,38.868757801256,100 " +
		    "            -77.05844056290393,38.86996206506943,100 " +
		    "            -77.05788457660967,38.87253259892824,100" +
		    "          </coordinates>" +
		    "        </LinearRing>" +
		    "      </outerBoundaryIs>" +
		    "      <innerBoundaryIs>" +
		    "        <LinearRing>" +
		    "          <coordinates>" +
		    "            -77.05668055019126,38.87154239798456,100 " +
		    "            -77.05542625960818,38.87167890344077,100 " +
		    "            -77.05485125901024,38.87076535397792,100 " +
		    "            -77.05577677433152,38.87008686581446,100 " +
		    "            -77.05691162017543,38.87054446963351,100 " +
		    "            -77.05668055019126,38.87154239798456,100" +
		    "          </coordinates>" +
		    "        </LinearRing>" +
		    "      </innerBoundaryIs>" +
		    "    </Polygon>" +
		    "  </Placemark>" +
		    "</kml>");

		ge.getFeatures().appendChild(pentagon);

		// Fly to the Pentagon
		KmlLookAt la = ge.createLookAt("");
		la.set(38.867, -77.0565, 500, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 0, 45, 900);
		ge.getView().setAbstractView(la);    
	}

}
