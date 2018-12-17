package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;

public class SeekBar {
    public interface SeekBarDelegate {
        void onSeekBarDrag(float arg1);
    }

    private int backgroundColor;
    private int backgroundSelectedColor;
    private float bufferedProgress;
    private int cacheColor;
    private int circleColor;
    private SeekBarDelegate delegate;
    private int height;
    private int lineHeight;
    private static Paint paint;
    private boolean pressed;
    private int progressColor;
    private RectF rect;
    private boolean selected;
    private int thumbDX;
    private static int thumbWidth;
    private int thumbX;
    private int width;

    public SeekBar(Context arg2) {
        super();
        this.thumbX = 0;
        this.thumbDX = 0;
        this.pressed = false;
        this.rect = new RectF();
        this.lineHeight = AndroidUtilities.dp(2f);
        if(SeekBar.paint == null) {
            SeekBar.paint = new Paint(1);
            SeekBar.thumbWidth = AndroidUtilities.dp(24f);
        }
    }

    public void draw(Canvas arg8) {
        this.rect.set(((float)(SeekBar.thumbWidth / 2)), ((float)(this.height / 2 - this.lineHeight / 2)), ((float)(this.width - SeekBar.thumbWidth / 2)), ((float)(this.height / 2 + this.lineHeight / 2)));
        Paint v0 = SeekBar.paint;
        int v1 = this.selected ? this.backgroundSelectedColor : this.backgroundColor;
        v0.setColor(v1);
        arg8.drawRoundRect(this.rect, ((float)(SeekBar.thumbWidth / 2)), ((float)(SeekBar.thumbWidth / 2)), SeekBar.paint);
        if(this.bufferedProgress > 0f) {
            v0 = SeekBar.paint;
            v1 = this.selected ? this.backgroundSelectedColor : this.cacheColor;
            v0.setColor(v1);
            this.rect.set(((float)(SeekBar.thumbWidth / 2)), ((float)(this.height / 2 - this.lineHeight / 2)), (((float)(SeekBar.thumbWidth / 2))) + this.bufferedProgress * (((float)(this.width - SeekBar.thumbWidth))), ((float)(this.height / 2 + this.lineHeight / 2)));
            arg8.drawRoundRect(this.rect, ((float)(SeekBar.thumbWidth / 2)), ((float)(SeekBar.thumbWidth / 2)), SeekBar.paint);
        }

        this.rect.set(((float)(SeekBar.thumbWidth / 2)), ((float)(this.height / 2 - this.lineHeight / 2)), ((float)(SeekBar.thumbWidth / 2 + this.thumbX)), ((float)(this.height / 2 + this.lineHeight / 2)));
        SeekBar.paint.setColor(this.progressColor);
        arg8.drawRoundRect(this.rect, ((float)(SeekBar.thumbWidth / 2)), ((float)(SeekBar.thumbWidth / 2)), SeekBar.paint);
        SeekBar.paint.setColor(this.circleColor);
        float v0_1 = ((float)(this.thumbX + SeekBar.thumbWidth / 2));
        float v1_1 = ((float)(this.height / 2));
        float v2 = this.pressed ? 8f : 6f;
        arg8.drawCircle(v0_1, v1_1, ((float)AndroidUtilities.dp(v2)), SeekBar.paint);
    }

    public float getProgress() {
        return (((float)this.thumbX)) / (((float)(this.width - SeekBar.thumbWidth)));
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public boolean onTouch(int arg5, float arg6, float arg7) {
        int v0 = 2;
        if(arg5 == 0) {
            arg5 = (this.height - SeekBar.thumbWidth) / v0;
            if((((float)(this.thumbX - arg5))) <= arg6 && arg6 <= (((float)(this.thumbX + SeekBar.thumbWidth + arg5))) && arg7 >= 0f && arg7 <= (((float)this.height))) {
                this.pressed = true;
                this.thumbDX = ((int)(arg6 - (((float)this.thumbX))));
                return 1;
            }
        }
        else {
            if(arg5 != 1) {
                if(arg5 == 3) {
                }
                else if(arg5 != v0) {
                    return 0;
                }
                else if(this.pressed) {
                    this.thumbX = ((int)(arg6 - (((float)this.thumbDX))));
                    if(this.thumbX < 0) {
                        this.thumbX = 0;
                    }
                    else if(this.thumbX > this.width - SeekBar.thumbWidth) {
                        this.thumbX = this.width - SeekBar.thumbWidth;
                    }

                    return 1;
                }
                else {
                    return 0;
                }
            }

            if(!this.pressed) {
                return 0;
            }

            if(arg5 == 1 && this.delegate != null) {
                this.delegate.onSeekBarDrag((((float)this.thumbX)) / (((float)(this.width - SeekBar.thumbWidth))));
            }

            this.pressed = false;
            return 1;
        }

        return 0;
    }

    public void setBufferedProgress(float arg1) {
        this.bufferedProgress = arg1;
    }

    public void setColors(int arg1, int arg2, int arg3, int arg4, int arg5) {
        this.backgroundColor = arg1;
        this.cacheColor = arg2;
        this.circleColor = arg4;
        this.progressColor = arg3;
        this.backgroundSelectedColor = arg5;
    }

    public void setDelegate(SeekBarDelegate arg1) {
        this.delegate = arg1;
    }

    public void setLineHeight(int arg1) {
        this.lineHeight = arg1;
    }

    public void setProgress(float arg3) {
        int v3;
        this.thumbX = ((int)Math.ceil(((double)((((float)(this.width - SeekBar.thumbWidth))) * arg3))));
        if(this.thumbX < 0) {
            v3 = 0;
            goto label_12;
        }
        else if(this.thumbX > this.width - SeekBar.thumbWidth) {
            v3 = this.width - SeekBar.thumbWidth;
        label_12:
            this.thumbX = v3;
        }
    }

    public void setSelected(boolean arg1) {
        this.selected = arg1;
    }

    public void setSize(int arg1, int arg2) {
        this.width = arg1;
        this.height = arg2;
    }
}

