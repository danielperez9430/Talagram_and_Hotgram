package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractMap$SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map$Entry;

final class lk extends lb {
    class a extends lc {
        private final transient lb a;
        private final transient Object[] b;
        private final transient int c;
        private final transient int d;

        a(lb arg1, Object[] arg2, int arg3, int arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        static int a(a arg0) {
            return arg0.d;
        }

        int a(Object[] arg2, int arg3) {
            return this.e().a(arg2, arg3);
        }

        public ln a() {
            return this.e().a();
        }

        static Object[] b(a arg0) {
            return arg0.b;
        }

        static int c(a arg0) {
            return arg0.c;
        }

        public boolean contains(Object arg4) {
            boolean v1 = false;
            if((arg4 instanceof Map$Entry)) {
                Object v0 = ((Map$Entry)arg4).getKey();
                arg4 = ((Map$Entry)arg4).getValue();
                if(arg4 != null && (arg4.equals(this.a.get(v0)))) {
                    v1 = true;
                }
            }

            return v1;
        }

        boolean f() {
            return 1;
        }

        la h() {
            return new la() {
                public Map$Entry b(int arg4) {
                    kr.a(arg4, a.a(this.a));
                    arg4 *= 2;
                    return new AbstractMap$SimpleImmutableEntry(a.b(this.a)[a.c(this.a) + arg4], a.b(this.a)[arg4 + (a.c(this.a) ^ 1)]);
                }

                public boolean f() {
                    return 1;
                }

                public Object get(int arg1) {
                    return this.b(arg1);
                }

                public int size() {
                    return a.a(this.a);
                }
            };
        }

        public Iterator iterator() {
            return this.a();
        }

        public int size() {
            return this.d;
        }
    }

    final class b extends lc {
        private final transient lb a;
        private final transient la b;

        b(lb arg1, la arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        int a(Object[] arg2, int arg3) {
            return this.e().a(arg2, arg3);
        }

        public ln a() {
            return this.e().a();
        }

        public boolean contains(Object arg2) {
            boolean v2 = this.a.get(arg2) != null ? true : false;
            return v2;
        }

        public la e() {
            return this.b;
        }

        boolean f() {
            return 1;
        }

        public Iterator iterator() {
            return this.a();
        }

        public int size() {
            return this.a.size();
        }
    }

    final class c extends la {
        private final transient Object[] a;
        private final transient int b;
        private final transient int c;

        c(Object[] arg1, int arg2, int arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        boolean f() {
            return 1;
        }

        public Object get(int arg3) {
            kr.a(arg3, this.c);
            return this.a[arg3 * 2 + this.b];
        }

        public int size() {
            return this.c;
        }
    }

    static final lb b;
    final transient Object[] c;
    private final transient int[] d;
    private final transient int e;

    static {
        lk.b = new lk(null, new Object[0], 0);
    }

    private lk(int[] arg1, Object[] arg2, int arg3) {
        super();
        this.d = arg1;
        this.c = arg2;
        this.e = arg3;
    }

    static lk a(int arg3, Object[] arg4) {
        if(arg3 == 0) {
            return lk.b;
        }

        if(arg3 == 1) {
            kv.a(arg4[0], arg4[1]);
            return new lk(null, arg4, 1);
        }

        kr.b(arg3, arg4.length >> 1);
        return new lk(lk.a(arg4, arg3, lc.a(arg3), 0), arg4, arg3);
    }

    static int[] a(Object[] arg10, int arg11, int arg12, int arg13) {
        int v8;
        if(arg11 == 1) {
            kv.a(arg10[arg13], arg10[arg13 ^ 1]);
            return null;
        }

        int v1 = arg12 - 1;
        int[] v12 = new int[arg12];
        int v2 = -1;
        Arrays.fill(v12, v2);
        int v3;
        for(v3 = 0; v3 < arg11; ++v3) {
            int v4 = v3 * 2;
            int v5 = v4 + arg13;
            Object v6 = arg10[v5];
            Object v4_1 = arg10[v4 + (arg13 ^ 1)];
            kv.a(v6, v4_1);
            int v7;
            for(v7 = ky.a(v6.hashCode()); true; ++v7) {
                v7 &= v1;
                v8 = v12[v7];
                if(v8 == v2) {
                    goto label_26;
                }

                if(arg10[v8].equals(v6)) {
                    break;
                }
            }

            String v12_1 = String.valueOf(v6);
            String v13 = String.valueOf(v4_1);
            String v1_1 = String.valueOf(arg10[v8]);
            String v10 = String.valueOf(arg10[1 ^ v8]);
            StringBuilder v2_1 = new StringBuilder(String.valueOf(v12_1).length() + 39 + String.valueOf(v13).length() + String.valueOf(v1_1).length() + String.valueOf(v10).length());
            v2_1.append("Multiple entries with same key: ");
            v2_1.append(v12_1);
            v2_1.append("=");
            v2_1.append(v13);
            v2_1.append(" and ");
            v2_1.append(v1_1);
            v2_1.append("=");
            v2_1.append(v10);
            throw new IllegalArgumentException(v2_1.toString());
        label_26:
            v12[v7] = v5;
        }

        return v12;
    }

    static Object a(int[] arg4, Object[] arg5, int arg6, int arg7, Object arg8) {
        Object v0 = null;
        if(arg8 == null) {
            return v0;
        }

        if(arg6 == 1) {
            if(arg5[arg7].equals(arg8)) {
                v0 = arg5[arg7 ^ 1];
            }

            return v0;
        }

        if(arg4 == null) {
            return v0;
        }

        arg6 = arg4.length - 1;
        for(arg7 = ky.a(arg8.hashCode()); true; ++arg7) {
            arg7 &= arg6;
            int v2 = arg4[arg7];
            if(v2 == -1) {
                return v0;
            }

            if(arg5[v2].equals(arg8)) {
                return arg5[v2 ^ 1];
            }
        }

        return v0;
    }

    lc b() {
        return new a(((lb)this), this.c, 0, this.e);
    }

    lc d() {
        return new b(((lb)this), new c(this.c, 0, this.e));
    }

    kz f() {
        return new c(this.c, 1, this.e);
    }

    public Object get(Object arg5) {
        return lk.a(this.d, this.c, this.e, 0, arg5);
    }

    public int size() {
        return this.e;
    }
}

