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

public enum GEMapType {
    /**
     * The Earth map type, used with GEOptions' setMapType
     */
    MAP_TYPE_EARTH,
    
    /**
     * The Sky map type, used with GEOptions' setMapType
     */
    MAP_TYPE_SKY;
    
    static GEMapType toJava(int type, GEPlugin plugin) {
        if (type == getMapTypeEarth(plugin)) {
            return GEMapType.MAP_TYPE_EARTH;
        } else if (type == getMapTypeSky(plugin)) {
            return GEMapType.MAP_TYPE_SKY;
        } else {
            throw new IllegalArgumentException("Unknown GEMapType: "+type);
        }
    }
    
    static int toJavaScript(GEMapType type, GEPlugin plugin) {
        switch (type) {
        case MAP_TYPE_EARTH:
            return getMapTypeEarth(plugin);
        case MAP_TYPE_SKY:
            return getMapTypeSky(plugin);
        default:
            throw new IllegalArgumentException("Unknown GEMapType: "+type);
        }
    }
    
    /**
     * 
     * @param plugin
     * @return The Earth map type, used with GEOptions' setMapType
     */
    public static native int getMapTypeEarth(GEPlugin plugin) /*-{
        return plugin.MAP_TYPE_EARTH;
    }-*/;
    
    /**
     * 
     * @param plugin
     * @return The Sky map type, used with GEOptions' setMapType
     */
    public static native int getMapTypeSky(GEPlugin plugin) /*-{
        return plugin.MAP_TYPE_SKY;
    }-*/;
    
}
