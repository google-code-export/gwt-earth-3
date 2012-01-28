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
 * This class controls the display of sunlight, historical imagery, and Street
 * View panoramas in the plugin. The KmlViewerOptions object is passed to
 * KmlAbstractView.setViewerOptions()
 * 
 * @author Nick
 * 
 */
public class KmlViewerOptions extends KmlObject {
	protected KmlViewerOptions() {
	}

	/**
	 * Set the state of viewer options, including sunlight, Street View, and
	 * historical imagery.
	 * 
	 * @param type
	 * @param state
	 */
	public final void setOption(GEViewerOptionsType type, GEViewerOptionsValue state){
        int typeArg = GEPluginConstants.get().toJavaScript(type);
	    int stateArg = GEPluginConstants.get().toJavaScript(state);
	    this.setOptionNative(typeArg, stateArg);
	}

	/**
	 * Returns the current state of the specified viewer option type.
	 * 
	 * @param type
	 * @return
	 */
	public final GEViewerOptionsValue getOption(GEViewerOptionsType type) {
	    GEPluginConstants consts = GEPluginConstants.get();
	    int arg = consts.toJavaScript(type);
	    int result = getOptionNative(arg);
	    return GEPluginConstants.get().toGEViewerOptionsValue(result);
	}

    /**
     * Set the state of viewer options, including sunlight, Street View, and
     * historical imagery.
     * 
     * @param type
     * @param state
     */
    private final native void setOptionNative(int type, int state) /*-{
        this.setOption(type, state);
    }-*/;

    /**
     * Returns the current state of the specified viewer option type.
     * 
     * @param type
     * @return
     */
    private final native int getOptionNative(int type) /*-{
        return this.getOption(type);
    }-*/;
}
