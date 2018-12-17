package okhttp3.internal.g;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.c;

class d extends f {
    class a implements InvocationHandler {
        boolean a;
        String b;
        private final List c;

        a(List arg1) {
            super();
            this.c = arg1;
        }

        public Object invoke(Object arg6, Method arg7, Object[] arg8) {
            String[] v8;
            String v6 = arg7.getName();
            Class v0 = arg7.getReturnType();
            if(arg8 == null) {
                v8 = c.b;
            }

            if((v6.equals("supports")) && Boolean.TYPE == v0) {
                return Boolean.valueOf(true);
            }

            Object v3 = null;
            if((v6.equals("unsupported")) && Void.TYPE == v0) {
                this.a = true;
                return v3;
            }

            if((v6.equals("protocols")) && ((Object[])v8).length == 0) {
                return this.c;
            }

            if(((v6.equals("selectProtocol")) || (v6.equals("select"))) && (String.class == v0 && ((Object[])v8).length == 1 && ((((Object[])v8)[0] instanceof List)))) {
                arg6 = ((Object[])v8)[0];
                int v7 = ((List)arg6).size();
                int v8_1 = 0;
                while(true) {
                    if(v8_1 >= v7) {
                        break;
                    }
                    else if(this.c.contains(((List)arg6).get(v8_1))) {
                        arg6 = ((List)arg6).get(v8_1);
                    }
                    else {
                        ++v8_1;
                        continue;
                    }

                    goto label_50;
                }

                arg6 = this.c.get(0);
            label_50:
                this.b = ((String)arg6);
                return arg6;
            }

            if(((v6.equals("protocolSelected")) || (v6.equals("selected"))) && ((Object[])v8).length == 1) {
                this.b = ((Object[])v8)[0];
                return v3;
            }

            return arg7.invoke(this, ((Object[])v8));
        }
    }

    private final Method a;
    private final Method b;
    private final Method c;
    private final Class d;
    private final Class e;

    d(Method arg1, Method arg2, Method arg3, Class arg4, Class arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    @Nullable public String a(SSLSocket arg4) {
        Object v4_1;
        try {
            Method v0 = this.b;
            Object[] v1 = new Object[]{arg4};
            v4_1 = null;
            InvocationHandler v0_1 = Proxy.getInvocationHandler(v0.invoke(v4_1, v1));
            if(!((a)v0_1).a && ((a)v0_1).b == null) {
                f.c().a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", ((Throwable)v4_1));
                return ((String)v4_1);
            }

            if(((a)v0_1).a) {
            }
            else {
                String v4_2 = ((a)v0_1).b;
            }
        }
        catch(IllegalAccessException v4) {
            throw c.a("unable to get selected protocol", ((Exception)v4));
        }

        return ((String)v4_1);
    }

    public void a(SSLSocket arg6, String arg7, List arg8) {
        List v7 = d.a(arg8);
        try {
            this.a.invoke(null, arg6, Proxy.newProxyInstance(f.class.getClassLoader(), new Class[]{this.d, this.e}, new a(v7)));
            return;
        }
        catch(IllegalAccessException v6) {
            throw c.a("unable to set alpn", ((Exception)v6));
        }
    }

    public static f b() {
        d v4;
        try {
            Class v1 = Class.forName("org.eclipse.jetty.alpn.ALPN");
            StringBuilder v2 = new StringBuilder();
            v2.append("org.eclipse.jetty.alpn.ALPN");
            v2.append("$Provider");
            Class v2_1 = Class.forName(v2.toString());
            StringBuilder v3 = new StringBuilder();
            v3.append("org.eclipse.jetty.alpn.ALPN");
            v3.append("$ClientProvider");
            Class v8 = Class.forName(v3.toString());
            v3 = new StringBuilder();
            v3.append("org.eclipse.jetty.alpn.ALPN");
            v3.append("$ServerProvider");
            v4 = new d(v1.getMethod("put", SSLSocket.class, v2_1), v1.getMethod("get", SSLSocket.class), v1.getMethod("remove", SSLSocket.class), v8, Class.forName(v3.toString()));
        }
        catch(NoSuchMethodException ) {
            return null;
        }

        return v4;
    }

    public void b(SSLSocket arg5) {
        try {
            this.c.invoke(null, arg5);
            return;
        }
        catch(InvocationTargetException v5) {
            throw c.a("unable to remove alpn", ((Exception)v5));
        }
    }
}

