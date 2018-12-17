package android.support.v7.widget;

import java.util.List;

class at {
    interface a {
        void a(b arg1);

        b a(int arg1, int arg2, int arg3, Object arg4);
    }

    final a a;

    at(a arg1) {
        super();
        this.a = arg1;
    }

    private void a(List arg8, int arg9, int arg10) {
        Object v4 = arg8.get(arg9);
        Object v6 = arg8.get(arg10);
        int v0 = ((b)v6).a;
        if(v0 != 4) {
            switch(v0) {
                case 1: {
                    goto label_15;
                }
                case 2: {
                    goto label_9;
                }
            }

            return;
        label_9:
            this.a(arg8, arg9, ((b)v4), arg10, ((b)v6));
            return;
        label_15:
            this.c(arg8, arg9, ((b)v4), arg10, ((b)v6));
        }
        else {
            this.b(arg8, arg9, ((b)v4), arg10, ((b)v6));
        }
    }

    void a(List arg9, int arg10, b arg11, int arg12, b arg13) {
        int v0;
        int v2 = 0;
        if(arg11.b < arg11.d) {
            if(arg13.b == arg11.b && arg13.d == arg11.d - arg11.b) {
                v0 = 0;
                goto label_14;
            }

            v0 = 0;
        }
        else {
            if(arg13.b == arg11.d + 1 && arg13.d == arg11.b - arg11.d) {
                v0 = 1;
            label_14:
                v2 = 1;
                goto label_30;
            }

            v0 = 1;
        }

    label_30:
        int v5 = 2;
        if(arg11.d < arg13.b) {
            --arg13.b;
        }
        else if(arg11.d < arg13.b + arg13.d) {
            --arg13.d;
            arg11.a = v5;
            arg11.d = 1;
            if(arg13.d == 0) {
                arg9.remove(arg12);
                this.a.a(arg13);
            }

            return;
        }

        Object v6 = null;
        if(arg11.b <= arg13.b) {
            ++arg13.b;
        }
        else if(arg11.b < arg13.b + arg13.d) {
            b v6_1 = this.a.a(v5, arg11.b + 1, arg13.b + arg13.d - arg11.b, v6);
            arg13.d = arg11.b - arg13.b;
        }

        if(v2 != 0) {
            arg9.set(arg10, arg13);
            arg9.remove(arg12);
            this.a.a(arg11);
            return;
        }

        if(v0 != 0) {
            if((((b)v6)) != null) {
                if(arg11.b > ((b)v6).b) {
                    arg11.b -= ((b)v6).d;
                }

                if(arg11.d <= ((b)v6).b) {
                    goto label_102;
                }

                arg11.d -= ((b)v6).d;
            }

        label_102:
            if(arg11.b > arg13.b) {
                arg11.b -= arg13.d;
            }

            if(arg11.d <= arg13.b) {
                goto label_143;
            }

            goto label_112;
        }
        else {
            if((((b)v6)) != null) {
                if(arg11.b >= ((b)v6).b) {
                    arg11.b -= ((b)v6).d;
                }

                if(arg11.d < ((b)v6).b) {
                    goto label_132;
                }

                arg11.d -= ((b)v6).d;
            }

        label_132:
            if(arg11.b >= arg13.b) {
                arg11.b -= arg13.d;
            }

            if(arg11.d < arg13.b) {
                goto label_143;
            }

        label_112:
            arg11.d -= arg13.d;
        }

    label_143:
        arg9.set(arg10, arg13);
        if(arg11.b != arg11.d) {
            arg9.set(arg12, arg11);
        }
        else {
            arg9.remove(arg12);
        }

        if((((b)v6)) != null) {
            arg9.add(arg10, v6);
        }
    }

    void a(List arg3) {
        while(true) {
            int v0 = this.b(arg3);
            if(v0 == -1) {
                return;
            }

            this.a(arg3, v0, v0 + 1);
        }
    }

    void b(List arg8, int arg9, b arg10, int arg11, b arg12) {
        b v0;
        int v2 = 4;
        b v3 = null;
        if(arg10.d < arg12.b) {
            --arg12.b;
            goto label_23;
        }
        else if(arg10.d < arg12.b + arg12.d) {
            --arg12.d;
            v0 = this.a.a(v2, arg10.b, 1, arg12.c);
        }
        else {
        label_23:
            v0 = v3;
        }

        if(arg10.b <= arg12.b) {
            ++arg12.b;
        }
        else if(arg10.b < arg12.b + arg12.d) {
            int v1 = arg12.b + arg12.d - arg10.b;
            v3 = this.a.a(v2, arg10.b + 1, v1, arg12.c);
            arg12.d -= v1;
        }

        arg8.set(arg11, arg10);
        if(arg12.d > 0) {
            arg8.set(arg9, arg12);
        }
        else {
            arg8.remove(arg9);
            this.a.a(arg12);
        }

        if(v0 != null) {
            arg8.add(arg9, v0);
        }

        if(v3 != null) {
            arg8.add(arg9, v3);
        }
    }

    private int b(List arg6) {
        int v0 = arg6.size() - 1;
        int v2 = 0;
        while(v0 >= 0) {
            if(arg6.get(v0).a != 8) {
                v2 = 1;
            }
            else if(v2 != 0) {
                return v0;
            }

            --v0;
        }

        return -1;
    }

    private void c(List arg4, int arg5, b arg6, int arg7, b arg8) {
        int v0 = arg6.d < arg8.b ? -1 : 0;
        if(arg6.b < arg8.b) {
            ++v0;
        }

        if(arg8.b <= arg6.b) {
            arg6.b += arg8.d;
        }

        if(arg8.b <= arg6.d) {
            arg6.d += arg8.d;
        }

        arg8.b += v0;
        arg4.set(arg5, arg8);
        arg4.set(arg7, arg6);
    }
}

