package com.d.a.b.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import com.d.a.b.a.d;
import com.d.a.b.a.e;
import com.d.a.b.a.h;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class a implements b {
    public class com.d.a.b.b.a$a {
        public final int a;
        public final boolean b;

        protected com.d.a.b.b.a$a(int arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        protected com.d.a.b.b.a$a() {
            super();
            this.a = 0;
            this.b = false;
        }
    }

    public class com.d.a.b.b.a$b {
        public final e a;
        public final com.d.a.b.b.a$a b;

        protected com.d.a.b.b.a$b(e arg1, com.d.a.b.b.a$a arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    protected final boolean a;

    public a(boolean arg1) {
        super();
        this.a = arg1;
    }

    private boolean a(String arg2, String arg3) {
        boolean v2 = !"image/jpeg".equalsIgnoreCase(arg3) || com.d.a.b.d.b$a.a(arg2) != com.d.a.b.d.b$a.c ? false : true;
        return v2;
    }

    protected Bitmap a(Bitmap arg11, c arg12, int arg13, boolean arg14) {
        Matrix v5 = new Matrix();
        d v0 = arg12.d();
        int v2 = 2;
        float v3 = 1f;
        if(v0 == d.e || v0 == d.f) {
            e v1 = new e(arg11.getWidth(), arg11.getHeight(), arg13);
            e v7 = arg12.c();
            h v8 = arg12.e();
            boolean v0_1 = v0 == d.f ? true : false;
            float v0_2 = com.d.a.c.a.b(v1, v7, v8, v0_1);
            if(Float.compare(v0_2, v3) == 0) {
                goto label_40;
            }

            v5.setScale(v0_2, v0_2);
            if(!this.a) {
                goto label_40;
            }

            Object[] v8_1 = new Object[4];
            v8_1[0] = v1;
            v8_1[1] = v1.a(v0_2);
            v8_1[v2] = Float.valueOf(v0_2);
            v8_1[3] = arg12.a();
            com.d.a.c.c.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", v8_1);
        }

    label_40:
        if(arg14) {
            v5.postScale(-1f, v3);
            if(this.a) {
                com.d.a.c.c.a("Flip image horizontally [%s]", new Object[]{arg12.a()});
            }
        }

        if(arg13 != 0) {
            v5.postRotate(((float)arg13));
            if(this.a) {
                Object[] v0_3 = new Object[v2];
                v0_3[0] = Integer.valueOf(arg13);
                v0_3[1] = arg12.a();
                com.d.a.c.c.a("Rotate image on %1$d° [%2$s]", v0_3);
            }
        }

        Bitmap v12 = Bitmap.createBitmap(arg11, 0, 0, arg11.getWidth(), arg11.getHeight(), v5, true);
        if(v12 != arg11) {
            arg11.recycle();
        }

        return v12;
    }

    public Bitmap a(c arg7) {
        Bitmap v0_1;
        InputStream v5;
        com.d.a.b.b.a$b v4;
        InputStream v0 = this.b(arg7);
        Bitmap v1 = null;
        if(v0 == null) {
            com.d.a.c.c.d("No stream for image [%s]", new Object[]{arg7.a()});
            return v1;
        }

        try {
            v4 = this.a(v0, arg7);
            v5 = this.b(v0, arg7);
        }
        catch(Throwable v7) {
            v5 = v0;
            goto label_34;
        }

        try {
            v0_1 = BitmapFactory.decodeStream(v5, ((Rect)v1), this.a(v4.a, arg7));
            goto label_16;
        }
        catch(Throwable v7) {
        }

    label_34:
        com.d.a.c.b.a(((Closeable)v5));
        throw v7;
    label_16:
        com.d.a.c.b.a(((Closeable)v5));
        if(v0_1 == null) {
            com.d.a.c.c.d("Image can\'t be decoded [%s]", new Object[]{arg7.a()});
        }
        else {
            v0_1 = this.a(v0_1, arg7, v4.b.a, v4.b.b);
        }

        return v0_1;
    }

    protected com.d.a.b.b.a$b a(InputStream arg5, c arg6) {
        BitmapFactory$Options v0 = new BitmapFactory$Options();
        v0.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(arg5, null, v0);
        String v5 = arg6.b();
        com.d.a.b.b.a$a v5_1 = !arg6.h() || !this.a(v5, v0.outMimeType) ? new com.d.a.b.b.a$a() : this.a(v5);
        return new com.d.a.b.b.a$b(new e(v0.outWidth, v0.outHeight, v5_1.a), v5_1);
    }

    protected BitmapFactory$Options a(e arg6, c arg7) {
        int v0_1;
        d v0 = arg7.d();
        if(v0 == d.a) {
            v0_1 = 1;
        }
        else if(v0 == d.b) {
            v0_1 = com.d.a.c.a.a(arg6);
        }
        else {
            e v1 = arg7.c();
            boolean v0_2 = v0 == d.c ? true : false;
            v0_1 = com.d.a.c.a.a(arg6, v1, arg7.e(), v0_2);
        }

        if(v0_1 > 1 && (this.a)) {
            com.d.a.c.c.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", new Object[]{arg6, arg6.a(v0_1), Integer.valueOf(v0_1), arg7.a()});
        }

        BitmapFactory$Options v6 = arg7.i();
        v6.inSampleSize = v0_1;
        return v6;
    }

    protected com.d.a.b.b.a$a a(String arg5) {
        int v0 = 0;
        boolean v1 = true;
        try {
            switch(new ExifInterface(com.d.a.b.d.b$a.c.c(arg5)).getAttributeInt("Orientation", 1)) {
                case 2: {
                    goto label_30;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_20;
                }
                case 5: {
                    goto label_15;
                }
                case 6: {
                    goto label_11;
                }
                case 7: {
                    goto label_10;
                }
                case 8: {
                    goto label_16;
                }
            }
        }
        catch(IOException ) {
            com.d.a.c.c.c("Can\'t read EXIF tags from file [%s]", new Object[]{arg5});
        }

        v1 = false;
        goto label_30;
    label_20:
        boolean v0_1 = true;
    label_21:
        v1 = ((boolean)v0);
        v0 = 180;
        goto label_30;
    label_10:
        v0_1 = true;
    label_11:
        v1 = ((boolean)v0);
        v0 = 90;
        goto label_30;
    label_15:
        v0_1 = true;
    label_16:
        v1 = ((boolean)v0);
        v0 = 270;
    label_30:
        return new com.d.a.b.b.a$a(v0, v1);
    }

    protected InputStream b(c arg3) {
        return arg3.f().a(arg3.b(), arg3.g());
    }

    protected InputStream b(InputStream arg2, c arg3) {
        if(!arg2.markSupported()) {
            goto label_4;
        }

        try {
            arg2.reset();
            return arg2;
        }
        catch(IOException ) {
        label_4:
            com.d.a.c.b.a(((Closeable)arg2));
            return this.b(arg3);
        }
    }
}

