package c.a.a.a.a.b;

import android.content.Context;
import c.a.a.a.c;

class d implements f {
    private final Context a;

    public d(Context arg1) {
        super();
        this.a = arg1.getApplicationContext();
    }

    public b a() {
        if(this.a(this.a)) {
            return new b(this.b(), this.c());
        }

        return null;
    }

    boolean a(Context arg7) {
        boolean v0 = false;
        try {
            if(Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{arg7}).intValue() == 0) {
                return true;
            }
        }
        catch(Exception ) {
        }

        return v0;
    }

    private String b() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("getId").invoke(this.d());
        }
        catch(Exception ) {
            c.h().d("Fabric", "Could not call getId on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return null;
        }
    }

    private boolean c() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info").getMethod("isLimitAdTrackingEnabled").invoke(this.d()).booleanValue();
        }
        catch(Exception ) {
            c.h().d("Fabric", "Could not call isLimitAdTrackingEnabled on com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            return 0;
        }
    }

    private Object d() {
        Object v0 = null;
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(v0, this.a);
        }
        catch(Exception ) {
            c.h().d("Fabric", "Could not call getAdvertisingIdInfo on com.google.android.gms.ads.identifier.AdvertisingIdClient");
            return v0;
        }
    }
}

