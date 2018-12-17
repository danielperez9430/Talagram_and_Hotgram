package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IInterface;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api$SimpleClient;

public abstract class BaseSimpleClient implements SimpleClient {
    private final Context mContext;
    private final Object mLock;
    private int mState;
    private IInterface zzrj;

    public BaseSimpleClient(Context arg2) {
        super();
        this.mLock = new Object();
        this.zzrj = null;
        this.mState = 1;
        this.mContext = arg2;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    public IInterface getService() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.mState != 5) {
                if(this.mState == 4) {
                    boolean v1_1 = this.zzrj != null ? true : false;
                    Preconditions.checkState(v1_1, "Client is connected but service is null");
                    __monitor_exit(v0);
                    return this.zzrj;
                }

                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }

            throw new DeadObjectException();
        label_26:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_26;
        }

        throw v1;
    }

    public void setState(int arg2, IInterface arg3) {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.mState = arg2;
            this.zzrj = arg3;
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_7;
        }

        throw v2;
    }
}

