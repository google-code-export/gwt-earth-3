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
 * Controls time in the plugin.
 * 
 * @author Nick
 * 
 */
public class GETime extends JavaScriptObject {
    protected GETime() {
    }

    /**
     * Set the plugin's clock rate. A value of 1 corresponds with real time; to
     * pass one year in the plugin for every real second, set the rate to
     * 31536000 (60 times 60 times 24 times 365).
     * 
     * @param rate
     */
    public final native void setRate(double rate) /*-{
        this.setRate(rate);
    }-*/;

    /**
     * 
     * @return Get the current plugin clock rate.
     */
    public final native double getRate() /*-{
        return this.getRate();
    }-*/;

    /**
     * 
     * @return Returns the current computer clock time as a KmlTimeStamp object.
     */
    public final native KmlTimeStamp getSystemTime() /*-{
        return this.getSystemTime();
    }-*/;

    /**
     * 
     * @return Returns the GETimeControl object; this is the time slider.
     */
    public final native GETimeControl getControl() /*-{
        return this.getControl();
    }-*/;

    /**
     * 
     * @return Whether or not historical imagery is enabled.
     */
    public final native boolean getHistoricalImageryEnabled() /*-{
        return this.getHistoricalImageryEnabled();
    }-*/;

    /**
     * 
     * @param historicalImageryEnabled
     *            Turn historical imagery on or off. For more information, read
     *            the Time chapter of the Developer's Guide.
     */
    public final native void setHistoricalImageryEnabled(boolean historicalImageryEnabled) /*-{
        this.setHistoricalImageryEnabled(historicalImageryEnabled);
    }-*/;

    /**
     * 
     * @return Get the current plugin time as a KmlTimeStamp or KmlTimeSpan.
     */
    public final native KmlTimePrimitive getTimePrimitive() /*-{
        return this.getTimePrimitive();
    }-*/;

    /**
     * Sets the current plugin time.
     * @param timePrimitive 
     */
    public final native void setTimePrimitive(KmlTimePrimitive timePrimitive) /*-{
        this.setTimePrimitive(timePrimitive);
    }-*/;

}
