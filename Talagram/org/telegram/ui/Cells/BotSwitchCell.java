package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class BotSwitchCell extends FrameLayout {
    private TextView textView;

    public BotSwitchCell(Context arg11) {
        super(arg11);
        this.textView = new TextView(arg11);
        this.textView.setTextSize(1, 15f);
        this.textView.setTextColor(Theme.getColor("chat_botSwitchToInlineText"));
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        this.textView.setMaxLines(1);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0);
        v11 = this.textView;
        int v3 = -2;
        float v4 = -2f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 16, 14f, 0f, 14f, 0f));
    }

    public TextView getTextView() {
        return this.textView;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36f), 1073741824));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }
}

