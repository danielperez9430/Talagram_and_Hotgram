package okhttp3.internal.g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

final class c extends f {
    final Method a;
    final Method b;

    c(Method arg1, Method arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    @Nullable public String a(SSLSocket arg3) {
        Object v3_1;
        try {
            v3_1 = this.b.invoke(arg3);
            if(v3_1 != null) {
                if(!((String)v3_1).equals("")) {
                    goto label_9;
                }

                return null;
            }

            return null;
        }
        catch(InvocationTargetException v3) {
            throw okhttp3.internal.c.a("unable to get selected protocols", ((Exception)v3));
        }

        return null;
    label_9:
        return ((String)v3_1);
    }

    public void a(SSLSocket arg5, String arg6, List arg7) {
        try {
            SSLParameters v6 = arg5.getSSLParameters();
            arg7 = c.a(arg7);
            this.a.invoke(v6, arg7.toArray(new String[arg7.size()]));
            arg5.setSSLParameters(v6);
            return;
        }
        catch(InvocationTargetException v5) {
            throw okhttp3.internal.c.a("unable to set ssl parameters", ((Exception)v5));
        }
    }

    public static c b() {
        try {
            return new c(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol"));
        }
        catch(NoSuchMethodException ) {
            return null;
        }
    }
}

