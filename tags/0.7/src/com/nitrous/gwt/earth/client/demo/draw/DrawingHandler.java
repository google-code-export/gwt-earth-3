package com.nitrous.gwt.earth.client.demo.draw;

/**
 * The interface that is implemented by any handler that can be registered to draw on the map
 * @author nick
 *
 */
public interface DrawingHandler {
    void register();
    void unregister();
    void cancel();
}
