package com.e.a;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

public interface g {
    public class a {
        final InputStream a;
        final Bitmap b;
        final boolean c;
        final long d;

        public InputStream a() {
            return this.a;
        }

        @Deprecated public Bitmap b() {
            return this.b;
        }

        public long c() {
            return this.d;
        }
    }

    public class b extends IOException {
        final boolean a;
        final int b;

    }

    a a(Uri arg1, int arg2);
}

