package com.google.android.gms.flags;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

public class FlagValueProvider {
    private boolean zzacf;
    private IFlagProvider zzacg;

    public FlagValueProvider() {
        super();
        this.zzacf = false;
        this.zzacg = null;
    }

    public Object getFlagValue(Flag arg2) {
        __monitor_enter(this);
        try {
            if(!this.zzacf) {
                __monitor_exit(this);
                return arg2.getDefault();
            }

            __monitor_exit(this);
        }
        catch(Throwable v2) {
            try {
            label_11:
                __monitor_exit(this);
            }
            catch(Throwable v2) {
                goto label_11;
            }

            throw v2;
        }

        return arg2.get(this.zzacg);
    }

    public void initialize(Context arg3) {
        __monitor_enter(this);
        try {
            if(this.zzacf) {
                __monitor_exit(this);
                return;
            }

            try {
                this.zzacg = Stub.asInterface(DynamiteModule.load(arg3, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION_NO_FORCE_STAGING, "com.google.android.gms.flags").instantiate("com.google.android.gms.flags.impl.FlagProviderImpl"));
                this.zzacg.init(ObjectWrapper.wrap(arg3));
                this.zzacf = true;
                goto label_22;
            }
            catch(RemoteException v3_1) {
                try {
                    Log.w("FlagValueProvider", "Failed to initialize flags module.", ((Throwable)v3_1));
                label_22:
                    __monitor_exit(this);
                    return;
                label_25:
                    __monitor_exit(this);
                }
                catch(Throwable v3) {
                    goto label_25;
                }
            }
        }
        catch(Throwable v3) {
            goto label_25;
        }

        throw v3;
    }
}

