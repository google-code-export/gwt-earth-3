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
package com.nitrous.gwt.earth.client.api;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The GEPlugin is the Google Earth Plugin's main object, and this is the object
 * that is returned to the JavaScript application when you first create a
 * plug-in instance. GEPlugin provides factory methods for constructing other
 * objects (placemarks, and so on), and is also used to retrieve the root
 * document objects.
 * 
 * @author Nick
 * 
 */
public class GEPlugin extends JavaScriptObject {

    protected GEPlugin() {
    }

    /**
     * Bind constants to underlying JavaScript implementation.
     */
    public final void bindConstants() {
        GEPluginConstants.bind(this);
    }

    /**
     * Parse a string of KML and return a handle to the root of the KML object
     * structure that was created.
     * 
     * @param kml
     * @return
     */
    public final native KmlObject parseKml(String kml) /*-{
		return this.parseKml(kml);
    }-*/;

    /**
     * Returns the currently active balloon, or null.
     * 
     * @return
     */
    public final native GEAbstractBalloon getBalloon() /*-{
		return this.getBalloon();
    }-*/;

    /**
     * Sets the given balloon as the active balloon, replacing any existing
     * active balloon. If the given feature is visible, then the balloon is
     * displayed. Otherwise, the balloon is hidden. If the argument is null,
     * then any existing active balloon will be hidden.
     * 
     * @param newActiveBalloon
     */
    public final native void setBalloon(GEAbstractBalloon newActiveBalloon) /*-{
		this.setBalloon(newActiveBalloon);
    }-*/;

    /**
     * Used for debugging purposes; if this value is not equal to the value
     * returned by getPluginVersion then there is a misconfiguration on the end
     * user's system. This check is automatically done during plugin
     * instantiation.
     * 
     * @return
     */
    public final native String getEarthVersion() /*-{
		return this.getEarthVersion();
    }-*/;

    /**
     * 
     * @return The version of the Google Earth Plug-in installed on the end
     *         user's machine.
     */
    public final native String getPluginVersion() /*-{
		return this.getPluginVersion();
    }-*/;

    /**
     * 
     * @return The version of the JavaScript API that the Google Earth Plug-in
     *         installed on the end user's machine is capable of interfacing
     *         with.
     */
    public final native String getApiVersion() /*-{
		return this.getApiVersion();
    }-*/;

    /**
     * 
     * @return The options used to manipulate the behavior of the Google Earth
     *         plugin.
     */
    public final native GEOptions getOptions() /*-{
		return this.getOptions();
    }-*/;

    /**
     * 
     * @return The time class used to manipulate the behavior of the Google
     *         Earth plugin time.
     */
    public final native GETime getTime() /*-{
		return this.getTime();
    }-*/;

    /**
     * Controls the window options.
     * 
     * @return
     */
    public final native GEWindow getWindow() /*-{
		return this.getWindow();
    }-*/;

    /**
     * Controls the globe behavior.
     * 
     * @return
     */
    public final native GEGlobe getGlobe() /*-{
		return this.getGlobe();
    }-*/;

    /**
     * Displays the dawn to dusk views.
     * 
     * @return
     */
    public final native GESun getSun() /*-{
		return this.getSun();
    }-*/;

    /**
     * Controls built-in layer behavior.
     * 
     * @return
     */
    public final native KmlLayerRoot getLayerRoot() /*-{
		return this.getLayerRoot();
    }-*/;

    /**
     * Controls the plugin viewport.
     * 
     * @return
     */
    public final native GEView getView() /*-{
		return this.getView();
    }-*/;

    /**
     * Controls the navigation controls on the globe.
     * 
     * @return
     */
    public final native GENavigationControl getNavigationControl() /*-{
		return this.getNavigationControl();
    }-*/;

    /**
     * 
     * @return The top-level features currently in the Earth object.
     */
    public final native GEFeatureContainer getFeatures() /*-{
		return this.getFeatures();
    }-*/;

    /**
     * Exposes functionality for interacting with KML tours.
     * 
     * @return
     */
    public final native GETourPlayer getTourPlayer() /*-{
		return this.getTourPlayer();
    }-*/;

    /**
     * Exposes functionality for interacting with photo overlays.
     * 
     * @return
     */
    public final native GEPhotoOverlayViewer getPhotoOverlayViewer() /*-{
		return this.getPhotoOverlayViewer();
    }-*/;

    /**
     * 
     * @return Returns a number between 0 and 100 (inclusive) that indicates the
     *         progress of the streaming of imagery for the current view. A
     *         value of 100 means that the imagery is completely streamed in.
     */
    public final native float getStreamingPercent() /*-{
		return this.getStreamingPercent();
    }-*/;

