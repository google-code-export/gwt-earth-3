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


/**
 * A container for the various layers displayed with the Google Earth Plug-in.
 * It contains the same layers as Google Earth.
 * 
 * @author Nick
 * 
 */
public class KmlLayerRoot extends KmlFolder {
    protected KmlLayerRoot() {
    }

    /**
     * Returns the layer based on the layer's ID.
     * 
     * @param id
     * @return
     */
    public final native KmlFolder getLayerById(String id) /*-{
        return this.getLayerById(id);
    }-*/;

    /**
     * Hide or show the layer associated with the specified ID.
     * 
     * @param layerId
     *            The ID of the layer to hide or show.
     * @param visible
     *            True to show the layer, false to hide it.
     */
    public final void enableLayer(GELayerId layer, boolean visible) {        
        enableLayerById(GEPluginConstants.get().toJavaScript(layer), visible);
    }
    
    /**
     * Enables a layer based on its ID.
     * 
     * @param id
     *            ID of layer. See the GEPlugin object reference for possible
     *            values.
     * @param visibility
     *            Specifies whether the feature is drawn in the 3D viewer when
     *            it is initially loaded. In order for a feature to be visible,
     *            visibility must also be set to 1.
     */
    public final native void enableLayerById(String id, boolean visibility) /*-{
        this.enableLayerById(id, visibility);
    }-*/;

    /**
     * @return Returns the drawing order for this database.
     */
    public final native int getDrawOrder() /*-{
        return this.getDrawOrder();
    }-*/;

    /**
     * Defines the drawing order for databases. Drawing order is lowest to
     * highest. Google Earth Enterprise customers can add a side database and
     * set the drawOrder to be either before or after that of the main database.
     * Side databases default to a drawing order of 0.
     * 
     * @param drawOrder
     */
    public final native void setDrawOrder(int drawOrder) /*-{
        this.setDrawOrder(drawOrder);
    }-*/;
}
