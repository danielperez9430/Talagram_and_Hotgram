package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class z {
    final String a;
    private static final long b;
    private final String c;
    private final long d;

    static {
        z.b = TimeUnit.DAYS.toMillis(7);
    }

    private z(String arg1, String arg2, long arg3) {
        super();
        this.a = arg1;
        this.c = arg2;
        this.d = arg3;
    }

    static String a(z arg0) {
        if(arg0 == null) {
            return null;
        }

        return arg0.a;
    }

    static z a(String arg6) {
        z v1 = null;
        if(TextUtils.isEmpty(((CharSequence)arg6))) {
            return v1;
        }

        if(arg6.startsWith("{")) {
            try {
                JSONObject v0 = new JSONObject(arg6);
                return new z(v0.getString("token"), v0.getString("appVersion"), v0.getLong("timestamp"));
            }
            catch(JSONException v6) {
                arg6 = String.valueOf(v6);
                StringBuilder v3 = new StringBuilder(String.valueOf(arg6).length() + 23);
                v3.append("Failed to parse token: ");
                v3.append(arg6);
                Log.w("FirebaseInstanceId", v3.toString());
                return v1;
            }
        }

        return new z(arg6, ((String)v1), 0);
    }

    static String a(String arg2, String arg3, long arg4) {
        try {
            JSONObject v0 = new JSONObject();
            v0.put("token", arg2);
            v0.put("appVersion", arg3);
            v0.put("timestamp", arg4);
            return v0.toString();
        }
        catch(JSONException v2) {
            arg2 = String.valueOf(v2);
            StringBuilder v5 = new StringBuilder(String.valueOf(arg2).length() + 24);
            v5.append("Failed to encode token: ");
            v5.append(arg2);
            Log.w("FirebaseInstanceId", v5.toString());
            return null;
        }
    }

    final boolean b(String arg7) {
        if(System.currentTimeMillis() <= this.d + z.b) {
            if(!arg7.equals(this.c)) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }
}

