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
 * Represents a mouse input event.
 * @author Nick
 *
 */
public class KmlMouseEvent extends KmlEvent {
	public static final int LEFT = 0;
	public static final int MIDDLE = 1;
	public static final int RIGHT = 2;

	protected KmlMouseEvent() {
	}

	/**
	 * @return The button on the mouse was pressed. Possible values include 0,
	 *         1, 2, where 0 is left, 1 is middle, and 2 is right mouse key.
	 * see {@link #LEFT}, {@link #MIDDLE} and {@link #RIGHT}
	 */
	public final native int getButton() /*-{
		return this.getButton();
	}-*/;

	/**
	 * @return The x coordinate at which the event occurred, measured in pixels
	 *         from the left edge of the plug-in window.
	 */
	public final native int getClientX() /*-{
		return this.getClientX();
	}-*/;

	/**
	 * @return The y coordinate at which the event occurred, measured in pixels
	 *         from the top edge of the plug-in window.
	 */
	public final native int getClientY() /*-{
		return this.getClientY();
	}-*/;

	/**
	 * @return The x coordinate at which the event occurred, measured in pixels
	 *         from the left edge of the computer screen.
	 */
	public final native int getScreenX() /*-{
		return this.getScreenX();
	}-*/;

	/**
	 * @return The y coordinate at which the event occurred, measured in pixels
	 *         from the top edge of the computer screen.
	 */
	public final native int getScreenY() /*-{
		return this.getScreenY();
	}-*/;

	/**
	 * 
	 * @return The latitude at which the event occurred, in decimal degrees.
	 */
	public final native float getLatitude() /*-{
		return this.getLatitude();
	}-*/;

	/**
	 * @return The latitude at which the event occurred, in decimal degrees.
	 */
	public final native float getLongitude() /*-{
		return this.getLongitude();
	}-*/;

	/**
	 * @return The altitude at which the event occurred, in meters.
	 */
	public final native float getAltitude() /*-{
		return this.getAltitude();
	}-*/;

	/**
	 * 
	 * @return Indicates whether a mouse action occurred while on the Google
	 *         Earth globe.
	 */
	public final native boolean getDidHitGlobe() /*-{
		return this.getDidHitGlobe() == true;
	}-*/;

	/**
	 * 
	 * @return Indicates whether the ALT key was held down when an event
	 *         occurred.
	 */
	public final native boolean getAltKey() /*-{
		return this.getAltKey() == true;
	}-*/;

	/**
	 * 
	 * @return Indicates whether the CTRL key was held down when an event
	 *         occurred.
	 */
	public final native boolean getCtrlKey() /*-{
		return this.getCtrlKey() == true;
	}-*/;

	/**
	 * 
	 * @return Indicates whether the SHIFT key was held down when an event
	 *         occurred.
	 */
	public final native boolean getShiftKey() /*-{
		return this.getShiftKey() == true;
	}-*/;

	/**
	 * 
	 * @return Used with the mouseover and mouseout events to specify a
	 *         secondary target. For mouseover, it specifies the object that the
	 *         mouse was over. For mouseout, it specifies the new object that
	 *         the mouse is over.
	 */
	public final native GEEventEmitter getRelatedTarget() /*-{
		return this.getRelatedTarget();
	}-*/;
}
