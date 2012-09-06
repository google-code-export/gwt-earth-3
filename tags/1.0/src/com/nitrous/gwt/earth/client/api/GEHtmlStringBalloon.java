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

/**
 * The GEHtmlStringBalloon class represents a balloon based on the contentString.
 * @author Nick
 *
 */
public class GEHtmlStringBalloon extends GEHtmlBalloon {
	protected GEHtmlStringBalloon() {
	}

	/**
	 * You can include any HTML using the contentString property. When the
	 * balloon is visible, the content specified in contentString property, is
	 * inserted directly into the balloon element in the web page.
	 * 
	 * @return When the balloon is visible, the content specified in
	 *         contentString property, is inserted directly into the balloon
	 *         element in the web page.
	 */
	public final native String getContentString() /*-{
		return this.getContentString();
	}-*/;

	/**
	 * You can include any HTML using the contentString property. When the
	 * balloon is visible, the content specified in contentString property, is
	 * inserted directly into the balloon element in the web page.
	 * 
	 * @param contentString
	 *            You can include any HTML using the contentString property.
	 *            When the balloon is visible, the content specified in
	 *            contentString property, is inserted directly into the balloon
	 *            element in the web page.
	 */
	public final native void setContentString(String contentString) /*-{
		this.setContentString(contentString);
	}-*/;

}
