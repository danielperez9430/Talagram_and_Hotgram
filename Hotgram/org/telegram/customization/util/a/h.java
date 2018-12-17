package org.telegram.customization.util.a;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import com.google.a.f;

public class h extends i {
    private SharedPreferences a;
    private f b;

    public h(Context arg3) {
        super();
        this.a = arg3.getSharedPreferences("LinphonePreferencesBase", 0);
        this.b = new f();
    }

    public void a(long arg3) {
        __monitor_enter(this);
        try {
            SharedPreferences$Editor v0 = this.a.edit();
            v0.putLong("callWhatsUpInterval", arg3);
            v0.apply();
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public void a(boolean arg3) {
        __monitor_enter(this);
        try {
            SharedPreferences$Editor v0 = this.a.edit();
            v0.putBoolean("callRatePopupShow", arg3);
            v0.apply();
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public boolean a() {
        if(this.a("enableEchoCancellation")) {
            return this.a.getBoolean("enableEchoCancellation", this.e());
        }

        return this.e();
    }

    boolean a(String arg2) {
        return this.a.getAll().containsKey(arg2);
    }

    public boolean b() {
        if(this.a("enableAdaptiveRateControl")) {
            return this.a.getBoolean("enableAdaptiveRateControl", this.f());
        }

        return this.f();
    }

    public boolean c() {
        if(this.a("callRatePopupShow")) {
            return this.a.getBoolean("callRatePopupShow", this.g());
        }

        return this.g();
    }

    public long d() {
        if(this.a("callWhatsUpInterval")) {
            return this.a.getLong("callWhatsUpInterval", this.h());
        }

        return this.h();
    }
}

