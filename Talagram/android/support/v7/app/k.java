package android.support.v7.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.d;
import android.util.Log;
import java.util.Calendar;

class k {
    class a {
        boolean a;
        long b;
        long c;
        long d;
        long e;
        long f;

        a() {
            super();
        }
    }

    private static k a;
    private final Context b;
    private final LocationManager c;
    private final a d;

    k(Context arg2, LocationManager arg3) {
        super();
        this.d = new a();
        this.b = arg2;
        this.c = arg3;
    }

    private Location a(String arg3) {
        try {
            if(!this.c.isProviderEnabled(arg3)) {
                return null;
            }

            return this.c.getLastKnownLocation(arg3);
        }
        catch(Exception v3) {
            Log.d("TwilightManager", "Failed to get last known location", ((Throwable)v3));
        }

        return null;
    }

    static k a(Context arg2) {
        if(k.a == null) {
            arg2 = arg2.getApplicationContext();
            k.a = new k(arg2, arg2.getSystemService("location"));
        }

        return k.a;
    }

    private void a(Location arg23) {
        a v1 = this.d;
        long v9 = System.currentTimeMillis();
        j v11 = j.a();
        long v12 = 86400000;
        v11.a(v9 - v12, arg23.getLatitude(), arg23.getLongitude());
        long v14 = v11.a;
        v11.a(v9, arg23.getLatitude(), arg23.getLongitude());
        boolean v7 = v11.c == 1 ? true : false;
        long v5 = v11.b;
        long v20 = v14;
        v14 = v11.a;
        long v3 = v12 + v9;
        v12 = v5;
        boolean v0 = v7;
        v11.a(v3, arg23.getLatitude(), arg23.getLongitude());
        long v2 = v11.b;
        long v4 = 0;
        long v6 = -1;
        if(v12 == v6 || v14 == v6) {
            v4 = 43200000 + v9;
        }
        else {
            if(v9 > v14) {
                v4 += v2;
            }
            else if(v9 > v12) {
                v4 += v14;
            }
            else {
                v4 += v12;
            }

            v4 += 60000;
        }

        v1.a = v0;
        v1.b = v20;
        v1.c = v12;
        v1.d = v14;
        v1.e = v2;
        v1.f = v4;
    }

    boolean a() {
        a v0 = this.d;
        if(this.c()) {
            return v0.a;
        }

        Location v1 = this.b();
        if(v1 != null) {
            this.a(v1);
            return v0.a;
        }

        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int v0_1 = Calendar.getInstance().get(11);
        boolean v0_2 = v0_1 < 6 || v0_1 >= 22 ? true : false;
        return v0_2;
    }

    @SuppressLint(value={"MissingPermission"}) private Location b() {
        Location v1 = null;
        Location v0 = d.a(this.b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? this.a("network") : v1;
        if(d.a(this.b, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            v1 = this.a("gps");
        }

        if(v1 != null && v0 != null) {
            if(v1.getTime() > v0.getTime()) {
                v0 = v1;
            }

            return v0;
        }

        if(v1 != null) {
            v0 = v1;
        }

        return v0;
    }

    private boolean c() {
        boolean v0 = this.d.f > System.currentTimeMillis() ? true : false;
        return v0;
    }
}

