package com.google.android.gms.wearable.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.ChannelIOException;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

public final class zzbl extends OutputStream {
    @Nullable private volatile zzav zzcw;
    private final OutputStream zzcy;

    public zzbl(OutputStream arg1) {
        super();
        this.zzcy = Preconditions.checkNotNull(arg1);
    }

    public final void close() {
        try {
            this.zzcy.close();
            return;
        }
        catch(IOException v0) {
            throw this.zza(v0);
        }
    }

    public final void flush() {
        try {
            this.zzcy.flush();
            return;
        }
        catch(IOException v0) {
            throw this.zza(v0);
        }
    }

    public final void write(int arg2) {
        try {
            this.zzcy.write(arg2);
            return;
        }
        catch(IOException v2) {
            throw this.zza(v2);
        }
    }

    public final void write(byte[] arg2) {
        try {
            this.zzcy.write(arg2);
            return;
        }
        catch(IOException v2) {
            throw this.zza(v2);
        }
    }

    public final void write(byte[] arg2, int arg3, int arg4) {
        try {
            this.zzcy.write(arg2, arg3, arg4);
            return;
        }
        catch(IOException v2) {
            throw this.zza(v2);
        }
    }

    private final IOException zza(IOException arg4) {
        ChannelIOException v4;
        zzav v0 = this.zzcw;
        if(v0 != null) {
            if(Log.isLoggable("ChannelOutputStream", 2)) {
                Log.v("ChannelOutputStream", "Caught IOException, but channel has been closed. Translating to ChannelIOException.", ((Throwable)arg4));
            }

            v4 = new ChannelIOException("Channel closed unexpectedly before stream was finished", v0.zzg, v0.zzcj);
        }

        return ((IOException)v4);
    }

    final void zzc(zzav arg1) {
        this.zzcw = arg1;
    }
}

