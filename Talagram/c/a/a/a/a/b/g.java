package c.a.a.a.a.b;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import c.a.a.a.c;
import c.a.a.a.l;

public class g {
    public g() {
        super();
    }

    protected String a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }

    public String a(Context arg3) {
        String v0 = this.c(arg3);
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = this.d(arg3);
        }

        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v0 = this.b(arg3);
        }

        if(TextUtils.isEmpty(((CharSequence)v0))) {
            this.e(arg3);
        }

        return v0;
    }

    protected String b(Context arg2) {
        return new o().a(arg2);
    }

    protected String c(Context arg6) {
        String v1;
        Bundle v6_1;
        String v0 = null;
        try {
            v6_1 = arg6.getPackageManager().getApplicationInfo(arg6.getPackageName(), 128).metaData;
            if(v6_1 == null) {
                return v0;
            }

            v1 = v6_1.getString("io.fabric.ApiKey");
        }
        catch(Exception v6) {
            goto label_31;
        }

        try {
            if("@string/twitter_consumer_secret".equals(v1)) {
                c.h().a("Fabric", "Ignoring bad default value for Fabric ApiKey set by FirebaseUI-Auth");
            }
            else {
                goto label_17;
            }

            goto label_18;
        }
        catch(Exception v6) {
            v0 = v1;
            goto label_31;
        }

    label_17:
        v0 = v1;
    label_18:
        if(v0 == null) {
            try {
                c.h().a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                return v6_1.getString("com.crashlytics.ApiKey");
            }
            catch(Exception v6) {
            }

        label_31:
            l v1_1 = c.h();
            v1_1.a("Fabric", "Caught non-fatal exception while retrieving apiKey: " + v6);
        }

        return v0;
    }

    protected String d(Context arg4) {
        int v0 = i.a(arg4, "io.fabric.ApiKey", "string");
        if(v0 == 0) {
            c.h().a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            v0 = i.a(arg4, "com.crashlytics.ApiKey", "string");
        }

        String v4 = v0 != 0 ? arg4.getResources().getString(v0) : null;
        return v4;
    }

    protected void e(Context arg3) {
        if(!c.i() && !i.i(arg3)) {
            c.h().e("Fabric", this.a());
            return;
        }

        throw new IllegalArgumentException(this.a());
    }
}

