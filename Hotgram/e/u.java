package e;

import java.nio.charset.Charset;

final class u {
    public static final Charset a;

    static {
        u.a = Charset.forName("UTF-8");
    }

    public static void a(long arg5, long arg7, long arg9) {
        if((arg7 | arg9) >= 0 && arg7 <= arg5 && arg5 - arg7 >= arg9) {
            return;
        }

        throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(arg5), Long.valueOf(arg7), Long.valueOf(arg9)));
    }

    public static short a(short arg1) {
        int v1 = arg1 & 65535;
        return ((short)((v1 & 255) << 8 | (65280 & v1) >>> 8));
    }

    public static int a(int arg2) {
        return (arg2 & 255) << 24 | ((-16777216 & arg2) >>> 24 | (16711680 & arg2) >>> 8 | (65280 & arg2) << 8);
    }

    public static boolean a(byte[] arg4, int arg5, byte[] arg6, int arg7, int arg8) {
        int v1;
        for(v1 = 0; v1 < arg8; ++v1) {
            if(arg4[v1 + arg5] != arg6[v1 + arg7]) {
                return 0;
            }
        }

        return 1;
    }

    public static void a(Throwable arg0) {
        u.b(arg0);
    }

    private static void b(Throwable arg0) {
        throw arg0;
    }
}

