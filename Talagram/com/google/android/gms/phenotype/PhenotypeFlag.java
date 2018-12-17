package com.google.android.gms.phenotype;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build$VERSION;
import android.os.UserManager;
import android.support.v4.content.d;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.phenotype.zzf;
import com.google.android.gms.internal.phenotype.zzh;
import javax.annotation.Nullable;

@KeepForSdk @Deprecated public abstract class PhenotypeFlag {
    @KeepForSdk public class Factory {
        private final String zzax;
        private final Uri zzay;
        private final String zzaz;
        private final String zzba;
        private final boolean zzbb;
        private final boolean zzbc;

        @KeepForSdk public Factory(Uri arg8) {
            this(null, arg8, "", "", false, false);
        }

        private Factory(String arg1, Uri arg2, String arg3, String arg4, boolean arg5, boolean arg6) {
            super();
            this.zzax = arg1;
            this.zzay = arg2;
            this.zzaz = arg3;
            this.zzba = arg4;
            this.zzbb = arg5;
            this.zzbc = arg6;
        }

        @KeepForSdk public PhenotypeFlag createFlag(String arg1, String arg2) {
            return PhenotypeFlag.zzb(this, arg1, arg2);
        }

        @KeepForSdk public Factory withGservicePrefix(String arg9) {
            if(!this.zzbb) {
                return new Factory(this.zzax, this.zzay, arg9, this.zzba, this.zzbb, this.zzbc);
            }

            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }

        @KeepForSdk public Factory withPhenotypePrefix(String arg9) {
            return new Factory(this.zzax, this.zzay, this.zzaz, arg9, this.zzbb, this.zzbc);
        }

        static String zza(Factory arg0) {
            return arg0.zzax;
        }

        static Uri zzb(Factory arg0) {
            return arg0.zzay;
        }

        static String zzc(Factory arg0) {
            return arg0.zzaz;
        }

        static String zzd(Factory arg0) {
            return arg0.zzba;
        }

        static boolean zze(Factory arg0) {
            return arg0.zzbc;
        }

        static boolean zzf(Factory arg0) {
            return arg0.zzbb;
        }
    }

    interface zza {
        Object zzh();
    }

    private static final Object zzak;
    @SuppressLint(value={"StaticFieldLeak"}) private static Context zzal;
    private static boolean zzam;
    private static Boolean zzan;
    private final Factory zzao;
    final String zzap;
    private final String zzaq;
    private final Object zzar;
    private Object zzas;

    static {
        PhenotypeFlag.zzak = new Object();
        PhenotypeFlag.zzal = null;
        PhenotypeFlag.zzam = false;
        PhenotypeFlag.zzan = null;
    }

    private PhenotypeFlag(Factory arg4, String arg5, Object arg6) {
        super();
        this.zzas = null;
        if(Factory.zza(arg4) == null) {
            if(Factory.zzb(arg4) != null) {
            }
            else {
                throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
            }
        }

        if(Factory.zza(arg4) != null) {
            if(Factory.zzb(arg4) == null) {
            }
            else {
                throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
            }
        }

        this.zzao = arg4;
        String v0 = String.valueOf(Factory.zzc(arg4));
        String v1 = String.valueOf(arg5);
        v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
        this.zzaq = v0;
        String v4 = String.valueOf(Factory.zzd(arg4));
        arg5 = String.valueOf(arg5);
        v4 = arg5.length() != 0 ? v4.concat(arg5) : new String(v4);
        this.zzap = v4;
        this.zzar = arg6;
    }

    PhenotypeFlag(Factory arg1, String arg2, Object arg3, zzr arg4) {
        this(arg1, arg2, arg3);
    }

