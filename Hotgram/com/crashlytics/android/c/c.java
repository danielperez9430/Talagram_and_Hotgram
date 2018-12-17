package com.crashlytics.android.c;

import android.os.Process;
import c.a.a.a.a.b.i;
import c.a.a.a.a.b.p;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

class c {
    private static final AtomicLong a;
    private static String b;

    static {
        c.a = new AtomicLong(0);
    }

    public c(p arg10) {
        super();
        byte[] v0 = new byte[10];
        this.a(v0);
        this.b(v0);
        this.c(v0);
        String v10 = i.a(arg10.b());
        String v0_1 = i.a(v0);
        c.b = String.format(Locale.US, "%s-%s-%s-%s", v0_1.substring(0, 12), v0_1.substring(12, 16), v0_1.subSequence(16, 20), v10.substring(0, 12)).toUpperCase(Locale.US);
    }

    private void a(byte[] arg8) {
        long v0 = new Date().getTime();
        byte[] v2 = c.a(v0 / 1000);
        arg8[0] = v2[0];
        arg8[1] = v2[1];
        arg8[2] = v2[2];
        arg8[3] = v2[3];
        byte[] v0_1 = c.b(v0 % 1000);
        arg8[4] = v0_1[0];
        arg8[5] = v0_1[1];
    }

    private static byte[] a(long arg1) {
        ByteBuffer v0 = ByteBuffer.allocate(4);
        v0.putInt(((int)arg1));
        v0.order(ByteOrder.BIG_ENDIAN);
        v0.position(0);
        return v0.array();
    }

    private void b(byte[] arg4) {
        byte[] v0 = c.b(c.a.incrementAndGet());
        arg4[6] = v0[0];
        arg4[7] = v0[1];
    }

    private static byte[] b(long arg1) {
        ByteBuffer v0 = ByteBuffer.allocate(2);
        v0.putShort(((short)(((int)arg1))));
        v0.order(ByteOrder.BIG_ENDIAN);
        v0.position(0);
        return v0.array();
    }

    private void c(byte[] arg4) {
        byte[] v0 = c.b(((long)Integer.valueOf(Process.myPid()).shortValue()));
        arg4[8] = v0[0];
        arg4[9] = v0[1];
    }

    public String toString() {
        return c.b;
    }
}

