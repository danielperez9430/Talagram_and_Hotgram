package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

public class ChatLoadingCell extends FrameLayout {
    private FrameLayout frameLayout;
    private RadialProgressView progressBar;

    public ChatLoadingCell(Context arg4) {
        super(arg4);
        this.frameLayout = new FrameLayout(arg4);
        this.frameLayout.setBackgroundResource(2131231592);
        this.frameLayout.getBackground().setColorFilter(Theme.colorFilter);
        this.addView(this.frameLayout, LayoutHelper.createFrame(36, 36, 17));
        this.progressBar = new RadialProgressView(arg4);
        this.progressBar.setSize(AndroidUtilities.dp(28f));
        this.progressBar.setProgressColor(Theme.getColor("chat_serviceText"));
        this.frameLayout.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44f), 1073741824));
    }

    public void setProgressVisible(boolean arg2) {
        FrameLayout v0 = this.frameLayout;
        int v2 = arg2 ? 0 : 4;
        v0.setVisibility(v2);
    }
}

