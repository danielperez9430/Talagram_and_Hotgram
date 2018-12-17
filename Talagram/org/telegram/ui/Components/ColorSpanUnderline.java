package org.telegram.ui.Components;

import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

public class ColorSpanUnderline extends ForegroundColorSpan {
    public ColorSpanUnderline(int arg1) {
        super(arg1);
    }

    public void updateDrawState(TextPaint arg2) {
        super.updateDrawState(arg2);
        arg2.setUnderlineText(true);
    }
}

