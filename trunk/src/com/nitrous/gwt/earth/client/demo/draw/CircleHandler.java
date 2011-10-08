package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.KmlColor;
import com.nitrous.gwt.earth.client.api.KmlCoordArray;
import com.nitrous.gwt.earth.client.api.KmlLineStyle;
import com.nitrous.gwt.earth.client.api.KmlLinearRing;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPolygon;
import com.nitrous.gwt.earth.client.api.KmlStyle;
import com.nitrous.gwt.earth.client.api.KmlStyleSelector;


/**
 * This handler registers a mouse listener that can be used to drawn circles on Google Earth
 * 
 * Based upon formulas found here: http://www.barnabu.co.uk/geapi/polyplot/
 * 
 * @author Nick
 */
public class CircleHandler extends AbstractDrawingHandler {
    private static final double SIDES = 40;
    
    private KmlLinearRing ring;
    private double centerLat;
    private double centerLon;

    private static int counter = 0;
    
    public CircleHandler(GEPlugin ge, DrawingListener listener) {
        super(ge, listener);
    }

    
    private void beginCircle(float lat, float lon) {
        GEPlugin ge = getGEPLugin();
        KmlPlacemark placemark = ge.createPlacemark("");
        setPlacemark(placemark);
        ring = makeCircle(.5, lat, lon);
        placemark.setGeometry(ring);
        ge.getFeatures().appendChild(placemark);
        centerLat = lat;
        centerLon = lon;
    }
    
    private void finishCircle() {
        KmlCoordArray coords = ring.getCoordinates();
        if (coords.getLength() == 1) {
            cancel();
            return;
        }
        
        // convert the linestring into a filled polygon
        GEPlugin ge = getGEPLugin();
        KmlPlacemark placemark = getPlacemark();
        
        KmlPolygon polygon = ge.createPolygon("");        
        KmlLinearRing outer = ge.createLinearRing("");
        polygon.setOuterBoundary(outer);
        
        // copy linestring coords to the polygon
        KmlCoordArray polyCoords = outer.getCoordinates();
        for (int i = 0, len = coords.getLength(); i < len; i++) {
            polyCoords.push(coords.get(i));
        }

        // replace the geometry of the placemark with the polygon
        placemark.setGeometry(polygon);

        // style the polygon
        KmlStyleSelector sel = ge.createStyle("");
        placemark.setStyleSelector(sel);
        
        KmlStyle style = (KmlStyle)sel;
        KmlLineStyle lineStyle = style.getLineStyle();
        lineStyle.setWidth(2);
        // Color is specified in 'aabbggrr' format.
        lineStyle.getColor().set("80ffffff");
        
        KmlColor polyColor = style.getPolyStyle().getColor();
        polyColor.setA(128);
        polyColor.setR(128);
        polyColor.setG(0);
        polyColor.setB(0);

        placemark.setName("Circle "+counter++);
        finish();
    }
    

    
    
    private void updateRadius(float mouseLat, float mouseLon) {
        ring.getCoordinates().clear();
        buildCircle(ring.getCoordinates(), centerLat, centerLon, mouseLat, mouseLon);
    }
    
    private KmlLinearRing makeCircle(double radius, double x, double y) {
        GEPlugin ge = getGEPLugin();
        KmlLinearRing ring = ge.createLinearRing("");
        buildCircle(ring.getCoordinates(), x, y, x+0.5, y+0.5);
        return ring;
    }
    
    private void buildCircle(KmlCoordArray coords, double originLatDeg, double originLonDeg, double radiusLatDeg, double radiusLonDeg) {
        double originLat = Math.toRadians(originLatDeg);
        double originLon = Math.toRadians(originLonDeg);
        double radiusLat = Math.toRadians(radiusLatDeg);
        double radiusLon = Math.toRadians(radiusLonDeg);
        
        double dist = distance(originLat, originLon, radiusLat, radiusLon);
        double bear = bearing(originLat, originLon, radiusLat, radiusLon);
        for (double i = 0; i < SIDES; i++) {
            double[] latlon = destination(originLat, originLon, dist, bear + i * 2.0D * Math.PI/SIDES);            
            coords.pushLatLngAlt(latlon[0], latlon[1], 0);
        }
    }
    
    /**
     * Revert to an initialized state
     */
    protected void reset() {
        super.reset();
        this.ring = null;
    }
    
    @Override
    public void onMouseMove(KmlMouseEvent event) {
        if (ring != null) {
            event.preventDefault();
            float lat = event.getLatitude();
            float lon = event.getLongitude();
            updateRadius(lat, lon);
        }
    }

    @Override
    public void onMouseDown(KmlMouseEvent event) {
        event.preventDefault();
    }

    @Override
    public void onClick(KmlMouseEvent event) {
        event.preventDefault();
        float lat = event.getLatitude();
        float lon = event.getLongitude();
        if (ring == null) {
            beginCircle(lat, lon);
        } else {
            finishCircle();
        }
    }
    
    // destination along great circle.  returns values in degrees
    double[] destination(double lata, double lona, double dist, double brng) { 
        double latb = Math.asin(Math.sin(lata)*Math.cos(dist/6371) + Math.cos(lata)*Math.sin(dist/6371)*Math.cos(brng));
        double lonb = lona+Math.atan2(Math.sin(brng)*Math.sin(dist/6371)*Math.cos(lata), Math.cos(dist/6371)-Math.sin(lata)*Math.sin(latb));
        return new double[]{180*latb/Math.PI, 180*lonb/Math.PI};
    }
    
    // great circle distance (km)
    private double  distance (double lata, double lona, double latb, double lonb) {  
        return Math.acos(Math.sin(lata)*Math.sin(latb)+Math.cos(lata)*Math.cos(latb)*Math.cos(lonb-lona))*6371;
    }
    
    // initial great circle bearing (rad)
    private double bearing(double lata, double lona, double latb, double lonb) {  
        return Math.atan2(Math.sin(lonb-lona)*Math.cos(latb), Math.cos(lata)*Math.sin(latb)-Math.sin(lata)*Math.cos(latb)*Math.cos(lonb-lona));
    }
}
