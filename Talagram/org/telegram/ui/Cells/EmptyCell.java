package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;

public class EmptyCell extends FrameLayout {
    int cellHeight;

    public EmptyCell(Context arg2) {
        this(arg2, 8);
    }

    public EmptyCell(Context arg1, int arg2) {
        super(arg1);
        this.cellHeight = arg2;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(this.cellHeight, 1073741824));
    }

    public void setHeight(int arg1) {
        this.cellHeight = arg1;
        this.requestLayout();
    }
}

