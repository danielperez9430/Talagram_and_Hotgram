package android.support.v4.f;

class c {
    static final int[] a;
    static final long[] b;
    static final Object[] c;

    static {
        c.a = new int[0];
        c.b = new long[0];
        c.c = new Object[0];
    }

    static int a(int[] arg3, int arg4, int arg5) {
        int v1;
        --arg4;
        int v0 = 0;
        while(true) {
            if(v0 > arg4) {
                goto label_15;
            }

            v1 = v0 + arg4 >>> 1;
            int v2 = arg3[v1];
            if(v2 < arg5) {
                v0 = v1 + 1;
                continue;
            }

            if(v2 <= arg5) {
                return v1;
            }

            arg4 = v1 - 1;
        }

        return v1;
    label_15:
        return v0 ^ -1;
    }

    public static int a(int arg0) {
        return c.c(arg0 * 4) / 4;
    }

    static int a(long[] arg5, int arg6, long arg7) {
        int v1;
        --arg6;
        int v0 = 0;
        while(true) {
            if(v0 > arg6) {
                goto label_15;
            }

            v1 = v0 + arg6 >>> 1;
            long v2 = arg5[v1];
            if(v2 < arg7) {
                v0 = v1 + 1;
                continue;
            }

            if(v2 <= arg7) {
                return v1;
            }

            arg6 = v1 - 1;
        }

        return v1;
    label_15:
        return v0 ^ -1;
    }

    public static boolean a(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public static int b(int arg0) {
        return c.c(arg0 * 8) / 8;
    }

    public static int c(int arg2) {
        int v0;
        for(v0 = 4; v0 < 32; ++v0) {
            int v1 = (1 << v0) - 12;
            if(arg2 <= v1) {
                return v1;
            }
        }

        return arg2;
    }
}

