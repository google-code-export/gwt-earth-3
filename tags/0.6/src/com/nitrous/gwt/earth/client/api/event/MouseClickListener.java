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
package com.nitrous.gwt.earth.client.api.event;

import com.nitrous.gwt.earth.client.api.KmlMouseEvent;

/**
 * The interface that is implemented by a listener that wishes to be notified of
 * mouse interactions with Google Earth.
 * 
 * @author nick
 * 
 */
public interface MouseClickListener {
	/**
	 * Triggers an event when the user clicks a location in Google Earth with
	 * the mouse.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onClick(KmlMouseEvent event);

	/**
	 * Triggers an event when the user double clicks a location in Google Earth
	 * with the mouse.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onDoubleClick(KmlMouseEvent event);
	
	/**
	 * Triggers an event when the user presses the mouse button over a location
	 * in Google Earth.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onMouseDown(KmlMouseEvent event);

	/**
	 * Triggers an event when the user releases the mouse button over a location
	 * in Google Earth.
	 * 
	 * @param event
	 *            The KmlMouseEvent
	 */
	void onMouseUp(KmlMouseEvent event);
}
