package com.e.a;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

final class i extends InputStream {
    private final InputStream a;
    private long b;
    private long c;
    private long d;
    private long e;

    public i(InputStream arg2) {
        this(arg2, 4096);
    }

    public i(InputStream arg3, int arg4) {
        BufferedInputStream v3;
        super();
        this.e = -1;
        if(!arg3.markSupported()) {
            v3 = new BufferedInputStream(arg3, arg4);
        }

        this.a = ((InputStream)v3);
    }

    public long a(int arg5) {
        long v0 = this.b + (((long)arg5));
        if(this.d < v0) {
            this.b(v0);
        }

        return this.b;
    }

    public void a(long arg6) {
        if(this.b <= this.d && arg6 >= this.c) {
            this.a.reset();
            this.a(this.c, arg6);
            this.b = arg6;
            return;
        }

        throw new IOException("Cannot reset");
    }

    private void a(long arg6, long arg8) {
        while(arg6 < arg8) {
            long v0 = this.a.skip(arg8 - arg6);
            if(v0 == 0) {
                if(this.read() == -1) {
                    return;
                }
                else {
                    v0 = 1;
                }
            }

            arg6 += v0;
        }
    }

    public int available() {
        return this.a.available();
    }

    private void b(long arg6) {
        try {
            if(this.c >= this.b || this.b > this.d) {
                this.c = this.b;
                this.a.mark(((int)(arg6 - this.b)));
            }
            else {
                this.a.reset();
                this.a.mark(((int)(arg6 - this.c)));
                this.a(this.c, this.b);
            }

            this.d = arg6;
            return;
        }
        catch(IOException v6) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Unable to mark: ");
            v0.append(v6);
            throw new IllegalStateException(v0.toString());
        }
    }

    public void close() {
        this.a.close();
    }

    public void mark(int arg3) {
        this.e = this.a(arg3);
    }

    public boolean markSupported() {
        return this.a.markSupported();
    }

    public int read() {
        int v0 = this.a.read();
        if(v0 != -1) {
            ++this.b;
        }

        return v0;
    }

    public int read(byte[] arg5) {
        int v5 = this.a.read(arg5);
        if(v5 != -1) {
            this.b += ((long)v5);
        }

        return v5;
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        int v3 = this.a.read(arg3, arg4, arg5);
        if(v3 != -1) {
            this.b += ((long)v3);
        }

        return v3;
    }

    public void reset() {
        this.a(this.e);
    }

    public long skip(long arg3) {
        arg3 = this.a.skip(arg3);
        this.b += arg3;
        return arg3;
    }
}

