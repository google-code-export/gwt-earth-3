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


public enum GEViewerOptionsValue {
    /**
     * Sets the render state to its default value. Currently, sunlight, Street
     * View, and historical imagery all default to a disabled state. Passed to
     * the KmlViewerOptions.setOption method.
     */
    OPTION_STATE_DEFAULT,
    /**
     * Set the render state to on. Passed to the KmlViewerOptions.setOption
     * method.
     */
    OPTION_STATE_ENABLED,
    /**
     * Set the render state to off. Passed to the KmlViewerOptions.setOption
     * method.
     */
    OPTION_STATE_DISABLED;

    public static final native int getOptionStateDefault(GEPlugin plugin) /*-{
		return plugin.OPTION_STATE_DEFAULT;
    }-*/;

    public static final native int getOptionStateEnabled(GEPlugin plugin) /*-{
		return plugin.OPTION_STATE_ENABLED;
    }-*/;

    public static final native int getOptionStateDisabled(GEPlugin plugin) /*-{
		return plugin.OPTION_STATE_DISABLED;
    }-*/;

    static GEViewerOptionsValue toJava(int value, GEPlugin plugin) {
        if (getOptionStateDefault(plugin) == value) {
            return OPTION_STATE_DEFAULT;
        } else if (getOptionStateDisabled(plugin) == value) {
            return OPTION_STATE_DISABLED;
        } else if (getOptionStateEnabled(plugin) == value) {
            return OPTION_STATE_ENABLED;
        } else {
            throw new IllegalArgumentException("Invalid GEViewerOptionsValue: "+value);
        }
    }
    
    static int toJavaScript(GEViewerOptionsValue value, GEPlugin plugin) {
        switch (value) {
        case OPTION_STATE_DEFAULT:
            return getOptionStateDefault(plugin);
        case OPTION_STATE_DISABLED:
            return getOptionStateDisabled(plugin);
        case OPTION_STATE_ENABLED:
            return getOptionStateEnabled(plugin);
        default:
            throw new IllegalArgumentException("Invalid GEViewerOptionsValue: "+value);
        }
    }
}
