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
 * The base class for all the other objects in the Google Earth Plug-in. The
 * methods and behavior of KMLObject are inherited by all other objects. This is
 * an abstract base class and cannot be used directly. It provides the id
 * attribute, which allows unique identification of an object.
 * 
 * @author Nick
 * 
 */
public class KmlObject extends GEEventEmitter {
	protected KmlObject() {
	}

	/**
	 * Test whether this object is the same as another object. Useful for Chrome
	 * and Safari, where the comparison a==b sometimes fails for plugin objects.
	 * 
	 * @param compareTo
	 * @return
	 */
	public final native boolean equals(KmlObject compareTo) /*-{
		return this.equals(compareTo) == true;
	}-*/;

	/**
	 * 
	 * @return The unique ID of the KML object.
	 */
	public final native String getId() /*-{
		return this.getId();
	}-*/;

	/**
	 * 
	 * @return The unique URL of the KML object. This is the base address joined
	 *         with the ID using the # character. For example:
	 *         http://www.google.com/bar.kml#atlantis
	 */
	public final native String getUrl() /*-{
		return this.getUrl();
	}-*/;

	/**
	 * 
	 * @return The parent node of the KML object.
	 */
	public final native KmlObject getParentNode() /*-{
		return this.getParentNode();
	}-*/;

	/**
	 * 
	 * @return The document that owns the KML object.
	 */
	public final native KmlDocument getOwnerDocument() /*-{
		return this.getOwnerDocument();
	}-*/;

	/**
	 * Permanently deletes an object, allowing its ID to be reused. Attempting
	 * to access the object once it is released will result in an error. 
	 * 
	 * Note:
	 * Calling this method is not required; JavaScript's garbage collector will
	 * automatically delete unused objects after some indeterminate amount of
	 * time.
	 */
	public final native void release() /*-{
		this.release();
	}-*/;

}
