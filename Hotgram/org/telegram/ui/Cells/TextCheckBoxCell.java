package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

public class TextCheckBoxCell extends FrameLayout {
    private CheckBoxSquare checkBox;
    private boolean needDivider;
    private TextView textView;

    public TextCheckBoxCell(Context arg13) {
        super(arg13);
        this.textView = new TextView(arg13);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v0 = this.textView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1 | 16);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        v1 = LocaleController.isRTL ? 5 : 3;
        int v6 = v1 | 48;
        float v1_1 = LocaleController.isRTL ? 64f : 17f;
        float v10 = LocaleController.isRTL ? 17f : 64f;
        this.addView(((View)v0), LayoutHelper.createFrame(v4, v5, v6, v1_1, 0f, v10, 0f));
        this.checkBox = new CheckBoxSquare(arg13, false);
        this.checkBox.setDuplicateParentStateEnabled(false);
        this.checkBox.setFocusable(false);
        this.checkBox.setFocusableInTouchMode(false);
        this.checkBox.setClickable(false);
        CheckBoxSquare v13 = this.checkBox;
        v4 = 18;
        v5 = 18f;
        if(LocaleController.isRTL) {
        }
        else {
            v2 = 5;
        }

        this.addView(((View)v13), LayoutHelper.createFrame(v4, v5, v2 | 16, 19f, 0f, 19f, 0f));
    }

    public void invalidate() {
        super.invalidate();
        this.checkBox.invalidate();
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
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setChecked(boolean arg3) {
        this.checkBox.setChecked(arg3, true);
    }

    public void setTextAndCheck(String arg2, boolean arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.checkBox.setChecked(arg3, false);
        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }
}

