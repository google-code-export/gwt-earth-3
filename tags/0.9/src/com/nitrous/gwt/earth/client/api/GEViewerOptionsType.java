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


public enum GEViewerOptionsType {
    /**
     * Passed to the KmlViewerOptions.setOption method, along with a
     * GEViewerOptionsValueEnum, to specify whether the Sun option should be
     * visible. Sun can also be enabled/disabled with GEPlugin.getSun.
     */
    OPTION_SUNLIGHT,

    /**
     * Passed to the KmlViewerOptions.setOption method, along with a
     * GEViewerOptionsValueEnum, to specify whether historical imagery should be
     * enabled.
     */
    OPTION_HISTORICAL_IMAGERY,

    /**
     * Passed to the KmlViewerOptions.setOption method, along with a
     * GEViewerOptionsValueEnum, to specify whether Street View should be
     * enabled when the view reaches ground level. Note that this applies only
     * to programmatic movement, such as fly-tos; to control whether the user
     * can enter Street View using manual navigation controls, call
     * ge.getPlugin().streetViewEnabled(true).
     */
    OPTION_STREET_VIEW;

    public static native int getOptionSunlight(GEPlugin plugin) /*-{
		return plugin.OPTION_SUNLIGHT;
    }-*/;

    public static native int getOptionHistoricalImagery(GEPlugin plugin) /*-{
		return plugin.OPTION_HISTORICAL_IMAGERY;
    }-*/;

    public static native int getOptionStreetView(GEPlugin plugin) /*-{
		return plugin.OPTION_STREET_VIEW;
    }-*/;
    
    static GEViewerOptionsType toJava(int value, GEPlugin plugin) {
        if (getOptionHistoricalImagery(plugin) == value) {
            return OPTION_HISTORICAL_IMAGERY;
        } else  if (getOptionStreetView(plugin) == value) {
              return OPTION_STREET_VIEW;
        } else  if (getOptionSunlight(plugin) == value) {
            return OPTION_SUNLIGHT;
        } else {
            throw new IllegalArgumentException("invalid GEViewerOptionType: "+value);
        }
    }
    
    static int toJavaScript(GEViewerOptionsType type, GEPlugin plugin) {
        switch (type) {
        case OPTION_HISTORICAL_IMAGERY:
            return getOptionHistoricalImagery(plugin);
        case OPTION_STREET_VIEW:
            return getOptionStreetView(plugin);
        case OPTION_SUNLIGHT:
            return getOptionSunlight(plugin);
        default:
            throw new IllegalArgumentException("unsupported GEViewerOptionsType: "+type);
        }
    }
}
