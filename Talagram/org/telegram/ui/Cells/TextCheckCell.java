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
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

public class TextCheckCell extends FrameLayout {
    private Switch checkBox;
    private boolean isMultiline;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public TextCheckCell(Context arg15) {
        super(arg15);
        this.textView = new TextView(arg15);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        TextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2 | 16);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.textView;
        int v5 = -1;
        float v6 = -1f;
        v2 = LocaleController.isRTL ? 5 : 3;
        int v7 = v2 | 48;
        float v8 = LocaleController.isRTL ? 64f : 17f;
        float v10 = LocaleController.isRTL ? 17f : 64f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, 0f, v10, 0f));
        this.valueTextView = new TextView(arg15);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.valueTextView.setTextSize(1, 13f);
        v0 = this.valueTextView;
        v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface(""));
        this.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.valueTextView;
        v5 = -2;
        v6 = -2f;
        v2 = LocaleController.isRTL ? 5 : 3;
        v7 = v2 | 48;
        v8 = LocaleController.isRTL ? 64f : 17f;
        float v9 = 35f;
        v10 = LocaleController.isRTL ? 17f : 64f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.checkBox = new Switch(arg15);
        this.checkBox.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        Switch v15 = this.checkBox;
        v5 = -2;
        v6 = -2f;
        if(LocaleController.isRTL) {
        }
        else {
            v3 = 5;
        }

        this.addView(((View)v15), LayoutHelper.createFrame(v5, v6, v3 | 16, 14f, 0f, 14f, 0f));
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        int v0 = 1073741824;
        if(this.isMultiline) {
            arg3 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), v0);
            arg4 = View$MeasureSpec.makeMeasureSpec(0, 0);
        }
        else {
            arg3 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), v0);
            float v4 = this.valueTextView.getVisibility() == 0 ? 64f : 48f;
            arg4 = View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v4) + this.needDivider, v0);
        }

        super.onMeasure(arg3, arg4);
    }

    public void setChecked(boolean arg2) {
        this.checkBox.setChecked(arg2);
    }

    public void setEnabled(boolean arg2) {
        float v0;
        TextView v2;
        super.setEnabled(arg2);
        if(arg2) {
            v2 = this.textView;
            v0 = 1f;
        }
        else {
            v2 = this.textView;
            v0 = 0.5f;
        }

        v2.setAlpha(v0);
        this.valueTextView.setAlpha(v0);
    }

    public void setTextAndCheck(String arg2, boolean arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.isMultiline = false;
        this.checkBox.setChecked(arg3);
        this.needDivider = arg4;
        this.valueTextView.setVisibility(8);
        ViewGroup$LayoutParams v3 = this.textView.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).height = -1;
        ((FrameLayout$LayoutParams)v3).topMargin = 0;
        this.textView.setLayoutParams(v3);
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }

    public void setTextAndValueAndCheck(String arg2, String arg3, boolean arg4, boolean arg5, boolean arg6) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        this.checkBox.setChecked(arg4);
        this.needDivider = arg6;
        this.valueTextView.setVisibility(0);
        this.isMultiline = arg5;
        if(arg5) {
            this.valueTextView.setLines(0);
            this.valueTextView.setMaxLines(0);
            this.valueTextView.setSingleLine(false);
            this.valueTextView.setEllipsize(null);
            this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(11f));
        }
        else {
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
            this.valueTextView.setPadding(0, 0, 0, 0);
        }

        ViewGroup$LayoutParams v3 = this.textView.getLayoutParams();
        ((FrameLayout$LayoutParams)v3).height = -2;
        ((FrameLayout$LayoutParams)v3).topMargin = AndroidUtilities.dp(10f);
        this.textView.setLayoutParams(v3);
        this.setWillNotDraw(1 ^ (((int)arg6)));
    }
}

