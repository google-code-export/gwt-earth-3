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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GEHtmlStringBalloon;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlObject;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.MouseClickListener;

/**
 * A demo that shows how to access ExtendedData in the Kml Document.
 * 
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#extended_data_in_balloons">here</a>.
 * 
 * @author nick
 * 
 */
public class KmlExtendedDataDemo implements EntryPoint {
	private GoogleEarthWidget earth;
	
    public void onModuleLoad() {
    	// Load the Earth API
    	GoogleEarth.loadApi(new Runnable(){
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

		// add a placemark using KML
		String kmlString =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<kml xmlns=\"http://www.opengis.net/kml/2.2\">" +
			"  <Document>" +
			"    <Style id=\"bstyle\">" +
			"      <BalloonStyle>" +
			"        <text>" +
			"          <![CDATA[" +
			"            Species is $[species]<br>" +
			"            Age is: $[age]" +
			"          ]]>" +
			"        </text>" +
			"      </BalloonStyle>" +
			"    </Style>" +
			"    <Placemark id=\"example_placemark\">" +
			"      <styleUrl>#bstyle</styleUrl>" +
			"      <name>Placemark 1</name>" +
			"      <description>" +
			"        <![CDATA[<a href=\"#\" onclick=\"alert('Running some JavaScript!');\">Alert!</a><br>]]>" +
			"      </description>" +
			"      <ExtendedData>" +
			"        <Data name=\"species\">" +
			"          <value>Badger</value>" +
			"        </Data>" +
			"        <Data name=\"age\">" +
			"          <value><![CDATA[<a href=\"#\" onclick=\"alert(3);\"> Click to see</a><br>]]></value>" +
			"        </Data>" +
			"      </ExtendedData>" +
			"      <Point>" +
			"        <coordinates>-122.4226,37.7792,0</coordinates>" +
			"      </Point>" +
			"    </Placemark>" +
			"  </Document>" +
			"</kml>";
		GWT.log(kmlString);
		
		// parse the kml and add to the map
		KmlObject kmlObject = ge.parseKml(kmlString);
		ge.getFeatures().appendChild(kmlObject);
		
		// extract the KmlPlacemark from the map by ID
		KmlPlacemark placemark = (KmlPlacemark)ge.getElementById("example_placemark");
		
		  // look at the placemark we created
		KmlPoint point = (KmlPoint)placemark.getGeometry();		
		KmlLookAt la = ge.createLookAt("");
		la.set(point.getLatitude(), point.getLongitude(),
		    0, // altitude
		    KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
		    0, // heading
		    0, // straight-down tilt
		    5000 // range (inverse of zoom)
		    );
		ge.getView().setAbstractView(la);
		
		// set a click listener that affects all placemarks
		ge.getGlobe().addMouseClickListener(new MouseClickListener(){

			@Override
			public void onClick(KmlMouseEvent event) {
				KmlObject obj = (KmlObject) event.getTarget();
				if ("KmlPlacemark".equals(obj.getType())) {
					GEPlugin ge = earth.getGEPlugin();
					event.preventDefault();
					KmlPlacemark placemark = (KmlPlacemark) obj;
					String placemark_name = placemark.getName();
					// get the full balloon html
					String placemark_desc_active = placemark.getBalloonHtmlUnsafe();
					// same as above, except with 'active' content like JS stripped out
					String placemark_desc = placemark.getBalloonHtml();
					// create new balloon with rendered content
					GEHtmlStringBalloon balloon = ge.createHtmlStringBalloon("");
					balloon.setMaxWidth(300);
					balloon.setContentString("<h3>" + placemark_name + "</h3>" + placemark_desc_active);
					ge.setBalloon(balloon);
				}
			}

			@Override
			public void onDoubleClick(KmlMouseEvent event) {
			}

			@Override
			public void onMouseDown(KmlMouseEvent event) {
			}

			@Override
			public void onMouseUp(KmlMouseEvent event) {
			}
		});
	}

}
