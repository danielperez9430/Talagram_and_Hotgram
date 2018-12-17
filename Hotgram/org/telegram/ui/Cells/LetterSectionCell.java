package org.telegram.ui.Cells;

import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class LetterSectionCell extends FrameLayout {
    private TextView textView;

    public LetterSectionCell(Context arg3) {
        super(arg3);
        this.setLayoutParams(new ViewGroup$LayoutParams(AndroidUtilities.dp(54f), AndroidUtilities.dp(64f)));
        this.textView = new TextView(this.getContext());
        this.textView.setTextSize(1, 22f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText4"));
        this.textView.setGravity(17);
        this.addView(this.textView, LayoutHelper.createFrame(-1, -1f));
    }

    public void setCellHeight(int arg3) {
        this.setLayoutParams(new ViewGroup$LayoutParams(AndroidUtilities.dp(54f), arg3));
    }

    public void setLetter(String arg2) {
        this.textView.setText(arg2.toUpperCase());
    }
}

