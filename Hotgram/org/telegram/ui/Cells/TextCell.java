package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

public class TextCell extends FrameLayout {
    private ImageView imageView;
    private SimpleTextView textView;
    private ImageView valueImageView;
    private SimpleTextView valueTextView;

    public TextCell(Context arg6) {
        super(arg6);
        this.textView = new SimpleTextView(arg6);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        int v1 = 16;
        this.textView.setTextSize(v1);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        SimpleTextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        this.addView(this.textView);
        this.valueTextView = new SimpleTextView(arg6);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteValueText"));
        this.valueTextView.setTextSize(v1);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface(""));
        v0 = this.valueTextView;
        if(LocaleController.isRTL) {
        }
        else {
            v3 = 5;
        }

        v0.setGravity(v3);
        this.addView(this.valueTextView);
        this.imageView = new ImageView(arg6);
        this.imageView.setScaleType(ImageView$ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayIcon"), PorterDuff$Mode.MULTIPLY));
        this.addView(this.imageView);
        this.valueImageView = new ImageView(arg6);
        this.valueImageView.setScaleType(ImageView$ScaleType.CENTER);
        this.addView(this.valueImageView);
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    public ImageView getValueImageView() {
        return this.valueImageView;
    }

    public SimpleTextView getValueTextView() {
        return this.valueTextView;
    }

    protected void onLayout(boolean arg4, int arg5, int arg6, int arg7, int arg8) {
        arg8 -= arg6;
        arg7 -= arg5;
        int v4 = (arg8 - this.valueTextView.getTextHeight()) / 2;
        float v6 = 24f;
        arg5 = LocaleController.isRTL ? AndroidUtilities.dp(v6) : 0;
        this.valueTextView.layout(arg5, v4, this.valueTextView.getMeasuredWidth() + arg5, this.valueTextView.getMeasuredHeight() + v4);
        v4 = (arg8 - this.textView.getTextHeight()) / 2;
        float v0 = 71f;
        arg5 = LocaleController.isRTL ? this.getMeasuredWidth() - this.textView.getMeasuredWidth() - AndroidUtilities.dp(v0) : AndroidUtilities.dp(v0);
        this.textView.layout(arg5, v4, this.textView.getMeasuredWidth() + arg5, this.textView.getMeasuredHeight() + v4);
        v4 = AndroidUtilities.dp(5f);
        v0 = 16f;
        arg5 = !LocaleController.isRTL ? AndroidUtilities.dp(v0) : arg7 - this.imageView.getMeasuredWidth() - AndroidUtilities.dp(v0);
        this.imageView.layout(arg5, v4, this.imageView.getMeasuredWidth() + arg5, this.imageView.getMeasuredHeight() + v4);
        arg8 = (arg8 - this.valueImageView.getMeasuredHeight()) / 2;
        v4 = LocaleController.isRTL ? AndroidUtilities.dp(v6) : arg7 - this.valueImageView.getMeasuredWidth() - AndroidUtilities.dp(v6);
        this.valueImageView.layout(v4, arg8, this.valueImageView.getMeasuredWidth() + v4, this.valueImageView.getMeasuredHeight() + arg8);
    }

    protected void onMeasure(int arg8, int arg9) {
        arg8 = View$MeasureSpec.getSize(arg8);
        int v0 = AndroidUtilities.dp(48f);
        this.valueTextView.measure(View$MeasureSpec.makeMeasureSpec(arg8 - AndroidUtilities.dp(24f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), 1073741824));
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg8 - AndroidUtilities.dp(95f) - this.valueTextView.getTextWidth(), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), 1073741824));
        this.imageView.measure(View$MeasureSpec.makeMeasureSpec(arg8, -2147483648), View$MeasureSpec.makeMeasureSpec(v0, -2147483648));
        this.valueImageView.measure(View$MeasureSpec.makeMeasureSpec(arg8, -2147483648), View$MeasureSpec.makeMeasureSpec(v0, -2147483648));
        this.setMeasuredDimension(arg8, AndroidUtilities.dp(48f));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(null);
        this.imageView.setVisibility(4);
        this.valueTextView.setVisibility(4);
        this.valueImageView.setVisibility(4);
    }

    public void setTextAndIcon(String arg2, int arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(null);
        this.imageView.setImageResource(arg3);
        this.imageView.setVisibility(0);
        this.valueTextView.setVisibility(4);
        this.valueImageView.setVisibility(4);
        this.imageView.setPadding(0, AndroidUtilities.dp(7f), 0, 0);
    }

    public void setTextAndValue(String arg2, String arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        this.valueTextView.setVisibility(0);
        this.imageView.setVisibility(4);
        this.valueImageView.setVisibility(4);
    }

    public void setTextAndValueAndIcon(String arg2, String arg3, int arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        this.valueTextView.setVisibility(0);
        this.valueImageView.setVisibility(4);
        this.imageView.setVisibility(0);
        this.imageView.setPadding(0, AndroidUtilities.dp(7f), 0, 0);
        this.imageView.setImageResource(arg4);
    }

    public void setTextAndValueDrawable(String arg2, Drawable arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(null);
        this.valueImageView.setVisibility(0);
        this.valueImageView.setImageDrawable(arg3);
        this.valueTextView.setVisibility(4);
        this.imageView.setVisibility(4);
        this.imageView.setPadding(0, AndroidUtilities.dp(7f), 0, 0);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

