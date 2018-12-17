package com.e.a;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;

class l extends q {
    class a extends IOException {
        public a(String arg1) {
            super(arg1);
        }
    }

    private final g a;
    private final r b;

    public com.e.a.q$a a(o arg7, int arg8) {
        com.e.a.g$a v7 = this.a.a(arg7.d, arg7.c);
        com.e.a.q$a v8 = null;
        if(v7 == null) {
            return v8;
        }

        b v0 = v7.c ? b.b : b.c;
        Bitmap v1 = v7.b();
        if(v1 != null) {
            return new com.e.a.q$a(v1, v0);
        }

        InputStream v1_1 = v7.a();
        if(v1_1 == null) {
            return v8;
        }

        long v2 = 0;
        if(v0 == b.b) {
            if(v7.c() != v2) {
            }
            else {
                v.a(v1_1);
                throw new a("Received response with 0 content-length header.");
            }
        }

        if(v0 == b.c && v7.c() > v2) {
            this.b.a(v7.c());
        }

        return new com.e.a.q$a(v1_1, v0);
    }
}

