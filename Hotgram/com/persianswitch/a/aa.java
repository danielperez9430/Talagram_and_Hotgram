package com.persianswitch.a;

import com.persianswitch.a.a.l;
import com.persianswitch.b.e;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public abstract class aa implements Closeable {
    public aa() {
        super();
    }

    public abstract t a();

    public abstract long b();

    public final InputStream c() {
        return this.d().f();
    }

    public void close() {
        l.a(this.d());
    }

    public abstract e d();

    public final byte[] e() {
        byte[] v3;
        long v0 = this.b();
        if(v0 <= 2147483647) {
            e v2 = this.d();
            try {
                v3 = v2.q();
            }
            catch(Throwable v0_1) {
                l.a(((Closeable)v2));
                throw v0_1;
            }

            l.a(((Closeable)v2));
            if(v0 != -1) {
                if(v0 == (((long)v3.length))) {
                }
                else {
                    throw new IOException("Content-Length and stream length disagree");
                }
            }

            return v3;
        }

        StringBuilder v3_1 = new StringBuilder();
        v3_1.append("Cannot buffer entire body for content length: ");
        v3_1.append(v0);
        throw new IOException(v3_1.toString());
    }

    public final String f() {
        return new String(this.e(), this.g().name());
    }

    private Charset g() {
        t v0 = this.a();
        Charset v0_1 = v0 != null ? v0.a(l.c) : l.c;
        return v0_1;
    }
}

