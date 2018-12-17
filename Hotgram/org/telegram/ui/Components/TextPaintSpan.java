package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TextPaintSpan extends MetricAffectingSpan {
    private int color;
    private TextPaint textPaint;
    private int textSize;

    public TextPaintSpan(TextPaint arg1) {
        super();
        this.textPaint = arg1;
    }

    public void updateDrawState(TextPaint arg2) {
        arg2.setColor(this.textPaint.getColor());
        arg2.setTypeface(this.textPaint.getTypeface());
        arg2.setFlags(this.textPaint.getFlags());
    }

    public void updateMeasureState(TextPaint arg2) {
        arg2.setColor(this.textPaint.getColor());
        arg2.setTypeface(this.textPaint.getTypeface());
        arg2.setFlags(this.textPaint.getFlags());
    }
}

