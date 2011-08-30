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
 * Specifies the behavior of the object's geometry.
 * @author Nick
 *
 */
public class KmlExtrudableGeometry extends KmlAltitudeGeometry {
    protected KmlExtrudableGeometry() {
    }

    /**
     * Specifies whether to connect the geometry to the ground.
     * @return
     */
    public final native boolean getExtrude() /*-{
        return this.getExtrude();
    }-*/;

    /**
     * 
     * @param extrude Specifies whether to connect the geometry to the ground.
     */
    public final native void setExtrude(boolean extrude) /*-{
        this.setExtrude(extrude);
    }-*/;

    /**
     * Specifies whether to allow the geometry to follow the terrain elevation.
     * @return 
     */
    public final native boolean getTessellate() /*-{
        return this.getTessellate();
    }-*/;

    /**
     * Specifies whether to allow the geometry to follow the terrain elevation.
     * @param tessellate
     */
    public final native void setTessellate(boolean tessellate) /*-{
        this.setTessellate(tessellate);
    }-*/;

}
