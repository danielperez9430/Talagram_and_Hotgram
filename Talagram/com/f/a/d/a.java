package com.f.a.d;

import java.util.Calendar;

public class a {
    protected Calendar a;
    private static a b;

    public a() {
        super();
    }

    protected static a a() {
        if(a.b == null) {
            a.b = new a();
        }

        return a.b;
    }

    public static Calendar b() {
        return a.a().c();
    }

    private Calendar c() {
        Calendar v0_1;
        if(this.a != null) {
            Object v0 = this.a.clone();
        }
        else {
            v0_1 = Calendar.getInstance();
        }

        return v0_1;
    }
}

