package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v4.f.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzfu;
import com.google.android.gms.internal.measurement.zzfv;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzga;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzyx;
import com.google.android.gms.internal.measurement.zzyy;
import com.google.android.gms.internal.measurement.zzzg;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import java.io.IOException;
import java.util.Map;

public final class zzbn extends zzez implements zzp {
    @VisibleForTesting private static int zzaon = 65535;
    @VisibleForTesting private static int zzaoo = 2;
    private final Map zzaop;
    private final Map zzaoq;
    private final Map zzaor;
    private final Map zzaos;
    private final Map zzaot;
    private final Map zzaou;

    static {
    }

    zzbn(zzfa arg1) {
        super(arg1);
        this.zzaop = new a();
        this.zzaoq = new a();
        this.zzaor = new a();
        this.zzaos = new a();
        this.zzaou = new a();
        this.zzaot = new a();
    }

    public final Context getContext() {
        return super.getContext();
    }

    protected final boolean zza(String arg17, byte[] arg18, String arg19) {
        byte[] v3_3;
        zzbn v1 = this;
        String v2 = arg17;
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg17);
        zzgb v0 = this.zza(arg17, arg18);
        if(v0 == null) {
            return 0;
        }

        v1.zza(v2, v0);
        v1.zzaos.put(v2, v0);
        v1.zzaou.put(v2, arg19);
        v1.zzaop.put(v2, zzbn.zza(v0));
        zzj v4 = ((zzey)this).zzjp();
        zzfu[] v5 = v0.zzawi;
        Preconditions.checkNotNull(v5);
        int v6 = v5.length;
        int v7;
        for(v7 = 0; v7 < v6; ++v7) {
            zzfu v8 = v5[v7];
            zzfv[] v9 = v8.zzava;
            int v10 = v9.length;
            int v11;
            for(v11 = 0; v11 < v10; ++v11) {
                zzfv v12 = v9[v11];
                String v13 = Event.zzal(v12.zzavf);
                if(v13 != null) {
                    v12.zzavf = v13;
                }

                zzfw[] v12_1 = v12.zzavg;
                int v13_1 = v12_1.length;
                int v14;
                for(v14 = 0; v14 < v13_1; ++v14) {
                    zzfw v15 = v12_1[v14];
                    String v3 = Param.zzal(v15.zzavn);
                    if(v3 != null) {
                        v15.zzavn = v3;
                    }
                }
            }

            zzfy[] v3_1 = v8.zzauz;
            int v8_1 = v3_1.length;
            int v9_1;
            for(v9_1 = 0; v9_1 < v8_1; ++v9_1) {
                zzfy v10_1 = v3_1[v9_1];
                String v11_1 = UserProperty.zzal(v10_1.zzavu);
                if(v11_1 != null) {
                    v10_1.zzavu = v11_1;
                }
            }
        }

