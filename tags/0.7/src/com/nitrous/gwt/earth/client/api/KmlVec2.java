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
 * Defines the x and y coordinates of a 2D vector.
 * 
 * @author Nick
 * 
 */
public class KmlVec2 extends KmlObjectPartial {
	protected KmlVec2() {
	}

	/**
	 * Sets the coordinates of the vector.
	 * 
	 * @param x
	 * @param xUnits
	 * @param y
	 * @param yUnits
	 */
	public final void set(double x, KmlUnits xUnits, double y, KmlUnits yUnits) {
	    GEPluginConstants consts = GEPluginConstants.get();
	    int xUnit = consts.toJavaScript(xUnits);
	    int yUnit = consts.toJavaScript(yUnits);
	    setNative(x, xUnit, y, yUnit);
	}

    /**
     * Sets the coordinates of the vector.
     * 
     * @param x
     * @param xUnits
     * @param y
     * @param yUnits
     */
    private final native void setNative(double x, int xUnits, double y,
            int yUnits) /*-{
        this.set(x, xUnits, y, yUnits);
    }-*/;
    
	/**
	 * 
	 * @return Indicates the x coordinate.
	 */
	public final native double getX() /*-{
		return this.getX();
	}-*/;

	/**
	 * 
	 * @param x
	 *            Indicates the x coordinate.
	 */
	public final native void setX(double x) /*-{
		this.setX(x);
	}-*/;

	/**
	 * Units in which the x value is specified.
	 * @return
	 */
	public final KmlUnits getXUnits() {
	    return GEPluginConstants.get().toKmlUnits(getXUnitsNative());
	}

	/**
	 * Units in which the x value is specified.
	 * @param xUnits
	 */
	public final void setXUnits(KmlUnits xUnits) {
	    setXUnitsNative(GEPluginConstants.get().toJavaScript(xUnits));
	}

    /**
     * Units in which the x value is specified.
     * @return
     */
    private final native int getXUnitsNative() /*-{
        return this.getXUnits();
    }-*/;

    /**
     * Units in which the x value is specified.
     * @param xUnits
     */
    private final native void setXUnitsNative(int xUnits) /*-{
        this.setXUnits(xUnits);
    }-*/;
    
	/**
	 * 
	 * @return Indicates the y coordinate.
	 */
	public final native double getY() /*-{
		return this.getY();
	}-*/;

	/**
	 * 
	 * @param y
	 *            Indicates the y coordinate.
	 */
	public final native void setY(double y) /*-{
		this.setY(y);
	}-*/;

	/**
	 * @return Units in which the y value is specified.
	 */
	public final KmlUnits getYUnits() {
	    return GEPluginConstants.get().toKmlUnits(getYUnitsNative());
	}

	/**
	 * Units in which the y value is specified.
	 * @param yUnits
	 */
	public final void setYUnits(KmlUnits yUnits) {
	    setYUnitsNative(GEPluginConstants.get().toJavaScript(yUnits));
	}

    /**
     * @return Units in which the y value is specified.
     */
    private final native int getYUnitsNative() /*-{
        return this.getYUnits();
    }-*/;

    /**
     * Units in which the y value is specified.
     * @param yUnits
     */
    private final native void setYUnitsNative(int yUnits) /*-{
        this.setYUnits(yUnits);
    }-*/;
}
