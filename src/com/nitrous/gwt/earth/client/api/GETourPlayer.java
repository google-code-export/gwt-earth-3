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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This interface enables programmatic and user-driven interaction with KML
 * tours in the Google Earth Plugin. Note: This interface is still under
 * development.
 * 
 * @see KmlTour
 * 
 * @author Nick
 * 
 */
public class GETourPlayer extends JavaScriptObject {
    protected GETourPlayer() {
    }

    /**
     * Returns the GETourPlayerControl
     * @return The GETourPlayerControl
     */
    public final native GETourPlayerControl getControl() /*-{
        return this.getControl();
    }-*/;
    
    /**
     * Enters the given tour object, exiting any other currently active tour.
     * This method does not automatically begin playing the tour. If the
     * argument is null, then any currently active tour is exited and normal
     * globe navigation is enabled.
     * 
     * @param tour
     */
    public final native void setTour(KmlTour tour) /*-{
        this.setTour(tour);
    }-*/;

    /**
     * Plays the currently active tour.
     */
    public final native void play() /*-{
        this.play();
    }-*/;

    /**
     * Pauses the currently active tour.
     */
    public final native void pause() /*-{
        this.pause();
    }-*/;

    /**
     * Resets the currently active tour, stopping playback and rewinding to the
     * start of the tour.
     */
    public final native void reset() /*-{
        this.reset();
    }-*/;

    /**
     * The current elapsed playing time of the active tour, in seconds.
     * 
     * @return
     */
    public final native float getCurrentTime() /*-{
        return this.getCurrentTime();
    }-*/;

    /**
     * The current elapsed playing time of the active tour, in seconds.
     * 
     * @param currentTime
     */
    public final native void setCurrentTime(float currentTime) /*-{
        this.setCurrentTime(currentTime);
    }-*/;

    /**
     * The total duration of the active tour, in seconds. If no tour is loaded,
     * the behavior of this method is undefined.
     * 
     * @return
     */
    public final native float getDuration() /*-{
        return this.getDuration();
    }-*/;

    /**
     * The current speed of the active tour.
     * 
     * @return The current speed of the active tour.
     * @since Google Earth JavaScript API Version v1.009
     */
    public final native float getCurrentSpeed() /*-{
        return this.getCurrentSpeed();
    }-*/;

    /**
     * The current speed of the active tour.
     * 
     * @param speed The speed to set for the active tour
     * @since Google Earth JavaScript API Version v1.009
     */
    public final native void setCurrentSpeed(float speed) /*-{
        this.setCurrentSpeed(speed);
    }-*/;
    
    /**
     * Specify whether the active tour should loop.
     * 
     * @param loop True to loop the active tour
     * @since Google Earth JavaScript API Version v1.009
     */
    public final native void setLoop(boolean loop) /*-{
        this.setLoop(loop);
    }-*/;
    
    /**
     * Returns whether the active tour is configured to loop.
     * 
     * @return loop True if the active tour is currently configured to loop.
     * @since Google Earth JavaScript API Version v1.009
     */
    public final native boolean getLoop() /*-{
        return this.getLoop();
    }-*/;
}
