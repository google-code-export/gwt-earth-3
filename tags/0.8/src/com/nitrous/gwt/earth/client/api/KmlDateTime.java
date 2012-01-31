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
 * Represents a specific point in time. The plugin accepts time in this format
 * only; it does not accept JavaScript date or time objects.
 * 
 * @author Nick
 * 
 */
public class KmlDateTime extends KmlObjectPartial {
	protected KmlDateTime() {
	}

	/**
	 * @return Returns the date and time in XML Schema time format.
	 */
	public final native String get() /*-{
		return this.get();
	}-*/;

	/**
	 * Set the date. Accepts only XML Schema time (see XML Schema Part 2:
	 * Datatypes Second Edition http://www.w3.org/TR/xmlschema-2/#isoformats).
	 * The value can be expressed as yyyy-mm-ddThh:mm:sszzzzzz, where T is the
	 * separator between the date and the time, and the time zone is either
	 * Z(for UTC) or zzzzzz, which represents +/-hh:mm in relation to UTC.
	 * Additionally, the value can be expressed as a date only.
	 * 
	 * @param date
	 *            the date. Accepts only XML Schema time (see XML Schema Part 2:
	 *            Datatypes Second Edition
	 *            http://www.w3.org/TR/xmlschema-2/#isoformats). The value can
	 *            be expressed as yyyy-mm-ddThh:mm:sszzzzzz, where T is the
	 *            separator between the date and the time, and the time zone is
	 *            either Z(for UTC) or zzzzzz, which represents +/-hh:mm in
	 *            relation to UTC. Additionally, the value can be expressed as a
	 *            date only.
	 */
	public final native void set(String date) /*-{
		this.set(date);
	}-*/;

}
