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
 * The object corresponding to the retangular region in which Google Earth is
 * displayed.
 * 
 * @author Nick
 * 
 */
public class GEWindow extends GEEventEmitter {
	protected GEWindow() {
	}

	/**
	 * Gives the Google Earth object focus.
	 */
	public final native void focus() /*-{
		this.focus();
	}-*/;

	/**
	 * Removes focus from the Google Earth object.
	 */
	public final native void blur() /*-{
		this.blur();
	}-*/;

	/**
	 * Determine whether Google Earth is visible inside the browser.
	 * @return True if Google Earth is visible inside the browser 
	 */
	public final native boolean getVisibility() /*-{
		return this.getVisibility() == true;
	}-*/;

	/**
	 * Toggles the overall visibility of Google Earth inside the browser.
	 * @param visibility True if Google Earth is visible inside the browser 
	 */
	public final native void setVisibility(boolean visibility) /*-{
		this.setVisibility(visibility);
	}-*/;

}
