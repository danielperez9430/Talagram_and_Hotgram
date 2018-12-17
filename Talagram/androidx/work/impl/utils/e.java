package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class e {
    private SharedPreferences a;

    public e(Context arg3) {
        this(arg3.getSharedPreferences("androidx.work.util.preferences", 0));
    }

    public e(SharedPreferences arg1) {
        super();
        this.a = arg1;
    }

    public void a(boolean arg3) {
        this.a.edit().putBoolean("reschedule_needed", arg3).apply();
    }

    public boolean a() {
        return this.a.getBoolean("reschedule_needed", false);
    }
}

