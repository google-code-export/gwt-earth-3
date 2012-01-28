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
import com.google.gwt.event.shared.HandlerRegistration;
import com.nitrous.gwt.earth.client.api.event.MouseClickListener;
import com.nitrous.gwt.earth.client.api.event.MouseListener;
import com.nitrous.gwt.earth.client.api.event.MouseMoveListener;

/**
 * Defines when and how an event gets passed in and triggered from the Google
 * Earth Plug-in.
 * 
 * @author Nick
 * 
 */
public class GEEventEmitter extends GESchemaObject {
	protected GEEventEmitter() {
	}

	/**
	 * Register a listener that will be notified of mouse interactions with
	 * Google Earth.
	 * 
	 * @param listener The listener to register
	 * @return The HandlerRegistration that can be used to unregister the listener
	 */
	public final HandlerRegistration addMouseClickListener(MouseClickListener listener) {
		JavaScriptObject clickListener = doAddClickListener(listener);
		JavaScriptObject dblClickListener = doAddDoubleClickListener(listener);
		JavaScriptObject mouseDownListener = doAddMouseDownListener(listener);
		JavaScriptObject mouseUpListener = doAddMouseUpListener(listener);
		HandlerRegistration reg = new GEHandlerRegistration(this, 
				new JavaScriptObject[]{
					clickListener, 
					dblClickListener, 
					mouseDownListener, 
					mouseUpListener
				}, 
				new String[]{
					"click",
					"dblclick",
					"mousedown",
					"mouseup"
				});
		return reg;
	}
	/**
	 * Register a listener that will be notified of mouse interactions with
	 * Google Earth.
	 * 
	 * @param listener The listener to register
	 * @return The HandlerRegistration that can be used to unregister the listener
	 */
	public final HandlerRegistration addMouseListener(MouseListener listener) {
		JavaScriptObject clickListener = doAddClickListener(listener);
		JavaScriptObject dblClickListener = doAddDoubleClickListener(listener);
		JavaScriptObject mouseOverListener = doAddMouseOverListener(listener);
		JavaScriptObject mouseDownListener = doAddMouseDownListener(listener);
		JavaScriptObject mouseUpListener = doAddMouseUpListener(listener);
		JavaScriptObject mouseOutListener = doAddMouseOutListener(listener);
		JavaScriptObject mouseMoveListener = doAddMouseMoveListener(listener);	
		HandlerRegistration reg = new GEHandlerRegistration(this, 
				new JavaScriptObject[]{
					clickListener, 
					dblClickListener, 
					mouseOverListener, 
					mouseDownListener, 
					mouseUpListener, 
					mouseOutListener,
					mouseMoveListener
				}, 
				new String[]{
					"click",
					"dblclick",
					"mouseover",
					"mousedown",
					"mouseup",
					"mouseout",
					"mousemove"
				});
		return reg;
	}
	private final native JavaScriptObject doAddClickListener(MouseClickListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseClickListener::onClick(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'click', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddDoubleClickListener(MouseClickListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseClickListener::onDoubleClick(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'dblclick', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddMouseDownListener(MouseClickListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseClickListener::onMouseDown(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'mousedown', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddMouseUpListener(MouseClickListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseClickListener::onMouseUp(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'mouseup', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddMouseOverListener(MouseMoveListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseMoveListener::onMouseOver(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'mouseover', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddMouseOutListener(MouseMoveListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseMoveListener::onMouseOut(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'mouseout', jsListener);
		return jsListener; 
	}-*/;
	private final native JavaScriptObject doAddMouseMoveListener(MouseMoveListener listener) /*-{
		var jsListener = function(event) {
			listener.@com.nitrous.gwt.earth.client.api.event.MouseMoveListener::onMouseMove(Lcom/nitrous/gwt/earth/client/api/KmlMouseEvent;)(event);
	    };
		$wnd.google.earth.addEventListener(this, 'mousemove', jsListener);
		return jsListener; 
	}-*/;

}
