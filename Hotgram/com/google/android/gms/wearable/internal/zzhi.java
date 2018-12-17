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

final class zzhi implements Runnable {
    zzhi(zzhg arg1, Uri arg2, ResultHolder arg3, boolean arg4, String arg5) {
        this.zzfi = arg1;
        this.zzco = arg2;
        this.zzfh = arg3;
        this.zzcp = arg4;
        this.zzcs = arg5;
        super();
    }

    public final void run() {
        ParcelFileDescriptor v1_1;
        if(Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing receiveFileFromChannelTask");
        }

        if(!"file".equals(this.zzco.getScheme())) {
            Log.w("WearableClient", "Channel.receiveFile used with non-file URI");
            this.zzfh.setFailedResult(new Status(10, "Channel.receiveFile used with non-file URI"));
            return;
        }

        File v0 = new File(this.zzco.getPath());
        int v1 = 671088640;
        int v2 = this.zzcp ? 33554432 : 0;
        v1 |= v2;
        try {
            v1_1 = ParcelFileDescriptor.open(v0, v1);
        }
        catch(FileNotFoundException ) {
            String v0_1 = String.valueOf(v0);
            StringBuilder v3 = new StringBuilder(String.valueOf(v0_1).length() + 49);
            v3.append("File couldn\'t be opened for Channel.receiveFile: ");
            v3.append(v0_1);
            Log.w("WearableClient", v3.toString());
            this.zzfh.setFailedResult(new Status(13));
            return;
        }

        try {
            this.zzfi.getService().zza(new zzhf(this.zzfh), this.zzcs, v1_1);
        }
        catch(Throwable v0_2) {
        label_49:
            try {
                v1_1.close();
            }
            catch(IOException v1_2) {
                Log.w("WearableClient", "Failed to close targetFd", ((Throwable)v1_2));
            }

            throw v0_2;
        }
        catch(RemoteException v0_3) {
            try {
                Log.w("WearableClient", "Channel.receiveFile failed.", ((Throwable)v0_3));
                this.zzfh.setFailedResult(new Status(8));
            }
            catch(Throwable v0_2) {
                goto label_49;
            }

            try {
                v1_1.close();
                return;
            }
            catch(IOException v0_4) {
                Log.w("WearableClient", "Failed to close targetFd", ((Throwable)v0_4));
                return;
            }
        }

        try {
            v1_1.close();
            return;
        }
        catch(IOException v0_4) {
            Log.w("WearableClient", "Failed to close targetFd", ((Throwable)v0_4));
            return;
        }
    }
}

