package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.f.a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzbr extends Fragment implements LifecycleFragment {
    private static WeakHashMap zzla;
    private Map zzlb;
    private int zzlc;
    private Bundle zzld;

    static {
        zzbr.zzla = new WeakHashMap();
    }

    public zzbr() {
        super();
        this.zzlb = new a();
        this.zzlc = 0;
    }

    public final void addCallback(String arg3, LifecycleCallback arg4) {
        if(!this.zzlb.containsKey(arg3)) {
            this.zzlb.put(arg3, arg4);
            if(this.zzlc > 0) {
                new Handler(Looper.getMainLooper()).post(new zzbs(this, arg4, arg3));
            }

            return;
        }

        StringBuilder v1 = new StringBuilder(String.valueOf(arg3).length() + 59);
        v1.append("LifecycleCallback with tag ");
        v1.append(arg3);
        v1.append(" already added to this fragment.");
        throw new IllegalArgumentException(v1.toString());
    }

    public final void dump(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        super.dump(arg3, arg4, arg5, arg6);
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().dump(arg3, arg4, arg5, arg6);
        }
    }

    public final LifecycleCallback getCallbackOrNull(String arg2, Class arg3) {
        return arg3.cast(this.zzlb.get(arg2));
    }

    public final Activity getLifecycleActivity() {
        return this.getActivity();
    }

    public final boolean isCreated() {
        if(this.zzlc > 0) {
            return 1;
        }

        return 0;
    }

    public final boolean isStarted() {
        if(this.zzlc >= 2) {
            return 1;
        }

        return 0;
    }

    public final void onActivityResult(int arg3, int arg4, Intent arg5) {
        super.onActivityResult(arg3, arg4, arg5);
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().onActivityResult(arg3, arg4, arg5);
        }
    }

    public final void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        this.zzlc = 1;
        this.zzld = arg4;
        Iterator v0 = this.zzlb.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getValue();
            Bundle v1_1 = arg4 != null ? arg4.getBundle(((Map$Entry)v1).getKey()) : null;
            ((LifecycleCallback)v2).onCreate(v1_1);
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzlc = 5;
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().onDestroy();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzlc = 3;
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().onResume();
        }
    }

    public final void onSaveInstanceState(Bundle arg5) {
        super.onSaveInstanceState(arg5);
        if(arg5 == null) {
            return;
        }

        Iterator v0 = this.zzlb.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Bundle v2 = new Bundle();
            ((Map$Entry)v1).getValue().onSaveInstanceState(v2);
            arg5.putBundle(((Map$Entry)v1).getKey(), v2);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzlc = 2;
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().onStart();
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzlc = 4;
        Iterator v0 = this.zzlb.values().iterator();
        while(v0.hasNext()) {
            v0.next().onStop();
        }
    }

    static int zza(zzbr arg0) {
        return arg0.zzlc;
    }

    static Bundle zzb(zzbr arg0) {
        return arg0.zzld;
    }

    public static zzbr zzc(Activity arg3) {
        zzbr v0_2;
        Object v0 = zzbr.zzla.get(arg3);
        if(v0 != null) {
            v0 = ((WeakReference)v0).get();
            if(v0 != null) {
                return ((zzbr)v0);
            }
        }

        try {
            Fragment v0_1 = arg3.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if(v0_1 == null) {
                goto label_12;
            }
        }
        catch(ClassCastException v3) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", ((Throwable)v3));
        }

        if(((zzbr)v0_1).isRemoving()) {
        label_12:
            v0_2 = new zzbr();
            arg3.getFragmentManager().beginTransaction().add(((Fragment)v0_2), "LifecycleFragmentImpl").commitAllowingStateLoss();
        }

        zzbr.zzla.put(arg3, new WeakReference(v0_2));
        return v0_2;
    }
}

