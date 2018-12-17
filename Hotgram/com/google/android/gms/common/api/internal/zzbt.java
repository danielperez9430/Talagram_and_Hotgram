package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzbt extends zzk {
    private TaskCompletionSource zzln;

    private zzbt(LifecycleFragment arg2) {
        super(arg2);
        this.zzln = new TaskCompletionSource();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", ((LifecycleCallback)this));
    }

    public final Task getTask() {
        return this.zzln.getTask();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzln.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    protected final void zza(ConnectionResult arg1, int arg2) {
        this.zzln.setException(ApiExceptionUtil.fromConnectionResult(arg1));
    }

    public static zzbt zzd(Activity arg2) {
        LifecycleFragment v2 = zzbt.getFragment(arg2);
        LifecycleCallback v0 = v2.getCallbackOrNull("GmsAvailabilityHelper", zzbt.class);
        if(v0 != null) {
            if(((zzbt)v0).zzln.getTask().isComplete()) {
                ((zzbt)v0).zzln = new TaskCompletionSource();
            }

            return ((zzbt)v0);
        }

        return new zzbt(v2);
    }

    protected final void zzr() {
        int v0 = this.zzdg.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        Object v1 = null;
        if(v0 == 0) {
            this.zzln.setResult(v1);
            return;
        }

        if(!this.zzln.getTask().isComplete()) {
            ((zzk)this).zzb(new ConnectionResult(v0, ((PendingIntent)v1)), 0);
        }
    }
}

