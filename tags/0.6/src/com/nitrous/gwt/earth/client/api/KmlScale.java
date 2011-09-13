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
 * Scales a model along the x, y, and z axes in the model's coordinate space.
 * 
 * @author Nick
 * 
 */
public class KmlScale extends KmlObject {
    protected KmlScale() {
    }

    /**
     * Sets the x, y, and z coordinates for a model.
     * 
     * @param x
     * @param y
     * @param z
     */
    public native final void set(double x, double y, double z) /*-{
		this.set(x, y, z);
    }-*/;

    /**
     * 
     * @return Indicates the x coordinate.
     */
    public native final double getX() /*-{
		return this.getX();
    }-*/;

    /**
     * 
     * @param x
     *            Indicates the x coordinate.
     */
    public native final void setX(double x) /*-{
		this.setX(x);
    }-*/;

    /**
     * 
     * @return Indicates the y coordinate.
     */
    public native final double getY() /*-{
		return this.getY();
    }-*/;

    /**
     * 
     * @param y
     *            Indicates the y coordinate.
     */
    public native final void setY(double y) /*-{
		this.setY(y);
    }-*/;

    /**
     * 
     * @return Indicates the z coordinate.
     */
    public native final double getZ() /*-{
		return this.getZ();
    }-*/;

    /**
     * 
     * @param z
     *            Indicates the y coordinate.
     */
    public native final void setZ(double z) /*-{
		this.setZ(z);
    }-*/;
}
