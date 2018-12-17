package org.telegram.customization.work;

import androidx.work.g;
import androidx.work.q;
import androidx.work.r;
import com.google.a.f;
import java.util.concurrent.TimeUnit;
import org.telegram.customization.Model.CUrl;
import org.telegram.messenger.ApplicationLoader;
import utils.a.b;

public class a {
    public static void a() {
        q.a().a("getProxyWork");
        androidx.work.l$a v0 = new androidx.work.l$a(ProxyWork.class);
        v0.a("getProxyWork");
        q.a().a(new r[]{v0.e()});
    }

    public static void a(CUrl arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(CurlWork.class, arg5.getPrd(), TimeUnit.MILLISECONDS);
        v0.a("getCurlWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_URL_DATA", new f().a(arg5));
        v0.a(v1.a());
        q.a().a("getCurlWork", g.a, v0.e());
    }

    public static void a(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(BgsWork.class, b.M(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getBgsWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getBgsWork", g.a, v0.e());
    }

    public static void b() {
        q.a().a("getSipWork");
        androidx.work.l$a v0 = new androidx.work.l$a(VoIPWhatsUpWork.class);
        v0.a("getSipWork");
        q.a().a(new r[]{v0.e()});
    }

    public static void b(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(BgtsWork.class, b.F(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getBgtsWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getBgtsWork", g.a, v0.e());
    }

    public static void c() {
        CUrl v0 = b.v();
        if(v0 != null && (v0.isEnable())) {
            q.a().a("getJobWork");
            androidx.work.l$a v0_1 = new androidx.work.l$a(JobUrlWork.class);
            v0_1.a("getJobWork");
            q.a().a(new r[]{v0_1.e()});
        }
    }

    public static void c(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(CtsWork.class, b.G(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getCtsWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getCtsWork", g.a, v0.e());
    }

    public static void d() {
        a.f(true);
        a.e(true);
        a.d(true);
        a.c(true);
        a.a(true);
        a.b(true);
    }

    public static void d(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(SgsWork.class, b.I(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getSgsWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getSgsWork", g.a, v0.e());
    }

    public static void e(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(LocationWork.class, b.H(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getLocWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getLocWork", g.a, v0.e());
    }

    public static void e() {
        a.f(false);
        a.e(false);
        a.d(false);
        a.c(false);
        a.a(false);
        a.b(false);
    }

    public static void f(boolean arg5) {
        androidx.work.m$a v0 = new androidx.work.m$a(UstateWork.class, b.K(ApplicationLoader.applicationContext), TimeUnit.MILLISECONDS);
        v0.a("getUStatWork");
        androidx.work.e$a v1 = new androidx.work.e$a();
        v1.a("EXTRA_IS_FORCE", arg5);
        v0.a(v1.a());
        q.a().a("getUStatWork", g.a, v0.e());
    }
}

