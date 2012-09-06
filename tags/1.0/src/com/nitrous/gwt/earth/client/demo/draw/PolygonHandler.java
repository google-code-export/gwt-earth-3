package com.nitrous.gwt.earth.client.demo.draw;

import com.nitrous.gwt.earth.client.api.GEPlugin;
import com.nitrous.gwt.earth.client.api.KmlColor;
import com.nitrous.gwt.earth.client.api.KmlCoordArray;
import com.nitrous.gwt.earth.client.api.KmlLineString;
import com.nitrous.gwt.earth.client.api.KmlLineStyle;
import com.nitrous.gwt.earth.client.api.KmlLinearRing;
import com.nitrous.gwt.earth.client.api.KmlMouseEvent;
import com.nitrous.gwt.earth.client.api.KmlPlacemark;
import com.nitrous.gwt.earth.client.api.KmlPolygon;
import com.nitrous.gwt.earth.client.api.KmlStyle;
import com.nitrous.gwt.earth.client.api.KmlStyleSelector;

/**
 * The mouse handler that allows the user to draw a polygon on the map.
 * @author nick
 *
 */
public class PolygonHandler extends AbstractDrawingHandler {
	private static int counter = 0;
    private KmlLineString lineString;
    
    public PolygonHandler(GEPlugin ge, DrawingListener listener) {
        super(ge, listener);
    }
    
    private void beginPolygon(float lat, float lon) {
        GEPlugin ge = getGEPLugin();
        KmlPlacemark placemark = ge.createPlacemark("");
        lineString = ge.createLineString("");
        placemark.setGeometry(lineString);
        
        KmlCoordArray coords = lineString.getCoordinates();
        coords.pushLatLngAlt(lat, lon, 0);
        coords.pushLatLngAlt(lat, lon, 0);
        
        ge.getFeatures().appendChild(placemark);
        setPlacemark(placemark);
    }

    private void addPoint(double lat, double lon) {
        KmlCoordArray coords = lineString.getCoordinates();
        coords.pushLatLngAlt(lat, lon, 0);
    }
    
    protected void reset() {
        super.reset();
        this.lineString = null;
    }
    
    /**
     * Convert the line string to a polygon
     * and finish the drawing
     */
    private void finishPolygon() {
        KmlCoordArray coords = lineString.getCoordinates();
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
        polyColor.setR(0);
        polyColor.setG(128);
        polyColor.setB(0);
        
        placemark.setName("Polygon "+counter++);

        finish();
    }
    
    @Override
    public void onClick(KmlMouseEvent event) {
        event.preventDefault();
        if (lineString == null) {
            beginPolygon(event.getLatitude(), event.getLongitude());
        } else {
            addPoint(event.getLatitude(), event.getLongitude());
        }
    }

    @Override
    public void onDoubleClick(KmlMouseEvent event) {
        event.preventDefault();
        if (lineString == null) {
            cancel();
        } else {
            addPoint(event.getLatitude(), event.getLongitude());
            finishPolygon();
        }
    }

    @Override
    public void onMouseMove(KmlMouseEvent event) {
        if (lineString == null) {
            return;
        }
        
        double lat = event.getLatitude();
        double lon = event.getLongitude();
        
        // move the last point to the mouse location
        KmlCoordArray coords = lineString.getCoordinates();
        coords.pop();
        coords.pushLatLngAlt(lat, lon, 0);
    }
    
}
