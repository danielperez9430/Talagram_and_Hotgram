package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.ChannelIOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public final class zzbj extends InputStream {
    private final InputStream zzcv;
    @Nullable private volatile zzav zzcw;

    public zzbj(InputStream arg1) {
        super();
        this.zzcv = Preconditions.checkNotNull(arg1);
    }

    public final int available() {
        return this.zzcv.available();
    }

    public final void close() {
        this.zzcv.close();
    }

    public final void mark(int arg2) {
        this.zzcv.mark(arg2);
    }

    public final boolean markSupported() {
        return this.zzcv.markSupported();
    }

    public final int read() {
        return this.zza(this.zzcv.read());
    }

    public final int read(byte[] arg2) {
        return this.zza(this.zzcv.read(arg2));
    }

    public final int read(byte[] arg2, int arg3, int arg4) {
        return this.zza(this.zzcv.read(arg2, arg3, arg4));
    }

    public final void reset() {
        this.zzcv.reset();
    }

    public final long skip(long arg2) {
        return this.zzcv.skip(arg2);
    }

    private final int zza(int arg4) {
        if(arg4 == -1) {
            zzav v0 = this.zzcw;
            if(v0 == null) {
            }
            else {
                throw new ChannelIOException("Channel closed unexpectedly before stream was finished", v0.zzg, v0.zzcj);
            }
        }

        return arg4;
    }

    final void zza(zzav arg1) {
        this.zzcw = Preconditions.checkNotNull(arg1);
    }
}

