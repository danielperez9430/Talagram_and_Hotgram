package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import android.util.Log;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ew implements ez {
    private static final Pattern b;
    private static final AtomicReference c;
    private final boolean d;
    private final int e;
    private final int f;
    private final String g;
    private final fq h;
    private final HashMap i;
    private final fb j;
    private eu k;
    private HttpURLConnection l;
    private InputStream m;
    private boolean n;
    private long o;
    private long p;
    private long q;
    private long r;

    static {
        ew.b = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
        ew.c = new AtomicReference();
    }

    public ew(String arg1, fq arg2, fb arg3, int arg4, int arg5, boolean arg6) {
        super();
        this.g = fe.a(arg1);
        this.h = arg2;
        this.j = arg3;
        this.i = new HashMap();
        this.e = arg4;
        this.f = arg5;
        this.d = arg6;
    }

    private static long a(HttpURLConnection arg8) {
        long v1;
        String v0 = arg8.getHeaderField("Content-Length");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            try {
                v1 = Long.parseLong(v0);
                goto label_20;
            }
            catch(NumberFormatException ) {
                StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 28);
                v3.append("Unexpected Content-Length [");
                v3.append(v0);
                v3.append("]");
                Log.e("DefaultHttpDataSource", v3.toString());
            }
        }

        v1 = -1;
    label_20:
        String v8 = arg8.getHeaderField("Content-Range");
        if(!TextUtils.isEmpty(((CharSequence)v8))) {
            Matcher v3_1 = ew.b.matcher(((CharSequence)v8));
            if(!v3_1.find()) {
                return v1;
            }

            int v4 = 2;
            try {
                long v4_1 = Long.parseLong(v3_1.group(v4)) - Long.parseLong(v3_1.group(1)) + 1;
                if(v1 < 0) {
                    return v4_1;
                }

                if(v1 == v4_1) {
                    return v1;
                }

                StringBuilder v7 = new StringBuilder(String.valueOf(v0).length() + 26 + String.valueOf(v8).length());
                v7.append("Inconsistent headers [");
                v7.append(v0);
                v7.append("] [");
                v7.append(v8);
                v7.append("]");
                Log.w("DefaultHttpDataSource", v7.toString());
                v1 = Math.max(v1, v4_1);
            }
            catch(NumberFormatException ) {
                StringBuilder v4_2 = new StringBuilder(String.valueOf(v8).length() + 27);
                v4_2.append("Unexpected Content-Range [");
                v4_2.append(v8);
                v4_2.append("]");
                Log.e("DefaultHttpDataSource", v4_2.toString());
            }
        }

        return v1;
    }

    private HttpURLConnection a(URL arg5, byte[] arg6, long arg7, long arg9, boolean arg11, boolean arg12) {
        // Method was not decompiled
    }

    private static URL a(URL arg2, String arg3) {
        if(arg3 != null) {
            URL v0 = new URL(arg2, arg3);
            String v2 = v0.getProtocol();
            if(!"https".equals(v2) && !"http".equals(v2)) {
                String v0_1 = "Unsupported protocol redirect: ";
                v2 = String.valueOf(v2);
                v2 = v2.length() != 0 ? v0_1.concat(v2) : new String(v0_1);
                throw new ProtocolException(v2);
            }

            return v0;
        }

        throw new ProtocolException("Null location redirect");
    }

    public int a(byte[] arg2, int arg3, int arg4) {
        try {
            this.c();
            return this.b(arg2, arg3, arg4);
        }
        catch(IOException v2) {
            throw new a(v2, this.k, 2);
        }
    }

    public long a(eu arg9) {
        int v3_1;
        String v4;
        String v3;
        this.k = arg9;
        long v0 = 0;
        this.r = v0;
        this.q = v0;
        try {
            this.l = this.b(arg9);
        }
        catch(IOException v0_1) {
            v3 = "Unable to connect to ";
            v4 = String.valueOf(arg9.a.toString());
            v3 = v4.length() != 0 ? v3.concat(v4) : new String(v3);
            throw new a(v3, v0_1, arg9, 1);
        }

        try {
            v3_1 = this.l.getResponseCode();
            int v4_1 = 200;
            if(v3_1 < v4_1) {
                goto label_62;
            }
        }
        catch(IOException v0_1) {
            this.d();
            v3 = "Unable to connect to ";
            v4 = String.valueOf(arg9.a.toString());
            v3 = v4.length() != 0 ? v3.concat(v4) : new String(v3);
            throw new a(v3, v0_1, arg9, 1);
        }

        if(v3_1 <= 299) {
            String v5 = this.l.getContentType();
            if(this.h != null) {
                if(this.h.a(v5)) {
                }
                else {
                    this.d();
                    throw new b(v5, arg9);
                }
            }

            if(v3_1 == v4_1 && arg9.d != v0) {
                v0 = arg9.d;
            }

            this.o = v0;
            if((arg9.g & 1) == 0) {
                v0 = ew.a(this.l);
                long v5_1 = -1;
                if(arg9.e != v5_1) {
                    v5_1 = arg9.e;
                }
                else if(v0 != v5_1) {
                    v5_1 = v0 - this.o;
                }

                this.p = v5_1;
            }
            else {
                this.p = arg9.e;
            }

            try {
                this.m = this.l.getInputStream();
            }
            catch(IOException v0_1) {
                this.d();
                throw new a(v0_1, arg9, 1);
            }

            this.n = true;
            if(this.j != null) {
                this.j.a();
            }

            return this.p;
        }

    label_62:
        Map v0_2 = this.l.getHeaderFields();
        this.d();
        throw new c(v3_1, v0_2, arg9);
    }

    public void a() {
        InputStream v1 = null;
        try {
            if(this.m != null) {
                ft.a(this.l, this.b());
                try {
                    this.m.close();
                }
                catch(IOException v2_1) {
                    try {
                        throw new a(v2_1, this.k, 3);
                    }
                    catch(Throwable v2) {
                    label_27:
                        this.m = v1;
                        this.d();
                        if(this.n) {
                            this.n = false;
                            if(this.j != null) {
                                this.j.b();
                            }
                        }

                        throw v2;
                    }
                }
            }
        }
        catch(Throwable v2) {
            goto label_27;
        }

        this.m = v1;
        this.d();
        if(this.n) {
            this.n = false;
            if(this.j != null) {
                this.j.b();
            }
        }
    }

    private int b(byte[] arg5, int arg6, int arg7) {
        if(arg7 == 0) {
            return 0;
        }

        int v5 = this.m.read(arg5, arg6, arg7);
        arg6 = -1;
        if(v5 == arg6) {
            if(this.p != -1) {
                if(this.p == this.r) {
                }
                else {
                    throw new EOFException();
                }
            }

            return arg6;
        }

        this.r += ((long)v5);
        if(this.j != null) {
            this.j.a(v5);
        }

        return v5;
    }

    private HttpURLConnection b(eu arg19) {
        int v0;
        URL v1 = new URL(arg19.a.toString());
        byte[] v2 = arg19.b;
        long v12 = arg19.d;
        long v14 = arg19.e;
        int v4 = 0;
        boolean v16 = (arg19.g & 1) != 0 ? true : false;
        ew v11 = this;
        if(!v11.d) {
            return this.a(v1, v2, v12, v14, v16, true);
        }

        while(true) {
            v0 = v4 + 1;
            if(v4 > 20) {
                break;
            }

            HttpURLConnection v3 = this.a(v1, v2, v12, v14, v16, false);
            v4 = v3.getResponseCode();
            if(v4 != 300 && v4 != 301 && v4 != 302 && v4 != 303) {
                if(v2 == null) {
                    if(v4 == 307) {
                        goto label_55;
                    }
                    else if(v4 == 308) {
                        goto label_55;
                    }
                }

                return v3;
            }

        label_55:
            v2 = null;
            String v4_1 = v3.getHeaderField("Location");
            v3.disconnect();
            v1 = ew.a(v1, v4_1);
            v4 = v0;
        }

        StringBuilder v3_1 = new StringBuilder(31);
        v3_1.append("Too many redirects: ");
        v3_1.append(v0);
        throw new NoRouteToHostException(v3_1.toString());
    }

    protected final long b() {
        long v0 = this.p == -1 ? this.p : this.p - this.r;
        return v0;
    }

    private void c() {
        if(this.q == this.o) {
            return;
        }

        Object v0 = ew.c.getAndSet(null);
        if(v0 == null) {
            byte[] v0_1 = new byte[4096];
        }

        while(true) {
            if(this.q == this.o) {
                goto label_42;
            }

            int v1 = this.m.read(((byte[])v0), 0, ((int)Math.min(this.o - this.q, ((long)v0.length))));
            if(Thread.interrupted()) {
                goto label_39;
            }

            if(v1 == -1) {
                break;
            }

            this.q += ((long)v1);
            if(this.j == null) {
                continue;
            }

            this.j.a(v1);
        }

        throw new EOFException();
    label_39:
        throw new InterruptedIOException();
    label_42:
        ew.c.set(v0);
    }

    private void d() {
        if(this.l != null) {
            try {
                this.l.disconnect();
            }
            catch(Exception v0) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", ((Throwable)v0));
            }

            this.l = null;
        }
    }
}

