package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zza extends ActivityLifecycleObserver {
    class com.google.android.gms.common.api.internal.zza$zza extends LifecycleCallback {
        private List zzdt;

        private com.google.android.gms.common.api.internal.zza$zza(LifecycleFragment arg2) {
            super(arg2);
            this.zzdt = new ArrayList();
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", ((LifecycleCallback)this));
        }

        public void onStop() {
            List v0_1;
            __monitor_enter(this);
            try {
                v0_1 = this.zzdt;
                this.zzdt = new ArrayList();
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                try {
                label_14:
                    __monitor_exit(this);
                }
                catch(Throwable v0) {
                    goto label_14;
                }

                throw v0;
            }

            Iterator v0_2 = v0_1.iterator();
            while(v0_2.hasNext()) {
                v0_2.next().run();
            }
        }

        static void zza(com.google.android.gms.common.api.internal.zza$zza arg0, Runnable arg1) {
            arg0.zza(arg1);
        }

        private static com.google.android.gms.common.api.internal.zza$zza zza(Activity arg3) {
            com.google.android.gms.common.api.internal.zza$zza v1_1;
            __monitor_enter(arg3);
            try {
                LifecycleFragment v0_1 = com.google.android.gms.common.api.internal.zza$zza.getFragment(arg3);
                LifecycleCallback v1 = v0_1.getCallbackOrNull("LifecycleObserverOnStop", com.google.android.gms.common.api.internal.zza$zza.class);
                if(v1 == null) {
                    v1_1 = new com.google.android.gms.common.api.internal.zza$zza(v0_1);
                }

                __monitor_exit(arg3);
                return v1_1;
            label_11:
                __monitor_exit(arg3);
            }
            catch(Throwable v0) {
                goto label_11;
            }

            throw v0;
        }

        private final void zza(Runnable arg2) {
            __monitor_enter(this);
            try {
                this.zzdt.add(arg2);
            }
            catch(Throwable v2) {
                __monitor_exit(this);
                throw v2;
            }

            __monitor_exit(this);
        }

        static com.google.android.gms.common.api.internal.zza$zza zzb(Activity arg0) {
            return com.google.android.gms.common.api.internal.zza$zza.zza(arg0);
        }
    }

    private final WeakReference zzds;

    public zza(Activity arg1) {
        this(com.google.android.gms.common.api.internal.zza$zza.zzb(arg1));
    }

    private zza(com.google.android.gms.common.api.internal.zza$zza arg2) {
        super();
        this.zzds = new WeakReference(arg2);
    }

    public final ActivityLifecycleObserver onStopCallOnce(Runnable arg2) {
        Object v0 = this.zzds.get();
        if(v0 != null) {
            com.google.android.gms.common.api.internal.zza$zza.zza(((com.google.android.gms.common.api.internal.zza$zza)v0), arg2);
            return this;
        }

        throw new IllegalStateException("The target activity has already been GC\'d");
    }
}

