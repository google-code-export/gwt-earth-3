package com.nitrous.gwt.earth.client.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlLookAt;

/**
 * A demo that illustrates how to render a GWT PopupPanel in-front of the Google Earth plug-in using the following 'shim' technique:
 * 
 * An IFrame is rendered in-front of the Google Earth plug-in but behind the PopupPanel. The size and z-index of the IFrame are configured
 * in such a way that the IFrame is not visible but is used to 'trick' the browser in to rendering the PopupPanel.
 * 
 * Note: This technique does not work for all browsers.
 * 
 * @author nick
 *
 */
public class ShimDemo implements EntryPoint {

	private GoogleEarthWidget earth;

	public void onModuleLoad() {
		// construct the UI widget
		earth = new GoogleEarthWidget();

		// register a listener to be notified when the earth plug-in has loaded
		earth.addPluginReadyListener(new GEPluginReadyListener() {
			public void pluginReady(GEPlugin ge) {
				// show map content once the plugin has loaded
				loadMapContent();
			}

			public void pluginInitFailure() {
				// failure!
				Window.alert("Failed to initialize Google Earth Plug-in");
			}
		});

		earth.setWidth("100%");
		earth.setHeight("100%");

		// The UI layout with an informative header.
		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(
			new HTML("Google Earth Shim Demo. " +
					"You should see a Popup Panel displayed over the Google Earth map below. " +
					"If not, your browser is not supported by this workaround.<br/>Source code here: " +
					"<a href='http://code.google.com/p/gwt-earth-3/source/browse/trunk/src/com/nitrous/gwt/earth/client/demo/ShimDemo.java'>" +
					"http://code.google.com/p/gwt-earth-3/source/browse/trunk/src/com/nitrous/gwt/earth/client/demo/ShimDemo.java</a>"),
					40);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);
		
		// Begin loading the Google Earth Plug-in
		earth.init();
	}

	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// show some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// look at the placemark
		KmlLookAt lookAt = ge.getView().copyAsLookAt(KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND);
		lookAt.setLatitude(34.73D);
		lookAt.setLongitude(-86.59D);
		lookAt.setRange(500000D);
		ge.getView().setAbstractView(lookAt);

		// Popup a window
		showPopupWindow();
	}

	/**
	 * This is how to display a GWT PopupPanel over the top of the Google Earth
	 * Plug-in using the 'shim' technique. The shim technique places an IFrame
	 * in-front of the earth plug-in but behind the PopupPanel by configuring
	 * the absolute position, size and z-index of the IFrame.
	 */
	private void showPopupWindow() {
		
		// the HTML content to be rendered inside the PopupPanel
		HTML content = new HTML(
				"<b>Window test</b><br>" 
				+ "This PopupPanel is visible since we have an IFrame (shim) between the PopupPanel and the Google Earth Plugin. "
				+ "View source code <a href=\"" 
				+ "http://code.google.com/p/gwt-earth-3/source/browse/trunk/src/com/nitrous/gwt/earth/client/demo/ShimDemo.java\"" 
				+ " target=\"new\">here</a>");

		// configure the PopupPanel size and position
		final PopupPanel window = new PopupPanel(false, false);
		window.setTitle("Shim test");
		window.add(content);
		final int width = 300;
		final int height = 80;
		window.setSize(width+"px", height+"px");
		window.center();
		final int left = window.getPopupLeft();
		final int top = window.getPopupTop();

		// Configure the z-index of the PopupPanel HTML content so that it is rendered in-front of everything (highest z-index)
		content.getElement().setAttribute("style", 
				  "z-index: " + (Integer.MAX_VALUE) + ";" 
				+ " width: " + width + "px;" 
				+ " height: " + height + "px;");
		
		// PopupPanel is to be displayed immediately behind content (slightly lower z-index)
		window.getElement().setAttribute("style", 
				  "z-index: " + (Integer.MAX_VALUE-1) + ";" 
				+ " position: absolute;" 
				+ " left: " + left + "px;"
				+ " top: " + top + "px;");
		
		window.show();
		
		// Allow some time for the browser to render the window and then configure the IFrame shim
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				final IFrameElement iframe = Document.get().createIFrameElement();

				// Initialize the IFrame to be the same size and position as the
				// window but with a smaller z-index value.
				// The size of the IFrame is tweaked by +9px to allow the border of the PopupPanel to be rendered correctly.
				iframe.setAttribute("style", 
						"z-index: " + (Integer.MAX_VALUE - 2) + ";" 
						+ " width: " + (width+9) + "px;" 
						+ " height: " + (height+9) + "px;"
						+ " position: absolute;" 
						+ " left: " + left + "px;"
						+ " top: " + top + "px;");

				// add the IFrame to the document body
				Document.get().getBody().appendChild(iframe);

				// remove the IFrame when the PopupPanel is closed
				window.addCloseHandler(new CloseHandler<PopupPanel>(){
					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						iframe.removeFromParent();
					}
				});
			}
		});
	}

}
