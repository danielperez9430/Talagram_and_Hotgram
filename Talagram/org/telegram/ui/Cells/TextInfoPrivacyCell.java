package org.telegram.ui.Cells;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class TextInfoPrivacyCell extends FrameLayout {
    private String linkTextColorKey;
    private TextView textView;

    public TextInfoPrivacyCell(Context arg11) {
        super(arg11);
        this.linkTextColorKey = "windowBackgroundWhiteLinkText";
        this.textView = new TextView(arg11);
        this.textView.setTextSize(1, 14f);
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0);
        this.textView.setPadding(0, AndroidUtilities.dp(10f), 0, AndroidUtilities.dp(17f));
        this.textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.textView.setTypeface(AndroidUtilities.getTypeface(""));
        v11 = this.textView;
        int v3 = -2;
        float v4 = -2f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 17f, 0f, 17f, 0f));
    }

    public TextView getTextView() {
        return this.textView;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText4"));
        this.textView.setLinkTextColor(Theme.getColor(this.linkTextColorKey));
    }

    protected void onMeasure(int arg1, int arg2) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg1), 1073741824), View$MeasureSpec.makeMeasureSpec(0, 0));
    }

    public void setEnabled(boolean arg7, ArrayList arg8) {
        float v0 = 0.5f;
        if(arg8 != null) {
            TextView v2 = this.textView;
            String v3 = "alpha";
            float[] v4 = new float[1];
            if(arg7) {
                v0 = 1f;
            }

            v4[0] = v0;
            arg8.add(ObjectAnimator.ofFloat(v2, v3, v4));
        }
        else {
            TextView v8 = this.textView;
            if(arg7) {
                v0 = 1f;
            }

            v8.setAlpha(v0);
        }
    }

    public void setLinkTextColorKey(String arg1) {
        this.linkTextColorKey = arg1;
    }

    public void setText(CharSequence arg5) {
        if(arg5 == null) {
            this.textView.setPadding(0, AndroidUtilities.dp(2f), 0, 0);
        }
        else {
            this.textView.setPadding(0, AndroidUtilities.dp(10f), 0, AndroidUtilities.dp(17f));
        }

        this.textView.setText(arg5);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }
}

