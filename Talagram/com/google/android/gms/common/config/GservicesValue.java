package com.google.android.gms.common.config;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Binder;
import android.os.Process;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.stable.zzg;
import com.google.android.gms.internal.stable.zzi;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public abstract class GservicesValue {
    interface zza {
        Long getLong(String arg1, Long arg2);

        String getString(String arg1, String arg2);

        Boolean zza(String arg1, Boolean arg2);

        Double zza(String arg1, Double arg2);

        Float zza(String arg1, Float arg2);

        Integer zza(String arg1, Integer arg2);

        String zzb(String arg1, String arg2);
    }

    final class zzb implements zza {
        private static final Collection zzna;

        static {
            zzb.zzna = new HashSet();
        }

        zzb(com.google.android.gms.common.config.zza arg1) {
            this();
        }

        private zzb() {
            super();
        }

        public final Long getLong(String arg1, Long arg2) {
            return arg2;
        }

        public final String getString(String arg1, String arg2) {
            return arg2;
        }

        public final Boolean zza(String arg1, Boolean arg2) {
            return arg2;
        }

        public final Double zza(String arg1, Double arg2) {
            return arg2;
        }

        public final Float zza(String arg1, Float arg2) {
            return arg2;
        }

        public final Integer zza(String arg1, Integer arg2) {
            return arg2;
        }

        public final String zzb(String arg1, String arg2) {
            return arg2;
        }

        static Collection zzci() {
            return zzb.zzna;
        }
    }

    @Deprecated final class zzc implements zza {
        private final Map values;

        public zzc(Map arg1) {
            super();
            this.values = arg1;
        }

        public final Long getLong(String arg1, Long arg2) {
            return this.zza(arg1, arg2);
        }

        public final String getString(String arg1, String arg2) {
            return this.zza(arg1, arg2);
        }

        private final Object zza(String arg2, Object arg3) {
            if(this.values.containsKey(arg2)) {
                return this.values.get(arg2);
            }

            return arg3;
        }

        public final Boolean zza(String arg1, Boolean arg2) {
            return this.zza(arg1, arg2);
        }

        public final Double zza(String arg1, Double arg2) {
            return this.zza(arg1, arg2);
        }

        public final Float zza(String arg1, Float arg2) {
            return this.zza(arg1, arg2);
        }

        public final Integer zza(String arg1, Integer arg2) {
            return this.zza(arg1, arg2);
        }

        public final String zzb(String arg1, String arg2) {
            return this.zza(arg1, arg2);
        }
    }

    final class zzd implements zza {
        private final ContentResolver mContentResolver;

        public zzd(ContentResolver arg1) {
            super();
            this.mContentResolver = arg1;
        }

        public final Long getLong(String arg4, Long arg5) {
            return Long.valueOf(zzi.getLong(this.mContentResolver, arg4, arg5.longValue()));
        }

        public final String getString(String arg2, String arg3) {
            return zzi.zza(this.mContentResolver, arg2, arg3);
        }

        public final Boolean zza(String arg2, Boolean arg3) {
            return Boolean.valueOf(zzi.zza(this.mContentResolver, arg2, arg3.booleanValue()));
        }

        public final Double zza(String arg3, Double arg4) {
            arg3 = zzi.zza(this.mContentResolver, arg3, null);
            if(arg3 == null) {
                return arg4;
            }

            try {
                return Double.valueOf(Double.parseDouble(arg3));
            }
            catch(NumberFormatException ) {
                return arg4;
            }
        }

        public final Float zza(String arg3, Float arg4) {
            arg3 = zzi.zza(this.mContentResolver, arg3, null);
            if(arg3 == null) {
                return arg4;
            }

            try {
                return Float.valueOf(Float.parseFloat(arg3));
            }
            catch(NumberFormatException ) {
                return arg4;
            }
        }

        public final Integer zza(String arg2, Integer arg3) {
            return Integer.valueOf(zzi.getInt(this.mContentResolver, arg2, arg3.intValue()));
        }

        public final String zzb(String arg2, String arg3) {
            return zzg.zza(this.mContentResolver, arg2, arg3);
        }
    }

    protected final Object mDefaultValue;
    protected final String mKey;
    private static final Object sLock;
    private static zza zzmu;
    private static int zzmv;
    private static Context zzmw;
    private static String zzmx;
    @GuardedBy(value="sLock") private static HashSet zzmy;
    private Object zzmz;

    static {
        GservicesValue.sLock = new Object();
        GservicesValue.zzmu = null;
        GservicesValue.zzmv = 0;
        GservicesValue.zzmx = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }

    protected GservicesValue(String arg2, Object arg3) {
        super();
        this.zzmz = null;
        this.mKey = arg2;
        this.mDefaultValue = arg3;
    }

    @VisibleForTesting @Deprecated public static void forceInit(Context arg1) {
        GservicesValue.forceInit(arg1, new HashSet());
    }

    @VisibleForTesting public static void forceInit(Context arg2, HashSet arg3) {
        GservicesValue.zza(arg2, new zzd(arg2.getContentResolver()), arg3);
    }

    public final Object get() {
        Object v3_2;
        long v1_4;
        String v2_1;
        String v1_1;
        String v0_2;
        Context v4;
        if(this.zzmz != null) {
            return this.zzmz;
        }

        StrictMode$ThreadPolicy v0 = StrictMode.allowThreadDiskReads();
        Object v1 = GservicesValue.sLock;
        __monitor_enter(v1);
        try {
            int v2 = GservicesValue.zzmw == null || !GservicesValue.zzd(GservicesValue.zzmw) ? 0 : 1;
            HashSet v3 = GservicesValue.zzmy;
            v4 = GservicesValue.zzmw;
            __monitor_exit(v1);
            if(v2 == 0) {
                goto label_57;
            }
        }
        catch(Throwable v0_1) {
            try {
            label_84:
                __monitor_exit(v1);
            }
            catch(Throwable v0_1) {
                goto label_84;
            }

            throw v0_1;
        }

        if(Log.isLoggable("GservicesValue", 3)) {
            v0_2 = "GservicesValue";
            v1_1 = "Gservice value accessed during directboot: ";
            v2_1 = String.valueOf(this.mKey);
            v1_1 = v2_1.length() != 0 ? v1_1.concat(v2_1) : new String(v1_1);
            Log.d(v0_2, v1_1);
        }

        if(v3 != null && !v3.contains(this.mKey)) {
            v0_2 = "GservicesValue";
            v1_1 = "Gservices key not whitelisted for directboot access: ";
            v2_1 = String.valueOf(this.mKey);
            v1_1 = v2_1.length() != 0 ? v1_1.concat(v2_1) : new String(v1_1);
            Log.e(v0_2, v1_1);
            return this.mDefaultValue;
        }

        return this.retrieveFromDirectBootCache(v4, this.mKey, this.mDefaultValue);
    label_57:
        Object v2_2 = GservicesValue.sLock;
        __monitor_enter(v2_2);
        HashSet v1_2 = null;
        try {
            GservicesValue.zzmy = v1_2;
            GservicesValue.zzmw = ((Context)v1_2);
            __monitor_exit(v2_2);
        }
        catch(Throwable v0_1) {
            try {
            label_81:
                __monitor_exit(v2_2);
            }
            catch(Throwable v0_1) {
                goto label_81;
            }

            throw v0_1;
        }

        try {
            v1 = this.retrieve(this.mKey);
        }
        catch(Throwable v1_3) {
        label_78:
            StrictMode.setThreadPolicy(v0);
            throw v1_3;
        }
        catch(SecurityException ) {
            try {
                v1_4 = Binder.clearCallingIdentity();
            }
            catch(Throwable v1_3) {
                goto label_78;
            }

            try {
                v3_2 = this.retrieve(this.mKey);
                goto label_72;
            }
            catch(Throwable v3_1) {
                try {
                    Binder.restoreCallingIdentity(v1_4);
                    throw v3_1;
                label_72:
                    Binder.restoreCallingIdentity(v1_4);
                }
                catch(Throwable v1_3) {
                    goto label_78;
                }
            }

            StrictMode.setThreadPolicy(v0);
            return v3_2;
        }

        StrictMode.setThreadPolicy(v0);
        return v1;
    }

    @Deprecated public final Object getBinderSafe() {
        return this.get();
    }

    @TargetApi(value=24) public static SharedPreferences getDirectBootCache(Context arg2) {
        return arg2.getApplicationContext().createDeviceProtectedStorageContext().getSharedPreferences("gservices-direboot-cache", 0);
    }

    public String getKey() {
        return this.mKey;
    }

    public static int getSharedUserId() {
        return GservicesValue.zzmv;
    }

    @Deprecated public static void init(Context arg1) {
        HashSet v0 = GservicesValue.zzd(arg1) ? new HashSet() : null;
        GservicesValue.init(arg1, v0);
    }

    public static void init(Context arg3, HashSet arg4) {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            if(GservicesValue.zzmu == null) {
                GservicesValue.zza(arg3, new zzd(arg3.getContentResolver()), arg4);
            }

            if(GservicesValue.zzmv != 0) {
                goto label_21;
            }

            try {
                GservicesValue.zzmv = arg3.getPackageManager().getApplicationInfo("com.google.android.gms", 0).uid;
                goto label_21;
            }
            catch(PackageManager$NameNotFoundException v3_1) {
                try {
                    Log.e("GservicesValue", v3_1.toString());
                label_21:
                    __monitor_exit(v0);
                    return;
                label_24:
                    __monitor_exit(v0);
                }
                catch(Throwable v3) {
                    goto label_24;
                }
            }
        }
        catch(Throwable v3) {
            goto label_24;
        }

        throw v3;
    }

    @VisibleForTesting @Deprecated public static void initForTests() {
        GservicesValue.zza(null, new zzb(null), new HashSet());
    }

    @VisibleForTesting public static void initForTests(Context arg2, HashSet arg3) {
        GservicesValue.zza(arg2, new zzb(null), arg3);
    }

    @VisibleForTesting @Deprecated public static void initForTests(String arg2, Object arg3) {
        HashMap v0 = new HashMap(1);
        ((Map)v0).put(arg2, arg3);
        GservicesValue.initForTests(((Map)v0));
    }

    @VisibleForTesting @Deprecated public static void initForTests(Map arg2) {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            GservicesValue.zzmu = new zzc(arg2);
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_8;
        }

        throw v2;
    }

    public static boolean isInitialized() {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = GservicesValue.zzmu != null ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_10:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_10;
        }

        throw v1;
    }

    @VisibleForTesting public void override(Object arg3) {
        if(!(GservicesValue.zzmu instanceof zzb)) {
            Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        }

        this.zzmz = arg3;
        arg3 = GservicesValue.sLock;
        __monitor_enter(arg3);
        try {
            if(GservicesValue.zzcg()) {
                zzb.zzci().add(this);
            }

            __monitor_exit(arg3);
            return;
        label_16:
            __monitor_exit(arg3);
        }
        catch(Throwable v0) {
            goto label_16;
        }

        throw v0;
    }

    public static GservicesValue partnerSetting(String arg1, String arg2) {
        return new com.google.android.gms.common.config.zzg(arg1, arg2);
    }

    @VisibleForTesting public static void resetAllOverrides() {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            if(GservicesValue.zzcg()) {
                Iterator v1_1 = zzb.zzci().iterator();
                while(v1_1.hasNext()) {
                    v1_1.next().resetOverride();
                }

                zzb.zzci().clear();
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    @VisibleForTesting public void resetOverride() {
        this.zzmz = null;
    }

    protected abstract Object retrieve(String arg1);

    @TargetApi(value=24) protected Object retrieveFromDirectBootCache(Context arg1, String arg2, Object arg3) {
        throw new UnsupportedOperationException("The Gservices classes used does not support direct-boot");
    }

    public static GservicesValue value(String arg1, Double arg2) {
        return new com.google.android.gms.common.config.zzd(arg1, arg2);
    }

    public static GservicesValue value(String arg1, Float arg2) {
        return new zze(arg1, arg2);
    }

    public static GservicesValue value(String arg1, Integer arg2) {
        return new com.google.android.gms.common.config.zzc(arg1, arg2);
    }

    public static GservicesValue value(String arg1, Long arg2) {
        return new com.google.android.gms.common.config.zzb(arg1, arg2);
    }

    public static GservicesValue value(String arg1, String arg2) {
        return new zzf(arg1, arg2);
    }

    public static GservicesValue value(String arg1, boolean arg2) {
        return new com.google.android.gms.common.config.zza(arg1, Boolean.valueOf(arg2));
    }

    @TargetApi(value=24) private static void zza(Context arg1, zza arg2, HashSet arg3) {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            GservicesValue.zzmu = arg2;
            GservicesValue.zzmy = null;
            GservicesValue.zzmw = null;
            if(arg1 != null && (GservicesValue.zzd(arg1))) {
                GservicesValue.zzmy = arg3;
                GservicesValue.zzmw = arg1.getApplicationContext().createDeviceProtectedStorageContext();
            }

            __monitor_exit(v0);
            return;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    private static boolean zzcg() {
        Object v0 = GservicesValue.sLock;
        __monitor_enter(v0);
        try {
            boolean v1_1 = ((GservicesValue.zzmu instanceof zzb)) || ((GservicesValue.zzmu instanceof zzc)) ? true : false;
            __monitor_exit(v0);
            return v1_1;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_15;
        }

        throw v1;
    }

    static zza zzch() {
        return GservicesValue.zzmu;
    }

    @TargetApi(value=24) private static boolean zzd(Context arg2) {
        if(!PlatformVersion.isAtLeastN()) {
            return 0;
        }

        Object v2 = arg2.getSystemService(UserManager.class);
        if(((UserManager)v2).isUserUnlocked()) {
            return 0;
        }

        if(!((UserManager)v2).isUserRunning(Process.myUserHandle())) {
            return 1;
        }

        return 0;
    }
}

