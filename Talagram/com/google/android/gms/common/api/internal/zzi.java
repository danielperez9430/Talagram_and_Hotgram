package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzk {
    final class zza implements OnConnectionFailedListener {
        public final int zzee;
        public final GoogleApiClient zzef;
        public final OnConnectionFailedListener zzeg;

        public zza(zzi arg1, int arg2, GoogleApiClient arg3, OnConnectionFailedListener arg4) {
            this.zzeh = arg1;
            super();
            this.zzee = arg2;
            this.zzef = arg3;
            this.zzeg = arg4;
            arg3.registerConnectionFailedListener(((OnConnectionFailedListener)this));
        }

        public final void onConnectionFailed(ConnectionResult arg5) {
            String v1 = String.valueOf(arg5);
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 27);
            v3.append("beginFailureResolution for ");
            v3.append(v1);
            Log.d("AutoManageHelper", v3.toString());
            this.zzeh.zzb(arg5, this.zzee);
        }
    }

    private final SparseArray zzed;

    private zzi(LifecycleFragment arg2) {
        super(arg2);
        this.zzed = new SparseArray();
        this.mLifecycleFragment.addCallback("AutoManageHelper", ((LifecycleCallback)this));
    }

    public final void dump(String arg5, FileDescriptor arg6, PrintWriter arg7, String[] arg8) {
        int v0;
        for(v0 = 0; v0 < this.zzed.size(); ++v0) {
            zza v1 = this.zzd(v0);
            if(v1 != null) {
                arg7.append(((CharSequence)arg5)).append("GoogleApiClient #").print(v1.zzee);
                arg7.println(":");
                v1.zzef.dump(String.valueOf(arg5).concat("  "), arg6, arg7, arg8);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        boolean v1 = this.mStarted;
        String v2 = String.valueOf(this.zzed);
        StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 14);
        v4.append("onStart ");
        v4.append(v1);
        v4.append(" ");
        v4.append(v2);
        Log.d("AutoManageHelper", v4.toString());
        if(this.zzer.get() == null) {
            int v0;
            for(v0 = 0; v0 < this.zzed.size(); ++v0) {
                zza v1_1 = this.zzd(v0);
                if(v1_1 != null) {
                    v1_1.zzef.connect();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        int v0;
        for(v0 = 0; v0 < this.zzed.size(); ++v0) {
            zza v1 = this.zzd(v0);
            if(v1 != null) {
                v1.zzef.disconnect();
            }
        }
    }

    public final void zza(int arg7, GoogleApiClient arg8, OnConnectionFailedListener arg9) {
        Preconditions.checkNotNull(arg8, "GoogleApiClient instance cannot be null");
        boolean v0 = this.zzed.indexOfKey(arg7) < 0 ? true : false;
        StringBuilder v2 = new StringBuilder(54);
        v2.append("Already managing a GoogleApiClient with id ");
        v2.append(arg7);
        Preconditions.checkState(v0, v2.toString());
        Object v0_1 = this.zzer.get();
        boolean v2_1 = this.mStarted;
        String v3 = String.valueOf(v0_1);
        StringBuilder v5 = new StringBuilder(String.valueOf(v3).length() + 49);
        v5.append("starting AutoManage for client ");
        v5.append(arg7);
        v5.append(" ");
        v5.append(v2_1);
        v5.append(" ");
        v5.append(v3);
        Log.d("AutoManageHelper", v5.toString());
        this.zzed.put(arg7, new zza(this, arg7, arg8, arg9));
        if((this.mStarted) && v0_1 == null) {
            String v9 = String.valueOf(arg8);
            StringBuilder v1 = new StringBuilder(String.valueOf(v9).length() + 11);
            v1.append("connecting ");
            v1.append(v9);
            Log.d("AutoManageHelper", v1.toString());
            arg8.connect();
        }
    }

    public static zzi zza(LifecycleActivity arg2) {
        LifecycleFragment v2 = zzi.getFragment(arg2);
        LifecycleCallback v0 = v2.getCallbackOrNull("AutoManageHelper", zzi.class);
        if(v0 != null) {
            return ((zzi)v0);
        }

        return new zzi(v2);
    }

    protected final void zza(ConnectionResult arg3, int arg4) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if(arg4 < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }

        Object v0 = this.zzed.get(arg4);
        if(v0 != null) {
            this.zzc(arg4);
            OnConnectionFailedListener v4 = ((zza)v0).zzeg;
            if(v4 != null) {
                v4.onConnectionFailed(arg3);
            }
        }
    }

    public final void zzc(int arg3) {
        Object v0 = this.zzed.get(arg3);
        this.zzed.remove(arg3);
        if(v0 != null) {
            ((zza)v0).zzef.unregisterConnectionFailedListener(((OnConnectionFailedListener)v0));
            ((zza)v0).zzef.disconnect();
        }
    }

    private final zza zzd(int arg3) {
        if(this.zzed.size() <= arg3) {
            return null;
        }

        return this.zzed.get(this.zzed.keyAt(arg3));
    }

    protected final void zzr() {
        int v0;
        for(v0 = 0; v0 < this.zzed.size(); ++v0) {
            zza v1 = this.zzd(v0);
            if(v1 != null) {
                v1.zzef.connect();
            }
        }
    }
}

