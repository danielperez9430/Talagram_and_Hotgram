package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor$AutoCloseOutputStream;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.Callable;

final class zzhh implements Callable {
    zzhh(zzhg arg1, ParcelFileDescriptor arg2, byte[] arg3) {
        this.zzfg = arg2;
        this.zzee = arg3;
        super();
    }

    public final Object call() {
        return this.zzd();
    }

    private final Boolean zzd() {
        Boolean v2_2;
        StringBuilder v5;
        String v3;
        StringBuilder v4;
        String v2;
        int v1 = 3;
        if(Log.isLoggable("WearableClient", v1)) {
            v2 = String.valueOf(this.zzfg);
            v4 = new StringBuilder(String.valueOf(v2).length() + 36);
            v4.append("processAssets: writing data to FD : ");
            v4.append(v2);
            Log.d("WearableClient", v4.toString());
        }

        ParcelFileDescriptor$AutoCloseOutputStream v0 = new ParcelFileDescriptor$AutoCloseOutputStream(this.zzfg);
        try {
            v0.write(this.zzee);
            v0.flush();
            if(Log.isLoggable("WearableClient", v1)) {
                v3 = String.valueOf(this.zzfg);
                v5 = new StringBuilder(String.valueOf(v3).length() + 27);
                v5.append("processAssets: wrote data: ");
                v5.append(v3);
                Log.d("WearableClient", v5.toString());
            }

            v2_2 = Boolean.valueOf(true);
        }
        catch(Throwable v2_1) {
        label_60:
            try {
                if(Log.isLoggable("WearableClient", v1)) {
                    v3 = String.valueOf(this.zzfg);
                    v5 = new StringBuilder(String.valueOf(v3).length() + 24);
                    v5.append("processAssets: closing: ");
                    v5.append(v3);
                    Log.d("WearableClient", v5.toString());
                }

                v0.close();
                goto label_111;
            }
            catch(IOException ) {
            label_111:
                throw v2_1;
            }
        }
        catch(IOException ) {
            try {
                v3 = String.valueOf(this.zzfg);
                v5 = new StringBuilder(String.valueOf(v3).length() + 36);
                v5.append("processAssets: writing data failed: ");
                v5.append(v3);
                Log.w("WearableClient", v5.toString());
            }
            catch(Throwable v2_1) {
                goto label_60;
            }

            try {
                if(Log.isLoggable("WearableClient", v1)) {
                    v2 = String.valueOf(this.zzfg);
                    v4 = new StringBuilder(String.valueOf(v2).length() + 24);
                    v4.append("processAssets: closing: ");
                    v4.append(v2);
                    Log.d("WearableClient", v4.toString());
                }

                v0.close();
                goto label_91;
            }
            catch(IOException ) {
            label_91:
                return Boolean.valueOf(false);
            }
        }

        try {
            if(Log.isLoggable("WearableClient", v1)) {
                v3 = String.valueOf(this.zzfg);
                v5 = new StringBuilder(String.valueOf(v3).length() + 24);
                v5.append("processAssets: closing: ");
                v5.append(v3);
                Log.d("WearableClient", v5.toString());
            }

            v0.close();
            return v2_2;
        }
        catch(IOException ) {
            return v2_2;
        }
    }
}

