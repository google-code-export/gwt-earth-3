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

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.nitrous.gwt.earth.client.api.ajaxloader.AjaxLoader;
import com.nitrous.gwt.earth.client.api.event.KmlEventListener;
import com.nitrous.gwt.earth.client.api.event.KmlLoadCallback;

/**
 * An overlay for the google.earth Namespace
 * http://code.google.com/apis/earth/documentation/reference/google_earth_namespace.html
 * 
 * @author Nick
 *
 */
public final class GoogleEarth {    
    private GoogleEarth() {
    }
    
	/**
	 * Load the Google earth API.
	 * 
	 * This method can be used as an alternative to placing the following script
	 * in the application host page:
	 * 
	 * <pre>
	 *      &lt;!-- To generate a key for a real deployment, visit http://code.google.com/apis/maps/signup.html --&gt;
	 *      &lt;script src="http://www.google.com/jsapi?key=YOUR_KEY_HERE"&gt&lt;/script&gt
	 *      &lt;script type="text/javascript"&gt
	 *         google.load("earth", "1");
	 *      &lt;/script&gt
	 * </pre>
	 * 
	 * @param key
	 *            The Google Earth API key to be used. To generate a key visit <a href="http://code.google.com/apis/maps/signup.html">http://code.google.com/apis/maps/signup.html</a>
	 */
    public static void loadApi(final String key, final Runnable callback) {
    	AjaxLoader.init(key);    	
    	Runnable cb = new Runnable(){
    		public void run() {
		    	Scheduler.get().scheduleDeferred(new ScheduledCommand(){
					@Override
					public void execute() {
						if (callback != null) {
							callback.run();
						}
					}
				});
    		}
    	};
    	AjaxLoader.loadApi("earth", "1", cb, null); 
    }
    
    /**
     * @return Whether or not the Google Earth Browser Plug-in and API are supported on the current browser and operating system. 
     */
    public static native boolean isSupported() /*-{
        return $wnd.google.earth.isSupported() == true;
    }-*/;
    
