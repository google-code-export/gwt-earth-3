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

/**
 * Maps between two different icon styles. Typically this interface is used to
 * provide separate normal and highlighted styles for a placemark, so that the
 * highlighted version appears when the user mouses over the icon.
 * 
 * @author Nick
 * 
 */
public class KmlStyleMap extends KmlStyleSelector {
    protected KmlStyleMap() {
    }

    /**
     * Sets both URLs for the placemark style.
     * 
     * @param normalStyleUrl
     * @param highlightStyleUrl
     */
    public final native void setUrl(String normalStyleUrl, String highlightStyleUrl) /*-{
        this.setUrl(normalStyleUrl, highlightStyleUrl);
    }-*/;

    /**
     * Sets both placemark styles.
     * 
     * @param normalStyle
     * @param highlightStyle
     */
    public final native void setStyle(KmlStyle normalStyle, KmlStyle highlightStyle) /*-{
        this.setStyle(normalStyle, highlightStyle);
    }-*/;

    /**
     * Defines a normal style for a placemark.
     * 
     * @return
     */
    public final native String getNormalStyleUrl() /*-{
        return this.getNormalStyleUrl();
    }-*/;

    /**
     * Defines a normal style for a placemark.
     * 
     * @param normalStyleUrl
     */
    public final native void setNormalStyleUrl(String normalStyleUrl) /*-{
        this.setNormalStyleUrl(normalStyleUrl);
    }-*/;

    /**
     * Defines highlighted styles for a placemark, so that the highlighted
     * version appears when the user mouses over the icon in Google Earth.
     * 
     * @return
     */
    public final native String getHighlightStyleUrl() /*-{
        return this.getHighlightStyleUrl();
    }-*/;

    /**
     * Defines highlighted styles for a placemark, so that the highlighted
     * version appears when the user mouses over the icon in Google Earth.
     * 
     * @param highlightStyleUrl
     */
    public final native void setHighlightStyleUrl(String highlightStyleUrl) /*-{
        this.setHighlightStyleUrl(highlightStyleUrl);
    }-*/;

    /**
     * Defines a normal style for a placemark.
     * 
     * @return
     */
    public final native KmlStyle getNormalStyle() /*-{
        return this.getNormalStyle();
    }-*/;

    /**
     * Defines a normal style for a placemark.
     * 
     * @param normalStyle
     */
    public final native void setNormalStyle(KmlStyle normalStyle) /*-{
        this.setNormalStyle(normalStyle);
    }-*/;

    /**
     * Defines highlighted styles for a placemark, so that the highlighted
     * version appears when the user mouses over the icon in Google Earth.
     * 
     * @return
     */
    public final native KmlStyle getHighlightStyle() /*-{
        return this.getHighlightStyle();
    }-*/;

    /**
     * Defines highlighted styles for a placemark, so that the highlighted
     * version appears when the user mouses over the icon in Google Earth.
     * 
     * @param highlightStyle
     */
    public final native void setHighlightStyle(KmlStyle highlightStyle) /*-{
        this.setHighlightStyle(highlightStyle);
    }-*/;
}
