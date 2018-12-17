package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

public class VideoSeekBarView extends View {
    public interface SeekBarDelegate {
        void onSeekBarDrag(float arg1);
    }

    private SeekBarDelegate delegate;
    private Paint paint;
    private Paint paint2;
    private boolean pressed;
    private float progress;
    private int thumbDX;
    private int thumbHeight;
    private int thumbWidth;

    public VideoSeekBarView(Context arg2) {
        super(arg2);
        this.paint = new Paint();
        this.paint2 = new Paint(1);
        this.thumbWidth = AndroidUtilities.dp(12f);
        this.thumbHeight = AndroidUtilities.dp(12f);
        this.thumbDX = 0;
        this.progress = 0f;
        this.pressed = false;
        this.paint.setColor(-10724260);
        this.paint2.setColor(-1);
    }

    public float getProgress() {
        return this.progress;
    }

    protected void onDraw(Canvas arg10) {
        int v0 = (this.getMeasuredHeight() - this.thumbHeight) / 2;
        int v1 = ((int)((((float)(this.getMeasuredWidth() - this.thumbWidth))) * this.progress));
        arg10.drawRect(((float)(this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(1f))), ((float)(this.getMeasuredWidth() - this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(1f))), this.paint);
        arg10.drawCircle(((float)(v1 + this.thumbWidth / 2)), ((float)(v0 + this.thumbHeight / 2)), ((float)(this.thumbWidth / 2)), this.paint2);
    }

    public boolean onTouchEvent(MotionEvent arg9) {
        float v9;
        if(arg9 == null) {
            return 0;
        }

        float v1 = arg9.getX();
        float v2 = arg9.getY();
        float v3 = ((float)(((int)((((float)(this.getMeasuredWidth() - this.thumbWidth))) * this.progress))));
        int v5 = 2;
        float v6 = 0f;
        if(arg9.getAction() == 0) {
            v9 = ((float)((this.getMeasuredHeight() - this.thumbWidth) / v5));
            if(v3 - v9 <= v1 && v1 <= (((float)this.thumbWidth)) + v3 + v9 && v2 >= 0f && v2 <= (((float)this.getMeasuredHeight()))) {
                this.pressed = true;
                this.thumbDX = ((int)(v1 - v3));
                this.getParent().requestDisallowInterceptTouchEvent(true);
                goto label_40;
            }
        }
        else {
            if(arg9.getAction() != 1) {
                if(arg9.getAction() == 3) {
                }
                else if(arg9.getAction() != v5) {
                    return 0;
                }
                else if(this.pressed) {
                    v9 = ((float)(((int)(v1 - (((float)this.thumbDX))))));
                    if(v9 < 0f) {
                    }
                    else if(v9 > (((float)(this.getMeasuredWidth() - this.thumbWidth)))) {
                        v6 = ((float)(this.getMeasuredWidth() - this.thumbWidth));
                    }
                    else {
                        v6 = v9;
                    }

                    this.progress = v6 / (((float)(this.getMeasuredWidth() - this.thumbWidth)));
                    goto label_40;
                }
                else {
                    return 0;
                }
            }

            if(!this.pressed) {
                return 0;
            }

            if(arg9.getAction() == 1 && this.delegate != null) {
                this.delegate.onSeekBarDrag(v3 / (((float)(this.getMeasuredWidth() - this.thumbWidth))));
            }

            this.pressed = false;
        label_40:
            this.invalidate();
            return 1;
        }

        return 0;
    }

    public void setDelegate(SeekBarDelegate arg1) {
        this.delegate = arg1;
    }

    public void setProgress(float arg4) {
        // Method was not decompiled
    }
}

