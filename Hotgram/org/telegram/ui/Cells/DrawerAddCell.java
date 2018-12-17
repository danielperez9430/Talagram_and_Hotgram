package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class DrawerAddCell extends FrameLayout {
    private TextView textView;

    public DrawerAddCell(Context arg11) {
        super(arg11);
        this.textView = new TextView(arg11);
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
        this.textView.setTextSize(1, 15f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        this.textView.setCompoundDrawablePadding(AndroidUtilities.dp(34f));
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 23f, 0f, 16f, 0f));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
        this.textView.setText(LocaleController.getString("AddAccount", 2131624011));
        Drawable v0 = this.getResources().getDrawable(2131230811);
        if(v0 != null) {
            v0.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chats_menuItemIcon"), PorterDuff$Mode.MULTIPLY));
        }

        this.textView.setCompoundDrawablesWithIntrinsicBounds(v0, null, null, null);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
    }
}

