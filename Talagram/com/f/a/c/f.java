package com.f.a.c;

import java.net.InetAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class f extends SSLSocketFactory {
    private final SSLSocketFactory a;
    private final boolean b;
    private final boolean c;

    public f() {
        String[] v1;
        super();
        this.a = HttpsURLConnection.getDefaultSSLSocketFactory();
        int v0 = 0;
        try {
            v1 = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
        }
        catch(NoSuchAlgorithmException ) {
            v1 = new String[0];
        }

        int v2 = v1.length;
        boolean v3 = false;
        boolean v4 = false;
        while(v0 < v2) {
            String v5 = v1[v0];
            if(v5.equals("TLSv1.1")) {
                v3 = true;
            }
            else if(v5.equals("TLSv1.2")) {
                v4 = true;
            }

            ++v0;
        }

        this.b = v3;
        this.c = v4;
    }

    private Socket a(Socket arg3) {
        if(!(arg3 instanceof SSLSocket)) {
            return arg3;
        }

        HashSet v0 = new HashSet(Arrays.asList(((SSLSocket)arg3).getEnabledProtocols()));
        if(this.b) {
            ((Set)v0).add("TLSv1.1");
        }

        if(this.c) {
            ((Set)v0).add("TLSv1.2");
        }

        ((SSLSocket)arg3).setEnabledProtocols(((Set)v0).toArray(new String[0]));
        return arg3;
    }

    public Socket createSocket(String arg2, int arg3) {
        return this.a(this.a.createSocket(arg2, arg3));
    }

    public Socket createSocket(String arg2, int arg3, InetAddress arg4, int arg5) {
        return this.a(this.a.createSocket(arg2, arg3, arg4, arg5));
    }

    public Socket createSocket(InetAddress arg2, int arg3) {
        return this.a(this.a.createSocket(arg2, arg3));
    }

    public Socket createSocket(InetAddress arg2, int arg3, InetAddress arg4, int arg5) {
        return this.a(this.a.createSocket(arg2, arg3, arg4, arg5));
    }

    public Socket createSocket(Socket arg2, String arg3, int arg4, boolean arg5) {
        return this.a(this.a.createSocket(arg2, arg3, arg4, arg5));
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}

