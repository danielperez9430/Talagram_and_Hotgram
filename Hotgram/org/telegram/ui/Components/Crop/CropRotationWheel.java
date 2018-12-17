package org.telegram.ui.Components.Crop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class CropRotationWheel extends FrameLayout {
    public interface RotationWheelListener {
        void aspectRatioPressed();

        void onChange(float arg1);

        void onEnd(float arg1);

        void onStart();

        void rotate90Pressed();
    }

    private static final int DELTA_ANGLE = 5;
    private static final int MAX_ANGLE = 45;
    private ImageView aspectRatioButton;
    private Paint bluePaint;
    private TextView degreesLabel;
    private float prevX;
    protected float rotation;
    private ImageView rotation90Button;
    private RotationWheelListener rotationListener;
    private RectF tempRect;
    private Paint whitePaint;

    public CropRotationWheel(Context arg8) {
        super(arg8);
        this.tempRect = new RectF(0f, 0f, 0f, 0f);
        this.whitePaint = new Paint();
        this.whitePaint.setStyle(Paint$Style.FILL);
        this.whitePaint.setColor(-1);
        this.whitePaint.setAlpha(255);
        this.whitePaint.setAntiAlias(true);
        this.bluePaint = new Paint();
        this.bluePaint.setStyle(Paint$Style.FILL);
        this.bluePaint.setColor(-11420173);
        this.bluePaint.setAlpha(255);
        this.bluePaint.setAntiAlias(true);
        this.aspectRatioButton = new ImageView(arg8);
        this.aspectRatioButton.setImageResource(2131231664);
        this.aspectRatioButton.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        this.aspectRatioButton.setScaleType(ImageView$ScaleType.CENTER);
        this.aspectRatioButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                if(CropRotationWheel.access$000(CropRotationWheel.this) != null) {
                    CropRotationWheel.access$000(CropRotationWheel.this).aspectRatioPressed();
                }
            }
        });
        this.addView(this.aspectRatioButton, LayoutHelper.createFrame(70, 64, 19));
        this.rotation90Button = new ImageView(arg8);
        this.rotation90Button.setImageResource(2131231670);
        this.rotation90Button.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        this.rotation90Button.setScaleType(ImageView$ScaleType.CENTER);
        this.rotation90Button.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                if(CropRotationWheel.access$000(CropRotationWheel.this) != null) {
                    CropRotationWheel.access$000(CropRotationWheel.this).rotate90Pressed();
                }
            }
        });
        this.addView(this.rotation90Button, LayoutHelper.createFrame(70, 64, 21));
        this.degreesLabel = new TextView(arg8);
        this.degreesLabel.setTextColor(-1);
        this.addView(this.degreesLabel, LayoutHelper.createFrame(-2, -2, 49));
        this.setWillNotDraw(false);
        this.setRotation(0f, false);
    }

    static RotationWheelListener access$000(CropRotationWheel arg0) {
        return arg0.rotationListener;
    }

    protected void drawLine(Canvas arg7, int arg8, float arg9, int arg10, int arg11, boolean arg12, Paint arg13) {
        int v0 = ((int)((((float)arg10)) / 2f - (((float)AndroidUtilities.dp(70f)))));
        double v1 = ((double)v0);
        double v8 = Math.cos(Math.toRadians(((double)(90f - ((((float)(arg8 * 5))) + arg9)))));
        Double.isNaN(v1);
        arg8 = ((int)(v1 * v8));
        int v9 = 2;
        arg10 = arg10 / v9 + arg8;
        float v8_1 = (((float)Math.abs(arg8))) / (((float)v0));
        arg8 = Math.min(255, Math.max(0, ((int)((1f - v8_1 * v8_1) * 255f))));
        if(arg12) {
            arg13 = this.bluePaint;
        }

        Paint v5 = arg13;
        v5.setAlpha(arg8);
        arg8 = arg12 ? 4 : 2;
        float v12 = arg12 ? 16f : 12f;
        int v12_1 = AndroidUtilities.dp(v12);
        arg8 /= v9;
        arg7.drawRect(((float)(arg10 - arg8)), ((float)((arg11 - v12_1) / v9)), ((float)(arg10 + arg8)), ((float)((arg11 + v12_1) / v9)), v5);
    }

    protected void onDraw(Canvas arg18) {
        boolean v6;
        CropRotationWheel v8 = this;
        super.onDraw(arg18);
        int v9 = this.getWidth();
        int v10 = this.getHeight();
        float v11 = 2f;
        float v0 = -v8.rotation * v11;
        float v12 = v0 % 5f;
        int v13 = ((int)Math.floor(((double)(v0 / 5f))));
        int v15;
        for(v15 = 0; v15 < 16; ++v15) {
            Paint v0_1 = v8.whitePaint;
            if(v15 < v13 || v15 == 0 && v12 < 0f) {
                v0_1 = v8.bluePaint;
            }

            Paint v7 = v0_1;
            if(v15 != v13) {
                if(v15 == 0 && v13 == -1) {
                    goto label_36;
                }

                v6 = false;
            }
            else {
            label_36:
                v6 = true;
            }

            this.drawLine(arg18, v15, v12, v9, v10, v6, v7);
            if(v15 != 0) {
                int v2 = -v15;
                v0_1 = v2 > v13 ? v8.bluePaint : v8.whitePaint;
                v7 = v0_1;
                v6 = v2 == v13 + 1 ? true : false;
                this.drawLine(arg18, v2, v12, v9, v10, v6, v7);
            }
        }

        v8.bluePaint.setAlpha(255);
        v8.tempRect.left = ((float)((v9 - AndroidUtilities.dp(2.5f)) / 2));
        v8.tempRect.top = ((float)((v10 - AndroidUtilities.dp(22f)) / 2));
        v8.tempRect.right = ((float)((v9 + AndroidUtilities.dp(2.5f)) / 2));
        v8.tempRect.bottom = ((float)((v10 + AndroidUtilities.dp(22f)) / 2));
        arg18.drawRoundRect(v8.tempRect, ((float)AndroidUtilities.dp(v11)), ((float)AndroidUtilities.dp(v11)), v8.bluePaint);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(Math.min(View$MeasureSpec.getSize(arg2), AndroidUtilities.dp(400f)), 1073741824), arg3);
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        int v0 = arg8.getActionMasked();
        float v8 = arg8.getX();
        if(v0 == 0) {
            this.prevX = v8;
            if(this.rotationListener != null) {
                this.rotationListener.onStart();
            }
        }
        else {
            if(v0 != 1) {
                if(v0 == 3) {
                }
                else {
                    if(v0 == 2) {
                        float v0_1 = this.prevX - v8;
                        float v2 = this.rotation;
                        double v3 = ((double)(v0_1 / AndroidUtilities.density));
                        Double.isNaN(v3);
                        v0_1 = Math.max(-45f, Math.min(45f, v2 + (((float)(v3 / 3.141593 / 1.65)))));
                        if((((double)Math.abs(v0_1 - this.rotation))) > 0.001) {
                            if((((double)Math.abs(v0_1))) < 0.05) {
                                v0_1 = 0f;
                            }

                            this.setRotation(v0_1, false);
                            if(this.rotationListener != null) {
                                this.rotationListener.onChange(this.rotation);
                            }

                            this.prevX = v8;
                        }
                        else {
                        }
                    }
                    else {
                    }

                    return 1;
                }
            }

            if(this.rotationListener == null) {
                return 1;
            }

            this.rotationListener.onEnd(this.rotation);
        }

        return 1;
    }

    public void reset() {
        this.setRotation(0f, false);
    }

    public void setAspectLock(boolean arg4) {
        ColorFilter v4_1;
        ImageView v0 = this.aspectRatioButton;
        if(arg4) {
            PorterDuffColorFilter v4 = new PorterDuffColorFilter(-11420173, PorterDuff$Mode.MULTIPLY);
        }
        else {
            v4_1 = null;
        }

        v0.setColorFilter(v4_1);
    }

    public void setFreeform(boolean arg2) {
        ImageView v0 = this.aspectRatioButton;
        int v2 = arg2 ? 0 : 8;
        v0.setVisibility(v2);
    }

    public void setListener(RotationWheelListener arg1) {
        this.rotationListener = arg1;
    }

    public void setRotation(float arg5, boolean arg6) {
        this.rotation = arg5;
        arg5 = this.rotation;
        if((((double)Math.abs(arg5))) < 0.099) {
            arg5 = Math.abs(arg5);
        }

        this.degreesLabel.setText(String.format("%.1fº", Float.valueOf(arg5)));
        this.invalidate();
    }
}

