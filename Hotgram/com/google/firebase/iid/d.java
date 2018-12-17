package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class d {
    @GuardedBy(value="MessengerIpcClient.class") private static d a;
    private final Context b;
    private final ScheduledExecutorService c;
    @GuardedBy(value="this") private f d;
    @GuardedBy(value="this") private int e;

    private d(Context arg3, ScheduledExecutorService arg4) {
        super();
        this.d = new f(this, null);
        this.e = 1;
        this.c = arg4;
        this.b = arg3.getApplicationContext();
    }

    private final int a() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.e;
            this.e = v0_1 + 1;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    static Context a(d arg0) {
        return arg0.b;
    }

    private final Task a(m arg5) {
        Task v5_1;
        __monitor_enter(this);
        try {
            if(Log.isLoggable("MessengerIpcClient", 3)) {
                String v1 = String.valueOf(arg5);
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 9);
                v3.append("Queueing ");
                v3.append(v1);
                Log.d("MessengerIpcClient", v3.toString());
            }

            if(!this.d.a(arg5)) {
                this.d = new f(this, null);
                this.d.a(arg5);
            }

            v5_1 = arg5.b.getTask();
        }
        catch(Throwable v5) {
            __monitor_exit(this);
            throw v5;
        }

        __monitor_exit(this);
        return v5_1;
    }

    public static d a(Context arg3) {
        d v3_1;
        Class v0 = d.class;
        __monitor_enter(v0);
        try {
            if(d.a == null) {
                d.a = new d(arg3, Executors.newSingleThreadScheduledExecutor());
            }

            v3_1 = d.a;
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
        return v3_1;
    }

    public final Task a(int arg3, Bundle arg4) {
        return this.a(new l(this.a(), 2, arg4));
    }

    static ScheduledExecutorService b(d arg0) {
        return arg0.c;
    }

    public final Task b(int arg3, Bundle arg4) {
        return this.a(new o(this.a(), 1, arg4));
    }
}

