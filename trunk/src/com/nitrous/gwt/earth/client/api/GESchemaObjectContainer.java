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


/**
 * Holds one or more KML objects.
 * 
 * @author Nick
 * 
 */
public class GESchemaObjectContainer extends GESchemaObject {
	protected GESchemaObjectContainer() {
	}

	/**
	 * Adds a node to the end of the list of children of a specified feature.
	 * Returns the appended object.
	 * 
	 * @param object
	 *            The object to add to the container.
	 * @return
	 */
	public final native KmlObject appendChild(KmlObject object) /*-{
		return this.appendChild(object);
	}-*/;

	/**
	 * Removes a node from the list of children of a specified object.
	 * 
	 * @param oldChild
	 *            child to be removed
	 * @return
	 */
	public final native KmlObject removeChild(KmlObject oldChild) /*-{
		return this.removeChild(oldChild);
	}-*/;

	/**
	 * Inserts a child before the referenced child in the list of objects.
	 * 
	 * @param newChild
	 *            new child to insert
	 * @param refChild
	 *            referenced child
	 * @return
	 */
	public final native KmlObject insertBefore(KmlObject newChild, KmlObject refChild) /*-{
		return this.insertBefore(newChild, refChild);
	}-*/;

	/**
	 * Replaces existing child in the list of features. Returns the old child.
	 * 
	 * @param newChild
	 * @param oldChild
	 * @return Returns the old child.
	 */
	public final native KmlObject replaceChild(KmlObject newChild, KmlObject oldChild) /*-{
		return this.replaceChild(newChild, refChild);
	}-*/;

	/**
	 * 
	 * @return Returns true if the container is not empty.
	 */
	public final native boolean hasChildNodes() /*-{
		return this.hasChildNodes();
	}-*/;

	/**
	 * 
	 * @return First child in the list of objects.
	 */
	public final native KmlObject getFirstChild() /*-{
		return this.getFirstChild();
	}-*/;

	/**
	 * 
	 * @return Last child in the list of objects.
	 */
	public final native KmlObject getLastChild() /*-{
		return this.getLastChild();
	}-*/;

	/**
	 * 
	 * @return List of features (for KmlContainer), or list of features, styles,
	 *         and schemas (for KmlDocument).
	 */
	public final native KmlObjectList getChildNodes() /*-{
		return this.getChildNodes();
	}-*/;

}
