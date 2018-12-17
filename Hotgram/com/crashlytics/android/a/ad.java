package com.crashlytics.android.a;

import android.app.Activity;
import java.util.Collections;
import java.util.Map;

final class ad {
    class com.crashlytics.android.a.ad$1 {
    }

    class a {
        final b a;
        final long b;
        Map c;
        String d;
        Map e;
        String f;
        Map g;

        public a(b arg3) {
            super();
            this.a = arg3;
            this.b = System.currentTimeMillis();
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
        }

        public a a(Map arg1) {
            this.c = arg1;
            return this;
        }

        public a a(String arg1) {
            this.d = arg1;
            return this;
        }

        public ad a(ae arg13) {
            return new ad(arg13, this.b, this.a, this.c, this.d, this.e, this.f, this.g, null);
        }

        public a b(Map arg1) {
            this.e = arg1;
            return this;
        }
    }

    enum b {
        public static final enum b a;
        public static final enum b b;
        public static final enum b c;
        public static final enum b d;
        public static final enum b e;
        public static final enum b f;
        public static final enum b g;
        public static final enum b h;

        static {
            b.a = new b("START", 0);
            b.b = new b("RESUME", 1);
            b.c = new b("PAUSE", 2);
            b.d = new b("STOP", 3);
            b.e = new b("CRASH", 4);
            b.f = new b("INSTALL", 5);
            b.g = new b("CUSTOM", 6);
            b.h = new b("PREDEFINED", 7);
            b.i = new b[]{b.a, b.b, b.c, b.d, b.e, b.f, b.g, b.h};
        }

        private b(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static b valueOf(String arg1) {
            return Enum.valueOf(b.class, arg1);
        }

        public static b[] values() {
            // Method was not decompiled
        }
    }

    public final ae a;
    public final long b;
    public final b c;
    public final Map d;
    public final String e;
    public final Map f;
    public final String g;
    public final Map h;
    private String i;

    private ad(ae arg1, long arg2, b arg4, Map arg5, String arg6, Map arg7, String arg8, Map arg9) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg4;
        this.d = arg5;
        this.e = arg6;
        this.f = arg7;
        this.g = arg8;
        this.h = arg9;
    }

    ad(ae arg1, long arg2, b arg4, Map arg5, String arg6, Map arg7, String arg8, Map arg9, com.crashlytics.android.a.ad$1 arg10) {
        this(arg1, arg2, arg4, arg5, arg6, arg7, arg8, arg9);
    }

    public static a a(long arg2) {
        return new a(b.f).a(Collections.singletonMap("installedAt", String.valueOf(arg2)));
    }

    public static a a(b arg1, Activity arg2) {
        return new a(arg1).a(Collections.singletonMap("activity", arg2.getClass().getName()));
    }

    public static a a(m arg2) {
        return new a(b.g).a(arg2.a()).b(arg2.b());
    }

    public static a a(String arg2) {
        return new a(b.e).a(Collections.singletonMap("sessionId", arg2));
    }

    public static a a(String arg1, String arg2) {
        return ad.a(arg1).b(Collections.singletonMap("exceptionName", arg2));
    }

    public String toString() {
        if(this.i == null) {
            this.i = "[" + this.getClass().getSimpleName() + ": " + "timestamp=" + this.b + ", type=" + this.c + ", details=" + this.d + ", customType=" + this.e + ", customAttributes=" + this.f + ", predefinedType=" + this.g + ", predefinedAttributes=" + this.h + ", metadata=[" + this.a + "]]";
        }

        return this.i;
    }
}

