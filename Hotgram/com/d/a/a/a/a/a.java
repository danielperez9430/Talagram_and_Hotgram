package com.d.a.a.a.a;

import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import com.d.a.c.b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class a implements com.d.a.a.a.a {
    public static final Bitmap$CompressFormat a;
    protected final File b;
    protected final File c;
    protected final com.d.a.a.a.b.a d;
    protected int e;
    protected Bitmap$CompressFormat f;
    protected int g;

    static {
        a.a = Bitmap$CompressFormat.PNG;
    }

    public a(File arg2, File arg3, com.d.a.a.a.b.a arg4) {
        super();
        this.e = 32768;
        this.f = a.a;
        this.g = 100;
        if(arg2 != null) {
            if(arg4 != null) {
                this.b = arg2;
                this.c = arg3;
                this.d = arg4;
                return;
            }

            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        }

        throw new IllegalArgumentException("cacheDir argument must be not null");
    }

    public File a(String arg1) {
        return this.b(arg1);
    }

    public boolean a(String arg5, Bitmap arg6) {
        boolean v2;
        File v5 = this.b(arg5);
        StringBuilder v1 = new StringBuilder();
        v1.append(v5.getAbsolutePath());
        v1.append(".tmp");
        File v0 = new File(v1.toString());
        BufferedOutputStream v1_1 = new BufferedOutputStream(new FileOutputStream(v0), this.e);
        try {
            v2 = arg6.compress(this.f, this.g, ((OutputStream)v1_1));
        }
        catch(Throwable v5_1) {
            b.a(((Closeable)v1_1));
            v0.delete();
            throw v5_1;
        }

        b.a(((Closeable)v1_1));
        if((v2) && !v0.renameTo(v5)) {
            v2 = false;
        }

        if(!v2) {
            v0.delete();
        }

        arg6.recycle();
        return v2;
    }

    public boolean a(String arg6, InputStream arg7, com.d.a.c.b$a arg8) {
        boolean v7_1;
        BufferedOutputStream v2;
        File v6 = this.b(arg6);
        StringBuilder v1 = new StringBuilder();
        v1.append(v6.getAbsolutePath());
        v1.append(".tmp");
        File v0 = new File(v1.toString());
        try {
            v2 = new BufferedOutputStream(new FileOutputStream(v0), this.e);
        }
        catch(Throwable v8) {
            goto label_32;
        }

        try {
            v7_1 = b.a(arg7, ((OutputStream)v2), arg8, this.e);
        }
        catch(Throwable v7) {
            try {
                b.a(((Closeable)v2));
                throw v7;
            }
            catch(Throwable v8) {
            label_32:
                v7_1 = false;
                goto label_33;
            }
        }

        try {
            b.a(((Closeable)v2));
            if(!v7_1) {
                goto label_23;
            }

            goto label_20;
        }
        catch(Throwable v8) {
        }

    label_33:
        if((v7_1) && !v0.renameTo(v6)) {
            v7_1 = false;
        }

        if(!v7_1) {
            v0.delete();
        }

        throw v8;
    label_20:
        if(!v0.renameTo(v6)) {
            v7_1 = false;
        }

    label_23:
        if(!v7_1) {
            v0.delete();
        }

        return v7_1;
    }

    protected File b(String arg3) {
        arg3 = this.d.a(arg3);
        File v0 = this.b;
        if(!this.b.exists() && !this.b.mkdirs() && this.c != null && ((this.c.exists()) || (this.c.mkdirs()))) {
            v0 = this.c;
        }

        return new File(v0, arg3);
    }
}

