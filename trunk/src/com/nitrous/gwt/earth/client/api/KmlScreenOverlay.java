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
 * Draws an image overlay fixed to the screen. Sample uses for ScreenOverlays
 * are compasses, logos, and heads-up displays. ScreenOverlay sizing is
 * determined by the size element. Positioning of the overlay is handled by
 * mapping a point in the image specified by screenXY to a point on the screen
 * specified by overlayXY. Then the image is rotated by rotation degrees about a
 * point relative to the screen specified by rotationXY.
 * 
 * <b>Note:</b> screenXY and overlayXY behave opposite to their corresponding
 * behaviors in KML. This is due to a <a
 * href="http://code.google.com/p/earth-api-samples/issues/detail?id=193"
 * >bug</a> in the Earth API that will intentionally remain unfixed until a
 * major version change.
 * 
 * @author Nick
 * 
 */
public class KmlScreenOverlay extends KmlOverlay {
    protected KmlScreenOverlay() {
    }

    /**
     * Specifies a point on (or outside of) the overlay image that is mapped to
     * the screen coordinate. It requires x and y values, and the units for
     * those values. <br>
     * 
     * <b>Note:</b> screenXY and overlayXY behave opposite to their
     * corresponding behaviors in KML. This is due to a <a
     * href="http://code.google.com/p/earth-api-samples/issues/detail?id=193"
     * >bug</a> in the Earth API that will intentionally remain unfixed until a
     * major version change.
     * 
     * @return
     */
    public final native KmlVec2 getScreenXY() /*-{
		return this.getScreenXY();
    }-*/;

    /**
     * Specifies a point relative to the screen origin that the overlay image is
     * mapped to. The x and y values can be specified in three different ways:
     * as pixels ("pixels"), as fractions of the screen ("fraction"), or as
     * inset pixels ("insetPixels"), which is an offset in pixels from the upper
     * right corner of the screen. The x and y positions can be specified in
     * different ways - for example, x can be in pixels and y can be a fraction.
     * The origin of the coordinate system is in the lower left corner of the
     * screen.
     * 
     * <b>Note:</b> screenXY and overlayXY behave opposite to their
     * corresponding behaviors in KML. This is due to a <a
     * href="http://code.google.com/p/earth-api-samples/issues/detail?id=193"
     * >bug</a> in the Earth API that will intentionally remain unfixed until a
     * major version change.
     * 
     * @return
     */
    public final native KmlVec2 getOverlayXY()/*-{
		return this.getOverlayXY();
    }-*/;

    /**
     * 
     * @return Point relative to the screen about which the screen overlay is
     *         rotated.
     */
    public final native KmlVec2 getRotationXY()/*-{
		return this.getRotationXY();
    }-*/;

    /**
     * Specifies the size of the image for the screen overlay, as follows
     * 
     * <pre>
     * <li>A value of -1 indicates to use the native dimension</li>
     * <li>A value of 0 indicates to maintain the aspect ratio</li>
     * <li>A value of n sets the value of the dimension</li>
     * </pre>
     * 
     * @return
     */
    public final native KmlVec2 getSize()/*-{
		return this.getSize();
    }-*/;

    /**
     * 
     * @return Adjusts how the image is placed inside the field of view. This
     *         element is useful if your image has been rotated and deviates
     *         slightly from a desired horizontal view.
     */
    public final native double getRotation()/*-{
		return this.getRotation();
    }-*/;

    /**
     * 
     * @param rotation
     *            Adjusts how the image is placed inside the field of view. This
     *            element is useful if your image has been rotated and deviates
     *            slightly from a desired horizontal view.
     */
    public final native void setRotation(double rotation)/*-{
		this.setRotation(rotation);
    }-*/;

}
