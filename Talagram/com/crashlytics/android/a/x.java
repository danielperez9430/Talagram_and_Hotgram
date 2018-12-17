package com.crashlytics.android.a;

import c.a.a.a.a.c.a.e;

class x {
    long a;
    private e b;

    public x(e arg2) {
        super();
        if(arg2 != null) {
            this.b = arg2;
            return;
        }

        throw new NullPointerException("retryState must not be null");
    }

    public void a() {
        this.a = 0;
        this.b = this.b.c();
    }

    public boolean a(long arg5) {
        boolean v5 = arg5 - this.a >= this.b.a() * 1000000 ? true : false;
        return v5;
    }

    public void b(long arg1) {
        this.a = arg1;
        this.b = this.b.b();
    }
}

