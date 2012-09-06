package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.KmlPlacemark;

/**
 * The interface that is implemented by any object that wishes to be notified when a DrawingHandler has completed drawing a shape on the map
 * @author nick
 *
 */
public interface DrawingListener {
    /**
     * The user has finished drawing the specified placemark.  
     * @param placemark the placemark that was recently added to the map.
     */
    public void onComplete(KmlPlacemark placemark);
    
    /**
     * The drawing action has been canceled
     */
    public void onCanceled();
}
