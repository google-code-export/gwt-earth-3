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
 * Defines the camera that views the scene. This element defines the position of
 * the camera relative to the Earth's surface as well as the viewing direction
 * of the camera. The camera position is defined by longitude, latitude,
 * altitude, and altitudeMode. The viewing direction of the camera is defined by
 * heading, tilt, and roll. Camera can be a child element of any feature.
 * 
 * @author Nick
 * 
 */
public class KmlCamera extends KmlAbstractView {
    protected KmlCamera() {
    }

    /**
     * Sets the latitude, longitude, altitude, alitudeMode, heading, tilt, and
     * roll values.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param heading
     * @param tilt
     * @param roll
     */
    public final void set(double latitude, double longitude, double altitude, KmlAltitudeMode altitudeMode,
            double heading, double tilt, double roll) {
		setNative(latitude, longitude, altitude, GEPluginConstants.get().toJavaScript(altitudeMode), heading, tilt,
				roll);
    }

    /**
     * Sets the latitude, longitude, altitude, alitudeMode, heading, tilt, and
     * roll values.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param heading
     * @param tilt
     * @param roll
     */
    private final native void setNative(double latitude, double longitude, double altitude, int altitudeMode,
            double heading, double tilt, double roll) /*-{
        this.set(latitude, longitude, altitude, altitudeMode, heading, tilt,
                roll);
    }-*/;
    
    /**
     * @return Latitude of the camera location. Degrees north or south of the
     *         Equator (0 degrees). Values range from -90 degrees to 90 degrees.
     */
    public final native double getLatitude() /*-{
		return this.getLatitude();
    }-*/;

    /**
     * @param latitude
     *            Latitude of the camera location. Degrees north or south of the
     *            Equator (0 degrees). Values range from -90 degrees to 90
     *            degrees.
     */
    public final native void setLatitude(double latitude) /*-{
		this.setLatitude(latitude);
    }-*/;

    /**
     * 
     * @return Longitude of the camera location. Angular distance in degrees,
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
     *            Longitude of the camera location. Angular distance in degrees,
     *            relative to the Prime Meridian. Values west of the Meridian
     *            range from -180 to 0 degrees. Values east of the Meridian
     *            range from 0 to 180 degrees.
     */
    public final native void setLongitude(double longitude) /*-{
		this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return Distance from the earth's surface.
     */
    public final native double getAltitude() /*-{
		return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude
     *            Distance from the earth's surface.
     */
    public final native void setAltitude(double altitude) /*-{
		this.setAltitude(altitude);
    }-*/;

    /**
     * 
     * @return Direction (that is, North, South, East, West), in degrees.
     *         Default=0 (North). Values range from 0 to 360 degrees.
     */
    public final native double getHeading() /*-{
		return this.getHeading();
    }-*/;

    /**
     * 
     * @param heading
     *            Direction (that is, North, South, East, West), in degrees.
     *            Default=0 (North). Values range from 0 to 360 degrees.
     */
    public final native void setHeading(double heading) /*-{
		this.setHeading(heading);
    }-*/;

    /**
     * 
     * @return Angle between the direction of the camera position and the normal
     *         to the surface of the earth. Values range from 0 to 360 degrees.
     *         A tilt value of 0 degrees indicates viewing from directly above,
     *         90 degrees indicates viewing along the horizon, and 180 degrees
     *         indicates viewing straight up at the sky.
     */
    public final native double getTilt() /*-{
		return this.getTilt();
    }-*/;

    /**
     * 
     * @param tilt
     *            Angle between the direction of the camera position and the
     *            normal to the surface of the earth. Values range from 0 to 360
     *            degrees. A tilt value of 0 degrees indicates viewing from
     *            directly above, 90 degrees indicates viewing along the
     *            horizon, and 180 degrees indicates viewing straight up at the
     *            sky.
     */
    public final native void setTilt(double tilt) /*-{
		this.setTilt(tilt);
    }-*/;

    /**
     * 
     * @return Rotation, in degrees, of the camera around the Z axis. Values
     *         range from -180 to +180 degrees.
     */
    public final native double getRoll() /*-{
		return this.getRoll();
    }-*/;

    /**
     * 
     * @param roll
     *            Rotation, in degrees, of the camera around the Z axis. Values
     *            range from -180 to +180 degrees.
     */
    public final native void setRoll(double roll) /*-{
		this.setRoll(roll);
    }-*/;

    /**
     * 
     * @return Specifies how altitude components in the coordinates are
     *         interpreted.
     */
    public final KmlAltitudeMode getAltitudeMode() {
		return GEPluginConstants.get().toKmlAltitudeMode(getAltitudeModeNative());
    }

    /**
     * 
     * @param altitudeMode
     *            Specifies how altitude components in the coordinates are
     *            interpreted.
     */
    public final void setAltitudeMode(KmlAltitudeMode altitudeMode) {
		this.setAltitudeModeNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }

    /**
     * 
     * @return Specifies how altitude components in the coordinates are
     *         interpreted.
     */
    private final native int getAltitudeModeNative() /*-{
        return this.getAltitudeMode();
    }-*/;

    /**
     * 
     * @param altitudeMode
     *            Specifies how altitude components in the coordinates are
     *            interpreted.
     */
    private final native void setAltitudeModeNative(int altitudeMode) /*-{
        this.setAltitudeMode(altitudeMode);
    }-*/;
}
