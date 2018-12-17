package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextPriceCell extends FrameLayout {
    private int dotLength;
    private String dotstring;
    private TextView textView;
    private TextView valueTextView;

    public TextPriceCell(Context arg14) {
        super(arg14);
        String v0 = LocaleController.isRTL ? " ." : ". ";
        this.dotstring = v0;
        this.setWillNotDraw(false);
        this.textView = new TextView(arg14);
        float v1 = 16f;
        this.textView.setTextSize(1, v1);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0_1 = this.textView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v3 | 16);
        v0_1 = this.textView;
        int v6 = -2;
        float v7 = -1f;
        v3 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v6, v7, v3 | 48, 17f, 0f, 17f, 0f));
        this.valueTextView = new TextView(arg14);
        this.valueTextView.setTextSize(1, v1);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v14 = this.valueTextView;
        int v0_2 = LocaleController.isRTL ? 3 : 5;
        v14.setGravity(v0_2 | 16);
        v14 = this.valueTextView;
        v6 = -2;
        v7 = -1f;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 5;
        }

        this.addView(((View)v14), LayoutHelper.createFrame(v6, v7, v4 | 48, 17f, 0f, 17f, 0f));
    }

    protected void onDraw(Canvas arg1) {
    }

    protected void onMeasure(int arg5, int arg6) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg5), AndroidUtilities.dp(40f));
        arg5 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight() - AndroidUtilities.dp(34f);
        this.valueTextView.measure(View$MeasureSpec.makeMeasureSpec(arg5 / 2, -2147483648), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(arg5 - this.valueTextView.getMeasuredWidth() - AndroidUtilities.dp(8f), -2147483648), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
        this.dotLength = ((int)Math.ceil(((double)this.textView.getPaint().measureText(this.dotstring))));
    }

    public void setTextAndValue(String arg2, String arg3, boolean arg4) {
        Typeface v3_1;
        int v3;
        TextView v2;
        this.textView.setText(((CharSequence)arg2));
        if(arg3 != null) {
            this.valueTextView.setText(((CharSequence)arg3));
            v2 = this.valueTextView;
            v3 = 0;
        }
        else {
            v2 = this.valueTextView;
            v3 = 4;
        }

        v2.setVisibility(v3);
        if(arg4) {
            this.setTag("windowBackgroundWhiteBlackText");
            this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            v2 = this.valueTextView;
            v3_1 = AndroidUtilities.getTypeface("fonts/rmedium.ttf");
        }
        else {
            this.setTag("windowBackgroundWhiteGrayText2");
            this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
            this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
            this.textView.setTypeface(Typeface.DEFAULT);
            v2 = this.valueTextView;
            v3_1 = Typeface.DEFAULT;
        }

        v2.setTypeface(v3_1);
        this.requestLayout();
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }

    public void setTextValueColor(int arg2) {
        this.valueTextView.setTextColor(arg2);
    }
}