        ((zzey)v4).zzjq().zza(v2, v5);
        zzfu[] v3_2 = null;
        try {
            v0.zzawi = v3_2;
            v3_3 = new byte[((zzzg)v0).zzvu()];
            ((zzzg)v0).zza(zzyy.zzk(v3_3, 0, v3_3.length));
        }
        catch(IOException v0_1) {
            ((zzco)this).zzgo().zzjg().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzap.zzbv(arg17), v0_1);
            v3_3 = arg18;
        }

        zzq v4_1 = ((zzey)this).zzjq();
        Preconditions.checkNotEmpty(arg17);
        ((zzco)v4_1).zzaf();
        ((zzez)v4_1).zzcl();
        ContentValues v0_2 = new ContentValues();
        v0_2.put("remote_config", v3_3);
        try {
            if((((long)v4_1.getWritableDatabase().update("apps", v0_2, "app_id = ?", new String[]{v2}))) != 0) {
                return 1;
            }

            ((zzco)v4_1).zzgo().zzjd().zzg("Failed to update remote config (got 0). appId", zzap.zzbv(arg17));
        }
        catch(SQLiteException v0_3) {
            ((zzco)v4_1).zzgo().zzjd().zze("Error storing remote config. appId", zzap.zzbv(arg17), v0_3);
        }

        return 1;
    }

    private final zzgb zza(String arg5, byte[] arg6) {
        if(arg6 == null) {
            return new zzgb();
        }

        zzyx v6 = zzyx.zzj(arg6, 0, arg6.length);
        zzgb v0 = new zzgb();
        try {
            ((zzzg)v0).zza(v6);
            ((zzco)this).zzgo().zzjl().zze("Parsed config. version, gmp_app_id", v0.zzawe, v0.zzafx);
            return v0;
        }
        catch(IOException v6_1) {
            ((zzco)this).zzgo().zzjg().zze("Unable to merge remote config. appId", zzap.zzbv(arg5), v6_1);
            return new zzgb();
        }
    }

    private static Map zza(zzgb arg5) {
        a v0 = new a();
        if(arg5 != null && arg5.zzawg != null) {
            zzgc[] v5 = arg5.zzawg;
            int v1 = v5.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                zzgc v3 = v5[v2];
                if(v3 != null) {
                    ((Map)v0).put(v3.zzoj, v3.value);
                }
            }
        }

        return ((Map)v0);
    }

    private final void zza(String arg10, zzgb arg11) {
        a v0 = new a();
        a v1 = new a();
        a v2 = new a();
        if(arg11 != null && arg11.zzawh != null) {
            zzga[] v11 = arg11.zzawh;
            int v3 = v11.length;
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                zzga v5 = v11[v4];
                if(TextUtils.isEmpty(v5.name)) {
                    ((zzco)this).zzgo().zzjg().zzbx("EventConfig contained null event name");
                }
                else {
                    String v6 = Event.zzal(v5.name);
                    if(!TextUtils.isEmpty(((CharSequence)v6))) {
                        v5.name = v6;
                    }

                    ((Map)v0).put(v5.name, v5.zzawb);
                    ((Map)v1).put(v5.name, v5.zzawc);
                    if(v5.zzawd == null) {
                        goto label_54;
                    }

                    if(v5.zzawd.intValue() >= zzbn.zzaoo) {
                        if(v5.zzawd.intValue() > zzbn.zzaon) {
                        }
                        else {
                            ((Map)v2).put(v5.name, v5.zzawd);
                            goto label_54;
                        }
                    }

                    ((zzco)this).zzgo().zzjg().zze("Invalid sampling rate. Event name, sample rate", v5.name, v5.zzawd);
                }

            label_54:
            }
        }

        this.zzaoq.put(arg10, v0);
        this.zzaor.put(arg10, v1);
        this.zzaot.put(arg10, v2);
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    private final void zzce(String arg5) {
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg5);
        if(this.zzaos.get(arg5) == null) {
            byte[] v0 = ((zzey)this).zzjq().zzbn(arg5);
            Object v1 = null;
            if(v0 == null) {
                this.zzaop.put(arg5, v1);
                this.zzaoq.put(arg5, v1);
                this.zzaor.put(arg5, v1);
                this.zzaos.put(arg5, v1);
                this.zzaou.put(arg5, v1);
                this.zzaot.put(arg5, v1);
                return;
            }
            else {
                zzgb v0_1 = this.zza(arg5, v0);
                this.zzaop.put(arg5, zzbn.zza(v0_1));
                this.zza(arg5, v0_1);
                this.zzaos.put(arg5, v0_1);
                this.zzaou.put(arg5, v1);
            }
        }
    }

    protected final zzgb zzcf(String arg2) {
        ((zzez)this).zzcl();
        ((zzco)this).zzaf();
        Preconditions.checkNotEmpty(arg2);
        this.zzce(arg2);
        return this.zzaos.get(arg2);
    }

    protected final String zzcg(String arg2) {
        ((zzco)this).zzaf();
        return this.zzaou.get(arg2);
    }

    protected final void zzch(String arg3) {
        ((zzco)this).zzaf();
        this.zzaou.put(arg3, null);
    }

    final void zzci(String arg2) {
        ((zzco)this).zzaf();
        this.zzaos.remove(arg2);
    }

    final long zzcj(String arg4) {
        String v0 = this.zzf(arg4, "measurement.account.time_zone_offset_minutes");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            try {
                return Long.parseLong(v0);
            }
            catch(NumberFormatException v0_1) {
                ((zzco)this).zzgo().zzjg().zze("Unable to parse timezone offset. appId", zzap.zzbv(arg4), v0_1);
            }
        }

        return 0;
    }

    final boolean zzck(String arg3) {
        return "1".equals(this.zzf(arg3, "measurement.upload.blacklist_internal"));
    }

    final boolean zzcl(String arg3) {
        return "1".equals(this.zzf(arg3, "measurement.upload.blacklist_public"));
    }

    public final String zzf(String arg2, String arg3) {
        ((zzco)this).zzaf();
        this.zzce(arg2);
        Object v2 = this.zzaop.get(arg2);
        if(v2 != null) {
            return ((Map)v2).get(arg3);
        }

        return null;
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

    public final zzfg zzjo() {
        return super.zzjo();
    }

    public final zzj zzjp() {
        return super.zzjp();
    }

    public final zzq zzjq() {
        return super.zzjq();
    }

    final boolean zzo(String arg3, String arg4) {
        ((zzco)this).zzaf();
        this.zzce(arg3);
        if((this.zzck(arg3)) && (zzfk.zzcv(arg4))) {
            return 1;
        }

        if((this.zzcl(arg3)) && (zzfk.zzcq(arg4))) {
            return 1;
        }

        Object v3 = this.zzaoq.get(arg3);
        if(v3 != null) {
            v3 = ((Map)v3).get(arg4);
            if(v3 == null) {
                return 0;
            }

            return ((Boolean)v3).booleanValue();
        }

        return 0;
    }

    final boolean zzp(String arg2, String arg3) {
        ((zzco)this).zzaf();
        this.zzce(arg2);
        if("ecommerce_purchase".equals(arg3)) {
            return 1;
        }

        Object v2 = this.zzaor.get(arg2);
        if(v2 != null) {
            v2 = ((Map)v2).get(arg3);
            if(v2 == null) {
                return 0;
            }

            return ((Boolean)v2).booleanValue();
        }

        return 0;
    }

    final int zzq(String arg2, String arg3) {
        ((zzco)this).zzaf();
        this.zzce(arg2);
        Object v2 = this.zzaot.get(arg2);
        if(v2 != null) {
            v2 = ((Map)v2).get(arg3);
            if(v2 == null) {
                return 1;
            }

            return ((Integer)v2).intValue();
        }

        return 1;
    }
}

