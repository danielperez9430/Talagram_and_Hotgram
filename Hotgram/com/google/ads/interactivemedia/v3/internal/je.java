package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint(value={"SetJavaScriptEnabled", "NewApi"}) public class je {
    public interface a {
        void a(jc arg1);
    }

    private final a a;
    private final WebView b;

    public je(Context arg2, a arg3) {
        this(new WebView(arg2), arg3);
    }

    public je(WebView arg4, a arg5) {
        super();
        this.a = arg5;
        this.b = arg4;
        this.b.setBackgroundColor(0);
        if(Build$VERSION.SDK_INT == 15) {
            this.b.setLayerType(1, null);
        }

        if(Build$VERSION.SDK_INT > 19) {
            arg4.getSettings().setMixedContentMode(0);
        }

        arg4.getSettings().setJavaScriptEnabled(true);
        arg4.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView arg2, String arg3) {
                String v2 = "Finished ";
                arg3 = String.valueOf(arg3);
                v2 = arg3.length() != 0 ? v2.concat(arg3) : new String(v2);
                je.c(v2);
            }

            public void onPageStarted(WebView arg1, String arg2, Bitmap arg3) {
                String v1 = "Started ";
                arg2 = String.valueOf(arg2);
                v1 = arg2.length() != 0 ? v1.concat(arg2) : new String(v1);
                je.c(v1);
            }

            public void onReceivedError(WebView arg2, int arg3, String arg4, String arg5) {
                StringBuilder v0 = new StringBuilder(String.valueOf(arg4).length() + 20 + String.valueOf(arg5).length());
                v0.append("Error: ");
                v0.append(arg3);
                v0.append(" ");
                v0.append(arg4);
                v0.append(" ");
                v0.append(arg5);
                je.c(v0.toString());
            }

            public boolean shouldOverrideUrlLoading(WebView arg1, String arg2) {
                if(!arg2.startsWith("gmsg://")) {
                    return 0;
                }

                this.a.b(arg2);
                return 1;
            }
        });
        arg4.setWebChromeClient(new WebChromeClient());
        jr.a(arg4.getSettings());
        CookieManager v5 = CookieManager.getInstance();
        v5.setAcceptCookie(true);
        if(Build$VERSION.SDK_INT >= 21) {
            v5.setAcceptThirdPartyCookies(arg4, true);
        }
    }

    static final void a(boolean arg3, jc arg4, String arg5) {
        StringBuilder v2;
        String v4;
        String v3 = arg3 ? "Sending javascript msg: " : "Received msg: ";
        if(com.google.ads.interactivemedia.v3.internal.iz$a.a(com.google.ads.interactivemedia.v3.internal.iz$a.a)) {
            v4 = String.valueOf(arg4);
            v2 = new StringBuilder(String.valueOf(v3).length() + 7 + String.valueOf(v4).length() + String.valueOf(arg5).length());
            v2.append(v3);
            v2.append(v4);
            v2.append("; URL: ");
            v2.append(arg5);
            Log.d("IMASDK", v2.toString());
        }
        else if(com.google.ads.interactivemedia.v3.internal.iz$a.a(com.google.ads.interactivemedia.v3.internal.iz$a.b)) {
            String v0 = arg4.a().name();
            v4 = arg4.b().name();
            v2 = new StringBuilder(String.valueOf(v3).length() + 17 + String.valueOf(v0).length() + String.valueOf(v4).length());
            v2.append(v3);
            v2.append("Channel: ");
            v2.append(v0);
            v2.append("; type: ");
            v2.append(v4);
            Log.d("IMASDK", v2.toString());
        }
    }

    public WebView a() {
        return this.b;
    }

    @TargetApi(value=19) public void a(jc arg3) {
        String v0 = arg3.e();
        je.a(true, arg3, v0);
        if(Build$VERSION.SDK_INT >= 19) {
            try {
                this.b.evaluateJavascript(v0, null);
            }
            catch(IllegalStateException ) {
            label_10:
                this.b.loadUrl(v0);
            }
        }
        else {
            goto label_10;
        }
    }

    public void a(String arg2) {
        this.b.loadUrl(arg2);
    }

    protected void b(String arg5) {
        String v1;
        jc v0_1;
        try {
            v0_1 = jc.a(arg5);
            je.a(false, v0_1, arg5);
        }
        catch(Exception v0) {
            v1 = "IMASDK";
            String v2 = "An internal error occured parsing message from javascript.  Message to be parsed: ";
            arg5 = String.valueOf(arg5);
            arg5 = arg5.length() != 0 ? v2.concat(arg5) : new String(v2);
            Log.e(v1, arg5, ((Throwable)v0));
            return;
        }
        catch(IllegalArgumentException ) {
            String v0_2 = "IMASDK";
            v1 = "Invalid internal message, ignoring. Please make sure the Google IMA SDK library is up to date. Message: ";
            arg5 = String.valueOf(arg5);
            arg5 = arg5.length() != 0 ? v1.concat(arg5) : new String(v1);
            Log.w(v0_2, arg5);
            return;
        }

        this.a.a(v0_1);
    }

    static final void c(String arg1) {
        if(com.google.ads.interactivemedia.v3.internal.iz$a.a(com.google.ads.interactivemedia.v3.internal.iz$a.c)) {
            Log.d("IMASDK", arg1);
        }
    }
}

