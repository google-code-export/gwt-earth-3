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
package com.nitrous.gwt.earth.client.api.event;

import com.nitrous.gwt.earth.client.api.KmlMouseEvent;

/**
 * The interface that is implemented by a listener that wishes to be notified of
 * mouse interactions with Google Earth.
 * 
 * @author nick
 * 
 */
public interface MouseMoveListener {
	/**
	 * Triggers an event when the user moves the mouse pointer over a location
	 * in Google Earth.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onMouseOver(KmlMouseEvent event);

	/**
	 * Triggers an event when the user moves the mouse off of the object in
	 * Google Earth.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onMouseOut(KmlMouseEvent event);

	/**
	 * Triggers an event when the user moves the mouse inside Google Earth.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onMouseMove(KmlMouseEvent event);
}
