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
 * The KmlFeature object is an abstract object and is the base for all feature
 * types (for example Placemarks, Overlays, and NetworkLinks).
 * 
 * @author Nick
 * 
 */
public class KmlFeature extends KmlObject {
	protected KmlFeature() {
	}

	/**
	 * Retrieves the contents of the feature's &lt;ExtendedData&gt; element. The
	 * retrieved contents are scrubbed to remove JavaScript; CSS; and iframe,
	 * embed, and object tags. It should be safe to insert the resulting HTML
	 * into your page without concern for malicious content embedded in the
	 * feature data; however any feature depending on CSS or Javascript will not
	 * work.
	 * 
	 * @return the contents of the feature's &lt;ExtendedData&gt; element. The
	 *         retrieved contents are scrubbed to remove JavaScript; CSS; and
	 *         iframe, embed, and object tags. It should be safe to insert the
	 *         resulting HTML into your page without concern for malicious
	 *         content embedded in the feature data; however any feature
	 *         depending on CSS or Javascript will not work.
	 */
	public final native String getBalloonHtml() /*-{
		return this.getBalloonHtml();
	}-*/;

	/**
	 * Retrieves the contents of the feature's &lt;ExtendedData&gt; element. The
	 * contents are not scrubbed. Use this method only if you trust the source
	 * of the feature data.
	 * 
	 * @return the contents of the feature's &lt;ExtendedData&gt; element. The
	 *         contents are not scrubbed. Use this method only if you trust the
	 *         source of the feature data.
	 */
	public final native String getBalloonHtmlUnsafe() /*-{
		return this.getBalloonHtmlUnsafe();
	}-*/;

	/**
	 * 
	 * @return User-defined text displayed in the plugin as the label for the
	 *         object (for example, for a Placemark).
	 */
	public final native String getName() /*-{
		return this.getName();
	}-*/;

	/**
	 * Set the User-defined text displayed in the plugin as the label for the
	 * object (for example, for a Placemark).
	 * 
	 * @param name
	 *            User-defined text displayed in the plugin as the label for the
	 *            object (for example, for a Placemark).
	 */
	public final native void setName(String name) /*-{
		this.setName(name);
	}-*/;

	/**
	 * @return True if the feature is drawn in the plugin. In order for a
	 *         feature to be visible, the visibility of all its ancestors must
	 *         also be set to true. In the Google Earth List View, each feature
	 *         has a checkbox that allows the user to control visibility of the
	 *         feature.
	 */
	public final native boolean getVisibility() /*-{
		return this.getVisibility() == true;
	}-*/;

	/**
	 * Specifies whether the feature is drawn in the plugin. In order for a
	 * feature to be visible, the visibility of all its ancestors must also be
	 * set to true. In the Google Earth List View, each feature has a checkbox
	 * that allows the user to control visibility of the feature.
	 * 
	 * @param visibility
	 */
	public final native void setVisibility(boolean visibility) /*-{
		this.setVisibility(visibility);
	}-*/;

	/**
	 * @return Default state of left panel.
	 */
	public final native boolean getOpen() /*-{
		return this.getOpen() == true;
	}-*/;

	/**
	 * 
	 * @param open
	 *            Default state of left panel.
	 */
	public final native void setOpen(boolean open) /*-{
		this.setOpen(open);
	}-*/;

	/**
	 * Specifies a value representing an unstructured address written as a
	 * standard street, city, state address, and/or as a postal code.
	 * 
	 * @return a value representing an unstructured address written as a
	 *         standard street, city, state address, and/or as a postal code.
	 */
	public final native String getAddress() /*-{
		return this.getAddress();
	}-*/;

	/**
	 * Specifies a value representing an unstructured address written as a
	 * standard street, city, state address, and/or as a postal code.
	 * 
	 * @param address
	 *            a value representing an unstructured address written as a
	 *            standard street, city, state address, and/or as a postal code.
	 */
	public final native void setAddress(String address) /*-{
		this.setAddress(address);
	}-*/;

	/**
	 * Specifies a short description of the feature.
	 * 
	 * @return a short description of the feature.
	 */
	public final native String getSnippet() /*-{
		return this.getSnippet();
	}-*/;

	/**
	 * Specifies a short description of the feature.
	 * 
	 * @param snippet
	 *            a short description of the feature.
	 */
	public final native void setSnippet(String snippet) /*-{
		this.setSnippet(snippet);
	}-*/;

	/**
	 * @return User-supplied text that appears in the description balloon.
	 */
	public final native String getDescription() /*-{
		return this.getDescription();
	}-*/;

	/**
	 * Set User-supplied text that appears in the description balloon.
	 * 
	 * @param description
	 *            User-supplied text that appears in the description balloon.
	 */
	public final native void setDescription(String description) /*-{
		this.setDescription(description);
	}-*/;

	/**
	 * @return Stores either the lookAt or camera view.
	 */
	public final native KmlAbstractView getAbstractView() /*-{
		return this.getAbstractView();
	}-*/;

	/**
	 * Stores either the lookAt or camera view.
	 * 
	 * @param abstractView
	 */
	public final native void setAbstractView(KmlAbstractView abstractView) /*-{
		this.setAbstractView(abstractView);
	}-*/;

