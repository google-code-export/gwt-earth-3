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
 * References a KML file or KMZ archive on a remote network. Use the Link
 * property to specify the location of the KML file. Within that property, you
 * can define the refresh options for updating the file, based on time and
 * camera change. NetworkLinks can be used in combination with Regions to handle
 * very large datasets efficiently.
 * 
 * @author Nick
 * 
 */
public class KmlNetworkLink extends KmlFeature {
    protected KmlNetworkLink() {
    }

    /**
     * Sets the link, refreshVisibility, and flyToView for the network link.
     * 
     * @param link
     * @param refreshVisibility
     * @param flyToView
     */
    public final native void set(KmlLink link, boolean refreshVisibility, boolean flyToView) /*-{
		this.set(link, refreshVisibility, flyToView);
    }-*/;

    /**
     * Specifies the location of any of the following:
     * 
     * <pre>
     * <li>KML files fetched by network links</li>
     * <li>Image files used by icons in icon styles, ground overlays, and screen overlays</li>
     * <li>Model files used in the Model object</li>
     * </pre>
     * 
     * @return
     */
    public final native KmlLink getLink() /*-{
		return this.getLink();
    }-*/;

    /**
     * Specifies the location of any of the following:
     * 
     * <pre>
     * <li>KML files fetched by network links</li>
     * <li>Image files used by icons in icon styles, ground overlays, and screen overlays</li>
     * <li>Model files used in the Model object</li>
     * </pre>
     * 
     * @param link
     */
    public final native void setLink(KmlLink link) /*-{
		this.setLink(link);
    }-*/;

    /**
     * A value of 0 leaves the visibility of features within the control of the
     * Google Earth user. Set the value to 1 to reset the visibility of features
     * each time the NetworkLink is refreshed. For example, suppose a Placemark
     * within the linked KML file has visibility set to 1 and the NetworkLink
     * has refreshVisibility set to 1. When the file is first loaded into Google
     * Earth, the user can clear the check box next to the item to turn off
     * display in the 3D viewer. However, when the NetworkLink is refreshed, the
     * Placemark will be made visible again, since its original visibility state
     * was TRUE.
     * 
     * @return
     */
    public final native boolean getRefreshVisibility() /*-{
		return this.getRefreshVisibility() == true;
    }-*/;

    /**
     * @param refreshVisibility
     *            A value of 0 leaves the visibility of features within the
     *            control of the Google Earth user. Set the value to 1 to reset
     *            the visibility of features each time the NetworkLink is
     *            refreshed. For example, suppose a Placemark within the linked
     *            KML file has visibility set to 1 and the NetworkLink has
     *            refreshVisibility set to 1. When the file is first loaded into
     *            Google Earth, the user can clear the check box next to the
     *            item to turn off display in the 3D viewer. However, when the
     *            NetworkLink is refreshed, the Placemark will be made visible
     *            again, since its original visibility state was TRUE.
     */
    public final native void setRefreshVisibility(boolean refreshVisibility) /*-{
		this.setRefreshVisibility(refreshVisibility);
    }-*/;

    /**
     * A value of 1 causes Google Earth to fly to the view of the LookAt or
     * Camera in the NetworkLinkControl (if it exists).
     * 
     * @return
     */
    public final native boolean getFlyToView() /*-{
		return this.getFlyToView() == true;
    }-*/;

    /**
     * 
     * @param flyToView
     *            A value of 1 causes Google Earth to fly to the view of the
     *            LookAt or Camera in the NetworkLinkControl (if it exists).
     */
    public final native void setFlyToView(boolean flyToView) /*-{
		this.setFlyToView(flyToView);
    }-*/;
}
