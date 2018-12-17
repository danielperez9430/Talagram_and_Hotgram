package okhttp3;

import e.d;
import e.f;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public abstract class ab {
    public ab() {
        super();
    }

    public static ab a(@Nullable v arg2, byte[] arg3) {
        return ab.a(arg2, arg3, 0, arg3.length);
    }

    public abstract void a(d arg1);

    public static ab a(@Nullable v arg1, f arg2) {
        return new ab(arg1, arg2) {
            @Nullable public v a() {
                return this.a;
            }

            public void a(d arg2) {
                arg2.b(this.b);
            }

            public long b() {
                return ((long)this.b.g());
            }
        };
    }

    public static ab a(@Nullable v arg2, String arg3) {
        Charset v0 = c.e;
        if(arg2 != null) {
            v0 = arg2.b();
            if(v0 == null) {
                v0 = c.e;
                StringBuilder v1 = new StringBuilder();
                v1.append(arg2);
                v1.append("; charset=utf-8");
                arg2 = v.a(v1.toString());
            }
        }

        return ab.a(arg2, arg3.getBytes(v0));
    }

    public static ab a(@Nullable v arg7, byte[] arg8, int arg9, int arg10) {
        if(arg8 != null) {
            c.a(((long)arg8.length), ((long)arg9), ((long)arg10));
            return new ab(arg7, arg10, arg8, arg9) {
                @Nullable public v a() {
                    return this.a;
                }

                public void a(d arg4) {
                    arg4.c(this.c, this.d, this.b);
                }

                public long b() {
                    return ((long)this.b);
                }
            };
        }

        throw new NullPointerException("content == null");
    }

    @Nullable public abstract v a();

    public long b() {
        return -1;
    }
}

