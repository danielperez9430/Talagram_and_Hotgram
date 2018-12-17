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
        Object v0;
        if(this.a != null) {
            v0 = this.a.clone();
        }
        else {
            Calendar v0_1 = Calendar.getInstance();
        }

        return ((Calendar)v0);
    }
}

