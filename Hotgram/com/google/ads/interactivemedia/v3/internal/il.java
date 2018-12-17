package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.impl.data.b;

public class il {
    private final jd a;
    private BaseDisplayContainer b;
    private jq c;
    private b d;

    public il(jd arg1, BaseDisplayContainer arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public void a() {
        if(this.c != null) {
            this.c.b();
        }

        this.d = null;
    }

    public void a(b arg2) {
        if(this.d != null) {
            this.b();
        }

        if(arg2.isLinear()) {
            this.d = arg2;
            this.c();
        }
    }

    public void b() {
        this.a();
        if(this.c != null) {
            this.c.c();
        }

        this.c = null;
    }

    private void c() {
        this.c = new jq(this.a, this.b.getAdContainer());
        this.c.a();
    }
}

