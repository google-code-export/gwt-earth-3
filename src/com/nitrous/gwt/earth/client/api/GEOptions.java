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
 * Used to manipulate the behavior of the Google Earth options such as,
 * navigation, flyToSpeed, scroll wheel speed and so on.
 * 
 * @author Nick
 * 
 */
public class GEOptions extends JavaScriptObject {
	protected GEOptions() {
	}

    /**
     * Sets the map type to Earth or sky mode.
     * 
     * @param type The type of map to set
     */
    public final void setMapType(GEMapType type) {
        setMapType(GEPluginConstants.get().toJavaScript(type));
    }
    
	/**
	 * Sets the map type to Earth or sky mode.
	 * 
	 * @param type
	 */
	private final native void setMapType(int type) /*-{
		this.setMapType(type);
	}-*/;

	/**
	 * Speed of zoom when user rolls the mouse wheel. Default is 1. Set to a
	 * negative number to reverse the zoom direction.
	 * 
	 * @return
	 */
	public final native double getScrollWheelZoomSpeed() /*-{
		return this.getScrollWheelZoomSpeed();
	}-*/;

	/**
	 * Speed of zoom when user rolls the mouse wheel. Default is 1. Set to a
	 * negative number to reverse the zoom direction.
	 * 
	 * @param scrollWheelZoomSpeed
	 */
	public final native void setScrollWheelZoomSpeed(double scrollWheelZoomSpeed) /*-{
		this.setScrollWheelZoomSpeed(scrollWheelZoomSpeed);
	}-*/;

	/**
	 * Specifies the speed at which the camera moves (0 to 5.0). Set to
	 * SPEED_TELEPORT to immediately appear at selected destination.
	 * 
	 * @see GEPlugin.SPEED_TELEPORT
	 * @return
	 */
	public final native double getFlyToSpeed() /*-{
		return this.getFlyToSpeed();
	}-*/;

	/**
	 * Specifies the speed at which the camera moves (0 to 5.0). Set to
	 * SPEED_TELEPORT to immediately appear at selected destination.
	 * 
	 * @see GEPlugin.SPEED_TELEPORT
	 * @param flyToSpeed
	 */
	public final native void setFlyToSpeed(double flyToSpeed) /*-{
		this.setFlyToSpeed(flyToSpeed);
	}-*/;

	/**
	 * Show or hide the status bar. Disabled by default.
	 * 
	 * @return
	 */
	public final native boolean getStatusBarVisibility() /*-{
		return this.getStatusBarVisibility();
	}-*/;

	/**
	 * Show or hide the status bar. Disabled by default.
	 * 
	 * @param statusBarVisibility
	 */
	public final native void setStatusBarVisibility(boolean statusBarVisibility) /*-{
		this.setStatusBarVisibility(statusBarVisibility);
	}-*/;

	/**
	 * Show or hide the grid. Disabled by default.
	 * 
	 * @return
	 */
	public final native boolean getGridVisibility() /*-{
		return this.getGridVisibility();
	}-*/;

	/**
	 * Show or hide the grid. Disabled by default.
	 * 
	 * @param gridVisibility
	 */
	public final native void setGridVisibility(boolean gridVisibility) /*-{
		this.setGridVisibility(gridVisibility);
	}-*/;

	/**
	 * Show or hide the overview map. Disabled by default.
	 * 
	 * @return
	 */
	public final native boolean getOverviewMapVisibility() /*-{
		return this.getOverviewMapVisibility();
	}-*/;

	/**
	 * Show or hide the overview map. Disabled by default.
	 * 
	 * @param overviewMapVisibility
	 */
	public final native void setOverviewMapVisibility(
			boolean overviewMapVisibility) /*-{
		this.setOverviewMapVisibility(overviewMapVisibility);
	}-*/;

	/**
	 * Show or hide the scale legend. Disabled by default.
	 * 
	 * @return
	 */
	public final native boolean getScaleLegendVisibility() /*-{
		return this.getScaleLegendVisibility();
	}-*/;

	/**
	 * Show or hide the scale legend. Disabled by default.
	 * 
	 * @param scaleLegendVisibility
	 */
	public final native void setScaleLegendVisibility(
			boolean scaleLegendVisibility) /*-{
		this.setScaleLegendVisibility(scaleLegendVisibility);
	}-*/;

	/**
	 * Show or hide the blue atmosphere that appears around the perimeter of the
	 * Earth. On by default.
	 * 
	 * @return
	 */
	public final native boolean getAtmosphereVisibility() /*-{
		return this.getAtmosphereVisibility();
	}-*/;

	/**
	 * Show or hide the blue atmosphere that appears around the perimeter of the
	 * Earth. On by default.
	 * 
	 * @param atmosphereVisibility
	 */
	public final native void setAtmosphereVisibility(
			boolean atmosphereVisibility) /*-{
		this.setAtmosphereVisibility(atmosphereVisibility);
	}-*/;

