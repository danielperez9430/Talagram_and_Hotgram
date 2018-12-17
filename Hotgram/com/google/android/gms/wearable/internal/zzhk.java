package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.Nullable;

@VisibleForTesting public final class zzhk extends zzen {
    private final IntentFilter[] zzba;
    @Nullable private final String zzbb;
    private ListenerHolder zzfj;
    private ListenerHolder zzfk;
    private ListenerHolder zzfl;
    private ListenerHolder zzfm;
    private ListenerHolder zzfn;
    private ListenerHolder zzfo;
    private ListenerHolder zzfp;
    private ListenerHolder zzfq;

    private zzhk(IntentFilter[] arg1, @Nullable String arg2) {
        super();
        this.zzba = Preconditions.checkNotNull(arg1);
        this.zzbb = arg2;
    }

    public final void clear() {
        zzhk.zza(null);
        this.zzfj = null;
        zzhk.zza(null);
        this.zzfk = null;
        zzhk.zza(this.zzfl);
        this.zzfl = null;
        zzhk.zza(this.zzfm);
        this.zzfm = null;
        zzhk.zza(null);
        this.zzfn = null;
        zzhk.zza(null);
        this.zzfo = null;
        zzhk.zza(this.zzfp);
        this.zzfp = null;
        zzhk.zza(this.zzfq);
        this.zzfq = null;
    }

    public final void onConnectedNodes(List arg1) {
    }

    public static zzhk zza(ListenerHolder arg1, String arg2, IntentFilter[] arg3) {
        zzhk v0 = new zzhk(arg3, Preconditions.checkNotNull(arg2));
        v0.zzfp = Preconditions.checkNotNull(arg1);
        return v0;
    }

    public static zzhk zza(ListenerHolder arg2, IntentFilter[] arg3) {
        zzhk v0 = new zzhk(arg3, null);
        v0.zzfl = Preconditions.checkNotNull(arg2);
        return v0;
    }

    private static void zza(ListenerHolder arg0) {
        if(arg0 != null) {
            arg0.clear();
        }
    }

    public final void zza(DataHolder arg3) {
        if(this.zzfl != null) {
            this.zzfl.notifyListener(new zzhl(arg3));
            return;
        }

        arg3.close();
    }

    public final void zza(zzah arg3) {
        if(this.zzfq != null) {
            this.zzfq.notifyListener(new zzho(arg3));
        }
    }

    public final void zza(zzaw arg3) {
        if(this.zzfp != null) {
            this.zzfp.notifyListener(new zzhn(arg3));
        }
    }

    public final void zza(zzfe arg3) {
        if(this.zzfm != null) {
            this.zzfm.notifyListener(new zzhm(arg3));
        }
    }

    public final void zza(zzfo arg1) {
    }

    public final void zza(zzi arg1) {
    }

    public final void zza(zzl arg1) {
    }

    public static zzhk zzb(ListenerHolder arg2, IntentFilter[] arg3) {
        zzhk v0 = new zzhk(arg3, null);
        v0.zzfm = Preconditions.checkNotNull(arg2);
        return v0;
    }

    public final void zzb(zzfo arg1) {
    }

    public static zzhk zzc(ListenerHolder arg2, IntentFilter[] arg3) {
        zzhk v0 = new zzhk(arg3, null);
        v0.zzfp = Preconditions.checkNotNull(arg2);
        return v0;
    }

    public static zzhk zzd(ListenerHolder arg2, IntentFilter[] arg3) {
        zzhk v0 = new zzhk(arg3, null);
        v0.zzfq = Preconditions.checkNotNull(arg2);
        return v0;
    }

    public final IntentFilter[] zze() {
        return this.zzba;
    }

    @Nullable public final String zzf() {
        return this.zzbb;
    }
}

