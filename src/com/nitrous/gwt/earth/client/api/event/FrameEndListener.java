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

/**
 * Listen for events that are fired when Earth has finished rendering the
 * viewport. This listener will be called many times in succession when the
 * viewport is changing. Register this event listener to the
 * <code>GEPlugin</code> and make incremental changes to the viewport for smooth
 * animation.
 * 
 * @author nick
 * 
 */
public interface FrameEndListener {
	/**
	 * Called many times in succession when the
	 * viewport is changing. Make incremental changes to the viewport for
	 * smooth animation.
	 */
	void onFrameEnd();
}
