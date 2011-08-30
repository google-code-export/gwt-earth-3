package com.nitrous.gwt.earth.client.api;

/**
 * Implementations of BatchFunction can be passed to GoogleEarth.executeBatch()
 * to efficiently execute an arbitrary, user-defined function (the batch
 * function), minimizing the amount of overhead incurred during cross-process
 * communication between the web browser and Google Earth Plugin. This method is
 * useful for batching together a large set of calls to the Earth API, for
 * example, a large number of consecutive calls to KmlCoordArray.pushLatLngAlt.
 * 
 * @see {@code GoogleEarth#executebatch}
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
