package androidx.work.impl;

import android.content.Context;
import android.os.Build$VERSION;
import androidx.a.a.c;
import androidx.work.NonBlockingWorker;
import androidx.work.b;
import androidx.work.e;
import androidx.work.i;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.o;
import androidx.work.s$a;
import androidx.work.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class h implements Runnable {
    class androidx.work.impl.h$2 {
        static {
            androidx.work.impl.h$2.a = new int[a.values().length];
            try {
                androidx.work.impl.h$2.a[a.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    androidx.work.impl.h$2.a[a.c.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        androidx.work.impl.h$2.a[a.b.ordinal()] = 3;
                        return;
                    }
                    catch(NoSuchFieldError ) {
                        return;
                    }
                }
            }
        }
    }

    public class androidx.work.impl.h$a {
        Context a;
        NonBlockingWorker b;
        androidx.work.impl.utils.a.a c;
        b d;
        WorkDatabase e;
        String f;
        List g;
        androidx.work.u$a h;

        public androidx.work.impl.h$a(Context arg2, b arg3, androidx.work.impl.utils.a.a arg4, WorkDatabase arg5, String arg6) {
            super();
            this.h = new androidx.work.u$a();
            this.a = arg2.getApplicationContext();
            this.c = arg4;
            this.d = arg3;
            this.e = arg5;
            this.f = arg6;
        }

        public androidx.work.impl.h$a a(androidx.work.u$a arg1) {
            if(arg1 != null) {
                this.h = arg1;
            }

            return this;
        }

        public androidx.work.impl.h$a a(List arg1) {
            this.g = arg1;
            return this;
        }

        public h a() {
            return new h(this);
        }
    }

    NonBlockingWorker a;
    private Context b;
    private String c;
    private List d;
    private androidx.work.u$a e;
    private j f;
    private b g;
    private androidx.work.impl.utils.a.a h;
    private WorkDatabase i;
    private k j;
    private androidx.work.impl.b.b k;
    private n l;
    private List m;
    private String n;
    private c o;
    private volatile boolean p;

    h(androidx.work.impl.h$a arg2) {
        super();
        this.o = c.d();
        this.b = arg2.a;
        this.h = arg2.c;
        this.c = arg2.f;
        this.d = arg2.g;
        this.e = arg2.h;
        this.a = arg2.b;
        this.g = arg2.d;
        this.i = arg2.e;
        this.j = this.i.m();
        this.k = this.i.n();
        this.l = this.i.o();
    }

    private String a(List arg5) {
        StringBuilder v0 = new StringBuilder("Work [ id=");
        v0.append(this.c);
        v0.append(", tags={ ");
        Iterator v5 = arg5.iterator();
        int v1 = 1;
        while(v5.hasNext()) {
            Object v2 = v5.next();
            if(v1 != 0) {
                v1 = 0;
            }
            else {
                v0.append(", ");
            }

            v0.append(((String)v2));
        }

        v0.append(" } ]");
        return v0.toString();
    }

    private void a(String arg5) {
        Iterator v0 = this.k.b(arg5).iterator();
        while(v0.hasNext()) {
            this.a(v0.next());
        }

        if(this.j.f(arg5) != o.f) {
            this.j.a(o.d, new String[]{arg5});
        }
    }

    void a(a arg4) {
        this.j();
        boolean v1 = false;
        if(!this.d()) {
            try {
                this.i.f();
                o v0 = this.j.f(this.c);
                if(v0 == null) {
                    this.b(false);
                }
                else if(v0 == o.b) {
                    this.b(arg4);
                }
                else if(!v0.a()) {
                    this.g();
                }

                v1 = this.j.f(this.c).a();
                this.i.h();
            }
            catch(Throwable v4) {
                this.i.g();
                throw v4;
            }

            this.i.g();
        }

        if(v1) {
            Iterator v4_1 = this.d.iterator();
            while(v4_1.hasNext()) {
                v4_1.next().a(this.c);
            }
        }

        d.a(this.g, this.i, this.d);
    }

    public com.google.common.a.a.a a() {
        return this.o;
    }

    public void a(boolean arg2) {
        this.p = true;
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    private void b() {
        e v0_1;
        if(this.d()) {
            return;
        }

        this.i.f();
        try {
            this.f = this.j.b(this.c);
            if(this.f != null) {
                goto label_25;
            }

            androidx.work.j.e("WorkerWrapper", String.format("Didn\'t find WorkSpec for id %s", this.c), new Throwable[0]);
            this.b(false);
        }
        catch(Throwable v0) {
            goto label_147;
        }

        this.i.g();
        return;
        try {
        label_25:
            if(this.f.b == o.a) {
                goto label_35;
            }

            this.c();
            this.i.h();
        }
        catch(Throwable v0) {
            goto label_147;
        }

        this.i.g();
        return;
        try {
        label_35:
            this.i.h();
        }
        catch(Throwable v0) {
        label_147:
            this.i.g();
            throw v0;
        }

        this.i.g();
        if(this.f.a()) {
            v0_1 = this.f.e;
        }
        else {
            i v0_2 = i.a(this.f.d);
            if(v0_2 == null) {
                androidx.work.j.e("WorkerWrapper", String.format("Could not create Input Merger %s", this.f.d), new Throwable[0]);
                this.f();
                return;
            }
            else {
                ArrayList v3 = new ArrayList();
                ((List)v3).add(this.f.e);
                ((List)v3).addAll(this.j.g(this.c));
                v0_1 = v0_2.a(((List)v3));
            }
        }

        e v5 = v0_1;
        u v0_3 = new u(UUID.fromString(this.c), v5, this.m, this.e, this.f.k, this.g.a(), this.g.b());
        if(this.a == null) {
            this.a = this.g.b().a(this.b, this.f.c, v0_3);
        }

        if(this.a == null) {
            androidx.work.j.e("WorkerWrapper", String.format("Could for create Worker %s", this.f.c), new Throwable[0]);
            this.f();
            return;
        }

        if(this.a.h()) {
            androidx.work.j.e("WorkerWrapper", String.format("Received an already-used Worker %s; WorkerFactory should return new instances", this.f.c), new Throwable[0]);
            this.f();
            return;
        }

        this.a.i();
        if(this.e()) {
            if(this.d()) {
                return;
            }

            c v0_4 = c.d();
            try {
                v0_4.a(this.a.f());
            }
            catch(Throwable v1) {
                v0_4.a(v1);
            }

            v0_4.a(new Runnable(v0_4, this.n) {
                public void run() {
                    try {
                        this.c.a(this.a.get().a);
                    }
                    catch(ExecutionException v0) {
                        androidx.work.j.e("WorkerWrapper", String.format("%s failed because it threw an exception/error", this.b), new Throwable[]{v0});
                        this.c.a(a.b);
                    }
                }
            }, this.h.c());
        }
        else {
            this.c();
        }
    }

    private void b(boolean arg4) {
        try {
            this.i.f();
            List v0 = this.i.m().a();
            int v0_1 = v0 == null || (v0.isEmpty()) ? 1 : 0;
            if(v0_1 != 0) {
                androidx.work.impl.utils.d.a(this.b, RescheduleReceiver.class, false);
            }

            this.i.h();
        }
        catch(Throwable v4) {
            this.i.g();
            throw v4;
        }

        this.i.g();
        this.o.a(Boolean.valueOf(arg4));
    }

    private void b(a arg5) {
        switch(androidx.work.impl.h$2.a[arg5.ordinal()]) {
            case 1: {
                androidx.work.j.c("WorkerWrapper", String.format("Worker result SUCCESS for %s", this.n), new Throwable[0]);
                if(this.f.a()) {
                label_17:
                    this.h();
                    return;
                }

                this.i();
                break;
            }
            case 2: {
                androidx.work.j.c("WorkerWrapper", String.format("Worker result RETRY for %s", this.n), new Throwable[0]);
                this.g();
                break;
            }
            default: {
                androidx.work.j.c("WorkerWrapper", String.format("Worker result FAILURE for %s", this.n), new Throwable[0]);
                if(this.f.a()) {
                    goto label_17;
                }

                this.f();
                break;
            }
        }
    }

    private void c() {
        o v0 = this.j.f(this.c);
        if(v0 == o.b) {
            androidx.work.j.b("WorkerWrapper", String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", this.c), new Throwable[0]);
            this.b(true);
        }
        else {
            androidx.work.j.b("WorkerWrapper", String.format("Status for %s is %s; not doing any work", this.c, v0), new Throwable[0]);
            this.b(false);
        }
    }

    private boolean d() {
        if(this.p) {
            androidx.work.j.c("WorkerWrapper", String.format("Work interrupted for %s", this.n), new Throwable[0]);
            o v0 = this.j.f(this.c);
            if(v0 == null) {
                this.b(false);
            }
            else {
                this.b(v0.a() ^ 1);
            }

            return 1;
        }

        return 0;
    }

    private boolean e() {
        boolean v2;
        this.i.f();
        try {
            v2 = true;
            if(this.j.f(this.c) == o.a) {
                this.j.a(o.b, new String[]{this.c});
                this.j.d(this.c);
            }
            else {
                v2 = false;
            }

            this.i.h();
        }
        catch(Throwable v0) {
            this.i.g();
            throw v0;
        }

        this.i.g();
        return v2;
    }

    private void f() {
        this.i.f();
        try {
            this.a(this.c);
            if(this.a != null) {
                this.j.a(this.c, this.a.g());
            }

            this.i.h();
        }
        catch(Throwable v1) {
            this.i.g();
            this.b(false);
            throw v1;
        }

        this.i.g();
        this.b(false);
    }

    private void g() {
        this.i.f();
        try {
            this.j.a(o.a, new String[]{this.c});
            this.j.a(this.c, System.currentTimeMillis());
            if(Build$VERSION.SDK_INT < 23) {
                this.j.b(this.c, -1);
            }

            this.i.h();
        }
        catch(Throwable v1) {
            this.i.g();
            this.b(true);
            throw v1;
        }

        this.i.g();
        this.b(true);
    }

    private void h() {
        this.i.f();
        try {
            this.j.a(this.c, this.f.n + this.f.h);
            this.j.a(o.a, new String[]{this.c});
            this.j.e(this.c);
            if(Build$VERSION.SDK_INT < 23) {
                this.j.b(this.c, -1);
            }

            this.i.h();
        }
        catch(Throwable v1) {
            this.i.g();
            this.b(false);
            throw v1;
        }

        this.i.g();
        this.b(false);
    }

    private void i() {
        this.i.f();
        try {
            this.j.a(o.c, new String[]{this.c});
            this.j.a(this.c, this.a.g());
            long v1_1 = System.currentTimeMillis();
            Iterator v4 = this.k.b(this.c).iterator();
            while(v4.hasNext()) {
                Object v5 = v4.next();
                if(!this.k.a(((String)v5))) {
                    continue;
                }

                androidx.work.j.c("WorkerWrapper", String.format("Setting status to enqueued for %s", v5), new Throwable[0]);
                this.j.a(o.a, new String[]{v5});
                this.j.a(((String)v5), v1_1);
            }

            this.i.h();
        }
        catch(Throwable v1) {
            goto label_49;
        }

        this.i.g();
        this.b(false);
        return;
    label_49:
        this.i.g();
        this.b(false);
        throw v1;
    }

    private void j() {
        if(this.h.b() == Thread.currentThread()) {
            return;
        }

        throw new IllegalStateException("Needs to be executed on the Background executor thread.");
    }

    public void run() {
        this.m = this.l.a(this.c);
        this.n = this.a(this.m);
        this.b();
    }
}

