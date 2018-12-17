package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class SeekBarView extends FrameLayout {
    public interface SeekBarViewDelegate {
        void onSeekBarDrag(float arg1);
    }

    private float bufferedProgress;
    private SeekBarViewDelegate delegate;
    private Paint innerPaint1;
    private Paint outerPaint1;
    private boolean pressed;
    private float progressToSet;
    private boolean reportChanges;
    private int thumbDX;
    private int thumbHeight;
    private int thumbWidth;
    private int thumbX;

    public SeekBarView(Context arg3) {
        super(arg3);
        this.setWillNotDraw(false);
        this.innerPaint1 = new Paint(1);
        this.innerPaint1.setColor(Theme.getColor("player_progressBackground"));
        this.outerPaint1 = new Paint(1);
        this.outerPaint1.setColor(Theme.getColor("player_progress"));
        this.thumbWidth = AndroidUtilities.dp(24f);
        this.thumbHeight = AndroidUtilities.dp(24f);
    }

    public boolean isDragging() {
        return this.pressed;
    }

    protected void onDraw(Canvas arg10) {
        int v0 = (this.getMeasuredHeight() - this.thumbHeight) / 2;
        float v8 = 1f;
        arg10.drawRect(((float)(this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v8))), ((float)(this.getMeasuredWidth() - this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v8))), this.innerPaint1);
        if(this.bufferedProgress > 0f) {
            arg10.drawRect(((float)(this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v8))), (((float)(this.thumbWidth / 2))) + this.bufferedProgress * (((float)(this.getMeasuredWidth() - this.thumbWidth))), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v8))), this.innerPaint1);
        }

        arg10.drawRect(((float)(this.thumbWidth / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v8))), ((float)(this.thumbWidth / 2 + this.thumbX)), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v8))), this.outerPaint1);
        float v1 = ((float)(this.thumbX + this.thumbWidth / 2));
        float v0_1 = ((float)(v0 + this.thumbHeight / 2));
        float v2 = this.pressed ? 8f : 6f;
        arg10.drawCircle(v1, v0_1, ((float)AndroidUtilities.dp(v2)), this.outerPaint1);
    }

    public boolean onInterceptTouchEvent(MotionEvent arg1) {
        return this.onTouch(arg1);
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(arg1, arg2);
        if(this.progressToSet >= 0f && this.getMeasuredWidth() > 0) {
            this.setProgress(this.progressToSet);
            this.progressToSet = -1f;
        }
    }

    boolean onTouch(MotionEvent arg8) {
        int v1 = 2;
        if(arg8.getAction() == 0) {
            this.getParent().requestDisallowInterceptTouchEvent(true);
            int v0 = (this.getMeasuredHeight() - this.thumbWidth) / v1;
            if(arg8.getY() >= 0f && arg8.getY() <= (((float)this.getMeasuredHeight()))) {
                if((((float)(this.thumbX - v0))) > arg8.getX() || arg8.getX() > (((float)(this.thumbX + this.thumbWidth + v0)))) {
                    this.thumbX = (((int)arg8.getX())) - this.thumbWidth / v1;
                    if(this.thumbX < 0) {
                        this.thumbX = 0;
                    }
                    else if(this.thumbX > this.getMeasuredWidth() - this.thumbWidth) {
                        this.thumbX = this.getMeasuredWidth() - this.thumbWidth;
                    }
                }

                this.thumbDX = ((int)(arg8.getX() - (((float)this.thumbX))));
                this.pressed = true;
                goto label_56;
            }
        }
        else {
            if(arg8.getAction() != 1) {
                if(arg8.getAction() == 3) {
                }
                else if(arg8.getAction() != v1) {
                    return 0;
                }
                else if(this.pressed) {
                    this.thumbX = ((int)(arg8.getX() - (((float)this.thumbDX))));
                    if(this.thumbX < 0) {
                        this.thumbX = 0;
                    }
                    else if(this.thumbX > this.getMeasuredWidth() - this.thumbWidth) {
                        this.thumbX = this.getMeasuredWidth() - this.thumbWidth;
                    }

                    if(this.reportChanges) {
                        this.delegate.onSeekBarDrag((((float)this.thumbX)) / (((float)(this.getMeasuredWidth() - this.thumbWidth))));
                    }

                    this.invalidate();
                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(!this.pressed) {
                return 0;
            }

            if(arg8.getAction() == 1) {
                this.delegate.onSeekBarDrag((((float)this.thumbX)) / (((float)(this.getMeasuredWidth() - this.thumbWidth))));
            }

            this.pressed = false;
        label_56:
            this.invalidate();
            return 1;
        }

        return 0;
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        return this.onTouch(arg1);
    }

    public void setBufferedProgress(float arg1) {
        this.bufferedProgress = arg1;
    }

    public void setColors(int arg2, int arg3) {
        this.innerPaint1.setColor(arg2);
        this.outerPaint1.setColor(arg3);
    }

    public void setDelegate(SeekBarViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setInnerColor(int arg2) {
        this.innerPaint1.setColor(arg2);
    }

    public void setOuterColor(int arg2) {
        this.outerPaint1.setColor(arg2);
    }

    public void setProgress(float arg3) {
        if(this.getMeasuredWidth() == 0) {
            this.progressToSet = arg3;
            return;
        }

        this.progressToSet = -1f;
        int v3 = ((int)Math.ceil(((double)((((float)(this.getMeasuredWidth() - this.thumbWidth))) * arg3))));
        if(this.thumbX != v3) {
            this.thumbX = v3;
            if(this.thumbX < 0) {
                v3 = 0;
                goto label_20;
            }
            else if(this.thumbX > this.getMeasuredWidth() - this.thumbWidth) {
                v3 = this.getMeasuredWidth() - this.thumbWidth;
            label_20:
                this.thumbX = v3;
            }

            this.invalidate();
        }
    }

    public void setReportChanges(boolean arg1) {
        this.reportChanges = arg1;
    }
}

