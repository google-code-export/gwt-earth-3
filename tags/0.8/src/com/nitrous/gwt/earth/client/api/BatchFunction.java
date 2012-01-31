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
 * Implementations of BatchFunction can be passed to GoogleEarth.executeBatch()
 * to efficiently execute an arbitrary, user-defined function (the batch
 * function), minimizing the amount of overhead incurred during cross-process
 * communication between the web browser and Google Earth Plugin. This method is
 * useful for batching together a large set of calls to the Earth API, for
 * example, a large number of consecutive calls to KmlCoordArray.pushLatLngAlt.
 * 
 * @see GoogleEarth#executeBatch(GEPlugin, BatchFunction)
 * @author Nick
 * 
 */
public interface BatchFunction {
    /**
     * Execute the batch calls to the google earth plugin here.
     * 
     * This method is guaranteed to run synchronously; that is, executeBatch
     * blocks and does not return until the batch function has completed. In
     * fact there should be no difference between calling executeBatch(fn) and
     * fn() besides execution performance.
     * 
     * @param plugin The GEPlugin upon which the batch operations should be performed
     */
    void run(GEPlugin plugin);
}
