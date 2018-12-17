package com.google.android.gms.internal.clearcut;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build$VERSION;
import android.os.UserManager;
import android.support.v4.content.d;
import android.util.Log;
import javax.annotation.Nullable;

public abstract class zzae {
    private static final Object zzdn;
    private static boolean zzdo;
    private static volatile Boolean zzdp;
    private static volatile Boolean zzdq;
    private final zzao zzdr;
    final String zzds;
    private final String zzdt;
    private final Object zzdu;
    private Object zzdv;
    private volatile zzab zzdw;
    private volatile SharedPreferences zzdx;
    @SuppressLint(value={"StaticFieldLeak"}) private static Context zzh;

    static {
        zzae.zzdn = new Object();
        zzae.zzh = null;
        zzae.zzdo = false;
        zzae.zzdp = null;
        zzae.zzdq = null;
    }

    private zzae(zzao arg4, String arg5, Object arg6) {
        super();
        this.zzdv = null;
        this.zzdw = null;
        this.zzdx = null;
        if(zzao.zza(arg4) == null) {
            if(zzao.zzb(arg4) != null) {
            }
            else {
                throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
            }
        }

        if(zzao.zza(arg4) != null) {
            if(zzao.zzb(arg4) == null) {
            }
            else {
                throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
            }
        }

        this.zzdr = arg4;
        String v0 = String.valueOf(zzao.zzc(arg4));
        String v1 = String.valueOf(arg5);
        v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
        this.zzdt = v0;
        String v4 = String.valueOf(zzao.zzd(arg4));
        arg5 = String.valueOf(arg5);
        v4 = arg5.length() != 0 ? v4.concat(arg5) : new String(v4);
        this.zzds = v4;
        this.zzdu = arg6;
    }

    zzae(zzao arg1, String arg2, Object arg3, zzai arg4) {
        this(arg1, arg2, arg3);
    }

    public final Object get() {
        Object v0;
        if(zzae.zzh != null) {
            if(zzao.zze(this.zzdr)) {
                v0 = this.zzm();
                if(v0 != null) {
                    return v0;
                }
                else {
                    v0 = this.zzl();
                    if(v0 != null) {
                        return v0;
                    }
                }
            }
            else {
                v0 = this.zzl();
                if(v0 != null) {
                    return v0;
                }
                else {
                    v0 = this.zzm();
                    if(v0 != null) {
                        return v0;
                    }
                }
            }

            return this.zzdu;
        }

        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    public static void maybeInit(Context arg3) {
        if(zzae.zzh == null) {
            Object v0 = zzae.zzdn;
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

                if(zzae.zzh != arg3) {
                    zzae.zzdp = null;
                }

                zzae.zzh = arg3;
                __monitor_exit(v0);
            }
            catch(Throwable v3) {
                try {
                label_24:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_24;
                }

                throw v3;
            }

            zzae.zzdo = false;
        }
    }

    static boolean zza(String arg1, boolean arg2) {
        if(zzae.zzn()) {
            return zzae.zza(new zzah(arg1, false)).booleanValue();
        }

        return 0;
    }

    private static zzae zza(zzao arg1, String arg2, Object arg3, zzan arg4) {
        return new zzal(arg1, arg2, arg3, arg4);
    }

    private static zzae zza(zzao arg1, String arg2, String arg3) {
        return new zzak(arg1, arg2, arg3);
    }

    private static zzae zza(zzao arg1, String arg2, boolean arg3) {
        return new zzaj(arg1, arg2, Boolean.valueOf(arg3));
    }

    private static Object zza(zzam arg2) {
        Object v2;
        try {
            v2 = arg2.zzp();
        }
        catch(SecurityException ) {
            long v0 = Binder.clearCallingIdentity();
            try {
                v2 = ((zzam)v2).zzp();
            }
            catch(Throwable v2_1) {
                Binder.restoreCallingIdentity(v0);
                throw v2_1;
            }

            Binder.restoreCallingIdentity(v0);
        }

        return v2;
    }

    protected abstract Object zza(SharedPreferences arg1);

    static zzae zzb(zzao arg0, String arg1, Object arg2, zzan arg3) {
        return zzae.zza(arg0, arg1, arg2, arg3);
    }

    static zzae zzb(zzao arg0, String arg1, String arg2) {
        return zzae.zza(arg0, arg1, null);
    }

    static zzae zzb(zzao arg0, String arg1, boolean arg2) {
        return zzae.zza(arg0, arg1, false);
    }

    static final Boolean zzb(String arg1, boolean arg2) {
        return Boolean.valueOf(zzy.zza(zzae.zzh.getContentResolver(), arg1, arg2));
    }

    protected abstract Object zzb(String arg1);

    @TargetApi(value=24) @Nullable private final Object zzl() {
        boolean v0_1;
        Object v2 = null;
        if(zzae.zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String v0_3 = "PhenotypeFlag";
            String v1 = "Bypass reading Phenotype values for flag: ";
            String v3 = String.valueOf(this.zzds);
            v1 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
            Log.w(v0_3, v1);
        }
        else if(zzao.zzb(this.zzdr) != null) {
            if(this.zzdw == null) {
                this.zzdw = zzab.zza(zzae.zzh.getContentResolver(), zzao.zzb(this.zzdr));
            }

            Object v0 = zzae.zza(new zzaf(this, this.zzdw));
            if(v0 == null) {
                return v2;
            }

            return this.zzb(((String)v0));
        }
        else {
            if(zzao.zza(this.zzdr) == null) {
                return v2;
            }

            if(Build$VERSION.SDK_INT < 24 || (zzae.zzh.isDeviceProtectedStorage())) {
                v0_1 = true;
            }
            else {
                if(zzae.zzdq == null || !zzae.zzdq.booleanValue()) {
                    zzae.zzdq = Boolean.valueOf(zzae.zzh.getSystemService(UserManager.class).isUserUnlocked());
                }

                v0_1 = zzae.zzdq.booleanValue();
            }

            if(!v0_1) {
                return v2;
            }

            if(this.zzdx == null) {
                this.zzdx = zzae.zzh.getSharedPreferences(zzao.zza(this.zzdr), 0);
            }

            SharedPreferences v0_2 = this.zzdx;
            if(!v0_2.contains(this.zzds)) {
                return v2;
            }

            return this.zza(v0_2);
        }

        return v2;
    }

    @Nullable private final Object zzm() {
        if(!zzao.zzf(this.zzdr) && (zzae.zzn())) {
            Object v0 = zzae.zza(new zzag(this));
            if(v0 != null) {
                return this.zzb(((String)v0));
            }
        }

        return null;
    }

    private static boolean zzn() {
        if(zzae.zzdp == null) {
            boolean v1 = false;
            if(zzae.zzh != null) {
                if(d.b(zzae.zzh, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    v1 = true;
                }

                zzae.zzdp = Boolean.valueOf(v1);
            }
            else {
                return 0;
            }
        }

        return zzae.zzdp.booleanValue();
    }

    final String zzo() {
        return zzy.zza(zzae.zzh.getContentResolver(), this.zzdt, null);
    }
}

