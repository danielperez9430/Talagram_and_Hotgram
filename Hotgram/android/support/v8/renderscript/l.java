package android.support.v8.renderscript;

public class l extends b {
    public class a {
        RenderScript a;
        int b;
        int c;
        int d;
        boolean e;
        boolean f;
        int g;
        c h;

        public a(RenderScript arg2, c arg3) {
            super();
            this.b = 1;
            arg3.d();
            this.a = arg2;
            this.h = arg3;
        }

        public a a(int arg2) {
            if(arg2 >= 1) {
                this.b = arg2;
                return this;
            }

            throw new f("Values of less than 1 for Dimension X are not valid.");
        }

        public a a(boolean arg1) {
            this.e = arg1;
            return this;
        }

        public l a() {
            if(this.d > 0) {
                if(this.b >= 1 && this.c >= 1) {
                    if(!this.f) {
                        goto label_18;
                    }
                    else {
                        throw new g("Cube maps not supported with 3D types.");
                    }
                }

                throw new g("Both X and Y dimension required when Z is present.");
            }

        label_18:
            if(this.c > 0) {
                if(this.b >= 1) {
                }
                else {
                    throw new g("X dimension required when Y is present.");
                }
            }

            if(this.f) {
                if(this.c >= 1) {
                }
                else {
                    throw new g("Cube maps require 2D Types.");
                }
            }

            if(this.g != 0 && (this.d != 0 || (this.f) || (this.e))) {
                throw new g("YUV only supports basic 2D.");
            }

            l v2 = new l(this.a.a(this.h.a(this.a), this.b, this.c, this.d, this.e, this.f, this.g), this.a);
            v2.h = this.h;
            v2.a = this.b;
            v2.b = this.c;
            v2.c = this.d;
            v2.d = this.e;
            v2.e = this.f;
            v2.f = this.g;
            v2.j();
            return v2;
        }

        public a b(int arg2) {
            if(arg2 >= 1) {
                this.c = arg2;
                return this;
            }

            throw new f("Values of less than 1 for Dimension Y are not valid.");
        }
    }

    public enum android.support.v8.renderscript.l$b {
        public static final enum android.support.v8.renderscript.l$b a;
        public static final enum android.support.v8.renderscript.l$b b;
        public static final enum android.support.v8.renderscript.l$b c;
        public static final enum android.support.v8.renderscript.l$b d;
        public static final enum android.support.v8.renderscript.l$b e;
        public static final enum android.support.v8.renderscript.l$b f;
        int g;

        static {
            android.support.v8.renderscript.l$b.a = new android.support.v8.renderscript.l$b("POSITIVE_X", 0, 0);
            android.support.v8.renderscript.l$b.b = new android.support.v8.renderscript.l$b("NEGATIVE_X", 1, 1);
            android.support.v8.renderscript.l$b.c = new android.support.v8.renderscript.l$b("POSITIVE_Y", 2, 2);
            android.support.v8.renderscript.l$b.d = new android.support.v8.renderscript.l$b("NEGATIVE_Y", 3, 3);
            android.support.v8.renderscript.l$b.e = new android.support.v8.renderscript.l$b("POSITIVE_Z", 4, 4);
            android.support.v8.renderscript.l$b.f = new android.support.v8.renderscript.l$b("NEGATIVE_Z", 5, 5);
            android.support.v8.renderscript.l$b.h = new android.support.v8.renderscript.l$b[]{android.support.v8.renderscript.l$b.a, android.support.v8.renderscript.l$b.b, android.support.v8.renderscript.l$b.c, android.support.v8.renderscript.l$b.d, android.support.v8.renderscript.l$b.e, android.support.v8.renderscript.l$b.f};
        }

        private android.support.v8.renderscript.l$b(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.g = arg3;
        }

        public static android.support.v8.renderscript.l$b valueOf(String arg1) {
            return Enum.valueOf(android.support.v8.renderscript.l$b.class, arg1);
        }

        public static android.support.v8.renderscript.l$b[] values() {
            // Method was not decompiled
        }
    }

    int a;
    int b;
    int c;
    boolean d;
    boolean e;
    int f;
    int g;
    c h;

    l(long arg1, RenderScript arg3) {
        super(arg1, arg3);
    }

    public c a() {
        return this.h;
    }

    public long a(RenderScript arg10, long arg11) {
        return arg10.b(arg11, this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public int b() {
        return this.a;
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.c;
    }

    public boolean g() {
        return this.d;
    }

    public boolean h() {
        return this.e;
    }

    public int i() {
        return this.g;
    }

    void j() {
        boolean v0 = this.g();
        int v1 = this.b();
        int v2 = this.e();
        int v3 = this.f();
        int v4 = this.h() ? 6 : 1;
        if(v1 == 0) {
            v1 = 1;
        }

        if(v2 == 0) {
            v2 = 1;
        }

        if(v3 == 0) {
            v3 = 1;
        }

        int v6;
        for(v6 = v1 * v2 * v3 * v4; v0; v6 += v1 * v2 * v3 * v4) {
            if(v1 <= 1 && v2 <= 1 && v3 <= 1) {
                break;
            }

            if(v1 > 1) {
                v1 >>= 1;
            }

            if(v2 > 1) {
                v2 >>= 1;
            }

            if(v3 > 1) {
                v3 >>= 1;
            }
        }

        this.g = v6;
    }
}

