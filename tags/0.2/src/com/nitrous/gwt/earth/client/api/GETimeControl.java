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
 * Represents the time slider object.
 * 
 * @author Nick
 * 
 */
public class GETimeControl extends GEControl {
    protected GETimeControl() {
    }

    /**
     * 
     * @return Whether the time slider is visible or not.
     */
    public final GEVisibility getVisibility() {
        return GEPluginConstants.get().toGEVisibility(getVisibilityNative());
    }

    /**
     * 
     * @return Whether the time slider is visible or not.
     */
    private final native int getVisibilityNative() /*-{
        return this.getVisibility();
    }-*/;

    /**
     * Specifies whether the control is visible or hidden.
     * 
     * @param visibility
     */
    private final native void setVisibilityNative(int visibility) /*-{
        this.setVisibility(visibility);
    }-*/;
    
    /**
     * Specifies whether the control is visible or hidden.
     * 
     * @param visibility
     */
    public final void setVisibility(GEVisibility visibility) {
        setVisibilityNative(GEPluginConstants.get().toJavaScript(visibility));
    }

    /**
     * @return Returns the clock rate that the plugin would use, if the play
     *         button on the time slider UI was pressed. This rate is calculated
     *         by the plugin based on the time range currently present in the
     *         slider.
     */
    public final native double getCalculatedRate() /*-{
		return this.getCalculatedRate();
    }-*/;

    /**
     * @return Returns a KmlTimeSpan object encompassing the earliest and latest
     *         times present in the time slider. For more information, refer to
     *         the Time chapter of the Developer's Guide.
     */
    public final native KmlTimeSpan getExtents() /*-{
		return this.getExtents();
    }-*/;

    /**
     * @return Returns an array containing the KmlTimeStamp objects associated
     *         with the historical imagery available in this view.
     */
    public final native KmlObjectList getAvailableImageDates() /*-{
		return this.getAvailableImageDates();
    }-*/;
}
