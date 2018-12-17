package com.google.android.gms.common.net;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.PrivateKey;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SSLCertificateSocketFactory extends SSLSocketFactory {
    @VisibleForTesting private final Context mContext;
    private static final TrustManager[] zzvf;
    @VisibleForTesting private SSLSocketFactory zzvg;
    @VisibleForTesting private SSLSocketFactory zzvh;
    @VisibleForTesting private TrustManager[] zzvi;
    @VisibleForTesting private KeyManager[] zzvj;
    @VisibleForTesting private byte[] zzvk;
    @VisibleForTesting private byte[] zzvl;
    @VisibleForTesting private PrivateKey zzvm;
    @VisibleForTesting private final int zzvn;
    @VisibleForTesting private final boolean zzvo;
    @VisibleForTesting private final boolean zzvp;
    @VisibleForTesting private final String zzvq;

    static {
        SSLCertificateSocketFactory.zzvf = new TrustManager[]{new zza()};
    }

    private SSLCertificateSocketFactory(Context arg2, int arg3, boolean arg4, boolean arg5, String arg6) {
        super();
        this.zzvg = null;
        this.zzvh = null;
        this.zzvi = null;
        this.zzvj = null;
        this.zzvk = null;
        this.zzvl = null;
        this.zzvm = null;
        this.mContext = arg2.getApplicationContext();
        this.zzvn = arg3;
        this.zzvo = arg4;
        this.zzvp = arg5;
        this.zzvq = arg6;
    }

    public Socket createSocket() {
        Socket v0 = this.zzcx().createSocket();
        SSLCertificateSocketFactory.zza(v0, this.zzvk);
        SSLCertificateSocketFactory.zzb(v0, this.zzvl);
        SSLCertificateSocketFactory.zza(v0, this.zzvn);
        SSLCertificateSocketFactory.zza(v0, this.zzvm);
        return v0;
    }

    public Socket createSocket(String arg2, int arg3) {
        Socket v3 = this.zzcx().createSocket(arg2, arg3);
        SSLCertificateSocketFactory.zza(v3, this.zzvk);
        SSLCertificateSocketFactory.zzb(v3, this.zzvl);
        SSLCertificateSocketFactory.zza(v3, this.zzvn);
        SSLCertificateSocketFactory.zza(v3, this.zzvm);
        if(this.zzvp) {
            SSLCertificateSocketFactory.verifyHostname(v3, arg2);
        }

        return v3;
    }

    public Socket createSocket(String arg2, int arg3, InetAddress arg4, int arg5) {
        Socket v3 = this.zzcx().createSocket(arg2, arg3, arg4, arg5);
        SSLCertificateSocketFactory.zza(v3, this.zzvk);
        SSLCertificateSocketFactory.zzb(v3, this.zzvl);
        SSLCertificateSocketFactory.zza(v3, this.zzvn);
        SSLCertificateSocketFactory.zza(v3, this.zzvm);
        if(this.zzvp) {
            SSLCertificateSocketFactory.verifyHostname(v3, arg2);
        }

        return v3;
    }

    public Socket createSocket(InetAddress arg2, int arg3) {
        Socket v2 = this.zzcx().createSocket(arg2, arg3);
        SSLCertificateSocketFactory.zza(v2, this.zzvk);
        SSLCertificateSocketFactory.zzb(v2, this.zzvl);
        SSLCertificateSocketFactory.zza(v2, this.zzvn);
        SSLCertificateSocketFactory.zza(v2, this.zzvm);
        return v2;
    }

    public Socket createSocket(InetAddress arg2, int arg3, InetAddress arg4, int arg5) {
        Socket v2 = this.zzcx().createSocket(arg2, arg3, arg4, arg5);
        SSLCertificateSocketFactory.zza(v2, this.zzvk);
        SSLCertificateSocketFactory.zzb(v2, this.zzvl);
        SSLCertificateSocketFactory.zza(v2, this.zzvn);
        SSLCertificateSocketFactory.zza(v2, this.zzvm);
        return v2;
    }

    public Socket createSocket(Socket arg2, String arg3, int arg4, boolean arg5) {
        arg2 = this.zzcx().createSocket(arg2, arg3, arg4, arg5);
        SSLCertificateSocketFactory.zza(arg2, this.zzvk);
        SSLCertificateSocketFactory.zzb(arg2, this.zzvl);
        SSLCertificateSocketFactory.zza(arg2, this.zzvn);
        SSLCertificateSocketFactory.zza(arg2, this.zzvm);
        if(this.zzvp) {
            SSLCertificateSocketFactory.verifyHostname(arg2, arg3);
        }

        return arg2;
    }

    public byte[] getAlpnSelectedProtocol(Socket arg5) {
        StringBuilder v3;
        String v5;
        try {
            return arg5.getClass().getMethod("getAlpnSelectedProtocol").invoke(arg5);
        }
        catch(IllegalAccessException v0) {
            v5 = String.valueOf(arg5.getClass());
            v3 = new StringBuilder(String.valueOf(v5).length() + 43);
            v3.append(v5);
            v3.append(" does not implement getAlpnSelectedProtocol");
            throw new IllegalArgumentException(v3.toString(), ((Throwable)v0));
        }
        catch(InvocationTargetException v0_1) {
            Throwable v1 = v0_1.getCause();
            if((v1 instanceof RuntimeException)) {
                throw v1;
            }

            v5 = String.valueOf(arg5.getClass());
            v3 = new StringBuilder(String.valueOf(v5).length() + 44);
            v3.append("Failed to invoke getAlpnSelectedProtocol on ");
            v3.append(v5);
            throw new RuntimeException(v3.toString(), ((Throwable)v0_1));
        }
    }

    public static SocketFactory getDefault(Context arg7, int arg8) {
        return new SSLCertificateSocketFactory(arg7, arg8, false, true, null);
    }

    public String[] getDefaultCipherSuites() {
        return this.zzcx().getDefaultCipherSuites();
    }

    public static SSLSocketFactory getDefaultWithCacheDir(int arg7, Context arg8, String arg9) {
        return new SSLCertificateSocketFactory(arg8, arg7, true, true, arg9);
    }

    public static SSLSocketFactory getDefaultWithSessionCache(int arg7, Context arg8) {
        return new SSLCertificateSocketFactory(arg8, arg7, true, true, null);
    }

    public static SSLSocketFactory getInsecure(Context arg7, int arg8, boolean arg9) {
        return new SSLCertificateSocketFactory(arg7, arg8, arg9, false, null);
    }

    public byte[] getNpnSelectedProtocol(Socket arg5) {
        StringBuilder v3;
        String v5;
        try {
            return arg5.getClass().getMethod("getNpnSelectedProtocol").invoke(arg5);
        }
        catch(IllegalAccessException v0) {
            v5 = String.valueOf(arg5.getClass());
            v3 = new StringBuilder(String.valueOf(v5).length() + 42);
            v3.append(v5);
            v3.append(" does not implement getNpnSelectedProtocol");
            throw new IllegalArgumentException(v3.toString(), ((Throwable)v0));
        }
        catch(InvocationTargetException v0_1) {
            Throwable v1 = v0_1.getCause();
            if((v1 instanceof RuntimeException)) {
                throw v1;
            }

            v5 = String.valueOf(arg5.getClass());
            v3 = new StringBuilder(String.valueOf(v5).length() + 43);
            v3.append("Failed to invoke getNpnSelectedProtocol on ");
            v3.append(v5);
            throw new RuntimeException(v3.toString(), ((Throwable)v0_1));
        }
    }

    public String[] getSupportedCipherSuites() {
        return this.zzcx().getSupportedCipherSuites();
    }

    public void setAlpnProtocols(byte[][] arg1) {
        this.zzvl = SSLCertificateSocketFactory.zza(arg1);
    }

    public void setChannelIdPrivateKey(PrivateKey arg1) {
        this.zzvm = arg1;
    }

    public void setHostname(Socket arg7, String arg8) {
        StringBuilder v2;
        String v7;
        try {
            arg7.getClass().getMethod("setHostname", String.class).invoke(arg7, arg8);
            return;
        }
        catch(IllegalAccessException v8) {
            v7 = String.valueOf(arg7.getClass());
            v2 = new StringBuilder(String.valueOf(v7).length() + 31);
            v2.append(v7);
            v2.append(" does not implement setHostname");
            throw new IllegalArgumentException(v2.toString(), ((Throwable)v8));
        }
        catch(InvocationTargetException v8_1) {
            Throwable v0 = v8_1.getCause();
            if((v0 instanceof RuntimeException)) {
                throw v0;
            }

            v7 = String.valueOf(arg7.getClass());
            v2 = new StringBuilder(String.valueOf(v7).length() + 32);
            v2.append("Failed to invoke setHostname on ");
            v2.append(v7);
            throw new RuntimeException(v2.toString(), ((Throwable)v8_1));
        }
    }

    public void setKeyManagers(KeyManager[] arg1) {
        this.zzvj = arg1;
        this.zzvh = null;
        this.zzvg = null;
    }

    public void setNpnProtocols(byte[][] arg1) {
        this.zzvk = SSLCertificateSocketFactory.zza(arg1);
    }

    public void setSoWriteTimeout(Socket arg7, int arg8) {
        StringBuilder v2;
        String v7;
        try {
            arg7.getClass().getMethod("setSoWriteTimeout", Integer.TYPE).invoke(arg7, Integer.valueOf(arg8));
            return;
        }
        catch(IllegalAccessException v8) {
            v7 = String.valueOf(arg7.getClass());
            v2 = new StringBuilder(String.valueOf(v7).length() + 37);
            v2.append(v7);
            v2.append(" does not implement setSoWriteTimeout");
            throw new IllegalArgumentException(v2.toString(), ((Throwable)v8));
        }
        catch(InvocationTargetException v8_1) {
            Throwable v0 = v8_1.getCause();
            if(!(v0 instanceof SocketException)) {
                if((v0 instanceof RuntimeException)) {
                    throw v0;
                }

                v7 = String.valueOf(arg7.getClass());
                v2 = new StringBuilder(String.valueOf(v7).length() + 38);
                v2.append("Failed to invoke setSoWriteTimeout on ");
                v2.append(v7);
                throw new RuntimeException(v2.toString(), ((Throwable)v8_1));
            }

            throw v0;
        }
    }

    public void setTrustManagers(TrustManager[] arg1) {
        this.zzvi = arg1;
        this.zzvh = null;
    }

    public void setUseSessionTickets(Socket arg7, boolean arg8) {
        StringBuilder v2;
        String v7;
        try {
            arg7.getClass().getMethod("setUseSessionTickets", Boolean.TYPE).invoke(arg7, Boolean.valueOf(arg8));
            return;
        }
        catch(IllegalAccessException v8) {
            v7 = String.valueOf(arg7.getClass());
            v2 = new StringBuilder(String.valueOf(v7).length() + 40);
            v2.append(v7);
            v2.append(" does not implement setUseSessionTickets");
            throw new IllegalArgumentException(v2.toString(), ((Throwable)v8));
        }
        catch(InvocationTargetException v8_1) {
            Throwable v0 = v8_1.getCause();
            if((v0 instanceof RuntimeException)) {
                throw v0;
            }

            v7 = String.valueOf(arg7.getClass());
            v2 = new StringBuilder(String.valueOf(v7).length() + 41);
            v2.append("Failed to invoke setUseSessionTickets on ");
            v2.append(v7);
            throw new RuntimeException(v2.toString(), ((Throwable)v8_1));
        }
    }

    public static void verifyHostname(Socket arg2, String arg3) {
        if((arg2 instanceof SSLSocket)) {
            ((SSLSocket)arg2).startHandshake();
            SSLSession v2 = ((SSLSocket)arg2).getSession();
            if(v2 != null) {
                if(!HttpsURLConnection.getDefaultHostnameVerifier().verify(arg3, v2)) {
                    String v0 = "Cannot verify hostname: ";
                    arg3 = String.valueOf(arg3);
                    arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
                    throw new SSLPeerUnverifiedException(arg3);
                }

                return;
            }

            throw new SSLException("Cannot verify SSL socket without session");
        }

        throw new IllegalArgumentException("Attempt to verify non-SSL socket");
    }

    @VisibleForTesting private static void zza(Socket arg6, int arg7) {
        StringBuilder v2;
        String v6;
        if(arg6 != null) {
            try {
                arg6.getClass().getMethod("setHandshakeTimeout", Integer.TYPE).invoke(arg6, Integer.valueOf(arg7));
            }
            catch(IllegalAccessException v7) {
                v6 = String.valueOf(arg6.getClass());
                v2 = new StringBuilder(String.valueOf(v6).length() + 45);
                v2.append(v6);
                v2.append(" does not implement setSocketHandshakeTimeout");
                throw new IllegalArgumentException(v2.toString(), ((Throwable)v7));
            }
            catch(InvocationTargetException v7_1) {
                Throwable v0 = v7_1.getCause();
                if((v0 instanceof RuntimeException)) {
                    throw v0;
                }
                else {
                    v6 = String.valueOf(arg6.getClass());
                    v2 = new StringBuilder(String.valueOf(v6).length() + 46);
                    v2.append("Failed to invoke setSocketHandshakeTimeout on ");
                    v2.append(v6);
                    throw new RuntimeException(v2.toString(), ((Throwable)v7_1));
                }
            }
        }
    }

    @VisibleForTesting private static void zza(Socket arg6, PrivateKey arg7) {
        StringBuilder v2;
        String v6;
        if(arg6 != null) {
            try {
                arg6.getClass().getMethod("setChannelIdPrivateKey", PrivateKey.class).invoke(arg6, arg7);
            }
            catch(IllegalAccessException v7) {
                v6 = String.valueOf(arg6.getClass());
                v2 = new StringBuilder(String.valueOf(v6).length() + 42);
                v2.append(v6);
                v2.append(" does not implement setChannelIdPrivateKey");
                throw new IllegalArgumentException(v2.toString(), ((Throwable)v7));
            }
            catch(InvocationTargetException v7_1) {
                Throwable v0 = v7_1.getCause();
                if((v0 instanceof RuntimeException)) {
                    throw v0;
                }
                else {
                    v6 = String.valueOf(arg6.getClass());
                    v2 = new StringBuilder(String.valueOf(v6).length() + 43);
                    v2.append("Failed to invoke setChannelIdPrivateKey on ");
                    v2.append(v6);
                    throw new RuntimeException(v2.toString(), ((Throwable)v7_1));
                }
            }
        }
    }

    @VisibleForTesting private static void zza(Socket arg6, byte[] arg7) {
        StringBuilder v2;
        String v6;
        if(arg6 != null) {
            try {
                arg6.getClass().getMethod("setNpnProtocols", byte[].class).invoke(arg6, arg7);
            }
            catch(IllegalAccessException v7) {
                v6 = String.valueOf(arg6.getClass());
                v2 = new StringBuilder(String.valueOf(v6).length() + 35);
                v2.append(v6);
                v2.append(" does not implement setNpnProtocols");
                throw new IllegalArgumentException(v2.toString(), ((Throwable)v7));
            }
            catch(InvocationTargetException v7_1) {
                Throwable v0 = v7_1.getCause();
                if((v0 instanceof RuntimeException)) {
                    throw v0;
                }
                else {
                    v6 = String.valueOf(arg6.getClass());
                    v2 = new StringBuilder(String.valueOf(v6).length() + 36);
                    v2.append("Failed to invoke setNpnProtocols on ");
                    v2.append(v6);
                    throw new RuntimeException(v2.toString(), ((Throwable)v7_1));
                }
            }
        }
    }

    private static byte[] zza(byte[][] arg10) {
        byte[] v4;
        if(arg10.length == 0) {
            goto label_53;
        }

        int v0 = arg10.length;
        int v2 = 0;
        int v3 = 0;
        while(true) {
            if(v2 >= v0) {
                goto label_29;
            }

            v4 = arg10[v2];
            if(v4.length != 0 && v4.length <= 255) {
                v3 += v4.length + 1;
                ++v2;
                continue;
            }

            break;
        }

        v0 = v4.length;
        StringBuilder v2_1 = new StringBuilder(44);
        v2_1.append("s.length == 0 || s.length > 255: ");
        v2_1.append(v0);
        throw new IllegalArgumentException(v2_1.toString());
    label_29:
        byte[] v0_1 = new byte[v3];
        v2 = arg10.length;
        v3 = 0;
        int v4_1;
        for(v4_1 = 0; v3 < v2; v4_1 = v7) {
            byte[] v5 = arg10[v3];
            int v6 = v4_1 + 1;
            v0_1[v4_1] = ((byte)v5.length);
            v4_1 = v5.length;
            int v7 = v6;
            v6 = 0;
            while(v6 < v4_1) {
                v0_1[v7] = v5[v6];
                ++v6;
                ++v7;
            }

            ++v3;
        }

        return v0_1;
    label_53:
        throw new IllegalArgumentException("items.length == 0");
    }

    @VisibleForTesting private static void zzb(Socket arg6, byte[] arg7) {
        StringBuilder v2;
        String v6;
        if(arg6 != null) {
            try {
                arg6.getClass().getMethod("setAlpnProtocols", byte[].class).invoke(arg6, arg7);
            }
            catch(IllegalAccessException v7) {
                v6 = String.valueOf(arg6.getClass());
                v2 = new StringBuilder(String.valueOf(v6).length() + 36);
                v2.append(v6);
                v2.append(" does not implement setAlpnProtocols");
                throw new IllegalArgumentException(v2.toString(), ((Throwable)v7));
            }
            catch(InvocationTargetException v7_1) {
                Throwable v0 = v7_1.getCause();
                if((v0 instanceof RuntimeException)) {
                    throw v0;
                }
                else {
                    v6 = String.valueOf(arg6.getClass());
                    v2 = new StringBuilder(String.valueOf(v6).length() + 37);
                    v2.append("Failed to invoke setAlpnProtocols on ");
                    v2.append(v6);
                    throw new RuntimeException(v2.toString(), ((Throwable)v7_1));
                }
            }
        }
    }

    @VisibleForTesting private final SSLSocketFactory zzcx() {
        SSLSocketFactory v0_1;
        __monitor_enter(this);
        try {
            if(this.zzvp) {
                goto label_18;
            }

            if(this.zzvg == null) {
                Log.w("SSLCertificateSocketFactory", "Bypassing SSL security checks at caller\'s request");
                this.zzvg = SocketFactoryCreator.getInstance().makeSocketFactory(this.mContext, this.zzvj, SSLCertificateSocketFactory.zzvf, this.zzvo);
            }

            v0_1 = this.zzvg;
        }
        catch(Throwable v0) {
            goto label_43;
        }

        __monitor_exit(this);
        return v0_1;
        try {
        label_18:
            if(this.zzvq != null) {
                if(this.zzvh == null) {
                    v0_1 = SocketFactoryCreator.getInstance().makeSocketFactoryWithCacheDir(this.mContext, this.zzvj, this.zzvi, this.zzvq);
                    goto label_28;
                }
            }
            else if(this.zzvh == null) {
                v0_1 = SocketFactoryCreator.getInstance().makeSocketFactory(this.mContext, this.zzvj, this.zzvi, this.zzvo);
            label_28:
                this.zzvh = v0_1;
            }

            v0_1 = this.zzvh;
        }
        catch(Throwable v0) {
        label_43:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }
}

