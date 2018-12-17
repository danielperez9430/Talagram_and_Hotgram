package com.google.ads.interactivemedia.v3.internal;

import java.util.logging.Logger;

final class kq {
    class com.google.ads.interactivemedia.v3.internal.kq$1 {
    }

    final class a implements kp {
        a(com.google.ads.interactivemedia.v3.internal.kq$1 arg1) {
            this();
        }

        private a() {
            super();
        }
    }

    private static final Logger a;
    private static final kp b;

    static {
        kq.a = Logger.getLogger(kq.class.getName());
        kq.b = kq.a();
    }

    private kq() {
        super();
    }

    private static kp a() {
        return new a(null);
    }

    static boolean a(String arg0) {
        boolean v0 = arg0 == null || (arg0.isEmpty()) ? true : false;
        return v0;
    }
}

