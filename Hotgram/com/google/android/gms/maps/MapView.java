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
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapView extends FrameLayout {
    @VisibleForTesting final class zza implements MapLifecycleDelegate {
        private final ViewGroup parent;
        private final IMapViewDelegate zzbg;
        private View zzbh;

        public zza(ViewGroup arg1, IMapViewDelegate arg2) {
            super();
            this.zzbg = Preconditions.checkNotNull(arg2);
            this.parent = Preconditions.checkNotNull(arg1);
        }

        public final void getMapAsync(OnMapReadyCallback arg3) {
            try {
                this.zzbg.getMapAsync(new zzac(this, arg3));
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
                this.zzbg.onCreate(v0);
                zzby.zza(v0, arg3);
                this.zzbh = ObjectWrapper.unwrap(this.zzbg.getView());
                this.parent.removeAllViews();
                this.parent.addView(this.zzbh);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final View onCreateView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public final void onDestroy() {
            try {
                this.zzbg.onDestroy();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public final void onEnterAmbient(Bundle arg3) {
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg3, v0);
                this.zzbg.onEnterAmbient(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzbg.onExitAmbient();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onInflate(Activity arg1, Bundle arg2, Bundle arg3) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public final void onLowMemory() {
            try {
                this.zzbg.onLowMemory();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onPause() {
            try {
                this.zzbg.onPause();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onResume() {
            try {
                this.zzbg.onResume();
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
                this.zzbg.onSaveInstanceState(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onStart() {
            try {
                this.zzbg.onStart();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onStop() {
            try {
                this.zzbg.onStop();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }
    }

    @VisibleForTesting final class zzb extends DeferredLifecycleHelper {
        private OnDelegateCreatedListener zzbc;
        private final List zzbe;
        private final ViewGroup zzbi;
        private final Context zzbj;
        private final GoogleMapOptions zzbk;

        @VisibleForTesting zzb(ViewGroup arg2, Context arg3, GoogleMapOptions arg4) {
            super();
            this.zzbe = new ArrayList();
            this.zzbi = arg2;
            this.zzbj = arg3;
            this.zzbk = arg4;
        }

        protected final void createDelegate(OnDelegateCreatedListener arg4) {
            this.zzbc = arg4;
            if(this.zzbc != null && this.getDelegate() == null) {
                try {
                    MapsInitializer.initialize(this.zzbj);
                    IMapViewDelegate v4_1 = zzbz.zza(this.zzbj).zza(ObjectWrapper.wrap(this.zzbj), this.zzbk);
                    if(v4_1 == null) {
                        return;
                    }
                    else {
                        this.zzbc.onDelegateCreated(new zza(this.zzbi, v4_1));
                        Iterator v4_2 = this.zzbe.iterator();
                        while(v4_2.hasNext()) {
                            this.getDelegate().getMapAsync(v4_2.next());
                        }

                        this.zzbe.clear();
                        return;
                    }

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

        public final void getMapAsync(OnMapReadyCallback arg2) {
            if(this.getDelegate() != null) {
                this.getDelegate().getMapAsync(arg2);
                return;
            }

            this.zzbe.add(arg2);
        }
    }

    private final zzb zzbf;

    public MapView(Context arg3) {
        super(arg3);
        this.zzbf = new zzb(((ViewGroup)this), arg3, null);
        this.setClickable(true);
    }

    public MapView(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.zzbf = new zzb(((ViewGroup)this), arg2, GoogleMapOptions.createFromAttributes(arg2, arg3));
        this.setClickable(true);
    }

    public MapView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.zzbf = new zzb(((ViewGroup)this), arg1, GoogleMapOptions.createFromAttributes(arg1, arg2));
        this.setClickable(true);
    }

    public MapView(Context arg2, GoogleMapOptions arg3) {
        super(arg2);
        this.zzbf = new zzb(((ViewGroup)this), arg2, arg3);
        this.setClickable(true);
    }

    public void getMapAsync(OnMapReadyCallback arg2) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        this.zzbf.getMapAsync(arg2);
    }

    public final void onCreate(Bundle arg3) {
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitAll().build());
        try {
            this.zzbf.onCreate(arg3);
            if(this.zzbf.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(((FrameLayout)this));
            }
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
    }

    public final void onDestroy() {
        this.zzbf.onDestroy();
    }

    public final void onEnterAmbient(Bundle arg3) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        zzb v0 = this.zzbf;
        if(v0.getDelegate() != null) {
            v0.getDelegate().onEnterAmbient(arg3);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzb v0 = this.zzbf;
        if(v0.getDelegate() != null) {
            v0.getDelegate().onExitAmbient();
        }
    }

    public final void onLowMemory() {
        this.zzbf.onLowMemory();
    }

    public final void onPause() {
        this.zzbf.onPause();
    }

    public final void onResume() {
        this.zzbf.onResume();
    }

    public final void onSaveInstanceState(Bundle arg2) {
        this.zzbf.onSaveInstanceState(arg2);
    }

    public final void onStart() {
        this.zzbf.onStart();
    }

    public final void onStop() {
        this.zzbf.onStop();
    }
}

