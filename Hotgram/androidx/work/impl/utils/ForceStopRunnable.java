package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import androidx.work.impl.g;
import androidx.work.j;
import java.util.concurrent.TimeUnit;

public class ForceStopRunnable implements Runnable {
    public class BroadcastReceiver extends android.content.BroadcastReceiver {
        public BroadcastReceiver() {
            super();
        }

        public void onReceive(Context arg3, Intent arg4) {
            if(arg4 != null && ("ACTION_FORCE_STOP_RESCHEDULE".equals(arg4.getAction()))) {
                j.a("ForceStopRunnable$Rcvr", "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
                ForceStopRunnable.b(arg3);
            }
        }
    }

    private static final long a;
    private final Context b;
    private final g c;

    static {
        ForceStopRunnable.a = TimeUnit.DAYS.toMillis(3650);
    }

    public ForceStopRunnable(Context arg1, g arg2) {
        super();
        this.b = arg1.getApplicationContext();
        this.c = arg2;
    }

    private static PendingIntent a(Context arg2, int arg3) {
        return PendingIntent.getBroadcast(arg2, -1, ForceStopRunnable.a(arg2), arg3);
    }

    static Intent a(Context arg3) {
        Intent v0 = new Intent();
        v0.setComponent(new ComponentName(arg3, BroadcastReceiver.class));
        v0.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return v0;
    }

    public boolean a() {
        if(ForceStopRunnable.a(this.b, 536870912) == null) {
            ForceStopRunnable.b(this.b);
            return 1;
        }

        return 0;
    }

    static void b(Context arg6) {
        Object v0 = arg6.getSystemService("alarm");
        PendingIntent v6 = ForceStopRunnable.a(arg6, 134217728);
        long v1 = System.currentTimeMillis() + ForceStopRunnable.a;
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 19) {
                ((AlarmManager)v0).setExact(0, v1, v6);
            }
            else {
                ((AlarmManager)v0).set(0, v1, v6);
            }
        }
    }

    boolean b() {
        return this.c.i().a();
    }

    public void run() {
        if(this.b()) {
            j.b("ForceStopRunnable", "Rescheduling Workers.", new Throwable[0]);
            this.c.j();
            this.c.i().a(false);
        }
        else if(this.a()) {
            j.b("ForceStopRunnable", "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.c.j();
        }

        this.c.k();
    }
}

