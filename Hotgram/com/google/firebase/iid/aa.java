package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager$WakeLock;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;

final class aa implements Runnable {
    private final long a;
    private final PowerManager$WakeLock b;
    private final FirebaseInstanceId c;
    private final p d;
    private final ac e;

    @VisibleForTesting aa(FirebaseInstanceId arg1, p arg2, ac arg3, long arg4) {
        super();
        this.c = arg1;
        this.d = arg2;
        this.e = arg3;
        this.a = arg4;
        this.b = this.a().getSystemService("power").newWakeLock(1, "fiid-sync");
        this.b.setReferenceCounted(false);
    }

    final Context a() {
        return this.c.b().a();
    }

    final boolean b() {
        Object v0 = this.a().getSystemService("connectivity");
        NetworkInfo v0_1 = v0 != null ? ((ConnectivityManager)v0).getActiveNetworkInfo() : null;
        if(v0_1 != null && (v0_1.isConnected())) {
            return 1;
        }

        return 0;
    }

    @VisibleForTesting private final boolean c() {
        try {
            if(!this.c.j()) {
                this.c.k();
            }
        }
        catch(IOException v0) {
            String v1 = "FirebaseInstanceId";
            String v2 = "Build channel failed: ";
            String v0_1 = String.valueOf(v0.getMessage());
            v0_1 = v0_1.length() != 0 ? v2.concat(v0_1) : new String(v2);
            Log.e(v1, v0_1);
            return 0;
        }

        return 1;
    }

    @VisibleForTesting private final boolean d() {
        String v3;
        z v0 = this.c.e();
        if(v0 != null && !v0.b(this.d.b())) {
            return 1;
        }

        try {
            v3 = this.c.f();
            if(v3 == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return 0;
            }

            if(Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }

            if(v0 == null || v0 != null && !v3.equals(v0.a)) {
                Context v0_2 = this.a();
                Intent v4 = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                v4.putExtra("token", v3);
                x.b(v0_2, v4);
                x.a(v0_2, new Intent("com.google.firebase.iid.TOKEN_REFRESH"));
            }
        }
        catch(SecurityException v0_1) {
            String v1 = "FirebaseInstanceId";
            v3 = "Token retrieval failed: ";
            String v0_3 = String.valueOf(((Exception)v0_1).getMessage());
            v0_3 = v0_3.length() != 0 ? v3.concat(v0_3) : new String(v3);
            Log.e(v1, v0_3);
            return 0;
        }

        return 1;
    }

    public final void run() {
        FirebaseInstanceId v0_1;
        this.b.acquire();
        try {
            this.c.a(true);
            if(!this.c.i()) {
                v0_1 = this.c;
                goto label_10;
            }
            else if(!this.b()) {
                new ab(this).a();
            }
            else {
                if((this.c()) && (this.d()) && (this.e.a(this.c))) {
                    v0_1 = this.c;
                label_10:
                    v0_1.a(false);
                    goto label_11;
                }

                this.c.a(this.a);
            }
        }
        catch(Throwable v0) {
            this.b.release();
            throw v0;
        }

    label_11:
        this.b.release();
    }
}

