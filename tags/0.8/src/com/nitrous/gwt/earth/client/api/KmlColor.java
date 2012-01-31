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
 * The KmlColor object values are expressed in hexadecimal notation. The range
 * of values for any one color component is 0 to 255 (00 to ff). For alpha, 00
 * is fully transparent and ff is fully opaque. The order of expression is
 * aabbggrr, where aa=alpha (00 to ff); bb=blue (00 to ff); gg=green (00 to ff);
 * rr=red (00 to ff). For example, if you want to apply a blue color with 50
 * percent opacity to an overlay, you would specify the following when setting
 * color value: 7fff0000, where alpha=0x7f, blue=0xff, green=0x00, and red=0x00.
 * 
 * @author Nick
 * 
 */
public class KmlColor extends KmlObjectPartial {
    protected KmlColor() {
    }

    /**
     * Set the color of an object.
     * 
     * @param color
     *            color of the object
     */
    public final native void set(String color) /*-{
		this.set(color);
    }-*/;

    /**
     * 
     * @return Returns the color of an object.
     */
    public final native String get() /*-{
		return this.get();
    }-*/;

    /**
     * 
     * @return red numerical value
     */
    public final native int getR() /*-{
		return this.getR();
    }-*/;

    /**
     * 
     * @param r
     *            red numerical value
     */
    public final native void setR(int r) /*-{
		this.setR(r);
    }-*/;

    /**
     * 
     * @return green numerical value
     */
    public final native int getG() /*-{
		return this.getG();
    }-*/;

    /**
     * 
     * @param g
     *            green numerical value
     */
    public final native void setG(int g) /*-{
		this.setG(g);
    }-*/;

    /**
     * 
     * @return blue numerical value
     */
    public final native int getB() /*-{
		return this.getB();
    }-*/;

    /**
     * 
     * @param b blue numerical value
     */
    public final native void setB(int b) /*-{
		this.setB(b);
    }-*/;

    /**
     * 
     * @return opacity value
     */
    public final native int getA() /*-{
		return this.getA();
    }-*/;

    /**
     * 
     * @param a opacity value
     */
    public final native void setA(int a) /*-{
		this.setA(a);
    }-*/;

}
