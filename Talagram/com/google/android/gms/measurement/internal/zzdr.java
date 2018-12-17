package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting public final class zzdr extends zzf {
    private final zzef zzarz;
    private zzag zzasa;
    private volatile Boolean zzasb;
    private final zzv zzasc;
    private final zzev zzasd;
    private final List zzase;
    private final zzv zzasf;

    protected zzdr(zzbt arg3) {
        super(arg3);
        this.zzase = new ArrayList();
        this.zzasd = new zzev(arg3.zzbx());
        this.zzarz = new zzef(this);
        this.zzasc = new zzds(this, ((zzcq)arg3));
        this.zzasf = new zzdx(this, ((zzcq)arg3));
    }

    public final void disconnect() {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        if(zzn.zzia()) {
            this.zzarz.zzlg();
        }

        try {
            ConnectionTracker.getInstance().unbindService(((zzco)this).getContext(), this.zzarz);
            goto label_10;
        }
        catch(IllegalArgumentException ) {
        label_10:
            this.zzasa = null;
            return;
        }
    }

    public final Context getContext() {
        return super.getContext();
    }

    public final boolean isConnected() {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        if(this.zzasa != null) {
            return 1;
        }

        return 0;
    }

    private final void onServiceDisconnected(ComponentName arg3) {
        ((zzco)this).zzaf();
        if(this.zzasa != null) {
            this.zzasa = null;
            ((zzco)this).zzgo().zzjl().zzg("Disconnected from device MeasurementService", arg3);
            ((zzco)this).zzaf();
            this.zzdj();
        }
    }

    protected final void resetAnalyticsData() {
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        zzh v0 = this.zzm(false);
        if(this.zzld()) {
            ((zze)this).zzgi().resetAnalyticsData();
        }

        this.zzf(new zzdt(this, v0));
    }

    protected final void zza(AtomicReference arg3, boolean arg4) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzee(this, arg3, this.zzm(false), arg4));
    }

    public final void zza(AtomicReference arg3) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzdu(this, arg3, this.zzm(false)));
    }

    protected final void zza(AtomicReference arg9, String arg10, String arg11, String arg12) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzeb(this, arg9, arg10, arg11, arg12, this.zzm(false)));
    }

    protected final void zza(AtomicReference arg10, String arg11, String arg12, String arg13, boolean arg14) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzec(this, arg10, arg11, arg12, arg13, arg14, this.zzm(false)));
    }

    @VisibleForTesting final void zza(zzag arg12, AbstractSafeParcelable arg13, zzh arg14) {
        String v10;
        zzar v9;
        int v5_1;
        ((zzco)this).zzaf();
        ((zzco)this).zzgb();
        ((zzf)this).zzcl();
        boolean v0 = this.zzld();
        int v2 = 100;
        int v3 = 0;
        int v4;
        for(v4 = 100; v3 < 1001; v4 = v5_1) {
            if(v4 != v2) {
                return;
            }

            ArrayList v4_1 = new ArrayList();
            if(v0) {
                List v5 = ((zze)this).zzgi().zzr(v2);
                if(v5 != null) {
                    ((List)v4_1).addAll(((Collection)v5));
                    v5_1 = v5.size();
                }
                else {
                    goto label_20;
                }
            }
            else {
            label_20:
                v5_1 = 0;
            }

            if(arg13 != null && v5_1 < v2) {
                ((List)v4_1).add(arg13);
            }

            int v6 = v4_1.size();
            int v7 = 0;
            while(v7 < v6) {
                Object v8 = v4_1.get(v7);
                ++v7;
                if((v8 instanceof zzad)) {
                    try {
                        arg12.zza(((zzad)v8), arg14);
                        continue;
                    }
                    catch(RemoteException v8_1) {
                        v9 = ((zzco)this).zzgo().zzjd();
                        v10 = "Failed to send event to the service";
                        goto label_37;
                    }
                }

                if((v8 instanceof zzfh)) {
                    try {
                        arg12.zza(((zzfh)v8), arg14);
                        continue;
                    }
                    catch(RemoteException v8_1) {
                        v9 = ((zzco)this).zzgo().zzjd();
                        v10 = "Failed to send attribute to the service";
                        goto label_37;
                    }
                }

                if((v8 instanceof zzl)) {
                    try {
                        arg12.zza(((zzl)v8), arg14);
                        continue;
                    }
                    catch(RemoteException v8_1) {
                        v9 = ((zzco)this).zzgo().zzjd();
                        v10 = "Failed to send conditional property to the service";
                    }

                label_37:
                    v9.zzg(v10, v8_1);
                    continue;
                }

                ((zzco)this).zzgo().zzjd().zzbx("Discarding data. Unrecognized parcel type.");
            }

            ++v3;
        }
    }

    @VisibleForTesting protected final void zza(zzag arg1) {
        ((zzco)this).zzaf();
        Preconditions.checkNotNull(arg1);
        this.zzasa = arg1;
        this.zzcy();
        this.zzlf();
    }

    static void zza(zzdr arg0, ComponentName arg1) {
        arg0.onServiceDisconnected(arg1);
    }

    static zzag zza(zzdr arg0, zzag arg1) {
        arg0.zzasa = null;
        return null;
    }

    static zzef zza(zzdr arg0) {
        return arg0.zzarz;
    }

    public final void zzaf() {
        super.zzaf();
    }

    protected final void zzb(zzdn arg2) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzdw(this, arg2));
    }

    static void zzb(zzdr arg0) {
        arg0.zzlf();
    }

    protected final void zzb(zzad arg9, String arg10) {
        Preconditions.checkNotNull(arg9);
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        boolean v2 = this.zzld();
        boolean v3 = !v2 || !((zze)this).zzgi().zza(arg9) ? false : true;
        this.zzf(new zzdz(this, v2, v3, arg9, this.zzm(true), arg10));
    }

    protected final void zzb(zzfh arg4) {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        boolean v0 = !this.zzld() || !((zze)this).zzgi().zza(arg4) ? false : true;
        this.zzf(new zzed(this, v0, arg4, this.zzm(true)));
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    static void zzc(zzdr arg0) {
        arg0.zzcz();
    }

    private final void zzcy() {
        ((zzco)this).zzaf();
        this.zzasd.start();
        this.zzasc.zzh(zzaf.zzakj.get().longValue());
    }

    private final void zzcz() {
        ((zzco)this).zzaf();
        if(!this.isConnected()) {
            return;
        }

        ((zzco)this).zzgo().zzjl().zzbx("Inactivity, disconnecting from the service");
        this.disconnect();
    }

    static zzag zzd(zzdr arg0) {
        return arg0.zzasa;
    }

    protected final void zzd(zzl arg10) {
        Preconditions.checkNotNull(arg10);
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        ((zzco)this).zzgr();
        boolean v5 = ((zze)this).zzgi().zzc(arg10) ? true : false;
        this.zzf(new zzea(this, true, v5, new zzl(arg10), this.zzm(true), arg10));
    }

    final void zzdj() {
        boolean v0_1;
        int v3_1;
        String v3;
        zzar v0_3;
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        if(this.isConnected()) {
            return;
        }

        int v1 = 0;
        if(this.zzasb == null) {
            ((zzco)this).zzaf();
            ((zzf)this).zzcl();
            Boolean v0 = ((zzco)this).zzgp().zzju();
            if(v0 == null || !v0.booleanValue()) {
                ((zzco)this).zzgr();
                if(((zze)this).zzgf().zzjb() != 1) {
                    ((zzco)this).zzgo().zzjl().zzbx("Checking service availability");
                    int v0_2 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(((zzco)this).zzgm().getContext(), 12451000);
                    if(v0_2 != 9) {
                        if(v0_2 != 18) {
                            switch(v0_2) {
                                case 0: {
                                    goto label_74;
                                }
                                case 1: {
                                    goto label_68;
                                }
                                case 2: {
                                    goto label_52;
                                }
                                case 3: {
                                    goto label_47;
                                }
                            }

                            ((zzco)this).zzgo().zzjg().zzg("Unexpected service status", Integer.valueOf(v0_2));
                            goto label_44;
                        label_68:
                            ((zzco)this).zzgo().zzjl().zzbx("Service missing");
                            goto label_72;
                        label_52:
                            ((zzco)this).zzgo().zzjk().zzbx("Service container out of date");
                            if(((zzco)this).zzgm().zzme() < 13000) {
                            label_72:
                                v0_1 = false;
                                goto label_23;
                            }
                            else {
                                v0 = ((zzco)this).zzgp().zzju();
                                if(v0 != null && !v0.booleanValue()) {
                                    goto label_44;
                                }

                                v0_1 = true;
                                goto label_45;
                            label_74:
                                v0_3 = ((zzco)this).zzgo().zzjl();
                                v3 = "Service available";
                                goto label_77;
                            label_47:
                                v0_3 = ((zzco)this).zzgo().zzjg();
                                v3 = "Service disabled";
                                goto label_50;
                            }
                        }
                        else {
                            v0_3 = ((zzco)this).zzgo().zzjg();
                            v3 = "Service updating";
                        }

                    label_77:
                        v0_3.zzbx(v3);
                        goto label_22;
                    }
                    else {
                        goto label_83;
                    }
                }
                else {
                label_22:
                    v0_1 = true;
                label_23:
                    v3_1 = 1;
                    goto label_87;
                label_83:
                    v0_3 = ((zzco)this).zzgo().zzjg();
                    v3 = "Service invalid";
                label_50:
                    v0_3.zzbx(v3);
                label_44:
                    v0_1 = false;
                label_45:
                    v3_1 = 0;
                }

            label_87:
                if(!v0_1 && (((zzco)this).zzgq().zzib())) {
                    ((zzco)this).zzgo().zzjd().zzbx("No way to upload. Consider using the full version of Analytics");
                    v3_1 = 0;
                }

                if(v3_1 == 0) {
                    goto label_99;
                }

                ((zzco)this).zzgp().zzg(v0_1);
            }
            else {
                v0_1 = true;
            }

        label_99:
            this.zzasb = Boolean.valueOf(v0_1);
        }

        if(this.zzasb.booleanValue()) {
            this.zzarz.zzlh();
            return;
        }

        if(!((zzco)this).zzgq().zzib()) {
            ((zzco)this).zzgr();
            List v0_4 = ((zzco)this).getContext().getPackageManager().queryIntentServices(new Intent().setClassName(((zzco)this).getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if(v0_4 != null && v0_4.size() > 0) {
                v1 = 1;
            }

            if(v1 != 0) {
                Intent v0_5 = new Intent("com.google.android.gms.measurement.START");
                Context v2 = ((zzco)this).getContext();
                ((zzco)this).zzgr();
                v0_5.setComponent(new ComponentName(v2, "com.google.android.gms.measurement.AppMeasurementService"));
                this.zzarz.zzc(v0_5);
                return;
            }

            ((zzco)this).zzgo().zzjd().zzbx("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
        }
    }

    static void zze(zzdr arg0) {
        arg0.zzcy();
    }

    private final void zzf(Runnable arg6) {
        ((zzco)this).zzaf();
        if(this.isConnected()) {
            arg6.run();
            return;
        }

        if((((long)this.zzase.size())) >= 1000) {
            ((zzco)this).zzgo().zzjd().zzbx("Discarding data. Max runnable queue size reached");
            return;
        }

        this.zzase.add(arg6);
        this.zzasf.zzh(60000);
        this.zzdj();
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

    public final zza zzgd() {
        return super.zzgd();
    }

    public final zzcs zzge() {
        return super.zzge();
    }

    public final zzaj zzgf() {
        return super.zzgf();
    }

    public final zzdr zzgg() {
        return super.zzgg();
    }

    public final zzdo zzgh() {
        return super.zzgh();
    }

    public final zzal zzgi() {
        return super.zzgi();
    }

    public final zzeq zzgj() {
        return super.zzgj();
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

    protected final void zzkz() {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzdv(this, this.zzm(true)));
    }

    protected final void zzlc() {
        ((zzco)this).zzaf();
        ((zzf)this).zzcl();
        this.zzf(new zzdy(this, this.zzm(true)));
    }

    private final boolean zzld() {
        ((zzco)this).zzgr();
        return 1;
    }

    final Boolean zzle() {
        return this.zzasb;
    }

    private final void zzlf() {
        ((zzco)this).zzaf();
        ((zzco)this).zzgo().zzjl().zzg("Processing queued up service tasks", Integer.valueOf(this.zzase.size()));
        Iterator v0 = this.zzase.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            try {
                ((Runnable)v1).run();
            }
            catch(Exception v1_1) {
                ((zzco)this).zzgo().zzjd().zzg("Task exception while flushing queue", v1_1);
            }
        }

        this.zzase.clear();
        this.zzasf.cancel();
    }

    private final zzh zzm(boolean arg2) {
        ((zzco)this).zzgr();
        zzaj v0 = ((zze)this).zzgf();
        String v2 = arg2 ? ((zzco)this).zzgo().zzjn() : null;
        return v0.zzbr(v2);
    }
}

