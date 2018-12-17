package com.crashlytics.android.b;

import c.a.a.a.a.b.a;
import c.a.a.a.a.e.c;
import c.a.a.a.i;
import c.a.a.a.l;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class e extends a {
    private final g b;

    public e(i arg7, String arg8, String arg9, c.a.a.a.a.e.e arg10, g arg11) {
        super(arg7, arg8, arg9, arg10, c.a);
        this.b = arg11;
    }

    public f a(String arg5, String arg6, d arg7) {
        String v7_2;
        f v6_3;
        l v6_2;
        c.a.a.a.a.e.d v5;
        c.a.a.a.a.e.d v1;
        Map v7;
        f v0 = null;
        try {
            v7 = this.a(arg7);
            v1 = this.a(v7);
        }
        catch(Throwable v6) {
            v5 = ((c.a.a.a.a.e.d)v0);
            goto label_102;
        }
        catch(Exception v6_1) {
            v5 = ((c.a.a.a.a.e.d)v0);
            goto label_78;
        }

        try {
            v5 = this.a(v1, arg5, arg6);
        }
        catch(Throwable v6) {
            v5 = v1;
            goto label_102;
        }
        catch(Exception v6_1) {
            v5 = v1;
            goto label_78;
        }

        try {
            v6_2 = c.a.a.a.c.h();
            v6_2.a("Beta", "Checking for updates from " + this.a());
            v6_2 = c.a.a.a.c.h();
            v6_2.a("Beta", "Checking for updates query params are: " + v7);
            if(!v5.c()) {
                goto label_47;
            }

            c.a.a.a.c.h().a("Beta", "Checking for updates was successful");
            v6_3 = this.b.a(new JSONObject(v5.e()));
            if(v5 != null) {
                goto label_35;
            }

            return v6_3;
        }
        catch(Exception v6_1) {
            goto label_66;
        }
        catch(Throwable v6) {
            goto label_102;
        }

    label_35:
        arg5 = v5.b("X-REQUEST-ID");
        l v7_1 = c.a.a.a.c.h();
        v7_1.a("Fabric", "Checking for updates request ID: " + arg5);
        return v6_3;
        try {
        label_47:
            v6_2 = c.a.a.a.c.h();
            v6_2.e("Beta", "Checking for updates failed. Response code: " + v5.b());
            if(v5 == null) {
                return v0;
            }

            goto label_58;
        }
        catch(Throwable v6) {
        }
        catch(Exception v6_1) {
        label_66:
            try {
            label_78:
                v7_1 = c.a.a.a.c.h();
                v7_1.e("Beta", "Error while checking for updates from " + this.a(), ((Throwable)v6_1));
                if(v5 == null) {
                    return v0;
                }
            }
            catch(Throwable v6) {
                goto label_102;
            }

            arg5 = v5.b("X-REQUEST-ID");
            v6_2 = c.a.a.a.c.h();
            v7_2 = "Fabric";
            v1_1 = new StringBuilder();
            goto label_95;
        }

    label_102:
        if(v5 != null) {
            arg5 = v5.b("X-REQUEST-ID");
            v7_1 = c.a.a.a.c.h();
            v7_1.a("Fabric", "Checking for updates request ID: " + arg5);
        }

        throw v6;
    label_58:
        arg5 = v5.b("X-REQUEST-ID");
        v6_2 = c.a.a.a.c.h();
        v7_2 = "Fabric";
        v1_1 = new StringBuilder();
    label_95:
        v1_1.append("Checking for updates request ID: ");
        v1_1.append(arg5);
        v6_2.a(v7_2, v1_1.toString());
        return v0;
    }

    private c.a.a.a.a.e.d a(c.a.a.a.a.e.d arg4, String arg5, String arg6) {
        arg4 = arg4.a("Accept", "application/json");
        StringBuilder v1 = new StringBuilder();
        v1.append("Crashlytics Android SDK/");
        v1.append(this.a.a());
        return arg4.a("User-Agent", v1.toString()).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa").a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a()).a("X-CRASHLYTICS-API-KEY", arg5).a("X-CRASHLYTICS-BETA-TOKEN", e.a(arg6));
    }

    static String a(String arg2) {
        return "3:" + arg2;
    }

    private Map a(d arg4) {
        HashMap v0 = new HashMap();
        ((Map)v0).put("build_version", arg4.a);
        ((Map)v0).put("display_version", arg4.b);
        ((Map)v0).put("instance", arg4.c);
        ((Map)v0).put("source", "3");
        return ((Map)v0);
    }
}

