package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

public class b {
    private boolean a;

    public b() {
        super();
    }

    String a() {
        return "1.2.4-google_20180831";
    }

    boolean a(String arg2, Context arg3) {
        this.b(arg2, arg3);
        if(this.a(arg2)) {
            if(!this.b()) {
                this.a(true);
                t.a().a(arg3);
                q.a().a(arg3);
                ac.a(arg3);
                r.a().a(arg3);
            }

            return 1;
        }

        return 0;
    }

    void a(boolean arg1) {
        this.a = arg1;
    }

    boolean a(String arg2) {
        boolean v2 = this.b(this.a()) == this.b(arg2) ? true : false;
        return v2;
    }

    boolean b() {
        return this.a;
    }

    private void b(String arg1, Context arg2) {
        this.c(arg1);
        af.a(arg2, "Application Context cannot be null");
    }

    int b(String arg3) {
        this.c(arg3);
        return Integer.parseInt(arg3.split("\\.", 2)[0]);
    }

    private void c(String arg4) {
        af.a(arg4, "Version cannot be null");
        if(!arg4.matches("[0-9]+\\.[0-9]+\\.[0-9]+.*")) {
            String v1 = "Invalid version format : ";
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v1.concat(arg4) : new String(v1);
            throw new IllegalArgumentException(arg4);
        }
    }
}

