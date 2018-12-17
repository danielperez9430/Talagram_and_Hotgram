package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(value=11) public class MapFragment extends Fragment {
    @VisibleForTesting final class zza implements MapLifecycleDelegate {
        private final Fragment zzaz;
        private final IMapFragmentDelegate zzba;

        public zza(Fragment arg1, IMapFragmentDelegate arg2) {
            super();
            this.zzba = Preconditions.checkNotNull(arg2);
            this.zzaz = Preconditions.checkNotNull(arg1);
        }

        public final void getMapAsync(OnMapReadyCallback arg3) {
            try {
                this.zzba.getMapAsync(new zzab(this, arg3));
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onCreate(Bundle arg5) {
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg5, v0);
                Bundle v1 = this.zzaz.getArguments();
                if(v1 != null && (v1.containsKey("MapOptions"))) {
                    zzby.zza(v0, "MapOptions", v1.getParcelable("MapOptions"));
                }

                this.zzba.onCreate(v0);
                zzby.zza(v0, arg5);
                return;
            }
            catch(RemoteException v5) {
                throw new RuntimeRemoteException(v5);
            }
        }

        public final View onCreateView(LayoutInflater arg3, ViewGroup arg4, Bundle arg5) {
            IObjectWrapper v3_1;
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg5, v0);
                v3_1 = this.zzba.onCreateView(ObjectWrapper.wrap(arg3), ObjectWrapper.wrap(arg4), v0);
                zzby.zza(v0, arg5);
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }

            return ObjectWrapper.unwrap(v3_1);
        }

        public final void onDestroy() {
            try {
                this.zzba.onDestroy();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onDestroyView() {
            try {
                this.zzba.onDestroyView();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onEnterAmbient(Bundle arg3) {
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg3, v0);
                this.zzba.onEnterAmbient(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzba.onExitAmbient();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onInflate(Activity arg3, Bundle arg4, Bundle arg5) {
            Parcelable v4 = arg4.getParcelable("MapOptions");
            try {
                Bundle v0 = new Bundle();
                zzby.zza(arg5, v0);
                this.zzba.onInflate(ObjectWrapper.wrap(arg3), ((GoogleMapOptions)v4), v0);
                zzby.zza(v0, arg5);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onLowMemory() {
            try {
                this.zzba.onLowMemory();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onPause() {
            try {
                this.zzba.onPause();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onResume() {
            try {
                this.zzba.onResume();
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
                this.zzba.onSaveInstanceState(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onStart() {
            try {
                this.zzba.onStart();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onStop() {
            try {
                this.zzba.onStop();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }
    }

    @VisibleForTesting final class zzb extends DeferredLifecycleHelper {
        private final Fragment zzaz;
        private OnDelegateCreatedListener zzbc;
        private Activity zzbd;
        private final List zzbe;

        @VisibleForTesting zzb(Fragment arg2) {
            super();
            this.zzbe = new ArrayList();
            this.zzaz = arg2;
        }

        protected final void createDelegate(OnDelegateCreatedListener arg1) {
            this.zzbc = arg1;
            this.zzc();
        }

        public final void getMapAsync(OnMapReadyCallback arg2) {
            if(this.getDelegate() != null) {
                this.getDelegate().getMapAsync(arg2);
                return;
            }

            this.zzbe.add(arg2);
        }

        private final void setActivity(Activity arg1) {
            this.zzbd = arg1;
            this.zzc();
        }

        static void zza(zzb arg0, Activity arg1) {
            arg0.setActivity(arg1);
        }

        private final void zzc() {
            if(this.zzbd != null && this.zzbc != null && this.getDelegate() == null) {
                try {
                    MapsInitializer.initialize(this.zzbd);
                    IMapFragmentDelegate v0_1 = zzbz.zza(this.zzbd).zzc(ObjectWrapper.wrap(this.zzbd));
                    if(v0_1 == null) {
                        return;
                    }
                    else {
                        this.zzbc.onDelegateCreated(new zza(this.zzaz, v0_1));
                        Iterator v0_2 = this.zzbe.iterator();
                        while(v0_2.hasNext()) {
                            this.getDelegate().getMapAsync(v0_2.next());
                        }

                        this.zzbe.clear();
                        return;
                    }

                    return;
                }
                catch(GooglePlayServicesNotAvailableException ) {
                    return;
                }
                catch(RemoteException v0) {
                    throw new RuntimeRemoteException(v0);
                }
            }
        }
    }

    private final zzb zzay;

    public MapFragment() {
        super();
        this.zzay = new zzb(((Fragment)this));
    }

    public void getMapAsync(OnMapReadyCallback arg2) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzay.getMapAsync(arg2);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions arg3) {
        MapFragment v0 = new MapFragment();
        Bundle v1 = new Bundle();
        v1.putParcelable("MapOptions", ((Parcelable)arg3));
        v0.setArguments(v1);
        return v0;
    }

    public void onActivityCreated(Bundle arg2) {
        if(arg2 != null) {
            arg2.setClassLoader(MapFragment.class.getClassLoader());
        }

        super.onActivityCreated(arg2);
    }

    public void onAttach(Activity arg2) {
        super.onAttach(arg2);
        zzb.zza(this.zzay, arg2);
    }

    public void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.zzay.onCreate(arg2);
    }

    public View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        View v2 = this.zzay.onCreateView(arg2, arg3, arg4);
        v2.setClickable(true);
        return v2;
    }

    public void onDestroy() {
        this.zzay.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzay.onDestroyView();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle arg3) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        zzb v0 = this.zzay;
        if(v0.getDelegate() != null) {
            v0.getDelegate().onEnterAmbient(arg3);
        }
    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        zzb v0 = this.zzay;
        if(v0.getDelegate() != null) {
            v0.getDelegate().onExitAmbient();
        }
    }

    @SuppressLint(value={"NewApi"}) public void onInflate(Activity arg4, AttributeSet arg5, Bundle arg6) {
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitAll().build());
        try {
            super.onInflate(arg4, arg5, arg6);
            zzb.zza(this.zzay, arg4);
            GoogleMapOptions v5 = GoogleMapOptions.createFromAttributes(((Context)arg4), arg5);
            Bundle v1 = new Bundle();
            v1.putParcelable("MapOptions", ((Parcelable)v5));
            this.zzay.onInflate(arg4, v1, arg6);
        }
        catch(Throwable v4) {
            StrictMode.setThreadPolicy(v0);
            throw v4;
        }

        StrictMode.setThreadPolicy(v0);
    }

    public void onLowMemory() {
        this.zzay.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzay.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzay.onResume();
    }

    public void onSaveInstanceState(Bundle arg2) {
        if(arg2 != null) {
            arg2.setClassLoader(MapFragment.class.getClassLoader());
        }

        super.onSaveInstanceState(arg2);
        this.zzay.onSaveInstanceState(arg2);
    }

    public void onStart() {
        super.onStart();
        this.zzay.onStart();
    }

    public void onStop() {
        this.zzay.onStop();
        super.onStop();
    }

    public void setArguments(Bundle arg1) {
        super.setArguments(arg1);
    }
}

