package android.support.v4.content.a;

import java.lang.reflect.Array;

final class e {
    static {
        e.a = e.class.desiredAssertionStatus() ^ 1;
    }

    private e() {
        super();
    }

    public static int[] a(int[] arg2, int arg3, int arg4) {
        if(!e.a) {
            if(arg3 <= arg2.length) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(arg3 + 1 > arg2.length) {
            int[] v0 = new int[e.a(arg3)];
            System.arraycopy(arg2, 0, v0, 0, arg3);
            arg2 = v0;
        }

        arg2[arg3] = arg4;
        return arg2;
    }

    public static Object[] a(Object[] arg2, int arg3, Object arg4) {
        Object v2;
        if(!e.a) {
            if(arg3 <= arg2.length) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(arg3 + 1 > arg2.length) {
            Object v0 = Array.newInstance(arg2.getClass().getComponentType(), e.a(arg3));
            System.arraycopy(arg2, 0, v0, 0, arg3);
            v2 = v0;
        }

        ((Object[])v2)[arg3] = arg4;
        return ((Object[])v2);
    }

    public static int a(int arg1) {
        if(arg1 <= 4) {
            arg1 = 8;
        }
        else {
            arg1 *= 2;
        }

        return arg1;
    }
}

