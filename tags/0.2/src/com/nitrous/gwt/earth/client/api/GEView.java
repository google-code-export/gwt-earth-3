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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Controls the behavior of the camera that views the scene in Google Earth.
 * 
 * @author Nick
 * 
 */
public class GEView extends JavaScriptObject {
    protected GEView() {
    }

    /**
     * Returns the screen x,y coordinates of a given point on the globe. Tip:
     * project() is the inverse of hitTest().
     * 
     * @param lat
     * @param lng
     * @param alt
     * @param altitudeMode
     * @return Returns a KmlVec2 object defining the current coordinates of a
     *         geographic point, or null if the lat/lng/alt is not currently on
     *         screen. Tip: project() is the inverse of hitTest().
     */
    private final native KmlVec2 project(double lat, double lng, double alt, int altitudeMode) /*-{
		return this.project(lat, lng, alt, altitudeMode);
    }-*/;

    /**
     * Returns the screen x,y coordinates of a given point on the globe. Tip:
     * project() is the inverse of hitTest().
     * 
     * @param lat
     * @param lng
     * @param alt
     * @param altitudeMode
     * @return Returns a KmlVec2 object defining the current coordinates of a
     *         geographic point, or null if the lat/lng/alt is not currently on
     *         screen. Tip: project() is the inverse of hitTest().
     */
    public final KmlVec2 project(double lat, double lng, double alt, KmlAltitudeMode altitudeMode) {
        return project(lat, lng, alt, GEPluginConstants.get().toJavaScript(altitudeMode));
    }
    
    /**
     * Sets the camera that views the scene in Google Earth.
     * 
     * @param view
     */
    public final native void setAbstractView(KmlAbstractView view) /*-{
		this.setAbstractView(view);
    }-*/;

    /**
     * Creates and returns a new KmlLookAt object, initialized to the current
     * camera position and orientation. Use 'altitudeMode' to specify the
     * altitude mode of the looked-at point.
     * 
     * @param altitudeMode
     * @return Creates and returns a new KmlLookAt object, initialized to the
     *         current camera position and orientation. Use 'altitudeMode' to
     *         specify the altitude mode of the looked-at point.
     */
    private final native KmlLookAt copyAsLookAtNative(int altitudeMode) /*-{
		return this.copyAsLookAt(altitudeMode);
    }-*/;

    /**
     * Creates and returns a new KmlLookAt object, initialized to the current
     * camera position and orientation. Use 'altitudeMode' to specify the
     * altitude mode of the looked-at point.
     * 
     * @param altitudeMode
     * @return Creates and returns a new KmlLookAt object, initialized to the
     *         current camera position and orientation. Use 'altitudeMode' to
     *         specify the altitude mode of the looked-at point.
     */
    public final KmlLookAt copyAsLookAt(KmlAltitudeMode altitudeMode) {
        return copyAsLookAtNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }
    
    /**
     * Creates and returns a new KmlCamera object, initialized to the current
     * camera position and orientation. Use 'altitudeMode' to specify the
     * altitude mode of the new camera.
     * 
     * @param altitudeMode
     * @return a new KmlCamera object, initialized to the current camera
     *         position and orientation. Use 'altitudeMode' to specify the
     *         altitude mode of the new camera.
     */
    private final native KmlCamera copyAsCameraNative(int altitudeMode) /*-{
		return this.copyAsCamera(altitudeMode);
    }-*/;

    /**
     * Creates and returns a new KmlCamera object, initialized to the current
     * camera position and orientation. Use 'altitudeMode' to specify the
     * altitude mode of the new camera.
     * 
     * @param altitudeMode
     * @return a new KmlCamera object, initialized to the current camera
     *         position and orientation. Use 'altitudeMode' to specify the
     *         altitude mode of the new camera.
     */
    public final KmlCamera copyAsCamera(KmlAltitudeMode altitudeMode) {
        return copyAsCameraNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }
    
    /**
     * Returns a bounding box that completely contains the region of the globe
     * that is currently visible. The returned box will be larger than what is
     * strictly visible, if that is necessary to include everything that is
     * visible.
     * 
     * @return Returns a KmlLatLonBox corresponding to the bounding box of the
     *         current viewport or null if no part of the globe is visible.
     */
    public final native KmlLatLonBox getViewportGlobeBounds() /*-{
		return this.getViewportGlobeBounds();
    }-*/;

    /**
     * Given a point on the screen in pixel coordinates, returns a
     * GEHitTestResult with information about the geographic location
     * corresponding to the point on the screen. Tip: hitTest() is the inverse
     * of project().
     * 
     * @param x
     *            The x coordinate, measured from the left or right edge of the
     *            plug-in window, depending on xUnits.
     * @param xUnits
     *            The units in which the x value is specified.
     * @param y
     *            The y coordinate, measured from the top or bottom edge of the
     *            plug-in window, depending on yUnits.
     * @param yUnits
     *            The units in which the y value is specified.
     * @param mode
     *            Selects which categories of objects to include in the hit
     *            test. Categories may be combined via JavaScript's OR operator,
     *            '|'.
     * @return Returns a GEHitTestResult, or null if the screen point is not
     *         over anything selected by mode. Note that for HIT_TEST_GLOBE,
     *         resulting altitude values will always be 0.
     */
    private final native GEHitTestResult hitTestNative(float x, int xUnits, float y, int yUnits,
            int mode) /*-{
        return this.hitTest(x, xUnits, y, yUnits, mode);
    }-*/;
    
    /**
     * Given a point on the screen in pixel coordinates, returns a
     * GEHitTestResult with information about the geographic location
     * corresponding to the point on the screen. Tip: hitTest() is the inverse
     * of project().
     * 
     * @param x
     *            The x coordinate, measured from the left or right edge of the
     *            plug-in window, depending on xUnits.
     * @param xUnits
     *            The units in which the x value is specified.
     * @param y
     *            The y coordinate, measured from the top or bottom edge of the
     *            plug-in window, depending on yUnits.
     * @param yUnits
     *            The units in which the y value is specified.
     * @param mode
     *            Selects which categories of objects to include in the hit
     *            test.
     * @return Returns a GEHitTestResult, or null if the screen point is not
     *         over anything selected by mode. Note that for HIT_TEST_GLOBE,
     *         resulting altitude values will always be 0.
     */
    public final GEHitTestResult hitTest(float x, KmlUnits xUnits, float y, KmlUnits yUnits,
            GEHitTestMode... mode) {
        GEPluginConstants consts = GEPluginConstants.get();
        int xUnit = consts.toJavaScript(xUnits);
        int yUnit = consts.toJavaScript(yUnits);
        int nativeMode = 0;
        for (GEHitTestMode m : mode) {
            nativeMode |= consts.toJavaScript(m);
        }
		return this.hitTestNative(x, xUnit, y, yUnit, nativeMode);
    };
}
