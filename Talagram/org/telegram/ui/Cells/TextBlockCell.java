package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextBlockCell extends FrameLayout {
    private boolean needDivider;
    private TextView textView;

    public TextBlockCell(Context arg11) {
        super(arg11);
        this.textView = new TextView(arg11);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        v11 = this.textView;
        int v3 = -1;
        float v4 = -2f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 17f, 10f, 17f, 10f));
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), arg3);
    }

    public void setText(String arg2, boolean arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.needDivider = arg3;
        this.setWillNotDraw((((int)arg3)) ^ 1);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

