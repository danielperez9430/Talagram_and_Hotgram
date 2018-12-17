package c.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import c.a.a.a.a.f.d;
import c.a.a.a.l;

class c {
    private final Context a;
    private final c.a.a.a.a.f.c b;

    public c(Context arg3) {
        super();
        this.a = arg3.getApplicationContext();
        this.b = new d(arg3, "TwitterAdvertisingInfoPreferences");
    }

    static b a(c arg0) {
        return arg0.e();
    }

    private void a(b arg3) {
        new Thread(new h(arg3) {
            public void a() {
                b v0 = c.a(this.b);
                if(!this.a.equals(v0)) {
                    c.a.a.a.c.h().a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    c.a(this.b, v0);
                }
            }
        }).start();
    }

    static void a(c arg0, b arg1) {
        arg0.b(arg1);
    }

    public b a() {
        b v0 = this.b();
        if(this.c(v0)) {
            c.a.a.a.c.h().a("Fabric", "Using AdvertisingInfo from Preference Store");
            this.a(v0);
            return v0;
        }

        v0 = this.e();
        this.b(v0);
        return v0;
    }

    @SuppressLint(value={"CommitPrefEdits"}) private void b(b arg5) {
        if(this.c(arg5)) {
            this.b.a(this.b.b().putString("advertising_id", arg5.a).putBoolean("limit_ad_tracking_enabled", arg5.b));
        }
        else {
            this.b.a(this.b.b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    protected b b() {
        return new b(this.b.a().getString("advertising_id", ""), this.b.a().getBoolean("limit_ad_tracking_enabled", false));
    }

    private boolean c(b arg1) {
        boolean v1 = arg1 == null || (TextUtils.isEmpty(arg1.a)) ? false : true;
        return v1;
    }

    public f c() {
        return new c.a.a.a.a.b.d(this.a);
    }

    public f d() {
        return new e(this.a);
    }

    private b e() {
        String v3;
        String v2;
        l v1;
        b v0 = this.c().a();
        if(!this.c(v0)) {
            v0 = this.d().a();
            if(!this.c(v0)) {
                v1 = c.a.a.a.c.h();
                v2 = "Fabric";
                v3 = "AdvertisingInfo not present";
            }
            else {
                v1 = c.a.a.a.c.h();
                v2 = "Fabric";
                v3 = "Using AdvertisingInfo from Service Provider";
            }
        }
        else {
            v1 = c.a.a.a.c.h();
            v2 = "Fabric";
            v3 = "Using AdvertisingInfo from Reflection Provider";
        }

        v1.a(v2, v3);
        return v0;
    }
}

