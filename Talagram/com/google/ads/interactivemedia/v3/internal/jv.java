package com.google.ads.interactivemedia.v3.internal;

import android.util.Base64;

class jv implements kd {
    jv() {
        super();
    }

    public String a(byte[] arg1, boolean arg2) {
        int v2 = arg2 ? 11 : 2;
        return Base64.encodeToString(arg1, v2);
    }

    public byte[] a(String arg1, boolean arg2) {
        int v2 = arg2 ? 11 : 2;
        return Base64.decode(arg1, v2);
    }
}

