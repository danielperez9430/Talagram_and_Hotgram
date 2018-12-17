package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Handler;
import java.util.Iterator;

public class t implements m, a {
    private static t a;
    private float b;
    private final o c;
    private final l d;
    private n e;
    private p f;

    public t(o arg2, l arg3) {
        super();
        this.b = 0f;
        this.c = arg2;
        this.d = arg3;
    }

    public void a(Context arg4) {
        this.e = this.c.a(new Handler(), arg4, this.d.a(), ((m)this));
    }

    public static t a() {
        if(t.a == null) {
            t.a = new t(new o(), new l());
        }

        return t.a;
    }

    public void a(float arg3) {
        this.b = arg3;
        Iterator v0 = this.e().c().iterator();
        while(v0.hasNext()) {
            v0.next().e().a(arg3);
        }
    }

    public void a(boolean arg1) {
        if(arg1) {
            aj.a().b();
        }
        else {
            aj.a().d();
        }
    }

    public void b() {
        q.a().a(((a)this));
        q.a().b();
        if(q.a().d()) {
            aj.a().b();
        }

        this.e.a();
    }

    public void c() {
        aj.a().c();
        q.a().c();
        this.e.b();
    }

    public float d() {
        return this.b;
    }

    private p e() {
        if(this.f == null) {
            this.f = p.a();
        }

        return this.f;
    }
}

