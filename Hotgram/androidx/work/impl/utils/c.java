package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class c {
    private final Context a;
    private SharedPreferences b;
    private boolean c;

    public c(Context arg1) {
        super();
        this.a = arg1;
    }

    public int a() {
        Class v0 = c.class;
        __monitor_enter(v0);
        try {
            this.b();
            __monitor_exit(v0);
            return this.a("next_alarm_manager_id");
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    private int a(String arg4) {
        int v1 = 0;
        int v0 = this.b.getInt(arg4, 0);
        if(v0 == 2147483647) {
        }
        else {
            v1 = v0 + 1;
        }

        this.a(arg4, v1);
        return v0;
    }

    private void a(String arg2, int arg3) {
        this.b.edit().putInt(arg2, arg3).apply();
    }

    public int a(int arg3, int arg4) {
        Class v0 = c.class;
        __monitor_enter(v0);
        try {
            this.b();
            int v1 = this.a("next_job_scheduler_id");
            if(v1 < arg3 || v1 > arg4) {
                this.a("next_job_scheduler_id", arg3 + 1);
            }
            else {
                arg3 = v1;
            }

            __monitor_exit(v0);
            return arg3;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_16;
        }

        throw v3;
    }

    private void b() {
        if(!this.c) {
            this.b = this.a.getSharedPreferences("androidx.work.util.id", 0);
            this.c = true;
        }
    }
}

