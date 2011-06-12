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
 * The KmlColorStyle object is an abstract object. It specifies the color and
 * color mode of extended style types.
 * 
 * @author Nick
 * 
 */
public class KmlColorStyle extends KmlObject {
    protected KmlColorStyle() {
    }

    /**
     * 
     * @return Color and opacity (alpha) values.
     */
    public final native KmlColor getColor() /*-{
		return this.getColor();
    }-*/;

    /**
     * Specifies which color mode effect to apply to the base color.
     * @return
     */
    public final KmlColorMode getColorMode() {
        return GEPluginConstants.get().toKmlColorMode(getColorModeNative());
    }

    /**
     * Specifies which color mode effect to apply to the base color.
     * @param colorMode
     */
    public final void setColorMode(KmlColorMode colorMode) {
        this.setColorModeNative(GEPluginConstants.get().toJavaScript(colorMode));
    }

    /**
     * Specifies which color mode effect to apply to the base color.
     * @return
     */
    private final native int getColorModeNative() /*-{
        return this.getColorMode();
    }-*/;

    /**
     * Specifies which color mode effect to apply to the base color.
     * @param colorMode
     */
    private final native void setColorModeNative(int colorMode) /*-{
        this.setColorMode(colorMode);
    }-*/;
}
