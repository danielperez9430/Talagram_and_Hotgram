package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build$VERSION;
import android.support.v4.content.d;
import android.util.Log;
import javax.annotation.Nullable;

public abstract class zzsl {
    private static final Object zzbqy;
    private static boolean zzbqz;
    private static volatile Boolean zzbra;
    private final zzsv zzbrb;
    final String zzbrc;
    private final String zzbrd;
    private final Object zzbre;
    private Object zzbrf;
    private volatile zzsi zzbrg;
    private volatile SharedPreferences zzbrh;
    @SuppressLint(value={"StaticFieldLeak"}) private static Context zzri;

    static {
        zzsl.zzbqy = new Object();
        zzsl.zzri = null;
        zzsl.zzbqz = false;
        zzsl.zzbra = null;
    }

    private zzsl(zzsv arg4, String arg5, Object arg6) {
        super();
        this.zzbrf = null;
        this.zzbrg = null;
        this.zzbrh = null;
        if(zzsv.zza(arg4) != null) {
            this.zzbrb = arg4;
            String v0 = String.valueOf(zzsv.zzb(arg4));
            String v1 = String.valueOf(arg5);
            v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
            this.zzbrd = v0;
            String v4 = String.valueOf(zzsv.zzc(arg4));
            arg5 = String.valueOf(arg5);
            v4 = arg5.length() != 0 ? v4.concat(arg5) : new String(v4);
            this.zzbrc = v4;
            this.zzbre = arg6;
            return;
        }

        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    zzsl(zzsv arg1, String arg2, Object arg3, zzsp arg4) {
        this(arg1, arg2, arg3);
    }

    public final Object get() {
        if(zzsl.zzri != null) {
            Object v0 = this.zzte();
            if(v0 != null) {
                return v0;
            }

            v0 = this.zztf();
            if(v0 != null) {
                return v0;
            }

            return this.zzbre;
        }

        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    public final Object getDefaultValue() {
        return this.zzbre;
    }

    public static void init(Context arg3) {
        Object v0 = zzsl.zzbqy;
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

            if(zzsl.zzri != arg3) {
                zzsl.zzbra = null;
            }

            zzsl.zzri = arg3;
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            try {
            label_22:
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                goto label_22;
            }

            throw v3;
        }

        zzsl.zzbqz = false;
    }

    private static zzsl zza(zzsv arg1, String arg2, double arg3) {
        return new zzss(arg1, arg2, Double.valueOf(arg3));
    }

    private static zzsl zza(zzsv arg1, String arg2, int arg3) {
        return new zzsq(arg1, arg2, Integer.valueOf(arg3));
    }

    private static zzsl zza(zzsv arg1, String arg2, long arg3) {
        return new zzsp(arg1, arg2, Long.valueOf(arg3));
    }

    private static zzsl zza(zzsv arg1, String arg2, String arg3) {
        return new zzst(arg1, arg2, arg3);
    }

    private static zzsl zza(zzsv arg1, String arg2, boolean arg3) {
        return new zzsr(arg1, arg2, Boolean.valueOf(arg3));
    }

    private static Object zza(zzsu arg2) {
        Object v2;
        try {
            v2 = arg2.zztj();
        }
        catch(SecurityException ) {
            long v0 = Binder.clearCallingIdentity();
            try {
                v2 = ((zzsu)v2).zztj();
            }
            catch(Throwable v2_1) {
                Binder.restoreCallingIdentity(v0);
                throw v2_1;
            }

            Binder.restoreCallingIdentity(v0);
        }

        return v2;
    }

    static zzsl zzb(zzsv arg0, String arg1, double arg2) {
        return zzsl.zza(arg0, arg1, arg2);
    }

    static zzsl zzb(zzsv arg0, String arg1, int arg2) {
        return zzsl.zza(arg0, arg1, arg2);
    }

    static zzsl zzb(zzsv arg0, String arg1, long arg2) {
        return zzsl.zza(arg0, arg1, arg2);
    }

    static zzsl zzb(zzsv arg0, String arg1, String arg2) {
        return zzsl.zza(arg0, arg1, arg2);
    }

    static zzsl zzb(zzsv arg0, String arg1, boolean arg2) {
        return zzsl.zza(arg0, arg1, arg2);
    }

    static boolean zzd(String arg2, boolean arg3) {
        try {
            if(!zzsl.zzth()) {
                return 0;
            }

            return zzsl.zza(new zzso(arg2, false)).booleanValue();
        }
        catch(SecurityException v2) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", ((Throwable)v2));
            return 0;
        }

        return 0;
    }

    static final Boolean zze(String arg1, boolean arg2) {
        return Boolean.valueOf(zzsg.zza(zzsl.zzri.getContentResolver(), arg1, arg2));
    }

    protected abstract Object zzfj(String arg1);

    @TargetApi(value=24) @Nullable private final Object zzte() {
        if(zzsl.zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String v0_2 = "PhenotypeFlag";
            String v1 = "Bypass reading Phenotype values for flag: ";
            String v2 = String.valueOf(this.zzbrc);
            v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
            Log.w(v0_2, v1);
        }
        else if(zzsv.zza(this.zzbrb) != null) {
            zzsi v0 = this.zztg();
            if(v0 != null) {
                Object v0_1 = zzsl.zza(new zzsm(this, v0));
                if(v0_1 != null) {
                    return this.zzfj(((String)v0_1));
                }
            }
        }

        return null;
    }

    @Nullable private final Object zztf() {
        if(zzsl.zzth()) {
            try {
                Object v0_1 = zzsl.zza(new zzsn(this));
                if(v0_1 == null) {
                    return null;
                }

                return this.zzfj(((String)v0_1));
            }
            catch(SecurityException v0) {
                String v1 = "PhenotypeFlag";
                String v2 = "Unable to read GServices for flag: ";
                String v3 = String.valueOf(this.zzbrc);
                v2 = v3.length() != 0 ? v2.concat(v3) : new String(v2);
                Log.e(v1, v2, ((Throwable)v0));
            }
        }

        return null;
    }

    private final zzsi zztg() {
        if(this.zzbrg == null) {
            try {
                this.zzbrg = zzsi.zza(zzsl.zzri.getContentResolver(), zzsv.zza(this.zzbrb));
                goto label_8;
            }
            catch(SecurityException ) {
            label_8:
                return this.zzbrg;
            }
        }

        goto label_8;
    }

    private static boolean zzth() {
        if(zzsl.zzbra == null) {
            boolean v1 = false;
            if(zzsl.zzri != null) {
                if(d.a(zzsl.zzri, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    v1 = true;
                }

                zzsl.zzbra = Boolean.valueOf(v1);
            }
            else {
                return 0;
            }
        }

        return zzsl.zzbra.booleanValue();
    }

    final String zzti() {
        return zzsg.zza(zzsl.zzri.getContentResolver(), this.zzbrd, null);
    }
}

