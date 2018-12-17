package com.d.a.c;

import android.opengl.GLES10;
import com.d.a.b.a.e;
import com.d.a.b.a.h;

public final class a {
    class com.d.a.c.a$1 {
        static {
            com.d.a.c.a$1.a = new int[h.values().length];
            try {
                com.d.a.c.a$1.a[h.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.d.a.c.a$1.a[h.b.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    private static e a;

    static {
        int[] v0 = new int[1];
        GLES10.glGetIntegerv(3379, v0, 0);
        int v0_1 = Math.max(v0[0], 2048);
        a.a = new e(v0_1, v0_1);
    }

    public static e a(com.d.a.b.e.a arg1, e arg2) {
        int v0 = arg1.a();
        if(v0 <= 0) {
            v0 = arg2.a();
        }

        int v1 = arg1.b();
        if(v1 <= 0) {
            v1 = arg2.b();
        }

        return new e(v0, v1);
    }

    private static int a(int arg3, int arg4, int arg5, boolean arg6) {
        int v0 = a.a.a();
        int v1 = a.a.b();
        while(true) {
            if(arg3 / arg5 <= v0) {
                if(arg4 / arg5 > v1) {
                }
                else {
                    return arg5;
                }
            }

            if(arg6) {
                arg5 *= 2;
                continue;
            }

            ++arg5;
        }
    }

    public static int a(e arg3) {
        return Math.max(((int)Math.ceil(((double)((((float)arg3.a())) / (((float)a.a.a())))))), ((int)Math.ceil(((double)((((float)arg3.b())) / (((float)a.a.b())))))));
    }

    public static int a(e arg6, e arg7, h arg8, boolean arg9) {
        int v3;
        int v8;
        int v0 = arg6.a();
        int v6 = arg6.b();
        int v1 = arg7.a();
        int v7 = arg7.b();
        switch(com.d.a.c.a$1.a[arg8.ordinal()]) {
            case 1: {
                if(arg9) {
                    v8 = v0 / 2;
                    v3 = v6 / 2;
                    int v4;
                    for(v4 = 1; true; v4 *= 2) {
                        if(v8 / v4 <= v1 && v3 / v4 <= v7) {
                            goto label_21;
                        }
                    }
                }

                v7 = Math.max(v0 / v1, v6 / v7);
                break;
            }
            case 2: {
                if(arg9) {
                    v8 = v0 / 2;
                    v3 = v6 / 2;
                    for(v4 = 1; v8 / v4 > v1; v4 *= 2) {
                        if(v3 / v4 <= v7) {
                            break;
                        }
                    }

                label_21:
                    v7 = v4;
                    goto label_40;
                }

                v7 = Math.min(v0 / v1, v6 / v7);
                break;
            }
            default: {
                v7 = 1;
                break;
            }
        }

    label_40:
        if(v7 < 1) {
            v7 = 1;
        }

        return a.a(v0, v6, v7, arg9);
    }

    public static float b(e arg7, e arg8, h arg9, boolean arg10) {
        int v0 = arg7.a();
        int v7 = arg7.b();
        int v1 = arg8.a();
        int v8 = arg8.b();
        float v2 = ((float)v0);
        float v3 = v2 / (((float)v1));
        float v4 = ((float)v7);
        float v5 = v4 / (((float)v8));
        if(arg9 != h.a || v3 < v5) {
            if(arg9 == h.b && v3 < v5) {
            label_16:
                v8 = ((int)(v4 / v3));
                goto label_21;
            }

            v1 = ((int)(v2 / v5));
        }
        else {
            goto label_16;
        }

    label_21:
        float v9 = 1f;
        if(!arg10 && v1 < v0 && v8 < v7 || (arg10) && v1 != v0 && v8 != v7) {
            v9 = (((float)v1)) / v2;
        }

        return v9;
    }
}

