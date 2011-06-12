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
 * Defines when and how an event gets passed in and triggered from the Google
 * Earth Plug-in.
 * 
 * @author Nick
 * 
 */
public class GEEventEmitter extends GESchemaObject {
	protected GEEventEmitter() {
	}

	/**
	 * Triggers an event when the user clicks a location in Google Earth with
	 * the mouse.
	 * 
	 * @param event The KmlMouseEvent
	 */
	public final native void click(KmlMouseEvent event) /*-{
		this.click(event);
	}-*/;

	/**
	 * Triggers an event when the user double clicks a location in Google Earth with the mouse.
	 * @param event The KmlMouseEvent
	 */
	public final native void dblclick(KmlMouseEvent event) /*-{
		this.dblclick(event);
	}-*/;

	/**
	 * Triggers an event when the user moves the mouse pointer over a location in Google Earth.
	 * @param event The KmlMouseEvent
	 */
	public final native void mouseover(KmlMouseEvent event) /*-{
		this.mouseover(event);
	}-*/;

	/**
	 * Triggers an event when the user presses the mouse button over a location in Google Earth.
	 * @param event The KmlMouseEvent
	 */
	public final native void mousedown(KmlMouseEvent event) /*-{
		this.mousedown(event);
	}-*/;

	/**
	 * Triggers an event when the user releases the mouse button over a location in Google Earth.
	 * @param event The KmlMouseEvent
	 */
	public final native void mouseup(KmlMouseEvent event) /*-{
		this.mouseup(event);
	}-*/;

	/**
	 * Triggers an event when the user moves the mouse off of the object in Google Earth.
	 * @param event The KmlMouseEvent
	 */
	public final native void mouseout(KmlMouseEvent event) /*-{
		this.mouseout(event);
	}-*/;

	/**
	 * Triggers an event when the user moves the mouse inside Google Earth.
	 * @param event The KmlMouseEvent
	 */
	public final native void mousemove(KmlMouseEvent event) /*-{
		this.mousemove(event);
	}-*/;
}
