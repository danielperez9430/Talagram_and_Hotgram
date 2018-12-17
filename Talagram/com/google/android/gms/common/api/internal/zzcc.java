package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.h;
import android.support.v4.f.a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzcc extends Fragment implements LifecycleFragment {
    private static WeakHashMap zzla;
    private Map zzlb;
    private int zzlc;
    private Bundle zzld;

    static {
        zzcc.zzla = new WeakHashMap();
    }

    public zzcc() {
        super();
        this.zzlb = new a();
        this.zzlc = 0;
    }

    public final void addCallback(String arg3, LifecycleCallback arg4) {
        if(!this.zzlb.containsKey(arg3)) {
            this.zzlb.put(arg3, arg4);
            if(this.zzlc > 0) {
                new Handler(Looper.getMainLooper()).post(new zzcd(this, arg4, arg3));
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

    public static zzcc zza(h arg3) {
        Fragment v0_1;
        Object v0 = zzcc.zzla.get(arg3);
        if(v0 != null) {
            v0 = ((WeakReference)v0).get();
            if(v0 != null) {
                return ((zzcc)v0);
            }
        }

        try {
            v0_1 = arg3.d().a("SupportLifecycleFragmentImpl");
            if(v0_1 == null) {
                goto label_12;
            }
        }
        catch(ClassCastException v3) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", ((Throwable)v3));
        }

        if(((zzcc)v0_1).isRemoving()) {
        label_12:
            zzcc v0_2 = new zzcc();
            arg3.d().a().a(((Fragment)v0_2), "SupportLifecycleFragmentImpl").d();
        }

        zzcc.zzla.put(arg3, new WeakReference(v0_1));
        return ((zzcc)v0_1);
    }

    static int zza(zzcc arg0) {
        return arg0.zzlc;
    }

    static Bundle zzb(zzcc arg0) {
        return arg0.zzld;
    }
}

