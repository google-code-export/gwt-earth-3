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
 * A single tuple consisting of floating point values for longitude, latitude,
 * and altitude (in that order). Longitude and latitude values are in degrees.
 * 
 * <pre>
 * <li>longitude = -180 and &lt;= 180</li> 
 * <li>latitude = -90 and = 90</li> 
 * <li>altitude values (optional) are in meters above sea level</li>
 * </pre>
 * 
 * @author Nick
 * 
 */
public class KmlCoord extends KmlObjectBase {
    protected KmlCoord() {
    }

    /**
     * Sets the latitude, longitude, altitude.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     */
    public final native void setLatLngAlt(double latitude, double longitude, double altitude) /*-{
		this.setLatLngAlt(latitude, longitude, altitude);
    }-*/;

    /**
     * 
     * @return Degrees north or south of the Equator (0 degrees). Values range
     *         from -90 degrees (South Pole) to 90 degrees (North Pole).
     */
    public final native double getLatitude()/*-{
		return this.getLatitude();
    }-*/;

    /**
     * 
     * @param latitude
     *            Degrees north or south of the Equator (0 degrees). Values
     *            range from -90 degrees (South Pole) to 90 degrees (North
     *            Pole).
     */
    public final native void setLatitude(double latitude)/*-{
		this.setLatitude(latitude);
    }-*/;

    /**
     * 
     * @return Angular distance in degrees, relative to the Prime Meridian.
     *         Values west of the Meridian range from -180 to 0 degrees. Values
     *         east of the Meridian range from 0 to 180 degrees.
     */
    public final native double getLongitude()/*-{
		return this.getLongitude();
    }-*/;

    /**
     * 
     * @param longitude
     *            Angular distance in degrees, relative to the Prime Meridian.
     *            Values west of the Meridian range from -180 to 0 degrees.
     *            Values east of the Meridian range from 0 to 180 degrees.
     */
    public final native void setLongitude(double longitude)/*-{
		this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return Distance from the earth's surface.
     */
    public final native double getAltitude()/*-{
		return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude
     *            Distance from the earth's surface.
     */
    public final native void setAltitude(double altitude)/*-{
		this.setAltitude(altitude);
    }-*/;

}
