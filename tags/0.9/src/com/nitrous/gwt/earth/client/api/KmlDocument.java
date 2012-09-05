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
 * A KmlDocument has containers that holds features and styles. This container
 * is required if you use shared styles. It is recommended that you use shared
 * styles, which require the following.
 * 
 * <pre>
 * <li>Define all Styles in a Document. Assign a unique ID to each Style. </li> 
 * <li>Within a given feature or StyleMap, reference the Style's ID using a styleUrl element. </li>
 * </pre>
 * 
 * <b>Note:</b> Shared styles are not inherited by the features in the Document.
 * 
 * @author Nick
 * 
 */
public class KmlDocument extends KmlContainer {
    protected KmlDocument() {
    }

    /**
     * 
     * @param styleUrl
     * @return Returns a list of elements using a particular style URL.
     */
    public final native KmlObjectList getElementsByStyleUrl(String styleUrl) /*-{
		return this.getElementsByStyleUrl(styleUrl);
    }-*/;

    /**
     * 
     * @return Returns an array containing the style selectors present in the
     *         KML document.
     */
    public final native GEStyleSelectorContainer getStyleSelectors() /*-{
		return this.getStyleSelectors();
    }-*/;

}
