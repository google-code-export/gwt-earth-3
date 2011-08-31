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
 * A 3D object described in a referenced COLLADA file. COLLADA files have a .dae
 * file extension. Models are created in their own coordinate space and then
 * located, positioned, and scaled in Google Earth. Google Earth supports the
 * COLLADA common profile, with the following exceptions:
 * 
 * <pre>
 * <li>Google Earth supports only triangles and lines as primitive types. The maximum number of triangles allowed is 21845.</li>
 * <li>Google Earth does not support animation or skinning.</li>
 * <li>Google Earth does not support external geometry references.</li>
 * </pre>
 * 
 * @author Nick
 * 
 */
public class KmlModel extends KmlAltitudeGeometry {
    protected KmlModel() {
    }

    /**
     * Specifies the exact coordinates of the Model's origin in latitude,
     * longitude, and altitude. Latitude and longitude measurements are standard
     * lat-lon projection with WGS84 datum. Altitude is distance above the
     * earth's surface, in meters, and is interpreted according to altitudeMode.
     * 
     * @return the exact coordinates of the Model's origin in latitude,
     *         longitude, and altitude. Latitude and longitude measurements are
     *         standard lat-lon projection with WGS84 datum. Altitude is
     *         distance above the earth's surface, in meters, and is interpreted
     *         according to altitudeMode.
     */
    public final native KmlLocation getLocation() /*-{
		return this.getLocation();
    }-*/;

    /**
     * Specifies the exact coordinates of the Model's origin in latitude,
     * longitude, and altitude. Latitude and longitude measurements are standard
     * lat-lon projection with WGS84 datum. Altitude is distance above the
     * earth's surface, in meters, and is interpreted according to altitudeMode.
     * 
     * @param location
     *            Specifies the exact coordinates of the Model's origin in
     *            latitude, longitude, and altitude. Latitude and longitude
     *            measurements are standard lat-lon projection with WGS84 datum.
     *            Altitude is distance above the earth's surface, in meters, and
     *            is interpreted according to altitudeMode.
     */
    public final native void setLocation(KmlLocation location) /*-{
		this.setLocation(location);
    }-*/;

    public final native KmlOrientation getOrientation() /*-{
		return this.getOrientation();
    }-*/;

    public final native void setOrientation(KmlOrientation orientation) /*-{
		this.setOrientation(orientation);
    }-*/;

    public final native KmlScale getScale() /*-{
		return this.getScale();
    }-*/;

    public final native void setScale(KmlScale scale) /*-{
		this.setScale(scale);
    }-*/;

    public final native KmlLink getLink() /*-{
		return this.getLink();
    }-*/;

    public final native void setLink(KmlLink link) /*-{
		this.setLink(link);
    }-*/;
}
