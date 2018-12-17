package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

public class CheckBoxCell extends FrameLayout {
    private CheckBoxSquare checkBox;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public CheckBoxCell(Context arg20, int arg21) {
        FrameLayout$LayoutParams v2_1;
        CheckBoxSquare v1_1;
        float v16;
        float v14_1;
        int v14;
        int v15;
        int v13;
        float v12;
        int v11;
        TextView v10;
        CheckBoxCell v0 = this;
        Context v1 = arg20;
        int v2 = arg21;
        super(arg20);
        v0.textView = new TextView(v1);
        TextView v3 = v0.textView;
        boolean v4 = true;
        String v5 = v2 == 1 ? "dialogTextBlack" : "windowBackgroundWhiteBlackText";
        v3.setTextColor(Theme.getColor(v5));
        float v5_1 = 16f;
        v0.textView.setTextSize(1, v5_1);
        v0.textView.setLines(1);
        v0.textView.setMaxLines(1);
        v0.textView.setSingleLine(true);
        v0.textView.setEllipsize(TextUtils$TruncateAt.END);
        v3 = v0.textView;
        int v7 = 3;
        int v6 = LocaleController.isRTL ? 5 : 3;
        v3.setGravity(v6 | 16);
        int v3_1 = 2;
        v6 = 17;
        if(v2 == v3_1) {
            v10 = v0.textView;
            v11 = -1;
            v12 = -1f;
            v13 = LocaleController.isRTL ? 5 : 3;
            v13 |= 48;
            v15 = 29;
            v14 = LocaleController.isRTL ? 0 : 29;
            v14_1 = ((float)v14);
            v16 = 0f;
            if(LocaleController.isRTL) {
                goto label_62;
            }

            v15 = 0;
        }
        else {
            v10 = v0.textView;
            v11 = -1;
            v12 = -1f;
            v13 = LocaleController.isRTL ? 5 : 3;
            v13 |= 48;
            v15 = 46;
            v14 = LocaleController.isRTL ? 17 : 46;
            v14_1 = ((float)v14);
            v16 = 0f;
            if(LocaleController.isRTL) {
                goto label_62;
            }

            v15 = 17;
        }

    label_62:
        v0.addView(((View)v10), LayoutHelper.createFrame(v11, v12, v13, v14_1, v16, ((float)v15), 0f));
        v0.valueTextView = new TextView(v1);
        v10 = v0.valueTextView;
        String v11_1 = v2 == 1 ? "dialogTextBlue" : "windowBackgroundWhiteValueText";
        v10.setTextColor(Theme.getColor(v11_1));
        v0.valueTextView.setTextSize(1, v5_1);
        v0.valueTextView.setLines(1);
        v0.valueTextView.setMaxLines(1);
        v0.valueTextView.setSingleLine(true);
        v0.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v5_2 = v0.valueTextView;
        int v10_1 = LocaleController.isRTL ? 3 : 5;
        v5_2.setGravity(v10_1 | 16);
        v5_2 = v0.valueTextView;
        v10_1 = -2;
        float v11_2 = -1f;
        int v12_1 = LocaleController.isRTL ? 3 : 5;
        v0.addView(((View)v5_2), LayoutHelper.createFrame(v10_1, v11_2, v12_1 | 48, 17f, 0f, 17f, 0f));
        if(v2 == 1) {
        }
        else {
            v4 = false;
        }

        v0.checkBox = new CheckBoxSquare(v1, v4);
        if(v2 == v3_1) {
            v1_1 = v0.checkBox;
            int v9 = 18;
            float v10_2 = 18f;
            if(LocaleController.isRTL) {
                v7 = 5;
            }

            v2_1 = LayoutHelper.createFrame(v9, v10_2, v7 | 48, 0f, 15f, 0f, 0f);
        }
        else {
            v1_1 = v0.checkBox;
            v10_1 = 18;
            v11_2 = 18f;
            if(LocaleController.isRTL) {
                v7 = 5;
            }

            v12_1 = v7 | 48;
            v2 = LocaleController.isRTL ? 0 : 17;
            float v13_1 = ((float)v2);
            v14_1 = 15f;
            if(LocaleController.isRTL) {
            }
            else {
                v6 = 0;
            }

            v2_1 = LayoutHelper.createFrame(v10_1, v11_2, v12_1, v13_1, v14_1, ((float)v6), 0f);
        }

        v0.addView(((View)v1_1), ((ViewGroup$LayoutParams)v2_1));
    }

    public CheckBoxSquare getCheckBox() {
        return this.checkBox;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg5, int arg6) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg5), AndroidUtilities.dp(48f) + this.needDivider);
        arg5 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight() - AndroidUtilities.dp(34f);
        this.valueTextView.measure(View$MeasureSpec.makeMeasureSpec(arg5 / 2, -2147483648), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg5 - this.valueTextView.getMeasuredWidth() - AndroidUtilities.dp(8f), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
        this.checkBox.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(18f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(18f), 1073741824));
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.checkBox.setChecked(arg2, arg3);
    }

    public void setEnabled(boolean arg5) {
        super.setEnabled(arg5);
        TextView v0 = this.textView;
        float v1 = 0.5f;
        float v3 = arg5 ? 1f : 0.5f;
        v0.setAlpha(v3);
        v0 = this.valueTextView;
        v3 = arg5 ? 1f : 0.5f;
        v0.setAlpha(v3);
        CheckBoxSquare v0_1 = this.checkBox;
        if(arg5) {
            v1 = 1f;
        }

        v0_1.setAlpha(v1);
    }

    public void setText(String arg2, String arg3, boolean arg4, boolean arg5) {
        this.textView.setText(((CharSequence)arg2));
        this.checkBox.setChecked(arg4, false);
        this.valueTextView.setText(((CharSequence)arg3));
        this.needDivider = arg5;
        this.setWillNotDraw((((int)arg5)) ^ 1);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

