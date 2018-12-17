package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.LayoutHelper;

public class TextColorThemeCell extends FrameLayout {
    private float alpha;
    private static Paint colorPaint;
    private int currentColor;
    private boolean needDivider;
    private TextView textView;

    public TextColorThemeCell(Context arg11) {
        super(arg11);
        this.alpha = 1f;
        if(TextColorThemeCell.colorPaint == null) {
            TextColorThemeCell.colorPaint = new Paint(1);
        }

        this.textView = new TextView(arg11);
        this.textView.setTextColor(-14606047);
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        this.textView.setPadding(0, 0, 0, AndroidUtilities.dp(3f));
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        int v5 = v1 | 48;
        v1 = 53;
        v0 = LocaleController.isRTL ? 17 : 53;
        float v6 = ((float)v0);
        if(LocaleController.isRTL) {
        }
        else {
            v1 = 17;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v5, v6, 0f, ((float)v1), 0f));
    }

    public float getAlpha() {
        return this.alpha;
    }

    protected void onDraw(Canvas arg5) {
        if(this.currentColor != 0) {
            TextColorThemeCell.colorPaint.setColor(this.currentColor);
            TextColorThemeCell.colorPaint.setAlpha(((int)(this.alpha * 255f)));
            float v1 = 28f;
            int v0 = !LocaleController.isRTL ? AndroidUtilities.dp(v1) : this.getMeasuredWidth() - AndroidUtilities.dp(v1);
            float v0_1 = ((float)v0);
            arg5.drawCircle(v0_1, ((float)(this.getMeasuredHeight() / 2)), ((float)AndroidUtilities.dp(10f)), TextColorThemeCell.colorPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setAlpha(float arg1) {
        this.alpha = arg1;
        this.invalidate();
    }

    public void setTextAndColor(String arg2, int arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.currentColor = arg3;
        boolean v2 = (this.needDivider) || this.currentColor != 0 ? false : true;
        this.setWillNotDraw(v2);
        this.invalidate();
    }
}

