package e;

import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

public final class c implements d, e, Cloneable, ByteChannel {
    @Nullable o a;
    long b;
    private static final byte[] c;

    static {
        c.c = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    }

    public c() {
        super();
    }

    public c a(String arg8, int arg9, int arg10) {
        int v2_1;
        int v6;
        int v5;
        int v4;
        byte[] v3;
        o v2;
        if(arg8 == null) {
            goto label_138;
        }

        if(arg9 < 0) {
            goto label_129;
        }

        if(arg10 < arg9) {
            goto label_117;
        }

        if(arg10 > arg8.length()) {
            goto label_104;
        }

    label_5:
        while(arg9 < arg10) {
            int v0 = arg8.charAt(arg9);
            int v1 = 128;
            if(v0 < v1) {
                v2 = this.e(1);
                v3 = v2.a;
                v4 = v2.c - arg9;
                v5 = Math.min(arg10, 8192 - v4);
                v6 = arg9 + 1;
                v3[arg9 + v4] = ((byte)v0);
                goto label_20;
            }

            if(v0 < 2048) {
                v2_1 = v0 >> 6 | 192;
            }
            else {
                int v3_1 = 63;
                if(v0 >= 55296) {
                    v2_1 = 57343;
                    if(v0 > v2_1) {
                    }
                    else {
                        v4 = arg9 + 1;
                        v5 = v4 < arg10 ? arg8.charAt(v4) : 0;
                        if(v0 <= 56319 && v5 >= 56320) {
                            if(v5 > v2_1) {
                            }
                            else {
                                v0 = ((v0 & -55297) << 10 | -56321 & v5) + 65536;
                                this.b(v0 >> 18 | 240);
                                this.b(v0 >> 12 & v3_1 | v1);
                                this.b(v0 >> 6 & v3_1 | v1);
                                this.b(v0 & v3_1 | v1);
                                arg9 += 2;
                                continue;
                            }
                        }

                        this.b(v3_1);
                        arg9 = v4;
                        continue;
                    }
                }

                this.b(v0 >> 12 | 224);
                v2_1 = v0 >> 6 & v3_1 | v1;
            }

            this.b(v2_1);
            this.b(v0 & 63 | v1);
            ++arg9;
        }

        return this;
    label_20:
        while(v6 < v5) {
            arg9 = arg8.charAt(v6);
            if(arg9 >= v1) {
            }
            else {
                v3[v6 + v4] = ((byte)arg9);
                ++v6;
                continue;
            }

            break;
        }

        v4 = v4 + v6 - v2.c;
        v2.c += v4;
        this.b += ((long)v4);
        arg9 = v6;
        goto label_5;
    label_104:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("endIndex > string.length: ");
        v0_1.append(arg10);
        v0_1.append(" > ");
        v0_1.append(arg8.length());
        throw new IllegalArgumentException(v0_1.toString());
    label_117:
        v0_1 = new StringBuilder();
        v0_1.append("endIndex < beginIndex: ");
        v0_1.append(arg10);
        v0_1.append(" < ");
        v0_1.append(arg9);
        throw new IllegalArgumentException(v0_1.toString());
    label_129:
        StringBuilder v10 = new StringBuilder();
        v10.append("beginIndex < 0: ");
        v10.append(arg9);
        throw new IllegalArgumentException(v10.toString());
    label_138:
        throw new IllegalArgumentException("string == null");
    }

    public c a(int arg4) {
        int v1;
        int v0 = 128;
        if(arg4 >= v0) {
            int v2 = 63;
            if(arg4 < 2048) {
                v1 = arg4 >> 6 | 192;
            }
            else {
                if(arg4 < 65536) {
                    if(arg4 >= 55296 && arg4 <= 57343) {
                        this.b(v2);
                        return this;
                    }

                    v1 = arg4 >> 12 | 224;
                }
                else {
                    if(arg4 > 1114111) {
                        goto label_38;
                    }

                    this.b(arg4 >> 18 | 240);
                    v1 = arg4 >> 12 & v2 | v0;
                }

                this.b(v1);
                v1 = arg4 >> 6 & v2 | v0;
            }

            this.b(v1);
            arg4 = arg4 & v2 | v0;
            goto label_2;
        }
        else {
        label_2:
            this.b(arg4);
        }

        return this;
    label_38:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Unexpected code point: ");
        v1_1.append(Integer.toHexString(arg4));
        throw new IllegalArgumentException(v1_1.toString());
    }

