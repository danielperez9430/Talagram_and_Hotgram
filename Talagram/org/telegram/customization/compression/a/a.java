package org.telegram.customization.compression.a;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

public enum a {
    static {
        a.a = a.class.desiredAssertionStatus() ^ 1;
        a.b = new a[0];
    }

    public static ByteBuffer a(ByteBuffer arg2) {
        if(arg2.order().equals(d.a)) {
            return arg2;
        }

        return arg2.duplicate().order(d.a);
    }

    public static void a(ByteBuffer arg0, int arg1) {
        if(arg1 >= 0 && arg1 < arg0.capacity()) {
            return;
        }

        throw new ArrayIndexOutOfBoundsException(arg1);
    }

    public static void a(ByteBuffer arg0, int arg1, int arg2) {
        c.a(arg2);
        if(arg2 > 0) {
            a.a(arg0, arg1);
            a.a(arg0, arg1 + arg2 - 1);
        }
    }

    public static void a(ByteBuffer arg2, int arg3, long arg4) {
        if(!a.a) {
            if(arg2.order() == d.a) {
            }
            else {
                throw new AssertionError();
            }
        }

        arg2.putLong(arg3, arg4);
    }

    public static byte b(ByteBuffer arg0, int arg1) {
        return arg0.get(arg1);
    }

    public static void b(ByteBuffer arg0) {
        if(!arg0.isReadOnly()) {
            return;
        }

        throw new ReadOnlyBufferException();
    }

    public static void b(ByteBuffer arg2, int arg3, int arg4) {
        if(!a.a) {
            if(arg2.order() == d.a) {
            }
            else {
                throw new AssertionError();
            }
        }

        arg2.putInt(arg3, arg4);
    }

    public static int c(ByteBuffer arg2, int arg3) {
        if(!a.a) {
            if(arg2.order() == d.a) {
            }
            else {
                throw new AssertionError();
            }
        }

        return arg2.getInt(arg3);
    }

    public static void c(ByteBuffer arg0, int arg1, int arg2) {
        arg0.put(arg1, ((byte)arg2));
    }

    public static long d(ByteBuffer arg2, int arg3) {
        if(!a.a) {
            if(arg2.order() == d.a) {
            }
            else {
                throw new AssertionError();
            }
        }

        return arg2.getLong(arg3);
    }

    public static void d(ByteBuffer arg1, int arg2, int arg3) {
        arg1.put(arg2, ((byte)arg3));
        arg1.put(arg2 + 1, ((byte)(arg3 >>> 8)));
    }

    public static int e(ByteBuffer arg1, int arg2) {
        return (arg1.get(arg2 + 1) & 255) << 8 | arg1.get(arg2) & 255;
    }

    public static a valueOf(String arg1) {
        return Enum.valueOf(a.class, arg1);
    }

    public static a[] values() {
        // Method was not decompiled
    }
}

