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
 * The KmlRegion object is used to set region objects and their properties. A
 * region contains a bounding box (LatLonAltBox) that describes an area of
 * interest defined by geographic coordinates and altitudes. In addition, a
 * Region contains an LOD (level of detail) extent that defines a validity range
 * of the associated Region in terms of projected screen size. A Region is said
 * to be "active" when the bounding box is within the user's view and the LOD
 * requirements are met. Objects associated with a Region are drawn only when
 * the Region is active. When the viewRefreshMode is onRegion, the Link or Icon
 * is loaded only when the Region is active.
 * 
 * @author Nick
 * 
 */
public class KmlRegion extends KmlObject {
    protected KmlRegion() {
    }

    /**
     * Sets the latLonAltBox and lod for the region.
     * 
     * @param latLonAltBox
     * @param lod
     */
    public final native void set(KmlLatLonAltBox latLonAltBox, KmlLod lod)/*-{
		this.set(latLonAltBox, lod);
    }-*/;

    /**
     * 
     * @return A bounding box that describes an area of interest defined by
     *         geographic coordinates and altitudes.
     */
    public final native KmlLatLonAltBox getLatLonAltBox()/*-{
		return this.getLatLonAltBox();
    }-*/;

    /**
     * 
     * @param latLonAltBox
     *            A bounding box that describes an area of interest defined by
     *            geographic coordinates and altitudes.
     */
    public final native void setLatLonAltBox(KmlLatLonAltBox latLonAltBox)/*-{
		this.setLatLonAltBox(latLonAltBox);
    }-*/;

    /**
     * 
     * @return LOD is an abbreviation for Level of Detail. Lod describes the
     *         size of the projected region on the screen that is required in
     *         order for the region to be considered "active." Also specifies
     *         the size of the pixel ramp used for fading in (from transparent
     *         to opaque) and fading out (from opaque to transparent).
     */
    public final native KmlLod getLod()/*-{
		return this.getLod();
    }-*/;

    /**
     * 
     * @param lod
     *            LOD is an abbreviation for Level of Detail. Lod describes the
     *            size of the projected region on the screen that is required in
     *            order for the region to be considered "active." Also specifies
     *            the size of the pixel ramp used for fading in (from
     *            transparent to opaque) and fading out (from opaque to
     *            transparent).
     */
    public final native void setLod(KmlLod lod)/*-{
        this.setLod(lod);
    }-*/;

}
