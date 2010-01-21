/*
 * @(#)AbstractColorWheelImageProducer.java
 * 
 * Copyright (c) 2010 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 * 
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.color;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;

/**
 * AbstractColorWheelImageProducer.
 *
 * @author Werner Randelshofer
 * @version 1.0 2010-01-20 Created.
 */
public abstract class AbstractColorWheelImageProducer extends MemoryImageSource {

    protected int[] pixels;
    protected int w, h;
    protected ColorSystem colorSystem;
    protected int radialIndex = 1;
    protected int angularIndex = 0;
    protected int verticalIndex = 2;
    protected boolean isPixelsValid = false;
    protected float verticalValue = 1f;
    protected boolean isLookupValid = false;

    public AbstractColorWheelImageProducer(ColorSystem sys, int w, int h) {
        super(w, h, null, 0, w);
        this.colorSystem = sys;
        pixels = new int[w * h];
        this.w = w;
        this.h = h;
        this.colorSystem = sys;
        setAnimated(true);

        newPixels(pixels, ColorModel.getRGBdefault(), 0, w);
    }

    public void setRadialComponentIndex(int newValue) {
        radialIndex = newValue;
        isPixelsValid = false;
    }

    public void setAngularComponentIndex(int newValue) {
        angularIndex = newValue;
        isPixelsValid = false;
    }

    public void setVerticalComponentIndex(int newValue) {
        verticalIndex = newValue;
        isPixelsValid = false;
    }

    public void setVerticalValue(float newValue) {
        isPixelsValid = isPixelsValid && verticalValue == newValue;
        verticalValue = newValue;
    }

    public boolean needsGeneration() {
        return !isPixelsValid;
    }

    public void regenerateColorWheel() {
        if (!isPixelsValid) {
            generateColorWheel();
        }
    }

    public int getRadius() {
        return Math.min(w, h) / 2 - 2;
    }

    protected abstract void generateColorWheel();

    public abstract Point getColorLocation(Color c);

    public abstract Point getColorLocation(float[] components);

    public abstract float[] getColorAt(int x, int y);

    public abstract Point getColorLocation(CompositeColor c);
}