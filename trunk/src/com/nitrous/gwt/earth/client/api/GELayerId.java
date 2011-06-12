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


public enum GELayerId {
    /**
     * The Layer ID of the terrain layer. Use as an argument to GEPlugin.getLayerById()
     * or GEPlugin.enableLayerById(). 
     * 
     * terrainUUID
     */
    LAYER_TERRAIN,
    /**
     * The Layer ID of the roads layer. Use as an argument to GEPlugin.getLayerById() or
     * GEPlugin.enableLayerById(). 
     * 
     * 4ddec456-c7b3-11dc-aaa5-dd553d8c9902
     */
    LAYER_ROADS,
    /**
     * The Layer ID of the photorealistic buildings layer. Use as an argument to
     * GEPlugin.getLayerById() or GEPlugin.enableLayerById(). 
     * 
     * 2a412484-7181-11de-8092-17a790575c91
     */
    LAYER_BUILDINGS,
    /**
     * The Layer ID of the low resolution (gray) buildings layer. Use as an
     * argument to GEPlugin.getLayerById() or GEPlugin.enableLayerById(). Note that as
     * photorealistic buildings continue to be created and added to the
     * LAYER_BUILDINGS layer, the low-resolution version of those buildings will
     * be removed from this layer. This layer will therefore change over time.
     * 
     * 3a5bb88e-7181-11de-88da-17a790575c91
     */
    LAYER_BUILDINGS_LOW_RESOLUTION,
    /**
     * The Layer ID of the borders layer. Use as an argument to GEPlugin.getLayerById()
     * or GEPlugin.enableLayerById(). 
     * 
     * 53004770-c7b3-11dc-92c2-dd553d8c9902
     */
    LAYER_BORDERS,
    
    /**
     * The Layer ID of the trees layer. Use as an argument to GEPlugin.getLayerById() or
     * GEPlugin.enableLayerById(). 
     * 
     * 8d540610-9429-11df-ad05-451522926098
     */
    LAYER_TREES;

    static String toJavaScript(GELayerId layerId, GEPlugin plugin) {
        switch (layerId) {
        case LAYER_BORDERS:
            return getBorderLayerId(plugin);
        case LAYER_BUILDINGS:
            return getBuildingLayerId(plugin);
        case LAYER_BUILDINGS_LOW_RESOLUTION:
            return getLoResBuildingLayerId(plugin);
        case LAYER_ROADS:
            return getRoadLayerId(plugin);
        case LAYER_TERRAIN:
            return getTerrainLayerId(plugin);            
        case LAYER_TREES:
            return getTreeLayerId(plugin);
        default:
            throw new IllegalArgumentException("Unreckognized GELayerId: "+layerId);
        }
    }

    /**
     * Returns the GELayerId representation of the specified layer ID or null if not reckognized.
     * @param layerId The layer ID to translate into a GELayerId
     * @param plugin The GEPlugin reference
     * @return the GELayerId representation of the specified layer ID or null if not reckognized.
     */
    static GELayerId toJava(String layerId, GEPlugin plugin) {
        if (getTerrainLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_TERRAIN;
        } else if (getBorderLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_BORDERS;
        } else if (getBuildingLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_BUILDINGS;
        } else if (getLoResBuildingLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_BUILDINGS_LOW_RESOLUTION;
        } else if (getRoadLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_ROADS;
        } else if (getTreeLayerId(plugin).equals(layerId)) {
            return GELayerId.LAYER_TREES;
        } else {
            return null;
        }
    }
    
    /**
     * The Layer ID of the terrain layer. Use as an argument to GEPlugin.getLayerById()
     * or GEPlugin.enableLayerById(). 
     * 
     * terrainUUID
     */
    public final native static String getTerrainLayerId(GEPlugin ge)/*-{
        return ge.LAYER_TERRAIN;
    }-*/;

    /**
     * The Layer ID of the roads layer. Use as an argument to GEPlugin.getLayerById() or
     * GEPlugin.enableLayerById(). 
     * 
     * 4ddec456-c7b3-11dc-aaa5-dd553d8c9902
     */
    public final native static String getRoadLayerId(GEPlugin ge)/*-{
        return ge.LAYER_ROADS;
    }-*/;

    /**
     * The Layer ID of the photorealistic buildings layer. Use as an argument to
     * GEPlugin.getLayerById() or GEPlugin.enableLayerById(). 
     * 
     * 2a412484-7181-11de-8092-17a790575c91
     */
    public final native static String getBuildingLayerId(GEPlugin ge)/*-{
        return ge.LAYER_BUILDINGS;
    }-*/;

    /**
     * The Layer ID of the low resolution (gray) buildings layer. Use as an
     * argument to GEPlugin.getLayerById() or GEPlugin.enableLayerById(). Note that as
     * photorealistic buildings continue to be created and added to the
     * LAYER_BUILDINGS layer, the low-resolution version of those buildings will
     * be removed from this layer. This layer will therefore change over time.
     * 
     * 3a5bb88e-7181-11de-88da-17a790575c91
     */
    public final native static String getLoResBuildingLayerId(GEPlugin ge)/*-{
        return ge.LAYER_BUILDINGS_LOW_RESOLUTION;
    }-*/;

    /**
     * The Layer ID of the borders layer. Use as an argument to GEPlugin.getLayerById()
     * or GEPlugin.enableLayerById(). 
     * 
     * 53004770-c7b3-11dc-92c2-dd553d8c9902
     */
    public final native static String getBorderLayerId(GEPlugin ge)/*-{
        return ge.LAYER_BORDERS;
    }-*/;

    /**
     * The Layer ID of the trees layer. Use as an argument to GEPlugin.getLayerById() or
     * GEPlugin.enableLayerById(). 
     * 
     * 8d540610-9429-11df-ad05-451522926098
     */
    public final native static String getTreeLayerId(GEPlugin ge)/*-{
        return ge.LAYER_TREES;
    }-*/;
}