    public c a(String arg3) {
        return this.a(arg3, 0, arg3.length());
    }

    public c a(String arg2, int arg3, int arg4, Charset arg5) {
        StringBuilder v5;
        if(arg2 != null) {
            if(arg3 >= 0) {
                if(arg4 >= arg3) {
                    if(arg4 <= arg2.length()) {
                        if(arg5 != null) {
                            if(arg5.equals(u.a)) {
                                return this.a(arg2, arg3, arg4);
                            }

                            byte[] v2 = arg2.substring(arg3, arg4).getBytes(arg5);
                            return this.b(v2, 0, v2.length);
                        }

                        throw new IllegalArgumentException("charset == null");
                    }

                    v5 = new StringBuilder();
                    v5.append("endIndex > string.length: ");
                    v5.append(arg4);
                    v5.append(" > ");
                    v5.append(arg2.length());
                    throw new IllegalArgumentException(v5.toString());
                }

                v5 = new StringBuilder();
                v5.append("endIndex < beginIndex: ");
                v5.append(arg4);
                v5.append(" < ");
                v5.append(arg3);
                throw new IllegalArgumentException(v5.toString());
            }

            StringBuilder v4 = new StringBuilder();
            v4.append("beginIndex < 0: ");
            v4.append(arg3);
            throw new IllegalAccessError(v4.toString());
        }

        throw new IllegalArgumentException("string == null");
    }

    public int a(byte[] arg8, int arg9, int arg10) {
        u.a(((long)arg8.length), ((long)arg9), ((long)arg10));
        o v0 = this.a;
        if(v0 == null) {
            return -1;
        }

        arg10 = Math.min(arg10, v0.c - v0.b);
        System.arraycopy(v0.a, v0.b, arg8, arg9, arg10);
        v0.b += arg10;
        this.b -= ((long)arg10);
        if(v0.b == v0.c) {
            this.a = v0.b();
            p.a(v0);
        }

        return arg10;
    }

    public long a(byte arg7) {
        return this.a(arg7, 0, 9223372036854775807L);
    }

