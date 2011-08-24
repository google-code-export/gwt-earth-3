package com.nitrous.gwt.earth.client.api.event;

/**
 * Listen for events that are fired when Earth has finished rendering the
 * viewport. This listener will be called many times in succession when the
 * viewport is changing. Register this event listener to the
 * <code>GEPlugin</code> and make incremental changes to the viewport for smooth
 * animation.
 * 
 * @author nick
 * 
 */
public interface FrameEndListener {
	/**
	 * Called many times in succession when the
	 * viewport is changing. Make incremental changes to the viewport for
	 * smooth animation.
	 */
	void onFrameEnd();
}
