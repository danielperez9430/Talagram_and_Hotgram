package org.telegram.ui.Cells;

import android.content.Context;
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

public class InviteTextCell extends FrameLayout {
    private ImageView imageView;
    private SimpleTextView textView;

    public InviteTextCell(Context arg4) {
        super(arg4);
        this.textView = new SimpleTextView(arg4);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(17);
        SimpleTextView v0 = this.textView;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1);
        this.addView(this.textView);
        this.imageView = new ImageView(arg4);
        this.imageView.setScaleType(ImageView$ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayIcon"), PorterDuff$Mode.MULTIPLY));
        this.addView(this.imageView);
    }

    public SimpleTextView getTextView() {
        return this.textView;
    }

    protected void onLayout(boolean arg3, int arg4, int arg5, int arg6, int arg7) {
        arg7 -= arg5;
        arg6 -= arg4;
        int v3 = (arg7 - this.textView.getTextHeight()) / 2;
        float v4 = !LocaleController.isRTL ? 71f : 24f;
        arg4 = AndroidUtilities.dp(v4);
        this.textView.layout(arg4, v3, this.textView.getMeasuredWidth() + arg4, this.textView.getMeasuredHeight() + v3);
        arg7 = (arg7 - this.imageView.getMeasuredHeight()) / 2;
        v4 = 20f;
        v3 = !LocaleController.isRTL ? AndroidUtilities.dp(v4) : arg6 - this.imageView.getMeasuredWidth() - AndroidUtilities.dp(v4);
        this.imageView.layout(v3, arg7, this.imageView.getMeasuredWidth() + v3, this.imageView.getMeasuredHeight() + arg7);
    }

    protected void onMeasure(int arg7, int arg8) {
        arg7 = View$MeasureSpec.getSize(arg7);
        int v0 = AndroidUtilities.dp(72f);
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg7 - AndroidUtilities.dp(95f), -2147483648), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(20f), 1073741824));
        this.imageView.measure(View$MeasureSpec.makeMeasureSpec(arg7, -2147483648), View$MeasureSpec.makeMeasureSpec(v0, -2147483648));
        this.setMeasuredDimension(arg7, AndroidUtilities.dp(72f));
    }

    public void setTextAndIcon(String arg2, int arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.imageView.setImageResource(arg3);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

