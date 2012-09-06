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
package com.nitrous.gwt.earth.client.api;


/**
 * The GEGlobe class encapsulates the Google Earth globe to determine access and
 * event behavior.
 * 
 * @author Nick
 * 
 */
public class GEGlobe extends KmlObject {
	protected GEGlobe() {
	}

	/**
	 * Returns the altitude for a given location on the globe. If the altitude
	 * data for the location has not yet been loaded, the return value is 0.
	 * 
	 * @param lat
	 *            latitude
	 * @param lon
	 *            longitude
	 * @return Returns the altitude for a given location on the globe. If the altitude
	 * data for the location has not yet been loaded, the return value is 0.
	 */
	public final native double getGroundAltitude(double lat, double lon) /*-{
		return this.getGroundAltitude(lat, lon);
	}-*/;

	/**
	 * 
	 * @return The top-level features currently in the Earth instance.
	 */
	public final native GEFeatureContainer getFeatures() /*-{
		return this.getFeatures();
	}-*/;
}
