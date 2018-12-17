package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BlockingServiceConnection implements ServiceConnection {
    private boolean zzaj;
    private final BlockingQueue zzak;

    public BlockingServiceConnection() {
        super();
        this.zzaj = false;
        this.zzak = new LinkedBlockingQueue();
    }

    public IBinder getService() {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if(!this.zzaj) {
            this.zzaj = true;
            return this.zzak.take();
        }

        throw new IllegalStateException("Cannot call get on this connection more than once");
    }

    public IBinder getServiceWithTimeout(long arg2, TimeUnit arg4) {
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if(!this.zzaj) {
            this.zzaj = true;
            Object v2 = this.zzak.poll(arg2, arg4);
            if(v2 != null) {
                return ((IBinder)v2);
            }

            throw new TimeoutException("Timed out waiting for the service connection");
        }

        throw new IllegalStateException("Cannot call get on this connection more than once");
    }

    public void onServiceConnected(ComponentName arg1, IBinder arg2) {
        this.zzak.add(arg2);
    }

    public void onServiceDisconnected(ComponentName arg1) {
    }
}

