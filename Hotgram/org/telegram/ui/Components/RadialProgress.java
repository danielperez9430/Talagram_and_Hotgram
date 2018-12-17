package org.telegram.ui.Components;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.ActionBar.Theme;

public class RadialProgress {
    class CheckDrawable extends Drawable {
        private Paint paint;
        private float progress;

        public CheckDrawable(RadialProgress arg2) {
            RadialProgress.this = arg2;
            super();
            this.paint = new Paint(1);
            this.paint.setStyle(Paint$Style.STROKE);
            this.paint.setStrokeWidth(((float)AndroidUtilities.dp(3f)));
            this.paint.setStrokeCap(Paint$Cap.ROUND);
            this.paint.setColor(-1);
        }

        public void draw(Canvas arg15) {
            int v0 = this.getBounds().centerX() - AndroidUtilities.dp(12f);
            float v2 = 6f;
            int v1 = this.getBounds().centerY() - AndroidUtilities.dp(v2);
            float v4 = 1f;
            if(this.progress != v4) {
                v4 = RadialProgress.decelerateInterpolator.getInterpolation(this.progress);
            }

            arg15.drawLine(((float)(AndroidUtilities.dp(7f) + v0)), ((float)((((int)AndroidUtilities.dpf2(13f))) + v1)), ((float)((((int)((((float)AndroidUtilities.dp(7f))) - (((float)AndroidUtilities.dp(v2))) * v4))) + v0)), ((float)((((int)(AndroidUtilities.dpf2(13f) - (((float)AndroidUtilities.dp(v2))) * v4))) + v1)), this.paint);
            arg15.drawLine(((float)((((int)AndroidUtilities.dpf2(7f))) + v0)), ((float)((((int)AndroidUtilities.dpf2(13f))) + v1)), ((float)(v0 + (((int)(AndroidUtilities.dpf2(7f) + (((float)AndroidUtilities.dp(13f))) * v4))))), ((float)(v1 + (((int)(AndroidUtilities.dpf2(13f) - (((float)AndroidUtilities.dp(13f))) * v4))))), this.paint);
        }

        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(48f);
        }

        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(48f);
        }

        public int getOpacity() {
            return -2;
        }

        public void resetProgress(boolean arg1) {
            float v1 = arg1 ? 0f : 1f;
            this.progress = v1;
        }

        public void setAlpha(int arg2) {
            this.paint.setAlpha(arg2);
        }

        public void setColorFilter(ColorFilter arg2) {
            this.paint.setColorFilter(arg2);
        }

        public boolean updateAnimation(long arg3) {
            float v1 = 1f;
            if(this.progress < v1) {
                this.progress += (((float)arg3)) / 700f;
                if(this.progress > v1) {
                    this.progress = v1;
                }

                return 1;
            }

            return 0;
        }
    }

    private boolean alphaForMiniPrevious;
    private boolean alphaForPrevious;
    private float animatedAlphaValue;
    private float animatedProgressValue;
    private float animationProgressStart;
    private Drawable checkBackgroundDrawable;
    private CheckDrawable checkDrawable;
    private RectF cicleRect;
    private Drawable currentDrawable;
    private Drawable currentMiniDrawable;
    private boolean currentMiniWithRound;
    private float currentProgress;
    private long currentProgressTime;
    long currentSize;
    private boolean currentWithRound;
    private static DecelerateInterpolator decelerateInterpolator;
    private int diff;
    private boolean drawMiniProgress;
    private boolean hideCurrentDrawable;
    private long lastUpdateTime;
    MessageObject messageObject;
    private Bitmap miniDrawBitmap;
    private Canvas miniDrawCanvas;
    private Paint miniProgressBackgroundPaint;
    private Paint miniProgressPaint;
    private float overrideAlpha;
    private View parent;
    private boolean previousCheckDrawable;
    private Drawable previousDrawable;
    private Drawable previousMiniDrawable;
    private boolean previousMiniWithRound;
    private boolean previousWithRound;
    private int progressColor;
    private Paint progressPaint;
    private RectF progressRect;
    private float radOffset;
    public TextPaint textView;

    public RadialProgress(View arg4) {
        super();
        this.lastUpdateTime = 0;
        this.radOffset = 0f;
        this.currentProgress = 0f;
        this.animationProgressStart = 0f;
        this.currentProgressTime = 0;
        this.animatedProgressValue = 0f;
        this.progressRect = new RectF();
        this.cicleRect = new RectF();
        this.animatedAlphaValue = 1f;
        this.progressColor = -1;
        this.diff = AndroidUtilities.dp(4f);
        this.alphaForPrevious = true;
        this.alphaForMiniPrevious = true;
        this.overrideAlpha = 1f;
        if(RadialProgress.decelerateInterpolator == null) {
            RadialProgress.decelerateInterpolator = new DecelerateInterpolator();
        }

        this.progressPaint = new Paint(1);
        this.progressPaint.setStyle(Paint$Style.STROKE);
        this.progressPaint.setStrokeCap(Paint$Cap.ROUND);
        this.progressPaint.setStrokeWidth(((float)AndroidUtilities.dp(3f)));
        this.miniProgressPaint = new Paint(1);
        this.miniProgressPaint.setStyle(Paint$Style.STROKE);
        this.miniProgressPaint.setStrokeCap(Paint$Cap.ROUND);
        this.miniProgressPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.miniProgressBackgroundPaint = new Paint(1);
        this.parent = arg4;
        this.textView = new TextPaint();
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setTextSize(((float)AndroidUtilities.dp(14f)));
    }

    static DecelerateInterpolator access$000() {
        return RadialProgress.decelerateInterpolator;
    }

    public void draw(Canvas arg18) {
        // Method was not decompiled
    }

    public float getAlpha() {
        float v0 = this.previousDrawable != null || this.currentDrawable != null ? this.animatedAlphaValue : 0f;
        return v0;
    }

    public RectF getProgressRect() {
        return this.progressRect;
    }

    private void invalidateParent() {
        int v0 = AndroidUtilities.dp(2f);
        View v1 = this.parent;
        int v2 = (((int)this.progressRect.left)) - v0;
        int v3 = (((int)this.progressRect.top)) - v0;
        v0 *= 2;
        v1.invalidate(v2, v3, (((int)this.progressRect.right)) + v0, (((int)this.progressRect.bottom)) + v0);
    }

    public boolean isDrawCheckDrawable() {
        boolean v0 = this.currentDrawable == this.checkBackgroundDrawable ? true : false;
        return v0;
    }

    public void setAlphaForMiniPrevious(boolean arg1) {
        this.alphaForMiniPrevious = arg1;
    }

    public void setAlphaForPrevious(boolean arg1) {
        this.alphaForPrevious = arg1;
    }

    public void setBackground(Drawable arg3, boolean arg4, boolean arg5) {
        this.lastUpdateTime = System.currentTimeMillis();
        if(!arg5 || this.currentDrawable == arg3) {
            this.previousDrawable = null;
            this.previousWithRound = false;
        }
        else {
            this.previousDrawable = this.currentDrawable;
            this.previousWithRound = this.currentWithRound;
            this.animatedAlphaValue = 1f;
            this.setProgress(1f, arg5);
        }

        this.currentWithRound = arg4;
        this.currentDrawable = arg3;
        if(!arg5) {
            this.parent.invalidate();
        }
        else {
            this.invalidateParent();
        }
    }

    public void setCheckBackground(boolean arg4, boolean arg5) {
        if(this.checkDrawable == null) {
            this.checkDrawable = new CheckDrawable(this);
            this.checkBackgroundDrawable = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(48f), this.checkDrawable, 0);
        }

        Theme.setCombinedDrawableColor(this.checkBackgroundDrawable, Theme.getColor("chat_mediaLoaderPhoto"), false);
        Theme.setCombinedDrawableColor(this.checkBackgroundDrawable, Theme.getColor("chat_mediaLoaderPhotoIcon"), true);
        if(this.currentDrawable != this.checkBackgroundDrawable) {
            this.setBackground(this.checkBackgroundDrawable, arg4, arg5);
            this.checkDrawable.resetProgress(arg5);
        }
    }

    public void setDiff(int arg1) {
        this.diff = arg1;
    }

    public void setHideCurrentDrawable(boolean arg1) {
        this.hideCurrentDrawable = arg1;
    }

    public void setMiniBackground(Drawable arg3, boolean arg4, boolean arg5) {
        this.lastUpdateTime = System.currentTimeMillis();
        boolean v0 = false;
        if(!arg5 || this.currentMiniDrawable == arg3) {
            this.previousMiniDrawable = null;
            this.previousMiniWithRound = false;
        }
        else {
            this.previousMiniDrawable = this.currentMiniDrawable;
            this.previousMiniWithRound = this.currentMiniWithRound;
            this.animatedAlphaValue = 1f;
            this.setProgress(1f, arg5);
        }

        this.currentMiniWithRound = arg4;
        this.currentMiniDrawable = arg3;
        if(this.previousMiniDrawable != null || this.currentMiniDrawable != null) {
            v0 = true;
        }

        this.drawMiniProgress = v0;
        if((this.drawMiniProgress) && this.miniDrawBitmap == null) {
            float v3 = 48f;
            try {
                this.miniDrawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(v3), AndroidUtilities.dp(v3), Bitmap$Config.ARGB_8888);
                this.miniDrawCanvas = new Canvas(this.miniDrawBitmap);
                goto label_39;
            }
            catch(Throwable ) {
            label_39:
                if(!arg5) {
                    this.parent.invalidate();
                }
                else {
                    this.invalidateParent();
                }

                return;
            }
        }

        goto label_39;
    }

    public void setMiniProgressBackgroundColor(int arg2) {
        this.miniProgressBackgroundPaint.setColor(arg2);
    }

    public void setOverrideAlpha(float arg1) {
        this.overrideAlpha = arg1;
    }

    public void setProgress(float arg5, boolean arg6) {
        Drawable v1 = null;
        float v2 = 1f;
        if(!this.drawMiniProgress) {
            if(arg5 == v2) {
                goto label_26;
            }

            if(this.animatedAlphaValue == 0f) {
                goto label_26;
            }

            if(this.previousDrawable == null) {
                goto label_26;
            }

            this.animatedAlphaValue = 0f;
            this.previousDrawable = v1;
        }
        else if(arg5 != v2 && this.animatedAlphaValue != 0f && this.previousMiniDrawable != null) {
            this.animatedAlphaValue = 0f;
            this.previousMiniDrawable = v1;
            boolean v0 = this.currentMiniDrawable != null ? true : false;
            this.drawMiniProgress = v0;
        }

    label_26:
        if(!arg6) {
            this.animatedProgressValue = arg5;
            this.animationProgressStart = arg5;
        }
        else {
            if(this.animatedProgressValue > arg5) {
                this.animatedProgressValue = arg5;
            }

            this.animationProgressStart = this.animatedProgressValue;
        }

        this.currentProgress = arg5;
        this.currentProgressTime = 0;
        this.invalidateParent();
    }

    public void setProgress(float arg3, boolean arg4, MessageObject arg5) {
        if(arg3 != 1f && this.animatedAlphaValue != 0f && this.previousDrawable != null) {
            this.animatedAlphaValue = 0f;
            this.previousDrawable = null;
        }

        if(!arg4) {
            this.animatedProgressValue = arg3;
            this.animationProgressStart = arg3;
        }
        else {
            if(this.animatedProgressValue > arg3) {
                this.animatedProgressValue = arg3;
            }

            this.animationProgressStart = this.animatedProgressValue;
        }

        this.currentProgress = arg3;
        this.currentProgressTime = 0;
        this.currentSize = ((long)arg5.getDocument().size);
        this.messageObject = arg5;
        this.invalidateParent();
    }

    public void setProgressColor(int arg1) {
        this.progressColor = arg1;
    }

    public void setProgressRect(int arg2, int arg3, int arg4, int arg5) {
        this.progressRect.set(((float)arg2), ((float)arg3), ((float)arg4), ((float)arg5));
    }

    public void setStrokeWidth(int arg2) {
        this.progressPaint.setStrokeWidth(((float)arg2));
    }

    public boolean swapBackground(Drawable arg2) {
        if(this.currentDrawable != arg2) {
            this.currentDrawable = arg2;
            return 1;
        }

        return 0;
    }

    public boolean swapMiniBackground(Drawable arg3) {
        boolean v1 = false;
        if(this.currentMiniDrawable != arg3) {
            this.currentMiniDrawable = arg3;
            if(this.previousMiniDrawable != null || this.currentMiniDrawable != null) {
                v1 = true;
            }

            this.drawMiniProgress = v1;
            return 1;
        }

        return 0;
    }

    private void updateAnimation(boolean arg14) {
        long v0 = System.currentTimeMillis();
        long v2 = v0 - this.lastUpdateTime;
        this.lastUpdateTime = v0;
        if(this.checkBackgroundDrawable != null && (this.currentDrawable == this.checkBackgroundDrawable || this.previousDrawable == this.checkBackgroundDrawable) && (this.checkDrawable.updateAnimation(v2))) {
            this.invalidateParent();
        }

        boolean v0_1 = false;
        Drawable v4 = null;
        float v5 = 200f;
        if(arg14) {
            float v7 = 1f;
            if(this.animatedProgressValue != v7) {
                this.radOffset += (((float)(360 * v2))) / 3000f;
                float v14 = this.currentProgress - this.animationProgressStart;
                if(v14 > 0f) {
                    this.currentProgressTime += v2;
                    if(this.currentProgressTime >= 300) {
                        this.animatedProgressValue = this.currentProgress;
                        this.animationProgressStart = this.currentProgress;
                        this.currentProgressTime = 0;
                    }
                    else {
                        this.animatedProgressValue = this.animationProgressStart + v14 * RadialProgress.decelerateInterpolator.getInterpolation((((float)this.currentProgressTime)) / 300f);
                    }
                }

                this.invalidateParent();
            }

            if(this.drawMiniProgress) {
                if(this.animatedProgressValue < v7) {
                    return;
                }

                if(this.previousMiniDrawable == null) {
                    return;
                }

                this.animatedAlphaValue -= (((float)v2)) / v5;
                if(this.animatedAlphaValue > 0f) {
                    goto label_111;
                }

                this.animatedAlphaValue = 0f;
                this.previousMiniDrawable = v4;
                if(this.currentMiniDrawable == null) {
                    goto label_110;
                }

                v0_1 = true;
                goto label_110;
            }

            if(this.animatedProgressValue < v7) {
                return;
            }

            if(this.previousDrawable == null) {
                return;
            }

            this.animatedAlphaValue -= (((float)v2)) / v5;
            if(this.animatedAlphaValue > 0f) {
                goto label_111;
            }

            goto label_91;
        }
        else {
            if(this.drawMiniProgress) {
                if(this.previousMiniDrawable == null) {
                    return;
                }

                this.animatedAlphaValue -= (((float)v2)) / v5;
                if(this.animatedAlphaValue > 0f) {
                    goto label_111;
                }

                this.animatedAlphaValue = 0f;
                this.previousMiniDrawable = v4;
                if(this.currentMiniDrawable != null) {
                    v0_1 = true;
                }

            label_110:
                this.drawMiniProgress = v0_1;
                goto label_111;
            }

            if(this.previousDrawable == null) {
                return;
            }

            this.animatedAlphaValue -= (((float)v2)) / v5;
            if(this.animatedAlphaValue <= 0f) {
            label_91:
                this.animatedAlphaValue = 0f;
                this.previousDrawable = v4;
            }

        label_111:
            this.invalidateParent();
        }
    }
}

