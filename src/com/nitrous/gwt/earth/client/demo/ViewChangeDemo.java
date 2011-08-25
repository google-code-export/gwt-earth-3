package com.nitrous.gwt.earth.client.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.event.ViewChangeListener;

/**
 * A GWT implementation of the frame end demo found here:
 * http://code.google.com/apis/ajax/playground/?exp=earth#viewchange_event_%28and_viewchangebegin,_viewchangeend%29
 * 
 * @author nick
 * 
 */
public class ViewChangeDemo implements EntryPoint {

	private GoogleEarthWidget earth;
	private HandlerRegistration eventListenerRegistration;

	private Label viewChangeEndCountLabel;
	private int viewChangeEndCount = 0;
	private Label viewChangedLabel;
	private static final String CHANGE_END_TEXT = "viewchangeend count: ";
	
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


		
		VerticalPanel labels = new VerticalPanel();
		viewChangeEndCountLabel = new Label(CHANGE_END_TEXT + 0);
		viewChangedLabel = new Label("View Changed!");
		labels.add(viewChangeEndCountLabel);
		labels.add(viewChangedLabel);

		HorizontalPanel topPanel = new HorizontalPanel();
		topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topPanel.add(labels);
		
		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 40D);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);
		
		viewChangedLabel.getElement().setAttribute("style", "background-color: #a00; color: #fff; font-weight: bold;");
		viewChangedLabel.setVisible(false);

		// begin loading the Google Earth Plug-in
		earth.init();
	}

	private Timer vcTimeout = null;
	
	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// the timer that will be used to hid the label after 250ms of inactivity
		vcTimeout = new Timer(){
			@Override
			public void run() {
				viewChangedLabel.setVisible(false);
			}
		};
		
		// register the view change listener to hide/show the label and update the view change end count label
		eventListenerRegistration = ge.getView().addViewChangeListener(new ViewChangeListener(){

			@Override
			public void onViewChange() {
				viewChangedLabel.setVisible(true);
				//reschedule the timer to hide the 'view changed' message after 250ms of inactivity
				if (vcTimeout != null) {
					vcTimeout.cancel();
				}
				vcTimeout.schedule(250);
				
			}

			@Override
			public void onViewChangeBegin() {
			}

			@Override
			public void onViewChangeEnd() {
				viewChangeEndCount++;
				viewChangeEndCountLabel.setText(CHANGE_END_TEXT + viewChangeEndCount);
			}
		});
		
	}

}
