package org.telegram.ui.Components;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;

public class URLSpanMono extends MetricAffectingSpan {
    private int currentEnd;
    private CharSequence currentMessage;
    private int currentStart;
    private byte currentType;

    public URLSpanMono(CharSequence arg1, int arg2, int arg3, byte arg4) {
        super();
        this.currentMessage = arg1;
        this.currentStart = arg2;
        this.currentEnd = arg3;
        this.currentType = arg4;
    }

    public void copyToClipboard() {
        AndroidUtilities.addToClipboard(this.currentMessage.subSequence(this.currentStart, this.currentEnd).toString());
    }

    public void updateDrawState(TextPaint arg4) {
        int v0;
        arg4.setTextSize(((float)AndroidUtilities.dp(((float)(SharedConfig.fontSize - 1)))));
        arg4.setTypeface(Typeface.MONOSPACE);
        arg4.setUnderlineText(false);
        if(this.currentType == 2) {
            v0 = -1;
        }
        else {
            String v0_1 = this.currentType == 1 ? "chat_messageTextOut" : "chat_messageTextIn";
            v0 = Theme.getColor(v0_1);
        }

        arg4.setColor(v0);
    }

    public void updateMeasureState(TextPaint arg2) {
        arg2.setTypeface(Typeface.MONOSPACE);
        arg2.setTextSize(((float)AndroidUtilities.dp(((float)(SharedConfig.fontSize - 1)))));
        arg2.setFlags(arg2.getFlags() | 128);
    }
}

