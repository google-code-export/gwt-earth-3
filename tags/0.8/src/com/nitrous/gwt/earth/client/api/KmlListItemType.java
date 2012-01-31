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


public enum KmlListItemType {
    /**
     * The feature's visibility is tied to its list item's checkbox state.
     */
    LIST_ITEM_CHECK,

    /**
     * When specified for a folder, document or network link, prevents all items
     * from being made visible at once, that is, the user can turn all children
     * off but cannot turn them all on at the same time. This setting is useful
     * for containers or network links containing large amounts of data.
     */
    LIST_ITEM_CHECK_OFF_ONLY,

    /**
     * Use a normal checkbox for visibility but do not display children in a
     * list view. The item's checkbox should allows the user to toggle
     * visibility of the child objects in the viewport.
     */
    LIST_ITEM_CHECK_HIDE_CHILDREN,

    /**
     * When specified for a container (a folder or a document), only one of the
     * container's items should be visible at a time.
     */
    LIST_ITEM_RADIO_FOLDER;

    public static native int getCheck(GEPlugin plugin) /*-{
		return plugin.LIST_ITEM_CHECK;
    }-*/;

    public static native int getCheckOffOnly(GEPlugin plugin) /*-{
		return plugin.LIST_ITEM_CHECK_OFF_ONLY;
    }-*/;

    public static native int getCheckHideChildren(GEPlugin plugin) /*-{
		return plugin.LIST_ITEM_CHECK_HIDE_CHILDREN;
    }-*/;

    public static native int getRadioFolder(GEPlugin plugin) /*-{
		return plugin.LIST_ITEM_RADIO_FOLDER;
    }-*/;

    static KmlListItemType toJava(int item, GEPlugin plugin) {
        if (item == getCheck(plugin)) {
            return LIST_ITEM_CHECK;
        } else if (item == getCheckOffOnly(plugin)) {
            return LIST_ITEM_CHECK_OFF_ONLY;
        } else if (item == getCheckHideChildren(plugin)) {
            return LIST_ITEM_CHECK_HIDE_CHILDREN;
        } else if (item == getRadioFolder(plugin)) {
            return LIST_ITEM_RADIO_FOLDER;
        } else {
            throw new IllegalArgumentException("Unsupported KmlListItemType: "+item);
        }
    }
    static int toJavaScript(KmlListItemType type, GEPlugin plugin) {
        switch (type) {
        case LIST_ITEM_CHECK:
            return getCheck(plugin);
        case LIST_ITEM_CHECK_HIDE_CHILDREN:
            return getCheckHideChildren(plugin);
        case LIST_ITEM_CHECK_OFF_ONLY:
            return getCheckOffOnly(plugin);
        case LIST_ITEM_RADIO_FOLDER:
            return getRadioFolder(plugin);
        default:
            throw new IllegalArgumentException("Unsupported KmlListItemType: "+type);
        }
    }
}
