package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;

public class PickerBottomLayout extends FrameLayout {
    public TextView cancelButton;
    public LinearLayout doneButton;
    public TextView doneButtonBadgeTextView;
    public TextView doneButtonTextView;
    private boolean isDarkTheme;

    public PickerBottomLayout(Context arg21, boolean arg22) {
        PickerBottomLayout v0 = this;
        Context v1 = arg21;
        super(arg21);
        v0.isDarkTheme = arg22;
        int v2 = v0.isDarkTheme ? -15066598 : Theme.getColor("windowBackgroundWhite");
        v0.setBackgroundColor(v2);
        v0.cancelButton = new TextView(v1);
        float v3 = 14f;
        v0.cancelButton.setTextSize(1, v3);
        TextView v2_1 = v0.cancelButton;
        int v6 = -1;
        int v5 = v0.isDarkTheme ? -1 : Theme.getColor("picker_enabledButton");
        v2_1.setTextColor(v5);
        v5 = 17;
        v0.cancelButton.setGravity(v5);
        v2_1 = v0.cancelButton;
        int v8 = 788529152;
        int v7 = v0.isDarkTheme ? -12763843 : 788529152;
        v2_1.setBackgroundDrawable(Theme.createSelectorDrawable(v7, 0));
        float v7_1 = 29f;
        v0.cancelButton.setPadding(AndroidUtilities.dp(v7_1), 0, AndroidUtilities.dp(v7_1), 0);
        v0.cancelButton.setText(LocaleController.getString("Cancel", 2131624257).toUpperCase());
        v0.cancelButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        int v12 = -2;
        v0.addView(v0.cancelButton, LayoutHelper.createFrame(v12, v6, 51));
        v0.doneButton = new LinearLayout(v1);
        v0.doneButton.setOrientation(0);
        LinearLayout v2_2 = v0.doneButton;
        if(v0.isDarkTheme) {
            v8 = -12763843;
        }

        v2_2.setBackgroundDrawable(Theme.createSelectorDrawable(v8, 0));
        v0.doneButton.setPadding(AndroidUtilities.dp(v7_1), 0, AndroidUtilities.dp(v7_1), 0);
        v0.addView(v0.doneButton, LayoutHelper.createFrame(v12, v6, 53));
        v0.doneButtonBadgeTextView = new TextView(v1);
        v0.doneButtonBadgeTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.doneButtonBadgeTextView.setTextSize(1, 13f);
        v2_1 = v0.doneButtonBadgeTextView;
        v7 = v0.isDarkTheme ? -1 : Theme.getColor("picker_badgeText");
        v2_1.setTextColor(v7);
        v0.doneButtonBadgeTextView.setGravity(v5);
        v7_1 = 11f;
        if(v0.isDarkTheme) {
            v2 = AndroidUtilities.dp(v7_1);
            v7 = -10043398;
        }
        else {
            v2 = AndroidUtilities.dp(v7_1);
            v7 = Theme.getColor("picker_badge");
        }

        Drawable v2_3 = Theme.createRoundRectDrawable(v2, v7);
        v0.doneButtonBadgeTextView.setBackgroundDrawable(v2_3);
        v0.doneButtonBadgeTextView.setMinWidth(AndroidUtilities.dp(23f));
        v7_1 = 8f;
        v0.doneButtonBadgeTextView.setPadding(AndroidUtilities.dp(v7_1), 0, AndroidUtilities.dp(v7_1), AndroidUtilities.dp(1f));
        v0.doneButton.addView(v0.doneButtonBadgeTextView, LayoutHelper.createLinear(-2, 23, 16, 0, 0, 10, 0));
        v0.doneButtonTextView = new TextView(v1);
        v0.doneButtonTextView.setTextSize(1, v3);
        TextView v1_1 = v0.doneButtonTextView;
        if(v0.isDarkTheme) {
        }
        else {
            v6 = Theme.getColor("picker_enabledButton");
        }

        v1_1.setTextColor(v6);
        v0.doneButtonTextView.setGravity(v5);
        v0.doneButtonTextView.setCompoundDrawablePadding(AndroidUtilities.dp(v7_1));
        v0.doneButtonTextView.setText(LocaleController.getString("Send", 2131625997).toUpperCase());
        v0.doneButtonTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.doneButton.addView(v0.doneButtonTextView, LayoutHelper.createLinear(v12, v12, 16));
    }

    public PickerBottomLayout(Context arg2) {
        this(arg2, true);
    }

    public void updateSelectedCount(int arg8, boolean arg9) {
        String v1_1;
        TextView v8;
        int v0 = -1;
        Object v1 = null;
        if(arg8 == 0) {
            this.doneButtonBadgeTextView.setVisibility(8);
            if(arg9) {
                v8 = this.doneButtonTextView;
                if(this.isDarkTheme) {
                }
                else {
                    v1_1 = "picker_disabledButton";
                }

                v8.setTag(v1);
                v8 = this.doneButtonTextView;
                int v9 = this.isDarkTheme ? -6710887 : Theme.getColor("picker_disabledButton");
                v8.setTextColor(v9);
                this.doneButton.setEnabled(false);
            }
            else {
                v8 = this.doneButtonTextView;
                if(this.isDarkTheme) {
                }
                else {
                    v1_1 = "picker_enabledButton";
                }

                v8.setTag(v1);
                v8 = this.doneButtonTextView;
                if(this.isDarkTheme) {
                }
                else {
                    v0 = Theme.getColor("picker_enabledButton");
                }

                v8.setTextColor(v0);
            }
        }
        else {
            this.doneButtonBadgeTextView.setVisibility(0);
            this.doneButtonBadgeTextView.setText(String.format("%d", Integer.valueOf(arg8)));
            v8 = this.doneButtonTextView;
            if(this.isDarkTheme) {
            }
            else {
                v1_1 = "picker_enabledButton";
            }

            v8.setTag(v1);
            v8 = this.doneButtonTextView;
            if(this.isDarkTheme) {
            }
            else {
                v0 = Theme.getColor("picker_enabledButton");
            }

            v8.setTextColor(v0);
            if(!arg9) {
                return;
            }

            this.doneButton.setEnabled(true);
        }
    }
}

