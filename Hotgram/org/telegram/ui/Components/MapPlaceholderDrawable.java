package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;

public class MapPlaceholderDrawable extends Drawable {
    private Paint linePaint;
    private Paint paint;

    public MapPlaceholderDrawable() {
        super();
        this.paint = new Paint();
        this.paint.setColor(-2172970);
        this.linePaint = new Paint();
        this.linePaint.setColor(-3752002);
        this.linePaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
    }

    public void draw(Canvas arg15) {
        arg15.drawRect(this.getBounds(), this.paint);
        int v0 = AndroidUtilities.dp(9f);
        int v1 = this.getBounds().width() / v0;
        int v2 = this.getBounds().height() / v0;
        int v3 = this.getBounds().left;
        int v4 = this.getBounds().top;
        int v5 = 0;
        int v6 = 0;
        while(v6 < v1) {
            ++v6;
            float v11 = ((float)(v0 * v6 + v3));
            arg15.drawLine(v11, ((float)v4), v11, ((float)(this.getBounds().height() + v4)), this.linePaint);
        }

        while(v5 < v2) {
            ++v5;
            float v10 = ((float)(v0 * v5 + v4));
            arg15.drawLine(((float)v3), v10, ((float)(this.getBounds().width() + v3)), v10, this.linePaint);
        }
    }

    public int getIntrinsicHeight() {
        return 0;
    }

    public int getIntrinsicWidth() {
        return 0;
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }
}

