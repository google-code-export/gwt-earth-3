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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This interface contains result information obtained by calling GEView's
 * hitTest method.
 * 
 * @see GEView.hitTest
 * @author Nick
 * 
 */
public class GEHitTestResult extends JavaScriptObject {
    protected GEHitTestResult() {
    }

    /**
     * 
     * @return Latitude of sampled point.
     */
    public final native double getLatitude() /*-{
		return this.getLatitude();
    }-*/;

    /**
     * 
     * @param latitude
     *            Latitude of sampled point.
     */
    public final native void setLatitude(double latitude) /*-{
		this.setLatitude(latitude);
    }-*/;

    /**
     * 
     * @return Longitude of sampled point.
     */
    public final native double getLongitude() /*-{
		return this.getLongitude();
    }-*/;

    /**
     * 
     * @param longitude
     *            Longitude of sampled point.
     */
    public final native void setLongitude(double longitude) /*-{
		this.setLongitude(longitude);
    }-*/;

    /**
     * 
     * @return Altitude of sampled point.
     */
    public final native double getAltitude() /*-{
		return this.getAltitude();
    }-*/;

    /**
     * 
     * @param altitude
     *            Altitude of sampled point.
     */
    public final native void setAltitude(double altitude) /*-{
		this.setAltitude(altitude);
    }-*/;

}
