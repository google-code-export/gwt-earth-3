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
 * Describes rotation of a 3D model's coordinate system to position the object
 * in Google Earth.
 * 
 * @author Nick
 * 
 */
public class KmlOrientation extends KmlObject {
    protected KmlOrientation() {
    }

    /**
     * Sets the heading, tilt, and roll of a model.
     * 
     * @param heading
     * @param tilt
     * @param roll
     */
    public native final void set(double heading, double tilt, double roll) /*-{
		this.set(heading, tilt, roll);
    }-*/;

    /**
     * @return Rotation about the z axis (normal to the Earth's surface). A
     *         value of 0 (the default) equals North. A positive rotation is
     *         clockwise around the z axis and specified in degrees from 0 to
     *         360.
     */
    public native final double getHeading() /*-{
		return this.getHeading();
    }-*/;

    /**
     * 
     * @param heading
     *            Rotation about the z axis (normal to the Earth's surface). A
     *            value of 0 (the default) equals North. A positive rotation is
     *            clockwise around the z axis and specified in degrees from 0 to
     *            360.
     */
    public native final void setHeading(double heading) /*-{
		this.setHeading(heading);
    }-*/;

    /**
     * 
     * @return Rotation about the x axis. A positive rotation is clockwise
     *         around the x axis and specified in degrees from 0 to 360.
     */
    public native final double getTilt() /*-{
		return this.getTilt();
    }-*/;

    /**
     * 
     * @param tilt
     *            Rotation about the x axis. A positive rotation is clockwise
     *            around the x axis and specified in degrees from 0 to 360.
     */
    public native final void setTilt(double tilt) /*-{
		this.setTilt(tilt);
    }-*/;

    /**
     * 
     * @return Rotation about the y axis. A positive rotation is clockwise
     *         around the y axis and specified in degrees from 0 to 360.
     */
    public native final double getRoll() /*-{
		return this.getRoll();
    }-*/;

    /**
     * 
     * @param roll
     *            Rotation about the y axis. A positive rotation is clockwise
     *            around the y axis and specified in degrees from 0 to 360.
     */
    public native final void setRoll(double roll) /*-{
        this.setRoll(roll);
    }-*/;

}
