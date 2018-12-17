package org.telegram.ui.Components;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;

public class EmptyTextProgressView extends FrameLayout {
    private boolean inLayout;
    private RadialProgressView progressBar;
    private boolean showAtCenter;
    private TextView textView;

    public EmptyTextProgressView(Context arg6) {
        super(arg6);
        this.progressBar = new RadialProgressView(arg6);
        this.progressBar.setVisibility(4);
        this.addView(this.progressBar, LayoutHelper.createFrame(-2, -2f));
        this.textView = new TextView(arg6);
        this.textView.setTextSize(1, 20f);
        this.textView.setTextColor(Theme.getColor("emptyListPlaceholder"));
        this.textView.setGravity(17);
        this.textView.setVisibility(4);
        this.textView.setPadding(AndroidUtilities.dp(20f), 0, AndroidUtilities.dp(20f), 0);
        this.textView.setText(LocaleController.getString("NoResult", 2131625283));
        this.addView(this.textView, LayoutHelper.createFrame(-2, -2f));
        this.setOnTouchListener(-$$Lambda$EmptyTextProgressView$AeVTSCBshpCl6wf4siSABV33AKw.INSTANCE);
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    static boolean lambda$new$0(View arg0, MotionEvent arg1) {
        return 1;
    }

    protected void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
        this.inLayout = true;
        arg9 -= arg7;
        arg10 -= arg8;
        int v6 = this.getChildCount();
        for(arg8 = 0; arg8 < v6; ++arg8) {
            View v0 = this.getChildAt(arg8);
            if(v0.getVisibility() == 8) {
            }
            else {
                int v1 = (arg9 - v0.getMeasuredWidth()) / 2;
                int v2 = this.showAtCenter ? arg10 / 2 - v0.getMeasuredHeight() : arg10 - v0.getMeasuredHeight();
                v2 /= 2;
                v0.layout(v1, v2, v0.getMeasuredWidth() + v1, v0.getMeasuredHeight() + v2);
            }
        }

        this.inLayout = false;
    }

    public void requestLayout() {
        if(!this.inLayout) {
            super.requestLayout();
        }
    }

    public void setProgressBarColor(int arg2) {
        this.progressBar.setProgressColor(arg2);
    }

    public void setShowAtCenter(boolean arg1) {
        this.showAtCenter = arg1;
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }

    public void setTextSize(int arg3) {
        this.textView.setTextSize(1, ((float)arg3));
    }

    public void showProgress() {
        this.textView.setVisibility(4);
        this.progressBar.setVisibility(0);
    }

    public void showTextView() {
        this.textView.setVisibility(0);
        this.progressBar.setVisibility(4);
    }
}

