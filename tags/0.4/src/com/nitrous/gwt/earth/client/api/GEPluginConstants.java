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

import java.util.ArrayList;


/**
 * A utility class that provides a mapping between GEPlugin JavaScript constants and Java enumerated types. 
 * This class must be initialized by calling GEPlugin.bindConstants() to provide the required GEPlugin reference.
 * @author Nick
 *
 */
class GEPluginConstants {
    private static GEPluginConstants INSTANCE;
    private GEPlugin plugin;
    
    private GEPluginConstants(GEPlugin plugin) {
        this.plugin = plugin;
    }
    
    static void bind(GEPlugin plugin) {
        INSTANCE = new GEPluginConstants(plugin);
    }
    
    /**
     * Retrieve the shared instance of GEPluginConstants
     * @return The shared instance of GEPluginConstants 
     */
    static GEPluginConstants get() {
        if (INSTANCE == null) {
            throw new IllegalStateException("GEPluginConstants is not yet initialized. Call GEPlugin.bindConstants() first");
        }
        return INSTANCE;
    }
    
    GEMapType toGEMapType(int value) {
        return GEMapType.toJava(value, plugin); 
    }
    
    int toJavaScript(GEMapType type) {
        return GEMapType.toJavaScript(type, plugin);
    }
    
    int toJavaScript(GEHitTestMode mode) {
        return GEHitTestMode.toJavaScript(mode, plugin);
    }
    
    GEHitTestMode[] toGEHitTestMode(int value) {
        ArrayList<GEHitTestMode> modes = new ArrayList<GEHitTestMode>();
        for (GEHitTestMode mode : GEHitTestMode.values()) {
            int modeNative = toJavaScript(mode);
            if ((modeNative & value) == 1) {
                modes.add(mode);
            }
        }
        return modes.toArray(new GEHitTestMode[modes.size()]);
    }
    
    GEEventPhase toGEEventPhase(int value) {
        return GEEventPhase.toJava(value, plugin);
    }
    
    int toJavaScript(GEVisibility type) {
        return GEVisibility.toJavaScript(type, plugin);
    }
    
    GEVisibility toGEVisibility(int value) {
        return GEVisibility.toJava(value, plugin);
    }
    
    int toJavaScript(GEEventPhase type) {
        return GEEventPhase.toJavaScript(type, plugin);
    }
    
    int toJavaScript(GENavigationControlType type) {
        return GENavigationControlType.toJavaScript(type, plugin);
    }
    
    GENavigationControlType toGENavigationControlType(int value) {
        return GENavigationControlType.toJava(value, plugin);
    }
    
    String toJavaScript(GELayerId layerId) {
        return GELayerId.toJavaScript(layerId, plugin);
    }
    
    GELayerId toGELayerId(String layerId) {
        return GELayerId.toJava(layerId, plugin);
    }
    
    int toJavaScript(GEViewerOptionsType type) {
        return GEViewerOptionsType.toJavaScript(type, plugin);
    }
    
    GEViewerOptionsType toGEViewerOptionsType(int type) {
        return GEViewerOptionsType.toJava(type, plugin);
    }
    
    int toJavaScript(GEViewerOptionsValue type) {
        return GEViewerOptionsValue.toJavaScript(type, plugin);
    }
    
    GEViewerOptionsValue toGEViewerOptionsValue(int type) {
        return GEViewerOptionsValue.toJava(type, plugin);
    }

    int toJavaScript(KmlAltitudeMode mode) {
        return KmlAltitudeMode.toJavaScript(mode, plugin);
    }
    
    KmlAltitudeMode toKmlAltitudeMode(int mode) {
        return KmlAltitudeMode.toJava(mode, plugin);
    }

    int toJavaScript(KmlColorMode mode) {
        return KmlColorMode.toJavaScript(mode, plugin);
    }
    
    KmlColorMode toKmlColorMode(int mode) {
        return KmlColorMode.toJava(mode, plugin);
    }
    
    int toJavaScript(KmlListItemType mode) {
        return KmlListItemType.toJavaScript(mode, plugin);
    }
    
    KmlListItemType toKmlListItemType(int mode) {
        return KmlListItemType.toJava(mode, plugin);
    }
    
    int toJavaScript(KmlRefreshMode mode) {
        return KmlRefreshMode.toJavaScript(mode, plugin);
    }
    
    KmlRefreshMode toKmlRefreshMode(int mode) {
        return KmlRefreshMode.toJava(mode, plugin);
    }

    int toJavaScript(KmlUnits mode) {
        return KmlUnits.toJavaScript(mode, plugin);
    }
    
    KmlUnits toKmlUnits(int mode) {
        return KmlUnits.toJava(mode, plugin);
    }

    int toJavaScript(KmlViewRefreshMode mode) {
        return KmlViewRefreshMode.toJavaScript(mode, plugin);
    }
    
    KmlViewRefreshMode toKmlViewRefreshMode(int mode) {
        return KmlViewRefreshMode.toJava(mode, plugin);
    }
    
}
