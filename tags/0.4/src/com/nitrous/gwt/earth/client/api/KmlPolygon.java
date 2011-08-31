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
 * A Polygon is defined by an outer boundary and 0 or more inner boundaries. The
 * boundaries, in turn, are defined by LinearRings. When a Polygon is extruded,
 * its boundaries are connected to the ground to form additional polygons, which
 * gives the appearance of a building or a box. Extruded Polygons use PolyStyle
 * for their color, color mode, and fill.
 * 
 * @author Nick
 * 
 */
public class KmlPolygon extends KmlExtrudableGeometry {
    protected KmlPolygon() {
    }

    /**
     * Contains a LinearRing element.
     * 
     * @return
     */
    public final native KmlLinearRing getOuterBoundary()/*-{
		return this.getOuterBoundary();
    }-*/;

    /**
     * Contains a LinearRing element.
     * 
     * @param outerBoundary
     */
    public final native void setOuterBoundary(KmlLinearRing outerBoundary)/*-{
		this.setOuterBoundary(outerBoundary);
    }-*/;

    /**
     * 
     * @return Contains a LinearRing element. You can specify multiple
     *         innerBoundary properties, which create multiple cut-outs inside
     *         the Polygon.
     */
    public final native GELinearRingContainer getInnerBoundaries()/*-{
		return this.getInnerBoundaries();
    }-*/;

}
