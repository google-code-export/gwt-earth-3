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



public enum GENavigationControlType {
    /**
     * The large navigation control type, used with
     * GENavigationControl.setControlType().
     */
    NAVIGATION_CONTROL_LARGE,
    
    /**
     * The small navigation control type, used with
     * GENavigationControl.setControlType().
     */
    NAVIGATION_CONTROL_SMALL;
    
    public static native int getNavigationControlLarge(GEPlugin plugin) /*-{
        return plugin.NAVIGATION_CONTROL_LARGE;
    }-*/;
    
    public static native int getNavigationControlSmall(GEPlugin plugin) /*-{
        return plugin.NAVIGATION_CONTROL_SMALL;
    }-*/;
    
    static GENavigationControlType toJava(int type, GEPlugin plugin) {
        if (type == getNavigationControlLarge(plugin)) {
            return NAVIGATION_CONTROL_LARGE;
        } else if (type == getNavigationControlSmall(plugin)) {
            return NAVIGATION_CONTROL_SMALL;
        } else {
            throw new IllegalArgumentException("Invalid GENaviationControlType: "+type);
        }
    }
    
    static int toJavaScript(GENavigationControlType type, GEPlugin plugin) {
        switch (type) {
        case NAVIGATION_CONTROL_LARGE:
            return getNavigationControlLarge(plugin);
        case NAVIGATION_CONTROL_SMALL:
            return getNavigationControlSmall(plugin);
        default:
            throw new IllegalArgumentException("Unsupported GENaviationControlType: "+type);
        }
    }
    
}
