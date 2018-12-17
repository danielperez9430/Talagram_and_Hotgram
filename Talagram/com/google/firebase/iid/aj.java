package com.google.firebase.iid;

import android.content.BroadcastReceiver$PendingResult;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class aj implements ServiceConnection {
    private final Context a;
    private final Intent b;
    private final ScheduledExecutorService c;
    private final Queue d;
    private ah e;
    private boolean f;

    public aj(Context arg3, String arg4) {
        this(arg3, arg4, new ScheduledThreadPoolExecutor(0));
    }

    private aj(Context arg2, String arg3, ScheduledExecutorService arg4) {
        super();
        this.d = new ArrayDeque();
        this.f = false;
        this.a = arg2.getApplicationContext();
        this.b = new Intent(arg3).setPackage(this.a.getPackageName());
        this.c = arg4;
    }

    private final void a() {
        __monitor_enter(this);
        try {
            int v1 = 3;
            if(Log.isLoggable("EnhancedIntentService", v1)) {
                Log.d("EnhancedIntentService", "flush queue called");
            }

            while(true) {
                if(this.d.isEmpty()) {
                    goto label_76;
                }

                if(Log.isLoggable("EnhancedIntentService", v1)) {
                    Log.d("EnhancedIntentService", "found intent to be delivered");
                }

                if(this.e != null && (this.e.isBinderAlive())) {
                    if(Log.isLoggable("EnhancedIntentService", v1)) {
                        Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                    }

                    this.e.a(this.d.poll());
                    continue;
                }

                break;
            }

            if(Log.isLoggable("EnhancedIntentService", v1)) {
                int v2 = this.f ^ 1;
                StringBuilder v4 = new StringBuilder(39);
                v4.append("binder is dead. start connection? ");
                v4.append(((boolean)v2));
                Log.d("EnhancedIntentService", v4.toString());
            }

            if(this.f) {
                goto label_74;
            }

            this.f = true;
            try {
                if(!ConnectionTracker.getInstance().bindService(this.a, this.b, ((ServiceConnection)this), 65)) {
                    goto label_59;
                }
            }
            catch(SecurityException v0_1) {
                goto label_64;
            }
        }
        catch(Throwable v0) {
            goto label_79;
        }

        __monitor_exit(this);
        return;
        try {
        label_59:
            Log.e("EnhancedIntentService", "binding to the service failed");
            goto label_67;
        }
        catch(Throwable v0) {
        label_79:
            __monitor_exit(this);
            throw v0;
        }
        catch(SecurityException v0_1) {
            try {
            label_64:
                Log.e("EnhancedIntentService", "Exception while binding the service", ((Throwable)v0_1));
                goto label_67;
            }
            catch(Throwable v0) {
                goto label_79;
            }

        label_76:
            __monitor_exit(this);
            return;
            try {
            label_67:
                while(!this.d.isEmpty()) {
                    this.d.poll().a();
                }
            }
            catch(Throwable v0) {
                goto label_79;
            }

        label_74:
            __monitor_exit(this);
            return;
        }
    }

    public final void a(Intent arg4, BroadcastReceiver$PendingResult arg5) {
        __monitor_enter(this);
        try {
            if(Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }

            this.d.add(new af(arg4, arg5, this.c));
            this.a();
        }
        catch(Throwable v4) {
            __monitor_exit(this);
            throw v4;
        }

        __monitor_exit(this);
    }

    public final void onServiceConnected(ComponentName arg3, IBinder arg4) {
        __monitor_enter(this);
        try {
            this.f = false;
            this.e = ((ah)arg4);
            if(Log.isLoggable("EnhancedIntentService", 3)) {
                String v3_1 = String.valueOf(arg3);
                StringBuilder v1 = new StringBuilder(String.valueOf(v3_1).length() + 20);
                v1.append("onServiceConnected: ");
                v1.append(v3_1);
                Log.d("EnhancedIntentService", v1.toString());
            }

            this.a();
            __monitor_exit(this);
            return;
        label_24:
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            goto label_24;
        }

        throw v3;
    }

    public final void onServiceDisconnected(ComponentName arg4) {
        if(Log.isLoggable("EnhancedIntentService", 3)) {
            String v4 = String.valueOf(arg4);
            StringBuilder v2 = new StringBuilder(String.valueOf(v4).length() + 23);
            v2.append("onServiceDisconnected: ");
            v2.append(v4);
            Log.d("EnhancedIntentService", v2.toString());
        }

        this.a();
    }
}

