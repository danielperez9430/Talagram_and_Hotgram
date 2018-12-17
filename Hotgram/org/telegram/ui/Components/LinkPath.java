package org.telegram.ui.Components;

import android.graphics.Path$Direction;
import android.graphics.Path;
import android.text.StaticLayout;

public class LinkPath extends Path {
    private StaticLayout currentLayout;
    private int currentLine;
    private float heightOffset;
    private float lastTop;

    public LinkPath() {
        super();
        this.lastTop = -1f;
    }

    public void addRect(float arg8, float arg9, float arg10, float arg11, Path$Direction arg12) {
        float v3 = arg9 + this.heightOffset;
        arg11 += this.heightOffset;
        if(this.lastTop == -1f) {
            this.lastTop = v3;
        }
        else if(this.lastTop != v3) {
            this.lastTop = v3;
            ++this.currentLine;
        }

        arg9 = this.currentLayout.getLineRight(this.currentLine);
        float v0 = this.currentLayout.getLineLeft(this.currentLine);
        if(arg8 >= arg9) {
            return;
        }

        float v4 = arg10 > arg9 ? arg9 : arg10;
        float v2 = arg8 < v0 ? v0 : arg8;
        arg8 = arg11 != (((float)this.currentLayout.getHeight())) ? this.currentLayout.getSpacingAdd() : 0f;
        super.addRect(v2, v3, v4, arg11 - arg8, arg12);
    }

    public void setCurrentLayout(StaticLayout arg1, int arg2, float arg3) {
        this.currentLayout = arg1;
        this.currentLine = arg1.getLineForOffset(arg2);
        this.lastTop = -1f;
        this.heightOffset = arg3;
    }
}

