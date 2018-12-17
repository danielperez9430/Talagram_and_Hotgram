package com.crashlytics.android.c;

import c.a.a.a.a.b.i;
import c.a.a.a.a.b.r$c;
import c.a.a.a.a.b.r;
import c.a.a.a.l;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

class ac implements s {
    private final File a;
    private final int b;
    private r c;

    public ac(File arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public b a() {
        b v1 = null;
        if(!this.a.exists()) {
            return v1;
        }

        this.d();
        if(this.c == null) {
            return v1;
        }

        int[] v0 = new int[]{0};
        byte[] v2 = new byte[this.c.a()];
        try {
            this.c.a(new c(v2, v0) {
                public void a(InputStream arg4, int arg5) {
                    try {
                        arg4.read(this.a, this.b[0], arg5);
                        this.b[0] += arg5;
                    }
                    catch(Throwable v5) {
                        arg4.close();
                        throw v5;
                    }

                    arg4.close();
                }
            });
        }
        catch(IOException v3) {
            c.a.a.a.c.h().e("CrashlyticsCore", "A problem occurred while reading the Crashlytics log file.", ((Throwable)v3));
        }

        return b.a(v2, 0, v0[0]);
    }

    public void b() {
        i.a(this.c, "There was a problem closing the Crashlytics log file.");
        this.c = null;
    }

    public void c() {
        this.b();
        this.a.delete();
    }

    private void d() {
        if(this.c == null) {
            try {
                this.c = new r(this.a);
            }
            catch(IOException v0) {
                l v1 = c.a.a.a.c.h();
                v1.e("CrashlyticsCore", "Could not open log file: " + this.a, ((Throwable)v0));
            }
        }
    }
}

