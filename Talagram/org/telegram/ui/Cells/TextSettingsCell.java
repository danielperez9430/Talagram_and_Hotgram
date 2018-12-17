package org.telegram.ui.Cells;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextSettingsCell extends FrameLayout {
    private boolean needDivider;
    private TextView textView;
    private ImageView valueImageView;
    private TextView valueTextView;

    public TextSettingsCell(Context arg14) {
        super(arg14);
        this.textView = new TextView(arg14);
        float v1 = 16f;
        this.textView.setTextSize(1, v1);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0 = this.textView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v3 | 16);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0 = this.textView;
        int v6 = -1;
        float v7 = -1f;
        v3 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0), LayoutHelper.createFrame(v6, v7, v3 | 48, 17f, 0f, 17f, 0f));
        this.valueTextView = new TextView(arg14);
        this.valueTextView.setTextSize(1, v1);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface(""));
        v0 = this.valueTextView;
        int v1_1 = LocaleController.isRTL ? 3 : 5;
        v0.setGravity(v1_1 | 16);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteValueText"));
        v0 = this.valueTextView;
        v6 = -2;
        v7 = -1f;
        v1_1 = LocaleController.isRTL ? 3 : 5;
        this.addView(((View)v0), LayoutHelper.createFrame(v6, v7, v1_1 | 48, 17f, 0f, 17f, 0f));
        this.valueImageView = new ImageView(arg14);
        this.valueImageView.setScaleType(ImageView$ScaleType.CENTER);
        this.valueImageView.setVisibility(4);
        this.valueImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayIcon"), PorterDuff$Mode.MULTIPLY));
        ImageView v14 = this.valueImageView;
        v6 = -2;
        v7 = -2f;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 5;
        }

        this.addView(((View)v14), LayoutHelper.createFrame(v6, v7, v4 | 16, 17f, 0f, 17f, 0f));
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg6, int arg7) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg6), AndroidUtilities.dp(48f) + this.needDivider);
        arg6 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight() - AndroidUtilities.dp(34f);
        arg7 = arg6 / 2;
        int v1 = -2147483648;
        int v2 = 1073741824;
        if(this.valueImageView.getVisibility() == 0) {
            this.valueImageView.measure(View$MeasureSpec.makeMeasureSpec(arg7, v1), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), v2));
        }

        if(this.valueTextView.getVisibility() == 0) {
            this.valueTextView.measure(View$MeasureSpec.makeMeasureSpec(arg7, v1), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), v2));
            arg6 = arg6 - this.valueTextView.getMeasuredWidth() - AndroidUtilities.dp(8f);
        }

        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg6, v2), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), v2));
    }

    public void setEnabled(boolean arg9, ArrayList arg10) {
        this.setEnabled(arg9);
        float v0 = 0.5f;
        if(arg10 != null) {
            TextView v2 = this.textView;
            String v3 = "alpha";
            float[] v5 = new float[1];
            float v6 = arg9 ? 1f : 0.5f;
            v5[0] = v6;
            arg10.add(ObjectAnimator.ofFloat(v2, v3, v5));
            if(this.valueTextView.getVisibility() == 0) {
                v2 = this.valueTextView;
                v3 = "alpha";
                v5 = new float[1];
                v6 = arg9 ? 1f : 0.5f;
                v5[0] = v6;
                arg10.add(ObjectAnimator.ofFloat(v2, v3, v5));
            }

            if(this.valueImageView.getVisibility() != 0) {
                return;
            }

            ImageView v2_1 = this.valueImageView;
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
            if(this.valueTextView.getVisibility() == 0) {
                v10 = this.valueTextView;
                v2_2 = arg9 ? 1f : 0.5f;
                v10.setAlpha(v2_2);
            }

            if(this.valueImageView.getVisibility() != 0) {
                return;
            }

            ImageView v10_1 = this.valueImageView;
            if(arg9) {
                v0 = 1f;
            }

            v10_1.setAlpha(v0);
        }
    }

    public void setText(String arg2, boolean arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setVisibility(4);
        this.valueImageView.setVisibility(4);
        this.needDivider = arg3;
        this.setWillNotDraw((((int)arg3)) ^ 1);
    }

    public void setTextAndIcon(String arg2, int arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        int v0 = 4;
        this.valueTextView.setVisibility(v0);
        if(arg3 != 0) {
            this.valueImageView.setVisibility(0);
            this.valueImageView.setImageResource(arg3);
        }
        else {
            this.valueImageView.setVisibility(v0);
        }

        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }

    public void setTextAndValue(String arg2, String arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        int v0 = 4;
        this.valueImageView.setVisibility(v0);
        if(arg3 != null) {
            this.valueTextView.setText(((CharSequence)arg3));
            this.valueTextView.setVisibility(0);
        }
        else {
            this.valueTextView.setVisibility(v0);
        }

        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
        this.requestLayout();
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }

    public void setTextValueColor(int arg2) {
        this.valueTextView.setTextColor(arg2);
    }
}

