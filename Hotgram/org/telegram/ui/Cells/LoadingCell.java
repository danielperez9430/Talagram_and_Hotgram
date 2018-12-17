package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

public class LoadingCell extends FrameLayout {
    private RadialProgressView progressBar;

    public LoadingCell(Context arg3) {
        super(arg3);
        this.progressBar = new RadialProgressView(arg3);
        this.addView(this.progressBar, LayoutHelper.createFrame(-2, -2, 17));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(54f), 1073741824));
    }
}

