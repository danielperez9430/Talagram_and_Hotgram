package org.telegram.ui.Components;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import org.telegram.messenger.AndroidUtilities;

public class TypefaceSpan extends MetricAffectingSpan {
    private int color;
    private int textSize;
    private Typeface typeface;

    public TypefaceSpan(Typeface arg1) {
        super();
        this.typeface = arg1;
    }

    public TypefaceSpan(Typeface arg1, int arg2) {
        super();
        this.typeface = arg1;
        this.textSize = arg2;
    }

    public TypefaceSpan(Typeface arg1, int arg2, int arg3) {
        super();
        this.typeface = arg1;
        this.textSize = arg2;
        this.color = arg3;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public boolean isBold() {
        boolean v0 = this.typeface == AndroidUtilities.getTypeface("fonts/rmedium.ttf") ? true : false;
        return v0;
    }

    public boolean isItalic() {
        boolean v0 = this.typeface == AndroidUtilities.getTypeface("fonts/ritalic.ttf") ? true : false;
        return v0;
    }

    public boolean isMono() {
        boolean v0 = this.typeface == Typeface.MONOSPACE ? true : false;
        return v0;
    }

    public void setColor(int arg1) {
        this.color = arg1;
    }

    public void updateDrawState(TextPaint arg2) {
        if(this.typeface != null) {
            arg2.setTypeface(this.typeface);
        }

        if(this.textSize != 0) {
            arg2.setTextSize(((float)this.textSize));
        }

        if(this.color != 0) {
            arg2.setColor(this.color);
        }

        arg2.setFlags(arg2.getFlags() | 128);
    }

    public void updateMeasureState(TextPaint arg2) {
        if(this.typeface != null) {
            arg2.setTypeface(this.typeface);
        }

        if(this.textSize != 0) {
            arg2.setTextSize(((float)this.textSize));
        }

        arg2.setFlags(arg2.getFlags() | 128);
    }
}

