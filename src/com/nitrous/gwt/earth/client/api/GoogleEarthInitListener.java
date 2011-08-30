package com.nitrous.gwt.earth.client.api;

/**
 * The interface that is implemented by any listener that wishes to be notified when the Google Earth plugin initialization is completed.
 * @author Nick
 */
public interface GoogleEarthInitListener {
    void onSuccess(GEPlugin plugin);
    void onFailure(String cause);
}
