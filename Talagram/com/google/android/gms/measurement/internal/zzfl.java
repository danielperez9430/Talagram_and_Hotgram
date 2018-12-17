package com.google.android.gms.measurement.internal;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class zzfl extends SSLSocketFactory {
    private final SSLSocketFactory zzaum;

    zzfl() {
        this(HttpsURLConnection.getDefaultSSLSocketFactory());
    }

    private zzfl(SSLSocketFactory arg1) {
        super();
        this.zzaum = arg1;
    }

    public final Socket createSocket() {
        return this.zza(this.zzaum.createSocket());
    }

    public final Socket createSocket(String arg2, int arg3) {
        return this.zza(this.zzaum.createSocket(arg2, arg3));
    }

    public final Socket createSocket(String arg2, int arg3, InetAddress arg4, int arg5) {
        return this.zza(this.zzaum.createSocket(arg2, arg3, arg4, arg5));
    }

    public final Socket createSocket(InetAddress arg2, int arg3) {
        return this.zza(this.zzaum.createSocket(arg2, arg3));
    }

    public final Socket createSocket(InetAddress arg2, int arg3, InetAddress arg4, int arg5) {
        return this.zza(this.zzaum.createSocket(arg2, arg3, arg4, arg5));
    }

    public final Socket createSocket(Socket arg2, String arg3, int arg4, boolean arg5) {
        return this.zza(this.zzaum.createSocket(arg2, arg3, arg4, arg5));
    }

    public final String[] getDefaultCipherSuites() {
        return this.zzaum.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzaum.getSupportedCipherSuites();
    }

    private final SSLSocket zza(SSLSocket arg2) {
        return new zzfm(this, arg2);
    }
}

