package com.google.android.gms.common.util;

@VisibleForTesting public class NumberUtils {
    private NumberUtils() {
        super();
    }

    public static int compare(int arg0, int arg1) {
        if(arg0 < arg1) {
            return -1;
        }

        if(arg0 == arg1) {
            return 0;
        }

        return 1;
    }

    public static int compare(long arg1, long arg3) {
        if(arg1 < arg3) {
            return -1;
        }

        if(arg1 == arg3) {
            return 0;
        }

        return 1;
    }

    public static boolean isNumeric(String arg0) {
        try {
            Long.parseLong(arg0);
            return 1;
        }
        catch(NumberFormatException ) {
            return 0;
        }
    }

    public static long parseHexLong(String arg5) {
        int v1 = 16;
        if(arg5.length() <= v1) {
            if(arg5.length() == v1) {
                return Long.parseLong(arg5.substring(0, 8), v1) << 32 | Long.parseLong(arg5.substring(8), v1);
            }

            return Long.parseLong(arg5, v1);
        }

        StringBuilder v2 = new StringBuilder(String.valueOf(arg5).length() + 37);
        v2.append("Invalid input: ");
        v2.append(arg5);
        v2.append(" exceeds 16 characters");
        throw new NumberFormatException(v2.toString());
    }
}

