package com.nitrous.gwt.earth.client.demo;

/*
 * Copyright 2011 Nick Kerr
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.nitrous.gwt.earth.client.api.GEHtmlStringBalloon;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlBalloonOpeningEvent;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.event.BalloonListener;

/**
 * An extended version of the "closing ballons" demo that shows how to use a Balloon Listener.
 * 
 * The original JavaScript demo can be found here:
 * http://code.google.com/apis/ajax/playground/?exp=earth#closing_balloons
 * 
 * 
 * @author nick
 * 
 */
public class BalloonListenerDemo implements EntryPoint {

	private GoogleEarthWidget earth;
	private KmlPlacemark placemark;
	
	// the timer that hides the status message
	private Timer timer;
	// the message showing the events
	private Label message;
	
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

		Button showButton = new Button("Show a balloon!");
		showButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GEPlugin ge = earth.getGEPlugin();
				GEHtmlStringBalloon balloon = ge.createHtmlStringBalloon("");
				balloon.setFeature(placemark); // optional
				balloon.setMaxWidth(300);

				  // Google logo.
				balloon.setContentString(
				      "<img src=\"http://www.google.com/intl/en_ALL/images/logo.gif\"><br>"
				      + "<font size=20>Earth Plugin</font><br><font size=-2>sample info "
				      + "window</font>");
				ge.setBalloon(balloon);
			}
		});
		Button closeButton = new Button("Close the balloon!");
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// hide the balloon
				earth.getGEPlugin().setBalloon(null);
			}
		});

		message = new Label();
		HorizontalPanel messageRow = new HorizontalPanel();
		messageRow.add(message);
		
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.add(showButton);
		buttons.add(closeButton);
		
		
		VerticalPanel topPanel = new VerticalPanel();
		topPanel.setWidth("100%");
		topPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		topPanel.add(buttons);
		topPanel.add(messageRow);

		DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
		layout.addNorth(topPanel, 80D);
		layout.add(earth);
		RootLayoutPanel.get().add(layout);

		// the timer that clears the message after a few seconds
		timer = new Timer() {
			@Override
			public void run() {
				message.setText("");
			}
		};


		// begin loading the Google Earth Plug-in
		earth.init();
	}

	/**
	 * Display content on the map
	 */
	private void loadMapContent() {
		// The GEPlugin is the core class and is a great place to start browsing
		// the API
		GEPlugin ge = earth.getGEPlugin();
		ge.getWindow().setVisibility(true);

		// add a navigation control
		ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
		
		// add some layers
		ge.enableLayer(GELayerId.LAYER_BORDERS, true);
		ge.enableLayer(GELayerId.LAYER_ROADS, true);

		// create the placemark
		placemark = ge.createPlacemark("");

		KmlPoint point = ge.createPoint("");
		point.setLatitude(37);
		point.setLongitude(-122);
		placemark.setGeometry(point);

		// add the placemark to the earth DOM
		ge.getFeatures().appendChild(placemark);

		  // look at the placemark we created
		KmlLookAt la = ge.createLookAt("");
		la.set(37, -122,
		    0, // altitude
		    KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
		    0, // heading
		    0, // straight-down tilt
		    5000 // range (inverse of zoom)
		    );
		ge.getView().setAbstractView(la);
		
		// register the balloon event listener
		ge.addBalloonListener(new BalloonListener(){

			@Override
			public void onBalloonClose() {
				timer.cancel();
				message.setText("Balloon close event received");
				// hide the message after 2 seconds
				timer.schedule(2000);
			}

			@Override
			public void onBalloonOpening(KmlBalloonOpeningEvent event) {
				timer.cancel();
				message.setText("Balloon open event received");
				// hide the message after 2 seconds
				timer.schedule(2000);
			}
			
		});
	}

}
