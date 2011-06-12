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
 * A container for zero or more geometry primitives associated with the same
 * feature.
 * 
 * @author Nick
 * 
 */
public class KmlMultiGeometry extends KmlGeometry {
    protected KmlMultiGeometry() {
    }

    /**
     * 
     * @return The collection of geometries that are children of this
     *         multi-geometry.
     */
    public final native GEGeometryContainer getGeometries()/*-{
		return this.getGeometries();
    }-*/;

}
