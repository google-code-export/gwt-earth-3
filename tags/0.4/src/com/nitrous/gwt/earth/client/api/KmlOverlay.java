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
 * The KmlOverlay object is an abstract object and cannot be used directly in a
 * KML file. Overlay is the base type for image overlays drawn on the planet
 * surface or on the screen. Icon specifies the image to use and can be
 * configured to reload images based on a timer or by camera changes. This
 * object also includes specifications for stacking order of multiple overlays
 * and for adding color and transparency values to the base image.
 * 
 * @author Nick
 * 
 */
public class KmlOverlay extends KmlFeature {
    protected KmlOverlay() {
    }

    /**
     * 
     * @return Specifies the color values.
     */
    public final native KmlColor getColor() /*-{
		return this.getColor();
    }-*/;

    /**
     * 
     * @return Defines the stacking order for the images in overlapping
     *         overlays. Overlays with higher drawOrder values are drawn on top
     *         of overlays with lower drawOrder values.
     */
    public final native int getDrawOrder() /*-{
		return this.getDrawOrder();
    }-*/;

    /**
     * 
     * @param drawOrder
     *            Defines the stacking order for the images in overlapping
     *            overlays. Overlays with higher drawOrder values are drawn on
     *            top of overlays with lower drawOrder values.
     */
    public final native void setDrawOrder(int drawOrder) /*-{
		this.setDrawOrder(drawOrder);
    }-*/;

    /**
     * 
     * @return Defines the image associated with the Overlay.
     */
    public final native KmlIcon getIcon() /*-{
		return this.getIcon();
    }-*/;

    /**
     * 
     * @param icon
     *            Defines the image associated with the Overlay.
     */
    public final native void setIcon(KmlIcon icon) /*-{
		this.setIcon(icon);
    }-*/;

}
