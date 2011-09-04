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
import com.nitrous.gwt.earth.client.api.GEHitTestMode;
import com.nitrous.gwt.earth.client.api.GEHitTestResult;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlEvent;
import com.nitrous.gwt.earth.client.api.KmlIcon;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.KmlStyle;
import com.nitrous.gwt.earth.client.api.KmlUnits;
import com.nitrous.gwt.earth.client.api.event.KmlEventListener;

/**
 * A GWT implementation of the demo found <a href="http://code.google.com/apis/ajax/playground/#hit-testing_%28x/y_to_lat/lon%29">here</a>.
 * 
 * This demo adds a click listener to the Google Earth globe and plots a placemark when the globe is clicked.
 * Depending on the type of object that is clicked, the placemark will be labeled 'B' for buildings, 'G' for ground and 'T' for terrain.
 * 
 * @author Nick
 * 
 */
public class HitTestingDemo implements EntryPoint {

	private GoogleEarthWidget earth;

	private KmlPlacemark globePlacemark = null;
	private KmlPlacemark terrainPlacemark = null;
	private KmlPlacemark buildingsPlacemark = null;

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
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);

		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);
		ge.enableLayer(GELayerId.LAYER_TERRAIN, true);
		ge.enableLayer(GELayerId.LAYER_BUILDINGS, true);

		KmlLookAt la = ge.createLookAt("");
		la.set(37.8018, -122.4060, 0, KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND, 15, 53, 227);
		ge.getView().setAbstractView(la);
		GoogleEarth.addEventListener(ge.getGlobe(), "click",
			new KmlEventListener() {
				@Override
				public void onEvent(KmlEvent event) {
					KmlMouseEvent mouseEvent = (KmlMouseEvent) event;
					if (mouseEvent.getButton() != 0) {
						return;
					}

					GEPlugin ge = earth.getGEPlugin();
					// remove old placemarks
					if (globePlacemark != null) {
						ge.getFeatures().removeChild(globePlacemark);
					}
					if (terrainPlacemark != null) {
						ge.getFeatures().removeChild(terrainPlacemark);
					}
					if (buildingsPlacemark != null) {
						ge.getFeatures().removeChild(buildingsPlacemark);
					}

					// hit test and create new placemarks
					GEHitTestResult hitTestResult = ge.getView().hitTest(
							mouseEvent.getClientX(), KmlUnits.UNITS_PIXELS,
							mouseEvent.getClientY(), KmlUnits.UNITS_PIXELS,
							GEHitTestMode.HIT_TEST_GLOBE);

					if (hitTestResult != null) {
						globePlacemark = makePlacemark(
								hitTestResult.getLatitude(),
								hitTestResult.getLongitude(),
								hitTestResult.getAltitude(),
								KmlAltitudeMode.ALTITUDE_ABSOLUTE, "G");
						ge.getFeatures().appendChild(globePlacemark);
					}

					hitTestResult = ge.getView().hitTest(
							mouseEvent.getClientX(), KmlUnits.UNITS_PIXELS,
							mouseEvent.getClientY(), KmlUnits.UNITS_PIXELS,
							GEHitTestMode.HIT_TEST_TERRAIN);

					if (hitTestResult != null) {
						terrainPlacemark = makePlacemark(
								hitTestResult.getLatitude(),
								hitTestResult.getLongitude(),
								hitTestResult.getAltitude(),
								KmlAltitudeMode.ALTITUDE_ABSOLUTE, "T");
						ge.getFeatures().appendChild(terrainPlacemark);
					}

					hitTestResult = ge.getView().hitTest(
							mouseEvent.getClientX(), KmlUnits.UNITS_PIXELS,
							mouseEvent.getClientY(), KmlUnits.UNITS_PIXELS,
							GEHitTestMode.HIT_TEST_BUILDINGS);

					if (hitTestResult != null) {
						buildingsPlacemark = makePlacemark(
								hitTestResult.getLatitude(),
								hitTestResult.getLongitude(),
								hitTestResult.getAltitude(),
								KmlAltitudeMode.ALTITUDE_ABSOLUTE, "B");
						ge.getFeatures().appendChild(buildingsPlacemark);
					}
				}
			});
	}

	private KmlPlacemark makePlacemark(double lat, double lng, double alt, KmlAltitudeMode altMode, String iconStr) {
		GEPlugin ge = earth.getGEPlugin();
		KmlIcon icon = ge.createIcon("");
		icon.setHref("http://maps.google.com/mapfiles/kml/paddle/" + iconStr + ".png");

		KmlStyle style = ge.createStyle("");
		style.getIconStyle().setIcon(icon);
		style.getIconStyle().getHotSpot()
				.set(0.5, KmlUnits.UNITS_FRACTION, 0, KmlUnits.UNITS_FRACTION);

		KmlPoint pt = ge.createPoint("");
		pt.set(lat, lng, alt, altMode, false, false);

		KmlPlacemark pm = ge.createPlacemark("");
		pm.setGeometry(pt);
		pm.setStyleSelector(style);

		return pm;
	}
}
