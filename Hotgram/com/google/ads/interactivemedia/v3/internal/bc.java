package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class bc implements Handler$Callback {
    private final Handler a;
    private final HandlerThread b;
    private final Handler c;
    private final bp d;
    private final AtomicInteger e;
    private final List f;
    private final bj[][] g;
    private final int[] h;
    private final long i;
    private final long j;
    private bq[] k;
    private bq l;
    private bd m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private long t;
    private long u;
    private volatile long v;
    private volatile long w;
    private volatile long x;

    public bc(Handler arg3, boolean arg4, int[] arg5, int arg6, int arg7) {
        super();
        this.r = 0;
        this.s = 0;
        this.c = arg3;
        this.o = arg4;
        this.i = (((long)arg6)) * 1000;
        this.j = (((long)arg7)) * 1000;
        this.h = Arrays.copyOf(arg5, arg5.length);
        this.q = 1;
        this.v = -1;
        this.x = -1;
        this.d = new bp();
        this.e = new AtomicInteger();
        this.f = new ArrayList(arg5.length);
        this.g = new bj[arg5.length][];
        this.b = new fr("ExoPlayerImplInternal:Handler", -16);
        this.b.start();
        this.a = new Handler(this.b.getLooper(), ((Handler$Callback)this));
    }

    private void a(int arg4) {
        if(this.q != arg4) {
            this.q = arg4;
            this.c.obtainMessage(2, arg4, 0).sendToTarget();
        }
    }

    private void a(int arg8, int arg9) {
        if(this.h[arg8] == arg9) {
            return;
        }

        this.h[arg8] = arg9;
        boolean v1 = true;
        if(this.q != 1) {
            int v2 = 2;
            if(this.q == v2) {
            }
            else {
                bq v0 = this.k[arg8];
                int v3 = v0.v();
                if(v3 != 0 && v3 != -1) {
                    if(v0.u() == 0) {
                    }
                    else {
                        v2 = v3 == v2 || v3 == 3 ? 1 : 0;
                        arg8 = arg9 < 0 || arg9 >= this.g[arg8].length ? 0 : 1;
                        if(v2 != 0) {
                            if(arg8 == 0 && v0 == this.l) {
                                this.d.a(this.m.a());
                            }

                            this.e(v0);
                            this.f.remove(v0);
                        }

                        if(arg8 == 0) {
                            return;
                        }

                        arg8 = !this.o || this.q != 4 ? 0 : 1;
                        if(v2 != 0 || arg8 == 0) {
                            v1 = false;
                        }
                        else {
                        }

                        this.a(v0, arg9, v1);
                        if(arg8 != 0) {
                            v0.w();
                        }

                        this.a.sendEmptyMessage(7);
                    }
                }
            }
        }
    }

    private void a(bq arg3, int arg4, boolean arg5) {
        arg3.b(arg4, this.w, arg5);
        this.f.add(arg3);
        bd v4 = arg3.b();
        if(v4 != null) {
            arg5 = this.m == null ? true : false;
            fe.b(arg5);
            this.m = v4;
            this.l = arg3;
        }
    }

    private void a(int arg2, long arg3, long arg5) {
        arg3 = arg3 + arg5 - SystemClock.elapsedRealtime();
        if(arg3 <= 0) {
            this.a.sendEmptyMessage(arg2);
        }
        else {
            this.a.sendEmptyMessageDelayed(arg2, arg3);
        }
    }

    private void a(int arg3, Object arg4) {
        try {
            ((Pair)arg4).first.a(arg3, ((Pair)arg4).second);
            if(this.q != 1 && this.q != 2) {
                this.a.sendEmptyMessage(7);
            }
        }
        catch(Throwable v3) {
            __monitor_enter(this);
            try {
                ++this.s;
                this.notifyAll();
                __monitor_exit(this);
            }
            catch(Throwable v3) {
                try {
                label_31:
                    __monitor_exit(this);
                }
                catch(Throwable v3) {
                    goto label_31;
                }

                throw v3;
            }

            throw v3;
        }

        __monitor_enter(this);
        try {
            ++this.s;
            this.notifyAll();
            __monitor_exit(this);
            return;
        label_20:
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            goto label_20;
        }

        throw v3;
    }

    private boolean a(bq arg14) {
        boolean v1 = true;
        if(arg14.e()) {
            return 1;
        }

        if(!arg14.f()) {
            return 0;
        }

        if(this.q == 4) {
            return 1;
        }

        long v3 = arg14.r();
        long v5 = arg14.q();
        long v7 = this.p ? this.j : this.i;
        if(v7 > 0) {
            long v9 = -1;
            if(v5 != v9 && v5 != -3 && v5 < this.w + v7) {
                if(v3 != v9 && v3 != -2 && v5 >= v3) {
                    return v1;
                }

                v1 = false;
            }
        }

        return v1;
    }

    public long a() {
        long v0 = this.e.get() > 0 ? this.t : this.w / 1000;
        return v0;
    }

    public void a(long arg3) {
        this.t = arg3;
        this.e.incrementAndGet();
        this.a.obtainMessage(6, ft.a(arg3), ft.b(arg3)).sendToTarget();
    }

    public void a(a arg3, int arg4, Object arg5) {
        ++this.r;
        this.a.obtainMessage(9, arg4, 0, Pair.create(arg3, arg5)).sendToTarget();
    }

    public void a(boolean arg4) {
        this.a.obtainMessage(3, ((int)arg4), 0).sendToTarget();
    }

    public void a(bq[] arg3) {
        this.a.obtainMessage(1, arg3).sendToTarget();
    }

    private void b(long arg6) {
        long v2;
        try {
            v2 = 1000;
            if(arg6 != this.w / v2) {
                goto label_7;
            }

            goto label_4;
        }
        catch(Throwable v6) {
            goto label_43;
        }

    label_7:
        int v0 = 0;
        try {
            this.p = false;
            this.w = arg6 * v2;
            this.d.c();
            this.d.a(this.w);
            if(this.q != 1) {
                if(this.q == 2) {
                    goto label_4;
                }

                while(v0 < this.f.size()) {
                    Object v6_1 = this.f.get(v0);
                    this.d(((bq)v6_1));
                    ((bq)v6_1).d(this.w);
                    ++v0;
                }

                this.a(3);
                this.a.sendEmptyMessage(7);
                goto label_38;
            }

            goto label_4;
        }
        catch(Throwable v6) {
            goto label_43;
        }

    label_38:
        this.e.decrementAndGet();
        return;
    label_43:
        this.e.decrementAndGet();
        throw v6;
    label_4:
        this.e.decrementAndGet();
    }

    private void b(bq arg3) {
        try {
            this.e(arg3);
        }
        catch(RuntimeException v3) {
            Log.e("ExoPlayerImplInternal", "Stop failed.", ((Throwable)v3));
        }
    }

    private void b(boolean arg4) {
        Handler v4_1;
        int v1 = 3;
        try {
            this.p = false;
            this.o = arg4;
            if(!arg4) {
                this.g();
                this.h();
            }
            else {
                int v2 = 7;
                if(this.q == 4) {
                    this.f();
                    v4_1 = this.a;
                }
                else if(this.q == v1) {
                    v4_1 = this.a;
                }
                else {
                    goto label_20;
                }

                v4_1.sendEmptyMessage(v2);
            }
        }
        catch(Throwable v4) {
            this.c.obtainMessage(v1).sendToTarget();
            throw v4;
        }

    label_20:
        this.c.obtainMessage(v1).sendToTarget();
    }

    private void b(bq[] arg2) {
        this.l();
        this.k = arg2;
        Arrays.fill(this.g, null);
        this.a(2);
        this.e();
    }

    public long b() {
        long v2 = -1;
        if(this.v == v2) {
        }
        else {
            v2 = this.v / 1000;
        }

        return v2;
    }

    public void b(a arg5, int arg6, Object arg7) {
        __monitor_enter(this);
        try {
            if(!this.n) {
                goto label_16;
            }

            StringBuilder v0 = new StringBuilder(57);
            v0.append("Sent message(");
            v0.append(arg6);
            v0.append(") after release. Message ignored.");
            Log.w("ExoPlayerImplInternal", v0.toString());
        }
        catch(Throwable v5) {
            goto label_35;
        }

        __monitor_exit(this);
        return;
        try {
        label_16:
            int v0_1 = this.r;
            this.r = v0_1 + 1;
            this.a.obtainMessage(9, arg6, 0, Pair.create(arg5, arg7)).sendToTarget();
            while(this.s <= v0_1) {
                try {
                    this.wait();
                }
                catch(InterruptedException ) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(Throwable v5) {
            goto label_35;
        }

        __monitor_exit(this);
        return;
    label_35:
        __monitor_exit(this);
        throw v5;
    }

    private void c(bq arg3) {
        try {
            arg3.z();
        }
        catch(RuntimeException v3) {
            Log.e("ExoPlayerImplInternal", "Release failed.", ((Throwable)v3));
        }
    }

    public void c() {
        this.a.sendEmptyMessage(4);
    }

    private void d(bq arg3) {
        if(arg3.v() == 3) {
            arg3.x();
        }
    }

    public void d() {
        __monitor_enter(this);
        try {
            if(!this.n) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_20;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            this.a.sendEmptyMessage(5);
            while(!this.n) {
                try {
                    this.wait();
                }
                catch(InterruptedException ) {
                    Thread.currentThread().interrupt();
                }
            }

            this.b.quit();
        }
        catch(Throwable v0) {
            goto label_20;
        }

        __monitor_exit(this);
        return;
    label_20:
        __monitor_exit(this);
        throw v0;
    }

    private void e(bq arg3) {
        this.d(arg3);
        if(arg3.v() == 2) {
            arg3.y();
            if(arg3 == this.l) {
                this.m = null;
                this.l = null;
            }
        }
    }

    private void e() {
        int v3_1;
        long v2 = SystemClock.elapsedRealtime();
        int v4 = 0;
        int v5 = 1;
        while(v4 < this.k.length) {
            bq v6 = this.k[v4];
            if(v6.v() == 0 && v6.f(this.w) == 0) {
                v6.s();
                v5 = 0;
            }

            ++v4;
        }

        if(v5 == 0) {
            this.a(2, v2, 10);
            return;
        }

        long v3 = 0;
        int v2_1 = 0;
        v5 = 1;
        int v6_1 = 1;
        while(true) {
            long v8 = -1;
            if(v2_1 >= this.k.length) {
                break;
            }

            bq v7 = this.k[v2_1];
            int v10 = v7.u();
            bj[] v11 = new bj[v10];
            int v12;
            for(v12 = 0; v12 < v10; ++v12) {
                v11[v12] = v7.b(v12);
            }

            this.g[v2_1] = v11;
            if(v10 > 0) {
                if(v3 == v8) {
                }
                else {
                    long v12_1 = v7.r();
                    if(v12_1 == v8) {
                        v3 = v8;
                    }
                    else if(v12_1 == -2) {
                    }
                    else {
                        v3 = Math.max(v3, v12_1);
                    }
                }

                int v8_1 = this.h[v2_1];
                if(v8_1 < 0) {
                    goto label_75;
                }

                if(v8_1 >= v11.length) {
                    goto label_75;
                }

                this.a(v7, v8_1, false);
                v5 = v5 == 0 || !v7.e() ? 0 : 1;
                if(v6_1 != 0 && (this.a(v7))) {
                    v6_1 = 1;
                    goto label_75;
                }

                v6_1 = 0;
            }

        label_75:
            ++v2_1;
        }

        this.v = v3;
        v2_1 = 4;
        if(v5 != 0) {
            if(v3 != v8 && v3 > this.w) {
                goto label_86;
            }

            v3_1 = 5;
        }
        else {
        label_86:
            if(v6_1 != 0) {
                v3_1 = 4;
                goto label_84;
            }

            v3_1 = 3;
        }

    label_84:
        this.q = v3_1;
        this.c.obtainMessage(1, this.q, 0, this.g).sendToTarget();
        if((this.o) && this.q == v2_1) {
            this.f();
        }

        this.a.sendEmptyMessage(7);
    }

    private void f() {
        int v0 = 0;
        this.p = false;
        this.d.b();
        while(v0 < this.f.size()) {
            this.f.get(v0).w();
            ++v0;
        }
    }

    private void g() {
        this.d.c();
        int v0;
        for(v0 = 0; v0 < this.f.size(); ++v0) {
            this.d(this.f.get(v0));
        }
    }

    private void h() {
        if(this.m == null || !this.f.contains(this.l) || (this.l.e())) {
            this.w = this.d.a();
        }
        else {
            this.w = this.m.a();
            this.d.a(this.w);
        }

        this.u = SystemClock.elapsedRealtime() * 1000;
    }

    public boolean handleMessage(Message arg5) {
        int v0 = 4;
        try {
            boolean v3 = false;
            switch(arg5.what) {
                case 1: {
                    goto label_32;
                }
                case 2: {
                    goto label_30;
                }
                case 3: {
                    goto label_25;
                }
                case 4: {
                    goto label_23;
                }
                case 5: {
                    goto label_21;
                }
                case 6: {
                    goto label_16;
                }
                case 7: {
                    goto label_14;
                }
                case 8: {
                    goto label_10;
                }
                case 9: {
                    goto label_6;
                }
            }

            return 0;
        label_21:
            this.k();
            return 1;
        label_6:
            this.a(arg5.arg1, arg5.obj);
            return 1;
        label_23:
            this.j();
            return 1;
        label_25:
            if(arg5.arg1 != 0) {
                v3 = true;
            }

            this.b(v3);
            return 1;
        label_10:
            this.a(arg5.arg1, arg5.arg2);
            return 1;
        label_30:
            this.e();
            return 1;
        label_14:
            this.i();
            return 1;
        label_32:
            this.b(arg5.obj);
            return 1;
        label_16:
            this.b(ft.b(arg5.arg1, arg5.arg2));
            return 1;
        }
        catch(RuntimeException v5) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", ((Throwable)v5));
            arg5 = this.c.obtainMessage(v0, new az(((Throwable)v5), true));
        }
        catch(az v5_1) {
            Log.e("ExoPlayerImplInternal", "Internal track renderer error.", ((Throwable)v5_1));
            arg5 = this.c.obtainMessage(v0, v5_1);
        }

        arg5.sendToTarget();
        this.j();
        return 1;
    }

    private void i() {
        bc v6 = this;
        fs.a("doSomeWork");
        long v2 = SystemClock.elapsedRealtime();
        long v4 = -1;
        long v0 = v6.v != v4 ? v6.v : 9223372036854775807L;
        this.h();
        long v9 = v0;
        int v0_1 = 0;
        int v1 = 1;
        int v11 = 1;
        while(v0_1 < v6.f.size()) {
            Object v12 = v6.f.get(v0_1);
            ((bq)v12).b(v6.w, v6.u);
            v1 = v1 == 0 || !((bq)v12).e() ? 0 : 1;
            boolean v7 = v6.a(((bq)v12));
            if(!v7) {
                ((bq)v12).s();
            }

            v11 = v11 == 0 || !v7 ? 0 : 1;
            if(v9 == v4) {
            }
            else {
                long v7_1 = ((bq)v12).r();
                long v12_1 = ((bq)v12).q();
                if(v12_1 == v4) {
                    v9 = v4;
                }
                else if(v12_1 != -3) {
                    if(v7_1 != v4 && v7_1 != -2 && v12_1 >= v7_1) {
                        goto label_53;
                    }

                    v9 = Math.min(v9, v12_1);
                }
            }

        label_53:
            ++v0_1;
        }

        v6.x = v9;
        v0_1 = 4;
        int v7_2 = 3;
        if(v1 != 0) {
            if(v6.v != v4 && v6.v > v6.w) {
                goto label_68;
            }

            v6.a(5);
            goto label_66;
        }
        else {
        label_68:
            if(v6.q == v7_2 && v11 != 0) {
                v6.a(v0_1);
                if(v6.o) {
                    this.f();
                }
                else {
                }

                goto label_83;
            }

            if(v6.q != v0_1) {
                goto label_83;
            }

            if(v11 != 0) {
                goto label_83;
            }

            v6.p = v6.o;
            v6.a(v7_2);
        label_66:
            this.g();
        }

    label_83:
        v6.a.removeMessages(7);
        if((v6.o) && v6.q == v0_1 || v6.q == v7_2) {
            v1 = 7;
            v4 = 10;
            goto label_94;
        }
        else if(!v6.f.isEmpty()) {
            v1 = 7;
            v4 = 1000;
        label_94:
            this.a(v1, v2, v4);
        }

        fs.a();
    }

    private void j() {
        this.l();
        this.a(1);
    }

    private void k() {
        this.l();
        this.a(1);
        __monitor_enter(this);
        try {
            this.n = true;
            this.notifyAll();
            __monitor_exit(this);
            return;
        label_9:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_9;
        }

        throw v0;
    }

    private void l() {
        this.a.removeMessages(7);
        this.a.removeMessages(2);
        int v0 = 0;
        this.p = false;
        this.d.c();
        if(this.k == null) {
            return;
        }

        while(v0 < this.k.length) {
            bq v1 = this.k[v0];
            this.b(v1);
            this.c(v1);
            ++v0;
        }

        this.k = null;
        this.m = null;
        this.l = null;
        this.f.clear();
    }
}

