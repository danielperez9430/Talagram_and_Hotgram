package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextDetailCell extends FrameLayout {
    private ImageView imageView;
    private boolean multiline;
    private TextView textView;
    private TextView valueTextView;

    public TextDetailCell(Context arg15) {
        super(arg15);
        this.textView = new TextView(arg15);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        TextView v0 = this.textView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v3);
        v0 = this.textView;
        int v6 = -2;
        float v7 = -2f;
        int v8 = LocaleController.isRTL ? 5 : 3;
        float v9 = LocaleController.isRTL ? 16f : 71f;
        float v10 = 10f;
        float v11 = LocaleController.isRTL ? 71f : 16f;
        this.addView(((View)v0), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        this.valueTextView = new TextView(arg15);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.valueTextView.setTextSize(1, 13f);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        v0 = this.valueTextView;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1);
        v0 = this.valueTextView;
        v6 = -2;
        v7 = -2f;
        v8 = LocaleController.isRTL ? 5 : 3;
        v9 = LocaleController.isRTL ? 16f : 71f;
        v10 = 35f;
        v11 = LocaleController.isRTL ? 71f : 16f;
        this.addView(((View)v0), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        this.imageView = new ImageView(arg15);
        this.imageView.setScaleType(ImageView$ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayIcon"), PorterDuff$Mode.MULTIPLY));
        ImageView v15 = this.imageView;
        v6 = -2;
        v7 = -2f;
        if(LocaleController.isRTL) {
            v4 = 5;
        }

        v8 = v4 | 48;
        v9 = LocaleController.isRTL ? 0f : 16f;
        v10 = 11f;
        v11 = LocaleController.isRTL ? 16f : 0f;
        this.addView(((View)v15), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        if(this.multiline) {
            int v1 = this.textView.getMeasuredHeight() + AndroidUtilities.dp(13f);
            this.valueTextView.layout(this.valueTextView.getLeft(), v1, this.valueTextView.getRight(), this.valueTextView.getMeasuredHeight() + v1);
        }
    }

    protected void onMeasure(int arg8, int arg9) {
        float v6 = 64f;
        if(!this.multiline) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg8), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v6), 1073741824));
        }
        else {
            this.measureChildWithMargins(this.textView, arg8, 0, arg9, 0);
            this.measureChildWithMargins(this.valueTextView, arg8, 0, arg9, 0);
            this.measureChildWithMargins(this.imageView, arg8, 0, arg9, 0);
            this.setMeasuredDimension(View$MeasureSpec.getSize(arg8), Math.max(AndroidUtilities.dp(v6), this.textView.getMeasuredHeight() + this.valueTextView.getMeasuredHeight() + AndroidUtilities.dp(20f)));
        }
    }

    public void setMultiline(boolean arg2) {
        boolean v0;
        TextView v2;
        this.multiline = arg2;
        if(this.multiline) {
            v2 = this.textView;
            v0 = false;
        }
        else {
            v0 = true;
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            v2 = this.textView;
        }

        v2.setSingleLine(v0);
    }

    public void setTextAndValue(String arg2, String arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        this.imageView.setVisibility(4);
    }

    public void setTextAndValueAndIcon(String arg10, String arg11, int arg12, int arg13) {
        float v6;
        this.textView.setText(((CharSequence)arg10));
        this.valueTextView.setText(((CharSequence)arg11));
        this.imageView.setVisibility(0);
        this.imageView.setImageResource(arg12);
        int v10 = 3;
        if(arg13 == 0) {
            ImageView v13 = this.imageView;
            int v1 = -2;
            float v2 = -2f;
            if(LocaleController.isRTL) {
                v10 = 5;
            }

            int v3 = v10 | 16;
            float v4 = LocaleController.isRTL ? 0f : 16f;
            v6 = LocaleController.isRTL ? 16f : 0f;
            v13.setLayoutParams(LayoutHelper.createFrame(v1, v2, v3, v4, 0f, v6, 0f));
        }
        else {
            ImageView v1_1 = this.imageView;
            int v2_1 = -2;
            float v3_1 = -2f;
            if(LocaleController.isRTL) {
                v10 = 5;
            }

            int v4_1 = v10 | 48;
            float v5 = LocaleController.isRTL ? 0f : 16f;
            v6 = ((float)arg13);
            float v7 = LocaleController.isRTL ? 16f : 0f;
            v1_1.setLayoutParams(LayoutHelper.createFrame(v2_1, v3_1, v4_1, v5, v6, v7, 0f));
        }
    }
}

