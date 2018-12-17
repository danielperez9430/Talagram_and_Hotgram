package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;

public class LetterDrawable extends Drawable {
    private static TextPaint namePaint;
    public static Paint paint;
    private StringBuilder stringBuilder;
    private float textHeight;
    private StaticLayout textLayout;
    private float textLeft;
    private float textWidth;

    static {
        LetterDrawable.paint = new Paint();
    }

    public LetterDrawable() {
        super();
        this.stringBuilder = new StringBuilder(5);
        if(LetterDrawable.namePaint == null) {
            LetterDrawable.paint.setColor(Theme.getColor("sharedMedia_linkPlaceholder"));
            LetterDrawable.namePaint = new TextPaint(1);
            LetterDrawable.namePaint.setColor(Theme.getColor("sharedMedia_linkPlaceholderText"));
        }

        LetterDrawable.namePaint.setTextSize(((float)AndroidUtilities.dp(28f)));
    }

    public void draw(Canvas arg10) {
        Rect v0 = this.getBounds();
        if(v0 == null) {
            return;
        }

        int v1 = v0.width();
        arg10.save();
        arg10.drawRect(((float)v0.left), ((float)v0.top), ((float)v0.right), ((float)v0.bottom), LetterDrawable.paint);
        if(this.textLayout != null) {
            float v1_1 = ((float)v1);
            arg10.translate((((float)v0.left)) + (v1_1 - this.textWidth) / 2f - this.textLeft, (((float)v0.top)) + (v1_1 - this.textHeight) / 2f);
            this.textLayout.draw(arg10);
        }

        arg10.restore();
    }

    public int getIntrinsicHeight() {
        return 0;
    }

    public int getIntrinsicWidth() {
        return 0;
    }

    public int getOpacity() {
        return -2;
    }

    public void setAlpha(int arg1) {
    }

    public void setBackgroundColor(int arg2) {
        LetterDrawable.paint.setColor(arg2);
    }

    public void setColor(int arg2) {
        LetterDrawable.namePaint.setColor(arg2);
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setTitle(String arg11) {
        this.stringBuilder.setLength(0);
        if(arg11 != null && arg11.length() > 0) {
            this.stringBuilder.append(arg11.substring(0, 1));
        }

        if(this.stringBuilder.length() > 0) {
            String v3 = this.stringBuilder.toString().toUpperCase();
            try {
                this.textLayout = new StaticLayout(((CharSequence)v3), LetterDrawable.namePaint, AndroidUtilities.dp(100f), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                if(this.textLayout.getLineCount() <= 0) {
                    return;
                }

                this.textLeft = this.textLayout.getLineLeft(0);
                this.textWidth = this.textLayout.getLineWidth(0);
                this.textHeight = ((float)this.textLayout.getLineBottom(0));
            }
            catch(Exception v11) {
                FileLog.e(((Throwable)v11));
            }
        }
        else {
            this.textLayout = null;
        }
    }
}

