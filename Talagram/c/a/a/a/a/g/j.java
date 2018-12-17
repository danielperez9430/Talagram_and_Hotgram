package c.a.a.a.a.g;

import android.annotation.SuppressLint;
import android.content.SharedPreferences$Editor;
import c.a.a.a.a.b.k;
import c.a.a.a.a.f.c;
import c.a.a.a.a.f.d;
import c.a.a.a.i;
import c.a.a.a.l;
import org.json.JSONObject;

class j implements s {
    private final w a;
    private final v b;
    private final k c;
    private final g d;
    private final x e;
    private final i f;
    private final c g;

    public j(i arg1, w arg2, k arg3, v arg4, g arg5, x arg6) {
        super();
        this.f = arg1;
        this.a = arg2;
        this.c = arg3;
        this.b = arg4;
        this.d = arg5;
        this.e = arg6;
        this.g = new d(this.f);
    }

    private void a(JSONObject arg4, String arg5) {
        l v0 = c.a.a.a.c.h();
        v0.a("Fabric", arg5 + arg4.toString());
    }

    public t a() {
        return this.a(r.a);
    }

    public t a(r arg5) {
        t v1;
        JSONObject v5_1;
        t v0 = null;
        try {
            if(!c.a.a.a.c.i() && !this.d()) {
                v0 = this.b(arg5);
            }

            if(v0 == null) {
                v5_1 = this.e.a(this.a);
                if(v5_1 != null) {
                    v1 = this.b.a(this.c, v5_1);
                    goto label_15;
                }
            }

            goto label_27;
        }
        catch(Exception v5) {
            goto label_32;
        }

        try {
        label_15:
            this.d.a(v1.g, v5_1);
            this.a(v5_1, "Loaded settings: ");
            this.a(this.b());
            v0 = v1;
        }
        catch(Exception v5) {
            v0 = v1;
            goto label_32;
        }

    label_27:
        if(v0 == null) {
            try {
                t v5_2 = this.b(r.c);
                return v5_2;
            }
            catch(Exception v5) {
            }

        label_32:
            c.a.a.a.c.h().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", ((Throwable)v5));
        }

        return v0;
    }

    @SuppressLint(value={"CommitPrefEdits"}) boolean a(String arg3) {
        SharedPreferences$Editor v0 = this.g.b();
        v0.putString("existing_instance_identifier", arg3);
        return this.g.a(v0);
    }

    private t b(r arg6) {
        t v0_1;
        String v2_1;
        String v1_1;
        l v6_1;
        t v2;
        Throwable v0 = null;
        try {
            if(r.b.equals(arg6)) {
                return v0_1;
            }

            JSONObject v1 = this.d.a();
            if(v1 != null) {
                v2 = this.b.a(this.c, v1);
                if(v2 != null) {
                    this.a(v1, "Loaded cached settings: ");
                    long v3 = this.c.a();
                    if(!r.c.equals(arg6)) {
                        if(!v2.a(v3)) {
                        }
                        else {
                            v6_1 = c.a.a.a.c.h();
                            v1_1 = "Fabric";
                            v2_1 = "Cached settings have expired.";
                            goto label_24;
                        }
                    }

                    goto label_26;
                }
                else {
                    goto label_35;
                }
            }
            else {
                goto label_40;
            }

            goto label_24;
        }
        catch(Exception v6) {
            goto label_45;
        }

        try {
        label_26:
            c.a.a.a.c.h().a("Fabric", "Returning cached settings.");
            v0_1 = v2;
            return v0_1;
        }
        catch(Exception v6) {
            v0_1 = v2;
            goto label_45;
        }

        try {
        label_35:
            c.a.a.a.c.h().e("Fabric", "Failed to transform cached settings data.", v0);
            return v0_1;
        label_40:
            v6_1 = c.a.a.a.c.h();
            v1_1 = "Fabric";
            v2_1 = "No cached settings data found.";
        label_24:
            v6_1.a(v1_1, v2_1);
            return v0_1;
        }
        catch(Exception v6) {
        }

    label_45:
        c.a.a.a.c.h().e("Fabric", "Failed to get cached settings", ((Throwable)v6));
        return v0_1;
    }

    String b() {
        return c.a.a.a.a.b.i.a(new String[]{c.a.a.a.a.b.i.m(this.f.q())});
    }

    String c() {
        return this.g.a().getString("existing_instance_identifier", "");
    }

    boolean d() {
        return this.c().equals(this.b()) ^ 1;
    }
}

