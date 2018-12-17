package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.internal.lx;
import com.google.ads.interactivemedia.v3.internal.ly;
import com.google.ads.interactivemedia.v3.internal.lz;
import com.google.ads.interactivemedia.v3.internal.ma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class b implements Ad {
    private String adId;
    @ly @ma private c adPodInfo;
    private String adSystem;
    @ly @ma private String[] adWrapperCreativeIds;
    @ly @ma private String[] adWrapperIds;
    @ly @ma private String[] adWrapperSystems;
    private String advertiserName;
    private String clickThroughUrl;
    @ly @ma private List companions;
    private String contentType;
    private String creativeAdId;
    private String creativeId;
    private String dealId;
    private String description;
    private boolean disableUi;
    private double duration;
    private int height;
    private boolean isUiDisabled_;
    private boolean linear;
    private double skipTimeOffset;
    private boolean skippable;
    private String surveyUrl;
    private String title;
    private String traffickingParameters;
    @ly @ma private Set uiElements;
    private String universalAdIdRegistry;
    private String universalAdIdValue;
    private int vastMediaBitrate;
    private int vastMediaHeight;
    private int vastMediaWidth;
    private int width;

    public b() {
        super();
        this.skipTimeOffset = -1;
        this.adPodInfo = new c();
        this.isUiDisabled_ = false;
    }

    public boolean canDisableUi() {
        return this.disableUi;
    }

    public boolean equals(Object arg2) {
        return lx.a(this, arg2, new String[0]);
    }

    public String getAdId() {
        return this.adId;
    }

    public AdPodInfo getAdPodInfo() {
        return this.adPodInfo;
    }

    public String getAdSystem() {
        return this.adSystem;
    }

    public String[] getAdWrapperCreativeIds() {
        return this.adWrapperCreativeIds;
    }

    public String[] getAdWrapperIds() {
        return this.adWrapperIds;
    }

    public String[] getAdWrapperSystems() {
        return this.adWrapperSystems;
    }

    public String getAdvertiserName() {
        return this.advertiserName;
    }

    public String getClickThruUrl() {
        return this.clickThroughUrl;
    }

    public List getCompanionAds() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.companions.iterator();
        while(v1.hasNext()) {
            v0.add(v1.next());
        }

        return ((List)v0);
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getCreativeAdId() {
        return this.creativeAdId;
    }

    public String getCreativeId() {
        return this.creativeId;
    }

    public String getDealId() {
        return this.dealId;
    }

    public String getDescription() {
        return this.description;
    }

    public double getDuration() {
        return this.duration;
    }

    public int getHeight() {
        return this.height;
    }

    public double getSkipTimeOffset() {
        return this.skipTimeOffset;
    }

    public String getSurveyUrl() {
        return this.surveyUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTraffickingParameters() {
        return this.traffickingParameters;
    }

    public Set getUiElements() {
        return this.uiElements;
    }

    public String getUniversalAdIdRegistry() {
        return this.universalAdIdRegistry;
    }

    public String getUniversalAdIdValue() {
        return this.universalAdIdValue;
    }

    public int getVastMediaBitrate() {
        return this.vastMediaBitrate;
    }

    public int getVastMediaHeight() {
        return this.vastMediaHeight;
    }

    public int getVastMediaWidth() {
        return this.vastMediaWidth;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return lz.a(this, new String[0]);
    }

    public boolean isLinear() {
        return this.linear;
    }

    public boolean isSkippable() {
        return this.skippable;
    }

    public boolean isUiDisabled() {
        return this.isUiDisabled_;
    }

    public void setAdId(String arg1) {
        this.adId = arg1;
    }

    public void setAdPodInfo(c arg1) {
        this.adPodInfo = arg1;
    }

    public void setAdSystem(String arg1) {
        this.adSystem = arg1;
    }

    public void setAdWrapperCreativeIds(String[] arg1) {
        this.adWrapperCreativeIds = arg1;
    }

    public void setAdWrapperIds(String[] arg1) {
        this.adWrapperIds = arg1;
    }

    public void setAdWrapperSystems(String[] arg1) {
        this.adWrapperSystems = arg1;
    }

    public void setAdvertiserName(String arg1) {
        this.advertiserName = arg1;
    }

    public void setCanDisableUi(boolean arg1) {
        this.disableUi = arg1;
    }

    public void setClickThruUrl(String arg1) {
        this.clickThroughUrl = arg1;
    }

    public void setCompanionAds(List arg1) {
        this.companions = arg1;
    }

    public void setContentType(String arg1) {
        this.contentType = arg1;
    }

    public void setCreativeAdId(String arg1) {
        this.creativeAdId = arg1;
    }

    public void setCreativeId(String arg1) {
        this.creativeId = arg1;
    }

    public void setDealId(String arg1) {
        this.dealId = arg1;
    }

    public void setDescription(String arg1) {
        this.description = arg1;
    }

    public void setDuration(double arg1) {
        this.duration = arg1;
    }

    public void setHeight(int arg1) {
        this.height = arg1;
    }

    public void setLinear(boolean arg1) {
        this.linear = arg1;
    }

    public void setSkipTimeOffset(double arg1) {
        this.skipTimeOffset = arg1;
    }

    public void setSkippable(boolean arg1) {
        this.skippable = arg1;
    }

    public void setSurveyUrl(String arg1) {
        this.surveyUrl = arg1;
    }

    public void setTitle(String arg1) {
        this.title = arg1;
    }

    public void setTraffickingParameters(String arg1) {
        this.traffickingParameters = arg1;
    }

    public void setUiDisabled(boolean arg1) {
        this.isUiDisabled_ = arg1;
    }

    public void setUiElements(Set arg1) {
        this.uiElements = arg1;
    }

    public void setUniversalAdIdRegistry(String arg1) {
        this.universalAdIdRegistry = arg1;
    }

    public void setUniversalAdIdValue(String arg1) {
        this.universalAdIdValue = arg1;
    }

    public void setVastMediaBitrate(int arg1) {
        this.vastMediaBitrate = arg1;
    }

    public void setVastMediaHeight(int arg1) {
        this.vastMediaHeight = arg1;
    }

    public void setVastMediaWidth(int arg1) {
        this.vastMediaWidth = arg1;
    }

    public void setWidth(int arg1) {
        this.width = arg1;
    }

    public String toString() {
        String v1 = this.adId;
        String v2 = this.creativeId;
        String v3 = this.creativeAdId;
        String v4 = this.universalAdIdValue;
        String v5 = this.universalAdIdRegistry;
        String v6 = this.title;
        String v7 = this.description;
        String v8 = this.contentType;
        String v9 = Arrays.toString(this.adWrapperIds);
        String v10 = Arrays.toString(this.adWrapperSystems);
        String v11 = Arrays.toString(this.adWrapperCreativeIds);
        String v12 = this.adSystem;
        String v13 = this.advertiserName;
        String v14 = this.surveyUrl;
        String v16 = this.dealId;
        boolean v17 = this.linear;
        boolean v18 = this.skippable;
        int v19 = this.width;
        int v20 = this.height;
        String v21 = this.traffickingParameters;
        String v22 = v14;
        String v23 = this.clickThroughUrl;
        double v24 = this.duration;
        v14 = String.valueOf(this.adPodInfo);
        String v26 = String.valueOf(this.uiElements);
        String v27 = v14;
        boolean v28 = this.disableUi;
        double v30 = this.skipTimeOffset;
        StringBuilder v14_1 = new StringBuilder(String.valueOf(v1).length() + 455 + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length() + String.valueOf(v5).length() + String.valueOf(v6).length() + String.valueOf(v7).length() + String.valueOf(v8).length() + String.valueOf(v9).length() + String.valueOf(v10).length() + String.valueOf(v11).length() + String.valueOf(v12).length() + String.valueOf(v13).length() + String.valueOf(v22).length() + String.valueOf(v16).length() + String.valueOf(v21).length() + String.valueOf(v23).length() + String.valueOf(v27).length() + String.valueOf(v26).length());
        v14_1.append("Ad [adId=");
        v14_1.append(v1);
        v14_1.append(", creativeId=");
        v14_1.append(v2);
        v14_1.append(", creativeAdId=");
        v14_1.append(v3);
        v14_1.append(", universalAdIdValue=");
        v14_1.append(v4);
        v14_1.append(", universalAdIdRegistry=");
        v14_1.append(v5);
        v14_1.append(", title=");
        v14_1.append(v6);
        v14_1.append(", description=");
        v14_1.append(v7);
        v14_1.append(", contentType=");
        v14_1.append(v8);
        v14_1.append(", adWrapperIds=");
        v14_1.append(v9);
        v14_1.append(", adWrapperSystems=");
        v14_1.append(v10);
        v14_1.append(", adWrapperCreativeIds=");
        v14_1.append(v11);
        v14_1.append(", adSystem=");
        v14_1.append(v12);
        v14_1.append(", advertiserName=");
        v14_1.append(v13);
        v14_1.append(", surveyUrl=");
        v14_1.append(v22);
        v14_1.append(", dealId=");
        v14_1.append(v16);
        v14_1.append(", linear=");
        v14_1.append(v17);
        v14_1.append(", skippable=");
        v14_1.append(v18);
        v14_1.append(", width=");
        v14_1.append(v19);
        v14_1.append(", height=");
        v14_1.append(v20);
        v14_1.append(", traffickingParameters=");
        v14_1.append(v21);
        v14_1.append(", clickThroughUrl=");
        v14_1.append(v23);
        v14_1.append(", duration=");
        v14_1.append(v24);
        v14_1.append(", adPodInfo=");
        v14_1.append(v27);
        v14_1.append(", uiElements=");
        v14_1.append(v26);
        v14_1.append(", disableUi=");
        v14_1.append(v28);
        v14_1.append(", skipTimeOffset=");
        v14_1.append(v30);
        v14_1.append("]");
        return v14_1.toString();
    }
}

