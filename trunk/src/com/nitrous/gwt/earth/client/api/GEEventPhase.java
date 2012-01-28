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

public enum GEEventPhase {
    EVENT_CAPTURING_PHASE, 
    EVENT_AT_TARGET_PHASE, 
    EVENT_BUBBLING_PHASE;
    
    static GEEventPhase toJava(int type, GEPlugin plugin) {
        if (type == getEventAtTargetPhase(plugin)) {
            return GEEventPhase.EVENT_AT_TARGET_PHASE;
        } else if (type == getEventBubblingPhase(plugin)) {
            return GEEventPhase.EVENT_BUBBLING_PHASE;
        } else if (type == getEventCapturingPhase(plugin)) {
            return GEEventPhase.EVENT_CAPTURING_PHASE;
        } else {
            throw new IllegalArgumentException("Unknown GEEventPhase: "+type);
        }
    }
    
    static int toJavaScript(GEEventPhase type, GEPlugin plugin) {
        switch (type) {
        case EVENT_AT_TARGET_PHASE:
            return getEventAtTargetPhase(plugin);
        case EVENT_BUBBLING_PHASE:
            return getEventBubblingPhase(plugin);
        case EVENT_CAPTURING_PHASE:
            return getEventCapturingPhase(plugin);
        default:
            throw new IllegalArgumentException("Unknown GEEventPhase: "+type);
        }
    }
    
    
    public static native int getEventCapturingPhase(GEPlugin plugin) /*-{
        return plugin.EVENT_CAPTURING_PHASE;
    }-*/;
    public static native int getEventAtTargetPhase(GEPlugin plugin) /*-{
        return plugin.EVENT_AT_TARGET_PHASE;
    }-*/;
    public static native int getEventBubblingPhase(GEPlugin plugin) /*-{
        return plugin.EVENT_BUBBLING_PHASE;
    }-*/;
    
}
