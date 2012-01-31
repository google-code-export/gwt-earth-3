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
 * A geographic location defined by longitude, latitude, and (optional)
 * altitude. When a Point is contained by a Placemark, the point itself
 * determines the position of the Placemark's name and icon. When a Point is
 * extruded, it is connected to the ground with a line. This tether uses the
 * current LineStyle.
 * 
 * @author Nick
 * 
 */
public class KmlPoint extends KmlExtrudableGeometry {
    protected KmlPoint() {
    }

    /**
     * Sets altitudeMode, extrude, tessellate, latitude, longitude, and altitude values.
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param extrude
     * @param tessellate
     */
    public final void set(double latitude, double longitude, double altitude, KmlAltitudeMode altitudeMode,
            boolean extrude, boolean tessellate) {
        int altMode = GEPluginConstants.get().toJavaScript(altitudeMode);
        this.setNative(latitude, longitude, altitude, altMode, extrude, tessellate);
    }

    /**
     * Sets altitudeMode, extrude, tessellate, latitude, longitude, and altitude values.
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param extrude
     * @param tessellate
     */
    private final native void setNative(double latitude, double longitude, double altitude, int altitudeMode,
            boolean extrude, boolean tessellate)/*-{
        this.set(latitude, longitude, altitude, altitudeMode, extrude, tessellate);
    }-*/;
    
    /**
     * Sets the latitude and longitude.
     * @param latitude
     * @param longitude
     */
    public final native void setLatLng(double latitude, double longitude)/*-{
        this.setLatLng(latitude, longitude);
    }-*/;

    /**
     * Sets the latitude, longitude, and altitide.
     * @param latitude
     * @param longitude
     * @param altitude
     */
    public final native void setLatLngAlt(double latitude, double longitude, double altitude)/*-{
        this.setLatLngAlt(latitude, longitude, altitude);
    }-*/;

    /**
     * 
     * @return The point's latitude, in degrees.
     */
    public final native double getLatitude()/*-{
        return this.getLatitude();
    }-*/;

    /**
     * 
     * @param latitude The point's latitude, in degrees.
     */
    public final native void setLatitude(double latitude)/*-{
        this.setLatitude(latitude);
    }-*/;

    /**
     * 
     * @return The point's longitude, in degrees
     */
    public final native double getLongitude()/*-{
        return this.getLongitude();
    }-*/;

    /**
     * 
     * @param longitude The point's longitude, in degrees
     */
    public final native void setLongitude(double longitude)/*-{
        this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return The point's altitude, in meters.
     */
    public final native double getAltitude()/*-{
        return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude The point's altitude, in meters.
     */
    public final native void setAltitude(double altitude)/*-{
        this.setAltitude(altitude);
    }-*/;

}
