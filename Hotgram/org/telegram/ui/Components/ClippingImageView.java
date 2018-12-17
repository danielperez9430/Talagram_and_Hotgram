package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix$ScaleToFit;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.support.annotation.Keep;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver$BitmapHolder;

public class ClippingImageView extends View {
    private float animationProgress;
    private float[][] animationValues;
    private RectF bitmapRect;
    private BitmapShader bitmapShader;
    private BitmapHolder bmp;
    private int clipBottom;
    private int clipLeft;
    private int clipRight;
    private int clipTop;
    private RectF drawRect;
    private Matrix matrix;
    private boolean needRadius;
    private int orientation;
    private Paint paint;
    private int radius;
    private Paint roundPaint;
    private RectF roundRect;
    private Matrix shaderMatrix;

    public ClippingImageView(Context arg2) {
        super(arg2);
        this.paint = new Paint();
        this.paint.setFilterBitmap(true);
        this.matrix = new Matrix();
        this.drawRect = new RectF();
        this.bitmapRect = new RectF();
        this.roundPaint = new Paint(1);
        this.roundRect = new RectF();
        this.shaderMatrix = new Matrix();
    }

    @Keep public float getAnimationProgress() {
        return this.animationProgress;
    }

    public int getClipBottom() {
        return this.clipBottom;
    }

    public int getClipHorizontal() {
        return this.clipRight;
    }

    public int getClipLeft() {
        return this.clipLeft;
    }

    public int getClipRight() {
        return this.clipRight;
    }

    public int getClipTop() {
        return this.clipTop;
    }

    public int getRadius() {
        return this.radius;
    }

