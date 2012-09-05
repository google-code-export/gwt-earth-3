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
 * An abstract object and cannot be used directly. A KmlContainer object holds
 * one or more features and allows the creation of nested hierarchies.
 * 
 * @author Nick
 * 
 */
public class KmlContainer extends KmlFeature {
	protected KmlContainer() {
	}

	/**
	 * Get an element by ID. This is functionally equivalent to getElementByUrl
	 * with an unspecified base URL. For example, getElementByUrl('#foo'). Usage
	 * is when finding objects created with JavaScript, which have unspecified
	 * base URLs. The object must be a descendant of the container before it can
	 * be found.
	 * 
	 * @param id
	 * @return
	 */
	public final native KmlObject getElementById(String id) /*-{
		return this.getElementById(id);
	}-*/;

	/**
	 * Get an element by URL. A URL consists of the base address and ID, joined
	 * with the # character. For example:
	 * http://www.google.com/bar.kml#here_be_monsters This applies to objects
	 * that are fetched. In the case of plugin created objects, the URL is
	 * simply #foo. The object must be a descendant of the container before it
	 * can be found.
	 * 
	 * @param url
	 * @return
	 */
	public final native KmlObject getElementByUrl(String url) /*-{
		return this.getElementByUrl(url);
	}-*/;

	/**
	 * Get an element by type.
	 * 
	 * @param type
	 * @return
	 */
	public final native KmlObjectList getElementsByType(String type) /*-{
		return this.getElementsByType(type);
	}-*/;

	/**
	 * A collection of features, such as name, description, and so on.
	 * 
	 * @return
	 */
	public final native GEFeatureContainer getFeatures() /*-{
		return this.getFeatures();
	}-*/;
}
