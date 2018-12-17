package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Keep;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView$BufferType;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

public class EditTextBoldCursor extends EditText {
    private int activeLineColor;
    private boolean allowDrawCursor;
    private boolean currentDrawHintAsHeader;
    private int cursorSize;
    private float cursorWidth;
    private Object editor;
    private StaticLayout errorLayout;
    private int errorLineColor;
    private TextPaint errorPaint;
    private CharSequence errorText;
    private static Method getVerticalOffsetMethod;
    private GradientDrawable gradientDrawable;
    private float headerAnimationProgress;
    private int headerHintColor;
    private AnimatorSet headerTransformAnimation;
    private float hintAlpha;
    private int hintColor;
    private StaticLayout hintLayout;
    private boolean hintVisible;
    private int ignoreBottomCount;
    private int ignoreTopCount;
    private long lastUpdateTime;
    private int lineColor;
    private Paint linePaint;
    private float lineSpacingExtra;
    private float lineY;
    private Drawable[] mCursorDrawable;
    private static Field mCursorDrawableField;
    private static Field mCursorDrawableResField;
    private static Field mEditor;
    private static Field mScrollYField;
    private static Field mShowCursorField;
    private boolean nextSetTextAnimated;
    private Rect rect;
    private int scrollY;
    private boolean supportRtlHint;
    private boolean transformHintToHeader;

    public EditTextBoldCursor(Context arg6) {
        super(arg6);
        this.rect = new Rect();
        this.hintVisible = true;
        this.hintAlpha = 1f;
        this.allowDrawCursor = true;
        this.cursorWidth = 2f;
        this.linePaint = new Paint();
        this.errorPaint = new TextPaint(1);
        this.errorPaint.setTextSize(((float)AndroidUtilities.dp(11f)));
        if(EditTextBoldCursor.mCursorDrawableField == null) {
            try {
                EditTextBoldCursor.mScrollYField = View.class.getDeclaredField("mScrollY");
                EditTextBoldCursor.mScrollYField.setAccessible(true);
                EditTextBoldCursor.mCursorDrawableResField = TextView.class.getDeclaredField("mCursorDrawableRes");
                EditTextBoldCursor.mCursorDrawableResField.setAccessible(true);
                EditTextBoldCursor.mEditor = TextView.class.getDeclaredField("mEditor");
                EditTextBoldCursor.mEditor.setAccessible(true);
                Class v0 = Class.forName("android.widget.Editor");
                EditTextBoldCursor.mShowCursorField = v0.getDeclaredField("mShowCursor");
                EditTextBoldCursor.mShowCursorField.setAccessible(true);
                EditTextBoldCursor.mCursorDrawableField = v0.getDeclaredField("mCursorDrawable");
                EditTextBoldCursor.mCursorDrawableField.setAccessible(true);
                EditTextBoldCursor.getVerticalOffsetMethod = TextView.class.getDeclaredMethod("getVerticalOffset", Boolean.TYPE);
                EditTextBoldCursor.getVerticalOffsetMethod.setAccessible(true);
                goto label_64;
            }
            catch(Throwable ) {
                try {
                label_64:
                    this.gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[]{-11230757, -11230757});
                    this.editor = EditTextBoldCursor.mEditor.get(this);
                    if(EditTextBoldCursor.mCursorDrawableField == null) {
                        goto label_86;
                    }

                    this.mCursorDrawable = EditTextBoldCursor.mCursorDrawableField.get(this.editor);
                    EditTextBoldCursor.mCursorDrawableResField.set(this, Integer.valueOf(2131231064));
                }
                catch(Exception v6) {
                    FileLog.e(((Throwable)v6));
                }

            label_86:
                this.cursorSize = AndroidUtilities.dp(24f);
                return;
            }
        }

