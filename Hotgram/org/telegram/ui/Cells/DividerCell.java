package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View$MeasureSpec;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class DividerCell extends View {
    public DividerCell(Context arg1) {
        super(arg1);
    }

    protected void onDraw(Canvas arg8) {
        arg8.drawLine(((float)this.getPaddingLeft()), ((float)AndroidUtilities.dp(8f)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)AndroidUtilities.dp(8f)), Theme.dividerPaint);
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(View$MeasureSpec.getSize(arg1), AndroidUtilities.dp(16f) + 1);
    }
}

