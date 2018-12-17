package okhttp3;

import java.io.IOException;

public enum y {
    public static final enum y a;
    public static final enum y b;
    public static final enum y c;
    public static final enum y d;
    public static final enum y e;
    private final String f;

    static {
        y.a = new y("HTTP_1_0", 0, "http/1.0");
        y.b = new y("HTTP_1_1", 1, "http/1.1");
        y.c = new y("SPDY_3", 2, "spdy/3.1");
        y.d = new y("HTTP_2", 3, "h2");
        y.e = new y("QUIC", 4, "quic");
        y.g = new y[]{y.a, y.b, y.c, y.d, y.e};
    }

    private y(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.f = arg3;
    }

    public static y a(String arg3) {
        if(arg3.equals(y.a.f)) {
            return y.a;
        }

        if(arg3.equals(y.b.f)) {
            return y.b;
        }

        if(arg3.equals(y.d.f)) {
            return y.d;
        }

        if(arg3.equals(y.c.f)) {
            return y.c;
        }

        if(arg3.equals(y.e.f)) {
            return y.e;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected protocol: ");
        v1.append(arg3);
        throw new IOException(v1.toString());
    }

    public String toString() {
        return this.f;
    }

    public static y valueOf(String arg1) {
        return Enum.valueOf(y.class, arg1);
    }

    public static y[] values() {
        // Method was not decompiled
    }
}

