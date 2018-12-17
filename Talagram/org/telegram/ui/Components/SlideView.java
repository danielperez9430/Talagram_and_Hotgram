package org.telegram.ui.Components;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SlideView extends LinearLayout {
    public SlideView(Context arg1) {
        super(arg1);
    }

    public String getHeaderName() {
        return "";
    }

    public boolean needBackButton() {
        return 0;
    }

    public void onBackPressed() {
    }

    public void onCancelPressed() {
    }

    public void onDestroyActivity() {
    }

    public void onNextPressed() {
    }

    public void onShow() {
    }

    public void restoreStateParams(Bundle arg1) {
    }

    public void saveStateParams(Bundle arg1) {
    }

    public void setParams(Bundle arg1, boolean arg2) {
    }
}

