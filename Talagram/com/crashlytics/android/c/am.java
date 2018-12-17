package com.crashlytics.android.c;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

final class am {
    final class com.crashlytics.android.c.am$1 implements FilenameFilter {
        com.crashlytics.android.c.am$1() {
            super();
        }

        public boolean accept(File arg1, String arg2) {
            return 1;
        }
    }

    private static final FilenameFilter a;

    static {
        am.a = new com.crashlytics.android.c.am$1();
    }

    static int a(File arg1, int arg2, Comparator arg3) {
        return am.a(arg1, am.a, arg2, arg3);
    }

    static int a(File arg2, FilenameFilter arg3, int arg4, Comparator arg5) {
        File[] v2 = arg2.listFiles(arg3);
        int v3 = 0;
        if(v2 == null) {
            return 0;
        }

        int v0 = v2.length;
        Arrays.sort(((Object[])v2), arg5);
        int v5 = v2.length;
        while(v3 < v5) {
            File v1 = v2[v3];
            if(v0 <= arg4) {
                return v0;
            }

            v1.delete();
            --v0;
            ++v3;
        }

        return v0;
    }
}

