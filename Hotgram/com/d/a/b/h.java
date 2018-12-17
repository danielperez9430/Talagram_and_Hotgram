package com.d.a.b;

import android.graphics.Bitmap;
import android.os.Handler;
import com.d.a.b.a.d;
import com.d.a.b.f.b;
import com.d.a.c.b$a;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class h implements a, Runnable {
    class com.d.a.b.h$a extends Exception {
        com.d.a.b.h$a(h arg1) {
            this.a = arg1;
            super();
        }
    }

    final String a;
    final com.d.a.b.e.a b;
    final c c;
    final com.d.a.b.f.a d;
    final b e;
    private final f f;
    private final g g;
    private final Handler h;
    private final e i;
    private final com.d.a.b.d.b j;
    private final com.d.a.b.d.b k;
    private final com.d.a.b.d.b l;
    private final com.d.a.b.b.b m;
    private final String n;
    private final com.d.a.b.a.e o;
    private final boolean p;
    private com.d.a.b.a.f q;

    public h(f arg2, g arg3, Handler arg4) {
        super();
        this.q = com.d.a.b.a.f.a;
        this.f = arg2;
        this.g = arg3;
        this.h = arg4;
        this.i = arg2.a;
        this.j = this.i.p;
        this.k = this.i.s;
        this.l = this.i.t;
        this.m = this.i.q;
        this.a = arg3.a;
        this.n = arg3.b;
        this.b = arg3.c;
        this.o = arg3.d;
        this.c = arg3.e;
        this.d = arg3.f;
        this.e = arg3.g;
        this.p = this.c.s();
    }

    String a() {
        return this.a;
    }

    static void a(Runnable arg0, boolean arg1, Handler arg2, f arg3) {
        if(arg1) {
            arg0.run();
        }
        else if(arg2 == null) {
            arg3.a(arg0);
        }
        else {
            arg2.post(arg0);
        }
    }

    private Bitmap a(String arg10) {
        return this.m.a(new com.d.a.b.b.c(this.n, arg10, this.a, this.o, this.b.c(), this.h(), this.c));
    }

    static e a(h arg0) {
        return arg0.i;
    }

    private void a(com.d.a.b.a.b$a arg3, Throwable arg4) {
        if(!this.p && !this.p()) {
            if(this.j()) {
            }
            else {
                h.a(new Runnable(arg3, arg4) {
                    public void run() {
                        if(this.c.c.c()) {
                            this.c.b.a(this.c.c.c(h.a(this.c).a));
                        }

                        this.c.d.onLoadingFailed(this.c.a, this.c.b.d(), new com.d.a.b.a.b(this.a, this.b));
                    }
                }, false, this.h, this.f);
            }
        }
    }

    public boolean a(int arg2, int arg3) {
        boolean v2 = (this.p) || (this.c(arg2, arg3)) ? true : false;
        return v2;
    }

    private boolean b() {
        AtomicBoolean v0 = this.f.a();
        if(v0.get()) {
            Object v1 = this.f.b();
            __monitor_enter(v1);
            try {
                if(v0.get()) {
                    com.d.a.c.c.a("ImageLoader is paused. Waiting...  [%s]", new Object[]{this.n});
                    try {
                        this.f.b().wait();
                    }
                    catch(InterruptedException ) {
                        com.d.a.c.c.d("Task was interrupted [%s]", new Object[]{this.n});
                        __monitor_exit(v1);
                        return 1;
                    }

                    com.d.a.c.c.a(".. Resume loading [%s]", new Object[]{this.n});
                }

                __monitor_exit(v1);
                goto label_37;
            label_35:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_35;
            }

            throw v0_1;
        }

    label_37:
        return this.j();
    }

    private boolean b(int arg12, int arg13) {
        File v0 = this.i.o.a(this.a);
        boolean v1 = false;
        if(v0 != null && (v0.exists())) {
            Bitmap v12 = this.m.a(new com.d.a.b.b.c(this.n, com.d.a.b.d.b$a.c.b(v0.getAbsolutePath()), this.a, new com.d.a.b.a.e(arg12, arg13), com.d.a.b.a.h.a, this.h(), new com.d.a.b.c$a().a(this.c).a(d.d).a()));
            if(v12 != null && this.i.f != null) {
                com.d.a.c.c.a("Process image before cache on disk [%s]", new Object[]{this.n});
                v12 = this.i.f.a(v12);
                if(v12 == null) {
                    com.d.a.c.c.d("Bitmap processor for disk cache returned null [%s]", new Object[]{this.n});
                }
            }

            if(v12 == null) {
                return v1;
            }

            v1 = this.i.o.a(this.a, v12);
            v12.recycle();
        }

        return v1;
    }

    private boolean c() {
        if(this.c.f()) {
            com.d.a.c.c.a("Delay %d ms before loading...  [%s]", new Object[]{Integer.valueOf(this.c.l()), this.n});
            try {
                Thread.sleep(((long)this.c.l()));
            }
            catch(InterruptedException ) {
                com.d.a.c.c.d("Task was interrupted [%s]", new Object[]{this.n});
                return 1;
            }

            return this.j();
        }

        return 0;
    }

    private boolean c(int arg3, int arg4) {
        if(!this.p()) {
            if(this.j()) {
            }
            else {
                if(this.e != null) {
                    h.a(new Runnable(arg3, arg4) {
                        public void run() {
                            this.c.e.a(this.c.a, this.c.b.d(), this.a, this.b);
                        }
                    }, false, this.h, this.f);
                }

                return 1;
            }
        }

        return 0;
    }

    private Bitmap d() {
        Bitmap v2_1;
        Bitmap v1_4;
        Throwable v0 = null;
        try {
            File v1_3 = this.i.o.a(this.a);
            if(v1_3 == null) {
                goto label_26;
            }
            else if(!v1_3.exists()) {
                goto label_26;
            }
            else if(v1_3.length() > 0) {
                com.d.a.c.c.a("Load image from disk cache [%s]", new Object[]{this.n});
                this.q = com.d.a.b.a.f.b;
                this.i();
                v1_4 = this.a(com.d.a.b.d.b$a.c.b(v1_3.getAbsolutePath()));
            }
            else {
                goto label_26;
            }

            goto label_27;
        }
        catch(IllegalStateException ) {
            goto label_109;
        }
        catch(com.d.a.b.h$a v0_1) {
            goto label_108;
        }
        catch(IOException v1) {
            goto label_101;
        }
        catch(OutOfMemoryError v1_1) {
            goto label_94;
        }
        catch(Throwable v1_2) {
            goto label_87;
        }

    label_26:
        v1_4 = ((Bitmap)v0);
    label_27:
        if(v1_4 != null) {
            try {
                if(v1_4.getWidth() > 0) {
                    if(v1_4.getHeight() <= 0) {
                    }
                    else {
                        return v1_4;
                    }
                }

            label_39:
                com.d.a.c.c.a("Load image from network [%s]", new Object[]{this.n});
                this.q = com.d.a.b.a.f.a;
                String v2 = this.a;
                if((this.c.i()) && (this.e())) {
                    File v3 = this.i.o.a(this.a);
                    if(v3 != null) {
                        v2 = com.d.a.b.d.b$a.c.b(v3.getAbsolutePath());
                    }
                }

                this.i();
                v2_1 = this.a(v2);
                if(v2_1 != null) {
                    goto label_63;
                }
                else {
                    goto label_82;
                }
            }
            catch(IllegalStateException ) {
                goto label_110;
            }
            catch(com.d.a.b.h$a v0_1) {
                goto label_108;
            }
            catch(IOException v0_2) {
                goto label_38;
            }
            catch(OutOfMemoryError v0_3) {
                goto label_36;
            }
            catch(Throwable v0) {
                goto label_34;
            }
        }

        goto label_39;
        try {
        label_63:
            if(v2_1.getWidth() <= 0 || v2_1.getHeight() <= 0) {
            label_82:
                this.a(com.d.a.b.a.b$a.b, v0);
            }
        }
        catch(IllegalStateException ) {
            goto label_80;
        }
        catch(com.d.a.b.h$a v0_1) {
            goto label_108;
        }
        catch(IOException v1) {
            goto label_77;
        }
        catch(OutOfMemoryError v1_1) {
            goto label_73;
        }
        catch(Throwable v1_2) {
            goto label_69;
        }

        return v2_1;
    label_87:
        Throwable v9 = v1_2;
        v1_4 = ((Bitmap)v0);
        v0 = v9;
        goto label_90;
    label_94:
        OutOfMemoryError v9_1 = v1_1;
        v1_4 = ((Bitmap)v0);
        v0_3 = v9_1;
        goto label_97;
    label_101:
        IOException v9_2 = v1;
        v1_4 = ((Bitmap)v0);
        v0_2 = v9_2;
        goto label_104;
    label_109:
        v1_4 = ((Bitmap)v0);
        goto label_110;
    label_34:
        goto label_90;
    label_36:
        goto label_97;
    label_38:
        goto label_104;
    label_69:
        v0 = v1_2;
        v1_4 = v2_1;
        goto label_90;
    label_73:
        v0_3 = v1_1;
        v1_4 = v2_1;
        goto label_97;
    label_77:
        v0_2 = v1;
        v1_4 = v2_1;
        goto label_104;
    label_80:
        v1_4 = v2_1;
        goto label_110;
    label_90:
        com.d.a.c.c.a(v0);
        com.d.a.b.a.b$a v2_2 = com.d.a.b.a.b$a.e;
    label_111:
        this.a(v2_2, v0);
        return v1_4;
    label_97:
        com.d.a.c.c.a(((Throwable)v0_3));
        v2_2 = com.d.a.b.a.b$a.d;
        goto label_111;
    label_104:
        com.d.a.c.c.a(((Throwable)v0_2));
        v2_2 = com.d.a.b.a.b$a.a;
        goto label_111;
    label_108:
        throw v0_1;
    label_110:
        v2_2 = com.d.a.b.a.b$a.c;
        goto label_111;
        return v1_4;
    }

    private boolean e() {
        boolean v0_1;
        com.d.a.c.c.a("Cache image on disk [%s]", new Object[]{this.n});
        try {
            v0_1 = this.f();
            if(!v0_1) {
                return v0_1;
            }

            int v2 = this.i.d;
            int v3 = this.i.e;
            if(v2 <= 0 && v3 <= 0) {
                return v0_1;
            }

            com.d.a.c.c.a("Resize image in disk cache [%s]", new Object[]{this.n});
            this.b(v2, v3);
        }
        catch(IOException v0) {
            com.d.a.c.c.a(((Throwable)v0));
            v0_1 = false;
        }

        return v0_1;
    }

    private boolean f() {
        boolean v1_1;
        InputStream v0 = this.h().a(this.a, this.c.n());
        if(v0 == null) {
            com.d.a.c.c.d("No stream for image [%s]", new Object[]{this.n});
            return 0;
        }

        try {
            v1_1 = this.i.o.a(this.a, v0, ((a)this));
        }
        catch(Throwable v1) {
            com.d.a.c.b.a(((Closeable)v0));
            throw v1;
        }

        com.d.a.c.b.a(((Closeable)v0));
        return v1_1;
    }

    private void g() {
        if(!this.p) {
            if(this.p()) {
            }
            else {
                h.a(new Runnable() {
                    public void run() {
                        this.a.d.onLoadingCancelled(this.a.a, this.a.b.d());
                    }
                }, false, this.h, this.f);
            }
        }
    }

    private com.d.a.b.d.b h() {
        com.d.a.b.d.b v0;
        if(this.f.c()) {
            v0 = this.k;
        }
        else if(this.f.d()) {
            v0 = this.l;
        }
        else {
            v0 = this.j;
        }

        return v0;
    }

    private void i() {
        this.k();
        this.m();
    }

    private boolean j() {
        boolean v0 = (this.l()) || (this.n()) ? true : false;
        return v0;
    }

    private void k() {
        if(!this.l()) {
            return;
        }

        throw new com.d.a.b.h$a(this);
    }

    private boolean l() {
        if(this.b.e()) {
            com.d.a.c.c.a("ImageAware was collected by GC. Task is cancelled. [%s]", new Object[]{this.n});
            return 1;
        }

        return 0;
    }

    private void m() {
        if(!this.n()) {
            return;
        }

        throw new com.d.a.b.h$a(this);
    }

    private boolean n() {
        if((this.n.equals(this.f.a(this.b)) ^ 1) != 0) {
            com.d.a.c.c.a("ImageAware is reused for another image. Task is cancelled. [%s]", new Object[]{this.n});
            return 1;
        }

        return 0;
    }

    private void o() {
        if(!this.p()) {
            return;
        }

        throw new com.d.a.b.h$a(this);
    }

    private boolean p() {
        if(Thread.interrupted()) {
            com.d.a.c.c.a("Task was interrupted [%s]", new Object[]{this.n});
            return 1;
        }

        return 0;
    }

    public void run() {
        Bitmap v1_1;
        if(this.b()) {
            return;
        }

        if(this.c()) {
            return;
        }

        ReentrantLock v0 = this.g.h;
        com.d.a.c.c.a("Start display image task [%s]", new Object[]{this.n});
        if(v0.isLocked()) {
            com.d.a.c.c.a("Image already is loading. Waiting... [%s]", new Object[]{this.n});
        }

        v0.lock();
        try {
            this.i();
            v1_1 = this.i.n.a(this.n);
            if(v1_1 == null || (v1_1.isRecycled())) {
                v1_1 = this.d();
                if(v1_1 == null) {
                    goto label_42;
                }
                else {
                    goto label_44;
                }
            }
            else {
                this.q = com.d.a.b.a.f.c;
                com.d.a.c.c.a("...Get cached bitmap from memory after waiting. [%s]", new Object[]{this.n});
            }

            goto label_76;
        }
        catch(Throwable v1) {
            goto label_112;
        }
        catch(com.d.a.b.h$a ) {
            goto label_109;
        }

    label_42:
        v0.unlock();
        return;
        try {
        label_44:
            this.i();
            this.o();
            if(this.c.d()) {
                com.d.a.c.c.a("PreProcess image before caching in memory [%s]", new Object[]{this.n});
                v1_1 = this.c.o().a(v1_1);
                if(v1_1 == null) {
                    com.d.a.c.c.d("Pre-processor returned null [%s]", new Object[]{this.n});
                }
            }

            if(v1_1 != null && (this.c.h())) {
                com.d.a.c.c.a("Cache image in memory [%s]", new Object[]{this.n});
                this.i.n.a(this.n, v1_1);
            }

        label_76:
            if(v1_1 != null && (this.c.e())) {
                com.d.a.c.c.a("PostProcess image before displaying [%s]", new Object[]{this.n});
                v1_1 = this.c.p().a(v1_1);
                if(v1_1 == null) {
                    com.d.a.c.c.d("Post-processor returned null [%s]", new Object[]{this.n});
                }
            }

            this.i();
            this.o();
        }
        catch(Throwable v1) {
        label_112:
            v0.unlock();
            throw v1;
        }
        catch(com.d.a.b.h$a ) {
            try {
            label_109:
                this.g();
            }
            catch(Throwable v1) {
                goto label_112;
            }

            v0.unlock();
            return;
        }

        v0.unlock();
        h.a(new com.d.a.b.b(v1_1, this.g, this.f, this.q), this.p, this.h, this.f);
    }
}

