package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class in implements AdsRequest {
    public enum a {
        @gt(a="auto") public static final enum a AUTO;
        @gt(a="click") public static final enum a CLICK;
        @gt(a="unknown") public static final enum a UNKNOWN;

        static {
            a.AUTO = new a("AUTO", 0);
            a.CLICK = new a("CLICK", 1);
            a.UNKNOWN = new a("UNKNOWN", 2);
            a.$VALUES = new a[]{a.AUTO, a.CLICK, a.UNKNOWN};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    public enum b {
        @gt(a="muted") public static final enum b MUTED;
        @gt(a="unknown") public static final enum b UNKNOWN;
        @gt(a="unmuted") public static final enum b UNMUTED;

        static {
            b.MUTED = new b("MUTED", 0);
            b.UNKNOWN = new b("UNKNOWN", 1);
            b.UNMUTED = new b("UNMUTED", 2);
            b.$VALUES = new b[]{b.MUTED, b.UNKNOWN, b.UNMUTED};
        }

        private b(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static b valueOf(String arg1) {
            return Enum.valueOf(b.class, arg1);
        }

        public static b[] values() {
            // Method was not decompiled
        }
    }

    private String a;
    private AdDisplayContainer b;
    private Map c;
    private String d;
    private ContentProgressProvider e;
    private a f;
    private b g;
    private Float h;
    private List i;
    private String j;
    private Float k;
    private Float l;
    private transient Object m;

    public in() {
        super();
        this.f = a.UNKNOWN;
        this.g = b.UNKNOWN;
    }

    public a a() {
        return this.f;
    }

    public b b() {
        return this.g;
    }

    public Float c() {
        return this.h;
    }

    public List d() {
        return this.i;
    }

    public String e() {
        return this.j;
    }

    public Float f() {
        return this.k;
    }

    public Float g() {
        return this.l;
    }

    public AdDisplayContainer getAdDisplayContainer() {
        return this.b;
    }

    public String getAdTagUrl() {
        return this.a;
    }

    public String getAdsResponse() {
        return this.d;
    }

    public ContentProgressProvider getContentProgressProvider() {
        return this.e;
    }

    public String getExtraParameter(String arg2) {
        if(this.c == null) {
            return null;
        }

        return this.c.get(arg2);
    }

    public Map getExtraParameters() {
        return this.c;
    }

    public Object getUserRequestContext() {
        return this.m;
    }

    public void setAdDisplayContainer(AdDisplayContainer arg1) {
        this.b = arg1;
    }

    public void setAdTagUrl(String arg1) {
        this.a = arg1;
    }

    public void setAdWillAutoPlay(boolean arg1) {
        a v1 = arg1 ? a.AUTO : a.CLICK;
        this.f = v1;
    }

    public void setAdWillPlayMuted(boolean arg1) {
        b v1 = arg1 ? b.MUTED : b.UNMUTED;
        this.g = v1;
    }

    public void setAdsResponse(String arg1) {
        this.d = arg1;
    }

    public void setContentDuration(float arg1) {
        this.h = Float.valueOf(arg1);
    }

    public void setContentKeywords(List arg1) {
        this.i = arg1;
    }

    public void setContentProgressProvider(ContentProgressProvider arg1) {
        this.e = arg1;
    }

    public void setContentTitle(String arg1) {
        this.j = arg1;
    }

    public void setExtraParameter(String arg2, String arg3) {
        if(this.c == null) {
            this.c = new HashMap();
        }

        this.c.put(arg2, arg3);
    }

    public void setLiveStreamPrefetchSeconds(float arg1) {
        this.l = Float.valueOf(arg1);
    }

    public void setUserRequestContext(Object arg1) {
        this.m = arg1;
    }

    public void setVastLoadTimeout(float arg1) {
        this.k = Float.valueOf(arg1);
    }
}

