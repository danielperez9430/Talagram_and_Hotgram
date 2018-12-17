package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzbv extends zzah {
    private final zzfa zzamz;
    private Boolean zzaql;
    private String zzaqm;

    public zzbv(zzfa arg2) {
        this(arg2, null);
    }

    private zzbv(zzfa arg1, String arg2) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzamz = arg1;
        this.zzaqm = null;
    }

    static zzfa zza(zzbv arg0) {
        return arg0.zzamz;
    }

    public final List zza(zzh arg5, boolean arg6) {
        ArrayList v1;
        this.zzb(arg5, false);
        Future v0 = this.zzamz.zzgn().zzb(new zzcl(this, arg5));
        try {
            Object v0_1 = v0.get();
            v1 = new ArrayList(((List)v0_1).size());
            Iterator v0_2 = ((List)v0_1).iterator();
            while(v0_2.hasNext()) {
                Object v2 = v0_2.next();
                if(!arg6 && (zzfk.zzcv(((zzfj)v2).name))) {
                    continue;
                }

                ((List)v1).add(new zzfh(((zzfj)v2)));
            }
        }
        catch(ExecutionException v6) {
            goto label_31;
        }

        return ((List)v1);
    label_31:
        this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(arg5.packageName), v6);
        return null;
    }

    public final List zza(String arg3, String arg4, zzh arg5) {
        this.zzb(arg5, false);
        Future v3 = this.zzamz.zzgn().zzb(new zzcd(this, arg5, arg3, arg4));
        try {
            return v3.get();
        }
        catch(ExecutionException v3_1) {
            this.zzamz.zzgo().zzjd().zzg("Failed to get conditional user properties", v3_1);
            return Collections.emptyList();
        }
    }

    public final List zza(String arg3, String arg4, String arg5, boolean arg6) {
        ArrayList v5;
        this.zzc(arg3, true);
        Future v4 = this.zzamz.zzgn().zzb(new zzcc(this, arg3, arg4, arg5));
        try {
            Object v4_2 = v4.get();
            v5 = new ArrayList(((List)v4_2).size());
            Iterator v4_3 = ((List)v4_2).iterator();
            while(v4_3.hasNext()) {
                Object v0 = v4_3.next();
                if(!arg6 && (zzfk.zzcv(((zzfj)v0).name))) {
                    continue;
                }

                ((List)v5).add(new zzfh(((zzfj)v0)));
            }
        }
        catch(ExecutionException v4_1) {
            goto label_30;
        }

        return ((List)v5);
    label_30:
        this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(arg3), v4_1);
        return Collections.emptyList();
    }

    public final List zza(String arg3, String arg4, boolean arg5, zzh arg6) {
        ArrayList v4;
        this.zzb(arg6, false);
        Future v3 = this.zzamz.zzgn().zzb(new zzcb(this, arg6, arg3, arg4));
        try {
            Object v3_2 = v3.get();
            v4 = new ArrayList(((List)v3_2).size());
            Iterator v3_3 = ((List)v3_2).iterator();
            while(v3_3.hasNext()) {
                Object v0 = v3_3.next();
                if(!arg5 && (zzfk.zzcv(((zzfj)v0).name))) {
                    continue;
                }

                ((List)v4).add(new zzfh(((zzfj)v0)));
            }
        }
        catch(ExecutionException v3_1) {
            goto label_31;
        }

        return ((List)v4);
    label_31:
        this.zzamz.zzgo().zzjd().zze("Failed to get user attributes. appId", zzap.zzbv(arg6.packageName), v3_1);
        return Collections.emptyList();
    }

    public final void zza(long arg9, String arg11, String arg12, String arg13) {
        this.zze(new zzcn(this, arg12, arg13, arg11, arg9));
    }

    public final void zza(zzad arg2, zzh arg3) {
        Preconditions.checkNotNull(arg2);
        this.zzb(arg3, false);
        this.zze(new zzcg(this, arg2, arg3));
    }

    public final void zza(zzad arg1, String arg2, String arg3) {
        Preconditions.checkNotNull(arg1);
        Preconditions.checkNotEmpty(arg2);
        this.zzc(arg2, true);
        this.zze(new zzch(this, arg1, arg2));
    }

    public final void zza(zzfh arg2, zzh arg3) {
        zzcj v0;
        Preconditions.checkNotNull(arg2);
        this.zzb(arg3, false);
        if(arg2.getValue() == null) {
            v0 = new zzcj(this, arg2, arg3);
        }
        else {
            zzck v0_1 = new zzck(this, arg2, arg3);
        }

        this.zze(((Runnable)v0));
    }

    public final void zza(zzh arg2) {
        this.zzb(arg2, false);
        this.zze(new zzcm(this, arg2));
    }

    public final void zza(zzl arg3, zzh arg4) {
        zzbx v3;
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg3.zzahb);
        this.zzb(arg4, false);
        zzl v0 = new zzl(arg3);
        v0.packageName = arg4.packageName;
        if(arg3.zzahb.getValue() == null) {
            v3 = new zzbx(this, v0, arg4);
        }
        else {
            zzby v3_1 = new zzby(this, v0, arg4);
        }

        this.zze(((Runnable)v3));
    }

    public final byte[] zza(zzad arg11, String arg12) {
        byte[] v4_2;
        Preconditions.checkNotEmpty(arg12);
        Preconditions.checkNotNull(arg11);
        this.zzc(arg12, true);
        this.zzamz.zzgo().zzjk().zzg("Log and bundle. event", this.zzamz.zzgl().zzbs(arg11.name));
        long v2 = 1000000;
        long v0 = this.zzamz.zzbx().nanoTime() / v2;
        Future v4 = this.zzamz.zzgn().zzc(new zzci(this, arg11, arg12));
        try {
            Object v4_1 = v4.get();
            if(v4_1 == null) {
                this.zzamz.zzgo().zzjd().zzg("Log and bundle returned null. appId", zzap.zzbv(arg12));
                v4_2 = new byte[0];
            }

            this.zzamz.zzgo().zzjk().zzd("Log and bundle processed. event, size, time_ms", this.zzamz.zzgl().zzbs(arg11.name), Integer.valueOf(v4_2.length), Long.valueOf(this.zzamz.zzbx().nanoTime() / v2 - v0));
            return v4_2;
        }
        catch(ExecutionException v0_1) {
            this.zzamz.zzgo().zzjd().zzd("Failed to log and bundle. appId, event, error", zzap.zzbv(arg12), this.zzamz.zzgl().zzbs(arg11.name), v0_1);
            return null;
        }
    }

    @VisibleForTesting final zzad zzb(zzad arg9, zzh arg10) {
        int v1 = 0;
        if(("_cmp".equals(arg9.name)) && arg9.zzaid != null) {
            if(arg9.zzaid.size() == 0) {
            }
            else {
                String v0 = arg9.zzaid.getString("_cis");
                if(!TextUtils.isEmpty(((CharSequence)v0))) {
                    if(!"referrer broadcast".equals(v0) && !"referrer API".equals(v0)) {
                        goto label_28;
                    }

                    if(!this.zzamz.zzgq().zzbg(arg10.packageName)) {
                        goto label_28;
                    }

                    v1 = 1;
                }
            }
        }

    label_28:
        if(v1 != 0) {
            this.zzamz.zzgo().zzjj().zzg("Event has been filtered ", arg9.toString());
            return new zzad("_cmpx", arg9.zzaid, arg9.origin, arg9.zzaip);
        }

        return arg9;
    }

    private final void zzb(zzh arg2, boolean arg3) {
        Preconditions.checkNotNull(arg2);
        this.zzc(arg2.packageName, false);
        this.zzamz.zzgm().zzt(arg2.zzafx, arg2.zzagk);
    }

    public final void zzb(zzh arg2) {
        this.zzb(arg2, false);
        this.zze(new zzbw(this, arg2));
    }

    public final void zzb(zzl arg3) {
        zzca v3_1;
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg3.zzahb);
        this.zzc(arg3.packageName, true);
        zzl v0 = new zzl(arg3);
        if(arg3.zzahb.getValue() == null) {
            zzbz v3 = new zzbz(this, v0);
        }
        else {
            v3_1 = new zzca(this, v0);
        }

        this.zze(((Runnable)v3_1));
    }

    private final void zzc(String arg4, boolean arg5) {
        if(TextUtils.isEmpty(((CharSequence)arg4))) {
            goto label_60;
        }

        if(arg5) {
            try {
                if(this.zzaql == null) {
                    arg5 = ("com.google.android.gms".equals(this.zzaqm)) || (UidVerifier.isGooglePlayServicesUid(this.zzamz.getContext(), Binder.getCallingUid())) || (GoogleSignatureVerifier.getInstance(this.zzamz.getContext()).isUidGoogleSigned(Binder.getCallingUid())) ? true : false;
                    this.zzaql = Boolean.valueOf(arg5);
                }

                if(!this.zzaql.booleanValue()) {
                label_34:
                    if(this.zzaqm == null && (GooglePlayServicesUtilLight.uidHasPackageName(this.zzamz.getContext(), Binder.getCallingUid(), arg4))) {
                        this.zzaqm = arg4;
                    }

                    if(!arg4.equals(this.zzaqm)) {
                        goto label_46;
                    }
                }

                return;
            label_46:
                throw new SecurityException(String.format("Unknown calling package name \'%s\'.", arg4));
            label_33:
                goto label_53;
            }
            catch(SecurityException v5) {
                goto label_33;
            }
        }
        else {
            goto label_34;
        }

        return;
    label_53:
        this.zzamz.zzgo().zzjd().zzg("Measurement Service called with invalid calling package. appId", zzap.zzbv(arg4));
        throw v5;
    label_60:
        this.zzamz.zzgo().zzjd().zzbx("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    public final String zzc(zzh arg2) {
        this.zzb(arg2, false);
        return this.zzamz.zzh(arg2);
    }

    public final void zzd(zzh arg3) {
        this.zzc(arg3.packageName, false);
        this.zze(new zzcf(this, arg3));
    }

    @VisibleForTesting private final void zze(Runnable arg2) {
        Preconditions.checkNotNull(arg2);
        if((zzaf.zzakv.get().booleanValue()) && (this.zzamz.zzgn().zzkb())) {
            arg2.run();
            return;
        }

        this.zzamz.zzgn().zzc(arg2);
    }

    public final List zze(String arg3, String arg4, String arg5) {
        this.zzc(arg3, true);
        Future v3 = this.zzamz.zzgn().zzb(new zzce(this, arg3, arg4, arg5));
        try {
            return v3.get();
        }
        catch(ExecutionException v3_1) {
            this.zzamz.zzgo().zzjd().zzg("Failed to get conditional user properties", v3_1);
            return Collections.emptyList();
        }
    }
}

