package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest$StreamFormat;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import java.util.Map;

public class jm implements StreamRequest {
    private StreamDisplayContainer a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map f;
    private String g;
    private String h;
    private String i;
    private StreamFormat j;
    private Boolean k;
    private transient Object l;

    public jm() {
        super();
    }

    public void a(String arg1) {
        this.b = arg1;
    }

    public void a(StreamDisplayContainer arg1) {
        this.a = arg1;
    }

    public void b(String arg1) {
        this.d = arg1;
    }

    public void c(String arg1) {
        this.e = arg1;
    }

    public void d(String arg1) {
        this.c = arg1;
    }

    public Map getAdTagParameters() {
        return this.f;
    }

    public String getApiKey() {
        return this.c;
    }

    public String getAssetKey() {
        return this.b;
    }

    public String getAuthToken() {
        return this.h;
    }

    public String getContentSourceId() {
        return this.d;
    }

    public StreamFormat getFormat() {
        return this.j;
    }

    public String getManifestSuffix() {
        return this.g;
    }

    public String getStreamActivityMonitorId() {
        return this.i;
    }

    public StreamDisplayContainer getStreamDisplayContainer() {
        return this.a;
    }

    public Boolean getUseQAStreamBaseUrl() {
        return this.k;
    }

    public Object getUserRequestContext() {
        return this.l;
    }

    public String getVideoId() {
        return this.e;
    }

    public void setAdTagParameters(Map arg1) {
        this.f = arg1;
    }

    public void setAuthToken(String arg1) {
        this.h = arg1;
    }

    public void setFormat(StreamFormat arg1) {
        this.j = arg1;
    }

    public void setManifestSuffix(String arg1) {
        this.g = arg1;
    }

    public void setStreamActivityMonitorId(String arg1) {
        this.i = arg1;
    }

    public void setUseQAStreamBaseUrl(Boolean arg1) {
        this.k = arg1;
    }

    public void setUserRequestContext(Object arg1) {
        this.l = arg1;
    }
}

