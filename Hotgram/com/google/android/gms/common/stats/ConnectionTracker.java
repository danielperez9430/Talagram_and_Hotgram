package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;

public class ConnectionTracker {
    private static final Object zztm;
    private static volatile ConnectionTracker zzyg;
    @VisibleForTesting private static boolean zzyh;
    private final List zzyi;
    private final List zzyj;
    private final List zzyk;
    private final List zzyl;

    static {
        ConnectionTracker.zztm = new Object();
        ConnectionTracker.zzyh = false;
    }

    private ConnectionTracker() {
        super();
        this.zzyi = Collections.EMPTY_LIST;
        this.zzyj = Collections.EMPTY_LIST;
        this.zzyk = Collections.EMPTY_LIST;
        this.zzyl = Collections.EMPTY_LIST;
    }

    public boolean bindService(Context arg8, Intent arg9, ServiceConnection arg10, int arg11) {
        return this.bindService(arg8, arg8.getClass().getName(), arg9, arg10, arg11);
    }

    public boolean bindService(Context arg7, String arg8, Intent arg9, ServiceConnection arg10, int arg11) {
        return ConnectionTracker.zza(arg7, arg8, arg9, arg10, arg11, true);
    }

    public boolean bindServiceAllowStoppedPackages(Context arg7, String arg8, Intent arg9, ServiceConnection arg10, int arg11) {
        return ConnectionTracker.zza(arg7, arg8, arg9, arg10, arg11, false);
    }

    public static ConnectionTracker getInstance() {
        if(ConnectionTracker.zzyg == null) {
            Object v0 = ConnectionTracker.zztm;
            __monitor_enter(v0);
            try {
                if(ConnectionTracker.zzyg == null) {
                    ConnectionTracker.zzyg = new ConnectionTracker();
                }

                __monitor_exit(v0);
                goto label_14;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

    label_14:
        return ConnectionTracker.zzyg;
    }

    public void logConnectService(Context arg1, ServiceConnection arg2, String arg3, Intent arg4) {
    }

    public void logDisconnectService(Context arg1, ServiceConnection arg2) {
    }

    public void logStartService(Service arg1, int arg2) {
    }

    public void logStopService(Service arg1, int arg2) {
    }

    @SuppressLint(value={"UntrackedBindService"}) public void unbindService(Context arg1, ServiceConnection arg2) {
        arg1.unbindService(arg2);
    }

    @SuppressLint(value={"UntrackedBindService"}) private static boolean zza(Context arg0, String arg1, Intent arg2, ServiceConnection arg3, int arg4, boolean arg5) {
        if(arg5) {
            ComponentName v1 = arg2.getComponent();
            boolean v1_1 = v1 == null ? false : ClientLibraryUtils.isPackageStopped(arg0, v1.getPackageName());
            if(!v1_1) {
                goto label_13;
            }

            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return 0;
        }

    label_13:
        return arg0.bindService(arg2, arg3, arg4);
    }
}

