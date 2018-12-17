package com.google.ads.interactivemedia.v3.internal;

public final class kr {
    public static Object a(Object arg0) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException();
    }

    public static int a(int arg1, int arg2) {
        return kr.a(arg1, arg2, "index");
    }

    public static int a(int arg1, int arg2, String arg3) {
        if(arg1 >= 0 && arg1 < arg2) {
            return arg1;
        }

        throw new IndexOutOfBoundsException(kr.c(arg1, arg2, arg3));
    }

    public static void a(int arg1, int arg2, int arg3) {
        if(arg1 >= 0 && arg2 >= arg1 && arg2 <= arg3) {
            return;
        }

        throw new IndexOutOfBoundsException(kr.b(arg1, arg2, arg3));
    }

    public static void a(boolean arg0, Object arg1) {
        if(arg0) {
            return;
        }

        throw new IllegalArgumentException(String.valueOf(arg1));
    }

    private static String b(int arg2, int arg3, int arg4) {
        if(arg2 >= 0) {
            if(arg2 > arg4) {
            }
            else {
                if(arg3 >= 0) {
                    if(arg3 > arg4) {
                    }
                    else {
                        return ks.a("end index (%s) must not be less than start index (%s)", new Object[]{Integer.valueOf(arg3), Integer.valueOf(arg2)});
                    }
                }

                return kr.d(arg3, arg4, "end index");
            }
        }

        return kr.d(arg2, arg4, "start index");
    }

    public static int b(int arg1, int arg2) {
        return kr.b(arg1, arg2, "index");
    }

    public static int b(int arg1, int arg2, String arg3) {
        if(arg1 >= 0 && arg1 <= arg2) {
            return arg1;
        }

        throw new IndexOutOfBoundsException(kr.d(arg1, arg2, arg3));
    }

    private static String c(int arg5, int arg6, String arg7) {
        int v2 = 2;
        if(arg5 < 0) {
            Object[] v2_1 = new Object[v2];
            v2_1[0] = arg7;
            v2_1[1] = Integer.valueOf(arg5);
            return ks.a("%s (%s) must not be negative", v2_1);
        }

        if(arg6 >= 0) {
            Object[] v4 = new Object[3];
            v4[0] = arg7;
            v4[1] = Integer.valueOf(arg5);
            v4[v2] = Integer.valueOf(arg6);
            return ks.a("%s (%s) must be less than size (%s)", v4);
        }

        StringBuilder v0 = new StringBuilder(26);
        v0.append("negative size: ");
        v0.append(arg6);
        throw new IllegalArgumentException(v0.toString());
    }

    private static String d(int arg5, int arg6, String arg7) {
        int v2 = 2;
        if(arg5 < 0) {
            Object[] v2_1 = new Object[v2];
            v2_1[0] = arg7;
            v2_1[1] = Integer.valueOf(arg5);
            return ks.a("%s (%s) must not be negative", v2_1);
        }

        if(arg6 >= 0) {
            Object[] v4 = new Object[3];
            v4[0] = arg7;
            v4[1] = Integer.valueOf(arg5);
            v4[v2] = Integer.valueOf(arg6);
            return ks.a("%s (%s) must not be greater than size (%s)", v4);
        }

        StringBuilder v0 = new StringBuilder(26);
        v0.append("negative size: ");
        v0.append(arg6);
        throw new IllegalArgumentException(v0.toString());
    }
}

