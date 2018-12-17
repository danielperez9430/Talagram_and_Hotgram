package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzap extends zzcp {
    private long zzadt;
    private char zzalw;
    private String zzalx;
    private final zzar zzaly;
    private final zzar zzalz;
    private final zzar zzama;
    private final zzar zzamb;
    private final zzar zzamc;
    private final zzar zzamd;
    private final zzar zzame;
    private final zzar zzamf;
    private final zzar zzamg;

    zzap(zzbt arg4) {
        super(arg4);
        this.zzalw = '\u0000';
        this.zzadt = -1;
        this.zzaly = new zzar(this, 6, false, false);
        this.zzalz = new zzar(this, 6, true, false);
        this.zzama = new zzar(this, 6, false, true);
        this.zzamb = new zzar(this, 5, false, false);
        this.zzamc = new zzar(this, 5, true, false);
        this.zzamd = new zzar(this, 5, false, true);
        this.zzame = new zzar(this, 4, false, false);
        this.zzamf = new zzar(this, 3, false, false);
        this.zzamg = new zzar(this, 2, false, false);
    }

    public final Context getContext() {
        return super.getContext();
    }

    @VisibleForTesting protected final boolean isLoggable(int arg2) {
        return Log.isLoggable(this.zzjm(), arg2);
    }

    @VisibleForTesting protected final void zza(int arg2, String arg3) {
        Log.println(arg2, this.zzjm(), arg3);
    }

    static char zza(zzap arg0) {
        return arg0.zzalw;
    }

    static char zza(zzap arg0, char arg1) {
        arg0.zzalw = arg1;
        return arg1;
    }

    static long zza(zzap arg0, long arg1) {
        arg0.zzadt = arg1;
        return arg1;
    }

    static String zza(boolean arg2, String arg3, Object arg4, Object arg5, Object arg6) {
        if(arg3 == null) {
            arg3 = "";
        }

        String v4 = zzap.zza(arg2, arg4);
        String v5 = zzap.zza(arg2, arg5);
        String v2 = zzap.zza(arg2, arg6);
        StringBuilder v6 = new StringBuilder();
        String v0 = "";
        if(!TextUtils.isEmpty(((CharSequence)arg3))) {
            v6.append(arg3);
            v0 = ": ";
        }

        if(!TextUtils.isEmpty(((CharSequence)v4))) {
            v6.append(v0);
            v6.append(v4);
            v0 = ", ";
        }

        if(!TextUtils.isEmpty(((CharSequence)v5))) {
            v6.append(v0);
            v6.append(v5);
            v0 = ", ";
        }

        if(!TextUtils.isEmpty(((CharSequence)v2))) {
            v6.append(v0);
            v6.append(v2);
        }

        return v6.toString();
    }

    protected final void zza(int arg8, boolean arg9, boolean arg10, String arg11, Object arg12, Object arg13, Object arg14) {
        String v8;
        if(!arg9 && (this.isLoggable(arg8))) {
            this.zza(arg8, zzap.zza(false, arg11, arg12, arg13, arg14));
        }

        if(!arg10 && arg8 >= 5) {
            Preconditions.checkNotNull(arg11);
            zzbo v9 = this.zzadj.zzkh();
            int v10 = 6;
            if(v9 == null) {
                v8 = "Scheduler not set. Not logging error/warn";
            }
            else if(!((zzcp)v9).isInitialized()) {
                v8 = "Scheduler not initialized. Not logging error/warn";
            }
            else {
                goto label_23;
            }

            this.zza(v10, v8);
            return;
        label_23:
            int v2 = 0 >= 9 ? 8 : 0;
            v9.zzc(new zzaq(this, v2, arg11, arg12, arg13, arg14));
        }
    }

    @VisibleForTesting private static String zza(boolean arg7, Object arg8) {
        String v7_1;
        Long v8;
        if(arg8 == null) {
            return "";
        }

        if((arg8 instanceof Integer)) {
            v8 = Long.valueOf(((long)((Integer)arg8).intValue()));
        }

        int v1 = 0;
        if((v8 instanceof Long)) {
            if(!arg7) {
                return String.valueOf(v8);
            }

            Object v7 = v8;
            if(Math.abs(((Long)v7).longValue()) < 100) {
                return String.valueOf(v8);
            }

            String v8_1 = String.valueOf(v8).charAt(0) == 45 ? "-" : "";
            v7_1 = String.valueOf(Math.abs(((Long)v7).longValue()));
            long v0 = Math.round(Math.pow(10, ((double)(v7_1.length() - 1))));
            long v2 = Math.round(Math.pow(10, ((double)v7_1.length())) - 1);
            StringBuilder v4 = new StringBuilder(String.valueOf(v8_1).length() + 43 + String.valueOf(v8_1).length());
            v4.append(v8_1);
            v4.append(v0);
            v4.append("...");
            v4.append(v8_1);
            v4.append(v2);
            return v4.toString();
        }

        if((v8 instanceof Boolean)) {
            return String.valueOf(v8);
        }

        if((v8 instanceof Throwable)) {
            v7_1 = arg7 ? v8.getClass().getName() : ((Throwable)v8).toString();
            StringBuilder v0_1 = new StringBuilder(v7_1);
            v7_1 = zzap.zzbw(AppMeasurement.class.getCanonicalName());
            String v2_1 = zzap.zzbw(zzbt.class.getCanonicalName());
            StackTraceElement[] v8_2 = ((Throwable)v8).getStackTrace();
            int v3 = v8_2.length;
            while(v1 < v3) {
                StackTraceElement v4_1 = v8_2[v1];
                if(!v4_1.isNativeMethod()) {
                    String v5 = v4_1.getClassName();
                    if(v5 != null) {
                        v5 = zzap.zzbw(v5);
                        if(!v5.equals(v7_1) && !v5.equals(v2_1)) {
                            goto label_95;
                        }

                        v0_1.append(": ");
                        v0_1.append(v4_1);
                        break;
                    }
                }

            label_95:
                ++v1;
            }

            return v0_1.toString();
        }

        if((v8 instanceof zzas)) {
            return zzas.zza(((zzas)v8));
        }

        if(arg7) {
            return "-";
        }

        return String.valueOf(v8);
    }

    public final void zzaf() {
        super.zzaf();
    }

    static long zzb(zzap arg2) {
        return arg2.zzadt;
    }

    protected static Object zzbv(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return new zzas(arg1);
    }

    private static String zzbw(String arg2) {
        if(TextUtils.isEmpty(((CharSequence)arg2))) {
            return "";
        }

        int v0 = arg2.lastIndexOf(46);
        if(v0 == -1) {
            return arg2;
        }

        return arg2.substring(0, v0);
    }

    public final Clock zzbx() {
        return super.zzbx();
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

    public final zzar zzjd() {
        return this.zzaly;
    }

    public final zzar zzje() {
        return this.zzalz;
    }

    public final zzar zzjf() {
        return this.zzama;
    }

    public final zzar zzjg() {
        return this.zzamb;
    }

    public final zzar zzjh() {
        return this.zzamc;
    }

    public final zzar zzji() {
        return this.zzamd;
    }

    public final zzar zzjj() {
        return this.zzame;
    }

    public final zzar zzjk() {
        return this.zzamf;
    }

    public final zzar zzjl() {
        return this.zzamg;
    }

    @VisibleForTesting private final String zzjm() {
        __monitor_enter(this);
        try {
            if(this.zzalx == null) {
                String v0_1 = this.zzadj.zzkm() != null ? this.zzadj.zzkm() : zzn.zzht();
                this.zzalx = v0_1;
            }

            __monitor_exit(this);
            return this.zzalx;
        label_16:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_16;
        }

        throw v0;
    }

    public final String zzjn() {
        Pair v0 = ((zzco)this).zzgp().zzand.zzfm();
        if(v0 != null) {
            if(v0 == zzba.zzanc) {
            }
            else {
                String v1 = String.valueOf(v0.second);
                Object v0_1 = v0.first;
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 1 + String.valueOf(v0_1).length());
                v3.append(v1);
                v3.append(":");
                v3.append(((String)v0_1));
                return v3.toString();
            }
        }

        return null;
    }
}

