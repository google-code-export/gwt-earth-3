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
 * Draws an image overlay draped onto the terrain. The href child of Icon
 * specifies the image to be used as the overlay. If this object is omitted or
 * contains no href, a rectangle is drawn using the color defined by the
 * overlay.
 * 
 * @author Nick
 * 
 */
public class KmlGroundOverlay extends KmlOverlay {
    protected KmlGroundOverlay() {
    }

    /**
     * Specifies the distance above the earth's surface.
     * 
     * @return
     */
    public final native double getAltitude() /*-{
		return this.getAltitude();
    }-*/;

    /**
     * Specifies the distance above the earth's surface.
     * 
     * @param altitude
     */
    public final native void setAltitude(double altitude)/*-{
		this.setAltitude(altitude);
    }-*/;

    /**
     * Specifies how the altitude property is interpreted.
     * 
     * @return
     */
    public final KmlAltitudeMode getAltitudeMode(){
        return GEPluginConstants.get().toKmlAltitudeMode(getAltitudeModeNative());
    }

    /**
     * Specifies how the altitude property is interpreted.
     * 
     * @param altitudeMode
     */
    public final void setAltitudeMode(KmlAltitudeMode altitudeMode){
        setAltitudeModeNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }

    /**
     * Specifies how the altitude property is interpreted.
     * 
     * @return
     */
    private final native int getAltitudeModeNative()/*-{
        return this.getAltitudeMode();
    }-*/;

    /**
     * Specifies how the altitude property is interpreted.
     * 
     * @param altitudeMode
     */
    private final native void setAltitudeModeNative(int altitudeMode)/*-{
        this.setAltitudeMode(altitudeMode);
    }-*/;

    /**
     * 
     * @return The bounding box of the ground overlay.
     */
    public final native KmlLatLonBox getLatLonBox()/*-{
        return this.getLatLonBox();
    }-*/;

    /**
     * 
     * @param latLonBox The bounding box of the ground overlay.
     */
    public final native void setLatLonBox(KmlLatLonBox latLonBox)/*-{
        this.setLatLonBox(latLonBox);
    }-*/;
}
