package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.KmlPlacemark;


/**
 * The interface that is implemented by a listener that wishes to be notified when the user moves a placemark on the map
 * @author nick
 */
public interface PlacemarkMovementListener {
    void onPlacemarkMoved(KmlPlacemark placemark);
}
