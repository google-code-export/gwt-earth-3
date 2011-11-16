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
 * Specifies a bounding box that describes an area of interest defined by
 * geographic coordinates and altitudes.
 * 
 * @author Nick
 * 
 */
public class KmlLatLonAltBox extends KmlLatLonBox {
    protected KmlLatLonAltBox() {
    }

    /**
     * Sets the north, south, east, west, rotation, minAltitude, maxAltitude,
     * and altitudeMode of bounding box.
     * 
     * @param north
     * @param south
     * @param east
     * @param west
     * @param rotation
     * @param minAltitude
     * @param maxAltitude
     * @param altitudeMode
     */
    public final void setAltBox(double north, double south, double east, double west, double rotation,
            float minAltitude, float maxAltitude, KmlAltitudeMode altitudeMode) {
        int alt = GEPluginConstants.get().toJavaScript(altitudeMode);
		setAltBoxNative(north, south, east, west, rotation, minAltitude,
				maxAltitude, alt);
    }

    /**
     * Sets the north, south, east, west, rotation, minAltitude, maxAltitude,
     * and altitudeMode of bounding box.
     * 
     * @param north
     * @param south
     * @param east
     * @param west
     * @param rotation
     * @param minAltitude
     * @param maxAltitude
     * @param altitudeMode
     */
    private final native void setAltBoxNative(double north, double south, double east, double west, double rotation,
            float minAltitude, float maxAltitude, int altitudeMode) /*-{
        this.setAltBox(north, south, east, west, rotation, minAltitude,
                maxAltitude, altitudeMode);
    }-*/;
    
    /**
     * 
     * @return Minimum altitude, specified in meters above sea level.
     */
    public final native float getMinAltitude() /*-{
		return this.getMinAltitude();
    }-*/;

    /**
     * 
     * @param minAltitude
     *            Minimum altitude, specified in meters above sea level.
     */
    public final native void setMinAltitude(float minAltitude) /*-{
		this.setMinAltitude(minAltitude);
    }-*/;

    /**
     * 
     * @return Maximum altitude, specified in meters above sea level.
     */
    public final native float getMaxAltitude() /*-{
		return this.getMaxAltitude();
    }-*/;

    /**
     * 
     * @param maxAltitude
     *            Maximum altitude, specified in meters above sea level.
     */
    public final native void setMaxAltitude(float maxAltitude) /*-{
		this.setMaxAltitude(maxAltitude);
    }-*/;

    /**
     * @return Specifies how the altitude property is interpreted.
     */
    public final KmlAltitudeMode getAltitudeMode() {
        return GEPluginConstants.get().toKmlAltitudeMode(getAltitudeModeNative());
    }

    /**
     * Specifies how the altitude property is interpreted.
     * @param altitudeMode
     */
    public final void setAltitudeMode(KmlAltitudeMode altitudeMode) {
        this.setAltitudeModeNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }

    /**
     * @return Specifies how the altitude property is interpreted.
     */
    private final native int getAltitudeModeNative() /*-{
        return this.getAltitudeMode();
    }-*/;

    /**
     * Specifies how the altitude property is interpreted.
     * @param altitudeMode
     */
    private final native void setAltitudeModeNative(int altitudeMode) /*-{
        this.setAltitudeMode(altitudeMode);
    }-*/;
}
