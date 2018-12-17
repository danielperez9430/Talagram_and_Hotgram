package com.persianswitch.sdk.base.jsevaluator;

import android.webkit.JavascriptInterface;
import com.persianswitch.sdk.base.jsevaluator.interfaces.CallJavaResultInterface;

public class JavaScriptInterface {
    private final CallJavaResultInterface a;

    public JavaScriptInterface(CallJavaResultInterface arg1) {
        super();
        this.a = arg1;
    }

    @JavascriptInterface public void returnResultToJava(String arg2, int arg3) {
        this.a.a(arg2, Integer.valueOf(arg3));
    }
}

