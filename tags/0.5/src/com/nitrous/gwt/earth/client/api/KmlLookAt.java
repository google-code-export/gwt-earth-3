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
 * Defines a camera that is associated with anything derived from feature. The
 * LookAt element positions the "camera" in relation to the object that is being
 * viewed. This class either positions the relative to a feature, or you can
 * manually change the view, using ge.getView().setAbstractView().
 * 
 * @author Nick
 * 
 */
public class KmlLookAt extends KmlAbstractView {
    protected KmlLookAt() {
    }

    /**
     * Sets the latitude, longitude, altitude, altitudeMode, heading, tilt, and
     * range for the camera.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param heading
     * @param tilt
     * @param range
     */
    public final void set(double latitude, double longitude, double altitude, KmlAltitudeMode altitudeMode,
            double heading, double tilt, double range) {
        int altMode = GEPluginConstants.get().toJavaScript(altitudeMode);
		this.setNative(latitude, longitude, altitude, altMode, heading, tilt,
				range);
    }

    /**
     * Sets the latitude, longitude, altitude, altitudeMode, heading, tilt, and
     * range for the camera.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param altitudeMode
     * @param heading
     * @param tilt
     * @param range
     */
    private final native void setNative(double latitude, double longitude, double altitude, int altitudeMode,
            double heading, double tilt, double range) /*-{
        this.set(latitude, longitude, altitude, altitudeMode, heading, tilt,
                range);
    }-*/;

    /**
     * Latitude of the point the camera is looking at. Degrees north or south of
     * the Equator (0 degrees). Values range from -90 degrees (South Pole) to 90
     * degrees (North Pole).
     * 
     * @return Latitude of the point the camera is looking at. Degrees north or
     *         south of the Equator (0 degrees). Values range from -90 degrees
     *         (South Pole) to 90 degrees (North Pole).
     */
    public final native double getLatitude() /*-{
		return this.getLatitude();
    }-*/;

    /**
     * 
     * @param latitude
     *            Latitude of the point the camera is looking at. Degrees north
     *            or south of the Equator (0 degrees). Values range from -90
     *            degrees (South Pole) to 90 degrees (North Pole).
     */
    public final native void setLatitude(double latitude) /*-{
		this.setLatitude(latitude);
    }-*/;

    /**
     * 
     * @return Longitude of the point the camera is looking at. Angular distance
     *         in degrees, relative to the Prime Meridian. Values west of the
     *         Meridian range from -180 to 0 degrees. Values east of the
     *         Meridian range from 0 to 180 degrees.
     */
    public final native double getLongitude() /*-{
		return this.getLongitude();
    }-*/;

    /**
     * 
     * @param longitude
     *            Longitude of the point the camera is looking at. Angular
     *            distance in degrees, relative to the Prime Meridian. Values
     *            west of the Meridian range from -180 to 0 degrees. Values east
     *            of the Meridian range from 0 to 180 degrees.
     */
    public final native void setLongitude(double longitude) /*-{
		this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return The distance in meters from the point specified by longitude,
     *         latitude, and altitude for the LookAt position.
     */
    public final native double getRange() /*-{
		return this.getRange();
    }-*/;

    /**
     * 
     * @param range
     *            The distance in meters from the point specified by longitude,
     *            latitude, and altitude for the LookAt position.
     */
    public final native void setRange(double range) /*-{
		this.setRange(range);
    }-*/;

    /**
     * 
     * @return Angle between the direction of the LookAt position and the normal
     *         to the surface of the earth. Values range from 0 to 90 degrees.
     *         Values for tilt cannot be negative. A tilt value of 0 degrees
     *         indicates viewing from directly above. A tilt value of 90 degrees
     *         indicates viewing along the horizon.
     */
    public final native double getTilt() /*-{
		return this.getTilt();
    }-*/;

    /**
     * 
     * @param tilt
     *            Angle between the direction of the LookAt position and the
     *            normal to the surface of the earth. Values range from 0 to 90
     *            degrees. Values for tilt cannot be negative. A tilt value of 0
     *            degrees indicates viewing from directly above. A tilt value of
     *            90 degrees indicates viewing along the horizon.
     */
    public final native void setTilt(double tilt) /*-{
		this.setTilt(tilt);
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
     * @return Distance from the earth's surface, in meters.
     */
    public final native double getAltitude() /*-{
		return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude Distance from the earth's surface, in meters.
     */
    public final native void setAltitude(double altitude) /*-{
		this.setAltitude(altitude);
    }-*/;

    /**
     * Specifies how altitude components in the coordinates element are interpreted.
     * @return 
     */
    public final KmlAltitudeMode getAltitudeMode() {
        return GEPluginConstants.get().toKmlAltitudeMode(getAltitudeModeNative());
    }

    /**
     * Specifies how altitude components in the coordinates element are interpreted.
     * @param altitudeMode
     */
    public final void setAltitudeMode(KmlAltitudeMode altitudeMode) {
        setAltitudeModeNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }
    
    /**
     * Specifies how altitude components in the coordinates element are interpreted.
     * @return 
     */
    private final native int getAltitudeModeNative() /*-{
        return this.getAltitudeMode();
    }-*/;

    /**
     * Specifies how altitude components in the coordinates element are interpreted.
     * @param altitudeMode
     */
    private final native void setAltitudeModeNative(int altitudeMode) /*-{
        this.setAltitudeMode(altitudeMode);
    }-*/;
}