    /**
     * Whether or not the Google Earth Browser Plug-in is currently installed on
     * the user's machine.
     * 
     * Note: if the plug-in is not installed, the user will be presented with a
     * 'download' link upon calls to google.earth.createInstance().
     * 
     * @return Whether or not the Google Earth Browser Plug-in is currently installed on
     * the user's machine.
     */
    public static native boolean isInstalled() /*-{
        return $wnd.google.earth.isInstalled() == true;
    }-*/;
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailure method of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param elementId
     *            The ID of the DOM element (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static void createInstance(String elementId, GoogleEarthInitListener callback) {
        createInstance(elementId, (JavaScriptObject)null, callback);
    }
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailure method of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param elementId
     *            The ID of the DOM element (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param options
     *            (optional) A Map containing extra initialization options. The
     *            following options are recognized:
     * 
     *            <pre>
     *            <b>database</b>
     * 
     *            The URL of an alternative Earth Enterprise database to connect
     *            to instead of the default database.
     * 
     *            Note: Certain changes may be required to your Google Earth
     *            Server configuration before Google Earth Plugin instances will
     *            be able to connect to it using this method. Google Earth
     *            Server versions 3.2 and higher are already pre-configured for
     *            connectivity with the plugin. Contact Google Earth Enterprise
     *            support for more details.
     * 
     *            Note: Keep in mind the <a href="http://code.google.com/apis/maps/terms.html">Google Earth API Terms of Service</a> while
     *            using this parameter. 
     *            
     *            <b>language</b>
     * 
     *            The language code specifying the language to use for road and
     *            border labels, Terms of Use text, and error messages.
     *            Supported language codes are listed in the Google Maps API
     *            Coverage document. Note that we often update the languages
     *            that we support, so this list may not be exhaustive.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static void createInstance(String elementId, Map<String, String> options, GoogleEarthInitListener callback) {
        createInstance(elementId, toJson(options), callback);
    }
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailuremethod of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param domNode
     *            The DOM element itself (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param options
     *            (optional) A Map containing extra initialization options. The
     *            following options are recognized:
     * 
     *            <pre>
     *            <b>database</b>
     * 
     *            The URL of an alternative Earth Enterprise database to connect
     *            to instead of the default database.
     * 
     *            Note: Certain changes may be required to your Google Earth
     *            Server configuration before Google Earth Plugin instances will
     *            be able to connect to it using this method. Google Earth
     *            Server versions 3.2 and higher are already pre-configured for
     *            connectivity with the plugin. Contact Google Earth Enterprise
     *            support for more details.
     * 
     *            Note: Keep in mind the <a href="http://code.google.com/apis/maps/terms.html">Google Earth API Terms of Service</a> while
     *            using this parameter. 
     *            
     *            <b>language</b>
     * 
     *            The language code specifying the language to use for road and
     *            border labels, Terms of Use text, and error messages.
     *            Supported language codes are listed in the Google Maps API
     *            Coverage document. Note that we often update the languages
     *            that we support, so this list may not be exhaustive.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static void createInstance(Element domNode, Map<String, String> options, GoogleEarthInitListener callback) {
        createInstance(domNode, toJson(options), callback);
    }
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailuremethod of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param domNode
     *            The DOM element itself (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static void createInstance(Element domNode, GoogleEarthInitListener callback) {
        createInstance(domNode, (JavaScriptObject)null, callback);
    }
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailuremethod of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param elementId
     *            The ID of the DOM element (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param options
     *            (optional) A JavaScript object literal (i.e, <code>{ option1:
     *            'value1', option2: 'value2' }</code>) containing extra initialization
     *            options. The following options are recognized:
     * 
     *            <pre>
     *            <b>database</b>
     * 
     *            The URL of an alternative Earth Enterprise database to connect
     *            to instead of the default database.
     * 
     *            Note: Certain changes may be required to your <a href="http://www.google.com/enterprise/earthmaps/earth_technical.html">Google Earth Server</a> 
     *            configuration before Google Earth Plugin instances will
     *            be able to connect to it using this method. Google Earth
     *            Server versions 3.2 and higher are already pre-configured for
     *            connectivity with the plugin. Contact Google Earth Enterprise
     *            support for more details.
     * 
     *            Note: Keep in mind the <a href="http://code.google.com/apis/maps/terms.html">Google Earth API Terms of Service</a> while
     *            using this parameter. 
     *            
     *            <b>language</b>
     * 
     *            The language code specifying the language to use for road and
     *            border labels, Terms of Use text, and error messages.
     *            Supported language codes are listed in the Google Maps API
     *            Coverage document. Note that we often update the languages
     *            that we support, so this list may not be exhaustive.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static native void createInstance(String elementId, JavaScriptObject options, GoogleEarthInitListener callback) /*-{
        function initCB(ge) {
            callback.@com.nitrous.gwt.earth.client.api.GoogleEarthInitListener::onSuccess(Lcom/nitrous/gwt/earth/client/api/GEPlugin;)(ge);
        }
        function failureCB(err) {
            callback.@com.nitrous.gwt.earth.client.api.GoogleEarthInitListener::onFailure(Ljava/lang/String;)("Failed to initialize the plugin");
        }
        $wnd.google.earth.createInstance(elementId, initCB, failureCB, options);
    }-*/;
    
    /**
     * Attempts to create an instance of the plugin under the given web browser
     * HTML DOM node. Upon success, calls the onSuccess method of the specified
     * callback. Upon failure, calls the onFailuremethod of the specified
     * callback and displays an error message to the user in place of the
     * plug-in object.
     * 
     * Note: The HTML DOM must be loaded before this method can be called.
     * 
     * @param domNode
     *            The DOM element itself (usually a <div>) that will contain the
     *            plug-in instance or error message.
     * @param options
     *            (optional) A JavaScript object literal (i.e, <code>{ option1:
     *            'value1', option2: 'value2' }</code>) containing extra initialization
     *            options. The following options are recognized:
     * 
     *            <pre>
     *            <b>database</b>
     * 
     *            The URL of an alternative Earth Enterprise database to connect
     *            to instead of the default database.
     * 
     *            Note: Certain changes may be required to your <a href="http://www.google.com/enterprise/earthmaps/earth_technical.html">Google Earth Server</a> 
     *            configuration before Google Earth Plugin instances will
     *            be able to connect to it using this method. Google Earth
     *            Server versions 3.2 and higher are already pre-configured for
     *            connectivity with the plugin. Contact Google Earth Enterprise
     *            support for more details.
     * 
     *            Note: Keep in mind the <a href="http://code.google.com/apis/maps/terms.html">Google Earth API Terms of Service</a> while
     *            using this parameter. 
     *            
     *            <b>language</b>
     * 
     *            The language code specifying the language to use for road and
     *            border labels, Terms of Use text, and error messages.
     *            Supported language codes are listed in the Google Maps API
     *            Coverage document. Note that we often update the languages
     *            that we support, so this list may not be exhaustive.
     * @param callback
     *            The callback to be notified with the success or failure of the
     *            GEPlugin
     */
    public static native void createInstance(Element domNode, JavaScriptObject options, GoogleEarthInitListener callback) /*-{
        function initCB(ge) {
            callback.@com.nitrous.gwt.earth.client.api.GoogleEarthInitListener::onSuccess(Lcom/nitrous/gwt/earth/client/api/GEPlugin;)(ge);
        }
        function failureCB(err) {
            callback.@com.nitrous.gwt.earth.client.api.GoogleEarthInitListener::onFailure(Ljava/lang/String;)("Failed to initialize the plugin");
        }
        $wnd.google.earth.createInstance(domNode, initCB, failureCB, options);
    }-*/;
    
    /**
     * Convert the specified Map to a JSON array
     * @param options The options to convert
     * @return The JSON array holding the contents of the specified map
     */
    private static JavaScriptObject toJson(Map<String, String> options) {
        JavaScriptObject json = null;
        if (options != null && options.size() > 0) {
            JSONObject obj = new JSONObject();
            for (Map.Entry<String, String> param : options.entrySet()) {
                String value = param.getValue();
                if (value != null && value.trim().length() > 0) {
                    obj.put(param.getKey(), new JSONString(param.getValue()));
                }
            }
            String str = obj.toString();
            json = toJson(str);
        }
        return json;
    }
    
    /**
     * Eval the specified string as JSON
     * @param str The string to eval
     * @return The JavaScriptObject reference to the evaluated JSON
     */
    private static native JavaScriptObject toJson(String str) /*-{ 
        var obj = eval('(' + str + ')');
        return obj;
    }-*/;

    
    /**
     * Attaches a listener to a given object for a specific event; when the event occurs on the object, the given callback is invoked.
     * @param targetObject  The object on which to listen to the event.  
     * @param eventId   The event string (i.e. 'click', 'mouseover', 'frameend', etc.) identifying the event to listen for. See the individual class reference pages for available events. 
     * @param listener  A listener that will be called with an instance of KmlEvent when the event occurs on the object.
     * @return the HandlerRegistration that can be used to unregister the listener 
     */
    public static HandlerRegistration addEventListener(JavaScriptObject targetObject, String eventId, KmlEventListener listener) {
        JavaScriptObject jsListener = doAddEventListener(targetObject, eventId, listener);
        HandlerRegistration reg = new GEHandlerRegistration(targetObject, jsListener, eventId);
        return reg;
    }
    
    /**
     * Attaches a listener to a given object for a specific event; when the event occurs on the object, the given callback is invoked.
     * @param targetObject  The object on which to listen to the event.  
     * @param eventId   The event string (i.e. 'click', 'mouseover', 'frameend', etc.) identifying the event to listen for. See the individual class reference pages for available events. 
     * @param listener  A listener that will be called with an instance of KmlEvent when the event occurs on the object. 
     * @return The JavaScript callback function that was registered 
     */
    private static native JavaScriptObject doAddEventListener(JavaScriptObject targetObject, String eventId, KmlEventListener listener) /*-{
        function callback(event) {
            listener.@com.nitrous.gwt.earth.client.api.event.KmlEventListener::onEvent(Lcom/nitrous/gwt/earth/client/api/KmlEvent;)(event);
        }
        $wnd.google.earth.addEventListener(targetObject, eventId, callback);
        return callback;
    }-*/;
    
    /**
     * Attaches a listener to a given object for a specific event; when the event occurs on the object, the given callback is invoked.
     * @param targetObject  The object on which to listen to the event.  
     * @param eventId   The event string (i.e. 'click', 'mouseover', 'frameend', etc.) identifying the event to listen for. See the individual class reference pages for available events. 
     * @param listener  A listener that will be called with an instance of KmlEvent when the event occurs on the object.
     * @param useCapture Whether or not this listener should initiate capture. For more details on event capture and bubbling, see the relevant <a href="http://www.w3.org/TR/DOM-Level-2-Events/events.html#Events-flow-capture">W3C DOM documentation</a>. 
     * @return the HandlerRegistration that can be used to unregister the listener 
     */
    public static HandlerRegistration addEventListener(JavaScriptObject targetObject, String eventId, KmlEventListener listener, boolean useCapture) {
        JavaScriptObject jsListener = doAddEventListener(targetObject, eventId, listener, useCapture);
        HandlerRegistration reg = new GEHandlerRegistration(targetObject, jsListener, eventId);
        return reg;
    }
    
    /**
     * Attaches a listener to a given object for a specific event; when the event occurs on the object, the given callback is invoked.
     * @param targetObject  The object on which to listen to the event.  
     * @param eventId   The event string (i.e. 'click', 'mouseover', 'frameend', etc.) identifying the event to listen for. See the individual class reference pages for available events. 
     * @param listener  A listener that will be called with an instance of KmlEvent when the event occurs on the object. 
     * @param useCapture Whether or not this listener should initiate capture. For more details on event capture and bubbling, see the relevant <a href="http://www.w3.org/TR/DOM-Level-2-Events/events.html#Events-flow-capture">W3C DOM documentation</a>.
     * @return The JavaScript callback function that was registered 
     */
    private static native JavaScriptObject doAddEventListener(JavaScriptObject targetObject, String eventId, KmlEventListener listener, boolean useCapture) /*-{
        function callback(event) {
            listener.@com.nitrous.gwt.earth.client.api.event.KmlEventListener::onEvent(Lcom/nitrous/gwt/earth/client/api/KmlEvent;)(event);
        }
        $wnd.google.earth.addEventListener(targetObject, eventId, callback, useCapture);
        return callback;
    }-*/;
    
    /**
     * Unregister the specified handler from the specified host for the specified event type
     * @param host The host to which the specified handler was registered
     * @param eventId The event type to be un-subscribed
     * @param handler The registered handler to be removed
     */
    public static native void removeHandlerNative(JavaScriptObject host, String eventId, JavaScriptObject handler) /*-{
        $wnd.google.earth.removeEventListener(host, eventId, handler);            
    }-*/;       

    
    /**
     * Retrieves and parses a KML or KMZ file at the given URL and returns an
     * instance of a KmlFeature-derived class representing the parsed KML object
     * model.
     * 
     * Note: This method does not display the feature on the Earth. See below
     * for more information.
     * 
     * @param plugin
     *            The instance of GEPlugin that will perform the URL fetching
     *            and parsing.
     * @param url
     *            The URL at which the KML or KMZ content is posted. This URL
     *            should serve either the <a href="http://code.google.com/apis/kml/documentation/kml_tut.html#kml_server">KML or KMZ content type</a>.
     * @param callback
     *            A callback that will be called with an instance of a
     *            KmlFeature-derived class upon
     *            successful fetching/parsing of the KML or KMZ content. If an
     *            error occurs, this function will be passed a null value.
     * 
     *            Note: In this callback, you can display the loaded KML in
     *            Earth by calling ge.getFeatures().appendChild(feature),
     *            assuming 'ge' is the GEPlugin instance variable in the
     *            callback function's scope and 'feature' is object received by the callback.
     */
    public static native void fetchKml(GEPlugin plugin, String url, KmlLoadCallback callback) /*-{   
        function cb(feature) {
            callback.@com.nitrous.gwt.earth.client.api.event.KmlLoadCallback::onLoaded(Lcom/nitrous/gwt/earth/client/api/KmlObject;)(feature);
        }
        $wnd.google.earth.fetchKml(plugin, url, cb);
    }-*/;
       
    /**
     * Efficiently executes an arbitrary, user-defined function (the batch
     * function), minimizing the amount of overhead incurred during
     * cross-process communication between the web browser and Google Earth
     * Plugin. This method is useful for batching together a large set of calls
     * to the Earth API, for example, a large number of consecutive calls to
     * KmlCoordArray.pushLatLngAlt.
     * 
     * Note: This method is guaranteed to run synchronously; that is,
     * executeBatch blocks and does not return until the batch function has
     * completed. In fact there should be no difference between calling
     * executeBatch(fn) and fn() besides execution performance.
     * 
     * @param plugin The instance of GEPlugin that batched API calls will be executed on. 
     * @param batchFunction The function containing the code to be executed. 
     */
    public static native void executeBatch(GEPlugin plugin, BatchFunction batchFunction) /*-{
        function jsBatchFunction() {
            batchFunction.@com.nitrous.gwt.earth.client.api.BatchFunction::run(Lcom/nitrous/gwt/earth/client/api/GEPlugin;)(plugin);
        }
        $wnd.google.earth.executeBatch(plugin, jsBatchFunction);
    }-*/;
    
    /**
     * Sets the language to be used for new instances of the plugin. Needs to be
     * called before google.earth.createInstance(). Affects road and border
     * labels, the error message displayed when the plugin fails to load, as
     * well as the language of the Terms of Use page linked from the plugin.
     * 
     * @param languageCode
     *            Supported language codes are listed in the <a href="https://spreadsheets.google.com/spreadsheet/pub?key=0Ah0xU81penP1cDlwZHdzYWkyaERNc0xrWHNvTTA1S1E&gid=1">Google Maps API Coverage document</a>. 
     *            Note that we often update the languages that we support, so this list may not be exhaustive.
     */
    public static native void setLanguage(String languageCode) /*-{
        $wnd.google.earth.setLanguage(languageCode);
    }-*/;
}
