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

public class RadioColorCell extends FrameLayout {
    private RadioButton radioButton;
    private TextView textView;

    public RadioColorCell(Context arg12) {
        super(arg12);
        this.radioButton = new RadioButton(arg12);
        this.radioButton.setSize(AndroidUtilities.dp(20f));
        this.radioButton.setColor(Theme.getColor("dialogRadioBackground"), Theme.getColor("dialogRadioBackgroundChecked"));
        RadioButton v0 = this.radioButton;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        int v6 = v1 | 48;
        int v4 = 18;
        v1 = LocaleController.isRTL ? 0 : 18;
        float v7 = ((float)v1);
        float v8 = 13f;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 0;
        }

        this.addView(((View)v0), LayoutHelper.createFrame(22, 22f, v6, v7, v8, ((float)v4), 0f));
        this.textView = new TextView(arg12);
        this.textView.setTextColor(Theme.getColor("dialogTextBlack"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView v12 = this.textView;
        int v0_1 = LocaleController.isRTL ? 5 : 3;
        v12.setGravity(v0_1 | 16);
        v12 = this.textView;
        v4 = -2;
        float v5 = -2f;
        if(LocaleController.isRTL) {
            v2 = 5;
        }

        v6 = v2 | 48;
        v1 = 51;
        v0_1 = LocaleController.isRTL ? 17 : 51;
        v7 = ((float)v0_1);
        v8 = 12f;
        if(LocaleController.isRTL) {
        }
        else {
            v1 = 17;
        }

        this.addView(((View)v12), LayoutHelper.createFrame(v4, v5, v6, v7, v8, ((float)v1), 0f));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
    }

    public void setCheckColor(int arg2, int arg3) {
        this.radioButton.setColor(arg2, arg3);
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.radioButton.setChecked(arg2, arg3);
    }

    public void setTextAndValue(String arg2, boolean arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.radioButton.setChecked(arg3, false);
    }
}

