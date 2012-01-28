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
package com.nitrous.gwt.earth.client.api;

/**
 * Represents an extent in time bounded by begin and end dateTimes.
 * @author Nick
 *
 */
public class KmlTimeSpan extends KmlTimePrimitive {
	protected KmlTimeSpan() {
	}

	/**
	 * 
	 * @return Describes the beginning instant of a time period. If absent, the
	 *         beginning of the period is unbounded.
	 */
	public final native KmlDateTime getBegin() /*-{
		return this.getBegin();
	}-*/;

	/**
	 * 
	 * @return Describes the ending instant of a time period. If absent, the end
	 *         of the period is unbounded.
	 */
	public final native KmlDateTime getEnd() /*-{
		return this.getEnd();
	}-*/;

}
