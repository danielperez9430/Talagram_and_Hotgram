package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class HashtagSearchCell extends TextView {
    private boolean needDivider;

    public HashtagSearchCell(Context arg3) {
        super(arg3);
        this.setGravity(16);
        this.setPadding(AndroidUtilities.dp(16f), 0, AndroidUtilities.dp(16f), 0);
        this.setTextSize(1, 17f);
        this.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
    }

    protected void onDraw(Canvas arg8) {
        super.onDraw(arg8);
        if(this.needDivider) {
            arg8.drawLine(0f, ((float)(this.getHeight() - 1)), ((float)this.getWidth()), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg1), AndroidUtilities.dp(48f) + 1);
    }

    public void setNeedDivider(boolean arg1) {
        this.needDivider = arg1;
    }
}

