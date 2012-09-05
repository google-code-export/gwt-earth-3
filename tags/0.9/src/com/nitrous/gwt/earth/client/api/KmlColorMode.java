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


public enum KmlColorMode {
    /**
     * Apply no color mode effect, i.e. use the base color as is.
     */
    COLOR_NORMAL,
    /**
     * Inherit the color mode from ancestor styles.
     */
    COLOR_INHERIT,
    /**
     * Apply a random linear scale to the base color. See the KML
     * &lt;colorMode&gt; documentation for more details.
     */
    COLOR_RANDOM;

    public static native int getNormal(GEPlugin plugin) /*-{
		return plugin.COLOR_NORMAL;
    }-*/;

    public static native int getInherit(GEPlugin plugin) /*-{
		return plugin.COLOR_INHERIT;
    }-*/;

    public static native int getRandom(GEPlugin plugin) /*-{
		return plugin.COLOR_RANDOM;
    }-*/;
    
    static KmlColorMode toJava(int mode, GEPlugin plugin) {
        if (mode == getNormal(plugin)) {
            return COLOR_NORMAL;
        } else if (mode == getInherit(plugin)) {
            return KmlColorMode.COLOR_INHERIT;
        } else if (mode == getRandom(plugin)) {
            return KmlColorMode.COLOR_RANDOM;
        } else {
            throw new IllegalArgumentException("Unsupported KmlColorMode: "+mode);
        }
    }
    
    static int toJavaScript(KmlColorMode mode, GEPlugin plugin) {
        switch (mode) {
        case COLOR_INHERIT:
            return getInherit(plugin);
        case COLOR_NORMAL:
            return getNormal(plugin);
        case COLOR_RANDOM:
            return getRandom(plugin);
        default:
            throw new IllegalArgumentException("Invalid KmlColorMode: "+mode);
        }
    }

}
