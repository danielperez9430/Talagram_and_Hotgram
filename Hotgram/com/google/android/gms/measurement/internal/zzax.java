package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

final class zzax implements Runnable {
    private final String packageName;
    private final URL url;
    private final byte[] zzamv;
    private final zzav zzamw;
    private final Map zzamx;

    public zzax(zzat arg1, String arg2, URL arg3, byte[] arg4, Map arg5, zzav arg6) {
        this.zzamy = arg1;
        super();
        Preconditions.checkNotEmpty(arg2);
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg6);
        this.url = arg3;
        this.zzamv = arg4;
        this.zzamw = arg6;
        this.packageName = arg2;
        this.zzamx = arg5;
    }

    public final void run() {
        zzaw v1;
        zzbo v0_2;
        byte[] v9;
        int v7;
        IOException v8;
        OutputStream v4_1;
        byte[] v3_3;
        Map v10;
        HttpURLConnection v2;
        this.zzamy.zzgc();
        OutputStream v0 = null;
        try {
            v2 = this.zzamy.zzb(this.url);
        }
        catch(Throwable v3) {
            v2 = ((HttpURLConnection)v0);
            v10 = ((Map)v2);
            goto label_91;
        }
        catch(IOException v3_1) {
            v2 = ((HttpURLConnection)v0);
            v10 = ((Map)v2);
            goto label_121;
        }

        try {
            if(this.zzamx != null) {
                Iterator v3_2 = this.zzamx.entrySet().iterator();
                while(v3_2.hasNext()) {
                    Object v4 = v3_2.next();
                    v2.addRequestProperty(((Map$Entry)v4).getKey(), ((Map$Entry)v4).getValue());
                }
            }

            if(this.zzamv != null) {
                v3_3 = this.zzamy.zzjo().zzb(this.zzamv);
                this.zzamy.zzgo().zzjl().zzg("Uploading data. size", Integer.valueOf(v3_3.length));
                v2.setDoOutput(true);
                v2.addRequestProperty("Content-Encoding", "gzip");
                v2.setFixedLengthStreamingMode(v3_3.length);
                v2.connect();
                v4_1 = v2.getOutputStream();
                goto label_41;
            }

            goto label_53;
        }
        catch(Throwable v3) {
            goto label_83;
        }
        catch(IOException v3_1) {
            goto label_86;
        }

        try {
        label_41:
            v4_1.write(v3_3);
            v4_1.close();
        }
        catch(Throwable v3) {
            v10 = ((Map)v0);
            v0 = v4_1;
            goto label_91;
        }
        catch(IOException v3_1) {
            v10 = ((Map)v0);
            v8 = v3_1;
            v0 = v4_1;
            goto label_122;
        }

        try {
        label_53:
            v7 = v2.getResponseCode();
            goto label_54;
        }
        catch(Throwable v3) {
        label_83:
            v10 = ((Map)v0);
        }
        catch(IOException v3_1) {
        label_86:
            v10 = ((Map)v0);
        label_121:
            v8 = v3_1;
        label_122:
            v7 = 0;
            goto label_123;
        }

    label_91:
        v7 = 0;
        goto label_92;
        try {
        label_54:
            v10 = v2.getHeaderFields();
        }
        catch(Throwable v3) {
            v10 = ((Map)v0);
            goto label_92;
        }
        catch(IOException v3_1) {
            v10 = ((Map)v0);
            goto label_80;
        }

        try {
            v9 = zzat.zza(this.zzamy, v2);
            if(v2 == null) {
                goto label_59;
            }

            goto label_58;
        }
        catch(Throwable v3) {
        }
        catch(IOException v3_1) {
        label_80:
            v8 = v3_1;
        label_123:
            if(v0 != null) {
                try {
                    v0.close();
                }
                catch(IOException v0_1) {
                    this.zzamy.zzgo().zzjd().zze("Error closing HTTP compressed POST connection output stream. appId", zzap.zzbv(this.packageName), v0_1);
                }

                goto label_134;
            }
            else {
            label_134:
                if(v2 != null) {
                    v2.disconnect();
                }

                v0_2 = this.zzamy.zzgn();
                v1 = new zzaw(this.packageName, this.zzamw, v7, ((Throwable)v8), null, v10, null);
                goto label_69;
            }
        }

    label_92:
        if(v0 != null) {
            try {
                v0.close();
            }
            catch(IOException v0_1) {
                this.zzamy.zzgo().zzjd().zze("Error closing HTTP compressed POST connection output stream. appId", zzap.zzbv(this.packageName), v0_1);
            }
        }

        if(v2 != null) {
            v2.disconnect();
        }

        this.zzamy.zzgn().zzc(new zzaw(this.packageName, this.zzamw, v7, null, null, v10, null));
        throw v3;
    label_58:
        v2.disconnect();
    label_59:
        v0_2 = this.zzamy.zzgn();
        v1 = new zzaw(this.packageName, this.zzamw, v7, null, v9, v10, null);
    label_69:
        v0_2.zzc(((Runnable)v1));
    }
}

