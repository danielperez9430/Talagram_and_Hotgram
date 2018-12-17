package android.support.v7.widget;

import android.view.View;

class bb {
    static int a(t arg1, au arg2, View arg3, View arg4, i arg5, boolean arg6) {
        if(arg5.x() != 0 && arg1.e() != 0 && arg3 != null) {
            if(arg4 == null) {
            }
            else if(!arg6) {
                return Math.abs(arg5.d(arg3) - arg5.d(arg4)) + 1;
            }
            else {
                return Math.min(arg2.f(), arg2.b(arg4) - arg2.a(arg3));
            }
        }

        return 0;
    }

    static int a(t arg4, au arg5, View arg6, View arg7, i arg8, boolean arg9, boolean arg10) {
        if(arg8.x() != 0 && arg4.e() != 0 && arg6 != null) {
            if(arg7 == null) {
            }
            else {
                int v0 = Math.min(arg8.d(arg6), arg8.d(arg7));
                int v2 = Math.max(arg8.d(arg6), arg8.d(arg7));
                int v4 = arg10 ? Math.max(0, arg4.e() - v2 - 1) : Math.max(0, v0);
                if(!arg9) {
                    return v4;
                }

                return Math.round((((float)v4)) * ((((float)Math.abs(arg5.b(arg7) - arg5.a(arg6)))) / (((float)(Math.abs(arg8.d(arg6) - arg8.d(arg7)) + 1)))) + (((float)(arg5.c() - arg5.a(arg6)))));
            }
        }

        return 0;
    }

    static int b(t arg1, au arg2, View arg3, View arg4, i arg5, boolean arg6) {
        if(arg5.x() != 0 && arg1.e() != 0 && arg3 != null) {
            if(arg4 == null) {
            }
            else if(!arg6) {
                return arg1.e();
            }
            else {
                return ((int)((((float)(arg2.b(arg4) - arg2.a(arg3)))) / (((float)(Math.abs(arg5.d(arg3) - arg5.d(arg4)) + 1))) * (((float)arg1.e()))));
            }
        }

        return 0;
    }
}