    public void onDraw(Canvas arg8) {
        int v6_1;
        float v2_2;
        RectF v1_2;
        Matrix$ScaleToFit v5_1;
        RectF v3_2;
        RectF v2_1;
        Matrix v1_1;
        int v4;
        float v3_1;
        float v5;
        int v1;
        if(this.getVisibility() != 0) {
            return;
        }

        if(this.bmp != null && !this.bmp.isRecycled()) {
            float v0 = this.getScaleY();
            arg8.save();
            int v2 = 270;
            int v3 = 90;
            if(this.needRadius) {
                this.shaderMatrix.reset();
                this.roundRect.set(0f, 0f, ((float)this.getWidth()), ((float)this.getHeight()));
                if(this.orientation % 360 == v3 || this.orientation % 360 == v2) {
                    v1 = this.bmp.getHeight();
                    v2 = this.bmp.getWidth();
                }
                else {
                    v1 = this.bmp.getWidth();
                    v2 = this.bmp.getHeight();
                }

                v5 = 1f;
                v3_1 = this.getWidth() != 0 ? (((float)v1)) / (((float)this.getWidth())) : 1f;
                if(this.getHeight() != 0) {
                    v5 = (((float)v2)) / (((float)this.getHeight()));
                }

                float v6 = Math.min(v3_1, v5);
                if(Math.abs(v3_1 - v5) > 0.00001f) {
                    v3 = ((int)Math.floor(((double)((((float)this.getWidth())) * v6))));
                    v4 = ((int)Math.floor(((double)((((float)this.getHeight())) * v6))));
                    this.bitmapRect.set(((float)((v1 - v3) / 2)), ((float)((v2 - v4) / 2)), ((float)v3), ((float)v4));
                    v1_1 = this.shaderMatrix;
                    v2_1 = this.bitmapRect;
                    v3_2 = this.roundRect;
                    v4 = this.orientation;
                    v5_1 = Matrix$ScaleToFit.START;
                }
                else {
                    this.bitmapRect.set(0f, 0f, ((float)this.bmp.getWidth()), ((float)this.bmp.getHeight()));
                    v1_1 = this.shaderMatrix;
                    v2_1 = this.bitmapRect;
                    v3_2 = this.roundRect;
                    v4 = this.orientation;
                    v5_1 = Matrix$ScaleToFit.FILL;
                }

                AndroidUtilities.setRectToRect(v1_1, v2_1, v3_2, v4, v5_1);
                this.bitmapShader.setLocalMatrix(this.shaderMatrix);
                arg8.clipRect((((float)this.clipLeft)) / v0, (((float)this.clipTop)) / v0, (((float)this.getWidth())) - (((float)this.clipRight)) / v0, (((float)this.getHeight())) - (((float)this.clipBottom)) / v0);
                arg8.drawRoundRect(this.roundRect, ((float)this.radius), ((float)this.radius), this.roundPaint);
                goto label_226;
            }

            if(this.orientation == v3 || this.orientation == v2) {
                v1_2 = this.drawRect;
                v2_2 = ((float)(-this.getHeight() / 2));
                v3_1 = ((float)(-this.getWidth() / 2));
                v5 = ((float)(this.getHeight() / 2));
                v6_1 = this.getWidth();
            label_179:
                v1_2.set(v2_2, v3_1, v5, ((float)(v6_1 / 2)));
                this.matrix.setRectToRect(this.bitmapRect, this.drawRect, Matrix$ScaleToFit.FILL);
                this.matrix.postRotate(((float)this.orientation), 0f, 0f);
                this.matrix.postTranslate(((float)(this.getWidth() / 2)), ((float)(this.getHeight() / 2)));
            }
            else if(this.orientation == 180) {
                v1_2 = this.drawRect;
                v2_2 = ((float)(-this.getWidth() / 2));
                v3_1 = ((float)(-this.getHeight() / 2));
                v5 = ((float)(this.getWidth() / 2));
                v6_1 = this.getHeight();
                goto label_179;
            }
            else {
                this.drawRect.set(0f, 0f, ((float)this.getWidth()), ((float)this.getHeight()));
                this.matrix.setRectToRect(this.bitmapRect, this.drawRect, Matrix$ScaleToFit.FILL);
            }

            arg8.clipRect((((float)this.clipLeft)) / v0, (((float)this.clipTop)) / v0, (((float)this.getWidth())) - (((float)this.clipRight)) / v0, (((float)this.getHeight())) - (((float)this.clipBottom)) / v0);
            try {
                arg8.drawBitmap(this.bmp.bitmap, this.matrix, this.paint);
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

        label_226:
            arg8.restore();
        }
    }

    @Keep public void setAnimationProgress(float arg6) {
        this.animationProgress = arg6;
        this.setScaleX(this.animationValues[0][0] + (this.animationValues[1][0] - this.animationValues[0][0]) * this.animationProgress);
        this.setScaleY(this.animationValues[0][1] + (this.animationValues[1][1] - this.animationValues[0][1]) * this.animationProgress);
        this.setTranslationX(this.animationValues[0][2] + (this.animationValues[1][2] - this.animationValues[0][2]) * this.animationProgress);
        this.setTranslationY(this.animationValues[0][3] + (this.animationValues[1][3] - this.animationValues[0][3]) * this.animationProgress);
        this.setClipHorizontal(((int)(this.animationValues[0][4] + (this.animationValues[1][4] - this.animationValues[0][4]) * this.animationProgress)));
        this.setClipTop(((int)(this.animationValues[0][5] + (this.animationValues[1][5] - this.animationValues[0][5]) * this.animationProgress)));
        this.setClipBottom(((int)(this.animationValues[0][6] + (this.animationValues[1][6] - this.animationValues[0][6]) * this.animationProgress)));
        this.setRadius(((int)(this.animationValues[0][7] + (this.animationValues[1][7] - this.animationValues[0][7]) * this.animationProgress)));
        this.invalidate();
    }

    public void setAnimationValues(float[][] arg1) {
        this.animationValues = arg1;
    }

    public void setClipBottom(int arg1) {
        this.clipBottom = arg1;
        this.invalidate();
    }

    public void setClipHorizontal(int arg1) {
        this.clipRight = arg1;
        this.clipLeft = arg1;
        this.invalidate();
    }

    public void setClipLeft(int arg1) {
        this.clipLeft = arg1;
        this.invalidate();
    }

    public void setClipRight(int arg1) {
        this.clipRight = arg1;
        this.invalidate();
    }

    public void setClipTop(int arg1) {
        this.clipTop = arg1;
        this.invalidate();
    }

    public void setClipVertical(int arg1) {
        this.clipBottom = arg1;
        this.clipTop = arg1;
        this.invalidate();
    }

    public void setImageBitmap(BitmapHolder arg5) {
        if(this.bmp != null) {
            this.bmp.release();
            this.bitmapShader = null;
        }

        this.bmp = arg5;
        if(arg5 != null) {
            this.bitmapRect.set(0f, 0f, ((float)arg5.getWidth()), ((float)arg5.getHeight()));
            if(this.needRadius) {
                this.bitmapShader = new BitmapShader(arg5.bitmap, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
                this.roundPaint.setShader(this.bitmapShader);
            }
        }

        this.invalidate();
    }

    public void setNeedRadius(boolean arg1) {
        this.needRadius = arg1;
    }

    public void setOrientation(int arg1) {
        this.orientation = arg1;
    }

    public void setRadius(int arg1) {
        this.radius = arg1;
    }
}

