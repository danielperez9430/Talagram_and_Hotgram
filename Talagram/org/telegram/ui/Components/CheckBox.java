package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Keep;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

public class CheckBox extends View {
    private boolean attachedToWindow;
    private static Paint backgroundPaint = null;
    private Canvas bitmapCanvas;
    private ObjectAnimator checkAnimator;
    private Bitmap checkBitmap;
    private Canvas checkCanvas;
    private Drawable checkDrawable;
    private int checkOffset;
    private static Paint checkPaint = null;
    private String checkedText;
    private int color;
    private boolean drawBackground;
    private Bitmap drawBitmap;
    private static Paint eraser = null;
    private static Paint eraser2 = null;
    private boolean hasBorder;
    private boolean isCheckAnimation;
    private boolean isChecked;
    private static Paint paint = null;
    private float progress;
    private static final float progressBounceDiff = 0.2f;
    private int size;
    private TextPaint textPaint;

    public CheckBox(Context arg6, int arg7) {
        super(arg6);
        this.isCheckAnimation = true;
        this.size = 22;
        if(CheckBox.paint == null) {
            CheckBox.paint = new Paint(1);
            CheckBox.eraser = new Paint(1);
            CheckBox.eraser.setColor(0);
            CheckBox.eraser.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
            CheckBox.eraser2 = new Paint(1);
            CheckBox.eraser2.setColor(0);
            CheckBox.eraser2.setStyle(Paint$Style.STROKE);
            CheckBox.eraser2.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.CLEAR));
            CheckBox.backgroundPaint = new Paint(1);
            CheckBox.backgroundPaint.setColor(-1);
            CheckBox.backgroundPaint.setStyle(Paint$Style.STROKE);
        }

        CheckBox.eraser2.setStrokeWidth(((float)AndroidUtilities.dp(28f)));
        CheckBox.backgroundPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.textPaint = new TextPaint(1);
        this.textPaint.setTextSize(((float)AndroidUtilities.dp(18f)));
        this.textPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.checkDrawable = arg6.getResources().getDrawable(arg7).mutate();
    }

    static ObjectAnimator access$000(CheckBox arg0) {
        return arg0.checkAnimator;
    }

    static ObjectAnimator access$002(CheckBox arg0, ObjectAnimator arg1) {
        arg0.checkAnimator = arg1;
        return arg1;
    }

    static boolean access$100(CheckBox arg0) {
        return arg0.isChecked;
    }

    static String access$202(CheckBox arg0, String arg1) {
        arg0.checkedText = arg1;
        return arg1;
    }

    private void animateToCheckedState(boolean arg4) {
        this.isCheckAnimation = arg4;
        String v0 = "progress";
        float[] v1 = new float[1];
        float v4 = arg4 ? 1f : 0f;
        v1[0] = v4;
        this.checkAnimator = ObjectAnimator.ofFloat(this, v0, v1);
        this.checkAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                ObjectAnimator v0 = null;
                if(arg2.equals(CheckBox.this.checkAnimator)) {
                    CheckBox.this.checkAnimator = v0;
                }

                if(!CheckBox.this.isChecked) {
                    CheckBox.this.checkedText = ((String)v0);
                }
            }
        });
        this.checkAnimator.setDuration(300);
        this.checkAnimator.start();
    }

    private void cancelCheckAnimator() {
        if(this.checkAnimator != null) {
            this.checkAnimator.cancel();
            this.checkAnimator = null;
        }
    }

    public float getProgress() {
        return this.progress;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachedToWindow = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attachedToWindow = false;
    }

    protected void onDraw(Canvas arg12) {
        // Method was not decompiled
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
    }

    public void setBackgroundColor(int arg1) {
        this.color = arg1;
        this.invalidate();
    }

    public void setCheckColor(int arg4) {
        this.checkDrawable.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
        this.textPaint.setColor(arg4);
        this.invalidate();
    }

    public void setCheckOffset(int arg1) {
        this.checkOffset = arg1;
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.setChecked(-1, arg2, arg3);
    }

    public void setChecked(int arg3, boolean arg4, boolean arg5) {
        if(arg3 >= 0) {
            this.checkedText = "" + (arg3 + 1);
            this.invalidate();
        }

        if(arg4 == this.isChecked) {
            return;
        }

        this.isChecked = arg4;
        if(!this.attachedToWindow || !arg5) {
            this.cancelCheckAnimator();
            float v3 = arg4 ? 1f : 0f;
            this.setProgress(v3);
        }
        else {
            this.animateToCheckedState(arg4);
        }
    }

    public void setColor(int arg3, int arg4) {
        this.color = arg3;
        this.checkDrawable.setColorFilter(new PorterDuffColorFilter(arg4, PorterDuff$Mode.MULTIPLY));
        this.textPaint.setColor(arg4);
        this.invalidate();
    }

    public void setDrawBackground(boolean arg1) {
        this.drawBackground = arg1;
    }

    public void setHasBorder(boolean arg1) {
        this.hasBorder = arg1;
    }

    public void setNum(int arg3) {
        String v3;
        if(arg3 >= 0) {
            v3 = "" + (arg3 + 1);
            goto label_8;
        }
        else if(this.checkAnimator == null) {
            v3 = null;
        label_8:
            this.checkedText = v3;
        }

        this.invalidate();
    }

    @Keep public void setProgress(float arg2) {
        if(this.progress == arg2) {
            return;
        }

        this.progress = arg2;
        this.invalidate();
    }

    public void setSize(int arg2) {
        this.size = arg2;
        if(arg2 == 40) {
            this.textPaint.setTextSize(((float)AndroidUtilities.dp(24f)));
        }
    }

    public void setVisibility(int arg3) {
        super.setVisibility(arg3);
        if(arg3 == 0 && this.drawBitmap == null) {
            try {
                this.drawBitmap = Bitmap.createBitmap(AndroidUtilities.dp(((float)this.size)), AndroidUtilities.dp(((float)this.size)), Bitmap$Config.ARGB_4444);
                this.bitmapCanvas = new Canvas(this.drawBitmap);
                this.checkBitmap = Bitmap.createBitmap(AndroidUtilities.dp(((float)this.size)), AndroidUtilities.dp(((float)this.size)), Bitmap$Config.ARGB_4444);
                this.checkCanvas = new Canvas(this.checkBitmap);
                return;
            }
            catch(Throwable ) {
                return;
            }
        }
    }
}

