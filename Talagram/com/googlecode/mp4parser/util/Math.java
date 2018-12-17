package com.googlecode.mp4parser.util;

public class Math {
    public Math() {
        super();
    }

    public static int gcd(int arg1, int arg2) {
        while(true) {
            int v0 = arg2;
            arg2 = arg1;
            arg1 = v0;
            if(arg1 <= 0) {
                return arg2;
            }

            arg2 %= arg1;
        }

        return arg2;
    }

    public static long gcd(long arg5, long arg7) {
        while(true) {
            long v3 = arg5;
            arg5 = arg7;
            arg7 = v3;
            if(arg5 <= 0) {
                return arg7;
            }

            arg7 %= arg5;
        }

        return arg7;
    }

    public static int lcm(int arg1, int arg2) {
        return arg1 * (arg2 / Math.gcd(arg1, arg2));
    }

    public static long lcm(long arg2, long arg4) {
        return arg2 * (arg4 / Math.gcd(arg2, arg4));
    }
}

