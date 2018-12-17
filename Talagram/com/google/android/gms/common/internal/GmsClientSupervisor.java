package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class GmsClientSupervisor {
    public final class ConnectionStatusConfig {
        private final ComponentName mComponentName;
        private final String zzto;
        private final String zztp;
        private final int zztq;

        public ConnectionStatusConfig(ComponentName arg2, int arg3) {
            super();
            this.zzto = null;
            this.zztp = null;
            this.mComponentName = Preconditions.checkNotNull(arg2);
            this.zztq = arg3;
        }

        public ConnectionStatusConfig(String arg1, int arg2) {
            super();
            this.zzto = Preconditions.checkNotEmpty(arg1);
            this.zztp = "com.google.android.gms";
            this.mComponentName = null;
            this.zztq = arg2;
        }

        public ConnectionStatusConfig(String arg1, String arg2, int arg3) {
            super();
            this.zzto = Preconditions.checkNotEmpty(arg1);
            this.zztp = Preconditions.checkNotEmpty(arg2);
            this.mComponentName = null;
            this.zztq = arg3;
        }

        public final boolean equals(Object arg5) {
            if(this == (((ConnectionStatusConfig)arg5))) {
                return 1;
            }

            if(!(arg5 instanceof ConnectionStatusConfig)) {
                return 0;
            }

            if((Objects.equal(this.zzto, ((ConnectionStatusConfig)arg5).zzto)) && (Objects.equal(this.zztp, ((ConnectionStatusConfig)arg5).zztp)) && (Objects.equal(this.mComponentName, ((ConnectionStatusConfig)arg5).mComponentName)) && this.zztq == ((ConnectionStatusConfig)arg5).zztq) {
                return 1;
            }

            return 0;
        }

        public final String getAction() {
            return this.zzto;
        }

        public final int getBindFlags() {
            return this.zztq;
        }

        public final ComponentName getComponentName() {
            return this.mComponentName;
        }

        public final String getPackage() {
            return this.zztp;
        }

        public final Intent getStartServiceIntent(Context arg2) {
            Intent v2 = this.zzto != null ? new Intent(this.zzto).setPackage(this.zztp) : new Intent().setComponent(this.mComponentName);
            return v2;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.zzto, this.zztp, this.mComponentName, Integer.valueOf(this.zztq)});
        }

        public final String toString() {
            if(this.zzto == null) {
                return this.mComponentName.flattenToString();
            }

            return this.zzto;
        }
    }

    public static final int DEFAULT_BIND_FLAGS = 129;
    private static final Object zztm;
    private static GmsClientSupervisor zztn;

    static {
        GmsClientSupervisor.zztm = new Object();
    }

    public GmsClientSupervisor() {
        super();
    }

    public boolean bindService(String arg2, String arg3, int arg4, ServiceConnection arg5, String arg6) {
        return this.bindService(new ConnectionStatusConfig(arg2, arg3, arg4), arg5, arg6);
    }

    public boolean bindService(ComponentName arg3, ServiceConnection arg4, String arg5) {
        return this.bindService(new ConnectionStatusConfig(arg3, 129), arg4, arg5);
    }

    protected abstract boolean bindService(ConnectionStatusConfig arg1, ServiceConnection arg2, String arg3);

    public boolean bindService(String arg3, ServiceConnection arg4, String arg5) {
        return this.bindService(new ConnectionStatusConfig(arg3, 129), arg4, arg5);
    }

    public boolean bindService(String arg7, String arg8, ServiceConnection arg9, String arg10) {
        return this.bindService(arg7, arg8, 129, arg9, arg10);
    }

    public static GmsClientSupervisor getInstance(Context arg2) {
        Object v0 = GmsClientSupervisor.zztm;
        __monitor_enter(v0);
        try {
            if(GmsClientSupervisor.zztn == null) {
                GmsClientSupervisor.zztn = new zzh(arg2.getApplicationContext());
            }

            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            try {
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_12;
            }

            throw v2;
        }

        return GmsClientSupervisor.zztn;
    }

    @VisibleForTesting public abstract void resetForTesting();

    public void unbindService(String arg2, String arg3, int arg4, ServiceConnection arg5, String arg6) {
        this.unbindService(new ConnectionStatusConfig(arg2, arg3, arg4), arg5, arg6);
    }

    public void unbindService(ComponentName arg3, ServiceConnection arg4, String arg5) {
        this.unbindService(new ConnectionStatusConfig(arg3, 129), arg4, arg5);
    }

    protected abstract void unbindService(ConnectionStatusConfig arg1, ServiceConnection arg2, String arg3);

    public void unbindService(String arg3, ServiceConnection arg4, String arg5) {
        this.unbindService(new ConnectionStatusConfig(arg3, 129), arg4, arg5);
    }

    public void unbindService(String arg7, String arg8, ServiceConnection arg9, String arg10) {
        this.unbindService(arg7, arg8, 129, arg9, arg10);
    }
}

