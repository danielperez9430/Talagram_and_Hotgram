package android.support.v4.graphics;

import android.graphics.Color;

public final class a {
    private static final ThreadLocal a;

    static {
        a.a = new ThreadLocal();
    }

    public static int a(int arg6, int arg7) {
        int v0 = Color.alpha(arg7);
        int v1 = Color.alpha(arg6);
        int v2 = a.d(v1, v0);
        return Color.argb(v2, a.a(Color.red(arg6), v1, Color.red(arg7), v0, v2), a.a(Color.green(arg6), v1, Color.green(arg7), v0, v2), a.a(Color.blue(arg6), v1, Color.blue(arg7), v0, v2));
    }

    public static double a(int arg5) {
        double[] v0 = a.a();
        a.a(arg5, v0);
        return v0[1] / 100;
    }

    private static double[] a() {
        double[] v0_1;
        Object v0 = a.a.get();
        if(v0 == null) {
            v0_1 = new double[3];
            a.a.set(v0_1);
        }

        return v0_1;
    }

    public static void a(int arg2, double[] arg3) {
        a.a(Color.red(arg2), Color.green(arg2), Color.blue(arg2), arg3);
    }

    private static float a(float arg1, float arg2, float arg3) {
        if(arg1 < arg2) {
            arg1 = arg2;
        }
        else if(arg1 > arg3) {
            arg1 = arg3;
        }

        return arg1;
    }

    private static int a(int arg0, int arg1, int arg2, int arg3, int arg4) {
        if(arg4 == 0) {
            return 0;
        }

        return (arg0 * 255 * arg1 + arg2 * arg3 * (255 - arg1)) / (arg4 * 255);
    }

    public static int a(int arg8, int arg9, float arg10) {
        int v1 = 255;
        if(Color.alpha(arg9) == v1) {
            double v4 = ((double)arg10);
            if(a.b(a.c(arg8, v1), arg9) < v4) {
                return -1;
            }

            int v10 = 0;
            int v0 = 0;
            while(v10 <= 10) {
                if(v1 - v0 <= 1) {
                    return v1;
                }

                int v2 = (v0 + v1) / 2;
                if(a.b(a.c(arg8, v2), arg9) < v4) {
                    v0 = v2;
                }
                else {
                    v1 = v2;
                }

                ++v10;
            }

            return v1;
        }

        StringBuilder v10_1 = new StringBuilder();
        v10_1.append("background can not be translucent: #");
        v10_1.append(Integer.toHexString(arg9));
        throw new IllegalArgumentException(v10_1.toString());
    }

    public static void a(int arg20, int arg21, int arg22, double[] arg23) {
        double[] v0 = arg23;
        if(v0.length == 3) {
            double v1 = ((double)arg20);
            double v3 = 255;
            Double.isNaN(v1);
            v1 /= v3;
            double v5 = 0.04045;
            double v8 = 2.4;
            double v10 = 1.055;
            double v12 = 0.055;
            double v14 = 12.92;
            if(Double.compare(v1, v5) < 0) {
                v1 /= v14;
            }
            else {
                v1 = Math.pow((v1 + v12) / v10, v8);
            }

            double v16 = v1;
            int v1_1 = arg21;
            v1 = ((double)v1_1);
            Double.isNaN(v1);
            v1 /= v3;
            if(v1 < v5) {
                v1 /= v14;
            }
            else {
                v1 = Math.pow((v1 + v12) / v10, v8);
            }

            double v18 = v1;
            v1_1 = arg22;
            v1 = ((double)v1_1);
            Double.isNaN(v1);
            v1 /= v3;
            if(v1 < v5) {
                v1 /= v14;
            }
            else {
                v1 = Math.pow((v1 + v12) / v10, v8);
            }

            v0[0] = (0.4124 * v16 + 0.3576 * v18 + 0.1805 * v1) * 100;
            v0[1] = (0.2126 * v16 + 0.7152 * v18 + 0.0722 * v1) * 100;
            v0[2] = (v16 * 0.0193 + v18 * 0.1192 + v1 * 0.9505) * 100;
            return;
        }

        throw new IllegalArgumentException("outXyz must have a length of 3.");
    }

    public static void a(int arg7, int arg8, int arg9, float[] arg10) {
        float v7 = (((float)arg7)) / 255f;
        float v8 = (((float)arg8)) / 255f;
        float v9 = (((float)arg9)) / 255f;
        float v0 = Math.max(v7, Math.max(v8, v9));
        float v1 = Math.min(v7, Math.min(v8, v9));
        float v2 = v0 - v1;
        float v4 = 2f;
        float v3 = (v0 + v1) / v4;
        float v5 = 1f;
        if(Float.compare(v0, v1) == 0) {
            v7 = 0f;
            v8 = 0f;
        }
        else {
            if(v0 == v7) {
                v7 = (v8 - v9) / v2 % 6f;
            }
            else if(v0 == v8) {
                v7 = (v9 - v7) / v2 + v4;
            }
            else {
                v7 = (v7 - v8) / v2 + 4f;
            }

            v8 = v2 / (v5 - Math.abs(v4 * v3 - v5));
        }

        v9 = 360f;
        v7 = v7 * 60f % v9;
        if(v7 < 0f) {
            v7 += v9;
        }

        arg10[0] = a.a(v7, 0f, v9);
        arg10[1] = a.a(v8, 0f, v5);
        arg10[2] = a.a(v3, 0f, v5);
    }

    public static void a(int arg2, float[] arg3) {
        a.a(Color.red(arg2), Color.green(arg2), Color.blue(arg2), arg3);
    }

    public static double b(int arg4, int arg5) {
        int v1 = 255;
        if(Color.alpha(arg5) == v1) {
            if(Color.alpha(arg4) < v1) {
                arg4 = a.a(arg4, arg5);
            }

            double v0 = a.a(arg4) + 0.05;
            double v4 = a.a(arg5) + 0.05;
            return Math.max(v0, v4) / Math.min(v0, v4);
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("background can not be translucent: #");
        v0_1.append(Integer.toHexString(arg5));
        throw new IllegalArgumentException(v0_1.toString());
    }

    public static int c(int arg1, int arg2) {
        if(arg2 >= 0 && arg2 <= 255) {
            return arg1 & 16777215 | arg2 << 24;
        }

        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }

    private static int d(int arg0, int arg1) {
        return 255 - (255 - arg1) * (255 - arg0) / 255;
    }
}

