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
 * The KmlCoordArray object defines an array of coordinates.
 * @author Nick
 *
 */
public class KmlCoordArray extends KmlObjectPartial { 
    protected KmlCoordArray() {
    }

    /**
     * 
     * @param index
     * @return Returns the coordinates at the given index.
     */
    public final native KmlCoord get(int index)/*-{
		return this.get(index);
    }-*/;

    /**
     * Sets the coordinates at the given index..
     * 
     * @param index
     * @param coord
     */
    public final native void set(int index, KmlCoord coord)/*-{
		this.set(index, coord);
    }-*/;

    /**
     * Sets the latitude, longitude, and altitude.
     * 
     * @param index
     * @param latitude
     * @param longitude
     * @param altitude
     */
    public final native void setLatLngAlt(int index, double latitude, double longitude, double altitude)/*-{
		this.setLatLngAlt(index, latitude, longitude, altitude);
    }-*/;

    /**
     * Appends one or more new elements to the end of an array and returns the
     * new length of the array.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @return the new length of the array.
     */
    public final native int pushLatLngAlt(double latitude, double longitude, double altitude)/*-{
		return this.pushLatLngAlt(latitude, longitude, altitude);
    }-*/;

    /**
     * Appends one or more new elements to the end of an array and returns the new length of the array.
     * @param coordOrList
     * @return the new length of the array.
     */
    public final native int push(KmlObjectBase coordOrList)/*-{
		return this.push(coordOrList);
    }-*/;

    /**
     * Deletes the last element of an array, decrements the array length, and returns the value that is removed.
     * @return the value that is removed.
     */
    public final native KmlCoord pop()/*-{
        return this.pop();
    }-*/;

    /**
     * Adds an element or elements to the beginning of an array.
     * @param coordOrList
     * @return
     */
    public final native int unshift(KmlObjectBase coordOrList)/*-{
        return this.unshift(coordOrList);
    }-*/;

    /**
     * Adds an element or elements to the beginning of an array.
     * @param latitude
     * @param longitude
     * @param altitude
     * @return
     */
    public final native int unshiftLatLngAlt(double latitude, double longitude, double altitude)/*-{
        return this.unshiftLatLngAlt(latitude, longitude, altitude);
    }-*/;

    /**
     * Removes and returns the first element of the array.
     * @return the first element of the array.
     */
    public final native KmlCoord shift()/*-{
        return this.shift();
    }-*/;

    /**
     * Reverses the order of the elements in the array.
     */
    public final native void reverse()/*-{
        this.reverse();
    }-*/;

    /**
     * Clears all of the elements in the array
     */
    public final native void clear()/*-{
        this.clear();
    }-*/;

    /**
     * Specifies the length of the index array.
     * @return the length of the index array.
     */
    public final native int getLength()/*-{
        return this.getLength();
    }-*/;

}
