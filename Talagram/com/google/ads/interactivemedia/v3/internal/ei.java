package com.google.ads.interactivemedia.v3.internal;

import java.util.Stack;

final class ei implements ej {
    class com.google.ads.interactivemedia.v3.internal.ei$1 {
    }

    final class a {
        private final int a;
        private final long b;

        a(int arg1, long arg2, com.google.ads.interactivemedia.v3.internal.ei$1 arg4) {
            this(arg1, arg2);
        }

        private a(int arg1, long arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        static long a(a arg2) {
            return arg2.b;
        }

        static int b(a arg0) {
            return arg0.a;
        }
    }

    private final byte[] a;
    private final Stack b;
    private final em c;
    private ek d;
    private int e;
    private int f;
    private long g;

    ei() {
        super();
        this.a = new byte[8];
        this.b = new Stack();
        this.c = new em();
    }

    private long a(cd arg7, int arg8) {
        int v1 = 0;
        arg7.b(this.a, 0, arg8);
        long v2 = 0;
        while(v1 < arg8) {
            v2 = v2 << 8 | (((long)(this.a[v1] & 255)));
            ++v1;
        }

        return v2;
    }

    public void a() {
        this.e = 0;
        this.b.clear();
        this.c.a();
    }

    public void a(ek arg1) {
        this.d = arg1;
    }

    public boolean a(cd arg10) {
        StringBuilder v3_1;
        long v0_2;
        long v3;
        boolean v0 = this.d != null ? true : false;
        fe.b(v0);
        while(true) {
            if(!this.b.isEmpty() && arg10.c() >= a.a(this.b.peek())) {
                this.d.c(a.b(this.b.pop()));
                return 1;
            }

            if(this.e == 0) {
                v3 = this.c.a(arg10, true, false, 4);
                if(v3 == -2) {
                    v3 = this.b(arg10);
                }

                if(v3 == -1) {
                    return 0;
                }

                this.f = ((int)v3);
                this.e = 1;
            }

            if(this.e == 1) {
                this.g = this.c.a(arg10, false, true, 8);
                this.e = 2;
            }

            int v0_1 = this.d.a(this.f);
            v3 = 8;
            switch(v0_1) {
                case 0: {
                    goto label_149;
                }
                case 1: {
                    goto label_134;
                }
                case 2: {
                    goto label_113;
                }
                case 3: {
                    goto label_91;
                }
                case 4: {
                    goto label_84;
                }
                case 5: {
                    goto label_59;
                }
            }

            StringBuilder v2 = new StringBuilder(32);
            v2.append("Invalid element type ");
            v2.append(v0_1);
            throw new bl(v2.toString());
        label_113:
            if(this.g <= v3) {
                this.d.a(this.f, this.a(arg10, ((int)this.g)));
                this.e = 0;
                return 1;
            }

            v0_2 = this.g;
            v3_1 = new StringBuilder(42);
            v3_1.append("Invalid integer size: ");
            v3_1.append(v0_2);
            throw new bl(v3_1.toString());
        label_84:
            this.d.a(this.f, ((int)this.g), arg10);
            this.e = 0;
            return 1;
        label_149:
            arg10.b(((int)this.g));
            this.e = 0;
        }

    label_134:
        long v5 = arg10.c();
        this.b.add(new a(this.f, this.g + v5, null));
        this.d.a(this.f, v5, this.g);
        this.e = 0;
        return 1;
    label_91:
        if(this.g <= 2147483647) {
            this.d.a(this.f, this.c(arg10, ((int)this.g)));
            this.e = 0;
            return 1;
        }

        v0_2 = this.g;
        v3_1 = new StringBuilder(41);
        v3_1.append("String element size: ");
        v3_1.append(v0_2);
        throw new bl(v3_1.toString());
    label_59:
        if(this.g != 4) {
            if(this.g == v3) {
            }
            else {
                v0_2 = this.g;
                v3_1 = new StringBuilder(40);
                v3_1.append("Invalid float size: ");
                v3_1.append(v0_2);
                throw new bl(v3_1.toString());
            }
        }

        this.d.a(this.f, this.b(arg10, ((int)this.g)));
        this.e = 0;
        return 1;
    }

    private double b(cd arg3, int arg4) {
        long v0 = this.a(arg3, arg4);
        double v3 = arg4 == 4 ? ((double)Float.intBitsToFloat(((int)v0))) : Double.longBitsToDouble(v0);
        return v3;
    }

    private long b(cd arg5) {
        arg5.a();
        while(true) {
            int v1 = 4;
            arg5.c(this.a, 0, v1);
            int v0 = em.a(this.a[0]);
            if(v0 != -1 && v0 <= v1) {
                v1 = ((int)em.a(this.a, v0, false));
                if(this.d.b(v1)) {
                    arg5.b(v0);
                    return ((long)v1);
                }
            }

            arg5.b(1);
        }
    }

    private String c(cd arg3, int arg4) {
        if(arg4 == 0) {
            return "";
        }

        byte[] v0 = new byte[arg4];
        arg3.b(v0, 0, arg4);
        return new String(v0);
    }
}

