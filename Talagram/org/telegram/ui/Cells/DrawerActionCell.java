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
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class DrawerActionCell extends FrameLayout {
    private View divider;
    private TextView textView;

    public DrawerActionCell(Context arg12) {
        super(arg12);
        this.textView = new TextView(arg12);
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
        this.textView.setTextSize(1, 15f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v0 = this.textView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1 | 16);
        this.textView.setCompoundDrawablePadding(AndroidUtilities.dp(29f));
        v0 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        if(LocaleController.isRTL) {
            v2 = 5;
        }

        this.addView(((View)v0), LayoutHelper.createFrame(v4, v5, v2 | 48, 19f, 0f, 16f, 0f));
        this.divider = new View(arg12);
        this.divider.setBackgroundColor(arg12.getResources().getColor(2131099856));
        this.addView(this.divider, LayoutHelper.createFrame(-1, 0.6f, 80, 30f, 10f, 0f, 0f));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
    }

    public void setTextAndIcon(String arg5, int arg6, boolean arg7, boolean arg8) {
        try {
            this.textView.setText(((CharSequence)arg5));
            Drawable v5_1 = this.getResources().getDrawable(arg6).mutate();
            if(v5_1 != null) {
                v5_1.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chats_menuItemIcon"), PorterDuff$Mode.MULTIPLY));
            }

            Drawable v0 = null;
            Drawable v6 = MessagesController.getGlobalMainSettings().getString("theme", "").contentEquals("Dark") ? this.getResources().getDrawable(2131231573).mutate() : v0;
            if(v6 != null) {
                v6.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chats_menuItemIcon"), PorterDuff$Mode.MULTIPLY));
            }

            TextView v1 = this.textView;
            if(arg8) {
            }
            else {
                v6 = v0;
            }

            v1.setCompoundDrawablesWithIntrinsicBounds(v5_1, v0, v6, v0);
            if(arg7) {
                this.divider.setVisibility(0);
                return;
            }

            this.divider.setVisibility(8);
        }
        catch(Throwable v5) {
            FileLog.e(v5);
        }
    }
}

