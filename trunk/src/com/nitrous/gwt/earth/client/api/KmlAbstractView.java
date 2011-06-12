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
 * This is an abstract class and cannot be created directly. This class is
 * extended by KmlCamera and KmlLookAt.
 * 
 * @author Nick
 * 
 */
public class KmlAbstractView extends KmlObject {
	protected KmlAbstractView() {
	}

	/**
	 * Creates a new KmlLookAt object that matches as closely as possible this
	 * KmlAbstractView. KmlLookAt is unable to represent roll, so roll values in
	 * the current view will not be passed to the new KmlLookAt object. If this
	 * view is already a KmlLookAt, this function returns a new KmlLookAt
	 * representing the same view.
	 * 
	 * @return Creates a new KmlLookAt object that matches as closely as
	 *         possible this KmlAbstractView. KmlLookAt is unable to represent
	 *         roll, so roll values in the current view will not be passed to
	 *         the new KmlLookAt object. If this view is already a KmlLookAt,
	 *         this function returns a new KmlLookAt representing the same view.
	 */
	public final native KmlLookAt copyAsLookAt() /*-{
		return this.copyAsLookAt();
	}-*/;

	/**
	 * Creates a new KmlCamera object that matches this KmlAbstractView. If this
	 * view is already a KmlCamera, this function returns a new KmlCamera
	 * representing the same view.
	 * 
	 * @return Creates a new KmlCamera object that matches this KmlAbstractView.
	 *         If this view is already a KmlCamera, this function returns a new
	 *         KmlCamera representing the same view.
	 */
	public final native KmlCamera copyAsCamera() /*-{
		return this.copyAsCamera();
	}-*/;

	/**
	 * 
	 * @return Returns the KmlTimeStamp or KmlTimeSpan object associated with
	 *         this view.
	 */
	public final native KmlTimePrimitive getTimePrimitive() /*-{
		return this.getTimePrimitive();
	}-*/;

	/**
	 * Associate a KmlTimeStamp or KmlTimeSpan object with this view.
	 * 
	 * @param timePrimitive
	 */
	public final native void setTimePrimitive(KmlTimePrimitive timePrimitive) /*-{
		this.setTimePrimitive(timePrimitive);
	}-*/;

	/**
	 * Returns the viewer options on the current view.
	 * 
	 * @return
	 * @see GEPlugin.OPTION_STREET_VIEW
	 * @see GEPlugin.OPTION_SUNLIGHT
	 * @see GEPlugin.OPTION_HISTORICAL_IMAGERY
	 */
	public final native KmlViewerOptions getViewerOptions() /*-{
		return this.getViewerOptions();
	}-*/;

	/**
	 * Sets the viewer options on the current view.
	 * 
	 * @param viewerOptions
	 * @see GEPlugin.OPTION_STREET_VIEW
	 * @see GEPlugin.OPTION_SUNLIGHT
	 * @see GEPlugin.OPTION_HISTORICAL_IMAGERY
	 */
	public final native void setViewerOptions(KmlViewerOptions viewerOptions) /*-{
		this.setViewerOptions(viewerOptions);
	}-*/;

}
