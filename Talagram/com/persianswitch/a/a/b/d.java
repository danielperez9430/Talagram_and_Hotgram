package com.persianswitch.a.a.b;

public final class d {
    public static int a(String arg2, int arg3) {
        while(arg3 < arg2.length()) {
            int v0 = arg2.charAt(arg3);
            if(v0 != 32 && v0 != 9) {
                return arg3;
            }

            ++arg3;
        }

        return arg3;
    }

    public static int a(String arg2, int arg3, String arg4) {
        while(arg3 < arg2.length()) {
            if(arg4.indexOf(arg2.charAt(arg3)) != -1) {
            }
            else {
                ++arg3;
                continue;
            }

            return arg3;
        }

        return arg3;
    }

    public static int b(String arg3, int arg4) {
        long v0;
        try {
            v0 = Long.parseLong(arg3);
            long v3 = 2147483647;
            if(v0 <= v3) {
                goto label_5;
            }
        }
        catch(NumberFormatException ) {
            return arg4;
        }

        return 2147483647;
    label_5:
        if(v0 < 0) {
            return 0;
        }

        return ((int)v0);
    }
}

