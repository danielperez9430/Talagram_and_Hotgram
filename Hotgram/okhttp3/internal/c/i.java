package okhttp3.internal.c;

import java.net.Proxy$Type;
import okhttp3.aa;
import okhttp3.t;

public final class i {
    public static String a(aa arg2, Proxy$Type arg3) {
        StringBuilder v0 = new StringBuilder();
        v0.append(arg2.b());
        v0.append(' ');
        if(i.b(arg2, arg3)) {
            v0.append(arg2.a());
        }
        else {
            v0.append(i.a(arg2.a()));
        }

        v0.append(" HTTP/1.1");
        return v0.toString();
    }

    public static String a(t arg2) {
        String v0 = arg2.h();
        String v2 = arg2.k();
        if(v2 != null) {
            v0 = v0 + '?' + v2;
        }

        return v0;
    }

    private static boolean b(aa arg0, Proxy$Type arg1) {
        boolean v0 = (arg0.g()) || arg1 != Proxy$Type.HTTP ? false : true;
        return v0;
    }
}

