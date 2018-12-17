package com.persianswitch.a;

import com.persianswitch.a.a.l;
import com.persianswitch.b.d;
import java.nio.charset.Charset;

public abstract class y {
    public y() {
        super();
    }

    public static y a(t arg2, String arg3) {
        Charset v0 = l.c;
        if(arg2 != null) {
            v0 = arg2.a();
            if(v0 == null) {
                v0 = l.c;
                StringBuilder v1 = new StringBuilder();
                v1.append(arg2);
                v1.append("; charset=utf-8");
                arg2 = t.a(v1.toString());
            }
        }

        return y.a(arg2, arg3.getBytes(v0));
    }

    public static y a(t arg2, byte[] arg3) {
        return y.a(arg2, arg3, 0, arg3.length);
    }

    public static y a(t arg7, byte[] arg8, int arg9, int arg10) {
        if(arg8 != null) {
            l.a(((long)arg8.length), ((long)arg9), ((long)arg10));
            return new y(arg7, arg10, arg8, arg9) {
                public t a() {
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

    public abstract t a();

    public abstract void a(d arg1);

    public long b() {
        return -1;
    }
}

