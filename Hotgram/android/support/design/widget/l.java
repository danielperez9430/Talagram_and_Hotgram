package android.support.design.widget;

public final class l {
    public static float a(float arg1, float arg2, float arg3, float arg4, float arg5, float arg6) {
        return l.b(l.a(arg1, arg2, arg3, arg4), l.a(arg1, arg2, arg5, arg4), l.a(arg1, arg2, arg5, arg6), l.a(arg1, arg2, arg3, arg6));
    }

    public static float a(float arg1, float arg2, float arg3) {
        return (1f - arg3) * arg1 + arg3 * arg2;
    }

    public static float a(float arg0, float arg1, float arg2, float arg3) {
        return ((float)Math.hypot(((double)(arg2 - arg0)), ((double)(arg3 - arg1))));
    }

    public static boolean b(float arg0, float arg1, float arg2) {
        boolean v0 = arg0 + arg2 >= arg1 ? true : false;
        return v0;
    }

    private static float b(float arg1, float arg2, float arg3, float arg4) {
        if(arg1 <= arg2 || arg1 <= arg3 || arg1 <= arg4) {
            if(arg2 > arg3 && arg2 > arg4) {
                return arg2;
            }

            if(arg3 > arg4) {
                return arg3;
            }

            arg1 = arg4;
        }
        else {
        }

        return arg1;
    }
}

