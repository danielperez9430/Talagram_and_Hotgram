package com.mp4parser.iso23001.part7;

import com.coremedia.iso.Hex;
import java.math.BigInteger;
import java.util.Arrays;

public class a {
    abstract class com.mp4parser.iso23001.part7.a$a implements j {
        private com.mp4parser.iso23001.part7.a$a(a arg1) {
            this.a = arg1;
            super();
        }

        com.mp4parser.iso23001.part7.a$a(a arg1, com.mp4parser.iso23001.part7.a$a arg2) {
            this(arg1);
        }

        public boolean equals(Object arg7) {
            if(this == (((com.mp4parser.iso23001.part7.a$a)arg7))) {
                return 1;
            }

            if(arg7 != null) {
                if(this.getClass() != arg7.getClass()) {
                }
                else if(this.a() != ((j)arg7).a()) {
                    return 0;
                }
                else if(this.b() != ((j)arg7).b()) {
                    return 0;
                }
                else {
                    return 1;
                }
            }

            return 0;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("P(");
            v0.append(this.a());
            v0.append("|");
            v0.append(this.b());
            v0.append(")");
            return v0.toString();
        }
    }

    class b extends com.mp4parser.iso23001.part7.a$a {
        private byte c;
        private byte d;

        public b(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((byte)arg3);
            this.d = ((byte)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class c extends com.mp4parser.iso23001.part7.a$a {
        private byte c;
        private int d;

        public c(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((byte)arg3);
            this.d = ((int)arg4);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class d extends com.mp4parser.iso23001.part7.a$a {
        private byte c;
        private long d;

        public d(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((byte)arg3);
            this.d = arg4;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    class e extends com.mp4parser.iso23001.part7.a$a {
        private byte c;
        private short d;

        public e(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((byte)arg3);
            this.d = ((short)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class f extends com.mp4parser.iso23001.part7.a$a {
        private int c;
        private byte d;

        public f(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = arg3;
            this.d = ((byte)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class g extends com.mp4parser.iso23001.part7.a$a {
        private int c;
        private int d;

        public g(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = arg3;
            this.d = ((int)arg4);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class h extends com.mp4parser.iso23001.part7.a$a {
        private int c;
        private long d;

        public h(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = arg3;
            this.d = arg4;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    class i extends com.mp4parser.iso23001.part7.a$a {
        private int c;
        private short d;

        public i(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = arg3;
            this.d = ((short)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    public interface j {
        int a();

        long b();
    }

    class k extends com.mp4parser.iso23001.part7.a$a {
        private short c;
        private byte d;

        public k(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((short)arg3);
            this.d = ((byte)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class l extends com.mp4parser.iso23001.part7.a$a {
        private short c;
        private int d;

        public l(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((short)arg3);
            this.d = ((int)arg4);
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    class m extends com.mp4parser.iso23001.part7.a$a {
        private short c;
        private long d;

        public m(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((short)arg3);
            this.d = arg4;
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return this.d;
        }
    }

    class n extends com.mp4parser.iso23001.part7.a$a {
        private short c;
        private short d;

        public n(a arg2, int arg3, long arg4) {
            this.b = arg2;
            super(arg2, null);
            this.c = ((short)arg3);
            this.d = ((short)(((int)arg4)));
        }

        public int a() {
            return this.c;
        }

        public long b() {
            return ((long)this.d);
        }
    }

    public byte[] a;
    public j[] b;

    public a() {
        super();
        this.a = new byte[0];
        this.b = null;
    }

    public j a(int arg8, long arg9) {
        long v0 = 2147483647;
        long v2 = 32767;
        long v4 = 127;
        if(arg8 <= 127) {
            if(arg9 <= v4) {
                return new b(this, arg8, arg9);
            }

            if(arg9 <= v2) {
                return new e(this, arg8, arg9);
            }

            if(arg9 <= v0) {
                return new c(this, arg8, arg9);
            }

            return new d(this, arg8, arg9);
        }

        if(arg8 <= 32767) {
            if(arg9 <= v4) {
                return new k(this, arg8, arg9);
            }

            if(arg9 <= v2) {
                return new n(this, arg8, arg9);
            }

            if(arg9 <= v0) {
                return new l(this, arg8, arg9);
            }

            return new m(this, arg8, arg9);
        }

        if(arg9 <= v4) {
            return new f(this, arg8, arg9);
        }

        if(arg9 <= v2) {
            return new i(this, arg8, arg9);
        }

        if(arg9 <= v0) {
            return new g(this, arg8, arg9);
        }

        return new h(this, arg8, arg9);
    }

    public int a() {
        int v0 = this.a.length;
        if(this.b != null && this.b.length > 0) {
            v0 = v0 + 2 + this.b.length * 6;
        }

        return v0;
    }

    public boolean equals(Object arg6) {
        if(this == (((a)arg6))) {
            return 1;
        }

        if(arg6 != null) {
            if(this.getClass() != arg6.getClass()) {
            }
            else if(!new BigInteger(this.a).equals(new BigInteger(((a)arg6).a))) {
                return 0;
            }
            else {
                if(this.b != null) {
                    if(!Arrays.equals(this.b, ((a)arg6).b)) {
                        return 0;
                    }
                }
                else if(((a)arg6).b != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.a != null ? Arrays.hashCode(this.a) : 0;
        v0 *= 31;
        if(this.b != null) {
            v1 = Arrays.hashCode(this.b);
        }

        return v0 + v1;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("Entry{iv=");
        v0.append(Hex.encodeHex(this.a));
        v0.append(", pairs=");
        v0.append(Arrays.toString(this.b));
        v0.append('}');
        return v0.toString();
    }
}

