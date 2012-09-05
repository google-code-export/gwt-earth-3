package com.nitrous.gwt.earth.client.demo;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.nitrous.gwt.earth.client.api.GELayerId;
import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.GEPluginReadyListener;
import com.nitrous.gwt.earth.client.api.GEVisibility;
import com.nitrous.gwt.earth.client.api.GoogleEarth;
import com.nitrous.gwt.earth.client.api.GoogleEarthWidget;
import com.nitrous.gwt.earth.client.api.KmlGeometry;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.demo.draw.CircleHandler;
import com.nitrous.gwt.earth.client.demo.draw.DrawingHandler;
import com.nitrous.gwt.earth.client.demo.draw.DrawingListener;
import com.nitrous.gwt.earth.client.demo.draw.MapBrowserHandler;
import com.nitrous.gwt.earth.client.demo.draw.PlacemarkHandler;
import com.nitrous.gwt.earth.client.demo.draw.PlacemarkMovementListener;
import com.nitrous.gwt.earth.client.demo.draw.PolygonHandler;

/**
 * This demo shows how to use Mouse Listeners to draw KML objects on the map 
 * @author Nick
 *
 */
public class PolygonDrawingDemo implements EntryPoint {

	private CircleHandler circleHandler;
    private PolygonHandler polyHandler;
    private PlacemarkHandler placemarkHandler;
    private MapBrowserHandler defaultHandler;
    
    private DrawingHandler activeHandler;
    
    private ToggleButton createCircleButton;
    private ToggleButton createPolyButton;
    private ToggleButton createPlacemarkButton;
    
    private ShinyButton clearButton;
    
    private GEPlugin ge;
    
    // the placemarks currently displayed on the map
    private ArrayList<KmlPlacemark> placemarks;
    
    public void onModuleLoad() {
    	// Load the Earth API
    	GoogleEarth.loadApi(new Runnable(){
			@Override
			public void run() {
				// start the application
				onApiLoaded();				
			}    		
    	});    	
    }
    
