package android.support.v7.view;

import android.support.v4.view.x;
import android.support.v4.view.y;
import android.support.v4.view.z;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class h {
    class android.support.v7.view.h$1 extends z {
        private boolean b;
        private int c;

        android.support.v7.view.h$1(h arg1) {
            this.a = arg1;
            super();
            this.b = false;
            this.c = 0;
        }

        void a() {
            this.c = 0;
            this.b = false;
            this.a.b();
        }

        public void a(View arg2) {
            if(this.b) {
                return;
            }

            this.b = true;
            if(this.a.b != null) {
                this.a.b.a(null);
            }
        }

        public void b(View arg2) {
            int v2 = this.c + 1;
            this.c = v2;
            if(v2 == this.a.a.size()) {
                if(this.a.b != null) {
                    this.a.b.b(null);
                }

                this.a();
            }
        }
    }

    final ArrayList a;
    y b;
    private long c;
    private Interpolator d;
    private boolean e;
    private final z f;

    public h() {
        super();
        this.c = -1;
        this.f = new android.support.v7.view.h$1(this);
        this.a = new ArrayList();
    }

    public h a(long arg2) {
        if(!this.e) {
            this.c = arg2;
        }

        return this;
    }

    public h a(x arg2) {
        if(!this.e) {
            this.a.add(arg2);
        }

        return this;
    }

    public h a(x arg3, x arg4) {
        this.a.add(arg3);
        arg4.b(arg3.a());
        this.a.add(arg4);
        return this;
    }

    public h a(y arg2) {
        if(!this.e) {
            this.b = arg2;
        }

        return this;
    }

    public h a(Interpolator arg2) {
        if(!this.e) {
            this.d = arg2;
        }

        return this;
    }

    public void a() {
        if(this.e) {
            return;
        }

        Iterator v0 = this.a.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(this.c >= 0) {
                ((x)v1).a(this.c);
            }

            if(this.d != null) {
                ((x)v1).a(this.d);
            }

            if(this.b != null) {
                ((x)v1).a(this.f);
            }

            ((x)v1).c();
        }

        this.e = true;
    }

    void b() {
        this.e = false;
    }

    public void c() {
        if(!this.e) {
            return;
        }

        Iterator v0 = this.a.iterator();
        while(v0.hasNext()) {
            v0.next().b();
        }

        this.e = false;
    }
}

