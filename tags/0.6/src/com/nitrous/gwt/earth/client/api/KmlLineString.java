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
 * Defines a connected set of line segments. Use KmlLineStyle to specify the
 * color, color mode, and width of the line. When a LineString is extruded, the
 * line is extended to the ground, forming a polygon that looks somewhat like a
 * wall or fence. For extruded LineStrings, the line itself uses the current
 * LineStyle, and the extrusion uses the current PolyStyle.
 * 
 * @author Nick
 * 
 */
public class KmlLineString extends KmlExtrudableGeometry {
    protected KmlLineString() {
    }

    /**
     * @return Two or more coordinate tuples, each consisting of floating point
     *         values for longitude, latitude, and altitude. The altitude
     *         component is optional.
     */
    public native final KmlCoordArray getCoordinates()/*-{
		return this.getCoordinates();
    }-*/;

    /**
     * Added to the altitude values for all points on the line string. Adjusts
     * the altitude of the feature as a whole, without the need to update each
     * coordinate set.
     * 
     * @param altitudeOffset
     */
    public native final void setAltitudeOffset(double altitudeOffset)/*-{
		this.setAltitudeOffset(altitudeOffset);
    }-*/;

    /**
     * 
     * @return the altitudeOffset, or 0 if not set.
     */
    public native final double getAltitudeOffset()/*-{
		return this.getAltitudeOffset();
    }-*/;
}
