package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class EditTextSettingsCell extends FrameLayout {
    private boolean needDivider;
    private EditText textView;

    public EditTextSettingsCell(Context arg11) {
        super(arg11);
        this.textView = new EditText(arg11);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setHintTextColor(Theme.getColor("windowBackgroundWhiteHintText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        EditText v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        this.textView.setBackgroundDrawable(null);
        this.textView.setPadding(0, 0, 0, 0);
        this.textView.setInputType(this.textView.getInputType() | 16384);
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 17f, 0f, 17f, 0f));
    }

    public TextView getTextView() {
        return this.textView;
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg3), AndroidUtilities.dp(48f) + this.needDivider);
        this.textView.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight() - AndroidUtilities.dp(34f), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
    }

    public void setEnabled(boolean arg1, ArrayList arg2) {
        this.setEnabled(arg1);
    }

    public void setText(String arg2, boolean arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.needDivider = arg3;
        this.setWillNotDraw((((int)arg3)) ^ 1);
    }

    public void setTextAndHint(String arg2, String arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.textView.setHint(((CharSequence)arg3));
        this.needDivider = arg4;
        this.setWillNotDraw((((int)arg4)) ^ 1);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

