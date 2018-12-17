package org.telegram.ui.Cells;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextColorCell extends FrameLayout {
    private float alpha;
    private static Paint colorPaint;
    public static final int[] colors;
    public static final int[] colorsToSave;
    private int currentColor;
    private boolean needDivider;
    private TextView textView;

    static {
        TextColorCell.colors = new int[]{-1031100, -29183, -12769, -8792480, -12521994, -12140801, -2984711, -45162, -4473925};
        TextColorCell.colorsToSave = new int[]{-65536, -29183, -256, -16711936, -16711681, -16776961, -2984711, -65281, -1};
    }

    public TextColorCell(Context arg11) {
        super(arg11);
        this.alpha = 1f;
        if(TextColorCell.colorPaint == null) {
            TextColorCell.colorPaint = new Paint(1);
        }

        this.textView = new TextView(arg11);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 17f, 0f, 17f, 0f));
    }

    public float getAlpha() {
        return this.alpha;
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }

        if(this.currentColor != 0) {
            TextColorCell.colorPaint.setColor(this.currentColor);
            TextColorCell.colorPaint.setAlpha(((int)(this.alpha * 255f)));
            float v1 = 29f;
            int v0 = LocaleController.isRTL ? AndroidUtilities.dp(v1) : this.getMeasuredWidth() - AndroidUtilities.dp(v1);
            float v0_1 = ((float)v0);
            arg8.drawCircle(v0_1, ((float)(this.getMeasuredHeight() / 2)), ((float)AndroidUtilities.dp(10f)), TextColorCell.colorPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setAlpha(float arg1) {
        this.alpha = arg1;
        this.invalidate();
    }

    public void setEnabled(boolean arg9, ArrayList arg10) {
        float v0 = 0.5f;
        if(arg10 != null) {
            TextView v2 = this.textView;
            String v3 = "alpha";
            float[] v5 = new float[1];
            float v6 = arg9 ? 1f : 0.5f;
            v5[0] = v6;
            arg10.add(ObjectAnimator.ofFloat(v2, v3, v5));
            String v2_1 = "alpha";
            float[] v3_1 = new float[1];
            if(arg9) {
                v0 = 1f;
            }

            v3_1[0] = v0;
            arg10.add(ObjectAnimator.ofFloat(this, v2_1, v3_1));
        }
        else {
            TextView v10 = this.textView;
            float v2_2 = arg9 ? 1f : 0.5f;
            v10.setAlpha(v2_2);
            if(arg9) {
                v0 = 1f;
            }

            this.setAlpha(v0);
        }
    }

    public void setTextAndColor(String arg2, int arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.needDivider = arg4;
        this.currentColor = arg3;
        boolean v2 = (this.needDivider) || this.currentColor != 0 ? false : true;
        this.setWillNotDraw(v2);
        this.invalidate();
    }
}

