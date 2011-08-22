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
 * Specifies how the description balloon for placemarks is drawn.
 * 
 * @author Nick
 * 
 */
public class KmlBalloonStyle extends KmlObject {
    protected KmlBalloonStyle() {
    }

    /**
     * 
     * @return Background color of the balloon (optional).
     */
    public final native KmlColor getBgColor()/*-{
		return this.getBgColor();
    }-*/;

    /**
     * 
     * @return Foreground color for text. The default is black (ff000000).
     */
    public final native KmlColor getTextColor()/*-{
		return this.getTextColor();
    }-*/;

    /**
     * 
     * @return The text contained in the balloon.
     */
    public final native String getText()/*-{
		return this.getText();
    }-*/;

    /**
     * 
     * @param text
     *            The text contained in the balloon.
     */
    public final native void setText(String text)/*-{
		this.setText(text);
    }-*/;
}
