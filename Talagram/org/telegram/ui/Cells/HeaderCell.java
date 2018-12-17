package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class HeaderCell extends FrameLayout {
    private TextView textView;

    public HeaderCell(Context arg11) {
        super(arg11);
        this.textView = new TextView(this.getContext());
        this.textView.setTextSize(1, 15f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlueHeader"));
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 17f, 15f, 17f, 0f));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlueHeader"));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(38f), 1073741824));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }
}

