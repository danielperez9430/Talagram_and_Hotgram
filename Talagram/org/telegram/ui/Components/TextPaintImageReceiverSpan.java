package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint$FontMetricsInt;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import android.view.View;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$FileLocation;

public class TextPaintImageReceiverSpan extends ReplacementSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM;
    private int height;
    private ImageReceiver imageReceiver;
    protected final int mVerticalAlignment;
    private int width;

    public TextPaintImageReceiverSpan(View arg9, Document arg10, int arg11, int arg12) {
        super();
        this.mVerticalAlignment = 1;
        String v4 = String.format(Locale.US, "%d_%d", Integer.valueOf(arg11), Integer.valueOf(arg12));
        this.width = AndroidUtilities.dp(((float)arg11));
        this.height = AndroidUtilities.dp(((float)arg12));
        this.imageReceiver = new ImageReceiver(arg9);
        ImageReceiver v0 = this.imageReceiver;
        FileLocation v2 = arg10.thumb != null ? arg10.thumb.location : null;
        FileLocation v3 = v2;
        v0.setImage(arg10, v4, v3, v4, -1, null, 1);
    }

    public void draw(Canvas arg1, CharSequence arg2, int arg3, int arg4, float arg5, int arg6, int arg7, int arg8, Paint arg9) {
        arg1.save();
        arg8 -= this.height;
        if(this.mVerticalAlignment == 1) {
            arg8 -= arg9.getFontMetricsInt().descent;
        }

        this.imageReceiver.setImageCoords(((int)arg5), arg8, this.width, this.height);
        this.imageReceiver.draw(arg1);
        arg1.restore();
    }

    public int getSize(Paint arg1, CharSequence arg2, int arg3, int arg4, Paint$FontMetricsInt arg5) {
        if(arg5 != null) {
            arg5.ascent = -this.height;
            arg5.descent = 0;
            arg5.top = arg5.ascent;
            arg5.bottom = 0;
        }

        return this.width;
    }
}