	/**
	 * URI of a Style or StyleMap defined in a Document. It refers to a Plug-in
	 * intitiated object.
	 * 
	 * @return URI of a Style or StyleMap defined in a Document. It refers to a
	 *         Plug-in intitiated object.
	 */
	public final native String getStyleUrl() /*-{
		return this.getStyleUrl();
	}-*/;

	/**
	 * URI of a Style or StyleMap defined in a Document. It refers to a Plug-in
	 * intitiated object.
	 * 
	 * @param styleUrl
	 *            URI of a Style or StyleMap defined in a Document. It refers to
	 *            a Plug-in intitiated object.
	 */
	public final native void setStyleUrl(String styleUrl) /*-{
		this.setStyleUrl(styleUrl);
	}-*/;

	/**
	 * @return The style based on the current mode of the Placemark.
	 */
	public final native KmlStyleSelector getStyleSelector() /*-{
		return this.getStyleSelector();
	}-*/;

	/**
	 * Set The style based on the current mode of the Placemark.
	 * 
	 * @param styleSelector
	 *            The style based on the current mode of the Placemark.
	 */
	public final native void setStyleSelector(KmlStyleSelector styleSelector) /*-{
		this.setStyleSelector(styleSelector);
	}-*/;

	/**
	 * Specifies region objects and their properties. A region contains a
	 * bounding box (LatLonAltBox) that describes an area of interest defined by
	 * geographic coordinates and altitudes.
	 * 
	 * @return Specifies region objects and their properties. A region contains
	 *         a bounding box (LatLonAltBox) that describes an area of interest
	 *         defined by geographic coordinates and altitudes.
	 */
	public final native KmlRegion getRegion() /*-{
		return this.getRegion();
	}-*/;

	/**
	 * Specifies region objects and their properties. A region contains a
	 * bounding box (LatLonAltBox) that describes an area of interest defined by
	 * geographic coordinates and altitudes.
	 * 
	 * @param region
	 */
	public final native void setRegion(KmlRegion region) /*-{
		this.setRegion(region);
	}-*/;

	/**
	 * Returns the KML for a feature.
	 * 
	 * @return Returns the KML for a feature.
	 */
	public final native String getKml() /*-{
		return this.getKml();
	}-*/;

	/**
	 * Returns previous sibling node within the container.
	 * 
	 * @return Returns previous sibling node within the container.
	 */
	public final native KmlFeature getPreviousSibling() /*-{
		return this.getPreviousSibling();
	}-*/;

	/**
	 * 
	 * @return Returns the next sibling node within the container.
	 */
	public final native KmlFeature getNextSibling() /*-{
		return this.getNextSibling();
	}-*/;

	/**
	 * Returns the KmlTimeStamp or KmlTimeSpan object associated with this
	 * feature.
	 * 
	 * @return Returns the KmlTimeStamp or KmlTimeSpan object associated with
	 *         this feature.
	 */
	public final native KmlTimePrimitive getTimePrimitive() /*-{
		return this.getTimePrimitive();
	}-*/;

	/**
	 * Attach a KmlTimeStamp or KmlTimeSpan object to this feature.
	 * 
	 * @param timePrimitive
	 *            Attach a KmlTimeStamp or KmlTimeSpan object to this feature.
	 */
	public final native void setTimePrimitive(KmlTimePrimitive timePrimitive) /*-{
		this.setTimePrimitive(timePrimitive);
	}-*/;

	/**
	 * 
	 * @return Returns the computed style of a feature, merging any inline
	 *         styles with styles imported from setHref() or a StyleUrl.
	 * 
	 *         Note: Modifying the returned KmlStyle object is undefined and not
	 *         recommended.
	 */
	public final native KmlStyle getComputedStyle() /*-{
		return this.getComputedStyle();
	}-*/;

	// Experimental Features

	/**
	 * This is an experimental feature and can change (or even be removed) at
	 * any time.
	 * 
	 * Retrieve the opacity of a feature, ranging from 0 (completely
	 * transparent) to 1 (complete opaque). The opacity of a folder or document
	 * will influence the opacity of child features. Thus, if a folder has an
	 * opacity of 0.5 and a child ground overlay in the folder also has an
	 * opacity of 0.5, the overlay will be drawn with an opacity of 0.25.
	 */
	public final native float getOpacity() /*-{
		return this.getOpacity();
	}-*/;

	/**
	 * this is an experimental feature and can change (or even be removed) at
	 * any time.
	 * 
	 * Set the opacity of a feature, ranging from 0 (completely transparent) to
	 * 1 (complete opaque). The opacity of a folder or document will influence
	 * the opacity of child features. Thus, if a folder has an opacity of 0.5
	 * and a child ground overlay in the folder also has an opacity of 0.5, the
	 * overlay will be drawn with an opacity of 0.25.
	 * 
	 * @param opacity
	 *            the opacity of a feature, ranging from 0 (completely
	 *            transparent) to 1 (complete opaque). The opacity of a folder
	 *            or document will influence the opacity of child features.
	 *            Thus, if a folder has an opacity of 0.5 and a child ground
	 *            overlay in the folder also has an opacity of 0.5, the overlay
	 *            will be drawn with an opacity of 0.25.
	 */
	public final native void setOpacity(float opacity) /*-{
		this.setOpacity(opacity);
	}-*/;

}
