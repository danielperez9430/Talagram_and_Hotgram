package net.hockeyapp.android.e;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public class i {
    class net.hockeyapp.android.e.i$1 {
    }

    class a {
        static final i a;

        static {
            a.a = new i(null);
        }
    }

    private SharedPreferences a;
    private SharedPreferences b;

    private i() {
        super();
    }

    i(net.hockeyapp.android.e.i$1 arg1) {
        this();
    }

    public void a(Context arg3, String arg4) {
        if(arg3 != null) {
            this.a = arg3.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
            if(this.a != null) {
                SharedPreferences$Editor v3 = this.a.edit();
                v3.putString("net.hockeyapp.android.prefs_key_feedback_token", arg4);
                v3.apply();
            }
        }
    }

    public static i a() {
        return a.a;
    }

    public void a(Context arg5, String arg6, String arg7, String arg8) {
        if(arg5 != null) {
            this.b = arg5.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
            if(this.b != null) {
                SharedPreferences$Editor v5 = this.b.edit();
                if(arg6 == null || arg7 == null || arg8 == null) {
                    v5.putString("net.hockeyapp.android.prefs_key_name_email", null);
                }
                else {
                    v5.putString("net.hockeyapp.android.prefs_key_name_email", String.format("%s|%s|%s", arg6, arg7, arg8));
                }

                v5.apply();
            }
        }
    }
}

