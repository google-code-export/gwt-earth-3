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
 * Specifies the top, bottom, right, and left sides of a bounding box on the
 * Earth's surface.
 * 
 * @author Nick
 * 
 */
public class KmlLatLonBox extends KmlObject {
    protected KmlLatLonBox() {
    }

    /**
     * Sets the north, south, east, and west edges of the bounding box, as well
     * as the rotation of the overlay.
     * 
     * @param north
     * @param south
     * @param east
     * @param west
     * @param rotation
     */
    public final native void setBox(double north, double south, double east, double west, double rotation) /*-{
		this.setBox(north, south, east, west, rotation);
    }-*/;

    /**
     * Specifies the latitude of the north edge of the bounding box, in decimal degrees from -90 to 90.
     * @return the latitude of the north edge of the bounding box, in decimal degrees from -90 to 90.
     */
    public final native double getNorth() /*-{
		return this.getNorth();
    }-*/;

    /**
     * Specifies the latitude of the north edge of the bounding box, in decimal degrees from -90 to 90.
     * @param north the latitude of the north edge of the bounding box, in decimal degrees from -90 to 90.
     */
    public final native void setNorth(double north) /*-{
		this.setNorth(north);
    }-*/;

    /**
     * Specifies the latitude of the south edge of the bounding box, in decimal degrees from -90 to 90.
     * @return the latitude of the south edge of the bounding box, in decimal degrees from -90 to 90.
     */
    public final native double getSouth() /*-{
		return this.getSouth();
    }-*/;

    /**
     * Specifies the latitude of the south edge of the bounding box, in decimal degrees from -90 to 90.
     * @param south the latitude of the south edge of the bounding box, in decimal degrees from -90 to 90.
     */
    public final native void setSouth(double south) /*-{
		this.setSouth(south);
    }-*/;

    /**
     * Specifies the longitude of the east edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     * @return the longitude of the east edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     */
    public final native double getEast() /*-{
		return this.getEast();
    }-*/;

    /**
     * Specifies the longitude of the east edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     * @param east the longitude of the east edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     */
    public final native void setEast(double east) /*-{
		this.setEast(east);
    }-*/;

    /**
     * Specifies the longitude of the west edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     * @return the longitude of the west edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     */
    public final native double getWest() /*-{
		return this.getWest();
    }-*/;

    /**
     * Specifies the longitude of the west edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     * @param west the longitude of the west edge of the bounding box, in decimal degrees from -180 to 180. (For overlays that overlap the meridian of 180 degrees longitude, values can extend beyond that range.)
     */
    public final native void setWest(double west) /*-{
		this.setWest(west);
    }-*/;

    /**
     * Specifies a rotation of the overlay about its center, in degrees. Values can be +/-180. The default is 0 (north). Rotations are specified in a counterclockwise direction.
     * @return rotation of the overlay about its center, in degrees. Values can be +/-180. The default is 0 (north). Rotations are specified in a counterclockwise direction.
     */
    public final native double getRotation() /*-{
		return this.getRotation();
    }-*/;

    /**
     * Specifies a rotation of the overlay about its center, in degrees. Values can be +/-180. The default is 0 (north). Rotations are specified in a counterclockwise direction.
     * @param rotation rotation of the overlay about its center, in degrees. Values can be +/-180. The default is 0 (north). Rotations are specified in a counterclockwise direction.
     */
    public final native void setRotation(double rotation) /*-{
		this.setRotation(rotation);
    }-*/;
}
