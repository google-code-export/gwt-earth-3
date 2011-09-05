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
package com.nitrous.gwt.earth.client.demo;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.event.MouseListener;

/**
 * An demo that shows how to use a Mouse Listener to monitor mouse interaction with Google Earth
 * 
 * @author nick
 * 
 */
public class MouseListenerDemo implements EntryPoint {

	private GoogleEarthWidget earth;
	
	// the table that is used to display the events
	private CellTable<MouseEventInfo> table;
	private ListDataProvider<MouseEventInfo> dataProvider;
	private DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("HH:mm:ss.SSSS");
	private ScrollPanel scrollPanel;
	private HTML mouseLocation;
	
	public void onModuleLoad() {
		// the table that will be used to render mouse event information
		table = new CellTable<MouseEventInfo>();
		TextColumn<MouseEventInfo> eventType = new TextColumn<MouseEventInfo>() {
			@Override
			public String getValue(MouseEventInfo object) {
				return object.getEventType();
			}
		};
		eventType.setSortable(false);
		TextColumn<MouseEventInfo> eventDetail = new TextColumn<MouseEventInfo>() {
			@Override
			public String getValue(MouseEventInfo object) {
				KmlMouseEvent event = object.getMouseEvent();
				return "Button="+event.getButton()
				       + " Latitude="+event.getLatitude()
				       + " Longitude="+event.getLongitude()
				       +" X="+event.getClientX()
				       +" Y="+event.getClientY();
			}
		};
		eventDetail.setSortable(false);
		TextColumn<MouseEventInfo> eventTime = new TextColumn<MouseEventInfo>() {
			@Override
			public String getValue(MouseEventInfo object) {
				return dateTimeFormat.format(object.getTimestamp());
			}
		};
		eventTime.setSortable(false);
		
		// add the columns
		table.addColumn(eventTime, "Time");
        table.addColumn(eventType, "Type");
		table.addColumn(eventDetail, "Detail");
		table.setWidth("100%");

		// set the data model into the table
		dataProvider = new ListDataProvider<MouseEventInfo>();
		dataProvider.addDataDisplay(table);
				
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

		scrollPanel = new ScrollPanel();
		scrollPanel.setHeight("100%");
		scrollPanel.add(table);

		HorizontalPanel header = new HorizontalPanel();
		header.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mouseLocation = new HTML("");
		header.add(mouseLocation);
		
        SplitLayoutPanel split = new SplitLayoutPanel();
        split.addWest(scrollPanel, 450);
        split.add(earth);

		DockLayoutPanel dockLayout = new DockLayoutPanel(Unit.PX);
		dockLayout.addNorth(header, 30D);
		dockLayout.add(split);
        RootLayoutPanel.get().add(dockLayout);
        
		// begin loading the Google Earth Plug-in
		earth.init();
	}

	private void showMouseLocation(KmlMouseEvent event) {
       String text = 
		     "<b>Mouse Location:</b> X="+event.getClientX()
		   + " Y="+event.getClientY()
    	   + " Latitude="+event.getLatitude()
		   + " Longitude="+event.getLongitude();
       mouseLocation.setHTML(text);		
	}
	
	private void addEvent(MouseEventInfo info) {
		List<MouseEventInfo> list = dataProvider.getList();
		list.add(info);
		table.setVisibleRange(0, list.size());		
		Scheduler.get().scheduleDeferred(new ScheduledCommand(){
			@Override
			public void execute() {
				table.redraw();
				Scheduler.get().scheduleDeferred(new ScheduledCommand(){
					@Override
					public void execute() {
						scrollPanel.scrollToBottom();
					};					
				});
			}			
		});
	};
	
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

		ge.getGlobe().addMouseListener(new MouseListener(){
			@Override
			public void onClick(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Click", event, new Date(System.currentTimeMillis())));;
			}

			@Override
			public void onDoubleClick(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Double Click", event, new Date(System.currentTimeMillis())));;
			}

			@Override
			public void onMouseDown(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Mouse Down", event, new Date(System.currentTimeMillis())));;
			}

			@Override
			public void onMouseUp(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Mouse Up", event, new Date(System.currentTimeMillis())));;
			}

			@Override
			public void onMouseMove(KmlMouseEvent event) {
				showMouseLocation(event);
				// dont show this in the table as its too busy
			}

			@Override
			public void onMouseOut(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Mouse Out", event, new Date(System.currentTimeMillis())));;
			}

			@Override
			public void onMouseOver(KmlMouseEvent event) {
				showMouseLocation(event);
				addEvent(new MouseEventInfo("Mouse Over", event, new Date(System.currentTimeMillis())));;
			}
		});
	}

	/**
	 * A bean that is used to hold mouse event information to be rendered by a CellTable
	 * @author nick
	 *
	 */
	private static class MouseEventInfo {
		private String eventType;
		private KmlMouseEvent event;
		private Date timestamp;
		public MouseEventInfo(String eventType, KmlMouseEvent event, Date timestamp) {
			this.eventType = eventType;
			this.event = event;
			this.timestamp = timestamp;
		}
		public String getEventType() {
			return eventType;
		}
		public KmlMouseEvent getMouseEvent() {
			return event;
		}
		public Date getTimestamp() {
			return timestamp;
		}
	}
}
