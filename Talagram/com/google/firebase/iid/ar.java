package com.google.firebase.iid;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.Executor;

final class ar implements b {
    private final com.google.firebase.b a;
    private final p b;
    private final v c;
    private final Executor d;

    ar(com.google.firebase.b arg3, p arg4, Executor arg5) {
        this(arg3, arg4, arg5, new v(arg3.a(), arg4));
    }

    private ar(com.google.firebase.b arg1, p arg2, Executor arg3, v arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg4;
        this.d = arg3;
    }

    final void a(Bundle arg2, TaskCompletionSource arg3) {
        try {
            arg3.setResult(this.c.a(arg2));
            return;
        }
        catch(IOException v2) {
            arg3.setException(((Exception)v2));
            return;
        }
    }

    private final Task a(Task arg3) {
        return arg3.continueWith(ak.a(), new at(this));
    }

    private final Task a(String arg2, String arg3, String arg4, Bundle arg5) {
        arg5.putString("scope", arg4);
        arg5.putString("sender", arg3);
        arg5.putString("subtype", arg3);
        arg5.putString("appid", arg2);
        arg5.putString("gmp_app_id", this.a.c().a());
        arg5.putString("gmsv", Integer.toString(this.b.d()));
        arg5.putString("osv", Integer.toString(Build$VERSION.SDK_INT));
        arg5.putString("app_ver", this.b.b());
        arg5.putString("app_ver_name", this.b.c());
        arg5.putString("cliv", "fiid-12451000");
        TaskCompletionSource v2 = new TaskCompletionSource();
        this.d.execute(new as(this, arg5, v2));
        return v2.getTask();
    }

    private static String a(Bundle arg2) {
        if(arg2 != null) {
            String v0 = arg2.getString("registration_id");
            if(v0 != null) {
                return v0;
            }

            v0 = arg2.getString("unregistered");
            if(v0 != null) {
                return v0;
            }

            v0 = arg2.getString("error");
            if(!"RST".equals(v0)) {
                if(v0 != null) {
                    throw new IOException(v0);
                }

                String v2 = String.valueOf(arg2);
                StringBuilder v1 = new StringBuilder(String.valueOf(v2).length() + 21);
                v1.append("Unexpected response: ");
                v1.append(v2);
                Log.w("FirebaseInstanceId", v1.toString(), new Throwable());
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }

            throw new IOException("INSTANCE_ID_RESET");
        }

        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    static String a(ar arg0, Bundle arg1) {
        return ar.a(arg1);
    }

    public final Task a(String arg1, String arg2) {
        return Tasks.forResult(null);
    }

    public final Task a(String arg6, String arg7, String arg8) {
        Bundle v0 = new Bundle();
        String v1 = "gcm.topic";
        String v2 = String.valueOf("/topics/");
        String v3 = String.valueOf(arg8);
        v2 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
        v0.putString(v1, v2);
        v1 = String.valueOf("/topics/");
        arg8 = String.valueOf(arg8);
        arg8 = arg8.length() != 0 ? v1.concat(arg8) : new String(v1);
        return this.a(this.b(this.a(arg6, arg7, arg8, v0)));
    }

    public final Task a(String arg1, String arg2, String arg3, String arg4) {
        return this.b(this.a(arg1, arg3, arg4, new Bundle()));
    }

    public final boolean a() {
        return 1;
    }

    private final Task b(Task arg3) {
        return arg3.continueWith(this.d, new au(this));
    }

    public final Task b(String arg6, String arg7, String arg8) {
        Bundle v0 = new Bundle();
        String v1 = "gcm.topic";
        String v2 = String.valueOf("/topics/");
        String v3 = String.valueOf(arg8);
        v2 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
        v0.putString(v1, v2);
        v0.putString("delete", "1");
        v1 = String.valueOf("/topics/");
        arg8 = String.valueOf(arg8);
        arg8 = arg8.length() != 0 ? v1.concat(arg8) : new String(v1);
        return this.a(this.b(this.a(arg6, arg7, arg8, v0)));
    }

    public final boolean b() {
        if(this.b.a() != 0) {
            return 1;
        }

        return 0;
    }
}

