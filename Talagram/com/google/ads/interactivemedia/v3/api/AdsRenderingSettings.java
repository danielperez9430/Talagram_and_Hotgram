package com.google.ads.interactivemedia.v3.api;

import java.util.List;
import java.util.Set;

public interface AdsRenderingSettings {
    int getBitrateKbps();

    boolean getDisableUi();

    boolean getEnablePreloading();

    boolean getFocusSkipButtonWhenAvailable();

    List getMimeTypes();

    boolean isRenderCompanions();

    void setBitrateKbps(int arg1);

    void setDisableUi(boolean arg1);

    void setEnablePreloading(boolean arg1);

    void setFocusSkipButtonWhenAvailable(boolean arg1);

    void setLoadVideoTimeout(int arg1);

    void setMimeTypes(List arg1);

    void setPlayAdsAfterTime(double arg1);

    void setRenderCompanions(boolean arg1);

    void setUiElements(Set arg1);
}

