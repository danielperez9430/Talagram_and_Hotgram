package com.e.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.os.Looper;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

final class v {
    @TargetApi(value=12) class a {
        static int a(Bitmap arg0) {
            return arg0.getByteCount();
        }
    }

    static final StringBuilder a;

    static {
        v.a = new StringBuilder();
    }

    static void a(String arg4, String arg5, String arg6, String arg7) {
        Log.d("Picasso", String.format("%1$-11s %2$-12s %3$s %4$s", arg4, arg5, arg6, arg7));
    }

    static void a(InputStream arg0) {
        if(arg0 == null) {
            return;
        }

        try {
            arg0.close();
            return;
        }
        catch(IOException ) {
            return;
        }
    }

    static void a(String arg1, String arg2, String arg3) {
        v.a(arg1, arg2, arg3, "");
    }

    static String a(b arg1) {
        return v.a(arg1, "");
    }

    static void a() {
        if(v.b()) {
            return;
        }

        throw new IllegalStateException("Method call should happen from the main thread.");
    }

    static String a(o arg2) {
        String v2 = v.a(arg2, v.a);
        v.a.setLength(0);
        return v2;
    }

    static Object a(Object arg0, String arg1) {
        if(arg0 != null) {
            return arg0;
        }

        throw new NullPointerException(arg1);
    }

    static int a(Bitmap arg3) {
        int v0 = Build$VERSION.SDK_INT >= 12 ? a.a(arg3) : arg3.getRowBytes() * arg3.getHeight();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Negative size: ");
        v1.append(arg3);
        throw new IllegalStateException(v1.toString());
    }

    static String a(b arg4, String arg5) {
        StringBuilder v0 = new StringBuilder(arg5);
        com.e.a.a v5 = arg4.d();
        if(v5 != null) {
            v0.append(v5.b.a());
        }

        List v4 = arg4.e();
        if(v4 != null) {
            int v1 = 0;
            int v2 = v4.size();
            while(v1 < v2) {
                if(v1 > 0 || v5 != null) {
                    v0.append(", ");
                }

                v0.append(v4.get(v1).b.a());
                ++v1;
            }
        }

        return v0.toString();
    }

    static String a(o arg4, StringBuilder arg5) {
        // Method was not decompiled
    }

    static byte[] b(InputStream arg4) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        byte[] v1 = new byte[4096];
        while(true) {
            int v3 = arg4.read(v1);
            if(-1 == v3) {
                break;
            }

            v0.write(v1, 0, v3);
        }

        return v0.toByteArray();
    }

    static boolean b() {
        boolean v0 = Looper.getMainLooper().getThread() == Thread.currentThread() ? true : false;
        return v0;
    }

    static boolean c(InputStream arg6) {
        byte[] v1 = new byte[12];
        boolean v2 = false;
        if(arg6.read(v1, 0, 12) == 12) {
            int v4 = 4;
            if(("RIFF".equals(new String(v1, 0, v4, "US-ASCII"))) && ("WEBP".equals(new String(v1, 8, v4, "US-ASCII")))) {
                v2 = true;
            }
        }

        return v2;
    }
}

