package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class ShadowSectionCell extends View {
    private int size;

    public ShadowSectionCell(Context arg3) {
        super(arg3);
        this.size = 12;
        this.setBackgroundDrawable(Theme.getThemedDrawable(arg3, 2131231087, "windowBackgroundGrayShadow"));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(((float)this.size)), 1073741824));
    }

    public void setSize(int arg1) {
        this.size = arg1;
    }
}

