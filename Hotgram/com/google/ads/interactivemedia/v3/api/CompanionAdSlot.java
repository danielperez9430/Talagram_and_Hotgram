package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;

public interface CompanionAdSlot {
    public interface ClickListener {
        void onCompanionAdClick();
    }

    void addClickListener(ClickListener arg1);

    ViewGroup getContainer();

    int getHeight();

    int getWidth();

    boolean isFilled();

    void removeClickListener(ClickListener arg1);

    void setContainer(ViewGroup arg1);

    void setSize(int arg1, int arg2);
}

