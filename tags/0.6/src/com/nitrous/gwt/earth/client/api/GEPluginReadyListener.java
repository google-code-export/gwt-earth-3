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
 * This file was copied from the gwt-earth project and modified to support a more strongly 
 * typed method signature and notify listeners of initialization failure.
 */
package com.nitrous.gwt.earth.client.api;

/**
 * The interface implemented by any object that wishes to be notified of the
 * success or failure of the Google Earth Plugin initialization.
 * 
 * @author nick
 * 
 */
public interface GEPluginReadyListener {
    /**
     * Plug-in initialized
     * 
     * @param ge
     *            The Google Earth plug-in
     */
    void pluginReady(GEPlugin ge);

    /**
     * Plug-in initialization failed
     */
    void pluginInitFailure();
}
