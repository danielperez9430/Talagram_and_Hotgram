package okhttp3.internal.h;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.c;

public final class d implements HostnameVerifier {
    public static final d a;

    static {
        d.a = new d();
    }

    private d() {
        super();
    }

    public static List a(X509Certificate arg4) {
        List v0 = d.a(arg4, 7);
        List v4 = d.a(arg4, 2);
        ArrayList v1 = new ArrayList(v0.size() + v4.size());
        ((List)v1).addAll(((Collection)v0));
        ((List)v1).addAll(((Collection)v4));
        return ((List)v1);
    }

    public boolean a(String arg2, X509Certificate arg3) {
        boolean v2 = c.c(arg2) ? this.b(arg2, arg3) : this.c(arg2, arg3);
        return v2;
    }

    private static List a(X509Certificate arg4, int arg5) {
        ArrayList v0 = new ArrayList();
        try {
            Collection v4 = arg4.getSubjectAlternativeNames();
            if(v4 == null) {
                return Collections.emptyList();
            }

            Iterator v4_1 = v4.iterator();
            while(v4_1.hasNext()) {
                Object v1 = v4_1.next();
                if(v1 == null) {
                    continue;
                }

                if(((List)v1).size() < 2) {
                    continue;
                }

                Object v2 = ((List)v1).get(0);
                if(v2 == null) {
                    continue;
                }

                if(((Integer)v2).intValue() != arg5) {
                    continue;
                }

                v1 = ((List)v1).get(1);
                if(v1 == null) {
                    continue;
                }

                ((List)v0).add(v1);
            }
        }
        catch(CertificateParsingException ) {
            goto label_27;
        }

        return ((List)v0);
    label_27:
        return Collections.emptyList();
    }

    public boolean a(String arg7, String arg8) {
        if(arg7 != null && arg7.length() != 0 && !arg7.startsWith(".")) {
            if(arg7.endsWith("..")) {
            }
            else if(arg8 != null && arg8.length() != 0 && !arg8.startsWith(".")) {
                if(arg8.endsWith("..")) {
                }
                else {
                    char v2 = '.';
                    if(!arg7.endsWith(".")) {
                        arg7 = arg7 + v2;
                    }

                    if(!arg8.endsWith(".")) {
                        arg8 = arg8 + v2;
                    }

                    arg8 = arg8.toLowerCase(Locale.US);
                    if(!arg8.contains("*")) {
                        return arg7.equals(arg8);
                    }

                    if(!arg8.startsWith("*.")) {
                        return 0;
                    }

                    int v4 = -1;
                    if(arg8.indexOf(42, 1) != v4) {
                        return 0;
                    }

                    if(arg7.length() < arg8.length()) {
                        return 0;
                    }

                    if("*.".equals(arg8)) {
                        return 0;
                    }

                    arg8 = arg8.substring(1);
                    if(!arg7.endsWith(arg8)) {
                        return 0;
                    }

                    int v1_1 = arg7.length() - arg8.length();
                    if(v1_1 > 0 && arg7.lastIndexOf(v2, v1_1 - 1) != v4) {
                        return 0;
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    private boolean b(String arg5, X509Certificate arg6) {
        List v6 = d.a(arg6, 7);
        int v0 = v6.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg5.equalsIgnoreCase(v6.get(v2))) {
                return 1;
            }
        }

        return 0;
    }

    private boolean c(String arg2, X509Certificate arg3) {
        arg2 = arg2.toLowerCase(Locale.US);
        Iterator v3 = d.a(arg3, 2).iterator();
        do {
            if(!v3.hasNext()) {
                return 0;
            }
        }
        while(!this.a(arg2, v3.next()));

        return 1;
    }

    public boolean verify(String arg2, SSLSession arg3) {
        try {
            return this.a(arg2, arg3.getPeerCertificates()[0]);
        }
        catch(SSLException ) {
            return 0;
        }
    }
}

