package com.d.a.c;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class b {
    public interface a {
        boolean a(int arg1, int arg2);
    }

    public static void a(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    public static void a(InputStream arg4) {
        int v0 = 32768;
        byte[] v1 = new byte[v0];
        try {
            while(true) {
            label_3:
                if(arg4.read(v1, 0, v0) == -1) {
                    goto label_10;
                }

                goto label_6;
            }
        }
        catch(IOException ) {
        label_10:
            b.a(((Closeable)arg4));
            return;
        }
        catch(Throwable v0_1) {
            b.a(((Closeable)arg4));
            throw v0_1;
        label_6:
            goto label_3;
        }
    }

    private static boolean a(a arg0, int arg1, int arg2) {
        if(arg0 != null && !arg0.a(arg1, arg2) && arg1 * 100 / arg2 < 75) {
            return 1;
        }

        return 0;
    }

    public static boolean a(InputStream arg6, OutputStream arg7, a arg8, int arg9) {
        int v0 = arg6.available();
        if(v0 <= 0) {
            v0 = 512000;
        }

        byte[] v1 = new byte[arg9];
        if(b.a(arg8, 0, v0)) {
            return 0;
        }

        int v3 = 0;
        do {
            int v4 = arg6.read(v1, 0, arg9);
            if(v4 == -1) {
                goto label_17;
            }

            arg7.write(v1, 0, v4);
            v3 += v4;
        }
        while(!b.a(arg8, v3, v0));

        return 0;
    label_17:
        arg7.flush();
        return 1;
    }
}

