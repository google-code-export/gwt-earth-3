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


public enum KmlUnits {
    /**
     * Screen coordinates are to be interpreted as a fraction of an item, like
     * an image or Google Earth window.
     */
    UNITS_FRACTION,

    /**
     * Screen coordinates are to be interpreted as pixels from the left or
     * bottom edge.
     */
    UNITS_PIXELS,

    /**
     * Screen coordinates are to be interpreted as pixels from the top or right
     * edge.
     */
    UNITS_INSET_PIXELS;

    public static native int getUnitsFraction(GEPlugin plugin) /*-{
		return plugin.UNITS_FRACTION;
    }-*/;

    public static native int getUnitsPixels(GEPlugin plugin) /*-{
		return plugin.UNITS_PIXELS;
    }-*/;

    public static native int getUnitsInsetPixels(GEPlugin plugin) /*-{
		return plugin.UNITS_INSET_PIXELS;
    }-*/;
    
    static KmlUnits toJava(int unit, GEPlugin plugin) {
        if (unit == getUnitsFraction(plugin)) {
            return UNITS_FRACTION;
        } else if (unit == getUnitsPixels(plugin)) {
            return UNITS_PIXELS;
        } else if (unit == getUnitsInsetPixels(plugin)) {
            return UNITS_INSET_PIXELS;
        } else {
            throw new IllegalArgumentException("Unsupported KmlUnits: "+unit);
        }
    }
    
    static int toJavaScript(KmlUnits unit, GEPlugin plugin) {
        switch (unit) {
        case UNITS_FRACTION:
            return getUnitsFraction(plugin);
        case UNITS_INSET_PIXELS:
            return getUnitsInsetPixels(plugin);
        case UNITS_PIXELS:
            return getUnitsPixels(plugin);
        default:
            throw new IllegalArgumentException("Invalid KmlUnits: "+unit);
        }
    }

}
