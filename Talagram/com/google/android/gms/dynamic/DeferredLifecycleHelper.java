package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import java.util.LinkedList;

public abstract class DeferredLifecycleHelper {
    interface zza {
        int getState();

        void zza(LifecycleDelegate arg1);
    }

    private LifecycleDelegate zzabc;
    private Bundle zzabd;
    private LinkedList zzabe;
    private final OnDelegateCreatedListener zzabf;

    public DeferredLifecycleHelper() {
        super();
        this.zzabf = new com.google.android.gms.dynamic.zza(this);
    }

    protected abstract void createDelegate(OnDelegateCreatedListener arg1);

    public LifecycleDelegate getDelegate() {
        return this.zzabc;
    }

    protected void handleGooglePlayUnavailable(FrameLayout arg1) {
        DeferredLifecycleHelper.showGooglePlayUnavailableMessage(arg1);
    }

    public void onCreate(Bundle arg2) {
        this.zza(arg2, new zzc(this, arg2));
    }

    public View onCreateView(LayoutInflater arg9, ViewGroup arg10, Bundle arg11) {
        FrameLayout v6 = new FrameLayout(arg9.getContext());
        this.zza(arg11, new zzd(this, v6, arg9, arg10, arg11));
        if(this.zzabc == null) {
            this.handleGooglePlayUnavailable(v6);
        }

        return ((View)v6);
    }

    public void onDestroy() {
        if(this.zzabc != null) {
            this.zzabc.onDestroy();
            return;
        }

        this.zzm(1);
    }

    public void onDestroyView() {
        if(this.zzabc != null) {
            this.zzabc.onDestroyView();
            return;
        }

        this.zzm(2);
    }

    public void onInflate(Activity arg2, Bundle arg3, Bundle arg4) {
        this.zza(arg4, new zzb(this, arg2, arg3, arg4));
    }

    public void onLowMemory() {
        if(this.zzabc != null) {
            this.zzabc.onLowMemory();
        }
    }

    public void onPause() {
        if(this.zzabc != null) {
            this.zzabc.onPause();
            return;
        }

        this.zzm(5);
    }

    public void onResume() {
        this.zza(null, new zzg(this));
    }

    public void onSaveInstanceState(Bundle arg2) {
        if(this.zzabc != null) {
            this.zzabc.onSaveInstanceState(arg2);
            return;
        }

        if(this.zzabd != null) {
            arg2.putAll(this.zzabd);
        }
    }

    public void onStart() {
        this.zza(null, new zzf(this));
    }

    public void onStop() {
        if(this.zzabc != null) {
            this.zzabc.onStop();
            return;
        }

        this.zzm(4);
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout arg8) {
        GoogleApiAvailability v0 = GoogleApiAvailability.getInstance();
        Context v1 = arg8.getContext();
        int v2 = ((GoogleApiAvailabilityLight)v0).isGooglePlayServicesAvailable(v1);
        String v3 = ConnectionErrorMessages.getErrorMessage(v1, v2);
        String v4 = ConnectionErrorMessages.getErrorDialogButtonMessage(v1, v2);
        LinearLayout v5 = new LinearLayout(arg8.getContext());
        v5.setOrientation(1);
        int v7 = -2;
        v5.setLayoutParams(new FrameLayout$LayoutParams(v7, v7));
        arg8.addView(((View)v5));
        TextView v6 = new TextView(arg8.getContext());
        v6.setLayoutParams(new FrameLayout$LayoutParams(v7, v7));
        v6.setText(((CharSequence)v3));
        v5.addView(((View)v6));
        Intent v8 = ((GoogleApiAvailabilityLight)v0).getErrorResolutionIntent(v1, v2, null);
        if(v8 != null) {
            Button v0_1 = new Button(v1);
            v0_1.setId(16908313);
            v0_1.setLayoutParams(new FrameLayout$LayoutParams(v7, v7));
            v0_1.setText(((CharSequence)v4));
            v5.addView(((View)v0_1));
            v0_1.setOnClickListener(new zze(v1, v8));
        }
    }

    static Bundle zza(DeferredLifecycleHelper arg0, Bundle arg1) {
        arg0.zzabd = null;
        return null;
    }

    static LifecycleDelegate zza(DeferredLifecycleHelper arg0, LifecycleDelegate arg1) {
        arg0.zzabc = arg1;
        return arg1;
    }

    static LinkedList zza(DeferredLifecycleHelper arg0) {
        return arg0.zzabe;
    }

    private final void zza(Bundle arg2, zza arg3) {
        if(this.zzabc != null) {
            arg3.zza(this.zzabc);
            return;
        }

        if(this.zzabe == null) {
            this.zzabe = new LinkedList();
        }

        this.zzabe.add(arg3);
        if(arg2 != null) {
            if(this.zzabd == null) {
                this.zzabd = arg2.clone();
            }
            else {
                this.zzabd.putAll(arg2);
            }
        }

        this.createDelegate(this.zzabf);
    }

    static LifecycleDelegate zzb(DeferredLifecycleHelper arg0) {
        return arg0.zzabc;
    }

    private final void zzm(int arg2) {
        while(!this.zzabe.isEmpty()) {
            if(this.zzabe.getLast().getState() < arg2) {
                return;
            }

            this.zzabe.removeLast();
        }
    }
}

