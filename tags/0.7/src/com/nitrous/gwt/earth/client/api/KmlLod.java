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
 * The KmlLod or level of detail, describes the size of the projected region on
 * the screen that is required in order for the region to be considered active.
 * Also specifies the size of the pixel ramp used for fading in (from
 * transparent to opaque) and fading out (from opaque to transparent).
 * 
 * @author Nick
 * 
 */
public class KmlLod extends KmlObject {
    protected KmlLod() {
    }

    /**
     * Sets the minLodPixels, maxLodPixels, minFadeExtent, and maxFadeExtent for
     * the projected region on the screen.
     * 
     * @param minLodPixels
     * @param maxLodPixels
     * @param minFadeExtent
     * @param maxFadeExtent
     */
    public final native void set(float minLodPixels, float maxLodPixels, float minFadeExtent, float maxFadeExtent)/*-{
		this.set(minLodPixels, maxLodPixels, minFadeExtent, maxFadeExtent);
    }-*/;

    /**
     * Specifies measurement in screen pixels that represents the minimum limit
     * of the visibility range for a given Region. Google Earth calculates the
     * size of the region when projected onto screen space. Then it computes the
     * square root of the region's area (if, for example, the Region is square
     * and the viewpoint is directly above the Region, and the Region is not
     * tilted, this measurement is equal to the width of the projected Region).
     * If this measurement falls within the limits defined by minLodPixels and
     * maxLodPixels (and if the LatLonAltBox is in view), the region is active.
     * If this limit is not reached, the associated geometry is considered to be
     * too far from the user's viewpoint to be drawn.
     * 
     * @return
     */
    public final native float getMinLodPixels()/*-{
		return this.getMinLodPixels();
    }-*/;

    /**
     * @param minLodPixels
     *            Specifies measurement in screen pixels that represents the
     *            minimum limit of the visibility range for a given Region.
     *            Google Earth calculates the size of the region when projected
     *            onto screen space. Then it computes the square root of the
     *            region's area (if, for example, the Region is square and the
     *            viewpoint is directly above the Region, and the Region is not
     *            tilted, this measurement is equal to the width of the
     *            projected Region). If this measurement falls within the limits
     *            defined by minLodPixels and maxLodPixels (and if the
     *            LatLonAltBox is in view), the region is active. If this limit
     *            is not reached, the associated geometry is considered to be
     *            too far from the user's viewpoint to be drawn.
     */
    public final native void setMinLodPixels(float minLodPixels)/*-{
		this.setMinLodPixels(minLodPixels);
    }-*/;

    /**
     * 
     * @return Measurement in screen pixels that represents the maximum limit of
     *         the visibility range for a given Region. A value of -1, the
     *         default, indicates "active to infinite size."
     */
    public final native float getMaxLodPixels()/*-{
		return this.getMaxLodPixels();
    }-*/;

    /**
     * 
     * @param maxLodPixels
     *            Measurement in screen pixels that represents the maximum limit
     *            of the visibility range for a given Region. A value of -1, the
     *            default, indicates "active to infinite size."
     */
    public final native void setMaxLodPixels(float maxLodPixels)/*-{
		this.setMaxLodPixels(maxLodPixels);
    }-*/;

    /**
     * 
     * @return Distance over which the geometry fades, from fully opaque to
     *         fully transparent. This ramp value, expressed in screen pixels,
     *         is applied at the minimum end of the LOD (visibility) limits.
     */
    public final native float getMinFadeExtent()/*-{
		return this.getMinFadeExtent();
    }-*/;

    /**
     * 
     * @param minFadeExtent
     *            Distance over which the geometry fades, from fully opaque to
     *            fully transparent. This ramp value, expressed in screen
     *            pixels, is applied at the minimum end of the LOD (visibility)
     *            limits.
     */
    public final native void setMinFadeExtent(float minFadeExtent)/*-{
		this.setMinFadeExtent(minFadeExtent);
    }-*/;

    /**
     * 
     * @return Distance over which the geometry fades, from fully transparent to
     *         fully opaque. This ramp value, expressed in screen pixels, is
     *         applied at the maximum end of the LOD (visibility) limits.
     */
    public final native float getMaxFadeExtent()/*-{
		return this.getMaxFadeExtent();
    }-*/;

    /**
     * 
     * @param maxFadeExtent
     *            Distance over which the geometry fades, from fully transparent
     *            to fully opaque. This ramp value, expressed in screen pixels,
     *            is applied at the maximum end of the LOD (visibility) limits.
     */
    public final native void setMaxFadeExtent(float maxFadeExtent)/*-{
		this.setMaxFadeExtent(maxFadeExtent);
    }-*/;
}
