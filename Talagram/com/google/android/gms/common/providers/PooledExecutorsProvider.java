package com.google.android.gms.common.providers;

import android.util.Log;
import java.util.concurrent.ScheduledExecutorService;

public class PooledExecutorsProvider {
    public interface PooledExecutorFactory {
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private static PooledExecutorFactory zzvs;

    private PooledExecutorsProvider() {
        super();
    }

    public static PooledExecutorFactory createDefaultFactory() {
        return new zza();
    }

    public static PooledExecutorFactory getInstance() {
        PooledExecutorFactory v1_1;
        Class v0 = PooledExecutorsProvider.class;
        __monitor_enter(v0);
        try {
            if(PooledExecutorsProvider.zzvs == null) {
                PooledExecutorsProvider.zzvs = PooledExecutorsProvider.createDefaultFactory();
            }

            v1_1 = PooledExecutorsProvider.zzvs;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static void setInstance(PooledExecutorFactory arg3) {
        Class v0 = PooledExecutorsProvider.class;
        __monitor_enter(v0);
        try {
            if(PooledExecutorsProvider.zzvs != null) {
                Log.e("PooledExecutorsProvider", "setInstance called when instance was already set.");
            }

            PooledExecutorsProvider.zzvs = arg3;
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }
}

