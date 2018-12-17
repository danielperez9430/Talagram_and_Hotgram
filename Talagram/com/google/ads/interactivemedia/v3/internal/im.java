package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import java.util.List;
import java.util.Set;

public class im implements AdsRenderingSettings {
    @gt(a="bitrate") private int a;
    @gt(a="mimeTypes") private List b;
    @gt(a="uiElements") private Set c;
    @gt(a="enablePreloading") private boolean d;
    @gt(a="enableFocusSkipButton") private boolean e;
    @gt(a="playAdsAfterTime") private double f;
    @gt(a="disableUi") private boolean g;
    @gt(a="loadVideoTimeout") private int h;
    private boolean i;

    public im() {
        super();
        this.a = -1;
        this.b = null;
        this.e = true;
        this.f = -1;
        this.g = false;
        this.h = -1;
        this.i = true;
    }

    public int getBitrateKbps() {
        return this.a;
    }

    public boolean getDisableUi() {
        return this.g;
    }

    public boolean getEnablePreloading() {
        return this.d;
    }

    public boolean getFocusSkipButtonWhenAvailable() {
        return this.e;
    }

    public List getMimeTypes() {
        return this.b;
    }

    public boolean isRenderCompanions() {
        return this.i;
    }

    public void setBitrateKbps(int arg1) {
        this.a = arg1;
    }

    public void setDisableUi(boolean arg1) {
        this.g = arg1;
    }

    public void setEnablePreloading(boolean arg1) {
        this.d = arg1;
    }

    public void setFocusSkipButtonWhenAvailable(boolean arg1) {
        this.e = arg1;
    }

    public void setLoadVideoTimeout(int arg1) {
        this.h = arg1;
    }

    public void setMimeTypes(List arg1) {
        this.b = arg1;
    }

    public void setPlayAdsAfterTime(double arg1) {
        this.f = arg1;
    }

    public void setRenderCompanions(boolean arg1) {
        this.i = arg1;
    }

    public void setUiElements(Set arg1) {
        this.c = arg1;
    }

    public String toString() {
        int v0 = this.a;
        String v1 = String.valueOf(this.b);
        String v2 = String.valueOf(this.c);
        boolean v3 = this.d;
        double v4 = this.f;
        StringBuilder v7 = new StringBuilder(String.valueOf(v1).length() + 134 + String.valueOf(v2).length());
        v7.append("AdsRenderingSettings [bitrate=");
        v7.append(v0);
        v7.append(", mimeTypes=");
        v7.append(v1);
        v7.append(", uiElements=");
        v7.append(v2);
        v7.append(", enablePreloading=");
        v7.append(v3);
        v7.append(", playAdsAfterTime=");
        v7.append(v4);
        v7.append("]");
        return v7.toString();
    }
}

