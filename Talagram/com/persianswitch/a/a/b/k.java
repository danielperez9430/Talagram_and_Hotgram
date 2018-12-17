package com.persianswitch.a.a.b;

import com.persianswitch.a.q;
import com.persianswitch.a.z;

public final class k {
    static boolean a(String arg1) {
        boolean v1 = ("Connection".equalsIgnoreCase(arg1)) || ("Keep-Alive".equalsIgnoreCase(arg1)) || ("Proxy-Authenticate".equalsIgnoreCase(arg1)) || ("Proxy-Authorization".equalsIgnoreCase(arg1)) || ("TE".equalsIgnoreCase(arg1)) || ("Trailers".equalsIgnoreCase(arg1)) || ("Transfer-Encoding".equalsIgnoreCase(arg1)) || ("Upgrade".equalsIgnoreCase(arg1)) ? false : true;
        return v1;
    }

    public static long a(z arg2) {
        return k.a(arg2.e());
    }

    public static long a(q arg2) {
        return k.b(arg2.a("Content-Length"));
    }

    private static long b(String arg4) {
        long v0 = -1;
        if(arg4 == null) {
            return v0;
        }

        try {
            return Long.parseLong(arg4);
        }
        catch(NumberFormatException ) {
            return v0;
        }
    }
}

