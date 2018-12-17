package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk public final class BackgroundDetector implements Application$ActivityLifecycleCallbacks, ComponentCallbacks2 {
    @KeepForSdk public interface BackgroundStateChangeListener {
        @KeepForSdk void onBackgroundStateChanged(boolean arg1);
    }

    private static final BackgroundDetector zzem;
    private final AtomicBoolean zzen;
    private final AtomicBoolean zzeo;
    @GuardedBy(value="sInstance") private final ArrayList zzep;
    @GuardedBy(value="sInstance") private boolean zzeq;

    static {
        BackgroundDetector.zzem = new BackgroundDetector();
    }

    @KeepForSdk private BackgroundDetector() {
        super();
        this.zzen = new AtomicBoolean();
        this.zzeo = new AtomicBoolean();
        this.zzep = new ArrayList();
        this.zzeq = false;
    }

    @KeepForSdk public final void addListener(BackgroundStateChangeListener arg3) {
        BackgroundDetector v0 = BackgroundDetector.zzem;
        __monitor_enter(v0);
        try {
            this.zzep.add(arg3);
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_7;
        }

        throw v3;
    }

    @KeepForSdk public static BackgroundDetector getInstance() {
        return BackgroundDetector.zzem;
    }

    @KeepForSdk public static void initialize(Application arg2) {
        BackgroundDetector v0 = BackgroundDetector.zzem;
        __monitor_enter(v0);
        try {
            if(!BackgroundDetector.zzem.zzeq) {
                arg2.registerActivityLifecycleCallbacks(BackgroundDetector.zzem);
                arg2.registerComponentCallbacks(BackgroundDetector.zzem);
                BackgroundDetector.zzem.zzeq = true;
            }

            __monitor_exit(v0);
            return;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_15;
        }

        throw v2;
    }

    @KeepForSdk public final boolean isInBackground() {
        return this.zzen.get();
    }

    public final void onActivityCreated(Activity arg3, Bundle arg4) {
        boolean v3 = this.zzen.compareAndSet(true, false);
        this.zzeo.set(true);
        if(v3) {
            this.onBackgroundStateChanged(false);
        }
    }

    public final void onActivityDestroyed(Activity arg1) {
    }

    public final void onActivityPaused(Activity arg1) {
    }

    public final void onActivityResumed(Activity arg4) {
        boolean v4 = this.zzen.compareAndSet(true, false);
        this.zzeo.set(true);
        if(v4) {
            this.onBackgroundStateChanged(false);
        }
    }

    public final void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
    }

    public final void onActivityStarted(Activity arg1) {
    }

    public final void onActivityStopped(Activity arg1) {
    }

    private final void onBackgroundStateChanged(boolean arg6) {
        BackgroundDetector v0 = BackgroundDetector.zzem;
        __monitor_enter(v0);
        try {
            ArrayList v1 = this.zzep;
            int v2 = v1.size();
            int v3 = 0;
            while(v3 < v2) {
                Object v4 = v1.get(v3);
                ++v3;
                ((BackgroundStateChangeListener)v4).onBackgroundStateChanged(arg6);
            }

            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_13;
        }

        throw v6;
    }

    public final void onConfigurationChanged(Configuration arg1) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int arg3) {
        if(arg3 == 20 && (this.zzen.compareAndSet(false, true))) {
            this.zzeo.set(true);
            this.onBackgroundStateChanged(true);
        }
    }

    @TargetApi(value=16) @KeepForSdk public final boolean readCurrentStateIfPossible(boolean arg3) {
        if(!this.zzeo.get()) {
            if(PlatformVersion.isAtLeastJellyBean()) {
                ActivityManager$RunningAppProcessInfo v3 = new ActivityManager$RunningAppProcessInfo();
                ActivityManager.getMyMemoryState(v3);
                if(!this.zzeo.getAndSet(true) && v3.importance > 100) {
                    this.zzen.set(true);
                }
            }
            else {
                return arg3;
            }
        }

        return this.isInBackground();
    }
}

