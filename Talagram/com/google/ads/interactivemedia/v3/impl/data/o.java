package com.google.ads.interactivemedia.v3.impl.data;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamRequest$StreamFormat;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.internal.ii$b;
import com.google.ads.interactivemedia.v3.internal.iq;
import com.google.ads.interactivemedia.v3.internal.js;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class o {
    public interface a {
        a adTagParameters(Map arg1);

        a adTagUrl(String arg1);

        a adsResponse(String arg1);

        a apiKey(String arg1);

        a assetKey(String arg1);

        a authToken(String arg1);

        o build();

        a companionSlots(Map arg1);

        a contentDuration(Float arg1);

        a contentKeywords(List arg1);

        a contentSourceId(String arg1);

        a contentTitle(String arg1);

        a env(String arg1);

        a extraParameters(Map arg1);

        a format(String arg1);

        a identifierInfo(js arg1);

        a isTv(Boolean arg1);

        a linearAdSlotHeight(Integer arg1);

        a linearAdSlotWidth(Integer arg1);

        a liveStreamPrefetchSeconds(Float arg1);

        a marketAppInfo(b arg1);

        a msParameter(String arg1);

        a network(String arg1);

        a settings(ImaSdkSettings arg1);

        a streamActivityMonitorId(String arg1);

        a useQAStreamBaseUrl(Boolean arg1);

        a vastLoadTimeout(Float arg1);

        a videoId(String arg1);

        a videoPlayActivation(com.google.ads.interactivemedia.v3.internal.in$a arg1);

        a videoPlayMuted(com.google.ads.interactivemedia.v3.internal.in$b arg1);
    }

    public o() {
        super();
    }

    public abstract Map adTagParameters();

    public abstract String adTagUrl();

    public abstract String adsResponse();

    public abstract String apiKey();

    public abstract String assetKey();

    public abstract String authToken();

    public static a builder() {
        return new com.google.ads.interactivemedia.v3.impl.data.h$a();
    }

    public abstract Map companionSlots();

    public abstract Float contentDuration();

    public abstract List contentKeywords();

    public abstract String contentSourceId();

    public abstract String contentTitle();

    public static o create(AdsRequest arg13, String arg14, String arg15, ImaSdkSettings arg16, b arg17, boolean arg18, js arg19) {
        String v0 = arg13.getAdTagUrl();
        String v1 = arg13.getAdsResponse();
        Map v2 = arg13.getExtraParameters();
        com.google.ads.interactivemedia.v3.internal.in$a v5 = arg13.a();
        com.google.ads.interactivemedia.v3.internal.in$b v6 = arg13.b();
        Float v7 = arg13.c();
        List v8 = arg13.d();
        String v9 = arg13.e();
        Float v10 = arg13.f();
        Float v4 = arg13.g();
        Map v11 = o.getCompanionSlots(arg13.getAdDisplayContainer());
        ViewGroup v3 = arg13.getAdDisplayContainer().getAdContainer();
        return o.builder().adTagUrl(v0).adsResponse(v1).env(arg14).network(arg15).extraParameters(v2).settings(arg16).videoPlayActivation(v5).videoPlayMuted(v6).contentDuration(v7).contentKeywords(v8).contentTitle(v9).vastLoadTimeout(v10).liveStreamPrefetchSeconds(v4).companionSlots(v11).marketAppInfo(arg17).isTv(Boolean.valueOf(arg18)).identifierInfo(arg19).linearAdSlotWidth(Integer.valueOf(v3.getWidth())).linearAdSlotHeight(Integer.valueOf(v3.getHeight())).build();
    }

    public static o createFromStreamRequest(StreamRequest arg5, String arg6, String arg7, ImaSdkSettings arg8, b arg9, boolean arg10, String arg11, js arg12) {
        Map v0 = o.getCompanionSlots(arg5.getStreamDisplayContainer());
        ViewGroup v1 = arg5.getStreamDisplayContainer().getAdContainer();
        String v2 = "hls";
        if(arg5.getFormat() == StreamFormat.DASH) {
            v2 = "dash";
        }

        return o.builder().assetKey(arg5.getAssetKey()).authToken(arg5.getAuthToken()).contentSourceId(arg5.getContentSourceId()).videoId(arg5.getVideoId()).apiKey(arg5.getApiKey()).adTagParameters(arg5.getAdTagParameters()).env(arg6).network(arg7).settings(arg8).companionSlots(v0).marketAppInfo(arg9).isTv(Boolean.valueOf(arg10)).msParameter(arg11).linearAdSlotWidth(Integer.valueOf(v1.getWidth())).linearAdSlotHeight(Integer.valueOf(v1.getHeight())).streamActivityMonitorId(arg5.getStreamActivityMonitorId()).format(v2).identifierInfo(arg12).useQAStreamBaseUrl(arg5.getUseQAStreamBaseUrl()).build();
    }

    public abstract String env();

    public abstract Map extraParameters();

    public abstract String format();

    private static Map getCompanionSlots(iq arg7) {
        Map v7 = arg7.a();
        if(v7 != null && !v7.isEmpty()) {
            com.google.ads.interactivemedia.v3.internal.lb$a v0 = new com.google.ads.interactivemedia.v3.internal.lb$a();
            Iterator v1 = v7.keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                Object v3 = v7.get(v2);
                int v4 = ((CompanionAdSlot)v3).getWidth();
                int v3_1 = ((CompanionAdSlot)v3).getHeight();
                StringBuilder v6 = new StringBuilder(23);
                v6.append(v4);
                v6.append("x");
                v6.append(v3_1);
                v0.a(v2, v6.toString());
            }

            return v0.a();
        }

        return null;
    }

    public abstract js identifierInfo();

    public abstract Boolean isTv();

    public abstract Integer linearAdSlotHeight();

    public abstract Integer linearAdSlotWidth();

    public abstract Float liveStreamPrefetchSeconds();

    public abstract b marketAppInfo();

    public abstract String msParameter();

    public abstract String network();

    public abstract ImaSdkSettings settings();

    public abstract String streamActivityMonitorId();

    public abstract Boolean useQAStreamBaseUrl();

    public abstract Float vastLoadTimeout();

    public abstract String videoId();

    public abstract com.google.ads.interactivemedia.v3.internal.in$a videoPlayActivation();

    public abstract com.google.ads.interactivemedia.v3.internal.in$b videoPlayMuted();
}

