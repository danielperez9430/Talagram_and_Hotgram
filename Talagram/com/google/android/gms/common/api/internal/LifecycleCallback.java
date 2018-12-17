package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk public class LifecycleCallback {
    @KeepForSdk protected final LifecycleFragment mLifecycleFragment;

    @KeepForSdk protected LifecycleCallback(LifecycleFragment arg1) {
        super();
        this.mLifecycleFragment = arg1;
    }

    public void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
    }

    public final Activity getActivity() {
        return this.mLifecycleFragment.getLifecycleActivity();
    }

    @Keep private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity arg1) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    @KeepForSdk public static LifecycleFragment getFragment(Activity arg1) {
        return LifecycleCallback.getFragment(new LifecycleActivity(arg1));
    }

    @KeepForSdk protected static LifecycleFragment getFragment(LifecycleActivity arg1) {
        if(arg1.zzbv()) {
            return zzcc.zza(arg1.zzby());
        }

        if(arg1.zzbw()) {
            return zzbr.zzc(arg1.zzbx());
        }

        throw new IllegalArgumentException("Can\'t get fragment for unexpected activity.");
    }

    public void onActivityResult(int arg1, int arg2, Intent arg3) {
    }

    public void onCreate(Bundle arg1) {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle arg1) {
    }

    public void onStart() {
    }

    @KeepForSdk public void onStop() {
    }
}

