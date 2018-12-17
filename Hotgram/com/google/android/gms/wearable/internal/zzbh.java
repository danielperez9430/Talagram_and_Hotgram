package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Channel$GetOutputStreamResult;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

final class zzbh implements GetOutputStreamResult {
    private final OutputStream zzcu;
    private final Status zzp;

    zzbh(Status arg1, @Nullable OutputStream arg2) {
        super();
        this.zzp = Preconditions.checkNotNull(arg1);
        this.zzcu = arg2;
    }

    @Nullable public final OutputStream getOutputStream() {
        return this.zzcu;
    }

    public final Status getStatus() {
        return this.zzp;
    }

    public final void release() {
        if(this.zzcu != null) {
            try {
                this.zzcu.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }
}

