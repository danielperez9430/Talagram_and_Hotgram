package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class er implements fc {
    public final class a extends IOException {
        public a(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private final AssetManager a;
    private final fb b;
    private String c;
    private InputStream d;
    private long e;
    private boolean f;

    public er(Context arg1, fb arg2) {
        super();
        this.a = arg1.getAssets();
        this.b = arg2;
    }

    public int a(byte[] arg7, int arg8, int arg9) {
        int v7_1;
        if(this.e == 0) {
            return -1;
        }

        try {
            long v2 = -1;
            if(this.e == v2) {
            }
            else {
                arg9 = ((int)Math.min(this.e, ((long)arg9)));
            }

            v7_1 = this.d.read(arg7, arg8, arg9);
            if(v7_1 <= 0) {
                return v7_1;
            }
        }
        catch(IOException v7) {
            throw new a(v7);
        }

        if(this.e != v2) {
            this.e -= ((long)v7_1);
        }

        if(this.b != null) {
            this.b.a(v7_1);
        }

        return v7_1;
    }

    public long a(eu arg8) {
        try {
            this.c = arg8.a.toString();
            String v0 = arg8.a.getPath();
            if(v0.startsWith("/android_asset/")) {
                v0 = v0.substring(15);
            }
            else if(v0.startsWith("/")) {
                v0 = v0.substring(1);
            }

            this.c = arg8.a.toString();
            this.d = this.a.open(v0, 1);
            if(this.d.skip(arg8.d) < arg8.d) {
                goto label_48;
            }

            long v3 = -1;
            if(arg8.e != v3) {
                this.e = arg8.e;
            }
            else {
                this.e = ((long)this.d.available());
                if(this.e == 2147483647) {
                    this.e = v3;
                }
            }
        }
        catch(IOException v8) {
            goto label_54;
        }

        this.f = true;
        if(this.b != null) {
            this.b.a();
        }

        return this.e;
        try {
        label_48:
            throw new EOFException();
        }
        catch(IOException v8) {
        label_54:
            throw new a(v8);
        }
    }

    public void a() {
        String v0 = null;
        this.c = v0;
        if(this.d != null) {
            try {
                this.d.close();
            }
            catch(Throwable v2) {
            }
            catch(IOException v2_1) {
                try {
                    throw new a(v2_1);
                }
                catch(Throwable v2) {
                    this.d = ((InputStream)v0);
                    if(this.f) {
                        this.f = false;
                        if(this.b != null) {
                            this.b.b();
                        }
                    }

                    throw v2;
                }
            }

            this.d = ((InputStream)v0);
            if(!this.f) {
                return;
            }

            this.f = false;
            if(this.b == null) {
                return;
            }

            this.b.b();
        }
    }
}

