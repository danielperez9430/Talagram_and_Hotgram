package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk @ParametersAreNonnullByDefault public class AdvertisingIdClient {
    @KeepForSdkWithMembers public final class Info {
        private final String zzq;
        private final boolean zzr;

        public Info(String arg1, boolean arg2) {
            super();
            this.zzq = arg1;
            this.zzr = arg2;
        }

        public final String getId() {
            return this.zzq;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.zzr;
        }

        public final String toString() {
            String v0 = this.zzq;
            boolean v1 = this.zzr;
            StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 7);
            v3.append("{");
            v3.append(v0);
            v3.append("}");
            v3.append(v1);
            return v3.toString();
        }
    }

    @VisibleForTesting final class zza extends Thread {
        private WeakReference zzm;
        private long zzn;
        CountDownLatch zzo;
        boolean zzp;

        public zza(AdvertisingIdClient arg2, long arg3) {
            super();
            this.zzm = new WeakReference(arg2);
            this.zzn = arg3;
            this.zzo = new CountDownLatch(1);
            this.zzp = false;
            this.start();
        }

        private final void disconnect() {
            Object v0 = this.zzm.get();
            if(v0 != null) {
                ((AdvertisingIdClient)v0).finish();
                this.zzp = true;
            }
        }

        public final void run() {
            try {
                if(!this.zzo.await(this.zzn, TimeUnit.MILLISECONDS)) {
                    this.disconnect();
                }
            }
            catch(InterruptedException ) {
                this.disconnect();
                return;
            }
        }
    }

    @GuardedBy(value="this") private final Context mContext;
    @GuardedBy(value="this") private BlockingServiceConnection zze;
    @GuardedBy(value="this") private zze zzf;
    @GuardedBy(value="this") private boolean zzg;
    private final Object zzh;
    @GuardedBy(value="mAutoDisconnectTaskLock") private zza zzi;
    private final boolean zzj;
    private final long zzk;

    @KeepForSdk public AdvertisingIdClient(Context arg7) {
        this(arg7, 30000, false, false);
    }

    @VisibleForTesting private AdvertisingIdClient(Context arg2, long arg3, boolean arg5, boolean arg6) {
        super();
        this.zzh = new Object();
        Preconditions.checkNotNull(arg2);
        if(arg5) {
            Context v5 = arg2.getApplicationContext();
            if(v5 == null) {
            }
            else {
                arg2 = v5;
            }
        }

        this.mContext = arg2;
        this.zzg = false;
        this.zzk = arg3;
        this.zzj = arg6;
    }

    protected void finalize() {
        this.finish();
        super.finalize();
    }

    public final void finish() {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        __monitor_enter(this);
        try {
            if(this.mContext != null) {
                if(this.zze == null) {
                    goto label_7;
                }
                else {
                    goto label_8;
                }
            }

            goto label_26;
        }
        catch(Throwable v0) {
            goto label_29;
        }

    label_7:
        goto label_26;
        try {
        label_8:
            if(this.zzg) {
                ConnectionTracker.getInstance().unbindService(this.mContext, this.zze);
            }
            else {
            }

            goto label_19;
        }
        catch(Throwable v0) {
        label_30:
            throw v0;
        }
        catch(Throwable v0) {
            try {
                Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", v0);
            label_19:
                this.zzg = false;
                this.zzf = null;
                this.zze = null;
                __monitor_exit(this);
                return;
            label_26:
                __monitor_exit(this);
                return;
            label_29:
                __monitor_exit(this);
                goto label_30;
            }
            catch(Throwable v0) {
                goto label_29;
            }
        }
    }

    @KeepForSdk public static Info getAdvertisingIdInfo(Context arg13) {
        Info v13_1;
        zzb v0 = new zzb(arg13);
        boolean v1 = v0.getBoolean("gads:ad_id_app_context:enabled", false);
        float v11 = v0.getFloat("gads:ad_id_app_context:ping_ratio", 0f);
        String v12 = v0.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        AdvertisingIdClient v0_1 = new AdvertisingIdClient(arg13, -1, v1, v0.getBoolean("gads:ad_id_use_persistent_service:enabled", false));
        try {
            long v3 = SystemClock.elapsedRealtime();
            v0_1.zza(false);
            v13_1 = v0_1.getInfo();
            v0_1.zza(v13_1, v1, v11, SystemClock.elapsedRealtime() - v3, v12, null);
        }
        catch(Throwable v13) {
        }
        catch(Throwable v13) {
            Info v4 = null;
            long v7 = -1;
            AdvertisingIdClient v3_1 = v0_1;
            boolean v5 = v1;
            float v6 = v11;
            String v9 = v12;
            Throwable v10 = v13;
            try {
                v3_1.zza(v4, v5, v6, v7, v9, v10);
                throw v13;
            }
            catch(Throwable v13) {
                v0_1.finish();
                throw v13;
            }
        }

        v0_1.finish();
        return v13_1;
    }

    @KeepForSdk public Info getInfo() {
        Info v0_4;
        Object v0_1;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        __monitor_enter(this);
        try {
            if(!this.zzg) {
                v0_1 = this.zzh;
                __monitor_enter(v0_1);
                goto label_7;
            }

            goto label_34;
        }
        catch(Throwable v0) {
            goto label_57;
        }

        try {
        label_7:
            if(this.zzi != null && (this.zzi.zzp)) {
                __monitor_exit(v0_1);
                v0_1 = null;
                goto label_14;
            }

            goto label_27;
        }
        catch(Throwable v1) {
            goto label_32;
        }

        try {
        label_14:
            this.zza(false);
            goto label_15;
        }
        catch(Throwable v0) {
        }
        catch(Exception v0_2) {
            try {
                throw new IOException("AdvertisingIdClient cannot reconnect.", ((Throwable)v0_2));
            label_15:
                if(this.zzg) {
                    goto label_34;
                }
                else {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            catch(Throwable v0) {
                goto label_57;
            }

            try {
            label_27:
                throw new IOException("AdvertisingIdClient is not connected.");
            label_32:
                __monitor_exit(v0_1);
            }
            catch(Throwable v1) {
                goto label_32;
            }

            try {
                throw v1;
            label_34:
                Preconditions.checkNotNull(this.zze);
                Preconditions.checkNotNull(this.zzf);
            }
            catch(Throwable v0) {
                goto label_57;
            }

            try {
                v0_4 = new Info(this.zzf.getId(), this.zzf.zzb(true));
                goto label_45;
            }
            catch(Throwable v0) {
            label_58:
                throw v0;
            }
            catch(RemoteException v0_3) {
                try {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", ((Throwable)v0_3));
                    throw new IOException("Remote exception");
                label_45:
                    __monitor_exit(this);
                    goto label_46;
                label_57:
                    __monitor_exit(this);
                    goto label_58;
                }
                catch(Throwable v0) {
                    goto label_57;
                }
            }
        }

    label_46:
        this.zza();
        return v0_4;
    }

    @KeepForSdk public static boolean getIsAdIdFakeForDebugLogging(Context arg9) {
        boolean v9_1;
        zzb v0 = new zzb(arg9);
        AdvertisingIdClient v0_1 = new AdvertisingIdClient(arg9, -1, v0.getBoolean("gads:ad_id_app_context:enabled", false), v0.getBoolean("com.google.android.gms.ads.identifier.service.PERSISTENT_START", false));
        try {
            v0_1.zza(false);
            v9_1 = v0_1.zzb();
        }
        catch(Throwable v9) {
            v0_1.finish();
            throw v9;
        }

        v0_1.finish();
        return v9_1;
    }

    @KeepForSdk public static void setShouldSkipGmsCoreVersionCheck(boolean arg0) {
    }

    @KeepForSdk public void start() {
        this.zza(true);
    }

    @VisibleForTesting private final void zza(boolean arg3) {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        __monitor_enter(this);
        try {
            if(this.zzg) {
                this.finish();
            }

            this.zze = AdvertisingIdClient.zza(this.mContext, this.zzj);
            this.zzf = AdvertisingIdClient.zza(this.mContext, this.zze);
            this.zzg = true;
            if(arg3) {
                this.zza();
            }

            __monitor_exit(this);
            return;
        label_21:
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            goto label_21;
        }

        throw v3;
    }

    @VisibleForTesting private final boolean zza(Info arg5, boolean arg6, float arg7, long arg8, String arg10, Throwable arg11) {
        if(Math.random() > (((double)arg7))) {
            return 0;
        }

        HashMap v7 = new HashMap();
        String v0 = "app_context";
        String v6 = arg6 ? "1" : "0";
        ((Map)v7).put(v0, v6);
        if(arg5 != null) {
            v6 = "limit_ad_tracking";
            v0 = arg5.isLimitAdTrackingEnabled() ? "1" : "0";
            ((Map)v7).put(v6, v0);
        }

        if(arg5 != null && arg5.getId() != null) {
            ((Map)v7).put("ad_id_size", Integer.toString(arg5.getId().length()));
        }

        if(arg11 != null) {
            ((Map)v7).put("error", arg11.getClass().getName());
        }

        if(arg10 != null && !arg10.isEmpty()) {
            ((Map)v7).put("experiment_id", arg10);
        }

        ((Map)v7).put("tag", "AdvertisingIdClient");
        ((Map)v7).put("time_spent", Long.toString(arg8));
        new com.google.android.gms.ads.identifier.zza(this, ((Map)v7)).start();
        return 1;
    }

    private static BlockingServiceConnection zza(Context arg3, boolean arg4) {
        try {
            arg3.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        catch(PackageManager$NameNotFoundException ) {
            throw new GooglePlayServicesNotAvailableException(9);
        }

        int v0 = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(arg3, 12451000);
        if(v0 != 0) {
            if(v0 == 2) {
            }
            else {
                throw new IOException("Google Play services not available");
            }
        }

        String v4 = arg4 ? "com.google.android.gms.ads.identifier.service.PERSISTENT_START" : "com.google.android.gms.ads.identifier.service.START";
        BlockingServiceConnection v0_1 = new BlockingServiceConnection();
        Intent v1 = new Intent(v4);
        v1.setPackage("com.google.android.gms");
        try {
            if(!ConnectionTracker.getInstance().bindService(arg3, v1, ((ServiceConnection)v0_1), 1)) {
                goto label_30;
            }
        }
        catch(Throwable v3) {
            throw new IOException(v3);
        }

        return v0_1;
    label_30:
        throw new IOException("Connection failure");
    }

    @VisibleForTesting private static zze zza(Context arg2, BlockingServiceConnection arg3) {
        long v0 = 10000;
        try {
            return zzf.zza(arg3.getServiceWithTimeout(v0, TimeUnit.MILLISECONDS));
        }
        catch(Throwable v2) {
            throw new IOException(v2);
        }
        catch(InterruptedException ) {
            throw new IOException("Interrupted exception");
        }
    }

    private final void zza() {
        Object v0 = this.zzh;
        __monitor_enter(v0);
        try {
            if(this.zzi != null) {
                this.zzi.zzo.countDown();
                try {
                    this.zzi.join();
                    goto label_9;
                }
                catch(InterruptedException ) {
                label_9:
                    if(this.zzk > 0) {
                        this.zzi = new zza(this, this.zzk);
                    }

                    __monitor_exit(v0);
                    return;
                }
            }

            goto label_9;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_19;
        }

        throw v1;
    }

    private final boolean zzb() {
        boolean v0_4;
        Object v0_1;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        __monitor_enter(this);
        try {
            if(!this.zzg) {
                v0_1 = this.zzh;
                __monitor_enter(v0_1);
                goto label_7;
            }

            goto label_34;
        }
        catch(Throwable v0) {
            goto label_52;
        }

        try {
        label_7:
            if(this.zzi != null && (this.zzi.zzp)) {
                __monitor_exit(v0_1);
                v0_1 = null;
                goto label_14;
            }

            goto label_27;
        }
        catch(Throwable v1) {
            goto label_32;
        }

        try {
        label_14:
            this.zza(false);
        }
        catch(Exception v0_2) {
            throw new IOException("AdvertisingIdClient cannot reconnect.", ((Throwable)v0_2));
        }

        if(this.zzg) {
            goto label_34;
        }
        else {
            throw new IOException("AdvertisingIdClient cannot reconnect.");
        }

        try {
        label_27:
            throw new IOException("AdvertisingIdClient is not connected.");
        label_32:
            __monitor_exit(v0_1);
        }
        catch(Throwable v1) {
            goto label_32;
        }

        try {
            throw v1;
        label_34:
            Preconditions.checkNotNull(this.zze);
            Preconditions.checkNotNull(this.zzf);
            try {
                v0_4 = this.zzf.zzc();
                goto label_40;
            }
            catch(RemoteException v0_3) {
                try {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", ((Throwable)v0_3));
                    throw new IOException("Remote exception");
                label_40:
                    __monitor_exit(this);
                    goto label_41;
                label_52:
                    __monitor_exit(this);
                }
                catch(Throwable v0) {
                    goto label_52;
                }
            }
        }
        catch(Throwable v0) {
            goto label_52;
        }

        throw v0;
    label_41:
        this.zza();
        return v0_4;
    }
}