    /**
     * Get an element by ID. This is functionally equivalent to getElementByUrl
     * with an unspecified base URL. For example, getElementByUrl('#foo'). Usage
     * is when finding objects created with JavaScript, which have unspecified
     * base URLs. The object must be a descendant of the DOM before it can be
     * found.
     * 
     * @param id
     * @return
     */
    public final native KmlObject getElementById(String id) /*-{
		return this.getElementById(id);
    }-*/;

    /**
     * Get an element by URL. A URL consists of the base address and the ID,
     * joined with the # character. For example:
     * http://www.google.com/bar.kml#here_be_monsters This applies to objects
     * that are fetched. In the case of plugin created objects, the URL is
     * simply #foo. The object must be a descendant of the DOM before it can be
     * found.
     * 
     * @param url
     * @return
     */
    public final native KmlObject getElementByUrl(String url) /*-{
		return this.getElementByUrl(url);
    }-*/;

    /**
     * Get a list of elements by type.
     * 
     * @param type
     * @return
     */
    public final native KmlObjectList getElementsByType(String type) /*-{
		return this.getElementsByType(type);
    }-*/;

    /**
     * Creates a placemark on the globe. A Placemark is a feature with
     * associated Geometry. A Placemark with a Point has an icon associated with
     * it that marks a point on the Earth in the 3D viewer. (In the Google Earth
     * 3D viewer, a Point Placemark is the only object you can click or roll
     * over. Other Geometry objects do not have an icon in the 3D viewer. To
     * allow the user to click in the 3D viewer, you would need to create a
     * MultiGeometry object that contains both a Point and the other Geometry
     * object.)
     * 
     * @param id
     *            ID of placemark.
     * @return
     */
    public final native KmlPlacemark createPlacemark(String id) /*-{
		return this.createPlacemark(id);
    }-*/;

    /**
     * Creates a point on the globe. Specifies the geographic location defined
     * by longitude, latitude, and (optional) altitude.
     * 
     * @param id
     *            ID of new location
     * @return
     */
    public final native KmlPoint createPoint(String id) /*-{
		return this.createPoint(id);
    }-*/;

    /**
     * Creates a line string on Google Earth.
     * 
     * @param id
     *            location of the new string.
     * @return
     */
    public final native KmlLineString createLineString(String id) /*-{
		return this.createLineString(id);
    }-*/;

    /**
     * Creates a folder. A KMLFolder is used to arrange other features
     * hierarchically (Folders, Placemarks, NetworkLinks, or Overlays). A
     * feature is visible only if it and all its ancestors are visible.
     * 
     * @param id
     *            ID of the new folder
     * @return
     */
    public final native KmlFolder createFolder(String id) /*-{
		return this.createFolder(id);
    }-*/;

    /**
     * Creates level of detail (LOD). LOD describes the size of the projected
     * region on the screen that is required in order for the region to be
     * considered active. Also specifies the size of the pixel ramp used for
     * fading in (from transparent to opaque) and fading out (from opaque to
     * transparent).
     * 
     * @param id
     *            ID of the new LOD.
     * 
     * @return
     */
    public final native KmlLod createLod(String id) /*-{
		return this.createLod(id);
    }-*/;

    /**
     * Creates a LatLonBox, a bounding box that describes an area of interest
     * defined by geographic coordinates and altitudes.
     * 
     * @param id
     *            ID of the new LatLonBox
     * @return
     */
    public final native KmlLatLonBox createLatLonBox(String id) /*-{
		return this.createLatLonBox(id);
    }-*/;

    /**
     * Creates a LatLonAltBox, a bounding box that describes an area of interest
     * defined by geographic coordinates and altitudes.
     * 
     * @param id
     *            ID of the new LatLonAltBox
     * @return
     */
    public final native KmlLatLonAltBox createLatLonAltBox(String id) /*-{
		return this.createLatLonAltBox(id);
    }-*/;

    /**
     * Creates a Document. A Document is a container for features and styles.
     * 
     * @param id
     *            ID of the new KML document.
     * @return
     */
    public final native KmlDocument createDocument(String id) /*-{
		return this.createDocument(id);
    }-*/;

    /**
     * Creates a Region in Google Earth. A Region contains a bounding box that
     * describes an area of interest defined by geographic coordinates and
     * altitudes.
     * 
     * @param id
     *            ID of the new KML region.
     * @return
     */
    public final native KmlRegion createRegion(String id) /*-{
		return this.createRegion(id);
    }-*/;

