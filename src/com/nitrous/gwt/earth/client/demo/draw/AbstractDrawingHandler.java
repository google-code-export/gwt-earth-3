package com.nitrous.gwt.earth.client.demo.draw;

import com.google.gwt.event.shared.HandlerRegistration;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.event.MouseListener;

/**
 * The base class for a mouse listener that is used to create a placemark on the map
 * @author nick
 *
 */
abstract class AbstractDrawingHandler implements DrawingHandler, MouseListener {
    private HandlerRegistration reg;
    private GEPlugin ge;
    private KmlPlacemark placemark;
    private DrawingListener listener;
    
    AbstractDrawingHandler(GEPlugin ge, DrawingListener listener) {
        this.ge = ge;
        this.listener = listener;
    }
    
    protected KmlPlacemark getPlacemark() {
        return placemark;        
    }
    
    protected void setPlacemark(KmlPlacemark placemark) {
        this.placemark = placemark;
    }
    
    protected GEPlugin getGEPLugin() {
        return ge;
    }
    
    /**
     * Abort the drawing operation and unregister this mouse handler from google earth
     */
    public void cancel() {
        unregister();
        if (placemark != null) {
            ge.getFeatures().removeChild(placemark);
        }
        if (listener != null) {
            listener.onCanceled();
        }
    }
    
    /**
     * Unregister this mouse handler from google earth
     */
    public void unregister() {
        if (reg != null) {
            reg.removeHandler();
            reg = null;
        }
    }
    
    /**
     * Register this handler to receive mouse events from GoogleEarth
     */
    public void register() {
        reset();
        reg = getGEPLugin().getWindow().addMouseListener(this);
    }
    
    /**
     * Notify the registered listener of the completed placemark
     */
    protected void finish() {
        if (listener != null) {
            listener.onComplete(placemark);
        }
        reset();
    }
    
    /**
     * Revert to an initialized state
     */
    protected void reset() {
        this.placemark = null;
    }
    
    @Override
    public void onMouseOver(KmlMouseEvent event) {
    }

    @Override
    public void onMouseOut(KmlMouseEvent event) {
    }

    @Override
    public void onMouseMove(KmlMouseEvent event) {
    }

    @Override
    public void onMouseUp(KmlMouseEvent event) {
    }

    @Override
    public void onMouseDown(KmlMouseEvent event) {
    }

    @Override
    public void onDoubleClick(KmlMouseEvent event) {
    }

    @Override
    public void onClick(KmlMouseEvent event) {
    }
    
}
