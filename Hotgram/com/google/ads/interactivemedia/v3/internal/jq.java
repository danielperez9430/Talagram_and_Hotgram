package com.google.ads.interactivemedia.v3.internal;

import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;

public class jq {
    private final ViewGroup a;
    private final WebView b;

    public jq(jd arg1, ViewGroup arg2) {
        super();
        this.a = arg2;
        this.b = arg1.b();
    }

    public void a() {
        ViewParent v0 = this.b.getParent();
        if(v0 != null) {
            this.b.setVisibility(4);
            ((ViewGroup)v0).removeView(this.b);
        }

        this.a.addView(this.b, new ViewGroup$LayoutParams(-1, -1));
        this.b.setVisibility(0);
    }

    public void b() {
        this.b.setVisibility(4);
    }

    public void c() {
        this.b();
        this.a.removeView(this.b);
    }
}

