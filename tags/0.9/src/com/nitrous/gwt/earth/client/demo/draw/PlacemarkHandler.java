package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.KmlAltitudeMode;
import com.nitrous.gwt.earth.client.api.KmlIcon;
import com.nitrous.gwt.earth.client.api.KmlLookAt;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPoint;
import com.nitrous.gwt.earth.client.api.KmlStyle;


/**
 * The mouse handler that allows the user to add placemarks on the map by clicking the location where the placemark should be created.
 * @author nick
 */
public class PlacemarkHandler extends AbstractDrawingHandler {
    private static int counter = 0;
    
    public PlacemarkHandler(GEPlugin ge, DrawingListener listener) {
        super(ge, listener);
    }
    
    private void createPlacemark(float lat, float lon) {
        GEPlugin ge = getGEPLugin();
        
        // plot a placemark
        KmlPlacemark placemark = ge.createPlacemark("");
        placemark.setName("placemark"+counter);
        ge.getFeatures().appendChild(placemark);
        
        // Create style map for placemark
        KmlIcon icon = ge.createIcon("");
        icon.setHref("http://maps.google.com/mapfiles/kml/paddle/red-circle.png");
        KmlStyle style = ge.createStyle("");
        style.getIconStyle().setIcon(icon);
        placemark.setStyleSelector(style);

        // Create point
        KmlPoint point = ge.createPoint("");
        point.setLatitude(lat);
        point.setLongitude(lon);
        placemark.setGeometry(point);

        counter++;  

        setPlacemark(placemark);
               
		  // look at the placemark we created
		KmlLookAt la = ge.createLookAt("");
		la.set(lat, lon,
		    2500000, // altitude
		    KmlAltitudeMode.ALTITUDE_RELATIVE_TO_GROUND,
		    0, // heading
		    0, // straight-down tilt
		    1000 // range (inverse of zoom)
		    );
		ge.getView().setAbstractView(la);		
        
        finish();
    }

    
    @Override
    public void onClick(KmlMouseEvent event) {
        event.preventDefault();
    }

    @Override
    public void onDoubleClick(KmlMouseEvent event) {
        event.preventDefault();
    }
    
    @Override
    public void onMouseDown(KmlMouseEvent event) {
        event.preventDefault();
        createPlacemark(event.getLatitude(), event.getLongitude());
    }
    
    @Override
    public void onMouseMove(KmlMouseEvent event) {
        event.preventDefault();
    }
    
}