    public long a(byte arg11, long arg12, long arg14) {
        long v0 = 0;
        if(arg12 >= v0 && arg14 >= arg12) {
            if(arg14 > this.b) {
                arg14 = this.b;
            }

            long v3 = -1;
            if(Long.compare(arg12, arg14) == 0) {
                return v3;
            }

            o v2 = this.a;
            if(v2 == null) {
                return v3;
            }

            if(this.b - arg12 < arg12) {
                for(v0 = this.b; v0 > arg12; v0 -= ((long)(v2.c - v2.b))) {
                    v2 = v2.g;
                }
            }
            else {
                while(true) {
                    long v5 = (((long)(v2.c - v2.b))) + v0;
                    if(v5 < arg12) {
                        v2 = v2.f;
                        v0 = v5;
                        continue;
                    }

                    break;
                }
            }

            while(v0 < arg14) {
                byte[] v5_1 = v2.a;
                int v6 = ((int)Math.min(((long)v2.c), (((long)v2.b)) + arg14 - v0));
                int v12;
                for(v12 = ((int)((((long)v2.b)) + arg12 - v0)); v12 < v6; ++v12) {
                    if(v5_1[v12] == arg11) {
                        return (((long)(v12 - v2.b))) + v0;
                    }
                }

                arg12 = (((long)(v2.c - v2.b))) + v0;
                v2 = v2.f;
                v0 = arg12;
            }

            return v3;
        }

        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.b), Long.valueOf(arg12), Long.valueOf(arg14)));
    }

    public long a(c arg6, long arg7) {
        if(arg6 != null) {
            long v0 = 0;
            if(arg7 >= v0) {
                if(this.b == v0) {
                    return -1;
                }

                if(arg7 > this.b) {
                    arg7 = this.b;
                }

                arg6.a_(this, arg7);
                return arg7;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("byteCount < 0: ");
            v0_1.append(arg7);
            throw new IllegalArgumentException(v0_1.toString());
        }

        throw new IllegalArgumentException("sink == null");
    }

    public long a(r arg6) {
        long v0 = this.b;
        if(v0 > 0) {
            arg6.a_(this, v0);
        }

        return v0;
    }

    public long a(s arg8) {
        if(arg8 != null) {
            long v0;
            for(v0 = 0; true; v0 += v2) {
                long v2 = arg8.a(this, 8192);
                if(v2 == -1) {
                    return v0;
                }
            }

            return v0;
        }

        throw new IllegalArgumentException("source == null");
    }

    public c a(c arg7, long arg8, long arg10) {
        if(arg7 != null) {
            u.a(this.b, arg8, arg10);
            long v0 = 0;
            if(arg10 == v0) {
                return this;
            }

            arg7.b += arg10;
            o v2;
            for(v2 = this.a; arg8 >= (((long)(v2.c - v2.b))); v2 = v2.f) {
                arg8 -= ((long)(v2.c - v2.b));
            }

            while(arg10 > v0) {
                o v3 = v2.a();
                v3.b = ((int)((((long)v3.b)) + arg8));
                v3.c = Math.min(v3.b + (((int)arg10)), v3.c);
                if(arg7.a == null) {
                    v3.g = v3;
                    v3.f = v3;
                    arg7.a = v3;
                }
                else {
                    arg7.a.g.a(v3);
                }

                arg10 -= ((long)(v3.c - v3.b));
                v2 = v2.f;
                arg8 = v0;
            }

            return this;
        }

        throw new IllegalArgumentException("out == null");
    }

    public c a(f arg2) {
        if(arg2 != null) {
            arg2.a(this);
            return this;
        }

        throw new IllegalArgumentException("byteString == null");
    }

    public t a() {
        return t.c;
    }

    public String a(long arg7, Charset arg9) {
        u.a(this.b, 0, arg7);
        if(arg9 != null) {
            if(arg7 <= 2147483647) {
                if(arg7 == 0) {
                    return "";
                }

                o v0 = this.a;
                if((((long)v0.b)) + arg7 > (((long)v0.c))) {
                    return new String(this.g(arg7), arg9);
                }

                String v1 = new String(v0.a, v0.b, ((int)arg7), arg9);
                v0.b = ((int)((((long)v0.b)) + arg7));
                this.b -= arg7;
                if(v0.b == v0.c) {
                    this.a = v0.b();
                    p.a(v0);
                }

                return v1;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("byteCount > Integer.MAX_VALUE: ");
            v0_1.append(arg7);
            throw new IllegalArgumentException(v0_1.toString());
        }

        throw new IllegalArgumentException("charset == null");
    }

    public String a(Charset arg3) {
        try {
            return this.a(this.b, arg3);
        }
        catch(EOFException v3) {
            throw new AssertionError(v3);
        }
    }

    public void a(long arg4) {
        if(this.b >= arg4) {
            return;
        }

        throw new EOFException();
    }

    public void a(byte[] arg4) {
        int v0;
        for(v0 = 0; true; v0 += v1) {
            if(v0 >= arg4.length) {
                return;
            }

            int v1 = this.a(arg4, v0, arg4.length - v0);
            if(v1 == -1) {
                break;
            }
        }

        throw new EOFException();
    }

    public boolean a(long arg7, f arg9) {
        return this.a(arg7, arg9, 0, arg9.g());
    }

    public boolean a(long arg7, f arg9, int arg10, int arg11) {
        // Method was not decompiled
    }

    public void a_(c arg7, long arg8) {
        long v1;
        o v0;
        if(arg7 != null) {
            if(arg7 != this) {
                u.a(arg7.b, 0, arg8);
                while(arg8 > 0) {
                    if(arg8 < (((long)(arg7.a.c - arg7.a.b)))) {
                        v0 = this.a != null ? this.a.g : null;
                        if(v0 != null && (v0.e)) {
                            v1 = (((long)v0.c)) + arg8;
                            int v3 = v0.d ? 0 : v0.b;
                            if(v1 - (((long)v3)) > 8192) {
                                goto label_46;
                            }

                            arg7.a.a(v0, ((int)arg8));
                            arg7.b -= arg8;
                            this.b += arg8;
                            return;
                        }

                    label_46:
                        arg7.a = arg7.a.a(((int)arg8));
                    }

                    v0 = arg7.a;
                    v1 = ((long)(v0.c - v0.b));
                    arg7.a = v0.b();
                    if(this.a == null) {
                        this.a = v0;
                        v0 = this.a;
                        o v3_1 = this.a;
                        o v4 = this.a;
                        v3_1.g = v4;
                        v0.f = v4;
                    }
                    else {
                        this.a.g.a(v0).c();
                    }

                    arg7.b -= v1;
                    this.b += v1;
                    arg8 -= v1;
                }

                return;
            }

            throw new IllegalArgumentException("source == this");
        }

        throw new IllegalArgumentException("source == null");
    }

    public c b(int arg5) {
        o v0 = this.e(1);
        byte[] v1 = v0.a;
        int v2 = v0.c;
        v0.c = v2 + 1;
        v1[v2] = ((byte)arg5);
        ++this.b;
        return this;
    }

    public c b(byte[] arg3) {
        if(arg3 != null) {
            return this.b(arg3, 0, arg3.length);
        }

        throw new IllegalArgumentException("source == null");
    }

    public long b() {
        return this.b;
    }

    public c b(byte[] arg10, int arg11, int arg12) {
        if(arg10 != null) {
            long v7 = ((long)arg12);
            u.a(((long)arg10.length), ((long)arg11), v7);
            arg12 += arg11;
            while(arg11 < arg12) {
                o v0 = this.e(1);
                int v1 = Math.min(arg12 - arg11, 8192 - v0.c);
                System.arraycopy(arg10, arg11, v0.a, v0.c, v1);
                arg11 += v1;
                v0.c += v1;
            }

            this.b += v7;
            return this;
        }

        throw new IllegalArgumentException("source == null");
    }

    public byte b(long arg7) {
        u.a(this.b, arg7, 1);
        if(this.b - arg7 > arg7) {
            o v0;
            for(v0 = this.a; true; v0 = v0.f) {
                long v1 = ((long)(v0.c - v0.b));
                if(arg7 < v1) {
                    break;
                }

                arg7 -= v1;
            }

            return v0.a[v0.b + (((int)arg7))];
        }

        arg7 -= this.b;
        v0 = this.a;
        do {
            v0 = v0.g;
            arg7 += ((long)(v0.c - v0.b));
        }
        while(arg7 < 0);

        return v0.a[v0.b + (((int)arg7))];
    }

    public d b(f arg1) {
        return this.a(arg1);
    }

    public d b(String arg1) {
        return this.a(arg1);
    }

    public c c() {
        return this;
    }

    public c c(int arg6) {
        o v0 = this.e(2);
        byte[] v1 = v0.a;
        int v3 = v0.c + 1;
        v1[v0.c] = ((byte)(arg6 >>> 8 & 255));
        v1[v3] = ((byte)(arg6 & 255));
        v0.c = v3 + 1;
        this.b += 2;
        return this;
    }

    public d c(byte[] arg1) {
        return this.b(arg1);
    }

    public d c(byte[] arg1, int arg2, int arg3) {
        return this.b(arg1, arg2, arg3);
    }

    public f c(long arg2) {
        return new f(this.g(arg2));
    }

    public Object clone() {
        return this.t();
    }

    public void close() {
    }

    public c d(int arg6) {
        o v0 = this.e(4);
        byte[] v1 = v0.a;
        int v3 = v0.c + 1;
        v1[v0.c] = ((byte)(arg6 >>> 24 & 255));
        int v2 = v3 + 1;
        v1[v3] = ((byte)(arg6 >>> 16 & 255));
        v3 = v2 + 1;
        v1[v2] = ((byte)(arg6 >>> 8 & 255));
        v1[v3] = ((byte)(arg6 & 255));
        v0.c = v3 + 1;
        this.b += 4;
        return this;
    }

    public OutputStream d() {
        return new OutputStream() {
            public void close() {
            }

            public void flush() {
            }

            public String toString() {
                return this.a + ".outputStream()";
            }

            public void write(int arg2) {
                this.a.b(((byte)arg2));
            }

            public void write(byte[] arg2, int arg3, int arg4) {
                this.a.b(arg2, arg3, arg4);
            }
        };
    }

    public String d(long arg2) {
        return this.a(arg2, u.a);
    }

    o e(int arg4) {
        o v1;
        if(arg4 >= 1) {
            int v0 = 8192;
            if(arg4 <= v0) {
                if(this.a == null) {
                    this.a = p.a();
                    o v4 = this.a;
                    o v0_1 = this.a;
                    v1 = this.a;
                    v0_1.g = v1;
                    v4.f = v1;
                    return v1;
                }
                else {
                    v1 = this.a.g;
                    if(v1.c + arg4 > v0 || !v1.e) {
                        v1 = v1.a(p.a());
                    }

                    return v1;
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public c e() {
        return this;
    }

    public String e(long arg12) {
        StringBuilder v1;
        if(arg12 >= 0) {
            long v0 = 9223372036854775807L;
            long v3 = 1;
            if(Long.compare(arg12, v0) == 0) {
            }
            else {
                v0 = arg12 + v3;
            }

            long v5 = this.a(10, 0, v0);
            if(v5 != -1) {
                return this.f(v5);
            }

            if(v0 < this.b() && this.b(v0 - v3) == 13 && this.b(v0) == 10) {
                return this.f(v0);
            }

            c v6 = new c();
            this.a(v6, 0, Math.min(32, this.b()));
            v1 = new StringBuilder();
            v1.append("\\n not found: limit=");
            v1.append(Math.min(this.b(), arg12));
            v1.append(" content=");
            v1.append(v6.o().e());
            v1.append('…');
            throw new EOFException(v1.toString());
        }

        v1 = new StringBuilder();
        v1.append("limit < 0: ");
        v1.append(arg12);
        throw new IllegalArgumentException(v1.toString());
    }

    public boolean equals(Object arg14) {
        if(this == (((c)arg14))) {
            return 1;
        }

        if(!(arg14 instanceof c)) {
            return 0;
        }

        if(this.b != ((c)arg14).b) {
            return 0;
        }

        long v5 = 0;
        if(this.b == v5) {
            return 1;
        }

        o v1 = this.a;
        o v14 = ((c)arg14).a;
        int v3 = v1.b;
        int v4 = v14.b;
        while(v5 < this.b) {
            long v7 = ((long)Math.min(v1.c - v3, v14.c - v4));
            int v9 = v4;
            v4 = v3;
            v3 = 0;
            while((((long)v3)) < v7) {
                int v11 = v4 + 1;
                int v12 = v9 + 1;
                if(v1.a[v4] != v14.a[v9]) {
                    return 0;
                }

                ++v3;
                v4 = v11;
                v9 = v12;
            }

            if(v4 == v1.c) {
                v1 = v1.f;
                v3 = v1.b;
            }
            else {
                v3 = v4;
            }

            if(v9 == v14.c) {
                v14 = v14.f;
                v4 = v14.b;
            }
            else {
                v4 = v9;
            }

            v5 += v7;
        }

        return 1;
    }

    public boolean f() {
        boolean v0 = this.b == 0 ? true : false;
        return v0;
    }

    String f(long arg7) {
        // Method was not decompiled
    }

    public f f(int arg2) {
        if(arg2 == 0) {
            return f.b;
        }

        return new q(this, arg2);
    }

    public void flush() {
    }

    public byte[] g(long arg7) {
        u.a(this.b, 0, arg7);
        if(arg7 <= 2147483647) {
            byte[] v7 = new byte[((int)arg7)];
            this.a(v7);
            return v7;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("byteCount > Integer.MAX_VALUE: ");
        v1.append(arg7);
        throw new IllegalArgumentException(v1.toString());
    }

    public d g(int arg1) {
        return this.d(arg1);
    }

    public InputStream g() {
        return new InputStream() {
            public int available() {
                return ((int)Math.min(this.a.b, 2147483647));
            }

            public void close() {
            }

            public int read() {
                if(this.a.b > 0) {
                    return this.a.i() & 255;
                }

                return -1;
            }

            public int read(byte[] arg2, int arg3, int arg4) {
                return this.a.a(arg2, arg3, arg4);
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public long h() {
        long v0 = this.b;
        long v2 = 0;
        if(v0 == v2) {
            return v2;
        }

        o v2_1 = this.a.g;
        if(v2_1.c < 8192 && (v2_1.e)) {
            v0 -= ((long)(v2_1.c - v2_1.b));
        }

        return v0;
    }

    public d h(int arg1) {
        return this.c(arg1);
    }

    public void h(long arg6) {
        while(true) {
            if(arg6 <= 0) {
                return;
            }

            if(this.a == null) {
                break;
            }

            int v0 = ((int)Math.min(arg6, ((long)(this.a.c - this.a.b))));
            long v3 = ((long)v0);
            this.b -= v3;
            arg6 -= v3;
            this.a.b += v0;
            if(this.a.b != this.a.c) {
                continue;
            }

            o v0_1 = this.a;
            this.a = v0_1.b();
            p.a(v0_1);
        }

        throw new EOFException();
    }

    public int hashCode() {
        o v0 = this.a;
        if(v0 == null) {
            return 0;
        }

        int v1 = 1;
        do {
            int v2 = v0.b;
            int v3 = v0.c;
            while(v2 < v3) {
                v1 = v1 * 31 + v0.a[v2];
                ++v2;
            }

            v0 = v0.f;
        }
        while(v0 != this.a);

        return v1;
    }

    public byte i() {
        if(this.b != 0) {
            o v0 = this.a;
            int v1 = v0.b;
            int v2 = v0.c;
            int v4 = v1 + 1;
            byte v1_1 = v0.a[v1];
            --this.b;
            if(v4 == v2) {
                this.a = v0.b();
                p.a(v0);
            }
            else {
                v0.b = v4;
            }

            return v1_1;
        }

        throw new IllegalStateException("size == 0");
    }

    public c i(long arg12) {
        // Method was not decompiled
    }

    public d i(int arg1) {
        return this.b(arg1);
    }

    public boolean isOpen() {
        return 1;
    }

    public c j(long arg10) {
        if(arg10 == 0) {
            return this.b(48);
        }

        int v1 = 4;
        int v0 = Long.numberOfTrailingZeros(Long.highestOneBit(arg10)) / v1 + 1;
        o v2 = this.e(v0);
        byte[] v3 = v2.a;
        int v4 = v2.c + v0 - 1;
        int v5 = v2.c;
        while(v4 >= v5) {
            v3[v4] = c.c[((int)(15 & arg10))];
            arg10 >>>= v1;
            --v4;
        }

        v2.c += v0;
        this.b += ((long)v0);
        return this;
    }

    public short j() {
        long v2 = 2;
        if(this.b >= v2) {
            o v0 = this.a;
            int v1 = v0.b;
            int v4 = v0.c;
            if(v4 - v1 < 2) {
                return ((short)((this.i() & 255) << 8 | this.i() & 255));
            }

            int v6 = v1 + 1;
            int v7 = v6 + 1;
            v1 = (v0.a[v1] & 255) << 8 | v0.a[v6] & 255;
            this.b -= v2;
            if(v7 == v4) {
                this.a = v0.b();
                p.a(v0);
            }
            else {
                v0.b = v7;
            }

            return ((short)v1);
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("size < 2: ");
        v1_1.append(this.b);
        throw new IllegalStateException(v1_1.toString());
    }

    public int k() {
        long v2 = 4;
        if(this.b >= v2) {
            o v0 = this.a;
            int v1 = v0.b;
            int v4 = v0.c;
            if(v4 - v1 < 4) {
                return (this.i() & 255) << 24 | (this.i() & 255) << 16 | (this.i() & 255) << 8 | this.i() & 255;
            }

            byte[] v5 = v0.a;
            int v6 = v1 + 1;
            int v7 = v6 + 1;
            v1 = (v5[v1] & 255) << 24 | (v5[v6] & 255) << 16;
            v6 = v7 + 1;
            v1 |= (v5[v7] & 255) << 8;
            v7 = v6 + 1;
            v1 |= v5[v6] & 255;
            this.b -= v2;
            if(v7 == v4) {
                this.a = v0.b();
                p.a(v0);
            }
            else {
                v0.b = v7;
            }

            return v1;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("size < 4: ");
        v1_1.append(this.b);
        throw new IllegalStateException(v1_1.toString());
    }

    public d k(long arg1) {
        return this.j(arg1);
    }

    public d l(long arg1) {
        return this.i(arg1);
    }

    public short l() {
        return u.a(this.j());
    }

    public int m() {
        return u.a(this.k());
    }

    public long n() {
        int v11;
        int v10;
        long v2 = 0;
        if(this.b != v2) {
            int v0 = 0;
            long v4 = v2;
            int v1 = 0;
            do {
                o v6 = this.a;
                byte[] v7 = v6.a;
                int v8 = v6.b;
                int v9 = v6.c;
                while(true) {
                    if(v8 < v9) {
                        v10 = v7[v8];
                        if(v10 < 48 || v10 > 57) {
                            if(v10 < 97 || v10 > 102) {
                                if(v10 >= 65 && v10 <= 70) {
                                    v11 = v10 - 65;
                                    goto label_23;
                                }

                                goto label_55;
                            }
                            else {
                                v11 = v10 - 97;
                            }

                        label_23:
                            v11 += 10;
                        }
                        else {
                            v11 = v10 - 48;
                        }

                        if((-1152921504606846976L & v4) == v2) {
                            v4 = v4 << 4 | (((long)v11));
                            ++v8;
                            ++v0;
                            continue;
                        }
                        else {
                            break;
                        }
                    }

                    goto label_68;
                }

                c v0_1 = new c().j(v4).b(v10);
                StringBuilder v2_1 = new StringBuilder();
                v2_1.append("Number too large: ");
                v2_1.append(v0_1.p());
                throw new NumberFormatException(v2_1.toString());
            label_55:
                if(v0 != 0) {
                    v1 = 1;
                }
                else {
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Expected leading [0-9a-fA-F] character but was 0x");
                    v1_1.append(Integer.toHexString(v10));
                    throw new NumberFormatException(v1_1.toString());
                }

            label_68:
                if(v8 == v9) {
                    this.a = v6.b();
                    p.a(v6);
                }
                else {
                    v6.b = v8;
                }

                if(v1 != 0) {
                    break;
                }
            }
            while(this.a != null);

            this.b -= ((long)v0);
            return v4;
        }

        throw new IllegalStateException("size == 0");
    }

    public f o() {
        return new f(this.r());
    }

    public String p() {
        try {
            return this.a(this.b, u.a);
        }
        catch(EOFException v0) {
            throw new AssertionError(v0);
        }
    }

    public String q() {
        return this.e(9223372036854775807L);
    }

    public byte[] r() {
        try {
            return this.g(this.b);
        }
        catch(EOFException v0) {
            throw new AssertionError(v0);
        }
    }

    public int read(ByteBuffer arg7) {
        o v0 = this.a;
        if(v0 == null) {
            return -1;
        }

        int v1 = Math.min(arg7.remaining(), v0.c - v0.b);
        arg7.put(v0.a, v0.b, v1);
        v0.b += v1;
        this.b -= ((long)v1);
        if(v0.b == v0.c) {
            this.a = v0.b();
            p.a(v0);
        }

        return v1;
    }

    public void s() {
        try {
            this.h(this.b);
            return;
        }
        catch(EOFException v0) {
            throw new AssertionError(v0);
        }
    }

    public c t() {
        c v0 = new c();
        if(this.b == 0) {
            return v0;
        }

        v0.a = this.a.a();
        o v1 = v0.a;
        o v2 = v0.a;
        o v3 = v0.a;
        v2.g = v3;
        v1.f = v3;
        v1 = this.a;
        while(true) {
            v1 = v1.f;
            if(v1 == this.a) {
                break;
            }

            v0.a.g.a(v1.a());
        }

        v0.b = this.b;
        return v0;
    }

    public String toString() {
        return this.u().toString();
    }

    public f u() {
        if(this.b <= 2147483647) {
            return this.f(((int)this.b));
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("size > Integer.MAX_VALUE: ");
        v1.append(this.b);
        throw new IllegalArgumentException(v1.toString());
    }

    public d v() {
        return this.e();
    }

    public int write(ByteBuffer arg7) {
        if(arg7 != null) {
            int v0 = arg7.remaining();
            int v1 = v0;
            while(v1 > 0) {
                o v2 = this.e(1);
                int v3 = Math.min(v1, 8192 - v2.c);
                arg7.get(v2.a, v2.c, v3);
                v1 -= v3;
                v2.c += v3;
            }

            this.b += ((long)v0);
            return v0;
        }

        throw new IllegalArgumentException("source == null");
    }
}

