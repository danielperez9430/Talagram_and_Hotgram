package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebView$WebViewTransport;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData$a;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.util.Iterator;
import java.util.List;

public class it extends WebView {
    public it(Context arg3, jd arg4, CompanionData arg5, List arg6) {
        super(arg3);
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setSupportMultipleWindows(true);
        this.setWebChromeClient(new WebChromeClient(arg3, arg4, arg6) {
            public boolean onCreateWindow(WebView arg1, boolean arg2, boolean arg3, Message arg4) {
                Object v1 = arg4.obj;
                ((WebView$WebViewTransport)v1).setWebView(new WebView(this.a));
                ((WebView$WebViewTransport)v1).getWebView().setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView arg1, String arg2) {
                        this.a.b.c(arg2);
                        Iterator v1 = this.a.c.iterator();
                        while(v1.hasNext()) {
                            v1.next().onCompanionAdClick();
                        }

                        return 1;
                    }
                });
                arg4.sendToTarget();
                return 1;
            }
        });
        if(arg5.type() == a.Html) {
            this.loadData(arg5.src(), "text/html", null);
        }
        else if(arg5.type() == a.IFrame) {
            this.loadUrl(arg5.src());
        }
        else {
            goto label_23;
        }

        return;
    label_23:
        String v4 = String.valueOf(arg5.type());
        StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 51);
        v6.append("Companion type ");
        v6.append(v4);
        v6.append(" is not valid for a CompanionWebView");
        throw new IllegalArgumentException(v6.toString());
    }
}

