package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Swatch;

public class ColorPicker extends FrameLayout {
    public interface ColorPickerDelegate {
        void onBeganColorPicking();

        void onColorValueChanged();

        void onFinishedColorPicking();

        void onSettingsPressed();

        void onUndoPressed();
    }

    private static final int[] COLORS;
    private static final float[] LOCATIONS;
    private Paint backgroundPaint;
    private boolean changingWeight;
    private ColorPickerDelegate delegate;
    private boolean dragging;
    private float draggingFactor;
    private Paint gradientPaint;
    private boolean interacting;
    private OvershootInterpolator interpolator;
    private float location;
    private RectF rectF;
    private ImageView settingsButton;
    private Drawable shadowDrawable;
    private Paint swatchPaint;
    private Paint swatchStrokePaint;
    private ImageView undoButton;
    private boolean wasChangingWeight;
    private float weight;

    static {
        ColorPicker.COLORS = new int[]{-1431751, -2409774, -13610525, -11942419, -8337308, -205211, -223667, -16777216, -1};
        ColorPicker.LOCATIONS = new float[]{0f, 0.14f, 0.24f, 0.39f, 0.49f, 0.62f, 0.73f, 0.85f, 1f};
    }

    public ColorPicker(Context arg7) {
        super(arg7);
        this.interpolator = new OvershootInterpolator(1.02f);
        this.gradientPaint = new Paint(1);
        this.backgroundPaint = new Paint(1);
        this.swatchPaint = new Paint(1);
        this.swatchStrokePaint = new Paint(1);
        this.rectF = new RectF();
        this.location = 1f;
        this.weight = 0.27f;
        this.setWillNotDraw(false);
        this.shadowDrawable = this.getResources().getDrawable(2131231297);
        this.backgroundPaint.setColor(-1);
        this.swatchStrokePaint.setStyle(Paint$Style.STROKE);
        this.swatchStrokePaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
        this.settingsButton = new ImageView(arg7);
        this.settingsButton.setScaleType(ImageView$ScaleType.CENTER);
        this.settingsButton.setImageResource(2131231463);
        this.addView(this.settingsButton, LayoutHelper.createFrame(60, 52f));
        this.settingsButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                if(ColorPicker.access$000(ColorPicker.this) != null) {
                    ColorPicker.access$000(ColorPicker.this).onSettingsPressed();
                }
            }
        });
        this.undoButton = new ImageView(arg7);
        this.undoButton.setScaleType(ImageView$ScaleType.CENTER);
        this.undoButton.setImageResource(2131231469);
        this.addView(this.undoButton, LayoutHelper.createFrame(60, 52f));
        this.undoButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                if(ColorPicker.access$000(ColorPicker.this) != null) {
                    ColorPicker.access$000(ColorPicker.this).onUndoPressed();
                }
            }
        });
        this.location = arg7.getSharedPreferences("paint", 0).getFloat("last_color_location", 1f);
        this.setLocation(this.location);
    }

    static ColorPickerDelegate access$000(ColorPicker arg0) {
        return arg0.delegate;
    }

    public int colorForLocation(float arg6) {
        // Method was not decompiled
    }

    public float getDraggingFactor() {
        return this.draggingFactor;
    }

    public View getSettingsButton() {
        return this.settingsButton;
    }

    public Swatch getSwatch() {
        return new Swatch(this.colorForLocation(this.location), this.location, this.weight);
    }

    private int interpolateColors(int arg6, int arg7, float arg8) {
        arg8 = Math.min(Math.max(arg8, 0f), 1f);
        int v0 = Color.red(arg6);
        int v1 = Color.red(arg7);
        int v2 = Color.green(arg6);
        int v3 = Color.green(arg7);
        arg6 = Color.blue(arg6);
        return Color.argb(255, Math.min(255, ((int)((((float)v0)) + (((float)(v1 - v0))) * arg8))), Math.min(255, ((int)((((float)v2)) + (((float)(v3 - v2))) * arg8))), Math.min(255, ((int)((((float)arg6)) + (((float)(Color.blue(arg7) - arg6))) * arg8))));
    }

    protected void onDraw(Canvas arg10) {
        arg10.drawRoundRect(this.rectF, ((float)AndroidUtilities.dp(6f)), ((float)AndroidUtilities.dp(6f)), this.gradientPaint);
        int v0 = ((int)(this.rectF.left + this.rectF.width() * this.location));
        float v1 = this.rectF.centerY() + this.draggingFactor * (((float)(-AndroidUtilities.dp(70f))));
        float v2 = this.changingWeight ? this.weight * (((float)AndroidUtilities.dp(190f))) : 0f;
        int v1_1 = ((int)(v1 - v2));
        int v2_1 = ((int)((((float)AndroidUtilities.dp(24f))) * ((this.draggingFactor + 1f) * 0.5f)));
        this.shadowDrawable.setBounds(v0 - v2_1, v1_1 - v2_1, v0 + v2_1, v2_1 + v1_1);
        this.shadowDrawable.draw(arg10);
        v2 = (((float)(((int)Math.floor(((double)((((float)AndroidUtilities.dp(4f))) + (((float)(AndroidUtilities.dp(19f) - AndroidUtilities.dp(4f)))) * this.weight))))))) * (this.draggingFactor + 1f) / 2f;
        float v0_1 = ((float)v0);
        v1 = ((float)v1_1);
        arg10.drawCircle(v0_1, v1, (((float)(AndroidUtilities.dp(22f) / 2))) * (this.draggingFactor + 1f), this.backgroundPaint);
        arg10.drawCircle(v0_1, v1, v2, this.swatchPaint);
        arg10.drawCircle(v0_1, v1, v2 - (((float)AndroidUtilities.dp(0.5f))), this.swatchStrokePaint);
    }

    @SuppressLint(value={"DrawAllocation"}) protected void onLayout(boolean arg9, int arg10, int arg11, int arg12, int arg13) {
        arg12 -= arg10;
        arg13 -= arg11;
        this.gradientPaint.setShader(new LinearGradient(((float)AndroidUtilities.dp(56f)), 0f, ((float)(arg12 - AndroidUtilities.dp(56f))), 0f, ColorPicker.COLORS, ColorPicker.LOCATIONS, Shader$TileMode.REPEAT));
        int v9 = arg13 - AndroidUtilities.dp(32f);
        this.rectF.set(((float)AndroidUtilities.dp(56f)), ((float)v9), ((float)(arg12 - AndroidUtilities.dp(56f))), ((float)(v9 + AndroidUtilities.dp(12f))));
        this.settingsButton.layout(arg12 - this.settingsButton.getMeasuredWidth(), arg13 - AndroidUtilities.dp(52f), arg12, arg13);
        this.undoButton.layout(0, arg13 - AndroidUtilities.dp(52f), this.settingsButton.getMeasuredWidth(), arg13);
    }

    public boolean onTouchEvent(MotionEvent arg7) {
        if(arg7.getPointerCount() > 1) {
            return 0;
        }

        float v0 = arg7.getX() - this.rectF.left;
        float v3 = arg7.getY() - this.rectF.top;
        float v5 = 10f;
        if(!this.interacting && v3 < (((float)(-AndroidUtilities.dp(v5))))) {
            return 0;
        }

        int v7 = arg7.getActionMasked();
        if(v7 == 3 || v7 == 1 || v7 == 6) {
            if((this.interacting) && this.delegate != null) {
                this.delegate.onFinishedColorPicking();
                this.getContext().getSharedPreferences("paint", 0).edit().putFloat("last_color_location", this.location).commit();
            }

            this.interacting = false;
            this.wasChangingWeight = this.changingWeight;
            this.changingWeight = false;
            this.setDragging(false, true);
        }
        else {
            if(v7 != 0 && v7 != 2) {
                return 0;
            }

            if(!this.interacting) {
                this.interacting = true;
                if(this.delegate != null) {
                    this.delegate.onBeganColorPicking();
                }
            }

            float v7_1 = 1f;
            this.setLocation(Math.max(0f, Math.min(v7_1, v0 / this.rectF.width())));
            this.setDragging(true, true);
            if(v3 < (((float)(-AndroidUtilities.dp(v5))))) {
                this.changingWeight = true;
                this.setWeight(Math.max(0f, Math.min(v7_1, (-v3 - (((float)AndroidUtilities.dp(v5)))) / (((float)AndroidUtilities.dp(190f))))));
            }

            if(this.delegate != null) {
                this.delegate.onColorValueChanged();
            }

            return 1;
        }

        return 0;
    }

    public void setDelegate(ColorPickerDelegate arg1) {
        this.delegate = arg1;
    }

    private void setDragging(boolean arg4, boolean arg5) {
        if(this.dragging == arg4) {
            return;
        }

        this.dragging = arg4;
        float v4 = this.dragging ? 1f : 0f;
        if(arg5) {
            ObjectAnimator v4_1 = ObjectAnimator.ofFloat(this, "draggingFactor", new float[]{this.draggingFactor, v4});
            ((Animator)v4_1).setInterpolator(this.interpolator);
            int v5 = 300;
            if(this.wasChangingWeight) {
                v5 = ((int)((((float)v5)) + this.weight * 75f));
            }

            ((Animator)v4_1).setDuration(((long)v5));
            ((Animator)v4_1).start();
        }
        else {
            this.setDraggingFactor(v4);
        }
    }

    private void setDraggingFactor(float arg1) {
        this.draggingFactor = arg1;
        this.invalidate();
    }

    public void setLocation(float arg7) {
        Paint v0_1;
        this.location = arg7;
        int v7 = this.colorForLocation(arg7);
        this.swatchPaint.setColor(v7);
        float[] v0 = new float[3];
        Color.colorToHSV(v7, v0);
        double v3 = 0.001;
        if((((double)v0[0])) >= v3 || (((double)v0[1])) >= v3) {
        label_34:
            v0_1 = this.swatchStrokePaint;
        }
        else {
            int v1 = 2;
            float v3_1 = 0.92f;
            if(v0[v1] > v3_1) {
                v7 = ((int)((1f - (v0[v1] - v3_1) / 0.08f * 0.22f) * 255f));
                v0_1 = this.swatchStrokePaint;
                v7 = Color.rgb(v7, v7, v7);
            }
            else {
                goto label_34;
            }
        }

        v0_1.setColor(v7);
        this.invalidate();
    }

    public void setSettingsButtonImage(int arg2) {
        this.settingsButton.setImageResource(arg2);
    }

    public void setSwatch(Swatch arg2) {
        this.setLocation(arg2.colorLocation);
        this.setWeight(arg2.brushWeight);
    }

    public void setUndoEnabled(boolean arg3) {
        ImageView v0 = this.undoButton;
        float v1 = arg3 ? 1f : 0.3f;
        v0.setAlpha(v1);
        this.undoButton.setEnabled(arg3);
    }

    public void setWeight(float arg1) {
        this.weight = arg1;
        this.invalidate();
    }
}

