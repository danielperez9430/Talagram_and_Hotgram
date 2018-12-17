package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.a;
import androidx.work.j;
import androidx.work.o;
import java.util.HashMap;
import java.util.Map;

public class b implements a {
    private final Context a;
    private final Map b;
    private final Object c;

    b(Context arg1) {
        super();
        this.a = arg1;
        this.b = new HashMap();
        this.c = new Object();
    }

    static Intent a(Context arg2) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_CONSTRAINTS_CHANGED");
        return v0;
    }

    static Intent a(Context arg2, String arg3) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_SCHEDULE_WORK");
        v0.putExtra("KEY_WORKSPEC_ID", arg3);
        return v0;
    }

    static Intent a(Context arg2, String arg3, boolean arg4) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_EXECUTION_COMPLETED");
        v0.putExtra("KEY_WORKSPEC_ID", arg3);
        v0.putExtra("KEY_NEEDS_RESCHEDULE", arg4);
        return v0;
    }

    private static boolean a(Bundle arg5, String[] arg6) {
        if(arg5 != null) {
            if(arg5.isEmpty()) {
            }
            else {
                int v1 = arg6.length;
                int v2 = 0;
                while(true) {
                    if(v2 < v1) {
                        String v3 = arg6[v2];
                        if(arg5.containsKey(v3)) {
                            if(arg5.get(v3) == null) {
                            }
                            else {
                                ++v2;
                                continue;
                            }
                        }

                        return 0;
                    }
                    else {
                        return 1;
                    }
                }

                return 0;
            }
        }

        return 0;
    }

    public void a(String arg3, boolean arg4) {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            Object v1 = this.b.remove(arg3);
            if(v1 != null) {
                ((a)v1).a(arg3, arg4);
            }

            __monitor_exit(v0);
            return;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }

    void a(Intent arg7, int arg8, e arg9) {
        String v0 = arg7.getAction();
        if("ACTION_CONSTRAINTS_CHANGED".equals(v0)) {
            this.e(arg7, arg8, arg9);
        }
        else if("ACTION_RESCHEDULE".equals(v0)) {
            this.f(arg7, arg8, arg9);
        }
        else if(!b.a(arg7.getExtras(), new String[]{"KEY_WORKSPEC_ID"})) {
            j.e("CommandHandler", String.format("Invalid request for %s, requires %s.", v0, "KEY_WORKSPEC_ID"), new Throwable[0]);
        }
        else if("ACTION_SCHEDULE_WORK".equals(v0)) {
            this.b(arg7, arg8, arg9);
        }
        else if("ACTION_DELAY_MET".equals(v0)) {
            this.c(arg7, arg8, arg9);
        }
        else if("ACTION_STOP_WORK".equals(v0)) {
            this.d(arg7, arg8, arg9);
        }
        else if("ACTION_EXECUTION_COMPLETED".equals(v0)) {
            this.g(arg7, arg8, arg9);
        }
        else {
            j.d("CommandHandler", String.format("Ignoring intent %s", arg7), new Throwable[0]);
        }
    }

    boolean a() {
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.b.isEmpty() ^ 1;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    static Intent b(Context arg2) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_RESCHEDULE");
        return v0;
    }

    static Intent b(Context arg2, String arg3) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_DELAY_MET");
        v0.putExtra("KEY_WORKSPEC_ID", arg3);
        return v0;
    }

    private void b(Intent arg8, int arg9, e arg10) {
        androidx.work.impl.b.j v1;
        String v8 = arg8.getExtras().getString("KEY_WORKSPEC_ID");
        j.b("CommandHandler", String.format("Handling schedule work for %s", v8), new Throwable[0]);
        WorkDatabase v0 = arg10.d().d();
        v0.f();
        try {
            v1 = v0.m().b(v8);
            if(v1 != null) {
                goto label_33;
            }

            j.d("CommandHandler", "Skipping scheduling " + v8 + " because it\'s no longer in " + "the DB", new Throwable[0]);
        }
        catch(Throwable v8_1) {
            goto label_84;
        }

        v0.g();
        return;
        try {
        label_33:
            if(v1.b == o.a) {
                goto label_51;
            }

            j.d("CommandHandler", "Skipping scheduling " + v8 + " because it is no longer " + "enqueued", new Throwable[0]);
        }
        catch(Throwable v8_1) {
            goto label_84;
        }

        v0.g();
        return;
        try {
        label_51:
            long v5 = v1.c();
            if(!v1.d()) {
                j.b("CommandHandler", String.format("Setting up Alarms for %s", v8), new Throwable[0]);
                androidx.work.impl.background.systemalarm.a.a(this.a, arg10.d(), v8, v5);
            }
            else {
                j.b("CommandHandler", String.format("Opportunistically setting an alarm for %s", v8), new Throwable[0]);
                androidx.work.impl.background.systemalarm.a.a(this.a, arg10.d(), v8, v5);
                arg10.a(new androidx.work.impl.background.systemalarm.e$a(arg10, b.a(this.a), arg9));
            }

            v0.h();
        }
        catch(Throwable v8_1) {
        label_84:
            v0.g();
            throw v8_1;
        }

        v0.g();
    }

    static Intent c(Context arg2, String arg3) {
        Intent v0 = new Intent(arg2, SystemAlarmService.class);
        v0.setAction("ACTION_STOP_WORK");
        v0.putExtra("KEY_WORKSPEC_ID", arg3);
        return v0;
    }

    private void c(Intent arg6, int arg7, e arg8) {
        Bundle v6 = arg6.getExtras();
        Object v0 = this.c;
        __monitor_enter(v0);
        try {
            String v6_2 = v6.getString("KEY_WORKSPEC_ID");
            j.b("CommandHandler", String.format("Handing delay met for %s", v6_2), new Throwable[0]);
            d v1 = new d(this.a, arg7, v6_2, arg8);
            this.b.put(v6_2, v1);
            v1.a();
            __monitor_exit(v0);
            return;
        label_23:
            __monitor_exit(v0);
        }
        catch(Throwable v6_1) {
            goto label_23;
        }

        throw v6_1;
    }

    private void d(Intent arg4, int arg5, e arg6) {
        String v4 = arg4.getExtras().getString("KEY_WORKSPEC_ID");
        j.b("CommandHandler", String.format("Handing stopWork work for %s", v4), new Throwable[0]);
        arg6.d().c(v4);
        androidx.work.impl.background.systemalarm.a.a(this.a, arg6.d(), v4);
        arg6.a(v4, false);
    }

    private void e(Intent arg5, int arg6, e arg7) {
        j.b("CommandHandler", String.format("Handling constraints changed %s", arg5), new Throwable[0]);
        new c(this.a, arg6, arg7).a();
    }

    private void f(Intent arg5, int arg6, e arg7) {
        j.b("CommandHandler", String.format("Handling reschedule %s, %s", arg5, Integer.valueOf(arg6)), new Throwable[0]);
        arg7.d().j();
    }

    private void g(Intent arg6, int arg7, e arg8) {
        Bundle v8 = arg6.getExtras();
        String v0 = v8.getString("KEY_WORKSPEC_ID");
        boolean v8_1 = v8.getBoolean("KEY_NEEDS_RESCHEDULE");
        j.b("CommandHandler", String.format("Handling onExecutionCompleted %s, %s", arg6, Integer.valueOf(arg7)), new Throwable[0]);
        this.a(v0, v8_1);
    }
}

