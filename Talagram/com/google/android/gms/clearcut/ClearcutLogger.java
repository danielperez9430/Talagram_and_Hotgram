package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.clearcut.zzaa;
import com.google.android.gms.internal.clearcut.zzge$zzv$zzb;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzp;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;
import javax.annotation.Nullable;

@KeepForSdk public final class ClearcutLogger {
    public class LogEventBuilder {
        private final zzha zzaa;
        private boolean zzab;
        private String zzj;
        private int zzk;
        private String zzl;
        private String zzm;
        private zzb zzo;
        private final com.google.android.gms.clearcut.ClearcutLogger$zzb zzt;
        private ArrayList zzu;
        private ArrayList zzv;
        private ArrayList zzw;
        private ArrayList zzx;
        private ArrayList zzy;
        private boolean zzz;

        LogEventBuilder(ClearcutLogger arg1, byte[] arg2, zza arg3) {
            this(arg1, arg2);
        }

        private LogEventBuilder(ClearcutLogger arg2, byte[] arg3) {
            this(arg2, arg3, null);
        }

        private LogEventBuilder(ClearcutLogger arg4, byte[] arg5, com.google.android.gms.clearcut.ClearcutLogger$zzb arg6) {
            this.zzac = arg4;
            super();
            this.zzk = ClearcutLogger.zza(this.zzac);
            this.zzj = ClearcutLogger.zzb(this.zzac);
            this.zzl = ClearcutLogger.zzc(this.zzac);
            String v6 = null;
            this.zzm = v6;
            this.zzo = ClearcutLogger.zzd(this.zzac);
            this.zzu = ((ArrayList)v6);
            this.zzv = ((ArrayList)v6);
            this.zzw = ((ArrayList)v6);
            this.zzx = ((ArrayList)v6);
            this.zzy = ((ArrayList)v6);
            this.zzz = true;
            this.zzaa = new zzha();
            this.zzab = false;
            this.zzl = ClearcutLogger.zzc(arg4);
            this.zzm = v6;
            this.zzaa.zzbkc = zzaa.zze(ClearcutLogger.zze(arg4));
            this.zzaa.zzbjf = ClearcutLogger.zzf(arg4).currentTimeMillis();
            this.zzaa.zzbjg = ClearcutLogger.zzf(arg4).elapsedRealtime();
            zzha v0 = this.zzaa;
            ClearcutLogger.zzg(arg4);
            v0.zzbju = ((long)(TimeZone.getDefault().getOffset(this.zzaa.zzbjf) / 1000));
            if(arg5 != null) {
                this.zzaa.zzbjp = arg5;
            }

            this.zzt = ((com.google.android.gms.clearcut.ClearcutLogger$zzb)v6);
        }

        @KeepForSdk public void log() {
            if(!this.zzab) {
                this.zzab = true;
                ArrayList v12 = null;
                zze v0 = new zze(new zzr(ClearcutLogger.zzi(this.zzac), ClearcutLogger.zzj(this.zzac), this.zzk, this.zzj, this.zzl, this.zzm, ClearcutLogger.zzh(this.zzac), this.zzo), this.zzaa, null, null, ClearcutLogger.zzb(v12), null, ClearcutLogger.zzb(v12), null, null, this.zzz);
                if(ClearcutLogger.zzk(this.zzac).zza(v0)) {
                    ClearcutLogger.zzl(this.zzac).zzb(v0);
                    return;
                }

                PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, ((GoogleApiClient)v12));
                return;
            }

            throw new IllegalStateException("do not reuse LogEventBuilder");
        }

