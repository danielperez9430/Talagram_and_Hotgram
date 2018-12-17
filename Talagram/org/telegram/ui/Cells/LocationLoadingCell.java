package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

public class LocationLoadingCell extends FrameLayout {
    private RadialProgressView progressBar;
    private TextView textView;

    public LocationLoadingCell(Context arg5) {
        super(arg5);
        this.progressBar = new RadialProgressView(arg5);
        this.addView(this.progressBar, LayoutHelper.createFrame(-2, -2, 17));
        this.textView = new TextView(arg5);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        this.textView.setTextSize(1, 16f);
        this.textView.setText(LocaleController.getString("NoResult", 2131625283));
        this.addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(((int)((((float)AndroidUtilities.dp(56f))) * 2.5f)), 1073741824));
    }

    public void setLoading(boolean arg5) {
        RadialProgressView v0 = this.progressBar;
        int v1 = 4;
        int v3 = arg5 ? 0 : 4;
        v0.setVisibility(v3);
        TextView v0_1 = this.textView;
        if(arg5) {
        }
        else {
            v1 = 0;
        }

        v0_1.setVisibility(v1);
    }
}

