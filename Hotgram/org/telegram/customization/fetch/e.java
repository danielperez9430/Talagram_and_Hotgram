package org.telegram.customization.fetch;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.c;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class e implements Runnable {
    private final long a;
    private final String b;
    private final String c;
    private final List d;
    private final boolean e;
    private final long f;
    private final Context g;
    private final c h;
    private final a i;
    private volatile boolean j;
    private HttpURLConnection k;
    private BufferedInputStream l;
    private RandomAccessFile m;
    private int n;
    private long o;
    private long p;

    e(Context arg2, long arg3, String arg5, String arg6, List arg7, long arg8, boolean arg10, long arg11) {
        ArrayList v7;
        super();
        this.j = false;
        if(arg2 != null) {
            if(arg5 != null) {
                if(arg6 != null) {
                    if(arg7 == null) {
                        v7 = new ArrayList();
                    }

                    this.d = ((List)v7);
                    this.a = arg3;
                    this.b = arg5;
                    this.c = arg6;
                    this.p = arg8;
                    this.g = arg2.getApplicationContext();
                    this.h = c.a(this.g);
                    this.i = a.a(this.g);
                    this.e = arg10;
                    this.f = arg11;
                    this.i.a(arg10);
                    return;
                }

                throw new NullPointerException("FilePath cannot be null");
            }

            throw new NullPointerException("Url cannot be null");
        }

        throw new NullPointerException("Context cannot be null");
    }

    static IntentFilter a() {
        return new IntentFilter("com.tonyodev.fetch.action_done");
    }

    static long a(Intent arg3) {
        long v0 = -1;
        if(arg3 == null) {
            return v0;
        }

        return arg3.getLongExtra("com.tonyodev.fetch.extra_id", v0);
    }

    private boolean a(int arg2) {
        if(arg2 != 200 && arg2 != 202 && arg2 != 206) {
            return 0;
        }

        return 1;
    }

    void b() {
        __monitor_enter(this);
        try {
            this.j = true;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private boolean b(int arg3) {
        if(!f.b(this.g)) {
            return 1;
        }

        if(arg3 != -118) {
            switch(arg3) {
                case -104: 
                case -103: {
                    return 1;
                }
                default: {
                    return 0;
                }
            }
        }

        return 1;
    }

    long c() {
        long v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.a;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private void d() {
        this.k = new URL(this.b).openConnection();
        this.k.setRequestMethod("GET");
        this.k.setReadTimeout(20000);
        this.k.setConnectTimeout(15000);
        this.k.setUseCaches(false);
        this.k.setDefaultUseCaches(false);
        this.k.setInstanceFollowRedirects(true);
        this.k.setDoInput(true);
        Iterator v0 = this.d.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            this.k.addRequestProperty(((org.telegram.customization.fetch.d.a)v1).a(), ((org.telegram.customization.fetch.d.a)v1).b());
        }
    }

    private void e() {
        try {
            this.p = this.o + Long.valueOf(this.k.getHeaderField("Content-Length")).longValue();
        }
        catch(Exception ) {
            this.p = -1;
        }
    }

    private void f() {
        e v0 = this;
        int v1 = 1024;
        byte[] v2 = new byte[v1];
        while(true) {
            long v3 = System.nanoTime();
            do {
                int v5 = v0.l.read(v2, 0, v1);
                if(v5 != -1 && !this.i()) {
                    v0.m.write(v2, 0, v5);
                    v0.o += ((long)v5);
                    if(!f.a(v3, System.nanoTime(), v0.f)) {
                        continue;
                    }

                    if(this.i()) {
                        continue;
                    }

                    break;
                }

                return;
            }
            while(true);

            v0.n = f.a(v0.o, v0.p);
            f.a(v0.h, v0.a, 901, v0.n, v0.o, v0.p, -1);
            v0.i.a(v0.a, v0.o, v0.p);
        }
    }

    private void g() {
        try {
            if(this.l == null) {
                goto label_9;
            }

            this.l.close();
        }
        catch(IOException v0) {
            if(!this.e) {
                goto label_9;
            }

            v0.printStackTrace();
        }

        try {
        label_9:
            if(this.m == null) {
                goto label_18;
            }

            this.m.close();
        }
        catch(IOException v0) {
            if(!this.e) {
                goto label_18;
            }

            v0.printStackTrace();
        }

    label_18:
        if(this.k != null) {
            this.k.disconnect();
        }
    }

    private void h() {
        Intent v0 = new Intent("com.tonyodev.fetch.action_done");
        v0.putExtra("com.tonyodev.fetch.extra_id", this.a);
        this.h.a(v0);
    }

    private boolean i() {
        return this.j;
    }

    public void run() {
        int v0 = -1;
        try {
            this.d();
            f.g(this.c);
            this.o = f.d(this.c);
            this.n = f.a(this.o, this.p);
            this.i.a(this.a, this.o, this.p);
            HttpURLConnection v1_1 = this.k;
            v1_1.setRequestProperty("Range", "bytes=" + this.o + "-");
            int v2 = -118;
            if(!this.i()) {
                this.k.connect();
                int v1_2 = this.k.getResponseCode();
                if(this.a(v1_2)) {
                    if(!this.i()) {
                        long v5 = 1;
                        if(this.p < v5) {
                            this.e();
                            this.i.a(this.a, this.o, this.p);
                            this.n = f.a(this.o, this.p);
                        }

                        this.m = new RandomAccessFile(this.c, "rw");
                        if(v1_2 == 206) {
                            this.m.seek(this.o);
                        }
                        else {
                            this.m.seek(0);
                        }

                        this.l = new BufferedInputStream(this.k.getInputStream());
                        this.f();
                        this.i.a(this.a, this.o, this.p);
                        if(!this.i()) {
                            if(this.o < this.p) {
                                goto label_174;
                            }

                            if(this.i()) {
                                goto label_174;
                            }

                            if(this.p < v5) {
                                this.p = f.d(this.c);
                                this.i.a(this.a, this.o, this.p);
                                v1_2 = f.a(this.o, this.p);
                            }
                            else {
                                v1_2 = f.a(this.o, this.p);
                            }

                            this.n = v1_2;
                            if(!this.i.a(this.a, 903, v0)) {
                                goto label_174;
                            }

                            f.a(this.h, this.a, 903, this.n, this.o, this.p, -1);
                            goto label_174;
                        }

                        throw new org.telegram.customization.fetch.b.a("DIE", v2);
                    }

                    throw new org.telegram.customization.fetch.b.a("DIE", v2);
                }

                v3 = new StringBuilder();
                v3.append("SSRV:");
                v3.append(v1_2);
                throw new IllegalStateException(v3.toString());
            }

            throw new org.telegram.customization.fetch.b.a("DIE", v2);
        }
        catch(Throwable v0_1) {
        label_177:
            this.g();
            this.h();
            throw v0_1;
        }
        catch(Exception v1) {
            try {
                if(this.e) {
                    v1.printStackTrace();
                }

                int v11 = b.a(v1.getMessage());
                if(this.b(v11)) {
                    if(!this.i.a(this.a, 900, v0)) {
                        goto label_174;
                    }

                    f.a(this.h, this.a, 900, this.n, this.o, this.p, -1);
                    goto label_174;
                }

                if(!this.i.a(this.a, 904, v11)) {
                    goto label_174;
                }

                f.a(this.h, this.a, 904, this.n, this.o, this.p, v11);
            }
            catch(Throwable v0_1) {
                goto label_177;
            }

        label_174:
            this.g();
            this.h();
            return;
        }
    }
}

