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
 * The KmlLineStyle object specifies the drawing style (color, color mode, and
 * line width) for all line geometry. Line geometry includes the outlines of
 * outlined polygons and the extruded "tether" of Placemark icons (if extrusion
 * is enabled).
 * 
 * @author Nick
 * 
 */
public class KmlLineStyle extends KmlColorStyle {
    protected KmlLineStyle() {
    }

    /**
     * 
     * @return Width of the line, in pixels.
     */
    public final native float getWidth() /*-{
		return this.getWidth();
    }-*/;

    /**
     * 
     * @param width Width of the line, in pixels.
     */
    public final native void setWidth(float width)/*-{
		this.setWidth(width);
    }-*/;
}
