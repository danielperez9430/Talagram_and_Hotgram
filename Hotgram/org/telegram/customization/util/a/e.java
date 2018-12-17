package org.telegram.customization.util.a;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public class e extends f {
    private SharedPreferences a;
    private com.google.a.f b;

    public e(Context arg3) {
        super();
        this.a = arg3.getSharedPreferences("ClientPreferencesBase", 0);
        this.b = new com.google.a.f();
    }

    public void a(boolean arg3) {
        __monitor_enter(this);
        try {
            SharedPreferences$Editor v0 = this.a.edit();
            v0.putBoolean("FCMTokenSendToServer", arg3);
            v0.apply();
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }
}

