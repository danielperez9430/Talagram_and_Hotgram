package org.telegram.customization.j.c;

import com.google.a.f;
import com.google.a.g;
import f.m$a;
import java.util.concurrent.TimeUnit;
import okhttp3.j;
import okhttp3.n;
import org.telegram.customization.i.b.i;
import org.telegram.customization.i.c.h;
import org.telegram.customization.i.d;
import org.telegram.customization.i.m;
import org.telegram.customization.voip.HeaderInterceptor;
import utils.a.b;

public class k {
    public k() {
        super();
    }

    f a() {
        return new g().a().b();
    }

    public static n b() {
        n v0 = new n();
        v0.a(1);
        v0.b(1);
        return v0;
    }

    h c() {
        return new h(new a().a(b.B()).a(new okhttp3.x$a().b(30, TimeUnit.SECONDS).a(30, TimeUnit.SECONDS).a(new HeaderInterceptor()).a(new j(1, 30, TimeUnit.SECONDS)).a(k.b()).a()).a(f.a.a.a.a()).a().a(org.telegram.customization.i.c.a.class));
    }

    org.telegram.customization.i.j d() {
        return new org.telegram.customization.i.j(new a().a("http://dummyurl.com/").a(new okhttp3.x$a().b(30, TimeUnit.SECONDS).a(30, TimeUnit.SECONDS).a(new m()).a(new j(1, 30, TimeUnit.SECONDS)).a(k.b()).a()).a(org.telegram.customization.i.a.a.a(new g().a().b())).a().a(d.class));
    }

    org.telegram.customization.i.b.h e() {
        return new org.telegram.customization.i.b.h(new a().a(b.H()).a(new okhttp3.x$a().b(30, TimeUnit.SECONDS).a(30, TimeUnit.SECONDS).a(new i()).a(new j(1, 30, TimeUnit.SECONDS)).a(k.b()).a()).a(f.a.a.a.a()).a().a(org.telegram.customization.i.b.a.class));
    }
}

