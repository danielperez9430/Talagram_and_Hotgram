package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

final class bb implements ba {
    private final Handler a;
    private final bc b;
    private final CopyOnWriteArraySet c;
    private final bj[][] d;
    private final int[] e;
    private boolean f;
    private int g;
    private int h;

    @SuppressLint(value={"HandlerLeak"}) public bb(int arg7, int arg8, int arg9) {
        super();
        Log.i("ExoPlayerImpl", "Init ExoPlayerLib/1.5.16");
        this.f = false;
        this.g = 1;
        this.c = new CopyOnWriteArraySet();
        this.d = new bj[arg7][];
        this.e = new int[arg7];
        this.a = new Handler() {
            public void handleMessage(Message arg2) {
                this.a.a(arg2);
            }
        };
        this.b = new bc(this.a, this.f, this.e, arg8, arg9);
    }

    void a(Message arg5) {
        Iterator v5;
        switch(arg5.what) {
            case 1: {
                System.arraycopy(arg5.obj, 0, this.d, 0, this.d.length);
                this.g = arg5.arg1;
                v5 = this.c.iterator();
                while(v5.hasNext()) {
                    v5.next().a(this.f, this.g);
                }
            }
            case 2: {
                this.g = arg5.arg1;
                v5 = this.c.iterator();
                while(v5.hasNext()) {
                    v5.next().a(this.f, this.g);
                }
            }
            case 3: {
                --this.h;
                if(this.h != 0) {
                    return;
                }

                v5 = this.c.iterator();
                while(v5.hasNext()) {
                    v5.next().a();
                }
            }
            case 4: {
                Object v5_1 = arg5.obj;
                Iterator v0 = this.c.iterator();
                while(v0.hasNext()) {
                    v0.next().a(((az)v5_1));
                }
            }
            default: {
                break;
            }
        }
    }

    public int a() {
        return this.g;
    }

    public void a(long arg2) {
        this.b.a(arg2);
    }

    public void a(a arg2, int arg3, Object arg4) {
        this.b.a(arg2, arg3, arg4);
    }

    public void a(c arg2) {
        this.c.add(arg2);
    }

    public void a(boolean arg4) {
        if(this.f != arg4) {
            this.f = arg4;
            ++this.h;
            this.b.a(arg4);
            Iterator v0 = this.c.iterator();
            while(v0.hasNext()) {
                v0.next().a(arg4, this.g);
            }
        }
    }

    public void a(bq[] arg3) {
        Arrays.fill(this.d, null);
        this.b.a(arg3);
    }

    public void b() {
        this.b.c();
    }

    public void b(a arg2, int arg3, Object arg4) {
        this.b.b(arg2, arg3, arg4);
    }

    public void b(c arg2) {
        this.c.remove(arg2);
    }

    public void c() {
        this.b.d();
        this.a.removeCallbacksAndMessages(null);
    }

    public long d() {
        return this.b.b();
    }

    public long e() {
        return this.b.a();
    }
}

