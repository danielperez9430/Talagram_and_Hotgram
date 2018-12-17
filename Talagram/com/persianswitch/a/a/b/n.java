package com.persianswitch.a.a.b;

import com.persianswitch.a.r;
import com.persianswitch.a.x;
import java.net.Proxy$Type;

public final class n {
    public static String a(r arg2) {
        String v0 = arg2.h();
        String v2 = arg2.j();
        if(v2 != null) {
            v0 = v0 + '?' + v2;
        }

        return v0;
    }

    static String a(x arg2, Proxy$Type arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append(arg2.b());
        v0.append(' ');
        if(n.b(arg2, arg3)) {
            v0.append(arg2.a());
        }
        else {
            v0.append(n.a(arg2.a()));
        }

        v0.append(" HTTP/1.1");
        return v0.toString();
    }

    private static boolean b(x arg0, Proxy$Type arg1) {
        boolean v0 = (arg0.g()) || arg1 != Proxy$Type.HTTP ? false : true;
        return v0;
    }
}

