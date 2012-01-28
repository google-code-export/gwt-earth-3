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
 * This interface provides access to the visibility controls of the GE Tour Control.
 * 
 * @see KmlTourPlayer
 * 
 * @Since Google Earth JavaScript API Version v1.009
 * @author Nick
 * 
 */
public class GETourPlayerControl extends JavaScriptObject {
    protected GETourPlayerControl() {
    }

    /**
     * @return True if the tour player control is visible, otherwise false
     */
    public final boolean isVisible() {
    	int visibility = getVisibilityNative();
    	return visibility != 0;
    }

    /**
     * Hide or show the tour player control
     * 
     * @param visibile True to show the control, false to hide it.
     */
    public final void setVisibile(boolean visible) {
        setVisibilityNative(visible ? 1 : 0);
    }
    
    /**
     * @return Whether the tour player control is visible or not.
     */
    private final native int getVisibilityNative() /*-{
        return this.getVisibility();
    }-*/;

    /**
     * Specifies whether the tour player control is visible or not.
     * 
     * @param visibility 1 to show, 0 to hide.
     */
    private final native void setVisibilityNative(int visibility) /*-{
        this.setVisibility(visibility);
    }-*/;
    
}
