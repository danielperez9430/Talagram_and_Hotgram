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
import org.telegram.ui.Components.RadioButton;

public class RadioButtonCell extends FrameLayout {
    private RadioButton radioButton;
    private TextView textView;
    private TextView valueTextView;

    public RadioButtonCell(Context arg22) {
        RadioButtonCell v0 = this;
        Context v1 = arg22;
        super(arg22);
        v0.radioButton = new RadioButton(v1);
        v0.radioButton.setSize(AndroidUtilities.dp(20f));
        v0.radioButton.setColor(Theme.getColor("radioBackground"), Theme.getColor("radioBackgroundChecked"));
        RadioButton v2 = v0.radioButton;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 48;
        int v6 = 18;
        v3 = LocaleController.isRTL ? 0 : 18;
        float v9 = ((float)v3);
        float v10 = 10f;
        if(LocaleController.isRTL) {
        }
        else {
            v6 = 0;
        }

        v0.addView(((View)v2), LayoutHelper.createFrame(22, 22f, v8, v9, v10, ((float)v6), 0f));
        v0.textView = new TextView(v1);
        v0.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.textView.setTextSize(1, 16f);
        v0.textView.setLines(1);
        v0.textView.setMaxLines(1);
        v0.textView.setSingleLine(true);
        TextView v2_1 = v0.textView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 16);
        v2_1 = v0.textView;
        int v14 = -2;
        float v15 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        int v16 = v3 | 48;
        int v7 = 51;
        v3 = LocaleController.isRTL ? 17 : 51;
        float v3_1 = ((float)v3);
        float v18 = 10f;
        int v9_1 = LocaleController.isRTL ? 51 : 17;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v14, v15, v16, v3_1, v18, ((float)v9_1), 0f));
        v0.valueTextView = new TextView(v1);
        v0.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        v0.valueTextView.setTextSize(1, 13f);
        TextView v1_1 = v0.valueTextView;
        int v2_2 = LocaleController.isRTL ? 5 : 3;
        v1_1.setGravity(v2_2);
        v0.valueTextView.setLines(0);
        v0.valueTextView.setMaxLines(0);
        v0.valueTextView.setSingleLine(false);
        v0.valueTextView.setPadding(0, 0, 0, AndroidUtilities.dp(12f));
        v1_1 = v0.valueTextView;
        v9_1 = -2;
        v10 = -2f;
        if(LocaleController.isRTL) {
            v4 = 5;
        }

        int v11 = v4 | 48;
        v2_2 = LocaleController.isRTL ? 17 : 51;
        float v12 = ((float)v2_2);
        float v13 = 35f;
        if(LocaleController.isRTL) {
        }
        else {
            v7 = 17;
        }

        v0.addView(((View)v1_1), LayoutHelper.createFrame(v9_1, v10, v11, v12, v13, ((float)v7), 0f));
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg1), 1073741824), View$MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.radioButton.setChecked(arg2, arg3);
    }

    public void setTextAndValue(String arg2, String arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        this.radioButton.setChecked(arg4, false);
    }
}

