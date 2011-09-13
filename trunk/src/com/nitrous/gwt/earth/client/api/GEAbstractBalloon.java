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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The base class for the three types of balloon windows that can overlay the 3D
 * window.
 * 
 * @author Nick
 * 
 */
public class GEAbstractBalloon extends JavaScriptObject {
	protected GEAbstractBalloon() {
	}

	/**
	 * @return The type of this object (e.g. 'GEHtmlStringBalloon').
	 */
	public final native String getType() /*-{
		return this.getType();
	}-*/;
	
	/**
	 * 
	 * @return The ID of the balloon.
	 */
	public final native String getId() /*-{
		return this.getId();
	}-*/;

	/**
	 * 
	 * @param id
	 *            The ID of the balloon.
	 */
	public final native void setId(String id) /*-{
		this.setId(id);
	}-*/;

	/**
	 * Determines what the balloon is attached to.
	 * 
	 * @return The KmlFeature that the balloon is attached to.
	 */
	public final native KmlFeature getFeature() /*-{
		return this.getFeature();
	}-*/;

	/**
	 * Determines what the balloon is attached to.
	 * 
	 * @param feature
	 */
	public final native void setFeature(KmlFeature feature) /*-{
		this.setFeature(feature);
	}-*/;

	/**
	 * 
	 * @return Minimum width of the balloon.
	 */
	public final native int getMinWidth() /*-{
		return this.getMinWidth();
	}-*/;

	/**
	 * 
	 * @param minWidth
	 *            Minimum width of the balloon.
	 */
	public final native void setMinWidth(int minWidth) /*-{
		this.setMinWidth(minWidth);
	}-*/;

	/**
	 * 
	 * @return Minimum height of the balloon.
	 */
	public final native int getMinHeight() /*-{
		return this.getMinHeight();
	}-*/;

	/**
	 * 
	 * @param minHeight
	 *            Minimum height of the balloon.
	 */
	public final native void setMinHeight(int minHeight) /*-{
		this.setMinHeight(minHeight);
	}-*/;

	/**
	 * 
	 * @return Maximum width of the balloon.
	 */
	public final native int getMaxWidth() /*-{
		return this.getMaxWidth();
	}-*/;

	/**
	 * 
	 * @param maxWidth
	 *            Maximum width of the balloon.
	 */
	public final native void setMaxWidth(int maxWidth) /*-{
		this.setMaxWidth(maxWidth);
	}-*/;

	/**
	 * 
	 * @return Maximum height of the balloon.
	 */
	public final native int getMaxHeight() /*-{
		return this.getMaxHeight();
	}-*/;

	/**
	 * Set the maximum height of the balloon.
	 * @param maxHeight
	 *            Maximum height of the balloon.
	 */
	public final native void setMaxHeight(int maxHeight) /*-{
		this.setMaxHeight(maxHeight);
	}-*/;

	/**
	 * When true, the balloon frame is displayed with a button that the user can
	 * click to close the balloon. When false, the balloon frame is just a plain
	 * frame. Default is true.
	 * 
	 * @return When true, the balloon frame is displayed with a button that the user can
	 * click to close the balloon. When false, the balloon frame is just a plain
	 * frame. Default is true.
	 */
	public final native boolean getCloseButtonEnabled() /*-{
		return this.getCloseButtonEnabled() == true;
	}-*/;

	/**
	 * When true, the balloon frame is displayed with a button that the user can
	 * click to close the balloon. When false, the balloon frame is just a plain
	 * frame. Default is true.
	 * 
	 * @param closeButtonEnabled  When true, the balloon frame is displayed with a button that the user can
	 * click to close the balloon. When false, the balloon frame is just a plain
	 * frame. Default is true.
	 */
	public final native void setCloseButtonEnabled(boolean closeButtonEnabled) /*-{
		this.setCloseButtonEnabled(closeButtonEnabled);
	}-*/;

}
