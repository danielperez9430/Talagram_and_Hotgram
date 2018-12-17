package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.Arrays;

public final class eu {
    public final Uri a;
    public final byte[] b;
    public final long c;
    public final long d;
    public final long e;
    public final String f;
    public final int g;

    public eu(Uri arg12, long arg13, long arg15, long arg17, String arg19, int arg20) {
        this(arg12, null, arg13, arg15, arg17, arg19, arg20);
    }

    public eu(Uri arg6, byte[] arg7, long arg8, long arg10, long arg12, String arg14, int arg15) {
        super();
        long v0 = 0;
        boolean v3 = false;
        boolean v2 = Long.compare(arg8, v0) >= 0 ? true : false;
        fe.a(v2);
        v2 = arg10 >= v0 ? true : false;
        fe.a(v2);
        if(arg12 > v0 || arg12 == -1) {
            v3 = true;
        }

        fe.a(v3);
        this.a = arg6;
        this.b = arg7;
        this.c = arg8;
        this.d = arg10;
        this.e = arg12;
        this.f = arg14;
        this.g = arg15;
    }

    public eu(Uri arg11, long arg12, long arg14, String arg16) {
        this(arg11, arg12, arg12, arg14, arg16, 0);
    }

    public String toString() {
        String v0 = String.valueOf(this.a);
        String v1 = Arrays.toString(this.b);
        long v2 = this.c;
        long v4 = this.d;
        long v6 = this.e;
        String v8 = this.f;
        int v9 = this.g;
        StringBuilder v11 = new StringBuilder(String.valueOf(v0).length() + 93 + String.valueOf(v1).length() + String.valueOf(v8).length());
        v11.append("DataSpec[");
        v11.append(v0);
        v11.append(", ");
        v11.append(v1);
        v11.append(", ");
        v11.append(v2);
        v11.append(", ");
        v11.append(v4);
        v11.append(", ");
        v11.append(v6);
        v11.append(", ");
        v11.append(v8);
        v11.append(", ");
        v11.append(v9);
        v11.append("]");
        return v11.toString();
    }
}

