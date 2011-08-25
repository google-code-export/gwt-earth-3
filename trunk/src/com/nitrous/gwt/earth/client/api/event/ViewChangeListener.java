package com.nitrous.gwt.earth.client.api.event;

/**
 * Listen for events that are fired when the view is changing in Earth. 
 * Register this event listener to the <code>GEView</code>.
 * 
 * @author nick
 * 
 */
public interface ViewChangeListener {
	/**
	 * Event fired when the view begins changing in Earth. This event will be
	 * fired once, followed by successive onViewChange() events, and ending with a
	 * onViewChangeEnd() event.
	 */
	void onViewChangeBegin();

	/**
	 * Event fired while the view is changing in Earth. This event will be
	 * triggered in rapid succession while the camera is in motion.
	 */
	void onViewChange();
	
	/**
	 * Event fired when the view stops changing in Earth. 
	 */
	void onViewChangeEnd();
	
}
