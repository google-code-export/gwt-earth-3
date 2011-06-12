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
 * Defines the icon, label, line, list, polygon, and balloon styles.
 * 
 * @author Nick
 * 
 */
public class KmlStyle extends KmlStyleSelector {
    protected KmlStyle() {
    }

    /**
     * Specifies how icons for point placemarks are drawn in Google Earth.
     * 
     * @return
     */
    public final native KmlIconStyle getIconStyle()/*-{
        return this.getIconStyle();
    }-*/;

    /**
     * Specifies how the name of a feature is drawn in the 3D viewer. A custom
     * color, color mode, and scale for the label (name) can be specified.
     * 
     * @return
     */
    public final native KmlLabelStyle getLabelStyle()/*-{
        return this.getLabelStyle();
    }-*/;

    /**
     * Specifies the drawing style (color, color mode, and line width) for line
     * geometry. Line geometry includes the outlines of outlined polygons and
     * the extruded tether of Placemark icons (if extrusion is enabled).
     * 
     * @return
     */
    public final native KmlLineStyle getLineStyle()/*-{
        return this.getLineStyle();
    }-*/;

    /**
     * Specifies the style for list geometry.
     * 
     * @return
     */
    public final native KmlListStyle getListStyle()/*-{
        return this.getListStyle();
    }-*/;

    /**
     * Specifies the drawing style for polygons, including polygon extrusions
     * (which look like the walls of buildings) and line extrusions (which look
     * like solid fences).
     * 
     * @return
     */
    public final native KmlPolyStyle getPolyStyle()/*-{
        return this.getPolyStyle();
    }-*/;

    /**
     * Specifies the drawing style for balloons.
     * 
     * @return
     */
    public final native KmlBalloonStyle getBalloonStyle()/*-{
        return this.getBalloonStyle();
    }-*/;

}
