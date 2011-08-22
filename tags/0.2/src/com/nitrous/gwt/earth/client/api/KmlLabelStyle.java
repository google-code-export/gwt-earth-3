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
 * Specifies how the name of a feature is drawn in the 3D viewer. A custom
 * color, color mode, and scale for the label (name) can be specified
 * 
 * @author Nick
 * 
 */
public class KmlLabelStyle extends KmlColorStyle {
    protected KmlLabelStyle() {
    }

    /**
     * @return the label size
     */
    public final native float getScale()/*-{
        return this.getScale();
    }-*/;

    /**
     * Resizes the label.
     * @param scale the label size
     */
    public final native void setScale(float scale)/*-{
        this.setScale(scale);
    }-*/;
}
