package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupportStreetViewPanoramaFragment extends Fragment {
    @VisibleForTesting final class zza implements StreetViewLifecycleDelegate {
        private final Fragment fragment;
        private final IStreetViewPanoramaFragmentDelegate zzbt;

        public zza(Fragment arg1, IStreetViewPanoramaFragmentDelegate arg2) {
            super();
            this.zzbt = Preconditions.checkNotNull(arg2);
            this.fragment = Preconditions.checkNotNull(arg1);
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg3) {
            try {
                this.zzbt.getStreetViewPanoramaAsync(new zzal(this, arg3));
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
                Bundle v1 = this.fragment.getArguments();
                if(v1 != null && (v1.containsKey("StreetViewPanoramaOptions"))) {
                    zzby.zza(v0, "StreetViewPanoramaOptions", v1.getParcelable("StreetViewPanoramaOptions"));
                }

                this.zzbt.onCreate(v0);
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
                v3_1 = this.zzbt.onCreateView(ObjectWrapper.wrap(arg3), ObjectWrapper.wrap(arg4), v0);
                zzby.zza(v0, arg5);
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }

            return ObjectWrapper.unwrap(v3_1);
        }

        public final void onDestroy() {
            try {
                this.zzbt.onDestroy();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onDestroyView() {
            try {
                this.zzbt.onDestroyView();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onInflate(Activity arg3, Bundle arg4, Bundle arg5) {
            try {
                arg4 = new Bundle();
                zzby.zza(arg5, arg4);
                this.zzbt.onInflate(ObjectWrapper.wrap(arg3), null, arg4);
                zzby.zza(arg4, arg5);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onLowMemory() {
            try {
                this.zzbt.onLowMemory();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onPause() {
            try {
                this.zzbt.onPause();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onResume() {
            try {
                this.zzbt.onResume();
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
                this.zzbt.onSaveInstanceState(v0);
                zzby.zza(v0, arg3);
                return;
            }
            catch(RemoteException v3) {
                throw new RuntimeRemoteException(v3);
            }
        }

        public final void onStart() {
            try {
                this.zzbt.onStart();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }

        public final void onStop() {
            try {
                this.zzbt.onStop();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeRemoteException(v0);
            }
        }
    }

    @VisibleForTesting final class zzb extends DeferredLifecycleHelper {
        private final Fragment fragment;
        private OnDelegateCreatedListener zzbc;
        private Activity zzbd;
        private final List zzbv;

        @VisibleForTesting zzb(Fragment arg2) {
            super();
            this.zzbv = new ArrayList();
            this.fragment = arg2;
        }

        protected final void createDelegate(OnDelegateCreatedListener arg1) {
            this.zzbc = arg1;
            this.zzc();
        }

        public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg2) {
            if(this.getDelegate() != null) {
                this.getDelegate().getStreetViewPanoramaAsync(arg2);
                return;
            }

            this.zzbv.add(arg2);
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
                    this.zzbc.onDelegateCreated(new zza(this.fragment, zzbz.zza(this.zzbd).zzd(ObjectWrapper.wrap(this.zzbd))));
                    Iterator v0_1 = this.zzbv.iterator();
                    while(v0_1.hasNext()) {
                        this.getDelegate().getStreetViewPanoramaAsync(v0_1.next());
                    }

                    this.zzbv.clear();
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

    private final zzb zzch;

    public SupportStreetViewPanoramaFragment() {
        super();
        this.zzch = new zzb(((Fragment)this));
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback arg2) {
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.zzch.getStreetViewPanoramaAsync(arg2);
    }

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions arg3) {
        SupportStreetViewPanoramaFragment v0 = new SupportStreetViewPanoramaFragment();
        Bundle v1 = new Bundle();
        v1.putParcelable("StreetViewPanoramaOptions", ((Parcelable)arg3));
        v0.setArguments(v1);
        return v0;
    }

    public void onActivityCreated(Bundle arg2) {
        if(arg2 != null) {
            arg2.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }

        super.onActivityCreated(arg2);
    }

    public void onAttach(Activity arg2) {
        super.onAttach(arg2);
        zzb.zza(this.zzch, arg2);
    }

    public void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.zzch.onCreate(arg2);
    }

    public View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        return this.zzch.onCreateView(arg2, arg3, arg4);
    }

    public void onDestroy() {
        this.zzch.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.zzch.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity arg3, AttributeSet arg4, Bundle arg5) {
        StrictMode$ThreadPolicy v0 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(v0).permitAll().build());
        try {
            super.onInflate(arg3, arg4, arg5);
            zzb.zza(this.zzch, arg3);
            this.zzch.onInflate(arg3, new Bundle(), arg5);
        }
        catch(Throwable v3) {
            StrictMode.setThreadPolicy(v0);
            throw v3;
        }

        StrictMode.setThreadPolicy(v0);
    }

    public void onLowMemory() {
        this.zzch.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.zzch.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.zzch.onResume();
    }

    public void onSaveInstanceState(Bundle arg2) {
        if(arg2 != null) {
            arg2.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }

        super.onSaveInstanceState(arg2);
        this.zzch.onSaveInstanceState(arg2);
    }

    public void onStart() {
        super.onStart();
        this.zzch.onStart();
    }

    public void onStop() {
        this.zzch.onStop();
        super.onStop();
    }

    public void setArguments(Bundle arg1) {
        super.setArguments(arg1);
    }
}