    /**
     * Specifies the exact coordinates of the Model's origin in latitude,
     * longitude, and altitude. Latitude and longitude measurements are standard
     * lat-lon projection with WGS84 datum. Altitude is distance above the
     * earth's surface, in meters, and is interpreted according to altitudeMode.
     * 
     * @param id
     *            ID of the new KML location.
     * @return
     */
    public final native KmlLocation createLocation(String id) /*-{
		return this.createLocation(id);
    }-*/;

    /**
     * Sets the rotation of a 3D model's coordinate system to position the
     * object in Google Earth.
     * 
     * @param id
     *            ID of the new KMLOrientation.
     * @return
     */
    public final native KmlOrientation createOrientation(String id) /*-{
		return this.createOrientation(id);
    }-*/;

    /**
     * Sets the scale of a model along the x, y, and z axes in the model's
     * coordinate space.
     * 
     * @param id
     *            ID of KmlScale.
     * @return
     */
    public final native KmlScale createScale(String id) /*-{
		return this.createScale(id);
    }-*/;

    /**
     * Creates a model. A model is a 3D object described in a COLLADA file.
     * COLLADA files have a .dae file extension. Models are created in their own
     * coordinate space and then located, positioned, and scaled in Google
     * Earth.
     * 
     * @param id
     *            ID of the new KmlModel.
     * @return
     */
    public final native KmlModel createModel(String id) /*-{
		return this.createModel(id);
    }-*/;

    /**
     * A Style defines an addressable style group that can be referenced by
     * StyleMaps and features.
     * 
     * @param id
     *            ID of the new KMLStyle object.
     * @return
     */
    public final native KmlStyle createStyle(String id) /*-{
		return this.createStyle(id);
    }-*/;

    /**
     * Creates a LinearRing. A LinearRing defines a closed line string,
     * typically the outer boundary of a Polygon. Optionally, a LinearRing can
     * also be used as the inner boundary of a Polygon to create holes in the
     * Polygon.
     * 
     * @param id
     *            ID of the new KmlLinearRing.
     * @return
     */
    public final native KmlLinearRing createLinearRing(String id) /*-{
		return this.createLinearRing(id);
    }-*/;

    /**
     * Creates a Polygon. A Polygon is defined by an outer boundary and 0 or
     * more inner boundaries.
     * 
     * @param id
     *            ID of the new Polygon.
     * @return
     */
    public final native KmlPolygon createPolygon(String id) /*-{
		return this.createPolygon(id);
    }-*/;

    /**
     * Creates an Icon. An icon defines an image associated with an Icon style
     * or overlay.
     * 
     * @param id
     *            ID of the new KmlIcon.
     * @return
     */
    public final native KmlIcon createIcon(String id) /*-{
		return this.createIcon(id);
    }-*/;

    /**
     * Creates a Link. A Link specifies the location of KML files fetched by
     * network links, image files used in any overlay, or model files used with
     * the Model object.
     * 
     * @param id
     *            ID of the new KmlLink.
     * @return
     */
    public final native KmlLink createLink(String id) /*-{
		return this.createLink(id);
    }-*/;

    /**
     * Creates a GroundOverlay. A GroundOverlay draws an image overlay draped
     * onto the terrain.
     * 
     * @param id
     *            ID of the new KmlGroundOverlay.
     * @return
     */
    public final native KmlGroundOverlay createGroundOverlay(String id) /*-{
		return this.createGroundOverlay(id);
    }-*/;

    /**
     * Creates a NetworkLink. A NetworkLink references a KML file or KMZ archive
     * on a local or remote network.
     * 
     * @param id
     *            ID of the new KmlNetworkLink.
     * @return
     */
    public final native KmlNetworkLink createNetworkLink(String id) /*-{
		return this.createNetworkLink(id);
    }-*/;

    /**
     * Creates a ScreenOverlay. A ScreenOverlay draws an image overlay fixed to
     * the screen.
     * 
     * @param id
     *            ID of the new KmlScreenOverlay.
     * @return
     */
    public final native KmlScreenOverlay createScreenOverlay(String id) /*-{
		return this.createScreenOverlay(id);
    }-*/;

    /**
     * Creates a container for one or more geometry primitives associated with
     * the same feature.
     * 
     * @param id
     *            ID of the new KmlMultiGeometry container.
     * @return
     */
    public final native KmlMultiGeometry createMultiGeometry(String id) /*-{
		return this.createMultiGeometry(id);
    }-*/;

    /**
     * Creates a StyleMap. A StyleMap maps between two different icon styles.
     * Typically, a StyleMap is used to provide separate normal and highlighted
     * styles for a Placemark, so that the highlighted version appears when the
     * user mouses over the icon in Google Earth.
     * 
     * @param id
     * @return
     */
    public final native KmlStyleMap createStyleMap(String id) /*-{
		return this.createStyleMap(id);
    }-*/;

