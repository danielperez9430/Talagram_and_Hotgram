package org.telegram.ui.Cells;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

public class RadioCell extends FrameLayout {
    private boolean needDivider;
    private RadioButton radioButton;
    private TextView textView;

    public RadioCell(Context arg12) {
        super(arg12);
        this.textView = new TextView(arg12);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0 = this.textView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1 | 16);
        v0 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        v1 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0), LayoutHelper.createFrame(v4, v5, v1 | 48, 17f, 0f, 17f, 0f));
        this.radioButton = new RadioButton(arg12);
        this.radioButton.setSize(AndroidUtilities.dp(20f));
        this.radioButton.setColor(Theme.getColor("radioBackground"), Theme.getColor("radioBackgroundChecked"));
        RadioButton v12 = this.radioButton;
        v4 = 22;
        v5 = 22f;
        if(LocaleController.isRTL) {
        }
        else {
            v2 = 5;
        }

        int v6 = v2 | 48;
        v1 = 0;
        int v0_1 = LocaleController.isRTL ? 18 : 0;
        float v7 = ((float)v0_1);
        float v8 = 13f;
        if(LocaleController.isRTL) {
        }
        else {
            v1 = 18;
        }

        this.addView(((View)v12), LayoutHelper.createFrame(v4, v5, v6, v7, v8, ((float)v1), 0f));
    }

    public boolean isChecked() {
        return this.radioButton.isChecked();
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg4, int arg5) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg4), AndroidUtilities.dp(48f) + this.needDivider);
        arg4 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight() - AndroidUtilities.dp(34f);
        this.radioButton.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22f), 1073741824));
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg4, 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.radioButton.setChecked(arg2, arg3);
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
            RadioButton v2_1 = this.radioButton;
            v3 = "alpha";
            float[] v4 = new float[1];
            if(arg9) {
                v0 = 1f;
            }

            v4[0] = v0;
            arg10.add(ObjectAnimator.ofFloat(v2_1, v3, v4));
        }
        else {
            TextView v10 = this.textView;
            float v2_2 = arg9 ? 1f : 0.5f;
            v10.setAlpha(v2_2);
            RadioButton v10_1 = this.radioButton;
            if(arg9) {
                v0 = 1f;
            }

            v10_1.setAlpha(v0);
        }
    }

    public void setText(String arg2, boolean arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.radioButton.setChecked(arg3, false);
        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

