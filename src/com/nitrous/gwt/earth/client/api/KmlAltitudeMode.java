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


public enum KmlAltitudeMode {
    /**
     * Specifies that altitudes are at ground level. For Ground overlays, this
     * means that the image will be draped over the terrain.
     */
    ALTITUDE_CLAMP_TO_GROUND,
    /**
     * Specifies that altitudes are to be interpreted as meters above or below
     * ground level (i.e. the elevation of the terrain at the location).
     */
    ALTITUDE_RELATIVE_TO_GROUND,

    /**
     * Specifies that altitudes are to be interpreted as meters above or below
     * sea level, regardless of the actual elevation of the terrain beneath the
     * object. For example, if you set the altitude of an object to 10 meters
     * with an absolute altitude mode, the object will appear to be at ground
     * level if the terrain beneath is also 10 meters above sea level. If the
     * terrain is 3 meters above sea level, the object will appear elevated
     * above the terrain by 7 meters. If, on the other hand, the terrain is 15
     * meters above sea level, the object may be completely invisible.
     */
    ALTITUDE_ABSOLUTE,

    /**
     * Specifies that altitudes are at sea floor level.
     */
    ALTITUDE_CLAMP_TO_SEA_FLOOR,

    /**
     * Specifies that altitudes are to be interpreted as meters above sea floor
     * (i.e. the elevation of the sea floor at the location).
     */
    ALTITUDE_RELATIVE_TO_SEA_FLOOR;

    public native static int getAltitudeClampToGround(GEPlugin plugin) /*-{
		return plugin.ALTITUDE_CLAMP_TO_GROUND;
    }-*/;

    public native static int getAltitudeRelativeToGround(GEPlugin plugin) /*-{
		return plugin.ALTITUDE_RELATIVE_TO_GROUND;
    }-*/;

    public native static int getAltitudeAbsolute(GEPlugin plugin) /*-{
		return plugin.ALTITUDE_ABSOLUTE;
    }-*/;

    public native static int getAltitudeClampToSeaFloor(GEPlugin plugin) /*-{
		return plugin.ALTITUDE_CLAMP_TO_SEA_FLOOR;
    }-*/;

    public native static int getAltitudeRelativeToSeaFloor(GEPlugin plugin) /*-{
		return plugin.ALTITUDE_RELATIVE_TO_SEA_FLOOR;
    }-*/;
    
    static KmlAltitudeMode toJava(int value, GEPlugin plugin) {
        if (value == getAltitudeAbsolute(plugin)) {
            return ALTITUDE_ABSOLUTE;
        } else if (value == getAltitudeClampToGround(plugin)) {
            return ALTITUDE_CLAMP_TO_GROUND;
        } else if (value == getAltitudeClampToSeaFloor(plugin)) {
            return ALTITUDE_CLAMP_TO_SEA_FLOOR;
        } else if (value == getAltitudeRelativeToGround(plugin)) {
            return ALTITUDE_RELATIVE_TO_GROUND;
        } else if (value == getAltitudeRelativeToSeaFloor(plugin)) {
            return ALTITUDE_RELATIVE_TO_SEA_FLOOR;
        } else {
            throw new IllegalArgumentException("Unrecognized KmlAltitudeMode: "+value);
        }
    }

    static int toJavaScript(KmlAltitudeMode mode, GEPlugin plugin) {
        switch (mode) {
        case ALTITUDE_ABSOLUTE:
            return getAltitudeAbsolute(plugin);
        case ALTITUDE_CLAMP_TO_GROUND:
            return getAltitudeClampToGround(plugin);
        case ALTITUDE_CLAMP_TO_SEA_FLOOR:
            return getAltitudeClampToSeaFloor(plugin);
        case ALTITUDE_RELATIVE_TO_GROUND:
            return getAltitudeRelativeToGround(plugin);
        case ALTITUDE_RELATIVE_TO_SEA_FLOOR:
           return getAltitudeRelativeToSeaFloor(plugin);
        default:
            throw new IllegalArgumentException("Invalid mode: "+mode);
        }
    }
}
