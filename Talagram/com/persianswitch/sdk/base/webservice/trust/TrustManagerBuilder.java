package com.persianswitch.sdk.base.webservice.trust;

import android.content.Context;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class TrustManagerBuilder {
    private CompositeTrustManager a;
    private Context b;
    private MemorizingTrustManager c;

    public TrustManagerBuilder(Context arg2) {
        super();
        this.a = CompositeTrustManager.a(new X509TrustManager[0]);
        this.b = null;
        this.c = null;
        this.b = arg2;
    }

    public TrustManagerBuilder() {
        this(null);
    }

    public TrustManagerBuilder a(String arg2) {
        return this.a(arg2, "X.509");
    }

    public TrustManager a() {
        return this.a;
    }

    public TrustManagerBuilder a(String arg2, String arg3) {
        this.d();
        this.a(TrustManagers.a(this.b.getAssets().open(arg2), arg3));
        return this;
    }

    public TrustManagerBuilder a(TrustManager[] arg5) {
        int v0 = arg5.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            TrustManager v2 = arg5[v1];
            if((v2 instanceof X509TrustManager)) {
                this.a.a(((X509TrustManager)v2));
            }
        }

        return this;
    }

    public TrustManagerBuilder b() {
        if(this.a.a()) {
            if(this.a.b() < 2) {
                this.a.a(false);
            }
            else {
                this.a = CompositeTrustManager.b(new X509TrustManager[]{this.a});
            }
        }

        return this;
    }

    public TrustManagerBuilder c() {
        TrustManagerFactory v0 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        v0.init(null);
        this.a(v0.getTrustManagers());
        return this;
    }

    private void d() {
        if(this.b != null) {
            return;
        }

        throw new IllegalArgumentException("Must use constructor supplying a Context");
    }
}

