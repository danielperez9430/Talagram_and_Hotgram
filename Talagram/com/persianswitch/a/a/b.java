package com.persianswitch.a.a;

import com.persianswitch.a.k;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

public final class b {
    private final List a;
    private int b;
    private boolean c;
    private boolean d;

    public b(List arg2) {
        super();
        this.b = 0;
        this.a = arg2;
    }

    public k a(SSLSocket arg5) {
        Object v2;
        int v0 = this.b;
        int v1 = this.a.size();
        while(true) {
            if(v0 < v1) {
                v2 = this.a.get(v0);
                if(((k)v2).a(arg5)) {
                    this.b = v0 + 1;
                }
                else {
                    ++v0;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_14;
        }

        v2 = null;
    label_14:
        if(v2 != null) {
            this.c = this.b(arg5);
            d.a.a(((k)v2), arg5, this.d);
            return ((k)v2);
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Unable to find acceptable protocols. isFallback=");
        v1_1.append(this.d);
        v1_1.append(", modes=");
        v1_1.append(this.a);
        v1_1.append(", supported protocols=");
        v1_1.append(Arrays.toString(arg5.getEnabledProtocols()));
        throw new UnknownServiceException(v1_1.toString());
    }

    public boolean a(IOException arg5) {
        boolean v0 = true;
        this.d = true;
        if(!this.c) {
            return 0;
        }

        if((arg5 instanceof ProtocolException)) {
            return 0;
        }

        if((arg5 instanceof InterruptedIOException)) {
            return 0;
        }

        boolean v1 = arg5 instanceof SSLHandshakeException;
        if((v1) && ((arg5.getCause() instanceof CertificateException))) {
            return 0;
        }

        if((arg5 instanceof SSLPeerUnverifiedException)) {
            return 0;
        }

        if(!v1) {
            if((arg5 instanceof SSLProtocolException)) {
            }
            else {
                v0 = false;
            }
        }

        return v0;
    }

    private boolean b(SSLSocket arg3) {
        int v0;
        for(v0 = this.b; v0 < this.a.size(); ++v0) {
            if(this.a.get(v0).a(arg3)) {
                return 1;
            }
        }

        return 0;
    }
}

