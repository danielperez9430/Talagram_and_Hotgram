package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class RoundVideoPlayingDrawable extends Drawable {
    private long lastUpdateTime;
    private Paint paint;
    private View parentView;
    private float progress1;
    private int progress1Direction;
    private float progress2;
    private int progress2Direction;
    private float progress3;
    private int progress3Direction;
    private boolean started;

    public RoundVideoPlayingDrawable(View arg3) {
        super();
        this.lastUpdateTime = 0;
        this.started = false;
        this.paint = new Paint(1);
        this.progress1 = 0.47f;
        this.progress2 = 0f;
        this.progress3 = 0.32f;
        this.progress1Direction = 1;
        this.progress2Direction = 1;
        this.progress3Direction = 1;
        this.parentView = arg3;
    }

    public void draw(Canvas arg20) {
        RoundVideoPlayingDrawable v0 = this;
        v0.paint.setColor(Theme.getColor("chat_mediaTimeText"));
        int v1 = this.getBounds().left;
        int v2 = this.getBounds().top;
        int v3;
        for(v3 = 0; v3 < 3; ++v3) {
            arg20.drawRect(((float)(AndroidUtilities.dp(2f) + v1)), ((float)(AndroidUtilities.dp(v0.progress1 * 7f + 2f) + v2)), ((float)(AndroidUtilities.dp(4f) + v1)), ((float)(AndroidUtilities.dp(10f) + v2)), v0.paint);
            arg20.drawRect(((float)(AndroidUtilities.dp(5f) + v1)), ((float)(AndroidUtilities.dp(v0.progress2 * 7f + 2f) + v2)), ((float)(AndroidUtilities.dp(7f) + v1)), ((float)(AndroidUtilities.dp(10f) + v2)), v0.paint);
            arg20.drawRect(((float)(AndroidUtilities.dp(8f) + v1)), ((float)(AndroidUtilities.dp(v0.progress3 * 7f + 2f) + v2)), ((float)(AndroidUtilities.dp(10f) + v1)), ((float)(AndroidUtilities.dp(10f) + v2)), v0.paint);
        }

        if(v0.started) {
            this.update();
        }
    }

    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(12f);
    }

    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(12f);
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int arg1) {
    }

    public void setColorFilter(ColorFilter arg1) {
    }

    public void start() {
        if(this.started) {
            return;
        }

        this.lastUpdateTime = System.currentTimeMillis();
        this.started = true;
        this.parentView.invalidate();
    }

    public void stop() {
        if(!this.started) {
            return;
        }

        this.started = false;
    }

    private void update() {
        // Method was not decompiled
    }
}

