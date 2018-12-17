package com.crashlytics.android.c;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

final class b {
    public static final b a;
    private final byte[] b;
    private volatile int c;

    static {
        b.a = new b(new byte[0]);
    }

    private b(byte[] arg2) {
        super();
        this.c = 0;
        this.b = arg2;
    }

    public static b a(String arg2) {
        try {
            return new b(arg2.getBytes("UTF-8"));
        }
        catch(UnsupportedEncodingException v2) {
            throw new RuntimeException("UTF-8 not supported.", ((Throwable)v2));
        }
    }

    public static b a(byte[] arg2, int arg3, int arg4) {
        byte[] v0 = new byte[arg4];
        System.arraycopy(arg2, arg3, v0, 0, arg4);
        return new b(v0);
    }

    public int a() {
        return this.b.length;
    }

    public void a(byte[] arg2, int arg3, int arg4, int arg5) {
        System.arraycopy(this.b, arg3, arg2, arg4, arg5);
    }

    public InputStream b() {
        return new ByteArrayInputStream(this.b);
    }

    public boolean equals(Object arg8) {
        if((((b)arg8)) == this) {
            return 1;
        }

        if(!(arg8 instanceof b)) {
            return 0;
        }

        int v1 = this.b.length;
        if(v1 != ((b)arg8).b.length) {
            return 0;
        }

        byte[] v3 = this.b;
        byte[] v8 = ((b)arg8).b;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            if(v3[v4] != v8[v4]) {
                return 0;
            }
        }

        return 1;
    }

    public int hashCode() {
        int v0 = this.c;
        if(v0 == 0) {
            byte[] v0_1 = this.b;
            int v1 = this.b.length;
            int v2 = 0;
            int v3 = v1;
            while(v2 < v1) {
                v3 = v3 * 31 + v0_1[v2];
                ++v2;
            }

            v0 = v3 == 0 ? 1 : v3;
            this.c = v0;
        }

        return v0;
    }
}

