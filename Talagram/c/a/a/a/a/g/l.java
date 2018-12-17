package c.a.a.a.a.g;

import android.text.TextUtils;
import c.a.a.a.a.b.a;
import c.a.a.a.a.e.c;
import c.a.a.a.a.e.d;
import c.a.a.a.a.e.e;
import c.a.a.a.i;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class l extends a implements x {
    public l(i arg7, String arg8, String arg9, e arg10) {
        this(arg7, arg8, arg9, arg10, c.a);
    }

    l(i arg1, String arg2, String arg3, e arg4, c arg5) {
        super(arg1, arg2, arg3, arg4, arg5);
    }

    private d a(d arg3, w arg4) {
        String v4;
        String v0;
        this.a(arg3, "X-CRASHLYTICS-API-KEY", arg4.a);
        this.a(arg3, "X-CRASHLYTICS-API-CLIENT-TYPE", "android");
        this.a(arg3, "X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a());
        this.a(arg3, "Accept", "application/json");
        this.a(arg3, "X-CRASHLYTICS-DEVICE-MODEL", arg4.b);
        this.a(arg3, "X-CRASHLYTICS-OS-BUILD-VERSION", arg4.c);
        this.a(arg3, "X-CRASHLYTICS-OS-DISPLAY-VERSION", arg4.d);
        this.a(arg3, "X-CRASHLYTICS-INSTALLATION-ID", arg4.f);
        if(TextUtils.isEmpty(arg4.e)) {
            v0 = "X-CRASHLYTICS-ANDROID-ID";
            v4 = arg4.g;
        }
        else {
            v0 = "X-CRASHLYTICS-ADVERTISING-TOKEN";
            v4 = arg4.e;
        }

        this.a(arg3, v0, v4);
        return arg3;
    }

    private void a(d arg1, String arg2, String arg3) {
        if(arg3 != null) {
            arg1.a(arg2, arg3);
        }
    }

    private JSONObject a(String arg6) {
        try {
            return new JSONObject(arg6);
        }
        catch(Exception v0) {
            c.a.a.a.l v1 = c.a.a.a.c.h();
            v1.a("Fabric", "Failed to parse settings JSON from " + this.a(), ((Throwable)v0));
            c.a.a.a.l v0_1 = c.a.a.a.c.h();
            v0_1.a("Fabric", "Settings response " + arg6);
            return null;
        }
    }

    JSONObject a(d arg6) {
        JSONObject v6;
        int v0 = arg6.b();
        c.a.a.a.l v1 = c.a.a.a.c.h();
        v1.a("Fabric", "Settings result was: " + v0);
        if(this.a(v0)) {
            v6 = this.a(arg6.e());
        }
        else {
            c.a.a.a.l v6_1 = c.a.a.a.c.h();
            v6_1.e("Fabric", "Failed to retrieve settings from " + this.a());
            v6 = null;
        }

        return v6;
    }

    boolean a(int arg2) {
        boolean v2 = arg2 == 200 || arg2 == 201 || arg2 == 202 || arg2 == 203 ? true : false;
        return v2;
    }

    public JSONObject a(w arg8) {
        c.a.a.a.l v1_3;
        JSONObject v1_2;
        d v8_1;
        Throwable v0_1;
        d v2;
        Map v1_1;
        JSONObject v0 = null;
        try {
            v1_1 = this.b(arg8);
            v2 = this.a(v1_1);
        }
        catch(Throwable v8) {
            JSONObject v6 = v0;
            v0_1 = v8;
            v8_1 = ((d)v6);
            goto label_71;
        }
        catch(c.a.a.a.a.e.d$c v1) {
            v8_1 = ((d)v0);
            goto label_53;
        }

        try {
            v8_1 = this.a(v2, arg8);
        }
        catch(Throwable v0_1) {
            v8_1 = v2;
            goto label_71;
        }
        catch(c.a.a.a.a.e.d$c v1) {
            v8_1 = v2;
            goto label_53;
        }

        try {
            c.a.a.a.l v2_1 = c.a.a.a.c.h();
            v2_1.a("Fabric", "Requesting settings from " + this.a());
            v2_1 = c.a.a.a.c.h();
            v2_1.a("Fabric", "Settings query params were: " + v1_1);
            v1_2 = this.a(v8_1);
            if(v8_1 == null) {
                return v1_2;
            }

            goto label_25;
        }
        catch(Throwable v0_1) {
        }
        catch(c.a.a.a.a.e.d$c v1) {
            try {
            label_53:
                c.a.a.a.c.h().e("Fabric", "Settings request failed.", ((Throwable)v1));
                if(v8_1 != null) {
                }
                else {
                    return v0;
                }
            }
            catch(Throwable v0_1) {
                goto label_71;
            }

            v1_3 = c.a.a.a.c.h();
            v1_3.a("Fabric", "Settings request ID: " + v8_1.b("X-REQUEST-ID"));
            return v0;
        }

    label_71:
        if(v8_1 != null) {
            v1_3 = c.a.a.a.c.h();
            v1_3.a("Fabric", "Settings request ID: " + v8_1.b("X-REQUEST-ID"));
        }

        throw v0_1;
    label_25:
        c.a.a.a.l v0_2 = c.a.a.a.c.h();
        v0_2.a("Fabric", "Settings request ID: " + v8_1.b("X-REQUEST-ID"));
        return v1_2;
    }

    private Map b(w arg4) {
        HashMap v0 = new HashMap();
        ((Map)v0).put("build_version", arg4.j);
        ((Map)v0).put("display_version", arg4.i);
        ((Map)v0).put("source", Integer.toString(arg4.k));
        if(arg4.l != null) {
            ((Map)v0).put("icon_hash", arg4.l);
        }

        String v4 = arg4.h;
        if(!c.a.a.a.a.b.i.d(v4)) {
            ((Map)v0).put("instance", v4);
        }

        return ((Map)v0);
    }
}

