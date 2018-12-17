package com.crashlytics.android.c;

import android.content.Context;
import c.a.a.a.a.b.i;
import c.a.a.a.a.g.o;

class r {
    private final Context a;
    private final o b;

    public r(Context arg1, o arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public String a() {
        return this.a("com.crashlytics.CrashSubmissionPromptTitle", this.b.a);
    }

    private String a(String arg2, String arg3) {
        return this.b(i.b(this.a, arg2), arg3);
    }

    private boolean a(String arg1) {
        boolean v1 = arg1 == null || arg1.length() == 0 ? true : false;
        return v1;
    }

    public String b() {
        return this.a("com.crashlytics.CrashSubmissionPromptMessage", this.b.b);
    }

    private String b(String arg2, String arg3) {
        if(this.a(arg2)) {
            arg2 = arg3;
        }

        return arg2;
    }

    public String c() {
        return this.a("com.crashlytics.CrashSubmissionSendTitle", this.b.c);
    }

    public String d() {
        return this.a("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.b.g);
    }

    public String e() {
        return this.a("com.crashlytics.CrashSubmissionCancelTitle", this.b.e);
    }
}

