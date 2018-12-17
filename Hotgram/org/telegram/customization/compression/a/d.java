package org.telegram.customization.compression.a;

import java.nio.ByteOrder;

public enum d {
    public static final ByteOrder a;
    private static final boolean b;

    static {
        boolean v0 = false;
        d.c = new d[0];
        d.a = ByteOrder.nativeOrder();
        String v1 = System.getProperty("os.arch");
        if((v1.equals("i386")) || (v1.equals("x86")) || (v1.equals("amd64")) || (v1.equals("x86_64"))) {
            v0 = true;
        }

        d.b = v0;
    }

    public static boolean a() {
        return d.b;
    }

    public static d valueOf(String arg1) {
        return Enum.valueOf(d.class, arg1);
    }

    public static d[] values() {
        // Method was not decompiled
    }
}

