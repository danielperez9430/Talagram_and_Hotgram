package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.PorterDuff$Mode;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.EditText;

public class EditTextOutline extends EditText {
    private Bitmap mCache;
    private final Canvas mCanvas;
    private final TextPaint mPaint;
    private int mStrokeColor;
    private float mStrokeWidth;
    private boolean mUpdateCachedBitmap;

    public EditTextOutline(Context arg2) {
        super(arg2);
        this.mCanvas = new Canvas();
        this.mPaint = new TextPaint();
        this.mStrokeColor = 0;
        this.mUpdateCachedBitmap = true;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint$Style.FILL_AND_STROKE);
    }

    protected void onDraw(Canvas arg13) {
        if(this.mCache != null && this.mStrokeColor != 0) {
            if(this.mUpdateCachedBitmap) {
                int v6 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
                int v0 = this.getMeasuredHeight();
                String v4 = this.getText().toString();
                this.mCanvas.setBitmap(this.mCache);
                this.mCanvas.drawColor(0, PorterDuff$Mode.CLEAR);
                float v2 = this.mStrokeWidth > 0f ? this.mStrokeWidth : ((float)Math.ceil(((double)(this.getTextSize() / 11.5f))));
                this.mPaint.setStrokeWidth(v2);
                this.mPaint.setColor(this.mStrokeColor);
                this.mPaint.setTextSize(this.getTextSize());
                this.mPaint.setTypeface(this.getTypeface());
                this.mPaint.setStyle(Paint$Style.FILL_AND_STROKE);
                StaticLayout v2_1 = new StaticLayout(((CharSequence)v4), this.mPaint, v6, Layout$Alignment.ALIGN_CENTER, 1f, 0f, true);
                this.mCanvas.save();
                this.mCanvas.translate(((float)this.getPaddingLeft()), (((float)(v0 - this.getPaddingTop() - this.getPaddingBottom() - v2_1.getHeight()))) / 2f + (((float)this.getPaddingTop())));
                v2_1.draw(this.mCanvas);
                this.mCanvas.restore();
                this.mUpdateCachedBitmap = false;
            }

            arg13.drawBitmap(this.mCache, 0f, 0f, this.mPaint);
        }

        super.onDraw(arg13);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        Bitmap v1;
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        if(arg1 <= 0 || arg2 <= 0) {
            v1 = null;
        }
        else {
            this.mUpdateCachedBitmap = true;
            v1 = Bitmap.createBitmap(arg1, arg2, Bitmap$Config.ARGB_8888);
        }

        this.mCache = v1;
    }

    protected void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        super.onTextChanged(arg1, arg2, arg3, arg4);
        this.mUpdateCachedBitmap = true;
    }

    public void setStrokeColor(int arg1) {
        this.mStrokeColor = arg1;
        this.mUpdateCachedBitmap = true;
        this.invalidate();
    }

    public void setStrokeWidth(float arg1) {
        this.mStrokeWidth = arg1;
        this.mUpdateCachedBitmap = true;
        this.invalidate();
    }
}

