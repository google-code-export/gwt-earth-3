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
 * A balloon that contains HTML.
 * 
 * @author Nick
 * 
 */
public class GEHtmlBalloon extends GEAbstractBalloon {
	protected GEHtmlBalloon() {
	}

	/**
	 * @return The color of the text in the balloon. This must be set using the
	 *         HTML hex format #RRGGBB. If not set, it is interpreted as
	 *         #000000.
	 */
	public final native String getForegroundColor() /*-{
		return this.getForegroundColor();
	}-*/;

	/**
	 * 
	 * @param foregroundColor
	 *            The color of the text in the balloon. This must be set using
	 *            the HTML hex format #RRGGBB. If not set, it is interpreted as
	 *            #000000.
	 */
	public final native void setForegroundColor(String foregroundColor) /*-{
		this.setForegroundColor(foregroundColor);
	}-*/;

	/**
	 * 
	 * @return The background color of the balloon. This must be set using the
	 *         HTML hex format #RRGGBB. If not set, the default is interpreted
	 *         as #FFFFFF.
	 */
	public final native String getBackgroundColor() /*-{
		return this.getBackgroundColor();
	}-*/;

	/**
	 * @param backgroundColor
	 *            The background color of the balloon. This must be set using
	 *            the HTML hex format #RRGGBB. If not set, the default is
	 *            interpreted as #FFFFFF.
	 */
	public final native void setBackgroundColor(String backgroundColor) /*-{
		this.setBackgroundColor(backgroundColor);
	}-*/;

}
