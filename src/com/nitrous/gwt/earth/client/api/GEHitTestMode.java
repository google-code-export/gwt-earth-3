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


public enum GEHitTestMode {
    /**
     * When using the GEView.hitTest method, this mode samples the globe (the
     * earth's sphere at altitude 0, without terrain or buildings).
     */
    HIT_TEST_GLOBE,
    
    /**
     * When using the GEView.hitTest method, this mode samples the earth's
     * terrain (the ground surface, including variations in altitude).
     */
    HIT_TEST_TERRAIN,
    
    /**
     * When using the GEView.hitTest method, this mode samples 3D buildings.
     */
    HIT_TEST_BUILDINGS;
    
    public static GEHitTestMode toJava(int mode, GEPlugin plugin) {
        if (mode == getHitTestGlobe(plugin)) {
            return GEHitTestMode.HIT_TEST_GLOBE;
        } else if (mode == getHitTestTerrain(plugin)) {
            return GEHitTestMode.HIT_TEST_TERRAIN;
        } else if (mode == getHitTestBuildings(plugin)) {
            return GEHitTestMode.HIT_TEST_BUILDINGS;
        } else {
            throw new IllegalArgumentException("Unreckognized GEHitTestMode: "+mode);
        }
    }
    public static int toJavaScript(GEHitTestMode mode, GEPlugin plugin) {
        switch (mode) {
        case HIT_TEST_BUILDINGS:
            return getHitTestBuildings(plugin);
        case HIT_TEST_GLOBE:
            return getHitTestGlobe(plugin);
        case HIT_TEST_TERRAIN:
            return getHitTestTerrain(plugin);
        default:
            throw new IllegalArgumentException("Unreckognized GEHitTestMode: "+mode);
        }
    }
    public native static int getHitTestGlobe(GEPlugin plugin) /*-{
        return plugin.HIT_TEST_GLOBE;
    }-*/;
    public native static int getHitTestTerrain(GEPlugin plugin) /*-{
        return plugin.HIT_TEST_TERRAIN;
    }-*/;
    public native static int getHitTestBuildings(GEPlugin plugin) /*-{
        return plugin.HIT_TEST_BUILDINGS;
    }-*/;

}
