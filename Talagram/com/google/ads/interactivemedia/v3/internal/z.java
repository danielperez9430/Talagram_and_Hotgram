package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.webkit.WebView;

public class z extends y {
    @SuppressLint(value={"SetJavaScriptEnabled"}) public z(WebView arg3) {
        super();
        if(arg3 != null && !arg3.getSettings().getJavaScriptEnabled()) {
            arg3.getSettings().setJavaScriptEnabled(true);
        }

        this.a(arg3);
    }
}

