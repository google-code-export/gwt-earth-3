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
 * Specifies how icons for point placemarks are drawn in Google Earth. The icon
 * property specifies the icon image. The scale property specifies the x, y
 * scaling of the icon. The color specified in the color property of
 * KmlIconStyle is blended with the color of the Icon.
 * 
 * @author Nick
 * 
 */
public class KmlIconStyle extends KmlColorStyle {
    protected KmlIconStyle() {
    }

    /**
     * Resizes the icon.
     * 
     * @return
     */
    public native final float getScale()/*-{
		return this.getScale();
    }-*/;

    /**
     * Resizes the icon.
     * 
     * @param scale
     */
    public native final void setScale(float scale)/*-{
		this.setScale(scale);
    }-*/;

    /**
     * 
     * @return The direction that icons are set to point, clockwise, and in
     *         degrees.
     */
    public native final float getHeading()/*-{
		return this.getHeading();
    }-*/;

    /**
     * 
     * @param heading
     *            The direction that icons should point, clockwise, and in
     *            degrees. The default is 0 (North). Values range from 0 to 360
     *            degrees.
     */
    public native final void setHeading(float heading)/*-{
		this.setHeading(heading);
    }-*/;

    /**
     * 
     * @return A custom Icon. In KmlIconStyle, the only child element of KmlIcon
     *         is href and href is an HTTP address or a local file specification
     *         used to load an icon.
     */
    public native final KmlIcon getIcon()/*-{
		return this.getIcon();
    }-*/;

    /**
     * 
     * @param icon
     *            A custom Icon. In KmlIconStyle, the only child element of
     *            KmlIcon is href and href is an HTTP address or a local file
     *            specification used to load an icon.
     */
    public native final void setIcon(KmlIcon icon)/*-{
		this.setIcon(icon);
    }-*/;

    /**
     * Specifies the position within the Icon that is anchored to the point
     * specified in the placemark. The x and y values can be specified in three
     * different ways: as pixels, as fractions of the icon, or as inset pixels,
     * which is an offset in pixels from the upper right corner of the icon. The
     * x and y positions can be specified in different ways. For example, x can
     * be in pixels and y can be a fraction. The origin of the coordinate system
     * is in the lower left corner of the icon.
     * 
     * @return
     */
    public native final KmlVec2 getHotSpot()/*-{
		return this.getHotSpot();
    }-*/;
}
