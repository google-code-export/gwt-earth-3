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



public enum GEVisibility {
    /**
     * Hide the UI element.
     */
    VISIBILITY_HIDE,
    
    /**
     * Show the UI element always.
     */
    VISIBILITY_SHOW,
    
    /**
     * Automatically show or hide the UI element depending on user interaction.
     */
    VISIBILITY_AUTO;
    
    public static native int getVisibilityHide(GEPlugin plugin) /*-{
        return plugin.VISIBILITY_HIDE;
    }-*/;
    public static native int getVisibilityShow(GEPlugin plugin) /*-{
        return plugin.VISIBILITY_SHOW;
    }-*/;
    public static native int getVisibilityAuto(GEPlugin plugin) /*-{
        return plugin.VISIBILITY_AUTO;
    }-*/;    
    
    static GEVisibility toJava(int visibility, GEPlugin plugin) {
        if (visibility == getVisibilityAuto(plugin)) {
            return VISIBILITY_AUTO;
        } else if (visibility == getVisibilityShow(plugin)) {
            return VISIBILITY_SHOW;
        } else if (visibility == getVisibilityHide(plugin)) {
            return VISIBILITY_HIDE;
        } else {
            throw new IllegalArgumentException("Invalid GEVisibility: "+visibility);
        }
    }
    
    static int toJavaScript(GEVisibility visibility, GEPlugin plugin) {
        switch (visibility) {
        case VISIBILITY_AUTO:
            return getVisibilityAuto(plugin);
        case VISIBILITY_HIDE:
            return getVisibilityHide(plugin);
        case VISIBILITY_SHOW:
            return getVisibilityShow(plugin);
        default:
            throw new IllegalArgumentException("Invalid GEVisibility: "+visibility);
        }
    }
}