    @KeepForSdk public Object get() {
        Object v0;
        if(PhenotypeFlag.zzal != null) {
            if(Factory.zze(this.zzao)) {
                v0 = this.zze();
                if(v0 != null) {
                    return v0;
                }
                else {
                    v0 = this.zzd();
                    if(v0 != null) {
                        return v0;
                    }
                }
            }
            else {
                v0 = this.zzd();
                if(v0 != null) {
                    return v0;
                }
                else {
                    v0 = this.zze();
                    if(v0 != null) {
                        return v0;
                    }
                }
            }

            return this.zzar;
        }

        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    @KeepForSdk public static void maybeInit(Context arg3) {
        zzh.maybeInit(arg3);
        if(PhenotypeFlag.zzal == null) {
            zzh.init(arg3);
            Object v0 = PhenotypeFlag.zzak;
            __monitor_enter(v0);
            try {
                if(Build$VERSION.SDK_INT < 24 || !arg3.isDeviceProtectedStorage()) {
                    Context v1 = arg3.getApplicationContext();
                    if(v1 == null) {
                    }
                    else {
                        arg3 = v1;
                    }
                }
                else {
                }

                if(PhenotypeFlag.zzal != arg3) {
                    PhenotypeFlag.zzan = null;
                }

                PhenotypeFlag.zzal = arg3;
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                try {
                label_26:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_26;
                }

                throw v3;
            }

            PhenotypeFlag.zzam = false;
        }
    }

    private static PhenotypeFlag zza(Factory arg1, String arg2, String arg3) {
        return new zzs(arg1, arg2, arg3);
    }

    private static Object zza(zza arg2) {
        Object v2;
        try {
            v2 = arg2.zzh();
        }
        catch(SecurityException ) {
            long v0 = Binder.clearCallingIdentity();
            try {
                v2 = ((zza)v2).zzh();
            }
            catch(Throwable v2_1) {
                Binder.restoreCallingIdentity(v0);
                throw v2_1;
            }

            Binder.restoreCallingIdentity(v0);
        }

        return v2;
    }

    static boolean zza(String arg1, boolean arg2) {
        if(PhenotypeFlag.zzf()) {
            return PhenotypeFlag.zza(new zzq(arg1, false)).booleanValue();
        }

        return 0;
    }

    public abstract Object zza(String arg1);

    public abstract Object zza(SharedPreferences arg1);

    static PhenotypeFlag zzb(Factory arg0, String arg1, String arg2) {
        return PhenotypeFlag.zza(arg0, arg1, arg2);
    }

    static final Boolean zzb(String arg1, boolean arg2) {
        return Boolean.valueOf(zzf.zza(PhenotypeFlag.zzal.getContentResolver(), arg1, arg2));
    }

    @TargetApi(value=24) @Nullable private final Object zzd() {
        Object v2 = null;
        if(PhenotypeFlag.zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String v0_2 = "PhenotypeFlag";
            String v1 = "Bypass reading Phenotype values for flag: ";
            String v3 = String.valueOf(this.zzap);
            v1 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
            Log.w(v0_2, v1);
        }
        else if(Factory.zzb(this.zzao) != null) {
            Object v0 = PhenotypeFlag.zza(new zzo(this, com.google.android.gms.phenotype.zza.zza(PhenotypeFlag.zzal.getContentResolver(), Factory.zzb(this.zzao))));
            if(v0 != null) {
                return this.zza(((String)v0));
            }
        }
        else if(Factory.zza(this.zzao) != null) {
            if(Build$VERSION.SDK_INT >= 24 && !PhenotypeFlag.zzal.isDeviceProtectedStorage() && !PhenotypeFlag.zzal.getSystemService(UserManager.class).isUserUnlocked()) {
                return v2;
            }

            SharedPreferences v0_1 = PhenotypeFlag.zzal.getSharedPreferences(Factory.zza(this.zzao), 0);
            if(!v0_1.contains(this.zzap)) {
                return v2;
            }

            return this.zza(v0_1);
        }

        return v2;
    }

    @Nullable private final Object zze() {
        if(!Factory.zzf(this.zzao) && (PhenotypeFlag.zzf())) {
            Object v0 = PhenotypeFlag.zza(new zzp(this));
            if(v0 != null) {
                return this.zza(((String)v0));
            }
        }

        return null;
    }

    private static boolean zzf() {
        if(PhenotypeFlag.zzan == null) {
            boolean v1 = false;
            if(PhenotypeFlag.zzal != null) {
                if(d.b(PhenotypeFlag.zzal, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    v1 = true;
                }

                PhenotypeFlag.zzan = Boolean.valueOf(v1);
            }
            else {
                return 0;
            }
        }

        return PhenotypeFlag.zzan.booleanValue();
    }

    final String zzg() {
        return zzf.zza(PhenotypeFlag.zzal.getContentResolver(), this.zzaq, null);
    }
}

