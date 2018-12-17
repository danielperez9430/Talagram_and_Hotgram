package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Parcelable;
import android.support.v4.content.e;
import android.support.v4.f.m;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

public final class x {
    final Queue a;
    private static x b;
    @GuardedBy(value="serviceClassNames") private final m c;
    private Boolean d;
    private final Queue e;

    private x() {
        super();
        this.c = new m();
        this.d = null;
        this.a = new ArrayDeque();
        this.e = new ArrayDeque();
    }

    public static void a(Context arg1, Intent arg2) {
        arg1.sendBroadcast(x.b(arg1, "com.google.firebase.INSTANCE_ID_EVENT", arg2));
    }

    public static PendingIntent a(Context arg0, int arg1, Intent arg2, int arg3) {
        return PendingIntent.getBroadcast(arg0, arg1, x.b(arg0, "com.google.firebase.MESSAGING_EVENT", arg2), 1073741824);
    }

    public static x a() {
        x v1_1;
        Class v0 = x.class;
        __monitor_enter(v0);
        try {
            if(x.b == null) {
                x.b = new x();
            }

            v1_1 = x.b;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public final int a(Context arg3, String arg4, Intent arg5) {
        int v0 = arg4.hashCode();
        if(v0 != -842411455) {
            if(v0 != 41532704) {
                goto label_16;
            }
            else if(arg4.equals("com.google.firebase.MESSAGING_EVENT")) {
                v0 = 1;
            }
            else {
                goto label_16;
            }
        }
        else if(arg4.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
            v0 = 0;
        }
        else {
        label_16:
            v0 = -1;
        }

        switch(v0) {
            case 0: {
                goto label_27;
            }
            case 1: {
                goto label_25;
            }
        }

        String v3 = "FirebaseInstanceId";
        String v5 = "Unknown service action: ";
        arg4 = String.valueOf(arg4);
        arg4 = arg4.length() != 0 ? v5.concat(arg4) : new String(v5);
        Log.w(v3, arg4);
        return 500;
    label_25:
        Queue v0_1 = this.e;
        goto label_28;
    label_27:
        v0_1 = this.a;
    label_28:
        v0_1.offer(arg5);
        arg5 = new Intent(arg4);
        arg5.setPackage(arg3.getPackageName());
        return this.c(arg3, arg5);
    }

    public static void b(Context arg1, Intent arg2) {
        arg1.sendBroadcast(x.b(arg1, "com.google.firebase.MESSAGING_EVENT", arg2));
    }

    private static Intent b(Context arg2, String arg3, Intent arg4) {
        Intent v0 = new Intent(arg2, FirebaseInstanceIdReceiver.class);
        v0.setAction(arg3);
        v0.putExtra("wrapped_intent", ((Parcelable)arg4));
        return v0;
    }

    public final Intent b() {
        return this.e.poll();
    }

    private final int c(Context arg7, Intent arg8) {
        ComponentName v7_3;
        String v3;
        String v2;
        String v1_3;
        boolean v0_1;
        Object v1;
        m v0 = this.c;
        __monitor_enter(v0);
        try {
            v1 = this.c.get(arg8.getAction());
            __monitor_exit(v0);
            v0_1 = false;
            if(v1 != null) {
                goto label_69;
            }
        }
        catch(Throwable v7) {
            try {
            label_133:
                __monitor_exit(v0_1);
            }
            catch(Throwable v7) {
                goto label_133;
            }

            throw v7;
        }

        ResolveInfo v1_1 = arg7.getPackageManager().resolveService(arg8, 0);
        if(v1_1 != null) {
            if(v1_1.serviceInfo == null) {
            }
            else {
                ServiceInfo v1_2 = v1_1.serviceInfo;
                if(arg7.getPackageName().equals(v1_2.packageName)) {
                    if(v1_2.name == null) {
                    }
                    else {
                        v1_3 = v1_2.name;
                        if(v1_3.startsWith(".")) {
                            v2 = String.valueOf(arg7.getPackageName());
                            v1_3 = String.valueOf(v1_3);
                            v1_3 = v1_3.length() != 0 ? v2.concat(v1_3) : new String(v2);
                        }

                        m v2_1 = this.c;
                        __monitor_enter(v2_1);
                        try {
                            this.c.put(arg8.getAction(), v1_3);
                            __monitor_exit(v2_1);
                            goto label_69;
                        label_43:
                            __monitor_exit(v2_1);
                        }
                        catch(Throwable v7) {
                            goto label_43;
                        }

                        throw v7;
                    }
                }

                v3 = v1_2.packageName;
                v1_3 = v1_2.name;
                StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 94 + String.valueOf(v1_3).length());
                v5.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
                v5.append(v3);
                v5.append("/");
                v5.append(v1_3);
                Log.e("FirebaseInstanceId", v5.toString());
                goto label_86;
            }
        }

        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        goto label_86;
    label_69:
        if(Log.isLoggable("FirebaseInstanceId", 3)) {
            v2 = "FirebaseInstanceId";
            v3 = "Restricting intent to a specific service: ";
            String v4 = String.valueOf(v1);
            v3 = v4.length() != 0 ? v3.concat(v4) : new String(v3);
            Log.d(v2, v3);
        }

        arg8.setClassName(arg7.getPackageName(), ((String)v1));
        try {
        label_86:
            if(this.d == null) {
                if(arg7.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                    v0_1 = true;
                }

                this.d = Boolean.valueOf(v0_1);
            }

            if(this.d.booleanValue()) {
                v7_3 = e.startWakefulService(arg7, arg8);
            }
            else {
                v7_3 = arg7.startService(arg8);
                Log.d("FirebaseInstanceId", "Missing wake lock permission, service start may be delayed");
            }

            if(v7_3 != null) {
                return -1;
            }

            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        }
        catch(IllegalStateException v7_1) {
            String v7_4 = String.valueOf(v7_1);
            StringBuilder v1_4 = new StringBuilder(String.valueOf(v7_4).length() + 45);
            v1_4.append("Failed to start service while in background: ");
            v1_4.append(v7_4);
            Log.e("FirebaseInstanceId", v1_4.toString());
            return 402;
        }
        catch(SecurityException v7_2) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", ((Throwable)v7_2));
            return 401;
        }

        return -1;
    }
}

