package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;
import java.io.IOException;
import java.io.InputStream;

public final class zzci implements GetFdForAssetResult {
    private volatile boolean closed;
    private volatile InputStream zzct;
    private volatile ParcelFileDescriptor zzf;
    private final Status zzp;

    public zzci(Status arg2, ParcelFileDescriptor arg3) {
        super();
        this.closed = false;
        this.zzp = arg2;
        this.zzf = arg3;
    }

    public final ParcelFileDescriptor getFd() {
        if(!this.closed) {
            return this.zzf;
        }

        throw new IllegalStateException("Cannot access the file descriptor after release().");
    }

    public final InputStream getInputStream() {
        if(!this.closed) {
            if(this.zzf == null) {
                return null;
            }

            if(this.zzct == null) {
                this.zzct = new ParcelFileDescriptor$AutoCloseInputStream(this.zzf);
            }

            return this.zzct;
        }

        throw new IllegalStateException("Cannot access the input stream after release().");
    }

    public final Status getStatus() {
        return this.zzp;
    }

    public final void release() {
        if(this.zzf == null) {
            return;
        }

        if(!this.closed) {
            try {
                if(this.zzct != null) {
                    this.zzct.close();
                }
                else {
                    goto label_10;
                }

            label_12:
                this.closed = true;
                this.zzf = null;
                this.zzct = null;
                return;
            label_10:
                this.zzf.close();
                goto label_12;
            }
            catch(IOException ) {
                return;
            }
        }

        throw new IllegalStateException("releasing an already released result.");
    }
}

