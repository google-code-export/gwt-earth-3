package com.nitrous.gwt.earth.client.api;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An implementation of HandlerRegistration that is responsible for removing 
 * installed event listeners from a specified host
 */
class GEHandlerRegistration implements HandlerRegistration {
	private JavaScriptObject[] jsHandler;
	private String[] eventType;
	private JavaScriptObject host;
	
	/**
	 * Constructor
	 * @param host The host to which the listener is registered
	 * @param jsHandler The handler function to be removed
	 * @param eventType The event type to be unregistered
	 */
	GEHandlerRegistration(JavaScriptObject host, JavaScriptObject jsHandler, String eventType) {
		this.jsHandler = new JavaScriptObject[]{jsHandler};
		this.eventType = new String[]{eventType};
		this.host = host;
	}
	
	/**
	 * Constructor
	 * @param host The host to which the listener is registered
	 * @param jsHandler The handler functions to be removed
	 * @param eventType The event types to be unregistered
	 */
	GEHandlerRegistration(JavaScriptObject host, JavaScriptObject[] jsHandler, String[] eventType) {
		this.jsHandler = jsHandler;
		this.host = host;
		this.eventType = eventType;
	}
	
	@Override
	public void removeHandler() {
		if (host == null || eventType == null || jsHandler == null) {
			return;
		}
		for (int i = 0 ; i < jsHandler.length; i++) {
			removeHandlerNative(host, eventType[i], jsHandler[i]);
		}
		this.host = null;
		this.jsHandler = null;
		this.eventType = null;
	}

	/**
	 * Unregister the specified handler from the specified host for the specified event type
	 * @param host The host to which the specified handler was registered
	 * @param eventType The event type to be un-subscribed
	 * @param handler The registered handler to be removed
	 */
	private native final void removeHandlerNative(JavaScriptObject host, String eventType, JavaScriptObject handler) /*-{
		$wnd.google.earth.removeEventListener(host, eventType, handler);			
	}-*/;		
}

