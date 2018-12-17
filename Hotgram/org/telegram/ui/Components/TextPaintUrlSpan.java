package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TextPaintUrlSpan extends MetricAffectingSpan {
    private int color;
    private String currentUrl;
    private TextPaint textPaint;
    private int textSize;

    public TextPaintUrlSpan(TextPaint arg1, String arg2) {
        super();
        this.textPaint = arg1;
        this.currentUrl = arg2;
    }

    public String getUrl() {
        return this.currentUrl;
    }

    public void updateDrawState(TextPaint arg2) {
        if(this.textPaint != null) {
            arg2.setColor(this.textPaint.getColor());
            arg2.setTypeface(this.textPaint.getTypeface());
            arg2.setFlags(this.textPaint.getFlags());
        }
    }

    public void updateMeasureState(TextPaint arg2) {
        if(this.textPaint != null) {
            arg2.setColor(this.textPaint.getColor());
            arg2.setTypeface(this.textPaint.getTypeface());
            arg2.setFlags(this.textPaint.getFlags());
        }
    }
}

