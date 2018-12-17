package org.telegram.ui.Components;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;

public class PickerBottomLayoutViewer extends FrameLayout {
    public TextView cancelButton;
    public TextView doneButton;
    public TextView doneButtonBadgeTextView;
    private boolean isDarkTheme;

    public PickerBottomLayoutViewer(Context arg2) {
        this(arg2, true);
    }

    public PickerBottomLayoutViewer(Context arg12, boolean arg13) {
        super(arg12);
        this.isDarkTheme = arg13;
        int v0 = -1;
        int v13 = this.isDarkTheme ? -15066598 : -1;
        this.setBackgroundColor(v13);
        this.cancelButton = new TextView(arg12);
        float v1 = 14f;
        this.cancelButton.setTextSize(1, v1);
        TextView v13_1 = this.cancelButton;
        int v4 = -15095832;
        int v3 = this.isDarkTheme ? -1 : -15095832;
        v13_1.setTextColor(v3);
        v3 = 17;
        this.cancelButton.setGravity(v3);
        v13_1 = this.cancelButton;
        int v6 = 788529152;
        int v5 = this.isDarkTheme ? -12763843 : 788529152;
        v13_1.setBackgroundDrawable(Theme.createSelectorDrawable(v5, 0));
        float v5_1 = 20f;
        this.cancelButton.setPadding(AndroidUtilities.dp(v5_1), 0, AndroidUtilities.dp(v5_1), 0);
        this.cancelButton.setText(LocaleController.getString("Cancel", 2131624257).toUpperCase());
        this.cancelButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        int v10 = -2;
        this.addView(this.cancelButton, LayoutHelper.createFrame(v10, v0, 51));
        this.doneButton = new TextView(arg12);
        this.doneButton.setTextSize(1, v1);
        v13_1 = this.doneButton;
        if(this.isDarkTheme) {
            v4 = -1;
        }

        v13_1.setTextColor(v4);
        this.doneButton.setGravity(v3);
        v13_1 = this.doneButton;
        if(this.isDarkTheme) {
            v6 = -12763843;
        }

        v13_1.setBackgroundDrawable(Theme.createSelectorDrawable(v6, 0));
        this.doneButton.setPadding(AndroidUtilities.dp(v5_1), 0, AndroidUtilities.dp(v5_1), 0);
        this.doneButton.setText(LocaleController.getString("Send", 2131625997).toUpperCase());
        this.doneButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addView(this.doneButton, LayoutHelper.createFrame(v10, v0, 53));
        this.doneButtonBadgeTextView = new TextView(arg12);
        this.doneButtonBadgeTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.doneButtonBadgeTextView.setTextSize(1, 13f);
        this.doneButtonBadgeTextView.setTextColor(v0);
        this.doneButtonBadgeTextView.setGravity(v3);
        TextView v12 = this.doneButtonBadgeTextView;
        v13 = this.isDarkTheme ? 2131231471 : 2131230930;
        v12.setBackgroundResource(v13);
        this.doneButtonBadgeTextView.setMinWidth(AndroidUtilities.dp(23f));
        this.doneButtonBadgeTextView.setPadding(AndroidUtilities.dp(8f), 0, AndroidUtilities.dp(8f), AndroidUtilities.dp(1f));
        this.addView(this.doneButtonBadgeTextView, LayoutHelper.createFrame(-2, 23f, 53, 0f, 0f, 7f, 0f));
    }

    public void updateSelectedCount(int arg8, boolean arg9) {
        TextView v8;
        int v0 = -15095832;
        if(arg8 == 0) {
            this.doneButtonBadgeTextView.setVisibility(8);
            if(arg9) {
                this.doneButton.setTextColor(-6710887);
                this.doneButton.setEnabled(false);
            }
            else {
                v8 = this.doneButton;
                if(this.isDarkTheme) {
                    v0 = -1;
                }

                v8.setTextColor(v0);
            }
        }
        else {
            this.doneButtonBadgeTextView.setVisibility(0);
            this.doneButtonBadgeTextView.setText(String.format("%d", Integer.valueOf(arg8)));
            v8 = this.doneButton;
            if(this.isDarkTheme) {
                v0 = -1;
            }

            v8.setTextColor(v0);
            if(!arg9) {
                return;
            }

            this.doneButton.setEnabled(true);
        }
    }
}