	/**
	 * Enable or disable user panning and zooming of the map. Enabled by
	 * default.
	 * 
	 * Note: This also enables and disables keyboard navigation (arrow keys,
	 * page-up/page-down, etc).
	 * 
	 * @return
	 */
	public final native boolean getMouseNavigationEnabled() /*-{
		return this.getMouseNavigationEnabled();
	}-*/;

	/**
	 * Enable or disable user panning and zooming of the map. Enabled by
	 * default.
	 * 
	 * Note: This also enables and disables keyboard navigation (arrow keys,
	 * page-up/page-down, etc).
	 * 
	 * @param mouseNavigationEnabled
	 */
	public final native void setMouseNavigationEnabled(
			boolean mouseNavigationEnabled) /*-{
		this.setMouseNavigationEnabled(mouseNavigationEnabled);
	}-*/;

	/**
	 * Returns true if the animation of features as they are added or removed
	 * from the globe has been enabled.
	 * 
	 * @return
	 */
	public final native boolean getFadeInOutEnabled() /*-{
		return this.getFadeInOutEnabled();
	}-*/;

	/**
	 * Enable or disable the animation of a feature when it is added or removed
	 * from the Google Earth plugin. The animation consists of a slight change
	 * of scale. Default is true.
	 * 
	 * @param fadeInOutEnabled
	 */
	public final native void setFadeInOutEnabled(boolean fadeInOutEnabled) /*-{
		this.setFadeInOutEnabled(fadeInOutEnabled);
	}-*/;

	/**
	 * Returns true if display units are set to imperial units (feet and miles).
	 * False denotes metric units (meters and kilometers).
	 * 
	 * @return
	 */
	public final native boolean getUnitsFeetMiles() /*-{
		return this.getUnitsFeetMiles();
	}-*/;

	/**
	 * Set display units to imperial (feet and miles) or metric (meters and
	 * kilometers). This setting affects only the values displayed in the status
	 * bar and the scale bar. The values passed and returned with an object's
	 * getters and setters are always metric.
	 * 
	 * @param unitsFeetMiles
	 */
	public final native void setUnitsFeetMiles(boolean unitsFeetMiles) /*-{
		this.setUnitsFeetMiles(unitsFeetMiles);
	}-*/;

	/**
	 * Enables or disables building selection. If enabled, clicking a building
	 * will pop a feature balloon containing information from the Google 3D
	 * Warehouse database.
	 * 
	 * @param buildingSelectionEnabled
	 */
	public final native void setBuildingSelectionEnabled(
			boolean buildingSelectionEnabled) /*-{
		this.setBuildingSelectionEnabled(buildingSelectionEnabled);
	}-*/;

	/**
	 * Whether or not building selection is enabled.
	 * 
	 * @return
	 */
	public final native boolean getBuildingSelectionEnabled() /*-{
		return this.getBuildingSelectionEnabled();
	}-*/;

	/**
	 * Returns true if building highlighting is enabled.
	 * 
	 * @return
	 */
	public final native boolean getBuildingHighlightingEnabled() /*-{
		return this.getBuildingHighlightingEnabled();
	}-*/;

	/**
	 * Enables or disables building highlighting. When enabled, buildings will
	 * be highlighted when they are moused over.
	 * 
	 * @param enabled
	 */
	public final native void setBuildingHighlightingEnabled(
			boolean enabled) /*-{
		this.setBuildingHighlightingEnabled(enabled);
	}-*/;

	/**
	 * Returns the terrain exaggeration value. Valid values are in the range of
	 * 1.0 through 3.0.
	 * 
	 * @return
	 */
	public final native double getTerrainExaggeration() /*-{
		return this.getTerrainExaggeration();
	}-*/;

	/**
	 * Set the terrain exaggeration value. Valid values are in the range of 1.0
	 * through 3.0. Attempting to set outside of this range will result in the
	 * value being clamped.
	 * 
	 * @param terrainExaggeration
	 */
	public final native void setTerrainExaggeration(double terrainExaggeration) /*-{
		this.setTerrainExaggeration(terrainExaggeration);
	}-*/;

	/**
	 * When enabled, the view will change to 'ground level view' when the camera
	 * reaches ground level. This view provides pan and lookAt controls, but no
	 * zoom slider. The tilt will be set to 90, or parallel with level ground.
	 * 
	 * @param autoGroundLevelViewEnabled
	 */
	public final native void setAutoGroundLevelViewEnabled(
			boolean autoGroundLevelViewEnabled) /*-{
		this.setAutoGroundLevelViewEnabled(autoGroundLevelViewEnabled);
	}-*/;

	/**
	 * Whether automatic ground level view is enabled.
	 * @return
	 */
	public final native boolean getAutoGroundLevelViewEnabled() /*-{
		return this.getAutoGroundLevelViewEnabled();
	}-*/;
}
