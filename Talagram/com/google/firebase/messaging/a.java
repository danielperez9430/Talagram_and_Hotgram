package com.google.firebase.messaging;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.regex.Pattern;

public class a {
    private static final Pattern a;
    private static a b;
    private final FirebaseInstanceId c;

    static {
        a.a = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    }

    private a(FirebaseInstanceId arg1) {
        super();
        this.c = arg1;
    }

    public static a a() {
        a v1_1;
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(a.b == null) {
                a.b = new a(FirebaseInstanceId.a());
            }

            v1_1 = a.b;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public Task a(String arg4) {
        if(arg4 != null && (arg4.startsWith("/topics/"))) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only \'topic-name\' should be used in subscribeToTopic.");
            arg4 = arg4.substring(8);
        }

        if(arg4 != null && (a.a.matcher(((CharSequence)arg4)).matches())) {
            FirebaseInstanceId v0 = this.c;
            String v1 = String.valueOf("S!");
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
            return v0.a(arg4);
        }

        StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 78);
        v2.append("Invalid topic name: ");
        v2.append(arg4);
        v2.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
        throw new IllegalArgumentException(v2.toString());
    }
}

