package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class ey implements fc {
    public class a extends IOException {
        public a(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private final fb a;
    private RandomAccessFile b;
    private String c;
    private long d;
    private boolean e;

    public ey(fb arg1) {
        super();
        this.a = arg1;
    }

    public ey() {
        this(null);
    }

    public int a(byte[] arg6, int arg7, int arg8) {
        int v6_1;
        if(this.d == 0) {
            return -1;
        }

        try {
            v6_1 = this.b.read(arg6, arg7, ((int)Math.min(this.d, ((long)arg8))));
            if(v6_1 <= 0) {
                return v6_1;
            }
        }
        catch(IOException v6) {
            throw new a(v6);
        }

        this.d -= ((long)v6_1);
        if(this.a != null) {
            this.a.a(v6_1);
        }

        return v6_1;
    }

    public long a(eu arg6) {
        try {
            this.c = arg6.a.toString();
            this.b = new RandomAccessFile(arg6.a.getPath(), "r");
            this.b.seek(arg6.d);
            long v0 = arg6.e == -1 ? this.b.length() - arg6.d : arg6.e;
            this.d = v0;
            if(this.d < 0) {
                goto label_34;
            }
        }
        catch(IOException v6) {
            goto label_40;
        }

        this.e = true;
        if(this.a != null) {
            this.a.a();
        }

        return this.d;
        try {
        label_34:
            throw new EOFException();
        }
        catch(IOException v6) {
        label_40:
            throw new a(v6);
        }
    }

    public void a() {
        String v0 = null;
        this.c = v0;
        if(this.b != null) {
            try {
                this.b.close();
            }
            catch(Throwable v2) {
            }
            catch(IOException v2_1) {
                try {
                    throw new a(v2_1);
                }
                catch(Throwable v2) {
                    this.b = ((RandomAccessFile)v0);
                    if(this.e) {
                        this.e = false;
                        if(this.a != null) {
                            this.a.b();
                        }
                    }

                    throw v2;
                }
            }

            this.b = ((RandomAccessFile)v0);
            if(!this.e) {
                return;
            }

            this.e = false;
            if(this.a == null) {
                return;
            }

            this.a.b();
        }
    }
}