        goto label_64;
    }

    private void checkHeaderVisibility(boolean arg8) {
        boolean v0;
        if(this.transformHintToHeader) {
            if(!this.isFocused() && this.getText().length() <= 0) {
                goto label_11;
            }

            v0 = true;
        }
        else {
        label_11:
            v0 = false;
        }

        if(this.currentDrawHintAsHeader != v0) {
            if(this.headerTransformAnimation != null) {
                this.headerTransformAnimation.cancel();
                this.headerTransformAnimation = null;
            }

            this.currentDrawHintAsHeader = v0;
            float v3 = 0f;
            if(arg8) {
                this.headerTransformAnimation = new AnimatorSet();
                AnimatorSet v8 = this.headerTransformAnimation;
                Animator[] v5 = new Animator[1];
                String v6 = "headerAnimationProgress";
                float[] v1 = new float[1];
                if(v0) {
                    v3 = 1f;
                }

                v1[0] = v3;
                v5[0] = ObjectAnimator.ofFloat(this, v6, v1);
                v8.playTogether(v5);
                this.headerTransformAnimation.setDuration(200);
                this.headerTransformAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
                this.headerTransformAnimation.start();
            }
            else {
                if(v0) {
                    v3 = 1f;
                }

                this.headerAnimationProgress = v3;
            }

            this.invalidate();
        }
    }

    public StaticLayout getErrorLayout(int arg10) {
        if(TextUtils.isEmpty(this.errorText)) {
            return null;
        }

        return new StaticLayout(this.errorText, this.errorPaint, arg10, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
    }

    public int getExtendedPaddingBottom() {
        if(this.ignoreBottomCount != 0) {
            --this.ignoreBottomCount;
            int v0 = this.scrollY != 2147483647 ? -this.scrollY : 0;
            return v0;
        }

        return super.getExtendedPaddingBottom();
    }

    public int getExtendedPaddingTop() {
        if(this.ignoreTopCount != 0) {
            --this.ignoreTopCount;
            return 0;
        }

        return super.getExtendedPaddingTop();
    }

    @Keep public float getHeaderAnimationProgress() {
        return this.headerAnimationProgress;
    }

    public float getLineY() {
        return this.lineY;
    }

    public boolean hasErrorText() {
        return TextUtils.isEmpty(this.errorText) ^ 1;
    }

    protected void onDraw(Canvas arg17) {
        // Method was not decompiled
    }

    protected void onFocusChanged(boolean arg1, int arg2, Rect arg3) {
        super.onFocusChanged(arg1, arg2, arg3);
        this.checkHeaderVisibility(true);
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(arg1, arg2);
        if(this.hintLayout != null) {
            this.lineY = (((float)(this.getMeasuredHeight() - this.hintLayout.getHeight()))) / 2f + (((float)this.hintLayout.getHeight())) + (((float)AndroidUtilities.dp(6f)));
        }
    }

    public void setAllowDrawCursor(boolean arg1) {
        this.allowDrawCursor = arg1;
    }

    public void setCursorColor(int arg2) {
        this.gradientDrawable.setColor(arg2);
        this.invalidate();
    }

    public void setCursorSize(int arg1) {
        this.cursorSize = arg1;
    }

    public void setCursorWidth(float arg1) {
        this.cursorWidth = arg1;
    }

    public void setErrorLineColor(int arg2) {
        this.errorLineColor = arg2;
        this.errorPaint.setColor(this.errorLineColor);
        this.invalidate();
    }

    public void setErrorText(CharSequence arg2) {
        if(TextUtils.equals(arg2, this.errorText)) {
            return;
        }

        this.errorText = arg2;
        this.requestLayout();
    }

    @Keep public void setHeaderAnimationProgress(float arg1) {
        this.headerAnimationProgress = arg1;
        this.invalidate();
    }

    public void setHeaderHintColor(int arg1) {
        this.headerHintColor = arg1;
        this.invalidate();
    }

    public void setHintColor(int arg1) {
        this.hintColor = arg1;
        this.invalidate();
    }

    public void setHintText(String arg10) {
        this.hintLayout = new StaticLayout(arg10, this.getPaint(), AndroidUtilities.dp(1000f), Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
    }

    public void setHintVisible(boolean arg3) {
        if(this.hintVisible == arg3) {
            return;
        }

        this.lastUpdateTime = System.currentTimeMillis();
        this.hintVisible = arg3;
        this.invalidate();
    }

    public void setLineColors(int arg1, int arg2, int arg3) {
        this.lineColor = arg1;
        this.activeLineColor = arg2;
        this.errorLineColor = arg3;
        this.errorPaint.setColor(this.errorLineColor);
        this.invalidate();
    }

    public void setLineSpacing(float arg1, float arg2) {
        super.setLineSpacing(arg1, arg2);
        this.lineSpacingExtra = arg1;
    }

    public void setNextSetTextAnimated(boolean arg1) {
        this.nextSetTextAnimated = arg1;
    }

    public void setSupportRtlHint(boolean arg1) {
        this.supportRtlHint = arg1;
    }

    public void setText(CharSequence arg1, TextView$BufferType arg2) {
        super.setText(arg1, arg2);
        this.checkHeaderVisibility(this.nextSetTextAnimated);
        this.nextSetTextAnimated = false;
    }

    public void setTransformHintToHeader(boolean arg2) {
        if(this.transformHintToHeader == arg2) {
            return;
        }

        this.transformHintToHeader = arg2;
        if(this.headerTransformAnimation != null) {
            this.headerTransformAnimation.cancel();
            this.headerTransformAnimation = null;
        }
    }
}

