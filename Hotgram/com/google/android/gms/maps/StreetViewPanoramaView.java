package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreetViewPanoramaView extends FrameLayout {
    @VisibleForTesting final class zza implements StreetViewLifecycleDelegate {
        private final ViewGroup parent;
        private final IStreetViewPanoramaViewDelegate zzcd;
        private View zzce;

        public zza(ViewGroup arg1, IStreetViewPanoramaViewDelegate arg2) {
            super();
            this.zzcd = Preconditions.checkNotNull(arg2);
            this.parent = Preconditions.checkNotNull(arg1);
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg3) {
            try {
                this.zzcd.getStreetViewPanoramaAsync(new zzaj(this, arg3));
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onCreate(Bundle arg3) {
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg3, v0);
                this.zzcd.onCreate(v0);
                zzby.zza(v0, arg3);
                this.zzce = ObjectWrapper.unwrap(this.zzcd.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzce);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final View onCreateView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3) {
            throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onDestroy() {
            try {
                this.zzcd.onDestroy();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onInflate(Activity arg1, Bundle arg2, Bundle arg3) {
            throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
        }

        public final void onLowMemory() {
            try {
                this.zzcd.onLowMemory();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onPause() {
            try {
                this.zzcd.onPause();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onResume() {
            try {
                this.zzcd.onResume();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onSaveInstanceState(Bundle arg3) {
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg3, v0);
                this.zzcd.onSaveInstanceState(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onStart() {
            try {
                this.zzcd.onStart();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onStop() {
            try {
                this.zzcd.onStop();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }
    }

    @VisibleForTesting final class zzb extends DeferredLifecycleHelper {
        private OnDelegateCreatedListener zzbc;
        private final ViewGroup zzbi;
        private final Context zzbj;
        private final List zzbv;
        private final StreetViewPanoramaOptions zzcf;

        @VisibleForTesting zzb(ViewGroup arg2, Context arg3, StreetViewPanoramaOptions arg4) {
            super();
            this.zzbv = new ArrayList();
            this.zzbi = arg2;
            this.zzbj = arg3;
            this.zzcf = arg4;
        }

        protected final void createDelegate(OnDelegateCreatedListener arg4) {
            this.zzbc = arg4;
            if(this.zzbc != null && this.getDelegate() == null) {
                try {
                    MapsInitializer.initialize(this.zzbj);
                    this.zzbc.onDelegateCreated(new zza(this.zzbi, zzbz.zza(this.zzbj).zza(ObjectWrapper.wrap(this.zzbj), this.zzcf)));
                    Iterator v4_1 = this.zzbv.iterator();
                    while(v4_1.hasNext()) {
                        this.getDelegate().getStreetViewPanoramaAsync(v4_1.next());
                    }

                    this.zzbv.clear();
                    return;
                }
                catch(GooglePlayServicesNotAvailableException ) {
                    return;
                }
                catch(RemoteException v4) {
                    throw new RuntimeRemoteException(v4);
                }
            }
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg2) {
            if(this.getDelegate() != null) {
                this.getDelegate().getStreetViewPanoramaAsync(arg2);
                return;
            }

            this.zzbv.add(arg2);
        }
    }

    private final zzb zzcc;

    public StreetViewPanoramaView(Context arg3) {
        super(arg3);
        this.zzcc = new zzb(((ViewGroup)this), arg3, null);
    }

    public StreetViewPanoramaView(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.zzcc = new zzb(((ViewGroup)this), arg2, null);
    }

    public StreetViewPanoramaView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.zzcc = new zzb(((ViewGroup)this), arg1, null);
    }

    public StreetViewPanoramaView(Context arg2, StreetViewPanoramaOptions arg3) {
        super(arg2);
        this.zzcc = new zzb(((ViewGroup)this), arg2, arg3);
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg2) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzcc.getStreetViewPanoramaAsync(arg2);
    }

    public final void onCreate(Bundle arg3) {
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitAll().build());
        try {
            this.zzcc.onCreate(arg3);
            if(this.zzcc.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(((FrameLayout)this));
            }
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
    }

    public void onDestroy() {
        this.zzcc.onDestroy();
    }

    public final void onLowMemory() {
        this.zzcc.onLowMemory();
    }

    public final void onPause() {
        this.zzcc.onPause();
    }

    public void onResume() {
        this.zzcc.onResume();
    }

    public final void onSaveInstanceState(Bundle arg2) {
        this.zzcc.onSaveInstanceState(arg2);
    }

    public void onStart() {
        this.zzcc.onStart();
    }

    public void onStop() {
        this.zzcc.onStop();
    }
}

