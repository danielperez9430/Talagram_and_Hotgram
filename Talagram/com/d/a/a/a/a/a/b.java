package com.d.a.a.a.a.a;

import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import com.d.a.a.a.a;
import com.d.a.c.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class b implements a {
    public static final Bitmap$CompressFormat a;
    protected com.d.a.a.a.a.a.a b;
    protected final com.d.a.a.a.b.a c;
    protected int d;
    protected Bitmap$CompressFormat e;
    protected int f;
    private File g;

    static {
        b.a = Bitmap$CompressFormat.PNG;
    }

    public b(File arg7, File arg8, com.d.a.a.a.b.a arg9, long arg10, int arg12) {
        super();
        this.d = 32768;
        this.e = b.a;
        this.f = 100;
        if(arg7 != null) {
            long v0 = 0;
            if(arg10 >= v0) {
                if(arg12 >= 0) {
                    if(arg9 != null) {
                        if(arg10 == v0) {
                            arg10 = 9223372036854775807L;
                        }

                        long v3 = arg10;
                        int v5 = arg12 == 0 ? 2147483647 : arg12;
                        this.g = arg8;
                        this.c = arg9;
                        this.a(arg7, arg8, v3, v5);
                        return;
                    }

                    throw new IllegalArgumentException("fileNameGenerator argument must be not null");
                }

                throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
            }

            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        }

        throw new IllegalArgumentException("cacheDir argument must be not null");
    }

    private void a(File arg7, File arg8, long arg9, int arg11) {
        File v0 = arg7;
        long v3 = arg9;
        int v5 = arg11;
        try {
            this.b = com.d.a.a.a.a.a.a.a(v0, 1, 1, v3, v5);
        }
        catch(IOException v7) {
            c.a(((Throwable)v7));
            if(arg8 != null) {
                this.a(arg8, null, arg9, arg11);
            }

            if(this.b != null) {
                return;
            }

            throw v7;
        }
    }

    public File a(String arg4) {
        com.d.a.a.a.a.a.a$c v0_2;
        com.d.a.a.a.a.a.a$c v4_1;
        File v0 = null;
        try {
            v4_1 = this.b.a(this.b(arg4));
            if(v4_1 != null) {
                goto label_7;
            }
        }
        catch(Throwable v4) {
            goto label_26;
        }
        catch(IOException v1) {
            v4_1 = ((com.d.a.a.a.a.a.a$c)v0);
            goto label_18;
        }

        goto label_9;
        try {
        label_7:
            v0 = v4_1.a(0);
            goto label_9;
        }
        catch(Throwable v0_1) {
        label_23:
            Throwable v2 = v0_1;
            v0_2 = v4_1;
            v4 = v2;
        }
        catch(IOException v1) {
            try {
            label_18:
                c.a(((Throwable)v1));
                if(v4_1 != null) {
                    goto label_20;
                }

                return v0;
            }
            catch(Throwable v0_1) {
                goto label_23;
            }

        label_20:
            v4_1.close();
            return v0;
        }

    label_26:
        if(v0_2 != null) {
            v0_2.close();
        }

        throw v4;
    label_9:
        if(v4_1 != null) {
            v4_1.close();
        }

        return v0;
    }

    public boolean a(String arg4, Bitmap arg5) {
        boolean v5;
        com.d.a.a.a.a.a.a$a v4 = this.b.b(this.b(arg4));
        if(v4 == null) {
            return 0;
        }

        BufferedOutputStream v1 = new BufferedOutputStream(v4.a(0), this.d);
        try {
            v5 = arg5.compress(this.e, this.f, ((OutputStream)v1));
        }
        catch(Throwable v4_1) {
            com.d.a.c.b.a(((Closeable)v1));
            throw v4_1;
        }

        com.d.a.c.b.a(((Closeable)v1));
        if(v5) {
            v4.a();
        }
        else {
            v4.b();
        }

        return v5;
    }

    public boolean a(String arg4, InputStream arg5, com.d.a.c.b$a arg6) {
        boolean v5_1;
        com.d.a.a.a.a.a.a$a v4 = this.b.b(this.b(arg4));
        if(v4 == null) {
            return 0;
        }

        BufferedOutputStream v1 = new BufferedOutputStream(v4.a(0), this.d);
        try {
            v5_1 = com.d.a.c.b.a(arg5, ((OutputStream)v1), arg6, this.d);
        }
        catch(Throwable v5) {
            com.d.a.c.b.a(((Closeable)v1));
            v4.b();
            throw v5;
        }

        com.d.a.c.b.a(((Closeable)v1));
        if(v5_1) {
            v4.a();
        }
        else {
            v4.b();
        }

        return v5_1;
    }

    private String b(String arg2) {
        return this.c.a(arg2);
    }
}

