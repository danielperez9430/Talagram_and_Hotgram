package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

public class PhotoEditorSeekBar extends View {
    public interface PhotoEditorSeekBarDelegate {
        void onProgressChanged(int arg1, int arg2);
    }

    private PhotoEditorSeekBarDelegate delegate;
    private Paint innerPaint;
    private int maxValue;
    private int minValue;
    private Paint outerPaint;
    private boolean pressed;
    private float progress;
    private int thumbDX;
    private int thumbSize;

    public PhotoEditorSeekBar(Context arg2) {
        super(arg2);
        this.innerPaint = new Paint();
        this.outerPaint = new Paint(1);
        this.thumbSize = AndroidUtilities.dp(16f);
        this.thumbDX = 0;
        this.progress = 0f;
        this.pressed = false;
        this.innerPaint.setColor(-11711155);
        this.outerPaint.setColor(-1);
    }

    public int getProgress() {
        return ((int)((((float)this.minValue)) + this.progress * (((float)(this.maxValue - this.minValue)))));
    }

    protected void onDraw(Canvas arg18) {
        int v3;
        PhotoEditorSeekBar v0 = this;
        int v1 = (this.getMeasuredHeight() - v0.thumbSize) / 2;
        int v2 = ((int)((((float)(this.getMeasuredWidth() - v0.thumbSize))) * v0.progress));
        float v10 = 1f;
        arg18.drawRect(((float)(v0.thumbSize / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v10))), ((float)(this.getMeasuredWidth() - v0.thumbSize / 2)), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v10))), v0.innerPaint);
        if(v0.minValue == 0) {
            v3 = v0.thumbSize;
            goto label_37;
        }
        else if(v0.progress > 0.5f) {
            arg18.drawRect(((float)(this.getMeasuredWidth() / 2 - AndroidUtilities.dp(v10))), ((float)((this.getMeasuredHeight() - v0.thumbSize) / 2)), ((float)(this.getMeasuredWidth() / 2)), ((float)((this.getMeasuredHeight() + v0.thumbSize) / 2)), v0.outerPaint);
            v3 = this.getMeasuredWidth();
        label_37:
            arg18.drawRect(((float)(v3 / 2)), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v10))), ((float)v2), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v10))), v0.outerPaint);
        }
        else {
            arg18.drawRect(((float)(this.getMeasuredWidth() / 2)), ((float)((this.getMeasuredHeight() - v0.thumbSize) / 2)), ((float)(this.getMeasuredWidth() / 2 + AndroidUtilities.dp(v10))), ((float)((this.getMeasuredHeight() + v0.thumbSize) / 2)), v0.outerPaint);
            arg18.drawRect(((float)v2), ((float)(this.getMeasuredHeight() / 2 - AndroidUtilities.dp(v10))), ((float)(this.getMeasuredWidth() / 2)), ((float)(this.getMeasuredHeight() / 2 + AndroidUtilities.dp(v10))), v0.outerPaint);
        }

        arg18.drawCircle(((float)(v2 + v0.thumbSize / 2)), ((float)(v1 + v0.thumbSize / 2)), ((float)(v0.thumbSize / 2)), v0.outerPaint);
    }

    public boolean onTouchEvent(MotionEvent arg9) {
        float v9;
        if(arg9 == null) {
            return 0;
        }

        float v1 = arg9.getX();
        float v2 = arg9.getY();
        float v3 = ((float)(((int)((((float)(this.getMeasuredWidth() - this.thumbSize))) * this.progress))));
        int v5 = 2;
        float v6 = 0f;
        if(arg9.getAction() == 0) {
            v9 = ((float)((this.getMeasuredHeight() - this.thumbSize) / v5));
            if(v3 - v9 <= v1 && v1 <= (((float)this.thumbSize)) + v3 + v9 && v2 >= 0f && v2 <= (((float)this.getMeasuredHeight()))) {
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
                    else if(v9 > (((float)(this.getMeasuredWidth() - this.thumbSize)))) {
                        v6 = ((float)(this.getMeasuredWidth() - this.thumbSize));
                    }
                    else {
                        v6 = v9;
                    }

                    this.progress = v6 / (((float)(this.getMeasuredWidth() - this.thumbSize)));
                    if(this.delegate != null) {
                        this.delegate.onProgressChanged(this.getTag().intValue(), this.getProgress());
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

            this.pressed = false;
        label_40:
            this.invalidate();
            return 1;
        }

        return 0;
    }

    public void setDelegate(PhotoEditorSeekBarDelegate arg1) {
        this.delegate = arg1;
    }

    public void setMinMax(int arg1, int arg2) {
        this.minValue = arg1;
        this.maxValue = arg2;
    }

    public void setProgress(int arg3, boolean arg4) {
        if(arg3 < this.minValue) {
            arg3 = this.minValue;
        }
        else if(arg3 > this.maxValue) {
            arg3 = this.maxValue;
        }

        this.progress = (((float)(arg3 - this.minValue))) / (((float)(this.maxValue - this.minValue)));
        this.invalidate();
        if((arg4) && this.delegate != null) {
            this.delegate.onProgressChanged(this.getTag().intValue(), this.getProgress());
        }
    }

    public void setProgress(int arg2) {
        this.setProgress(arg2, true);
    }
}

