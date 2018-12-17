package com.crashlytics.android.c;

class aj {
    public final String a;
    public final String b;
    public final StackTraceElement[] c;
    public final aj d;

    public aj(Throwable arg2, ai arg3) {
        super();
        this.a = arg2.getLocalizedMessage();
        this.b = arg2.getClass().getName();
        this.c = arg3.a(arg2.getStackTrace());
        arg2 = arg2.getCause();
        aj v0 = arg2 != null ? new aj(arg2, arg3) : null;
        this.d = v0;
    }
}

