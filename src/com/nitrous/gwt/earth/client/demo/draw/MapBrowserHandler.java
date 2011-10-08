package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.KmlGeometry;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;


/**
 * The mouse handler that allows the user to move placemarks
 * 
 * @author Nick
 * 
 */
public class MapBrowserHandler extends AbstractDrawingHandler {
    private DragInfo dragInfo;

    private PlacemarkMovementListener placemarkMoveListener;
    public MapBrowserHandler(GEPlugin ge, PlacemarkMovementListener listener) {
        super(ge, null);
        this.placemarkMoveListener = listener;
    }

    @Override
    public void onClick(KmlMouseEvent event) {
    }

    @Override
    public void onMouseMove(KmlMouseEvent event) {
        // listen for mousemove on the globe
        if (dragInfo != null) {
            event.preventDefault();
            KmlPoint point = (KmlPoint)dragInfo.placemark.getGeometry();
            point.setLatitude(event.getLatitude());
            point.setLongitude(event.getLongitude());
            dragInfo.dragged = true;
        }
    }

    @Override
    public void onMouseUp(KmlMouseEvent event) {
        // listen for mouseup on the window
        if (dragInfo != null) {
            if (dragInfo.dragged) {
                // if the placemark was dragged, prevent balloons from popping up
                event.preventDefault();
                if (placemarkMoveListener != null) {
                    placemarkMoveListener.onPlacemarkMoved(dragInfo.placemark);
                }
            }
            dragInfo = null;
        }
    }

    @Override
    public void onMouseDown(KmlMouseEvent event) {
        // listen for mousedown on the window (look specifically for point placemarks)
        if ("KmlPlacemark".equals(event.getTarget().getType())) {
            KmlPlacemark placemark = (KmlPlacemark)event.getTarget();
            KmlGeometry geo = placemark.getGeometry();
            if ("KmlPoint".equals(geo.getType())) {
                dragInfo = new DragInfo(placemark, false);
            }
        }
    }

    private static class DragInfo {
        KmlPlacemark placemark;
        boolean dragged;
        DragInfo(KmlPlacemark placemark, boolean dragged) {
            this.placemark = placemark;
            this.dragged = dragged;
        }
    }
}
