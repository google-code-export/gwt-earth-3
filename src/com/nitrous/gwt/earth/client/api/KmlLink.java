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
 * Specifies the location of any KML files fetched by network links, image files
 * used by icons in icon styles, ground overlays, and screen overlays, or model
 * files used in the Model object.
 * 
 * @author Nick
 * 
 */
public class KmlLink extends KmlObject {
    protected KmlLink() {
    }

    /**
     * @return A URL (either an HTTP address or a local file specification).
     *         When the parent of Link is a NetworkLink, href is a KML file.
     *         When the parent of Link is a Model, href is a COLLADA file. When
     *         the parent of Link is an Overlay, href is an image.
     */
    public final native String getHref() /*-{
		return this.getHref();
    }-*/;

    /**
     * 
     * @param href
     *            A URL (either an HTTP address or a local file specification).
     *            When the parent of Link is a NetworkLink, href is a KML file.
     *            When the parent of Link is a Model, href is a COLLADA file.
     *            When the parent of Link is an Overlay, href is an image.
     */
    public final native void setHref(String href) /*-{
		this.setHref(href);
    }-*/;

    /**
     * Specifies to use a time-based refresh mode.
     * @return
     */
    public final KmlRefreshMode getRefreshMode() {
        return GEPluginConstants.get().toKmlRefreshMode(getRefreshModeNative());
    }

    /**
     * Specifies to use a time-based refresh mode.
     * @param refreshMode
     */
    public final void setRefreshMode(KmlRefreshMode refreshMode) {
        setRefreshModeNative(GEPluginConstants.get().toJavaScript(refreshMode));
    }

    /**
     * Specifies to use a time-based refresh mode.
     * @return
     */
    private final native int getRefreshModeNative() /*-{
        return this.getRefreshMode();
    }-*/;

    /**
     * Specifies to use a time-based refresh mode.
     * @param refreshMode
     */
    private final native void setRefreshModeNative(int refreshMode) /*-{
        this.setRefreshMode(refreshMode);
    }-*/;
    
    /**
     * Indicates to refresh the file every n seconds.
     * 
     * @return
     */
    public final native float getRefreshInterval() /*-{
		return this.getRefreshInterval();
    }-*/;

    /**
     * Indicates to refresh the file every n seconds.
     * 
     * @param refreshInterval
     */
    public final native void setRefreshInterval(float refreshInterval) /*-{
		this.setRefreshInterval(refreshInterval);
    }-*/;

    /**
     * Specifies how the link is refreshed when the viewport changes.
     * @return
     */
    public final KmlViewRefreshMode getViewRefreshMode() {
        return GEPluginConstants.get().toKmlViewRefreshMode(getViewRefreshModeNative());
    }

    /**
     * Specifies how the link is refreshed when the viewport changes.
     * @param viewRefreshMode
     */
    public final void setViewRefreshMode(KmlViewRefreshMode viewRefreshMode) {
        setViewRefreshModeNative(GEPluginConstants.get().toJavaScript(viewRefreshMode));
    }

    /**
     * Specifies how the link is refreshed when the viewport changes.
     * @return
     */
    private final native int getViewRefreshModeNative() /*-{
        return this.getViewRefreshMode();
    }-*/;

    /**
     * Specifies how the link is refreshed when the viewport changes.
     * @param viewRefreshMode
     */
    private final native void setViewRefreshModeNative(int viewRefreshMode) /*-{
        this.setViewRefreshMode(viewRefreshMode);
    }-*/;
    
    /**
     * Specifies how the link is refreshed when the camera changes.
     * 
     * @return
     */
    public final native float getViewRefreshTime() /*-{
		return this.getViewRefreshTime();
    }-*/;

    /**
     * Specifies how the link is refreshed when the camera changes.
     * 
     * @param viewRefreshTime
     */
    public final native void setViewRefreshTime(float viewRefreshTime) /*-{
		this.setViewRefreshTime(viewRefreshTime);
    }-*/;

    /**
     * Scales the BBOX parameters before sending them to the server. A value
     * less than 1 specifies to use less than the full view (screen). A value
     * greater than 1 specifies to fetch an area that extends beyond the edges
     * of the current view.
     * 
     * @return
     */
    public final native float getViewBoundScale() /*-{
		return this.getViewBoundScale();
    }-*/;

    /**
     * Scales the BBOX parameters before sending them to the server. A value
     * less than 1 specifies to use less than the full view (screen). A value
     * greater than 1 specifies to fetch an area that extends beyond the edges
     * of the current view.
     * 
     * @param viewBoundScale
     */
    public final native void setViewBoundScale(float viewBoundScale) /*-{
		this.setViewBoundScale(viewBoundScale);
    }-*/;

    /**
     * Specifies the format of the query string that is appended to the Link's
     * href before the file is fetched.(If the href specifies a local file, this
     * element is ignored.)
     * 
     * @return
     */
    public final native String getViewFormat() /*-{
		return this.getViewFormat();
    }-*/;

    /**
     * Specifies the format of the query string that is appended to the Link's
     * href before the file is fetched.(If the href specifies a local file, this
     * element is ignored.)
     * 
     * @param viewFormat
     */
    public final native void setViewFormat(String viewFormat) /*-{
		this.setViewFormat(viewFormat);
    }-*/;

}
