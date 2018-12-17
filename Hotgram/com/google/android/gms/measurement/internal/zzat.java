package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzat extends zzez {
    private final SSLSocketFactory zzamq;

    public zzat(zzfa arg2) {
        zzfl v2;
        super(arg2);
        if(Build$VERSION.SDK_INT < 19) {
            v2 = new zzfl();
        }
        else {
            SSLSocketFactory v2_1 = null;
        }

        this.zzamq = ((SSLSocketFactory)v2);
    }

    public final Context getContext() {
        return super.getContext();
    }

    static byte[] zza(zzat arg0, HttpURLConnection arg1) {
        return zzat.zzb(arg1);
    }

    public final void zzaf() {
        super.zzaf();
    }

    @VisibleForTesting protected final HttpURLConnection zzb(URL arg3) {
        URLConnection v3 = arg3.openConnection();
        if((v3 instanceof HttpURLConnection)) {
            if(this.zzamq != null && ((v3 instanceof HttpsURLConnection))) {
                v3.setSSLSocketFactory(this.zzamq);
            }

            ((HttpURLConnection)v3).setDefaultUseCaches(false);
            ((HttpURLConnection)v3).setConnectTimeout(60000);
            ((HttpURLConnection)v3).setReadTimeout(61000);
            ((HttpURLConnection)v3).setInstanceFollowRedirects(false);
            ((HttpURLConnection)v3).setDoInput(true);
            return ((HttpURLConnection)v3);
        }

        throw new IOException("Failed to obtain HTTP connection");
    }

    private static byte[] zzb(HttpURLConnection arg5) {
        byte[] v0_2;
        Throwable v0_1;
        int v0;
        InputStream v5_1;
        ByteArrayOutputStream v1;
        try {
            v1 = new ByteArrayOutputStream();
            v5_1 = arg5.getInputStream();
            v0 = 1024;
        }
        catch(Throwable v5) {
            InputStream v4 = ((InputStream)v0);
            v0_1 = v5;
            v5_1 = v4;
            goto label_21;
        }

        try {
            v0_2 = new byte[v0];
            while(true) {
                int v2 = v5_1.read(v0_2);
                if(v2 <= 0) {
                    break;
                }

                v1.write(v0_2, 0, v2);
            }

            v0_2 = v1.toByteArray();
            if(v5_1 != null) {
                goto label_13;
            }

            return v0_2;
        }
        catch(Throwable v0_1) {
            goto label_21;
        }

    label_13:
        v5_1.close();
        return v0_2;
    label_21:
        if(v5_1 != null) {
            v5_1.close();
        }

        throw v0_1;
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    public final boolean zzfb() {
        NetworkInfo v0_1;
        ((zzez)this).zzcl();
        Object v0 = ((zzco)this).getContext().getSystemService("connectivity");
        try {
            v0_1 = ((ConnectivityManager)v0).getActiveNetworkInfo();
        }
        catch(SecurityException ) {
            v0_1 = null;
        }

        if(v0_1 != null && (v0_1.isConnected())) {
            return 1;
        }

        return 0;
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    public final zzfg zzjo() {
        return super.zzjo();
    }

    public final zzj zzjp() {
        return super.zzjp();
    }

    public final zzq zzjq() {
        return super.zzjq();
    }
}

