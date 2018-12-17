package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Build$VERSION;
import android.webkit.WebSettings;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;

public class jr {
    @TargetApi(value=17) public static void a(WebSettings arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setMediaPlaybackRequiresUserGesture(false);
        }
    }

    @TargetApi(value=13) public static boolean a(Context arg3, TestingConfiguration arg4) {
        boolean v0 = true;
        if(arg4 != null && (arg4.forceTvMode())) {
            return 1;
        }

        if(Build$VERSION.SDK_INT <= 12) {
            return 0;
        }

        Object v3 = arg3.getSystemService("uimode");
        if(v3 == null || ((UiModeManager)v3).getCurrentModeType() != 4) {
            v0 = false;
        }
        else {
        }

        return v0;
    }
}

