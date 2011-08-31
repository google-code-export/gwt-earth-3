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
 * Specifies an AltitudeMode for derived classes.
 * 
 * @author Nick
 * 
 */
public class KmlAltitudeGeometry extends KmlGeometry {
    protected KmlAltitudeGeometry() {
    }

    /**
     * Specifies how altitude components in the geometry coordinates are interpreted.
     * @return
     */
    public final KmlAltitudeMode getAltitudeMode() {
        return GEPluginConstants.get().toKmlAltitudeMode(getAltitudeModeNative());
    }

    /**
     * 
     * @param altitudeMode Specifies how altitude components in the geometry coordinates are interpreted.
     */
    public final void setAltitudeMode(KmlAltitudeMode altitudeMode) {
        setAltitudeModeNative(GEPluginConstants.get().toJavaScript(altitudeMode));
    }

    /**
     * Specifies how altitude components in the geometry coordinates are interpreted.
     * @return
     */
    private final native int getAltitudeModeNative() /*-{
        return this.getAltitudeMode();
    }-*/;

    /**
     * 
     * @param altitudeMode Specifies how altitude components in the geometry coordinates are interpreted.
     */
    private final native void setAltitudeModeNative(int altitudeMode) /*-{
        this.setAltitudeMode(altitudeMode);
    }-*/;
}
