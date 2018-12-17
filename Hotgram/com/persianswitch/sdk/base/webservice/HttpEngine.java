package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import com.persianswitch.a.a.d.d;
import com.persianswitch.a.aa;
import com.persianswitch.a.t;
import com.persianswitch.a.u;
import com.persianswitch.a.x$a;
import com.persianswitch.a.x;
import com.persianswitch.a.y;
import com.persianswitch.a.z;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.base.security.SecurityManager;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.webservice.exception.WSCallException;
import com.persianswitch.sdk.base.webservice.exception.WSConnectTimeoutException;
import com.persianswitch.sdk.base.webservice.exception.WSHttpStatusException;
import com.persianswitch.sdk.base.webservice.exception.WSParseResponseException;
import com.persianswitch.sdk.base.webservice.exception.WSSocketTimeoutException;
import com.persianswitch.sdk.base.webservice.trust.TrustManagerBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpEngine {
    public static final t a;
    private static HttpEngine b;
    private final u c;
    private final u d;

    static {
        HttpEngine.a = t.a("text/plain; charset=utf-8");
    }

    private HttpEngine(u arg1, u arg2) {
        super();
        this.c = arg1;
        this.d = arg2;
    }

    public static HttpEngine a(Context arg2, Config arg3) {
        if(HttpEngine.b == null) {
            HttpEngine.b = new HttpEngine(HttpEngine.a(HttpEngine.b(arg2, arg3)), HttpEngine.b(HttpEngine.b(arg2, arg3)));
        }

        return HttpEngine.b;
    }

    public InputStream a(String arg2) {
        try {
            return this.d.a(new a().a(arg2).a()).a().f().c();
        }
        catch(Exception ) {
            return null;
        }
    }

    private static u a(X509TrustManager arg4) {
        return new com.persianswitch.a.u$a().a(15, TimeUnit.SECONDS).b(60, TimeUnit.SECONDS).c(60, TimeUnit.SECONDS).a(false).a(HttpEngine.a(((TrustManager)arg4)), arg4).a(new APMBHostNameVerifier(d.a)).a();
    }

    private static SSLSocketFactory a(TrustManager arg4) {
        SSLSocketFactory v4_1;
        KeyManager[] v0 = null;
        try {
            SSLContext v1 = SSLContext.getInstance("TLS");
            v1.init(v0, new TrustManager[]{arg4}, ((SecureRandom)v0));
            v4_1 = v1.getSocketFactory();
        }
        catch(Exception v4) {
            v4.printStackTrace();
            v4_1 = ((SSLSocketFactory)v0);
        }

        return v4_1;
    }

    public HttpResult a(Context arg10, String arg11, String arg12, boolean arg13, byte[] arg14) {
        aa v11_1;
        long v7_1;
        z v7;
        HttpResult v0 = new HttpResult();
        String v1 = "";
        try {
            arg12 = SecurityManager.a(arg10).a(arg12, arg14);
        }
        catch(Exception v12) {
            SDKLog.b("HttpEngine", "error in encrypt request body", ((Throwable)v12), new Object[0]);
            arg12 = v1;
        }

        u v13 = arg13 ? this.d : this.c;
        x v11 = new a().a(arg11).a(y.a(HttpEngine.a, StringUtils.a(arg12))).a();
        int v12_1 = 3;
        long v1_1 = 30000;
        WSCallException v3 = null;
        z v4 = ((z)v3);
        while(true) {
            if(v12_1 > 0 && v1_1 > 0) {
                --v12_1;
                long v5 = System.currentTimeMillis();
                try {
                    v7 = v13.a(v11).a();
                }
                catch(ConnectException ) {
                    goto label_49;
                }
                catch(IOException ) {
                    goto label_44;
                }
                catch(Throwable v10) {
                    goto label_58;
                }

                try {
                    v0.a(v3);
                    break;
                }
                catch(Throwable v10) {
                }
                catch(IOException ) {
                }
                catch(ConnectException ) {
                    v4 = v7;
                    try {
                    label_49:
                        v0.a(new WSConnectTimeoutException());
                        v7_1 = 5000;
                    }
                    catch(Throwable v10) {
                        goto label_58;
                    }

                    try {
                        Thread.sleep(v7_1);
                        goto label_54;
                    }
                    catch(Throwable v10) {
                    label_58:
                        System.currentTimeMillis();
                        throw v10;
                    }
                    catch(InterruptedException ) {
                    label_54:
                        v1_1 -= System.currentTimeMillis() - v5;
                        continue;
                        try {
                        label_44:
                            v0.a(new WSSocketTimeoutException());
                        }
                        catch(Throwable v10) {
                            goto label_58;
                        }

                        System.currentTimeMillis();
                        return v0;
                    }
                }
            }

            goto label_60;
        }

        System.currentTimeMillis();
        v4 = v7;
    label_60:
        if(v4 == null) {
            return v0;
        }

        v0.a(v4.b());
        if(!v4.c()) {
            v0.a(new WSHttpStatusException(v0.b()));
            return v0;
        }

        try {
            v11_1 = v4.f();
        }
        catch(Exception ) {
            goto label_85;
        }
        catch(Throwable v10) {
            goto label_83;
        }

        try {
            v0.a(SecurityManager.a(arg10).b(v11_1.f(), arg14));
            if(v11_1 == null) {
                return v0;
            }

            goto label_76;
        }
        catch(Throwable v10) {
        }
        catch(Exception ) {
            aa v3_1 = v11_1;
            try {
            label_85:
                v0.a(new WSParseResponseException());
                if(v3_1 == null) {
                    return v0;
                }
            }
            catch(Throwable v10) {
            label_83:
                v11_1 = v3_1;
                goto label_91;
            }

            try {
                v3_1.close();
            }
            catch(Exception ) {
            }

            return v0;
        }

    label_91:
        if(v11_1 != null) {
            try {
                v11_1.close();
                goto label_93;
            }
            catch(Exception ) {
            label_93:
                throw v10;
            }
        }

        goto label_93;
        try {
        label_76:
            v11_1.close();
            return v0;
        }
        catch(Exception ) {
            return v0;
        }
    }

    private static X509TrustManager b(Context arg3, Config arg4) {
        TrustManagerBuilder v4 = new TrustManagerBuilder(arg3);
        try {
            v4.a("cert/root.cer").b().a("cert/inter.cer").b().a("cert/asanNet_ca.cer").b().a("cert/apms_ssl_test_ca.cer").c();
        }
        catch(Exception v3) {
            SDKLog.b("HttpEngine", "exception in build custom trust manager.", ((Throwable)v3), new Object[0]);
        }

        return v4.a();
    }

    private static u b(X509TrustManager arg4) {
        return new com.persianswitch.a.u$a().a(15, TimeUnit.SECONDS).b(60, TimeUnit.SECONDS).c(60, TimeUnit.SECONDS).a(true).a(HttpEngine.a(((TrustManager)arg4)), arg4).a(new APMBHostNameVerifier(d.a)).a();
    }
}

