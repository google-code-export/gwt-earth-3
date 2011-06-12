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
package com.nitrous.gwt.earth.client.api;

/**
 * Specifies the exact coordinates of the Model's origin in latitude, longitude,
 * and altitude. Latitude and longitude measurements are standard lat-lon
 * projection with WGS84 datum. Altitude is distance above the earth's surface,
 * in meters, and is interpreted according to altitudeMode.
 * 
 * @author Nick
 * 
 */
public class KmlLocation extends KmlObject {
    protected KmlLocation() {
    }

    /**
     * Sets the latitude, longitude, and altitude of the Model.
     * 
     * @param lat
     * @param lng
     * @param alt
     */
    public final native void setLatLngAlt(double lat, double lng, double alt) /*-{
		this.setLatLngAlt(lat, lng, alt);
    }-*/;

    /**
     * @return Longitude of the Model's location. Angular distance in degrees,
     *         relative to the Prime Meridian. Values west of the Meridian range
     *         from -180 to 0 degrees. Values east of the Meridian range from 0
     *         to 180 degrees.
     */
    public final native double getLongitude() /*-{
		return this.getLongitude();
    }-*/;

    /**
     * 
     * @param longitude
     *            Longitude of the Model's location. Angular distance in
     *            degrees, relative to the Prime Meridian. Values west of the
     *            Meridian range from -180 to 0 degrees. Values east of the
     *            Meridian range from 0 to 180 degrees.
     */
    public final native void setLongitude(double longitude) /*-{
		this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return Latitude of the camera location. Degrees north or south of the
     *         Equator (0 degrees). Values range from -90 degrees (South Pole)
     *         to 90 degrees (North Pole).
     */
    public final native double getLatitude() /*-{
		return this.getLatitude();
    }-*/;

    /**
     * 
     * @param latitude
     *            Latitude of the camera location. Degrees north or south of the
     *            Equator (0 degrees). Values range from -90 degrees (South
     *            Pole) to 90 degrees (North Pole).
     */
    public final native void setLatitude(double latitude) /*-{
		this.setLatitude(latitude);
    }-*/;

    /**
     * Specifies the distance above the earth's surface.
     * 
     * @return
     */
    public final native double getAltitude() /*-{
		return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude
     *            Specifies the distance above the earth's surface.
     */
    public final native void setAltitude(double altitude) /*-{
		this.setAltitude(altitude);
    }-*/;
}
