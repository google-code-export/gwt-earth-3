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
 * Defines a closed line string, typically the outer boundary of a Polygon.
 * Optionally, a LinearRing can also be used as the inner boundary of a Polygon
 * to create holes in the Polygon. A Polygon can contain multiple LinearRing
 * elements used as inner boundaries. You do not need to connect the first and
 * last points.
 * 
 * @author Nick
 * 
 */
public class KmlLinearRing extends KmlLineString {
    protected KmlLinearRing() {
    }
}
