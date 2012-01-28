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
 * The GESun class controls the dawn to dusk views.
 * 
 * @author Nick
 * 
 */
public class GESun extends JavaScriptObject {
    protected GESun() {
    }

    /**
     * Specifies whether the feature is drawn in the 3D viewer when it is
     * initially loaded. In order for a feature to be visible, the visibility
     * property and all of its ancestors must also be set to 1.
     * 
     * @return
     */
    public final native boolean getVisibility() /*-{
		return this.getVisibility() == true;
    }-*/;

    /**
     * Specifies whether the feature is drawn in the 3D viewer when it is
     * initially loaded. In order for a feature to be visible, the visibility
     * property and all of its ancestors must also be set to 1.
     * 
     * @param visibility
     */
    public final native void setVisibility(boolean visibility) /*-{
		this.setVisibility(visibility);
    }-*/;

}
