package c.a.a.a.a.b;

import android.content.Context;
import android.text.TextUtils;
import c.a.a.a.c;

public class o {
    public o() {
        super();
    }

    protected String a(Context arg5) {
        int v0 = i.a(arg5, "google_app_id", "string");
        if(v0 != 0) {
            c.h().a("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
            return this.a(arg5.getResources().getString(v0));
        }

        return null;
    }

    protected String a(String arg3) {
        return i.b(arg3).substring(0, 40);
    }

    public boolean b(Context arg5) {
        boolean v1 = false;
        if(i.a(arg5, "com.crashlytics.useFirebaseAppId", false)) {
            return 1;
        }

        int v0 = i.a(arg5, "google_app_id", "string") != 0 ? 1 : 0;
        int v5 = !TextUtils.isEmpty(new g().c(arg5)) || !TextUtils.isEmpty(new g().d(arg5)) ? 1 : 0;
        if(v0 != 0 && v5 == 0) {
            v1 = true;
        }

        return v1;
    }
}

