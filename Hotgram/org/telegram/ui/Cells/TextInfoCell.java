package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextInfoCell extends FrameLayout {
    private TextView textView;

    public TextInfoCell(Context arg8) {
        super(arg8);
        this.textView = new TextView(arg8);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText5"));
        this.textView.setTextSize(1, 13f);
        this.textView.setGravity(17);
        this.textView.setPadding(0, AndroidUtilities.dp(19f), 0, AndroidUtilities.dp(19f));
        this.addView(this.textView, LayoutHelper.createFrame(-2, -2f, 17, 17f, 0f, 17f, 0f));
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg1), 1073741824), View$MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }
}

