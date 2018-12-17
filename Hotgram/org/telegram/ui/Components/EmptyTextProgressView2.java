package org.telegram.ui.Components;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.customization.Activities.t;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import utils.a.b;
import utils.view.FarsiButton;

public class EmptyTextProgressView2 extends FrameLayout {
    private Button button;
    private BaseFragment fragment;
    private boolean inLayout;
    private LinearLayout layoutContainer;
    private RadialProgressView progressBar;
    private String searchTerm;
    private boolean showAtCenter;
    private TextView textView;

    public EmptyTextProgressView2(Context arg10) {
        super(arg10);
        this.searchTerm = "";
        this.progressBar = new RadialProgressView(arg10);
        int v1 = 4;
        this.progressBar.setVisibility(v1);
        float v2 = -2f;
        int v3 = -2;
        this.addView(this.progressBar, LayoutHelper.createFrame(v3, v2));
        this.layoutContainer = new LinearLayout(arg10);
        this.layoutContainer.setOrientation(1);
        this.layoutContainer.setVisibility(v1);
        this.textView = new TextView(arg10);
        float v5 = 20f;
        this.textView.setTextSize(1, v5);
        this.textView.setTextColor(Theme.getColor("emptyListPlaceholder"));
        int v4 = 17;
        this.textView.setGravity(v4);
        this.textView.setVisibility(v1);
        this.textView.setPadding(AndroidUtilities.dp(v5), 0, AndroidUtilities.dp(v5), 0);
        if(!TextUtils.isEmpty(this.searchTerm)) {
            this.textView.setText(LocaleController.getString("NoResult", 2131625283));
        }

        this.layoutContainer.addView(this.textView);
        this.button = new FarsiButton(arg10);
        this.button.setText(LocaleController.getString("SearchInHotgram", 2131625965));
        this.button.setGravity(v4);
        this.button.setVisibility(v1);
        this.button.setPadding(AndroidUtilities.dp(v5), 0, AndroidUtilities.dp(v5), 0);
        this.button.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                Bundle v3 = new Bundle();
                v3.putString("EXTRA_TAG", EmptyTextProgressView2.this.getSearchTerm());
                if(EmptyTextProgressView2.this.getFragment() != null) {
                    EmptyTextProgressView2.this.getFragment().presentFragment(new t(v3));
                }
            }
        });
        if(b.o(ApplicationLoader.applicationContext)) {
            this.layoutContainer.addView(this.button);
        }

        this.addView(this.layoutContainer, LayoutHelper.createFrame(v3, v2));
        this.setOnTouchListener(new View$OnTouchListener() {
            public boolean onTouch(View arg1, MotionEvent arg2) {
                return 1;
            }
        });
    }

    public BaseFragment getFragment() {
        return this.fragment;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public boolean hasOverlappingRendering() {
        return 0;
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

    public void setFragment(BaseFragment arg1) {
        this.fragment = arg1;
    }

    public void setProgressBarColor(int arg2) {
        this.progressBar.setProgressColor(arg2);
    }

    public void setSearchTerm(String arg1) {
        this.searchTerm = arg1;
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
        this.textView.setText("");
        this.textView.setVisibility(4);
        this.button.setVisibility(4);
        this.layoutContainer.setVisibility(4);
        this.progressBar.setVisibility(0);
    }

    public void showTextView() {
        if(!TextUtils.isEmpty(this.searchTerm)) {
            this.textView.setText(LocaleController.getString("NoResult", 2131625283));
        }

        this.textView.setVisibility(0);
        this.button.setVisibility(0);
        this.layoutContainer.setVisibility(0);
        this.progressBar.setVisibility(4);
    }
}

