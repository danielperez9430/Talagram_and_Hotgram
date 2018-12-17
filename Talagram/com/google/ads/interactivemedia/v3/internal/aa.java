package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.webkit.WebView;
import java.util.Iterator;
import java.util.List;

public class aa extends y {
    private WebView a;
    private List b;
    private final String c;

    public aa(List arg1, String arg2) {
        super();
        this.b = arg1;
        this.c = arg2;
    }

    static WebView a(aa arg0) {
        return arg0.a;
    }

    public void a() {
        super.a();
        this.g();
    }

    public void b() {
        super.b();
        new Handler().postDelayed(new Runnable() {
            private WebView b;

            public void run() {
                this.b.destroy();
            }
        }, 2000);
        this.a = null;
    }

    @SuppressLint(value={"SetJavaScriptEnabled"}) void g() {
        this.a = new WebView(r.a().b());
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a(this.a);
        s.a().a(this.a, this.c);
        Iterator v0 = this.b.iterator();
        while(v0.hasNext()) {
            s.a().b(this.a, v0.next().b().toExternalForm());
        }
    }
}

