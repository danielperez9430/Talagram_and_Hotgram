package com.e.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import java.io.InputStream;

public abstract class q {
    public final class a {
        private final b a;
        private final Bitmap b;
        private final InputStream c;
        private final int d;

        public a(Bitmap arg3, b arg4) {
            this(v.a(arg3, "bitmap == null"), null, arg4, 0);
        }

        a(Bitmap arg4, InputStream arg5, b arg6, int arg7) {
            super();
            int v0 = 0;
            int v2 = arg4 != null ? 1 : 0;
            if(arg5 != null) {
                v0 = 1;
            }

            if((v0 ^ v2) != 0) {
                this.b = arg4;
                this.c = arg5;
                this.a = v.a(arg6, "loadedFrom == null");
                this.d = arg7;
                return;
            }

            throw new AssertionError();
        }

        public a(InputStream arg3, b arg4) {
            this(null, v.a(arg3, "stream == null"), arg4, 0);
        }

        public Bitmap a() {
            return this.b;
        }

        public InputStream b() {
            return this.c;
        }

        public b c() {
            return this.a;
        }

        int d() {
            return this.d;
        }
    }

    public q() {
        super();
    }

    static BitmapFactory$Options a(o arg3) {
        boolean v0 = arg3.d();
        int v1 = arg3.q != null ? 1 : 0;
        BitmapFactory$Options v2 = null;
        if((v0) || v1 != 0) {
            v2 = new BitmapFactory$Options();
            v2.inJustDecodeBounds = v0;
            if(v1 != 0) {
                v2.inPreferredConfig = arg3.q;
            }
        }

        return v2;
    }

    static boolean a(BitmapFactory$Options arg0) {
        boolean v0 = arg0 == null || !arg0.inJustDecodeBounds ? false : true;
        return v0;
    }

    static void a(int arg6, int arg7, BitmapFactory$Options arg8, o arg9) {
        q.a(arg6, arg7, arg8.outWidth, arg8.outHeight, arg8, arg9);
    }

    public abstract a a(o arg1, int arg2);

    static void a(int arg2, int arg3, int arg4, int arg5, BitmapFactory$Options arg6, o arg7) {
        double v2;
        if(arg5 > arg3 || arg4 > arg2) {
            if(arg3 == 0) {
                v2 = ((double)((((float)arg4)) / (((float)arg2))));
            }
            else if(arg2 == 0) {
                v2 = ((double)((((float)arg5)) / (((float)arg3))));
            }
            else {
                goto label_19;
            }

            arg2 = ((int)Math.floor(v2));
            goto label_36;
        label_19:
            arg3 = ((int)Math.floor(((double)((((float)arg5)) / (((float)arg3))))));
            arg2 = ((int)Math.floor(((double)((((float)arg4)) / (((float)arg2))))));
            if(arg7.k) {
                arg2 = Math.max(arg3, arg2);
                goto label_36;
            }

            arg2 = Math.min(arg3, arg2);
        }
        else {
            arg2 = 1;
        }

    label_36:
        arg6.inSampleSize = arg2;
        arg6.inJustDecodeBounds = false;
    }
}