    /**
     * The Google earth API has loaded, start the application
     */
    private void onApiLoaded() {
    	GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				GWT.log("Uncaught exception", e);
			}
		});
        
    	
        GoogleEarthWidget earth = new GoogleEarthWidget();
        earth.addPluginReadyListener(new GEPluginReadyListener() {
            public void pluginReady(GEPlugin ge) {
                PolygonDrawingDemo.this.ge = ge;                
                onMapLoaded();
            }

            public void pluginInitFailure() {
            	GWT.log("Failed to initialize google earth plugin");
            }
        });
        earth.setSize("100%", "100%");
        
        
        HorizontalPanel buttons = new HorizontalPanel();        
        buttons.setSpacing(5);
        createCircleButton = new ToggleButton("Create Circle");
        createCircleButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {
                    beginCreateCircle();
                } else if (activeHandler != null && activeHandler == circleHandler) {
                    circleHandler.cancel();
                }
            }
        });
        
        createPolyButton = new ToggleButton("Create Polygon");
        createPolyButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {
                    beginPolygon();
                } else if (activeHandler != null && activeHandler == polyHandler) {
                    polyHandler.cancel();
                }
            }
        });
        
        createPlacemarkButton = new ToggleButton("Create Placemark");
        createPlacemarkButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                if (event.getValue()) {
                    beginPlacemark();
                } else if (activeHandler != null && activeHandler == placemarkHandler) {
                    placemarkHandler.cancel();
                }
            }
        });
        
        // toggle buttons that act like regular buttons to make style/size consistent
        clearButton = new ShinyButton("Clear Map");
        clearButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                clearMap();
            }
        });
        
        buttons.add(createCircleButton);
        buttons.add(createPolyButton);
        buttons.add(createPlacemarkButton);
        buttons.add(clearButton);
        
        HorizontalPanel header = new HorizontalPanel();
        header.setWidth("100%");
        header.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        header.add(buttons);
        
        DockLayoutPanel layout = new DockLayoutPanel(Unit.PX);
        layout.addNorth(header,  40);
        layout.add(earth);
        RootLayoutPanel.get().add(layout);
        
        RootLayoutPanel.get().sinkEvents(Event.KEYDOWN);
        RootLayoutPanel.get().addDomHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
					if (activeHandler != null && activeHandler != defaultHandler) {						
						cancelHandler();
					}
				}
			}
		}, KeyDownEvent.getType());

        placemarks = new ArrayList<KmlPlacemark>();
        
        earth.init();
    }
   
    private void onMapLoaded() {
        // The GEPlugin is the core class and is a great place to start browsing the API
        ge.getWindow().setVisibility(true);
        
        // add a navigation control
        ge.getNavigationControl().setVisibility(GEVisibility.VISIBILITY_AUTO);
        
        // show some layers
        ge.enableLayer(GELayerId.LAYER_BORDERS, true);
        ge.enableLayer(GELayerId.LAYER_ROADS, true);

        // show an over-view pane
        ge.getOptions().setOverviewMapVisibility(true);
        ge.getOptions().setStatusBarVisibility(true);
        
        // register the default mouse handler
        registerDefaultHandler();
    }

    private void clearMap() {
    	// hide the balloon if visible
    	ge.setBalloon(null);
    	
        for (KmlPlacemark placemark : placemarks) {
            ge.getFeatures().removeChild(placemark);
        }
        placemarks.clear();
    }
    
    private void beginCreateCircle() {
        cancelHandler();
        if (circleHandler == null) {
        	DrawingListenerImpl listener = new DrawingListenerImpl(createCircleButton);
        	circleHandler = new CircleHandler(ge, listener);
        }
        registerHandler(circleHandler); 
    }
    
    private void beginPlacemark() {
        cancelHandler();
        if (placemarkHandler == null) {
        	DrawingListenerImpl listener = new DrawingListenerImpl(createPlacemarkButton);
        	placemarkHandler = new PlacemarkHandler(ge, listener);
        }
        registerHandler(placemarkHandler); 
    }
    
    private void beginPolygon() {
        cancelHandler();
        if (polyHandler == null) {
        	DrawingListenerImpl listener = new DrawingListenerImpl(createPolyButton);
            polyHandler = new PolygonHandler(ge, listener);
        }
        registerHandler(polyHandler); 
    }
    
    private void registerDefaultHandler() {
        if (defaultHandler == null) {
            defaultHandler = new MapBrowserHandler(ge,  new PlacemarkMovementListener() {
                @Override
                public void onPlacemarkMoved(KmlPlacemark placemark) {
                    KmlGeometry geo = placemark.getGeometry();
                    if ("KmlPoint".equals(geo.getType())) {
                        KmlPoint point = (KmlPoint)geo;
                        GWT.log("placemark "+placemark.getName()
                                +" moved to " + point.getLatitude() 
                                + ", "+point.getLongitude());
                    }
                }
            });
        }
        registerHandler(defaultHandler);
    }
    
    private void registerHandler(DrawingHandler handler) {
        activeHandler = handler;
        activeHandler.register();
    }
    
    private void unregisterHandler() {
        if (activeHandler != null) {
            activeHandler.unregister();
            activeHandler = null;
        }
    }
    
    private void cancelHandler() {
        if (activeHandler != null) {
            activeHandler.cancel();
        }
    }

    /**
     * A ToggleButton that behaves like a normal button. 
     * This button is used so that all buttons in the header look the same.
     * 
     * @author nick
     */
    private static class ShinyButton extends ToggleButton {
        ShinyButton(String label) {
            super(label);
            this.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if (event.getValue()) {
                        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                            @Override
                            public void execute() {
                                Timer t = new Timer() {
                                    @Override
                                    public void run() {
                                        ShinyButton.this.setValue(false);
                                    }
                                };
                                t.schedule(200);
                            }
                        });
                    }
                }
            });
        }
    }
    
    /**
     * A drawing listener that adds the completed placemark to the list of known placemarks
     * and resets the toggle button that was used to initiate the drawing mode
     * @author nick
     *
     */
    private class DrawingListenerImpl implements DrawingListener {
    	private ToggleButton button;
    	DrawingListenerImpl(ToggleButton button) {
    		this.button = button;
    	}
    	@Override
        public void onComplete(final KmlPlacemark placemark) {
            placemarks.add(placemark);
            unregisterHandler();
            registerDefaultHandler();
            untoggle();            
        }
        
        @Override
        public void onCanceled() {
            unregisterHandler();
            registerDefaultHandler();
            untoggle();            
        }
        
        private void untoggle() {
        	Timer t = new Timer() {
                @Override
                public void run() {
                    button.setValue(false);
                }
            };
            t.schedule(200);
        }
    }
    
}
