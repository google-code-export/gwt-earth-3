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
 * Used to manipulate the navigation controls in Google Earth.
 * 
 * @author Nick
 * 
 */
public class GENavigationControl extends JavaScriptObject {
	protected GENavigationControl() {
	}

	/**
	 * Whether the control is always visible, always hidden, or visible only
	 * when the user intends to use the control.
	 * @return Whether the control is always visible, always hidden, or visible only
	 * when the user intends to use the control.
	 */
	private final native int getVisibilityNative() /*-{
		return this.getVisibility();
	}-*/;

    /**
     * Whether the control is always visible, always hidden, or visible only
     * when the user intends to use the control.
     * @return Whether the control is always visible, always hidden, or visible only
     * when the user intends to use the control.
     */
    public final GEVisibility getVisibility() {
        return GEPluginConstants.get().toGEVisibility(getVisibilityNative());
    }
    
	/**
	 * Specifies whether the control is always visible, always hidden, or
	 * visible only when the user intends to use the control.
	 * @param visibility
	 */
	public final void setVisibility(GEVisibility visibility) {
	    setVisibilityNative(GEPluginConstants.get().toJavaScript(visibility));
	}

    /**
     * Specifies whether the control is always visible, always hidden, or
     * visible only when the user intends to use the control.
     * @param visibility
     */
    private final native void setVisibilityNative(int visibility) /*-{
        this.setVisibility(visibility);
    }-*/;
    
	/**
	 * 
	 * @return Returns the currently specified navigation control type.
	 */
	private final native int getControlTypeNative() /*-{
		return this.getControlType();
	}-*/;

    /**
     * @return Returns the currently specified navigation control type.
     */
	public final GENavigationControlType getControlType() {
	    return GEPluginConstants.get().toGENavigationControlType(getControlTypeNative());
	}
	
    /**
     * Specifies the size of the navigation control.
     * 
     * @param controlType
     */
    public final void setControlType(GENavigationControlType controlType) {
        setControlTypeNative(GEPluginConstants.get().toJavaScript(controlType));
    }
    
	/**
	 * Specifies the size of the navigation control.
	 * 
	 * @param controlType
	 */
	private final native void setControlTypeNative(int controlType) /*-{
		this.setControlType(controlType);
	}-*/;

	/**
	 * 
	 * @return The position of the navigation controls in Google Earth
	 */
	public final native KmlVec2 getScreenXY() /*-{
		return this.getScreenXY();
	}-*/;

	/**
	 * Enables or disables user-initiated entry to Street View imagery. When
	 * true, the Pegman icon is present in the navigation controls, allowing a
	 * user to drag the Pegman onto a street to initiate Street View. Users can
	 * also zoom down to ground level to enter Street View when this is set to
	 * true.
	 * 
	 * @param streetViewEnabled
	 */
	public final native void setStreetViewEnabled(boolean streetViewEnabled) /*-{
		this.setStreetViewEnabled(streetViewEnabled);
	}-*/;

	/**
	 * 
	 * @return Whether Street View is enabled in the navigation controls.
	 */
	public final native boolean getStreetViewEnabled() /*-{
		return this.getStreetViewEnabled() == true;
	}-*/;

}
