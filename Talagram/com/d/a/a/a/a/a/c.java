package com.d.a.a.a.a.a;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class c implements Closeable {
    private final InputStream a;
    private final Charset b;
    private byte[] c;
    private int d;
    private int e;

    public c(InputStream arg2, Charset arg3) {
        this(arg2, 8192, arg3);
    }

    public c(InputStream arg2, int arg3, Charset arg4) {
        super();
        if(arg2 != null && arg4 != null) {
            if(arg3 < 0) {
                throw new IllegalArgumentException("capacity <= 0");
            }
            else if(arg4.equals(d.a)) {
                this.a = arg2;
                this.b = arg4;
                this.c = new byte[arg3];
                return;
            }
            else {
                throw new IllegalArgumentException("Unsupported encoding");
            }
        }

        throw new NullPointerException();
    }

    public String a() {
        InputStream v0 = this.a;
        __monitor_enter(v0);
        try {
            if(this.c != null) {
                if(this.d >= this.e) {
                    this.b();
                }

                int v1_1;
                for(v1_1 = this.d; true; ++v1_1) {
                    int v3 = 10;
                    if(v1_1 == this.e) {
                        break;
                    }

                    if(this.c[v1_1] == v3) {
                        if(v1_1 != this.d) {
                            v3 = v1_1 - 1;
                            if(this.c[v3] == 13) {
                            }
                            else {
                                goto label_23;
                            }
                        }
                        else {
                        label_23:
                            v3 = v1_1;
                        }

                        String v2 = new String(this.c, this.d, v3 - this.d, this.b.name());
                        this.d = v1_1 + 1;
                        __monitor_exit(v0);
                        return v2;
                    }
                }

                com.d.a.a.a.a.a.c$1 v1_2 = new ByteArrayOutputStream(this.e - this.d + 80) {
                    public String toString() {
                        int v0 = this.count <= 0 || this.buf[this.count - 1] != 13 ? this.count : this.count - 1;
                        try {
                            return new String(this.buf, 0, v0, c.a(this.a).name());
                        }
                        catch(UnsupportedEncodingException v0_1) {
                            throw new AssertionError(v0_1);
                        }
                    }
                };
            label_44:
                ((ByteArrayOutputStream)v1_2).write(this.c, this.d, this.e - this.d);
                this.e = -1;
                this.b();
                int v2_1;
                for(v2_1 = this.d; true; ++v2_1) {
                    if(v2_1 == this.e) {
                        goto label_44;
                    }

                    if(this.c[v2_1] == v3) {
                        if(v2_1 != this.d) {
                            ((ByteArrayOutputStream)v1_2).write(this.c, this.d, v2_1 - this.d);
                        }

                        this.d = v2_1 + 1;
                        __monitor_exit(v0);
                        return ((ByteArrayOutputStream)v1_2).toString();
                    }
                }
            }

            throw new IOException("LineReader is closed");
        label_78:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_78;
        }

        throw v1;
    }

    static Charset a(c arg0) {
        return arg0.b;
    }

    private void b() {
        int v0 = this.a.read(this.c, 0, this.c.length);
        if(v0 != -1) {
            this.d = 0;
            this.e = v0;
            return;
        }

        throw new EOFException();
    }

    public void close() {
        InputStream v0 = this.a;
        __monitor_enter(v0);
        try {
            if(this.c != null) {
                this.c = null;
                this.a.close();
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_11;
        }

        throw v1;
    }
}

