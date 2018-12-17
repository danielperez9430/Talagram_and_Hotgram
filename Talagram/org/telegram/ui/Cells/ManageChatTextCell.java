package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;

public class ManageChatTextCell extends FrameLayout {
    private boolean divider;
    private ImageView imageView;
    private SimpleTextView textView;
    private SimpleTextView valueTextView;

    public ManageChatTextCell(Context arg6) {
        super(arg6);
        this.textView = new SimpleTextView(arg6);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        int v1 = 16;
        this.textView.setTextSize(v1);
        SimpleTextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        this.addView(this.textView);
        this.valueTextView = new SimpleTextView(arg6);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteValueText"));
        this.valueTextView.setTextSize(v1);
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
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    public SimpleTextView getValueTextView() {
        return this.valueTextView;
    }

    protected void onDraw(Canvas arg8) {
        if(this.divider) {
            arg8.drawLine(((float)AndroidUtilities.dp(71f)), ((float)(this.getMeasuredHeight() - 1)), ((float)this.getMeasuredWidth()), ((float)(this.getMeasuredHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onLayout(boolean arg4, int arg5, int arg6, int arg7, int arg8) {
        arg8 -= arg6;
        arg7 -= arg5;
        int v4 = (arg8 - this.valueTextView.getTextHeight()) / 2;
        float v6 = 24f;
        arg5 = LocaleController.isRTL ? AndroidUtilities.dp(v6) : 0;
        this.valueTextView.layout(arg5, v4, this.valueTextView.getMeasuredWidth() + arg5, this.valueTextView.getMeasuredHeight() + v4);
        arg8 = (arg8 - this.textView.getTextHeight()) / 2;
        v4 = !LocaleController.isRTL ? AndroidUtilities.dp(71f) : AndroidUtilities.dp(v6);
        this.textView.layout(v4, arg8, this.textView.getMeasuredWidth() + v4, this.textView.getMeasuredHeight() + arg8);
        v4 = AndroidUtilities.dp(9f);
        v6 = 16f;
        arg5 = !LocaleController.isRTL ? AndroidUtilities.dp(v6) : arg7 - this.imageView.getMeasuredWidth() - AndroidUtilities.dp(v6);
        this.imageView.layout(arg5, v4, this.imageView.getMeasuredWidth() + arg5, this.imageView.getMeasuredHeight() + v4);
    }

    protected void onMeasure(int arg7, int arg8) {
        arg7 = View$MeasureSpec.getSize(arg7);
        arg8 = AndroidUtilities.dp(48f);
        this.valueTextView.measure(View$MeasureSpec.makeMeasureSpec(arg7 - AndroidUtilities.dp(24f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), 1073741824));
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg7 - AndroidUtilities.dp(95f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), 1073741824));
        this.imageView.measure(View$MeasureSpec.makeMeasureSpec(arg7, -2147483648), View$MeasureSpec.makeMeasureSpec(arg8, -2147483648));
        this.setMeasuredDimension(arg7, AndroidUtilities.dp(56f) + this.divider);
    }

    public void setText(String arg2, String arg3, int arg4, boolean arg5) {
        this.textView.setText(((CharSequence)arg2));
        if(arg3 != null) {
            this.valueTextView.setText(((CharSequence)arg3));
            this.valueTextView.setVisibility(0);
        }
        else {
            this.valueTextView.setVisibility(4);
        }

        this.imageView.setPadding(0, AndroidUtilities.dp(7f), 0, 0);
        this.imageView.setImageResource(arg4);
        this.divider = arg5;
        this.setWillNotDraw(this.divider ^ 1);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

