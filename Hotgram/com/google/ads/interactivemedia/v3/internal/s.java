package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import org.json.JSONObject;

public class s {
    private static s a;

    static {
        s.a = new s();
    }

    private s() {
        super();
    }

    public static s a() {
        return s.a;
    }

    public void a(WebView arg3) {
        this.a(arg3, "finishSession", new Object[0]);
    }

    void a(WebView arg3, String arg4, Object[] arg5) {
        if(arg3 != null) {
            StringBuilder v0 = new StringBuilder(128);
            v0.append("javascript: if(window.omidBridge!==undefined){omidBridge.");
            v0.append(arg4);
            v0.append("(");
            this.a(v0, arg5);
            v0.append(")}");
            this.a(arg3, v0);
        }
        else {
            String v3 = String.valueOf("The WebView is null for ");
            arg4 = String.valueOf(arg4);
            v3 = arg4.length() != 0 ? v3.concat(arg4) : new String(v3);
            ad.a(v3);
        }
    }

    public void a(WebView arg4, float arg5) {
        this.a(arg4, "setDeviceVolume", new Object[]{Float.valueOf(arg5)});
    }

    public void a(WebView arg4, String arg5, JSONObject arg6, JSONObject arg7) {
        this.a(arg4, "startSession", new Object[]{arg5, arg6, arg7});
    }

    void a(StringBuilder arg6, Object[] arg7) {
        if(arg7 != null && arg7.length > 0) {
            int v0 = arg7.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = arg7[v1];
                char v3 = '\"';
                if(v2 == null) {
                    arg6.append(v3);
                    goto label_10;
                }
                else if((v2 instanceof String)) {
                    String v2_1 = v2.toString();
                    if(v2_1.startsWith("{")) {
                        arg6.append(v2_1);
                    }
                    else {
                        arg6.append(v3);
                        arg6.append(v2_1);
                    label_10:
                        arg6.append(v3);
                    }
                }
                else {
                    arg6.append(v2);
                }

                arg6.append(",");
            }

            arg6.setLength(arg6.length() - 1);
        }
    }

    void a(WebView arg4, StringBuilder arg5) {
        String v5 = arg5.toString();
        Handler v0 = arg4.getHandler();
        if(v0 == null || Looper.myLooper() == v0.getLooper()) {
            arg4.loadUrl(v5);
        }
        else {
            v0.post(new Runnable(arg4, v5) {
                public void run() {
                    this.a.loadUrl(this.b);
                }
            });
        }
    }

    public void a(WebView arg4, JSONObject arg5) {
        this.a(arg4, "init", new Object[]{arg5});
    }

    public boolean a(WebView arg3, String arg4) {
        if(arg3 != null && !TextUtils.isEmpty(((CharSequence)arg4))) {
            String v0 = "javascript: ";
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v0.concat(arg4) : new String(v0);
            arg3.loadUrl(arg4);
            return 1;
        }

        return 0;
    }

    public void b(WebView arg3, String arg4) {
        if(arg4 != null) {
            this.a(arg3, "var script=document.createElement(\'script\');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", ((CharSequence)arg4)));
        }
    }

    public void c(WebView arg4, String arg5) {
        this.a(arg4, "setNativeViewHierarchy", new Object[]{arg5});
    }

    public void d(WebView arg4, String arg5) {
        this.a(arg4, "setState", new Object[]{arg5});
    }
}

