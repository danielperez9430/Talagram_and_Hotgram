package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Parcelable;
import android.support.v4.content.e;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import javax.annotation.concurrent.GuardedBy;

public final class FirebaseInstanceIdReceiver extends e {
    private static boolean a = false;
    @GuardedBy(value="FirebaseInstanceIdReceiver.class") private static aj b;
    @GuardedBy(value="FirebaseInstanceIdReceiver.class") private static aj c;

    static {
    }

    public FirebaseInstanceIdReceiver() {
        super();
    }

    public static int a(BroadcastReceiver arg4, Context arg5, String arg6, Intent arg7) {
        if(Log.isLoggable("FirebaseInstanceId", 3)) {
            String v0 = "FirebaseInstanceId";
            String v1 = "Starting service: ";
            String v2 = String.valueOf(arg6);
            v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
            Log.d(v0, v1);
        }

        int v0_1 = !PlatformVersion.isAtLeastO() || arg5.getApplicationInfo().targetSdkVersion < 26 ? 0 : 1;
        if(v0_1 != 0) {
            int v1_1 = -1;
            if(arg4.isOrderedBroadcast()) {
                arg4.setResultCode(v1_1);
            }

            FirebaseInstanceIdReceiver.a(arg5, arg6).a(arg7, arg4.goAsync());
            return v1_1;
        }

        return x.a().a(arg5, arg6, arg7);
    }

    private static aj a(Context arg2, String arg3) {
        aj v2_1;
        Class v0 = FirebaseInstanceIdReceiver.class;
        __monitor_enter(v0);
        try {
            if(!"com.google.firebase.MESSAGING_EVENT".equals(arg3)) {
                goto label_13;
            }

            if(FirebaseInstanceIdReceiver.c == null) {
                FirebaseInstanceIdReceiver.c = new aj(arg2, arg3);
            }

            v2_1 = FirebaseInstanceIdReceiver.c;
        }
        catch(Throwable v2) {
            goto label_22;
        }

        __monitor_exit(v0);
        return v2_1;
        try {
        label_13:
            if(FirebaseInstanceIdReceiver.b == null) {
                FirebaseInstanceIdReceiver.b = new aj(arg2, arg3);
            }

            v2_1 = FirebaseInstanceIdReceiver.b;
        }
        catch(Throwable v2) {
        label_22:
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    private final void a(Context arg5, Intent arg6, String arg7) {
        String v0_1;
        ComponentName v0 = null;
        arg6.setComponent(v0);
        arg6.setPackage(arg5.getPackageName());
        if(Build$VERSION.SDK_INT <= 18) {
            arg6.removeCategory(arg5.getPackageName());
        }

        String v1 = arg6.getStringExtra("gcm.rawData64");
        if(v1 != null) {
            arg6.putExtra("rawData", Base64.decode(v1, 0));
            arg6.removeExtra("gcm.rawData64");
        }

        if(("google.com/iid".equals(arg6.getStringExtra("from"))) || ("com.google.firebase.INSTANCE_ID_EVENT".equals(arg7))) {
            v0_1 = "com.google.firebase.INSTANCE_ID_EVENT";
        }
        else {
            if(!"com.google.android.c2dm.intent.RECEIVE".equals(arg7)) {
                if("com.google.firebase.MESSAGING_EVENT".equals(arg7)) {
                }
                else {
                    Log.d("FirebaseInstanceId", "Unexpected intent");
                    goto label_41;
                }
            }

            v0_1 = "com.google.firebase.MESSAGING_EVENT";
        }

    label_41:
        int v7 = -1;
        if(v0_1 != null) {
            v7 = FirebaseInstanceIdReceiver.a(((BroadcastReceiver)this), arg5, v0_1, arg6);
        }

        if(this.isOrderedBroadcast()) {
            this.setResultCode(v7);
        }
    }

    public final void onReceive(Context arg3, Intent arg4) {
        if(arg4 == null) {
            return;
        }

        Parcelable v0 = arg4.getParcelableExtra("wrapped_intent");
        if((v0 instanceof Intent)) {
        }
        else {
            v0 = null;
        }

        if(v0 != null) {
            this.a(arg3, ((Intent)v0), arg4.getAction());
            return;
        }

        this.a(arg3, arg4, arg4.getAction());
    }
}

