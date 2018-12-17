package com.google.ads.interactivemedia.v3.api;

import android.content.Context;
import android.net.Uri;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.internal.ic;
import com.google.ads.interactivemedia.v3.internal.ii;
import com.google.ads.interactivemedia.v3.internal.im;
import com.google.ads.interactivemedia.v3.internal.in;
import com.google.ads.interactivemedia.v3.internal.is;
import com.google.ads.interactivemedia.v3.internal.iz;
import com.google.ads.interactivemedia.v3.internal.ja;
import com.google.ads.interactivemedia.v3.internal.jk;
import com.google.ads.interactivemedia.v3.internal.jm;

public class ImaSdkFactory {
    private static ImaSdkFactory instance;

    private ImaSdkFactory() {
        super();
    }

    public AdDisplayContainer createAdDisplayContainer() {
        return new ic();
    }

    private AdsLoader createAdsLoader(Context arg2, Uri arg3, ImaSdkSettings arg4, TestingConfiguration arg5) {
        ii v0 = new ii(arg2, arg3, arg4, arg5);
        v0.a();
        return ((AdsLoader)v0);
    }

    private AdsLoader createAdsLoader(Context arg3, ImaSdkSettings arg4, TestingConfiguration arg5) {
        Uri v0 = iz.b;
        if(arg4 != null && (arg4.isDebugMode())) {
            v0 = iz.c;
        }

        return this.createAdsLoader(arg3, v0, arg4, arg5);
    }

    public AdsLoader createAdsLoader(Context arg2) {
        return this.createAdsLoader(arg2, this.createImaSdkSettings());
    }

    public AdsLoader createAdsLoader(Context arg3, ImaSdkSettings arg4) {
        Uri v0 = iz.b;
        if(arg4 != null && (arg4.isDebugMode())) {
            v0 = iz.c;
        }

        return new ii(arg3, v0, arg4);
    }

    public AdsRenderingSettings createAdsRenderingSettings() {
        return new im();
    }

    public AdsRequest createAdsRequest() {
        return new in();
    }

    public CompanionAdSlot createCompanionAdSlot() {
        return new is();
    }

    public ImaSdkSettings createImaSdkSettings() {
        return new ja();
    }

    public StreamRequest createLiveStreamRequest(String arg2, String arg3, StreamDisplayContainer arg4) {
        jm v0 = new jm();
        v0.a(arg2);
        v0.d(arg3);
        v0.a(arg4);
        return ((StreamRequest)v0);
    }

    public StreamDisplayContainer createStreamDisplayContainer() {
        return new jk();
    }

    public StreamRequest createVodStreamRequest(String arg2, String arg3, String arg4, StreamDisplayContainer arg5) {
        jm v0 = new jm();
        v0.b(arg2);
        v0.c(arg3);
        v0.d(arg4);
        v0.a(arg5);
        return ((StreamRequest)v0);
    }

    public static ImaSdkFactory getInstance() {
        if(ImaSdkFactory.instance == null) {
            ImaSdkFactory.instance = new ImaSdkFactory();
        }

        return ImaSdkFactory.instance;
    }
}

