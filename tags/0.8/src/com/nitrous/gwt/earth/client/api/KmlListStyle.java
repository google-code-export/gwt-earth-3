/*
 * Copyright 2012 Nick Kerr
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


/**
 * Specifies how a feature is displayed in the list view.
 * 
 * @author Nick
 * 
 */
public class KmlListStyle extends KmlObject {
    protected KmlListStyle() {
    }

    /**
     * 
     * @return Background color for the Snippet.
     */
    public native final KmlColor getBgColor()/*-{
		return this.getBgColor();
    }-*/;

    /**
     * 
     * @return Maximum number of lines of text for the Snippet.
     */
    public native final int getMaxSnippetLines()/*-{
		return this.getMaxSnippetLines();
    }-*/;

    /**
     * 
     * @param maxSnippetLines
     *            Maximum number of lines of text for the Snippet.
     */
    public native final void setMaxSnippetLines(int maxSnippetLines)/*-{
		this.setMaxSnippetLines(maxSnippetLines);
    }-*/;

    /**
     * @return Specifies how a feature should be displayed in a list view.
     */
    public final KmlListItemType getListItemType(){
        return GEPluginConstants.get().toKmlListItemType(getListItemTypeNative());
    }
    
    /**
     * @return Specifies how a feature should be displayed in a list view.
     */
    private native final int getListItemTypeNative()/*-{
        return this.getListItemType();
    }-*/;
}
