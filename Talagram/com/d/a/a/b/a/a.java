package com.d.a.a.b.a;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class a implements com.d.a.a.b.a {
    private final com.d.a.a.b.a a;
    private final Comparator b;

    public a(com.d.a.a.b.a arg1, Comparator arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public Bitmap a(String arg2) {
        return this.a.a(arg2);
    }

    public Collection a() {
        return this.a.a();
    }

    public boolean a(String arg6, Bitmap arg7) {
        Object v3;
        com.d.a.a.b.a v0 = this.a;
        __monitor_enter(v0);
        Object v1 = null;
        try {
            Iterator v2 = this.a.a().iterator();
            do {
                if(v2.hasNext()) {
                    v3 = v2.next();
                    if(this.b.compare(arg6, v3) != 0) {
                        continue;
                    }

                    break;
                }

                goto label_13;
            }
            while(true);

            v1 = v3;
        label_13:
            if(v1 != null) {
                this.a.b(((String)v1));
            }

            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_21;
        }

        return this.a.a(arg6, arg7);
        try {
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_21;
        }

        throw v6;
    }

    public Bitmap b(String arg2) {
        return this.a.b(arg2);
    }
}

