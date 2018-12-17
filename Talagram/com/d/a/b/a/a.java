package com.d.a.b.a;

import java.io.InputStream;

public class a extends InputStream {
    private final InputStream a;
    private final int b;

    public a(InputStream arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public int available() {
        return this.b;
    }

    public void close() {
        this.a.close();
    }

    public void mark(int arg2) {
        this.a.mark(arg2);
    }

    public boolean markSupported() {
        return this.a.markSupported();
    }

    public int read() {
        return this.a.read();
    }

    public int read(byte[] arg2) {
        return this.a.read(arg2);
    }

    public int read(byte[] arg2, int arg3, int arg4) {
        return this.a.read(arg2, arg3, arg4);
    }

    public void reset() {
        this.a.reset();
    }

    public long skip(long arg2) {
        return this.a.skip(arg2);
    }
}

