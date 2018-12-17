package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

public final class zzan extends zzcp {
    private static final AtomicReference zzalt;
    private static final AtomicReference zzalu;
    private static final AtomicReference zzalv;

    static {
        zzan.zzalt = new AtomicReference();
        zzan.zzalu = new AtomicReference();
        zzan.zzalv = new AtomicReference();
    }

    zzan(zzbt arg1) {
        super(arg1);
    }

    public final Context getContext() {
        return super.getContext();
    }

    private static String zza(String arg3, String[] arg4, String[] arg5, AtomicReference arg6) {
        Preconditions.checkNotNull(arg4);
        Preconditions.checkNotNull(arg5);
        Preconditions.checkNotNull(arg6);
        int v2 = 0;
        boolean v0 = arg4.length == arg5.length ? true : false;
        Preconditions.checkArgument(v0);
        while(v2 < arg4.length) {
            if(zzfk.zzu(arg3, arg4[v2])) {
                __monitor_enter(arg6);
                try {
                    Object v3_1 = arg6.get();
                    if(v3_1 == null) {
                        String[] v3_2 = new String[arg5.length];
                        arg6.set(v3_2);
                    }

                    if(v3_1[v2] == null) {
                        v3_1[v2] = arg5[v2] + "(" + arg4[v2] + ")";
                    }

                    __monitor_exit(arg6);
                    return v3_1[v2];
                }
                catch(Throwable v3) {
                    goto label_40;
                }
            }

            ++v2;
        }

        return arg3;
        try {
        label_40:
            __monitor_exit(arg6);
        }
        catch(Throwable v3) {
            goto label_40;
        }

        throw v3;
    }

    protected final String zza(zzy arg3) {
        if(arg3 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg3.toString();
        }

        return "Event{appId=\'" + arg3.zztt + "\', name=\'" + this.zzbs(arg3.name) + "\', params=" + this.zzb(arg3.zzaid) + "}";
    }

    public final void zzaf() {
        super.zzaf();
    }

    private final String zzb(zzaa arg2) {
        if(arg2 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg2.toString();
        }

        return this.zzd(arg2.zziv());
    }

    protected final String zzb(zzad arg3) {
        if(arg3 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg3.toString();
        }

        return "origin=" + arg3.origin + ",name=" + this.zzbs(arg3.name) + ",params=" + this.zzb(arg3.zzaid);
    }

    protected final String zzbs(String arg4) {
        if(arg4 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg4;
        }

        return zzan.zza(arg4, Event.zzadl, Event.zzadk, zzan.zzalt);
    }

    protected final String zzbt(String arg4) {
        if(arg4 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg4;
        }

        return zzan.zza(arg4, Param.zzadn, Param.zzadm, zzan.zzalu);
    }

    protected final String zzbu(String arg4) {
        if(arg4 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg4;
        }

        if(arg4.startsWith("_exp_")) {
            return "experiment_id" + "(" + arg4 + ")";
        }

        return zzan.zza(arg4, UserProperty.zzadp, UserProperty.zzado, zzan.zzalv);
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    protected final String zzd(Bundle arg5) {
        if(arg5 == null) {
            return null;
        }

        if(!this.zzjc()) {
            return arg5.toString();
        }

        StringBuilder v0 = new StringBuilder();
        Iterator v1 = arg5.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            String v3 = v0.length() != 0 ? ", " : "Bundle[{";
            v0.append(v3);
            v0.append(this.zzbt(((String)v2)));
            v0.append("=");
            v0.append(arg5.get(((String)v2)));
        }

        v0.append("}]");
        return v0.toString();
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        return 0;
    }

    private final boolean zzjc() {
        ((zzco)this).zzgr();
        if((this.zzadj.zzkj()) && (this.zzadj.zzgo().isLoggable(3))) {
            return 1;
        }

        return 0;
    }
}

