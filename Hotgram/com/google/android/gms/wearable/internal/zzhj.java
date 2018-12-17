package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

final class zzhj implements Runnable {
    zzhj(zzhg arg1, Uri arg2, ResultHolder arg3, String arg4, long arg5, long arg7) {
        this.zzfi = arg1;
        this.zzco = arg2;
        this.zzfh = arg3;
        this.zzcs = arg4;
        this.zzcq = arg5;
        this.zzcr = arg7;
        super();
    }

    public final void run() {
        ParcelFileDescriptor v1_1;
        if(Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing sendFileToChannelTask");
        }

        if(!"file".equals(this.zzco.getScheme())) {
            Log.w("WearableClient", "Channel.sendFile used with non-file URI");
            this.zzfh.setFailedResult(new Status(10, "Channel.sendFile used with non-file URI"));
            return;
        }

        File v0 = new File(this.zzco.getPath());
        int v1 = 268435456;
        try {
            v1_1 = ParcelFileDescriptor.open(v0, v1);
        }
        catch(FileNotFoundException ) {
            String v0_1 = String.valueOf(v0);
            StringBuilder v3 = new StringBuilder(String.valueOf(v0_1).length() + 46);
            v3.append("File couldn\'t be opened for Channel.sendFile: ");
            v3.append(v0_1);
            Log.w("WearableClient", v3.toString());
            this.zzfh.setFailedResult(new Status(13));
            return;
        }

        try {
            this.zzfi.getService().zza(new zzhc(this.zzfh), this.zzcs, v1_1, this.zzcq, this.zzcr);
        }
        catch(Throwable v0_2) {
        label_47:
            try {
                v1_1.close();
            }
            catch(IOException v1_2) {
                Log.w("WearableClient", "Failed to close sourceFd", ((Throwable)v1_2));
            }

            throw v0_2;
        }
        catch(RemoteException v0_3) {
            try {
                Log.w("WearableClient", "Channel.sendFile failed.", ((Throwable)v0_3));
                this.zzfh.setFailedResult(new Status(8));
            }
            catch(Throwable v0_2) {
                goto label_47;
            }

            try {
                v1_1.close();
                return;
            }
            catch(IOException v0_4) {
                Log.w("WearableClient", "Failed to close sourceFd", ((Throwable)v0_4));
                return;
            }
        }

        try {
            v1_1.close();
            return;
        }
        catch(IOException v0_4) {
            Log.w("WearableClient", "Failed to close sourceFd", ((Throwable)v0_4));
            return;
        }
    }
}

