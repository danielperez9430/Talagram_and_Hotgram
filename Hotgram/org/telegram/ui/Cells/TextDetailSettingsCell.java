package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextDetailSettingsCell extends FrameLayout {
    private boolean multiline;
    private boolean needDivider;
    private TextView textView;
    private TextView valueTextView;

    public TextDetailSettingsCell(Context arg13) {
        super(arg13);
        this.textView = new TextView(arg13);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        TextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2 | 16);
        v0 = this.textView;
        int v5 = -2;
        float v6 = -2f;
        v2 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v2 | 48, 17f, 10f, 17f, 0f));
        this.valueTextView = new TextView(arg13);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.valueTextView.setTextSize(1, 13f);
        TextView v13 = this.valueTextView;
        int v0_1 = LocaleController.isRTL ? 5 : 3;
        v13.setGravity(v0_1);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setPadding(0, 0, 0, 0);
        this.valueTextView.setTypeface(AndroidUtilities.getTypeface(""));
        v13 = this.valueTextView;
        v5 = -2;
        v6 = -2f;
        if(LocaleController.isRTL) {
            v3 = 5;
        }

        this.addView(((View)v13), LayoutHelper.createFrame(v5, v6, v3 | 48, 17f, 35f, 17f, 0f));
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    public void invalidate() {
        super.invalidate();
        this.textView.invalidate();
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        int v0 = 1073741824;
        if(!this.multiline) {
            arg3 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), v0);
            arg4 = View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64f) + this.needDivider, v0);
        }
        else {
            arg3 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), v0);
            arg4 = View$MeasureSpec.makeMeasureSpec(0, 0);
        }

        super.onMeasure(arg3, arg4);
    }

    public void setMultilineDetail(boolean arg3) {
        this.multiline = arg3;
        if(arg3) {
            this.valueTextView.setLines(0);
            this.valueTextView.setMaxLines(0);
            this.valueTextView.setSingleLine(false);
            this.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(12f));
        }
        else {
            this.valueTextView.setLines(1);
            this.valueTextView.setMaxLines(1);
            this.valueTextView.setSingleLine(true);
            this.valueTextView.setPadding(0, 0, 0, 0);
        }
    }

    public void setTextAndValue(String arg2, CharSequence arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(arg3);
        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }

    public void setTextWithEmojiAndValue(String arg5, CharSequence arg6, boolean arg7) {
        this.textView.setText(Emoji.replaceEmoji(((CharSequence)arg5), this.textView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14f), false));
        this.valueTextView.setText(arg6);
        this.needDivider = arg7;
        this.setWillNotDraw((((int)arg7)) ^ 1);
    }

    public void setValue(CharSequence arg2) {
        this.valueTextView.setText(arg2);
    }
}

