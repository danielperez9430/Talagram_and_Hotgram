package android.support.v8.renderscript;

public class c extends b {
    class android.support.v8.renderscript.c$1 {
        static {
            android.support.v8.renderscript.c$1.b = new int[a.values().length];
            try {
                android.support.v8.renderscript.c$1.b[a.d.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
            label_9:
                int v1 = 2;
                try {
                    android.support.v8.renderscript.c$1.b[a.e.ordinal()] = v1;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                label_14:
                    int v2 = 3;
                    try {
                        android.support.v8.renderscript.c$1.b[a.f.ordinal()] = v2;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                    label_19:
                        android.support.v8.renderscript.c$1.a = new int[android.support.v8.renderscript.c$b.values().length];
                        try {
                            android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.b.ordinal()] = 1;
                            goto label_27;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_27:
                                android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.c.ordinal()] = v1;
                                goto label_31;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_31:
                                    android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.d.ordinal()] = v2;
                                    goto label_35;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_35:
                                        android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.e.ordinal()] = 4;
                                        goto label_40;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_40:
                                            android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.f.ordinal()] = 5;
                                            goto label_45;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_45:
                                                android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.g.ordinal()] = 6;
                                                goto label_50;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_50:
                                                    android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.h.ordinal()] = 7;
                                                    goto label_55;
                                                }
                                                catch(NoSuchFieldError ) {
                                                    try {
                                                    label_55:
                                                        android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.i.ordinal()] = 8;
                                                        goto label_60;
                                                    }
                                                    catch(NoSuchFieldError ) {
                                                        try {
                                                        label_60:
                                                            android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.j.ordinal()] = 9;
                                                            goto label_65;
                                                        }
                                                        catch(NoSuchFieldError ) {
                                                            try {
                                                            label_65:
                                                                android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.k.ordinal()] = 10;
                                                                goto label_70;
                                                            }
                                                            catch(NoSuchFieldError ) {
                                                                try {
                                                                label_70:
                                                                    android.support.v8.renderscript.c$1.a[android.support.v8.renderscript.c$b.l.ordinal()] = 11;
                                                                    return;
                                                                }
                                                                catch(NoSuchFieldError ) {
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public static final enum a e;
        public static final enum a f;
        public static final enum a g;
        public static final enum a h;
        int i;

        static {
            a.a = new a("USER", 0, 0);
            a.b = new a("PIXEL_L", 1, 7);
            a.c = new a("PIXEL_A", 2, 8);
            a.d = new a("PIXEL_LA", 3, 9);
            a.e = new a("PIXEL_RGB", 4, 10);
            a.f = new a("PIXEL_RGBA", 5, 11);
            a.g = new a("PIXEL_DEPTH", 6, 12);
            a.h = new a("PIXEL_YUV", 7, 13);
            a.j = new a[]{a.a, a.b, a.c, a.d, a.e, a.f, a.g, a.h};
        }

        private a(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.i = arg3;
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    public enum android.support.v8.renderscript.c$b {
        public static final enum android.support.v8.renderscript.c$b a;
        public static final enum android.support.v8.renderscript.c$b b;
        public static final enum android.support.v8.renderscript.c$b c;
        public static final enum android.support.v8.renderscript.c$b d;
        public static final enum android.support.v8.renderscript.c$b e;
        public static final enum android.support.v8.renderscript.c$b f;
        public static final enum android.support.v8.renderscript.c$b g;
        public static final enum android.support.v8.renderscript.c$b h;
        public static final enum android.support.v8.renderscript.c$b i;
        public static final enum android.support.v8.renderscript.c$b j;
        public static final enum android.support.v8.renderscript.c$b k;
        public static final enum android.support.v8.renderscript.c$b l;
        public static final enum android.support.v8.renderscript.c$b m;
        public static final enum android.support.v8.renderscript.c$b n;
        public static final enum android.support.v8.renderscript.c$b o;
        public static final enum android.support.v8.renderscript.c$b p;
        public static final enum android.support.v8.renderscript.c$b q;
        public static final enum android.support.v8.renderscript.c$b r;
        public static final enum android.support.v8.renderscript.c$b s;
        public static final enum android.support.v8.renderscript.c$b t;
        public static final enum android.support.v8.renderscript.c$b u;
        public static final enum android.support.v8.renderscript.c$b v;
        public static final enum android.support.v8.renderscript.c$b w;
        int x;
        int y;

        static {
            android.support.v8.renderscript.c$b.a = new android.support.v8.renderscript.c$b("NONE", 0, 0, 0);
            android.support.v8.renderscript.c$b.b = new android.support.v8.renderscript.c$b("FLOAT_32", 1, 2, 4);
            android.support.v8.renderscript.c$b.c = new android.support.v8.renderscript.c$b("FLOAT_64", 2, 3, 8);
            android.support.v8.renderscript.c$b.d = new android.support.v8.renderscript.c$b("SIGNED_8", 3, 4, 1);
            android.support.v8.renderscript.c$b.e = new android.support.v8.renderscript.c$b("SIGNED_16", 4, 5, 2);
            android.support.v8.renderscript.c$b.f = new android.support.v8.renderscript.c$b("SIGNED_32", 5, 6, 4);
            android.support.v8.renderscript.c$b.g = new android.support.v8.renderscript.c$b("SIGNED_64", 6, 7, 8);
            android.support.v8.renderscript.c$b.h = new android.support.v8.renderscript.c$b("UNSIGNED_8", 7, 8, 1);
            android.support.v8.renderscript.c$b.i = new android.support.v8.renderscript.c$b("UNSIGNED_16", 8, 9, 2);
            android.support.v8.renderscript.c$b.j = new android.support.v8.renderscript.c$b("UNSIGNED_32", 9, 10, 4);
            android.support.v8.renderscript.c$b.k = new android.support.v8.renderscript.c$b("UNSIGNED_64", 10, 11, 8);
            android.support.v8.renderscript.c$b.l = new android.support.v8.renderscript.c$b("BOOLEAN", 11, 12, 1);
            android.support.v8.renderscript.c$b.m = new android.support.v8.renderscript.c$b("UNSIGNED_5_6_5", 12, 13, 2);
            android.support.v8.renderscript.c$b.n = new android.support.v8.renderscript.c$b("UNSIGNED_5_5_5_1", 13, 14, 2);
            android.support.v8.renderscript.c$b.o = new android.support.v8.renderscript.c$b("UNSIGNED_4_4_4_4", 14, 15, 2);
            android.support.v8.renderscript.c$b.p = new android.support.v8.renderscript.c$b("MATRIX_4X4", 15, 16, 64);
            android.support.v8.renderscript.c$b.q = new android.support.v8.renderscript.c$b("MATRIX_3X3", 16, 17, 36);
            android.support.v8.renderscript.c$b.r = new android.support.v8.renderscript.c$b("MATRIX_2X2", 17, 18, 16);
            android.support.v8.renderscript.c$b.s = new android.support.v8.renderscript.c$b("RS_ELEMENT", 18, 1000);
            android.support.v8.renderscript.c$b.t = new android.support.v8.renderscript.c$b("RS_TYPE", 19, 1001);
            android.support.v8.renderscript.c$b.u = new android.support.v8.renderscript.c$b("RS_ALLOCATION", 20, 1002);
            android.support.v8.renderscript.c$b.v = new android.support.v8.renderscript.c$b("RS_SAMPLER", 21, 1003);
            android.support.v8.renderscript.c$b.w = new android.support.v8.renderscript.c$b("RS_SCRIPT", 22, 1004);
            android.support.v8.renderscript.c$b.z = new android.support.v8.renderscript.c$b[]{android.support.v8.renderscript.c$b.a, android.support.v8.renderscript.c$b.b, android.support.v8.renderscript.c$b.c, android.support.v8.renderscript.c$b.d, android.support.v8.renderscript.c$b.e, android.support.v8.renderscript.c$b.f, android.support.v8.renderscript.c$b.g, android.support.v8.renderscript.c$b.h, android.support.v8.renderscript.c$b.i, android.support.v8.renderscript.c$b.j, android.support.v8.renderscript.c$b.k, android.support.v8.renderscript.c$b.l, android.support.v8.renderscript.c$b.m, android.support.v8.renderscript.c$b.n, android.support.v8.renderscript.c$b.o, android.support.v8.renderscript.c$b.p, android.support.v8.renderscript.c$b.q, android.support.v8.renderscript.c$b.r, android.support.v8.renderscript.c$b.s, android.support.v8.renderscript.c$b.t, android.support.v8.renderscript.c$b.u, android.support.v8.renderscript.c$b.v, android.support.v8.renderscript.c$b.w};
        }

        private android.support.v8.renderscript.c$b(String arg1, int arg2, int arg3, int arg4) {
            super(arg1, arg2);
            this.x = arg3;
            this.y = arg4;
        }

        private android.support.v8.renderscript.c$b(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.x = arg3;
            this.y = 4;
            if(RenderScript.g == 8) {
                this.y = 32;
            }
        }

        public static android.support.v8.renderscript.c$b valueOf(String arg1) {
            return Enum.valueOf(android.support.v8.renderscript.c$b.class, arg1);
        }

        public static android.support.v8.renderscript.c$b[] values() {
            // Method was not decompiled
        }
    }

    int a;
    android.support.v8.renderscript.c$b b;
    a c;
    boolean d;
    int e;

    c(long arg1, RenderScript arg3, android.support.v8.renderscript.c$b arg4, a arg5, boolean arg6, int arg7) {
        int v1;
        super(arg1, arg3);
        if(arg4 == android.support.v8.renderscript.c$b.m || arg4 == android.support.v8.renderscript.c$b.o || arg4 == android.support.v8.renderscript.c$b.n) {
            v1 = arg4.y;
        }
        else if(arg7 == 3) {
            v1 = arg4.y * 4;
        }
        else {
            v1 = arg4.y * arg7;
        }

        this.a = v1;
        this.b = arg4;
        this.c = arg5;
        this.d = arg6;
        this.e = arg7;
    }

    public int a() {
        return this.a;
    }

    public boolean a(c arg4) {
        boolean v1 = true;
        if(this.equals(arg4)) {
            return 1;
        }

        if(this.a != arg4.a || this.b == android.support.v8.renderscript.c$b.a || this.b != arg4.b || this.e != arg4.e) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    static c a(RenderScript arg12, android.support.v8.renderscript.c$b arg13) {
        return new c(arg12.a(((long)arg13.x), a.a.i, false, 1), arg12, arg13, a.a, false, 1);
    }

    public static c a(RenderScript arg10, android.support.v8.renderscript.c$b arg11, int arg12) {
        if(arg12 >= 2 && arg12 <= 4) {
            switch(android.support.v8.renderscript.c$1.a[arg11.ordinal()]) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: {
                    return new c(arg10.a(((long)arg11.x), a.a.i, false, arg12), arg10, arg11, a.a, false, arg12);
                }
                default: {
                    throw new f("Cannot create vector of non-primitive type.");
                }
            }
        }

        throw new f("Vector size out of range 2-4.");
    }

    public static c a(RenderScript arg9, android.support.v8.renderscript.c$b arg10, a arg11) {
        int v8;
        if(arg11 != a.b && arg11 != a.c && arg11 != a.d && arg11 != a.e && arg11 != a.f && arg11 != a.g) {
            if(arg11 == a.h) {
            }
            else {
                throw new f("Unsupported DataKind");
            }
        }

        if(arg10 != android.support.v8.renderscript.c$b.h && arg10 != android.support.v8.renderscript.c$b.i && arg10 != android.support.v8.renderscript.c$b.m && arg10 != android.support.v8.renderscript.c$b.o) {
            if(arg10 == android.support.v8.renderscript.c$b.n) {
            }
            else {
                throw new f("Unsupported DataType");
            }
        }

        if(arg10 == android.support.v8.renderscript.c$b.m) {
            if(arg11 == a.e) {
            }
            else {
                throw new f("Bad kind and type combo");
            }
        }

        if(arg10 == android.support.v8.renderscript.c$b.n) {
            if(arg11 == a.f) {
            }
            else {
                throw new f("Bad kind and type combo");
            }
        }

        if(arg10 == android.support.v8.renderscript.c$b.o) {
            if(arg11 == a.f) {
            }
            else {
                throw new f("Bad kind and type combo");
            }
        }

        if(arg10 == android.support.v8.renderscript.c$b.i) {
            if(arg11 == a.g) {
            }
            else {
                throw new f("Bad kind and type combo");
            }
        }

        switch(android.support.v8.renderscript.c$1.b[arg11.ordinal()]) {
            case 1: {
                v8 = 2;
                break;
            }
            case 2: {
                v8 = 3;
                break;
            }
            case 3: {
                v8 = 4;
                break;
            }
            default: {
                v8 = 1;
                break;
            }
        }

        return new c(arg9.a(((long)arg10.x), arg11.i, true, v8), arg9, arg10, arg11, true, v8);
    }

    public static c b(RenderScript arg1) {
        if(arg1.n == null) {
            arg1.n = c.a(arg1, android.support.v8.renderscript.c$b.h);
        }

        return arg1.n;
    }

    public static c c(RenderScript arg2) {
        if(arg2.o == null) {
            arg2.o = c.a(arg2, android.support.v8.renderscript.c$b.h, a.c);
        }

        return arg2.o;
    }

    public static c d(RenderScript arg2) {
        if(arg2.p == null) {
            arg2.p = c.a(arg2, android.support.v8.renderscript.c$b.m, a.e);
        }

        return arg2.p;
    }

    public static c e(RenderScript arg2) {
        if(arg2.q == null) {
            arg2.q = c.a(arg2, android.support.v8.renderscript.c$b.o, a.f);
        }

        return arg2.q;
    }

    public static c f(RenderScript arg2) {
        if(arg2.r == null) {
            arg2.r = c.a(arg2, android.support.v8.renderscript.c$b.h, a.f);
        }

        return arg2.r;
    }

    public static c g(RenderScript arg2) {
        if(arg2.s == null) {
            arg2.s = c.a(arg2, android.support.v8.renderscript.c$b.h, 4);
        }

        return arg2.s;
    }

    public long h(RenderScript arg8) {
        return arg8.b(((long)this.b.x), this.c.i, this.d, this.e);
    }
}

