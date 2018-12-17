package com.crashlytics.android.a;

import c.a.a.a.c;
import java.util.Locale;
import java.util.Map;

class e {
    final int a;
    final int b;
    boolean c;

    public e(int arg1, int arg2, boolean arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public boolean a(Object arg2, String arg3) {
        if(arg2 == null) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg3);
            v0.append(" must not be null");
            this.a(new NullPointerException(v0.toString()));
            return 1;
        }

        return 0;
    }

    public String a(String arg6) {
        if(arg6.length() > this.b) {
            this.a(new IllegalArgumentException(String.format(Locale.US, "String is too long, truncating to %d characters", Integer.valueOf(this.b))));
            arg6 = arg6.substring(0, this.b);
        }

        return arg6;
    }

    public boolean a(Map arg5, String arg6) {
        if(arg5.size() >= this.a && !arg5.containsKey(arg6)) {
            this.a(new IllegalArgumentException(String.format(Locale.US, "Limit of %d attributes reached, skipping attribute", Integer.valueOf(this.a))));
            return 1;
        }

        return 0;
    }

    private void a(RuntimeException arg4) {
        if(!this.c) {
            c.h().e("Answers", "Invalid user input detected", ((Throwable)arg4));
            return;
        }

        throw arg4;
    }
}

