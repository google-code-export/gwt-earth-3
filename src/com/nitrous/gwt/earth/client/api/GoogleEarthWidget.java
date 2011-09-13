/*
 * Copyright 2008 Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 * Author: Samuel Charron
 * 
 * This file was copied from the gwt-earth project and modified to support Google Earth JavaScript API v3.0  
 */
package com.nitrous.gwt.earth.client.api;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * The widget that allows you to add the Google Earth view to a GWT user interface
 * @author Nick
 *
 */
public class GoogleEarthWidget extends Composite {
	private static int id = 0;
	private GEPlugin gePlugin;
	private ArrayList<GEPluginReadyListener> pluginReadyListeners = new ArrayList<GEPluginReadyListener>();

	private String containerId;
	
	/**
	 * Constructor
	 */
	public GoogleEarthWidget() {
		containerId = "map3d"+id;
		HTML container = new HTML(
				"<div class='map3dcontainer' id='map3dcontainer" + id + "'>" + 
				"<div class='map3d' id='" + containerId + "'></div></div>");
		initWidget(container);
	}
		
	/**
	 * Begin loading the Google Earth Plugin
	 */
	public void init() {
        GoogleEarth.createInstance(containerId, new GoogleEarthInitListener() {
            @Override
            public void onSuccess(GEPlugin plugin) {
                onInitSuccess(plugin);
            }
            
            @Override
            public void onFailure(String cause) {
                onInitFailure(cause);
            }
        });
	}
	
	/**
	 * Initialize the Google Earth Plugin with the additional specified parameters
	 * @param jsonInitParams The JSON encoded initialization parameters to be passed to the google earth plugin
	 * <pre>
	 * Supported parameters are: 
	 * <li>database - The URL of an alternative Earth Enterprise database to connect to instead of the default database.
	 * Note: Certain changes may be required to your Google Earth Server configuration before Google Earth Plugin instances will be able to connect to it using this method. Google Earth Server versions 3.2 and higher are already pre-configured for connectivity with the plugin. Contact Google Earth Enterprise support for more details.
	 * Note: Keep in mind the Google Earth API Terms of Service while using this parameter.</li>
	 * 
	 * <li>language - The language code specifying the language to use for road and border labels, 
	 * Terms of Use text, and error messages.
	 * Supported language codes are listed in the Google Maps API Coverage document.</li>
	 * </pre> 
	 */
	public void init(String jsonInitParams) {
		if (jsonInitParams != null && jsonInitParams.trim().length() > 0) {
			JSONValue json = JSONParser.parseLenient(jsonInitParams);
			init(json);
		} else {
			init();
		}
	}
	
	/**
	 * Initialize the Google Earth Plugin with the additional specified parameters
	 * @param initParams The initialization parameters to be passed to the google earth plugin.
	 * <pre>
	 * Supported parameters are: 
	 * <li>database - The URL of an alternative Earth Enterprise database to connect to instead of the default database.
	 * Note: Certain changes may be required to your Google Earth Server configuration before Google Earth Plugin instances will be able to connect to it using this method. Google Earth Server versions 3.2 and higher are already pre-configured for connectivity with the plugin. Contact Google Earth Enterprise support for more details.
	 * Note: Keep in mind the Google Earth API Terms of Service while using this parameter.</li>
	 * 
	 * <li>language - The language code specifying the language to use for road and border labels, 
	 * Terms of Use text, and error messages.
	 * Supported language codes are listed in the Google Maps API Coverage document.</li>
	 * </pre> 
	 */
	public void init(Map<String, String> initParams) {
        GoogleEarth.createInstance(containerId, initParams, new GoogleEarthInitListener() {
            @Override
            public void onSuccess(GEPlugin plugin) {
                onInitSuccess(plugin);
            }
            
            @Override
            public void onFailure(String cause) {
                onInitFailure(cause);
            }
        });
	}
	
	/**
	 * Initialize the Google Earth Plugin with the additional specified parameters
	 * @param jsonInitParams The JSON encoded initialization parameters to be passed to the google earth plugin
	 * <pre>
	 * Supported parameters are: 
	 * <li>database - The URL of an alternative Earth Enterprise database to connect to instead of the default database.
	 * Note: Certain changes may be required to your Google Earth Server configuration before Google Earth Plugin instances will be able to connect to it using this method. Google Earth Server versions 3.2 and higher are already pre-configured for connectivity with the plugin. Contact Google Earth Enterprise support for more details.
	 * Note: Keep in mind the Google Earth API Terms of Service while using this parameter.</li>
	 * 
	 * <li>language - The language code specifying the language to use for road and border labels, 
	 * Terms of Use text, and error messages.
	 * Supported language codes are listed in the Google Maps API Coverage document.</li>
	 * </pre> 
	 */
	public void init(JSONValue jsonInitParams) {
		String str = jsonInitParams.toString();
		JavaScriptObject json = toJson(str);
        GoogleEarth.createInstance(containerId, json, new GoogleEarthInitListener() {
            @Override
            public void onSuccess(GEPlugin plugin) {
                onInitSuccess(plugin);
            }
            
            @Override
            public void onFailure(String cause) {
                onInitFailure(cause);
            }
        });
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
	 * Notify registered listeners that the plug-in is ready
	 * @param ge The reference to the plug-in that is ready.
	 */
	private void onInitSuccess(GEPlugin ge) {
		this.gePlugin = ge;
		this.gePlugin.bindConstants();
		id++;
		GEPluginReadyListener[] arr = pluginReadyListeners.toArray(new GEPluginReadyListener[pluginReadyListeners.size()]); 
		for (GEPluginReadyListener listener : arr) {
			listener.pluginReady(ge);
		}
	}
	
	/**
	 * Notify registered listeners that the plug-in failed to initialize
	 */
	private void onInitFailure(String err) {
		GWT.log("Failed to initialize Google Earth Plugin: "+err);
		GEPluginReadyListener[] arr = pluginReadyListeners.toArray(new GEPluginReadyListener[pluginReadyListeners.size()]); 
		for (GEPluginReadyListener listener : arr) {
			listener.pluginInitFailure();
		}
	}
	
	public void addPluginReadyListener(GEPluginReadyListener listener) {
		pluginReadyListeners.add(listener);
	}
	
	public GEPlugin getGEPlugin() {
		return gePlugin;
	}
}
