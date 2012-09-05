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



public enum KmlViewRefreshMode {
    /**
     * Ignore changes in the view. Also ignore viewFormat parameters, if any.
     * This view refresh mode is the default.
     */
    VIEW_REFRESH_NEVER,

    /**
     * Refresh the file only when the user explicitly requests it.
     */
    VIEW_REFRESH_ON_REQUEST,

    /**
     * Refresh n seconds after movement stops, where n is specified in
     * viewRefreshTime.
     */
    VIEW_REFRESH_ON_STOP,

    /**
     * Refresh only when the feature's Region becomes active.
     */
    VIEW_REFRESH_ON_REGION;
    
    /**
     * Ignore changes in the view. Also ignore viewFormat parameters, if any.
     * This view refresh mode is the default.
     * 
     * @param plugin
     * @return
     */
    public static native int getRefreshNever(GEPlugin plugin) /*-{
        return plugin.VIEW_REFRESH_NEVER;
    }-*/;

    /**
     * Refresh the file only when the user explicitly requests it.
     * 
     * @param plugin
     * @return
     */
    public static native int getRefreshOnRequest(GEPlugin plugin) /*-{
        return plugin.VIEW_REFRESH_ON_REQUEST;
    }-*/;

    /**
     * Refresh n seconds after movement stops, where n is specified in
     * viewRefreshTime.
     * 
     * @param plugin
     * @return
     */
    public static native int getRefreshOnStop(GEPlugin plugin) /*-{
        return plugin.VIEW_REFRESH_ON_STOP;
    }-*/;

    /**
     * Refresh only when the feature's Region becomes active.
     * 
     * @param plugin
     * @return
     */
    public static native int getRefreshOnRegion(GEPlugin plugin) /*-{
        return plugin.VIEW_REFRESH_ON_REGION;
    }-*/;
    
    static KmlViewRefreshMode toJava(int mode, GEPlugin plugin) {
        if (mode == getRefreshNever(plugin)) {
            return VIEW_REFRESH_NEVER;
        } else if (mode == getRefreshOnRegion(plugin)) {
            return VIEW_REFRESH_ON_REGION;
        } else if (mode == getRefreshOnRequest(plugin)) {
            return VIEW_REFRESH_ON_REQUEST;
        } else if (mode == getRefreshOnStop(plugin)) {
            return VIEW_REFRESH_ON_STOP;
        } else {
            throw new IllegalArgumentException("Unsupported KmlViewRefreshMode: "+mode);
        }
    }
    
    static int toJavaScript(KmlViewRefreshMode mode, GEPlugin plugin) {
        switch (mode) {
        case VIEW_REFRESH_NEVER:
            return getRefreshNever(plugin);
        case VIEW_REFRESH_ON_REGION:
            return getRefreshOnRegion(plugin);
        case VIEW_REFRESH_ON_REQUEST:
            return getRefreshOnRequest(plugin);
        case VIEW_REFRESH_ON_STOP:
            return getRefreshOnStop(plugin);
        default:
            throw new IllegalArgumentException("Unsupported KmlViewRefreshMode: "+mode);
        }
    }
    
}
