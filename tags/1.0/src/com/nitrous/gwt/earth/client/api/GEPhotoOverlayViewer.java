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
 * This interface enables programmatic and user-driven interaction with photo
 * overlays in the Google Earth Plugin.
 * 
 * Note: This interface is still under development.
 * 
 * @see KmlPhotoOverlay
 * @author Nick
 * 
 */
public class GEPhotoOverlayViewer extends JavaScriptObject {
    protected GEPhotoOverlayViewer() {
    }

    /**
     * Enters the given photo overlay object, exiting any other currently active
     * photo overlay. If the argument is null, then any currently active photo
     * overlay is exited and normal global navigation is enabled.
     * 
     * @param photoOverlay
     */
    public final native void setPhotoOverlay(KmlPhotoOverlay photoOverlay) /*-{
		this.setPhotoOverlay(photoOverlay);
    }-*/;

}
