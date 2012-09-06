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
 * Represents a single moment in time. This is a simple element and contains no
 * children. Its value is a dateTime, specified in XML time. The precision of
 * the TimeStamp is dictated by the dateTime value in the when property.
 * 
 * @author Nick
 * 
 */
public class KmlTimeStamp extends KmlTimePrimitive {
	protected KmlTimeStamp() {
	}

	/**
	 * Represents a single moment in time. This is a simple element and contains
	 * no children. Its value is a dateTime, specified in XML time. The
	 * precision of the TimeStamp is dictated by the dateTime value in the when
	 * property. 
	 * 
	 * <pre>
	 * <li>dateTime gives second resolution</li>
	 * <li>date gives day resolution</li>
	 * <li>gYearMonth gives month resolution </li>
	 * <li>gYear gives year resolution</li>
	 * </pre>
	 * @return
	 */
	public final native KmlDateTime getWhen() /*-{
		return this.getWhen();
	}-*/;

}
