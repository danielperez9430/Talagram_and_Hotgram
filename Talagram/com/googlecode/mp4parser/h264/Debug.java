package com.googlecode.mp4parser.h264;

import java.nio.ShortBuffer;

public class Debug {
    public static final boolean debug = false;

    public Debug() {
        super();
    }

    public static void print(int arg0) {
    }

    public static void print(String arg0) {
    }

    public static void print(short[] arg9) {
        int v1 = 0;
        int v2;
        for(v2 = 0; true; v2 = v4) {
            int v3 = 8;
            if(v1 >= v3) {
                return;
            }

            int v4 = v2;
            for(v2 = 0; v2 < v3; ++v2) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(arg9[v4])});
                ++v4;
            }

            System.out.println();
            ++v1;
        }
    }

    public static final void print8x8(ShortBuffer arg8) {
        int v1;
        for(v1 = 0; true; ++v1) {
            int v2 = 8;
            if(v1 >= v2) {
                return;
            }

            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(arg8.get())});
            }

            System.out.println();
        }
    }

    public static final void print8x8(int[] arg9) {
        int v1 = 0;
        int v2;
        for(v2 = 0; true; v2 = v4) {
            int v3 = 8;
            if(v1 >= v3) {
                return;
            }

            int v4 = v2;
            for(v2 = 0; v2 < v3; ++v2) {
                System.out.printf("%3d, ", new Object[]{Integer.valueOf(arg9[v4])});
                ++v4;
            }

            System.out.println();
            ++v1;
        }
    }

    public static final void print8x8(short[] arg9) {
        int v1 = 0;
        int v2;
        for(v2 = 0; true; v2 = v4) {
            int v3 = 8;
            if(v1 >= v3) {
                return;
            }

            int v4 = v2;
            for(v2 = 0; v2 < v3; ++v2) {
                System.out.printf("%3d, ", new Object[]{Short.valueOf(arg9[v4])});
                ++v4;
            }

            System.out.println();
            ++v1;
        }
    }

    public static void println(String arg0) {
    }

    public static void trace(String arg0, Object[] arg1) {
    }
}

