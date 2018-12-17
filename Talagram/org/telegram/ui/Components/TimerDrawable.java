package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.ui.ActionBar.Theme;

public class TimerDrawable extends Drawable {
    private Paint linePaint;
    private Paint paint;
    private int time;
    private int timeHeight;
    private StaticLayout timeLayout;
    private TextPaint timePaint;
    private float timeWidth;

    public TimerDrawable(Context arg2) {
        super();
        this.timePaint = new TextPaint(1);
        this.paint = new Paint(1);
        this.linePaint = new Paint(1);
        this.timeWidth = 0f;
        this.timeHeight = 0;
        this.time = 0;
        this.timePaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.timePaint.setTextSize(((float)AndroidUtilities.dp(11f)));
        this.linePaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
        this.linePaint.setStyle(Paint$Style.STROKE);
    }

    public void draw(Canvas arg12) {
        int v0 = this.getIntrinsicWidth();
        int v1 = this.getIntrinsicHeight();
        float v3 = 9.5f;
        if(this.time == 0) {
            this.paint.setColor(Theme.getColor("chat_secretTimerBackground"));
            this.linePaint.setColor(Theme.getColor("chat_secretTimerText"));
            arg12.drawCircle(AndroidUtilities.dpf2(9f), AndroidUtilities.dpf2(9f), AndroidUtilities.dpf2(7.5f), this.paint);
            arg12.drawCircle(AndroidUtilities.dpf2(9f), AndroidUtilities.dpf2(9f), AndroidUtilities.dpf2(8f), this.linePaint);
            this.paint.setColor(Theme.getColor("chat_secretTimerText"));
            arg12.drawLine(((float)AndroidUtilities.dp(9f)), ((float)AndroidUtilities.dp(9f)), ((float)AndroidUtilities.dp(13f)), ((float)AndroidUtilities.dp(9f)), this.linePaint);
            arg12.drawLine(((float)AndroidUtilities.dp(9f)), ((float)AndroidUtilities.dp(5f)), ((float)AndroidUtilities.dp(9f)), ((float)AndroidUtilities.dp(v3)), this.linePaint);
            arg12.drawRect(AndroidUtilities.dpf2(7f), AndroidUtilities.dpf2(0f), AndroidUtilities.dpf2(11f), AndroidUtilities.dpf2(1.5f), this.paint);
        }
        else {
            this.paint.setColor(Theme.getColor("chat_secretTimerBackground"));
            this.timePaint.setColor(Theme.getColor("chat_secretTimerText"));
            arg12.drawCircle(((float)AndroidUtilities.dp(v3)), ((float)AndroidUtilities.dp(v3)), ((float)AndroidUtilities.dp(v3)), this.paint);
        }

        if(this.time != 0 && this.timeLayout != null) {
            int v2 = 0;
            if(AndroidUtilities.density == 3f) {
                v2 = -1;
            }

            double v3_1 = ((double)(v0 / 2));
            double v5 = Math.ceil(((double)(this.timeWidth / 2f)));
            Double.isNaN(v3_1);
            arg12.translate(((float)((((int)(v3_1 - v5))) + v2)), ((float)((v1 - this.timeHeight) / 2)));
            this.timeLayout.draw(arg12);
        }
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(19f);
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(19f);
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void setTime(int arg9) {
        String v9;
        this.time = arg9;
        int v1 = 2;
        int v2 = 60;
        if(this.time < 1 || this.time >= v2) {
            int v3 = 3600;
            if(this.time >= v2 && this.time < v3) {
                v9 = "" + arg9 / v2;
                if(v9.length() < v1) {
                    v0 = new StringBuilder();
                    v0.append(v9);
                    v9 = "m";
                    goto label_20;
                }
                else {
                    goto label_22;
                }
            }

            int v4 = 86400;
            if(this.time >= v3 && this.time < v4) {
                v9 = "" + arg9 / v2 / v2;
                if(v9.length() < v1) {
                    v0 = new StringBuilder();
                    v0.append(v9);
                    v9 = "h";
                    goto label_20;
                }
                else {
                    goto label_22;
                }
            }

            if(this.time >= v4 && this.time < 604800) {
                v9 = "" + arg9 / v2 / v2 / 24;
                if(v9.length() < v1) {
                    v0 = new StringBuilder();
                    v0.append(v9);
                    v9 = "d";
                    goto label_20;
                }
                else {
                    goto label_22;
                }
            }

            v9 = "" + arg9 / v2 / v2 / 24 / 7;
            if(v9.length() < v1) {
                v0 = new StringBuilder();
                v0.append(v9);
                v9 = "w";
            label_20:
                v0.append(v9);
                v9 = v0.toString();
                goto label_22;
            }

            if(v9.length() <= v1) {
                goto label_22;
            }

            v9 = "c";
        }
        else {
            v9 = "" + arg9;
            if(v9.length() < v1) {
                v0 = new StringBuilder();
                v0.append(v9);
                v9 = "s";
                goto label_20;
            }
        }

    label_22:
        String v1_1 = v9;
        this.timeWidth = this.timePaint.measureText(v1_1);
        try {
            this.timeLayout = new StaticLayout(((CharSequence)v1_1), this.timePaint, ((int)Math.ceil(((double)this.timeWidth))), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
            this.timeHeight = this.timeLayout.getHeight();
        }
        catch(Exception v9_1) {
            this.timeLayout = null;
            FileLog.e(((Throwable)v9_1));
        }

        this.invalidateSelf();
    }
}

