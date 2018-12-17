package com.google.android.gms.common.api.internal;

import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzk extends LifecycleCallback implements DialogInterface$OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zzdg;
    protected final AtomicReference zzer;
    private final Handler zzes;

    protected zzk(LifecycleFragment arg2) {
        this(arg2, GoogleApiAvailability.getInstance());
    }

    @VisibleForTesting private zzk(LifecycleFragment arg2, GoogleApiAvailability arg3) {
        super(arg2);
        this.zzer = new AtomicReference(null);
        this.zzes = new Handler(Looper.getMainLooper());
        this.zzdg = arg3;
    }

    public final void onActivityResult(int arg4, int arg5, Intent arg6) {
        zzl v0_1;
        Object v0 = this.zzer.get();
        int v1 = 1;
        switch(arg4) {
            case 1: {
                if(arg5 == -1) {
                    goto label_38;
                }

                if(arg5 == 0) {
                    arg4 = 13;
                    if(arg6 != null) {
                        arg4 = arg6.getIntExtra("<<ResolutionFailureErrorDetail>>", arg4);
                    }

                    zzl v5 = new zzl(new ConnectionResult(arg4, null), zzk.zza(((zzl)v0)));
                    this.zzer.set(v5);
                    v0_1 = v5;
                }

            label_37:
                v1 = 0;
                break;
            }
            case 2: {
                arg4 = this.zzdg.isGooglePlayServicesAvailable(((LifecycleCallback)this).getActivity());
                if(arg4 == 0) {
                }
                else {
                    v1 = 0;
                }

                if(v0 == null) {
                    return;
                }

                int v6 = 18;
                if(((zzl)v0).getConnectionResult().getErrorCode() != v6) {
                    goto label_38;
                }

                if(arg4 != v6) {
                    goto label_38;
                }

                return;
            }
            default: {
                goto label_37;
            }
        }

    label_38:
        if(v1 != 0) {
            this.zzt();
            return;
        }

        if(v0_1 != null) {
            this.zza(v0_1.getConnectionResult(), v0_1.zzu());
        }
    }

    public void onCancel(DialogInterface arg3) {
        this.zza(new ConnectionResult(13, null), zzk.zza(this.zzer.get()));
        this.zzt();
    }

    public final void onCreate(Bundle arg5) {
        Object v2_1;
        super.onCreate(arg5);
        if(arg5 != null) {
            AtomicReference v0 = this.zzer;
            if(arg5.getBoolean("resolving_error", false)) {
                zzl v2 = new zzl(new ConnectionResult(arg5.getInt("failed_status"), arg5.getParcelable("failed_resolution")), arg5.getInt("failed_client_id", -1));
            }
            else {
                v2_1 = null;
            }

            v0.set(v2_1);
        }
    }

    public final void onSaveInstanceState(Bundle arg4) {
        super.onSaveInstanceState(arg4);
        Object v0 = this.zzer.get();
        if(v0 != null) {
            arg4.putBoolean("resolving_error", true);
            arg4.putInt("failed_client_id", ((zzl)v0).zzu());
            arg4.putInt("failed_status", ((zzl)v0).getConnectionResult().getErrorCode());
            arg4.putParcelable("failed_resolution", ((zzl)v0).getConnectionResult().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult arg1, int arg2);

    private static int zza(zzl arg0) {
        if(arg0 == null) {
            return -1;
        }

        return arg0.zzu();
    }

    public final void zzb(ConnectionResult arg2, int arg3) {
        zzl v0 = new zzl(arg2, arg3);
        if(this.zzer.compareAndSet(null, v0)) {
            this.zzes.post(new zzm(this, v0));
        }
    }

    protected abstract void zzr();

    protected final void zzt() {
        this.zzer.set(null);
        this.zzr();
    }
}

