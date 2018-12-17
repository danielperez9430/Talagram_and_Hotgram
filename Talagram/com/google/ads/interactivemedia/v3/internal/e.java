package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class e {
    private final i a;
    private final WebView b;
    private final List c;
    private final String d;
    private final String e;
    private final f f;

    private e(i arg2, WebView arg3, String arg4, List arg5, String arg6) {
        f v2;
        super();
        this.c = new ArrayList();
        this.a = arg2;
        this.b = arg3;
        this.d = arg4;
        if(arg5 != null) {
            this.c.addAll(((Collection)arg5));
            v2 = f.b;
        }
        else {
            v2 = f.a;
        }

        this.f = v2;
        this.e = arg6;
    }

    public static e a(i arg8, WebView arg9, String arg10) {
        af.a(arg8, "Partner is null");
        af.a(arg9, "WebView is null");
        if(arg10 != null) {
            af.a(arg10, 256, "CustomReferenceData is greater than 256 characters");
        }

        return new e(arg8, arg9, null, null, arg10);
    }

    public i a() {
        return this.a;
    }

    public List b() {
        return Collections.unmodifiableList(this.c);
    }

    public WebView c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public f f() {
        return this.f;
    }
}

