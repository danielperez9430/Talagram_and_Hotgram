package com.google.firebase.iid;

import android.support.v4.f.a;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

final class s {
    @GuardedBy(value="this") private final Map a;

    s() {
        super();
        this.a = new a();
    }

    final Task a(String arg4, String arg5, u arg6) {
        Task v4_2;
        Object v4_1;
        Pair v0;
        __monitor_enter(this);
        try {
            v0 = new Pair(arg4, arg5);
            v4_1 = this.a.get(v0);
            int v5 = 3;
            if(v4_1 == null) {
                goto label_24;
            }

            if(Log.isLoggable("FirebaseInstanceId", v5)) {
                String v6 = String.valueOf(v0);
                StringBuilder v1 = new StringBuilder(String.valueOf(v6).length() + 29);
                v1.append("Joining ongoing request for: ");
                v1.append(v6);
                Log.d("FirebaseInstanceId", v1.toString());
            }
        }
        catch(Throwable v4) {
            goto label_49;
        }

        __monitor_exit(this);
        return ((Task)v4_1);
        try {
        label_24:
            if(Log.isLoggable("FirebaseInstanceId", v5)) {
                arg5 = String.valueOf(v0);
                StringBuilder v2 = new StringBuilder(String.valueOf(arg5).length() + 24);
                v2.append("Making new request for: ");
                v2.append(arg5);
                Log.d("FirebaseInstanceId", v2.toString());
            }

            v4_2 = arg6.a().continueWithTask(ak.a(), new t(this, v0));
            this.a.put(v0, v4_2);
        }
        catch(Throwable v4) {
        label_49:
            __monitor_exit(this);
            throw v4;
        }

        __monitor_exit(this);
        return v4_2;
    }

    final Task a(Pair arg2, Task arg3) {
        __monitor_enter(this);
        try {
            this.a.remove(arg2);
            __monitor_exit(this);
            return arg3;
        label_6:
            __monitor_exit(this);
        }
        catch(Throwable v2) {
            goto label_6;
        }

        throw v2;
    }
}

