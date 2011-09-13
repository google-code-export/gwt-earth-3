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
import com.google.gwt.user.client.ui.Widget;

public class GEHtmlDivBalloon extends GEHtmlBalloon {
	protected GEHtmlDivBalloon() {
	}

	public final void setContent(Widget w) {
		String html = w.toString();
		setContentDiv(html);
	}

	/**
	 * Set the contents of the balloon. When the balloon is shown, the HTML Div
	 * Element is attached to the balloon element in the web page. You can
	 * manipulate this balloon using ordinary HTML DOM techniques.
	 * 
	 * @param html
	 *            The HTML to be inserted into a DIV
	 */
	public final native void setContentDiv(String html) /*-{
		var div = $doc.createElement('DIV');
		div.innerHTML = html;
		this.setContentDiv(div);
	}-*/;

	/**
	 * The google API returns an 'ISupports' but this class has no documentation so returning a JavaScriptObject for now.
	 * Maybe we can return  com.google.gwt.dom.client.DivElement?
	 * 
	 * @return An HTMLDivElement to be used as the contents of the balloon. When
	 *         the balloon is shown, the HTMLDivElement is attached to the
	 *         balloon element in the web page. You can manipulate this balloon
	 *         using ordinary HTML DOM techniques.
	 */
	public final native JavaScriptObject getContentDiv() /*-{
		return this.getContentDiv();
	}-*/;
}