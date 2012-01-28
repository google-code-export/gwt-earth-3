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
 * A container that holds a collection of KmlStyle and KmlStyleMap objects. The
 * KmlStyleMap object selects a style based on the current mode of the
 * Placemark. An object derived from KmlStyleSelector is uniquely identified by
 * its ID and its URL.
 * 
 * @author Nick
 * 
 */
public class GEStyleSelectorContainer extends GESchemaObjectContainer {
	protected GEStyleSelectorContainer() {
	}
}
