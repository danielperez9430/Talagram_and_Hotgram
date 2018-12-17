package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.PowerManager$WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.providers.PooledExecutorsProvider;
import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.common.stats.WakeLockTracker;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe public class WakeLock {
    public interface Configuration {
        long getMaximumTimeout(String arg1, String arg2);

        boolean isWorkChainsEnabled();
    }

    public class HeldLock {
        private boolean zzaek;
        private Future zzael;
        private final String zzaem;

        HeldLock(WakeLock arg1, String arg2, zza arg3) {
            this(arg1, arg2);
        }

        private HeldLock(WakeLock arg1, String arg2) {
            this.zzaei = arg1;
            super();
            this.zzaek = true;
            this.zzaem = arg2;
        }

        public void finalize() {
            if(this.zzaek) {
                String v0 = "WakeLock";
                String v1 = "HeldLock finalized while still holding the WakeLock! Reason: ";
                String v2 = String.valueOf(this.zzaem);
                v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
                Log.e(v0, v1);
                this.release(0);
            }
        }

        public void release(int arg3) {
            __monitor_enter(this);
            try {
                if(this.zzaek) {
                    goto label_6;
                }
            }
            catch(Throwable v3) {
                goto label_19;
            }

            __monitor_exit(this);
            return;
            try {
            label_6:
                this.zzaek = false;
                if(this.zzael != null) {
                    this.zzael.cancel(false);
                    this.zzael = null;
                }

                WakeLock.zza(this.zzaei, this.zzaem, arg3);
            }
            catch(Throwable v3) {
            label_19:
                __monitor_exit(this);
                throw v3;
            }

            __monitor_exit(this);
        }

        public void release() {
            this.release(0);
        }

        static Future zza(HeldLock arg0, Future arg1) {
            arg0.zzael = arg1;
            return arg1;
        }
    }

    private final PowerManager$WakeLock zzadv;
    private WorkSource zzadw;
    private String zzadx;
    private final int zzady;
    private final String zzadz;
    private final String zzaea;
    private final String zzaeb;
    private boolean zzaec;
    private final Map zzaed;
    private int zzaee;
    private AtomicInteger zzaef;
    private static ScheduledExecutorService zzaeg;
    private static Configuration zzaeh;
    private final Context zzjp;

    static {
        WakeLock.zzaeh = new zza();
    }

    public WakeLock(Context arg8, int arg9, @Nonnull String arg10) {
        String v0 = arg8 == null ? null : arg8.getPackageName();
        String v6 = v0;
        this(arg8, arg9, arg10, null, v6);
    }

    @SuppressLint(value={"UnwrappedWakeLock"}) public WakeLock(Context arg8, int arg9, @Nonnull String arg10, @Nullable String arg11, @Nonnull String arg12) {
        this(arg8, arg9, arg10, arg11, arg12, null);
    }

    public WakeLock(Context arg8, int arg9, @Nonnull String arg10, @Nullable String arg11) {
        String v0 = arg8 == null ? null : arg8.getPackageName();
        String v6 = v0;
        this(arg8, arg9, arg10, arg11, v6);
    }

    @SuppressLint(value={"UnwrappedWakeLock"}) public WakeLock(Context arg3, int arg4, @Nonnull String arg5, @Nullable String arg6, @Nonnull String arg7, @Nullable String arg8) {
        WorkSource v3;
        super();
        this.zzaec = true;
        this.zzaed = new HashMap();
        this.zzaef = new AtomicInteger(0);
        Preconditions.checkNotEmpty(arg5, "Wake lock name can NOT be empty");
        this.zzady = arg4;
        this.zzaea = arg6;
        this.zzaeb = arg8;
        this.zzjp = arg3.getApplicationContext();
        if(!"com.google.android.gms".equals(arg3.getPackageName())) {
            arg6 = String.valueOf("*gcore*:");
            String v0 = String.valueOf(arg5);
            arg6 = v0.length() != 0 ? arg6.concat(v0) : new String(arg6);
            this.zzadz = arg6;
        }
        else {
            this.zzadz = arg5;
        }

        this.zzadv = arg3.getSystemService("power").newWakeLock(arg4, arg5);
        if(WorkSourceUtil.hasWorkSourcePermission(arg3)) {
            if(Strings.isEmptyOrWhitespace(arg7)) {
                arg7 = arg3.getPackageName();
            }

            if(!WakeLock.zzaeh.isWorkChainsEnabled() || arg7 == null || arg8 == null) {
                v3 = WorkSourceUtil.fromPackage(arg3, arg7);
            }
            else {
                StringBuilder v6 = new StringBuilder(String.valueOf(arg7).length() + 42 + String.valueOf(arg8).length());
                v6.append("Using experimental Pi WorkSource chains: ");
                v6.append(arg7);
                v6.append(",");
                v6.append(arg8);
                Log.d("WakeLock", v6.toString());
                this.zzadx = arg7;
                v3 = WorkSourceUtil.fromPackageAndModuleExperimentalPi(arg3, arg7, arg8);
            }

            this.zzadw = v3;
            this.addWorkSource(this.zzadw);
        }

        if(WakeLock.zzaeg == null) {
            WakeLock.zzaeg = PooledExecutorsProvider.getInstance().newSingleThreadScheduledExecutor();
        }
    }

    public void acquire() {
        this.zzaef.incrementAndGet();
        this.zza(null, 0);
    }

    public void acquire(long arg2) {
        this.zzaef.incrementAndGet();
        this.zza(null, arg2);
    }

    public void acquire(String arg3) {
        this.zzaef.incrementAndGet();
        this.zza(arg3, 0);
    }

    public void acquire(String arg2, long arg3) {
        this.zzaef.incrementAndGet();
        this.zza(arg2, arg3);
    }

    public HeldLock acquireLock(long arg4, String arg6) {
        arg4 = Math.min(arg4, WakeLock.zzaeh.getMaximumTimeout(this.zzadz, arg6));
        HeldLock v0 = new HeldLock(this, arg6, null);
        this.zza(arg6, 0);
        HeldLock.zza(v0, WakeLock.zzaeg.schedule(new zzc(new WeakReference(v0)), arg4, TimeUnit.MILLISECONDS));
        return v0;
    }

    public void addWorkSource(WorkSource arg2) {
        if(arg2 != null && (WorkSourceUtil.hasWorkSourcePermission(this.zzjp))) {
            if(this.zzadw != null) {
                this.zzadw.add(arg2);
            }
            else {
                this.zzadw = arg2;
            }

            this.zza(this.zzadw);
        }
    }

    public PowerManager$WakeLock getWakeLock() {
        return this.zzadv;
    }

    public boolean isHeld() {
        return this.zzadv.isHeld();
    }

    public void release() {
        this.zzb(null, 0);
    }

    public void release(int arg2) {
        this.zzb(null, arg2);
    }

    public void release(String arg2) {
        this.zzb(arg2, 0);
    }

    public void release(String arg1, int arg2) {
        this.zzb(arg1, arg2);
    }

    public void removeWorkSource(WorkSource arg2) {
        if(arg2 != null && (WorkSourceUtil.hasWorkSourcePermission(this.zzjp))) {
            try {
                if(this.zzadw != null) {
                    this.zzadw.remove(arg2);
                }

                this.zza(this.zzadw);
                return;
            }
            catch(ArrayIndexOutOfBoundsException v2) {
                Log.e("WakeLock", v2.toString());
            }
        }
    }

    public static void setConfiguration(Configuration arg0) {
        WakeLock.zzaeh = arg0;
    }

    public void setReferenceCounted(boolean arg2) {
        this.zzadv.setReferenceCounted(arg2);
        this.zzaec = arg2;
    }

    public void setWorkSource(WorkSource arg2) {
        if(WorkSourceUtil.hasWorkSourcePermission(this.zzjp)) {
            this.zza(arg2);
            this.zzadw = arg2;
            this.zzadx = null;
        }
    }

    private final void zza(WorkSource arg2) {
        try {
            this.zzadv.setWorkSource(arg2);
            return;
        }
        catch(ArrayIndexOutOfBoundsException v2) {
            Log.wtf("WakeLock", ((RuntimeException)v2).toString());
            return;
        }
    }

    static void zza(WakeLock arg0, int arg1) {
        arg0.zzn(0);
    }

    static void zza(WakeLock arg0, String arg1, int arg2) {
        arg0.zzc(arg1, arg2);
    }

    @SuppressLint(value={"WakelockTimeout"}) private final void zza(String arg13, long arg14) {
        String v5 = this.zzn(arg13);
        __monitor_enter(this);
        try {
            int v0 = 0;
            if((!this.zzaed.isEmpty() || this.zzaee > 0) && !this.zzadv.isHeld()) {
                this.zzaed.clear();
                this.zzaee = 0;
            }

            if(this.zzaec) {
                Object v13_1 = this.zzaed.get(v5);
                if(v13_1 == null) {
                    this.zzaed.put(v5, new Integer[]{Integer.valueOf(1)});
                    v0 = 1;
                }
                else {
                    v13_1[0] = Integer.valueOf(v13_1[0].intValue() + 1);
                }

                if(v0 != 0) {
                    goto label_37;
                }

                goto label_33;
            }
            else {
            label_33:
                if(this.zzaec) {
                    goto label_51;
                }

                if(this.zzaee != 0) {
                    goto label_51;
                }

            label_37:
                WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, v5), 7, this.zzadz, v5, this.zzaeb, this.zzady, this.zzdo(), arg14);
                ++this.zzaee;
            }

        label_51:
            __monitor_exit(this);
        }
        catch(Throwable v13) {
            try {
            label_79:
                __monitor_exit(this);
            }
            catch(Throwable v13) {
                goto label_79;
            }

            throw v13;
        }

        this.zzadv.acquire();
        if(arg14 > 0) {
            WakeLock.zzaeg.schedule(new zzb(this), arg14, TimeUnit.MILLISECONDS);
            if(!PlatformVersion.isAtLeastIceCreamSandwich() && (this.zzaec)) {
                arg13 = "WakeLock";
                String v14 = "Do not acquire with timeout on reference counted wakeLocks before ICS. wakelock: ";
                String v15 = String.valueOf(this.zzadz);
                v14 = v15.length() != 0 ? v14.concat(v15) : new String(v14);
                Log.wtf(arg13, v14);
            }
        }
    }

    private final void zzb(String arg3, int arg4) {
        if(this.zzaef.decrementAndGet() < 0) {
            Log.e("WakeLock", "release without a matched acquire!");
        }

        this.zzc(arg3, arg4);
    }

    private final void zzc(String arg11, int arg12) {
        String v5 = this.zzn(arg11);
        __monitor_enter(this);
        try {
            if(this.zzaec) {
                Object v11_1 = this.zzaed.get(v5);
                int v0 = 0;
                if(v11_1 == null) {
                }
                else if(v11_1[0].intValue() == 1) {
                    this.zzaed.remove(v5);
                    v0 = 1;
                }
                else {
                    v11_1[0] = Integer.valueOf(v11_1[0].intValue() - 1);
                }

                if(v0 != 0) {
                    goto label_27;
                }

                goto label_23;
            }
            else {
            label_23:
                if(this.zzaec) {
                    goto label_40;
                }

                if(this.zzaee != 1) {
                    goto label_40;
                }

            label_27:
                WakeLockTracker.getInstance().registerEvent(this.zzjp, StatsUtils.getEventKey(this.zzadv, v5), 8, this.zzadz, v5, this.zzaeb, this.zzady, this.zzdo());
                --this.zzaee;
            }

        label_40:
            __monitor_exit(this);
        }
        catch(Throwable v11) {
            try {
            label_44:
                __monitor_exit(this);
            }
            catch(Throwable v11) {
                goto label_44;
            }

            throw v11;
        }

        this.zzn(arg12);
    }

    private final List zzdo() {
        List v0 = WorkSourceUtil.getNames(this.zzadw);
        if(this.zzadx == null) {
            return v0;
        }

        ArrayList v1 = new ArrayList(((Collection)v0));
        ((List)v1).add(this.zzadx);
        return ((List)v1);
    }

    private final void zzn(int arg4) {
        if(this.zzadv.isHeld()) {
            try {
                if(Build$VERSION.SDK_INT >= 21 && arg4 > 0) {
                    this.zzadv.release(arg4);
                    return;
                }

                this.zzadv.release();
                return;
            }
            catch(RuntimeException v4) {
                if(v4.getClass().equals(RuntimeException.class)) {
                    Log.e("WakeLock", String.valueOf(this.zzadz).concat(" was already released!"), ((Throwable)v4));
                    return;
                }

                throw v4;
            }
        }
    }

    private final String zzn(String arg2) {
        if(this.zzaec) {
            if(!TextUtils.isEmpty(((CharSequence)arg2))) {
                return arg2;
            }

            return this.zzaea;
        }

        return this.zzaea;
    }
}