    /**
     * Creates a new LookAt. A LookAt element positions the camera view in
     * relation to an object that is being viewed.
     * 
     * @param id
     * @return
     */
    public final native KmlLookAt createLookAt(String id) /*-{
		return this.createLookAt(id);
    }-*/;

    /**
     * Creates a new Camera. This element positions the camera relative to the
     * Earth's surface and defines the view direction.
     * 
     * @param id
     * @return
     */
    public final native KmlCamera createCamera(String id) /*-{
		return this.createCamera(id);
    }-*/;

    public final native KmlTour createTour(String id) /*-{
		return this.createTour(id);
    }-*/;

    public final native KmlPhotoOverlay createPhotoOverlay(String id) /*-{
		return this.createPhotoOverlay(id);
    }-*/;

    /**
     * Creates a new viewer options object.
     * 
     * @param id
     * @return
     */
    public final native KmlViewerOptions createViewerOptions(String id) /*-{
		return this.createViewerOptions(id);
    }-*/;

    public final native KmlCoord createCoord() /*-{
		return this.createCoord();
    }-*/;

    public final native KmlObjectBase createElement(String tagName, String id) /*-{
		return this.createElement(tagName, id);
    }-*/;

    /**
     * Create a KmlTimeStamp object. For more information, refer to the Time
     * chapter of the Google Earth API developer's guide.
     * 
     * @param id
     * @return
     */
    public final native KmlTimeStamp createTimeStamp(String id) /*-{
		return this.createTimeStamp(id);
    }-*/;

    /**
     * Create a KmlTimeSpan object. For more information, refer to the Time
     * chapter of the Google Earth API developer's guide.
     * 
     * @param id
     * @return
     */
    public final native KmlTimeSpan createTimeSpan(String id) /*-{
		return this.createTimeSpan(id);
    }-*/;

    /**
     * Creates a Feature balloon.
     * 
     * @param id
     *            ID of GEFeatureBalloon.
     * @return
     */
    public final native GEFeatureBalloon createFeatureBalloon(String id) /*-{
		return this.createFeatureBalloon(id);
    }-*/;

    /**
     * Creates an HTML string balloon.
     * 
     * @param id
     *            ID of GEHtmlStringBalloon.
     * @return
     */
    public final native GEHtmlStringBalloon createHtmlStringBalloon(String id) /*-{
		return this.createHtmlStringBalloon(id);
    }-*/;

    /**
     * Creates an Html Div Balloon.
     * 
     * @param id
     *            ID of GEHtmlDivBalloon.
     * @return
     */
    public final native GEHtmlDivBalloon createHtmlDivBalloon(String id) /*-{
		return this.createHtmlDivBalloon(id);
    }-*/;

    /**
     * Hide or show the layer associated with the specified ID.
     * 
     * @param layerId
     *            The ID of the layer to hide or show.
     * @param visible
     *            True to show the layer, false to hide it.
     */
    public final void enableLayer(GELayerId layer, boolean visible) {
        getLayerRoot().enableLayer(layer, visible);
    }
    
    /**
     * Hide or show the layer associated with the specified ID.
     * 
     * @param layerId
     *            The ID of the layer to hide or show.
     * @param visible
     *            True to show the layer, false to hide it.
     */
    public final void enableLayerById(String layerId, boolean visible) {
        getLayerRoot().enableLayerById(layerId, visible);
    }
    
    /**
     * Retrieve a layer by ID
     * 
     * @param layerId
     *            The ID of the layer to retrieve
     * @return The layer identified by the specified id
     * @see GEPlugin.LAYER_TERRAIN
     * @see GEPlugin.LAYER_ROADS
     * @see GEPlugin.LAYER_BUILDINGS
     * @see GEPlugin.LAYER_BUILDINGS_LOW_RESOLUTION;
     * @see GEPlugin.LAYER_BORDERS;
     * @see GEPlugin.LAYER_TREES;
     */
    public final KmlFolder getLayerById(String layerId) {
        return getLayerRoot().getLayerById(layerId);
    }
    
    /**
     * TODO: implement event handling methods
     * 
     * <pre>
     * 		void GEPlugin.frameend	(		 ) 	
     * 			Event fired when Earth has finished rendering the viewport. This event will be called many times in succession when the viewport is changing. Add a listener for this event and make incremental changes to the viewport for smooth animation.
     * 		
     * 		void GEPlugin.balloonclose	(		 ) 	
     * 			Event fired when the current balloon is closed.
     * 		
     * 		void GEPlugin.balloonopening	(	KmlBalloonOpeningEvent 	event	 ) 	
     * 			Event fired when a balloon is about to open.
     * </pre>
     */
}