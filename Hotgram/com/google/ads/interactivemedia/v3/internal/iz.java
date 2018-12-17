package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;

public class iz {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;

        static {
            a.a = new a("VERBOSE", 0);
            a.b = new a("ABRIDGED", 1);
            a.c = new a("LIFECYCLE", 2);
            a.d = new a("PROD", 3);
            a.e = new a[]{a.a, a.b, a.c, a.d};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static boolean a(a arg1) {
            boolean v1 = arg1.ordinal() >= iz.a.ordinal() ? true : false;
            return v1;
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    public static final a a;
    public static final Uri b;
    public static final Uri c;

    static {
        iz.a = a.d;
        iz.b = Uri.parse("https://imasdk.googleapis.com/native/sdkloader/native_sdk_v3.html");
        iz.c = Uri.parse("https://imasdk.googleapis.com/native/sdkloader/native_sdk_v3_debug.html");
    }
}

