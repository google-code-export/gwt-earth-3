package com.nitrous.gwt.earth.client.api.event;

import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.KmlObject;

/**
 * A listener that can be notified when a KmlFeature has loaded.
 * 
 * @see GoogleEarth#fetchKml(com.nitrous.gwt.earth.client.api.GEPlugin, String, KmlLoadCallback)
 * @author Nick
 */
public interface KmlLoadCallback {
    /**
     * Called when the specified KmlObject has loaded
     * @param feature The feature that has loaded or null if loading failed.
     */
    void onLoaded(KmlObject feature);
}
