package com.google.android.gms.ads.identifier;

import android.net.Uri$Builder;
import android.net.Uri;
import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

final class zza extends Thread {
    zza(AdvertisingIdClient arg1, Map arg2) {
        this.zzl = arg2;
        super();
    }

    public final void run() {
        String v4;
        String v3_1;
        String v2_3;
        StringBuilder v5;
        int v2_2;
        URLConnection v1_3;
        new zzc();
        Map v0 = this.zzl;
        Uri$Builder v1 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        Iterator v2 = v0.keySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            v1.appendQueryParameter(((String)v3), v0.get(v3));
        }

        String v0_1 = v1.build().toString();
        try {
            v1_3 = new URL(v0_1).openConnection();
        }
        catch(IndexOutOfBoundsException v1_1) {
            goto label_64;
        }
        catch(RuntimeException v1_2) {
            goto label_44;
        }

        try {
            v2_2 = ((HttpURLConnection)v1_3).getResponseCode();
        }
        catch(IndexOutOfBoundsException v1_1) {
            goto label_64;
        }
        catch(RuntimeException v1_2) {
            goto label_44;
        }
        catch(Throwable v2_1) {
            goto label_41;
        }

        try {
            if(v2_2 < 200 || v2_2 >= 300) {
                v5 = new StringBuilder(String.valueOf(v0_1).length() + 65);
                v5.append("Received non-success response code ");
                v5.append(v2_2);
                v5.append(" from pinging URL: ");
                v5.append(v0_1);
                Log.w("HttpUrlPinger", v5.toString());
            }

            goto label_38;
        }
        catch(Throwable v2_1) {
            try {
            label_41:
                ((HttpURLConnection)v1_3).disconnect();
                throw v2_1;
            label_38:
                ((HttpURLConnection)v1_3).disconnect();
                return;
            }
            catch(RuntimeException v1_2) {
            label_44:
                v2_3 = "HttpUrlPinger";
                v3_1 = ((Exception)v1_2).getMessage();
                v5 = new StringBuilder(String.valueOf(v0_1).length() + 27 + String.valueOf(v3_1).length());
                v4 = "Error while pinging URL: ";
            }
            catch(IndexOutOfBoundsException v1_1) {
            label_64:
                v2_3 = "HttpUrlPinger";
                v3_1 = v1_1.getMessage();
                v5 = new StringBuilder(String.valueOf(v0_1).length() + 32 + String.valueOf(v3_1).length());
                v4 = "Error while parsing ping URL: ";
            }
        }

        v5.append(v4);
        v5.append(v0_1);
        v5.append(". ");
        v5.append(v3_1);
        Log.w(v2_3, v5.toString(), ((Throwable)v1_2));
    }
}

