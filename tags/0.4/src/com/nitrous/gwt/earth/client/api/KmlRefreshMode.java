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


public enum KmlRefreshMode {
    /**
     * Refresh when the file is loaded and whenever the Link parameters change.
     * This refresh mode is the default.
     */
    REFRESH_ON_CHANGE,

    /**
     * Refresh every n seconds (specified in refreshInterval).
     */
    REFRESH_ON_INTERVAL,

    /**
     * Refresh when the expiration time is reached. If a fetched file has a
     * NetworkLinkControl, the expires time takes precedence over expiration
     * times specified in HTTP headers. If no expires time is specified, the
     * HTTP max-age header is used (if present). If max-age is not present, the
     * Expires HTTP header is used (if present).
     */
    REFRESH_ON_EXPIRE;

    public static native int getRefreshOnChange(GEPlugin plugin) /*-{
		return plugin.REFRESH_ON_CHANGE;
    }-*/;

    public static native int getRefreshOnInterval(GEPlugin plugin) /*-{
		return plugin.REFRESH_ON_INTERVAL;
    }-*/;

    public static native int getRefreshOnExpire(GEPlugin plugin) /*-{
		return plugin.REFRESH_ON_EXPIRE;
    }-*/;
    
    static KmlRefreshMode toJava(int mode, GEPlugin plugin) {
        if (mode == getRefreshOnChange(plugin)) {
            return KmlRefreshMode.REFRESH_ON_CHANGE;
        } else if (mode == getRefreshOnExpire(plugin)) {
            return KmlRefreshMode.REFRESH_ON_EXPIRE;
        } else if (mode == getRefreshOnInterval(plugin)) {
            return KmlRefreshMode.REFRESH_ON_INTERVAL;
        } else {
            throw new IllegalArgumentException("Unsupported KmlRefreshMode: "+mode);
        }
    }
    static int toJavaScript(KmlRefreshMode mode, GEPlugin plugin) {
        switch (mode) {
        case REFRESH_ON_CHANGE:
            return getRefreshOnChange(plugin);
        case REFRESH_ON_EXPIRE:
            return getRefreshOnExpire(plugin);
        case REFRESH_ON_INTERVAL:
            return getRefreshOnInterval(plugin);
        default:
            throw new IllegalArgumentException("Unsupported KmlRefreshMode: "+mode);
        }
    }

}
