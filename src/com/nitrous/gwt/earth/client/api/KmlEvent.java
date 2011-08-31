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
 * The event object used with all KMLObjects.
 * @author Nick
 *
 */
public class KmlEvent extends JavaScriptObject {
	protected KmlEvent() {
	}

	/**
	 * Cancels the default action of the event. For example, calling this method
	 * in a placemark click handler prevents the placemark's default balloon
	 * from popping up.
	 */
	public final native void preventDefault() /*-{
		this.preventDefault();
	}-*/;

	/**
	 * Prevents event propagation. For example, if click event handlers are set
	 * up on both the GEGlobe and GEWindow objects, and stopPropagation is
	 * called in the GEGlobe click event handler, the GEWindow event handler
	 * will not be triggered when the globe is clicked.
	 */
	public final native void stopPropagation() /*-{
		this.stopPropagation();
	}-*/;

	/**
	 * 
	 * @return The object to which the KMLEvent was originally dispatched.
	 */
	public final native KmlObject getTarget() /*-{
		return this.getTarget();
	}-*/;

	/**
	 * 
	 * @return The target whose event listeners are currently being processed.
	 */
	public final native GEEventEmitter getCurrentTarget() /*-{
		return this.getCurrentTarget();
	}-*/;

	public final GEEventPhase getEventPhase() {
	    return GEPluginConstants.get().toGEEventPhase(getEventPhaseNative());
	}
	/**
	 * 
	 * @return The current stage of the flow of events.
	 */
	private final native int getEventPhaseNative() /*-{
		return this.getEventPhase();
	}-*/;

	/**
	 * 
	 * @return Indicates whether or not an event is a bubbling event.
	 */
	public final native boolean getBubbles() /*-{
		return this.getBubbles();
	}-*/;

	/**
	 * 
	 * @return Indicates whether the event can be cancelled. 
	 * Note: Currently, cancelable has no effect.
	 */
	public final native boolean getCancelable() /*-{
		return this.getCancelable();
	}-*/;

}
