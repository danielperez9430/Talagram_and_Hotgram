package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule$LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import javax.annotation.concurrent.GuardedBy;

public abstract class zzl {
    private static String PREFIX = "com.google.android.gms.vision.dynamite";
    private final Object lock;
    private final String tag;
    private final String zzci;
    private final String zzcj;
    private boolean zzck;
    @GuardedBy(value="lock") private Object zzcl;
    private final Context zze;

    static {
    }

    public zzl(Context arg2, String arg3, String arg4) {
        super();
        this.lock = new Object();
        this.zzck = false;
        this.zze = arg2;
        this.tag = arg3;
        String v2 = zzl.PREFIX;
        StringBuilder v0 = new StringBuilder(String.valueOf(v2).length() + 1 + String.valueOf(arg4).length());
        v0.append(v2);
        v0.append(".");
        v0.append(arg4);
        this.zzci = v0.toString();
        this.zzcj = zzl.PREFIX;
    }

    public final boolean isOperational() {
        if(this.zzp() != null) {
            return 1;
        }

        return 0;
    }

    protected abstract Object zza(DynamiteModule arg1, Context arg2);

    protected abstract void zzm();

    public final void zzo() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.zzcl == null) {
                __monitor_exit(v0);
                return;
            }

            try {
                this.zzm();
                goto label_12;
            }
            catch(RemoteException v1_1) {
                try {
                    Log.e(this.tag, "Could not finalize native handle", ((Throwable)v1_1));
                label_12:
                    __monitor_exit(v0);
                    return;
                label_15:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_15;
                }
            }
        }
        catch(Throwable v1) {
            goto label_15;
        }

        throw v1;
    }

    protected final Object zzp() {
        DynamiteModule v2;
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.zzcl == null) {
                goto label_7;
            }

            __monitor_exit(v0);
            return this.zzcl;
        }
        catch(Throwable v1) {
            goto label_57;
        }

    label_7:
        DynamiteModule v1_1 = null;
        try {
            v2 = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzci);
            goto label_28;
        }
        catch(Throwable v1) {
        label_58:
            throw v1;
        }
        catch(RemoteException v1_2) {
        }
        catch(LoadingException ) {
            try {
                Log.d(this.tag, "Cannot load feature, fall back to load whole module.");
                try {
                    v2 = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzcj);
                    goto label_28;
                }
                catch(LoadingException v2_1) {
                    try {
                        Log.e(this.tag, "Error Loading module", ((Throwable)v2_1));
                        v2 = v1_1;
                    label_28:
                        if(v2 == null) {
                            goto label_36;
                        }

                        this.zzcl = this.zza(v2, this.zze);
                        goto label_36;
                    }
                    catch(RemoteException v1_2) {
                    label_14:
                        try {
                            Log.e(this.tag, "Error creating remote native handle", ((Throwable)v1_2));
                        label_36:
                            if(!this.zzck && this.zzcl == null) {
                                Log.w(this.tag, "Native handle not yet available. Reverting to no-op handle.");
                                this.zzck = true;
                            }
                            else if((this.zzck) && this.zzcl != null) {
                                Log.w(this.tag, "Native handle is now available.");
                            }

                            __monitor_exit(v0);
                            return this.zzcl;
                        label_57:
                            __monitor_exit(v0);
                            goto label_58;
                        }
                        catch(Throwable v1) {
                            goto label_57;
                        }
                    }
                }
            }
            catch(Throwable v1) {
                goto label_57;
            }
            catch(RemoteException v1_2) {
                goto label_14;
            }
        }
    }
}

