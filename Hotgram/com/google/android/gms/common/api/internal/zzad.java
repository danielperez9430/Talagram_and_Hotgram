package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.f.b;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

public class zzad extends zzk {
    private GoogleApiManager zzcq;
    private final b zzhb;

    private zzad(LifecycleFragment arg2) {
        super(arg2);
        this.zzhb = new b();
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", ((LifecycleCallback)this));
    }

    public final void onResume() {
        super.onResume();
        this.zzan();
    }

    public final void onStart() {
        super.onStart();
        this.zzan();
    }

    public void onStop() {
        super.onStop();
        this.zzcq.zzb(this);
    }

    public static void zza(Activity arg2, GoogleApiManager arg3, zzh arg4) {
        LifecycleFragment v2 = zzad.getFragment(arg2);
        LifecycleCallback v0 = v2.getCallbackOrNull("ConnectionlessLifecycleHelper", zzad.class);
        if(v0 == null) {
            zzad v0_1 = new zzad(v2);
        }

        ((zzad)v0).zzcq = arg3;
        Preconditions.checkNotNull(arg4, "ApiKey cannot be null");
        ((zzad)v0).zzhb.add(arg4);
        arg3.zza(((zzad)v0));
    }

    protected final void zza(ConnectionResult arg2, int arg3) {
        this.zzcq.zza(arg2, arg3);
    }

    final b zzam() {
        return this.zzhb;
    }

    private final void zzan() {
        if(!this.zzhb.isEmpty()) {
            this.zzcq.zza(this);
        }
    }

    protected final void zzr() {
        this.zzcq.zzr();
    }
}

