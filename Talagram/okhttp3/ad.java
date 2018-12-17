package okhttp3;

import e.e;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public abstract class ad implements Closeable {
    final class a extends Reader {
        private final e a;
        private final Charset b;
        private boolean c;
        private Reader d;

        a(e arg1, Charset arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void close() {
            this.c = true;
            if(this.d != null) {
                this.d.close();
            }
            else {
                this.a.close();
            }
        }

        public int read(char[] arg4, int arg5, int arg6) {
            if(!this.c) {
                Reader v0 = this.d;
                if(v0 == null) {
                    InputStreamReader v1 = new InputStreamReader(this.a.g(), c.a(this.a, this.b));
                    this.d = ((Reader)v1);
                    InputStreamReader v0_1 = v1;
                }

                return v0.read(arg4, arg5, arg6);
            }

            throw new IOException("Stream closed");
        }
    }

    private Reader a;

    public ad() {
        super();
    }

    public static ad a(@Nullable v arg1, long arg2, e arg4) {
        if(arg4 != null) {
            return new ad(arg1, arg2, arg4) {
                @Nullable public v a() {
                    return this.a;
                }

                public long b() {
                    return this.b;
                }

                public e c() {
                    return this.c;
                }
            };
        }

        throw new NullPointerException("source == null");
    }

    @Nullable public abstract v a();

    public static ad a(@Nullable v arg3, byte[] arg4) {
        return ad.a(arg3, ((long)arg4.length), new e.c().b(arg4));
    }

    public abstract long b();

    public abstract e c();

    public void close() {
        c.a(this.c());
    }

    public final byte[] d() {
        byte[] v3;
        long v0 = this.b();
        if(v0 <= 2147483647) {
            e v2 = this.c();
            try {
                v3 = v2.r();
            }
            catch(Throwable v0_1) {
                c.a(((Closeable)v2));
                throw v0_1;
            }

            c.a(((Closeable)v2));
            if(v0 != -1) {
                if(v0 == (((long)v3.length))) {
                }
                else {
                    StringBuilder v4 = new StringBuilder();
                    v4.append("Content-Length (");
                    v4.append(v0);
                    v4.append(") and stream length (");
                    v4.append(v3.length);
                    v4.append(") disagree");
                    throw new IOException(v4.toString());
                }
            }

            return v3;
        }

        StringBuilder v3_1 = new StringBuilder();
        v3_1.append("Cannot buffer entire body for content length: ");
        v3_1.append(v0);
        throw new IOException(v3_1.toString());
    }

    public final Reader e() {
        a v0_1;
        Reader v0 = this.a;
        if(v0 != null) {
        }
        else {
            v0_1 = new a(this.c(), this.g());
            this.a = ((Reader)v0_1);
        }

        return ((Reader)v0_1);
    }

    public final String f() {
        String v1_1;
        e v0 = this.c();
        try {
            v1_1 = v0.a(c.a(v0, this.g()));
        }
        catch(Throwable v1) {
            c.a(((Closeable)v0));
            throw v1;
        }

        c.a(((Closeable)v0));
        return v1_1;
    }

    private Charset g() {
        v v0 = this.a();
        Charset v0_1 = v0 != null ? v0.a(c.e) : c.e;
        return v0_1;
    }
}