        @KeepForSdk public LogEventBuilder setEventCode(int arg2) {
            this.zzaa.zzbji = arg2;
            return this;
        }
    }

    public interface com.google.android.gms.clearcut.ClearcutLogger$zza {
        boolean zza(zze arg1);
    }

    public interface com.google.android.gms.clearcut.ClearcutLogger$zzb {
        byte[] zza();
    }

    public final class zzc {
        public zzc() {
            super();
        }
    }

    @Deprecated public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    private final String packageName;
    private static final ExperimentTokens[] zze;
    private static final String[] zzf;
    private static final byte[][] zzg;
    private final Context zzh;
    private final int zzi;
    private String zzj;
    private int zzk;
    private String zzl;
    private String zzm;
    private final boolean zzn;
    private zzb zzo;
    private final com.google.android.gms.clearcut.zzb zzp;
    private final Clock zzq;
    private zzc zzr;
    private final com.google.android.gms.clearcut.ClearcutLogger$zza zzs;

    static {
        ClearcutLogger.CLIENT_KEY = new ClientKey();
        ClearcutLogger.CLIENT_BUILDER = new zza();
        ClearcutLogger.API = new Api("ClearcutLogger.API", ClearcutLogger.CLIENT_BUILDER, ClearcutLogger.CLIENT_KEY);
        ClearcutLogger.zze = new ExperimentTokens[0];
        ClearcutLogger.zzf = new String[0];
        ClearcutLogger.zzg = new byte[0][];
    }

    @VisibleForTesting private ClearcutLogger(Context arg1, int arg2, String arg3, String arg4, String arg5, boolean arg6, com.google.android.gms.clearcut.zzb arg7, Clock arg8, zzc arg9, com.google.android.gms.clearcut.ClearcutLogger$zza arg10) {
        super();
        this.zzk = -1;
        this.zzo = zzb.zzbhk;
        this.zzh = arg1;
        this.packageName = arg1.getPackageName();
        this.zzi = ClearcutLogger.zza(arg1);
        this.zzk = -1;
        this.zzj = arg3;
        this.zzl = arg4;
        this.zzm = null;
        this.zzn = arg6;
        this.zzp = arg7;
        this.zzq = arg8;
        this.zzr = new zzc();
        this.zzo = zzb.zzbhk;
        this.zzs = arg10;
        if(arg6) {
            boolean v1 = arg4 == null ? true : false;
            Preconditions.checkArgument(v1, "can\'t be anonymous with an upload account");
        }
    }

    @KeepForSdk public ClearcutLogger(Context arg12, String arg13, @Nullable String arg14) {
        this(arg12, -1, arg13, arg14, null, false, com.google.android.gms.internal.clearcut.zze.zzb(arg12), DefaultClock.getInstance(), null, new zzp(arg12));
    }

    @KeepForSdk public static ClearcutLogger anonymousLogger(Context arg12, String arg13) {
        return new ClearcutLogger(arg12, -1, arg13, null, null, true, com.google.android.gms.internal.clearcut.zze.zzb(arg12), DefaultClock.getInstance(), null, new zzp(arg12));
    }

    @KeepForSdk public final LogEventBuilder newEvent(@Nullable byte[] arg3) {
        return new LogEventBuilder(this, arg3, null);
    }

    private static int zza(Context arg3) {
        int v3_1;
        try {
            v3_1 = arg3.getPackageManager().getPackageInfo(arg3.getPackageName(), 0).versionCode;
        }
        catch(PackageManager$NameNotFoundException v3) {
            Log.wtf("ClearcutLogger", "This can\'t happen.", ((Throwable)v3));
            v3_1 = 0;
        }

        return v3_1;
    }

    static int zza(ClearcutLogger arg0) {
        return arg0.zzk;
    }

    private static int[] zza(ArrayList arg6) {
        if(arg6 == null) {
            return null;
        }

        int[] v0 = new int[arg6.size()];
        int v1 = arg6.size();
        int v2 = 0;
        int v3;
        for(v3 = 0; v2 < v1; ++v3) {
            Object v4 = arg6.get(v2);
            ++v2;
            v0[v3] = ((Integer)v4).intValue();
        }

        return v0;
    }

    static String zzb(ClearcutLogger arg0) {
        return arg0.zzj;
    }

    static int[] zzb(ArrayList arg0) {
        return ClearcutLogger.zza(null);
    }

    static String zzc(ClearcutLogger arg0) {
        return arg0.zzl;
    }

    static zzb zzd(ClearcutLogger arg0) {
        return arg0.zzo;
    }

    static Context zze(ClearcutLogger arg0) {
        return arg0.zzh;
    }

    static Clock zzf(ClearcutLogger arg0) {
        return arg0.zzq;
    }

    static zzc zzg(ClearcutLogger arg0) {
        return arg0.zzr;
    }

    static boolean zzh(ClearcutLogger arg0) {
        return arg0.zzn;
    }

    static String zzi(ClearcutLogger arg0) {
        return arg0.packageName;
    }

    static int zzj(ClearcutLogger arg0) {
        return arg0.zzi;
    }

    static com.google.android.gms.clearcut.ClearcutLogger$zza zzk(ClearcutLogger arg0) {
        return arg0.zzs;
    }

    static com.google.android.gms.clearcut.zzb zzl(ClearcutLogger arg0) {
        return arg0.zzp;
    }
}

