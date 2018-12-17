package com.google.ads.interactivemedia.v3.api;

import android.view.View;
import android.view.ViewGroup;
import java.util.Collection;

public interface BaseDisplayContainer {
    ViewGroup getAdContainer();

    Collection getCompanionSlots();

    void registerVideoControlsOverlay(View arg1);

    void setAdContainer(ViewGroup arg1);

    void setCompanionSlots(Collection arg1);

    void unregisterAllVideoControlsOverlays();
}

