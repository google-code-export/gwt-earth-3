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

/**
 * Listen for events that are fired when the view is changing in Earth. 
 * Register this event listener to the <code>GEView</code>.
 * 
 * @author nick
 * 
 */
public interface ViewChangeListener {
	/**
	 * Event fired when the view begins changing in Earth. This event will be
	 * fired once, followed by successive onViewChange() events, and ending with a
	 * onViewChangeEnd() event.
	 */
	void onViewChangeBegin();

	/**
	 * Event fired while the view is changing in Earth. This event will be
	 * triggered in rapid succession while the camera is in motion.
	 */
	void onViewChange();
	
	/**
	 * Event fired when the view stops changing in Earth. 
	 */
	void onViewChangeEnd();
	
}
