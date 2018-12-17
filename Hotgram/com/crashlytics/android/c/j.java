package com.crashlytics.android.c;

import c.a.a.a.a.f.a;
import c.a.a.a.c;
import c.a.a.a.l;
import java.io.File;
import java.io.IOException;

class j {
    private final String a;
    private final a b;

    public j(String arg1, a arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public boolean a() {
        boolean v0_1;
        try {
            v0_1 = this.d().createNewFile();
        }
        catch(IOException v0) {
            l v1 = c.h();
            v1.e("CrashlyticsCore", "Error creating marker: " + this.a, ((Throwable)v0));
            v0_1 = false;
        }

        return v0_1;
    }

    public boolean b() {
        return this.d().exists();
    }

    public boolean c() {
        return this.d().delete();
    }

    private File d() {
        return new File(this.b.a(), this.a);
    }
}

