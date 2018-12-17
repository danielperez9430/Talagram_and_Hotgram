package com.google.android.gms.wallet.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.FragmentWrapper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zzn;
import com.google.android.gms.internal.wallet.zzr;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.R$string;

@TargetApi(value=12) public final class WalletFragment extends Fragment {
    public interface OnStateChangedListener {
        void onStateChanged(WalletFragment arg1, int arg2, int arg3, Bundle arg4);
    }

    final class zza extends zzr {
        private OnStateChangedListener zzfw;
        private final WalletFragment zzfx;

        zza(WalletFragment arg1) {
            super();
            this.zzfx = arg1;
        }

        public final void zza(OnStateChangedListener arg1) {
            this.zzfw = arg1;
        }

        public final void zza(int arg3, int arg4, Bundle arg5) {
            if(this.zzfw != null) {
                this.zzfw.onStateChanged(this.zzfx, arg3, arg4, arg5);
            }
        }
    }

    final class zzb implements LifecycleDelegate {
        private final zzn zzfp;

        private zzb(zzn arg1) {
            super();
            this.zzfp = arg1;
        }

        zzb(zzn arg1, com.google.android.gms.wallet.fragment.zzb arg2) {
            this(arg1);
        }

        private final int getState() {
            try {
                return this.zzfp.getState();
            }
            catch(RemoteException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

        private final void initialize(WalletFragmentInitParams arg2) {
            try {
                this.zzfp.initialize(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        private final void onActivityResult(int arg2, int arg3, Intent arg4) {
            try {
                this.zzfp.onActivityResult(arg2, arg3, arg4);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        public final void onCreate(Bundle arg2) {
            try {
                this.zzfp.onCreate(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        public final View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
            try {
                return ObjectWrapper.unwrap(this.zzfp.onCreateView(ObjectWrapper.wrap(arg2), ObjectWrapper.wrap(arg3), arg4));
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        public final void onDestroy() {
        }

        public final void onDestroyView() {
        }

        public final void onInflate(Activity arg2, Bundle arg3, Bundle arg4) {
            Parcelable v3 = arg3.getParcelable("extraWalletFragmentOptions");
            try {
                this.zzfp.zza(ObjectWrapper.wrap(arg2), ((WalletFragmentOptions)v3), arg4);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        public final void onLowMemory() {
        }

        public final void onPause() {
            try {
                this.zzfp.onPause();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

        public final void onResume() {
            try {
                this.zzfp.onResume();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

        public final void onSaveInstanceState(Bundle arg2) {
            try {
                this.zzfp.onSaveInstanceState(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        public final void onStart() {
            try {
                this.zzfp.onStart();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

        public final void onStop() {
            try {
                this.zzfp.onStop();
                return;
            }
            catch(RemoteException v0) {
                throw new RuntimeException(((Throwable)v0));
            }
        }

        private final void setEnabled(boolean arg2) {
            try {
                this.zzfp.setEnabled(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        private final void updateMaskedWallet(MaskedWallet arg2) {
            try {
                this.zzfp.updateMaskedWallet(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        private final void updateMaskedWalletRequest(MaskedWalletRequest arg2) {
            try {
                this.zzfp.updateMaskedWalletRequest(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeException(((Throwable)v2));
            }
        }

        static int zza(zzb arg0) {
            return arg0.getState();
        }

        static void zza(zzb arg0, WalletFragmentInitParams arg1) {
            arg0.initialize(arg1);
        }

        static void zza(zzb arg0, int arg1, int arg2, Intent arg3) {
            arg0.onActivityResult(arg1, arg2, arg3);
        }

        static void zza(zzb arg0, boolean arg1) {
            arg0.setEnabled(arg1);
        }

        static void zza(zzb arg0, MaskedWallet arg1) {
            arg0.updateMaskedWallet(arg1);
        }

        static void zza(zzb arg0, MaskedWalletRequest arg1) {
            arg0.updateMaskedWalletRequest(arg1);
        }
    }

    final class zzc extends DeferredLifecycleHelper implements View$OnClickListener {
        zzc(WalletFragment arg1, com.google.android.gms.wallet.fragment.zzb arg2) {
            this(arg1);
        }

        private zzc(WalletFragment arg1) {
            this.zzfy = arg1;
            super();
        }

        protected final void createDelegate(OnDelegateCreatedListener arg5) {
            com.google.android.gms.wallet.fragment.zzb v3;
            Activity v0 = WalletFragment.zza(this.zzfy).getActivity();
            if(WalletFragment.zzb(this.zzfy) == null && (WalletFragment.zzc(this.zzfy)) && v0 != null) {
                try {
                    v3 = null;
                    WalletFragment.zza(this.zzfy, new zzb(zzam.zza(v0, WalletFragment.zzd(this.zzfy), WalletFragment.zze(this.zzfy), WalletFragment.zzf(this.zzfy)), v3));
                    WalletFragment.zza(this.zzfy, ((WalletFragmentOptions)v3));
                }
                catch(GooglePlayServicesNotAvailableException ) {
                    return;
                }

                arg5.onDelegateCreated(WalletFragment.zzb(this.zzfy));
                if(WalletFragment.zzg(this.zzfy) != null) {
                    zzb.zza(WalletFragment.zzb(this.zzfy), WalletFragment.zzg(this.zzfy));
                    WalletFragment.zza(this.zzfy, ((WalletFragmentInitParams)v3));
                }

                if(WalletFragment.zzh(this.zzfy) != null) {
                    zzb.zza(WalletFragment.zzb(this.zzfy), WalletFragment.zzh(this.zzfy));
                    WalletFragment.zza(this.zzfy, ((MaskedWalletRequest)v3));
                }

                if(WalletFragment.zzi(this.zzfy) != null) {
                    zzb.zza(WalletFragment.zzb(this.zzfy), WalletFragment.zzi(this.zzfy));
                    WalletFragment.zza(this.zzfy, ((MaskedWallet)v3));
                }

                if(WalletFragment.zzj(this.zzfy) == null) {
                    return;
                }

                zzb.zza(WalletFragment.zzb(this.zzfy), WalletFragment.zzj(this.zzfy).booleanValue());
                WalletFragment.zza(this.zzfy, ((Boolean)v3));
            }
        }

        protected final void handleGooglePlayUnavailable(FrameLayout arg7) {
            Button v0 = new Button(WalletFragment.zza(this.zzfy).getActivity());
            v0.setText(string.wallet_buy_button_place_holder);
            int v2 = -2;
            int v3 = -1;
            if(WalletFragment.zze(this.zzfy) != null) {
                WalletFragmentStyle v1 = WalletFragment.zze(this.zzfy).getFragmentStyle();
                if(v1 != null) {
                    DisplayMetrics v4 = WalletFragment.zza(this.zzfy).getResources().getDisplayMetrics();
                    v3 = v1.zza("buyButtonWidth", v4, v3);
                    v2 = v1.zza("buyButtonHeight", v4, v2);
                }
            }

            v0.setLayoutParams(new ViewGroup$LayoutParams(v3, v2));
            v0.setOnClickListener(((View$OnClickListener)this));
            arg7.addView(((View)v0));
        }

        public final void onClick(View arg3) {
            Activity v3 = WalletFragment.zza(this.zzfy).getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(((Context)v3), 12451000), v3, -1);
        }
    }

    private boolean zzfe;
    private WalletFragmentOptions zzfi;
    private WalletFragmentInitParams zzfj;
    private MaskedWalletRequest zzfk;
    private MaskedWallet zzfl;
    private Boolean zzfm;
    private zzb zzfr;
    private final FragmentWrapper zzfs;
    private final zzc zzft;
    private zza zzfu;
    private final Fragment zzfv;

    public WalletFragment() {
        super();
        this.zzfe = false;
        this.zzfs = FragmentWrapper.wrap(((Fragment)this));
        this.zzft = new zzc(this, null);
        this.zzfu = new zza(this);
        this.zzfv = ((Fragment)this);
    }

    public final int getState() {
        if(this.zzfr != null) {
            return zzb.zza(this.zzfr);
        }

        return 0;
    }

    public final void initialize(WalletFragmentInitParams arg2) {
        if(this.zzfr != null) {
            zzb.zza(this.zzfr, arg2);
            this.zzfj = null;
            return;
        }

        if(this.zzfj == null) {
            this.zzfj = arg2;
            if(this.zzfk != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }

            if(this.zzfl == null) {
                return;
            }

            Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            return;
        }
        else {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions arg3) {
        WalletFragment v0 = new WalletFragment();
        Bundle v1 = new Bundle();
        v1.putParcelable("extraWalletFragmentOptions", ((Parcelable)arg3));
        v0.zzfv.setArguments(v1);
        return v0;
    }

    public final void onActivityResult(int arg2, int arg3, Intent arg4) {
        super.onActivityResult(arg2, arg3, arg4);
        if(this.zzfr != null) {
            zzb.zza(this.zzfr, arg2, arg3, arg4);
        }
    }

    public final void onCreate(Bundle arg4) {
        Parcelable v0;
        super.onCreate(arg4);
        if(arg4 != null) {
            arg4.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            v0 = arg4.getParcelable("walletFragmentInitParams");
            if(v0 != null) {
                if(this.zzfj != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }

                this.zzfj = ((WalletFragmentInitParams)v0);
            }

            if(this.zzfk == null) {
                this.zzfk = arg4.getParcelable("maskedWalletRequest");
            }

            if(this.zzfl == null) {
                this.zzfl = arg4.getParcelable("maskedWallet");
            }

            if(arg4.containsKey("walletFragmentOptions")) {
                this.zzfi = arg4.getParcelable("walletFragmentOptions");
            }

            if(!arg4.containsKey("enabled")) {
                goto label_50;
            }

            this.zzfm = Boolean.valueOf(arg4.getBoolean("enabled"));
        }
        else {
            if(this.zzfv.getArguments() == null) {
                goto label_50;
            }

            v0 = this.zzfv.getArguments().getParcelable("extraWalletFragmentOptions");
            if(v0 == null) {
                goto label_50;
            }

            ((WalletFragmentOptions)v0).zza(this.zzfv.getActivity());
            this.zzfi = ((WalletFragmentOptions)v0);
        }

    label_50:
        this.zzfe = true;
        this.zzft.onCreate(arg4);
    }

    public final View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        return this.zzft.onCreateView(arg2, arg3, arg4);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzfe = false;
    }

    public final void onInflate(Activity arg3, AttributeSet arg4, Bundle arg5) {
        super.onInflate(arg3, arg4, arg5);
        if(this.zzfi == null) {
            this.zzfi = WalletFragmentOptions.zza(((Context)arg3), arg4);
        }

        Bundle v4 = new Bundle();
        v4.putParcelable("attrKeyWalletFragmentOptions", this.zzfi);
        this.zzft.onInflate(arg3, v4, arg5);
    }

    public final void onPause() {
        super.onPause();
        this.zzft.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.zzft.onResume();
        FragmentManager v0 = this.zzfv.getActivity().getFragmentManager();
        Fragment v1 = v0.findFragmentByTag("GooglePlayServicesErrorDialog");
        if(v1 != null) {
            v0.beginTransaction().remove(v1).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzfv.getActivity(), 12451000), this.zzfv.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle arg4) {
        super.onSaveInstanceState(arg4);
        arg4.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzft.onSaveInstanceState(arg4);
        WalletFragmentInitParams v1 = null;
        if(this.zzfj != null) {
            arg4.putParcelable("walletFragmentInitParams", this.zzfj);
            this.zzfj = v1;
        }

        if(this.zzfk != null) {
            arg4.putParcelable("maskedWalletRequest", this.zzfk);
            this.zzfk = ((MaskedWalletRequest)v1);
        }

        if(this.zzfl != null) {
            arg4.putParcelable("maskedWallet", this.zzfl);
            this.zzfl = ((MaskedWallet)v1);
        }

        if(this.zzfi != null) {
            arg4.putParcelable("walletFragmentOptions", this.zzfi);
            this.zzfi = ((WalletFragmentOptions)v1);
        }

        if(this.zzfm != null) {
            arg4.putBoolean("enabled", this.zzfm.booleanValue());
            this.zzfm = ((Boolean)v1);
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzft.onStart();
    }

    public final void onStop() {
        super.onStop();
        this.zzft.onStop();
    }

    public final void setEnabled(boolean arg2) {
        Boolean v2;
        if(this.zzfr != null) {
            zzb.zza(this.zzfr, arg2);
            v2 = null;
        }
        else {
            v2 = Boolean.valueOf(arg2);
        }

        this.zzfm = v2;
    }

    public final void setOnStateChangedListener(OnStateChangedListener arg2) {
        this.zzfu.zza(arg2);
    }

    public final void updateMaskedWallet(MaskedWallet arg2) {
        if(this.zzfr != null) {
            zzb.zza(this.zzfr, arg2);
            this.zzfl = null;
            return;
        }

        this.zzfl = arg2;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest arg2) {
        if(this.zzfr != null) {
            zzb.zza(this.zzfr, arg2);
            this.zzfk = null;
            return;
        }

        this.zzfk = arg2;
    }

    static Fragment zza(WalletFragment arg0) {
        return arg0.zzfv;
    }

    static MaskedWallet zza(WalletFragment arg0, MaskedWallet arg1) {
        arg0.zzfl = null;
        return null;
    }

    static MaskedWalletRequest zza(WalletFragment arg0, MaskedWalletRequest arg1) {
        arg0.zzfk = null;
        return null;
    }

    static zzb zza(WalletFragment arg0, zzb arg1) {
        arg0.zzfr = arg1;
        return arg1;
    }

    static WalletFragmentInitParams zza(WalletFragment arg0, WalletFragmentInitParams arg1) {
        arg0.zzfj = null;
        return null;
    }

    static WalletFragmentOptions zza(WalletFragment arg0, WalletFragmentOptions arg1) {
        arg0.zzfi = null;
        return null;
    }

    static Boolean zza(WalletFragment arg0, Boolean arg1) {
        arg0.zzfm = null;
        return null;
    }

    static zzb zzb(WalletFragment arg0) {
        return arg0.zzfr;
    }

    static boolean zzc(WalletFragment arg0) {
        return arg0.zzfe;
    }

    static FragmentWrapper zzd(WalletFragment arg0) {
        return arg0.zzfs;
    }

    static WalletFragmentOptions zze(WalletFragment arg0) {
        return arg0.zzfi;
    }

    static zza zzf(WalletFragment arg0) {
        return arg0.zzfu;
    }

    static WalletFragmentInitParams zzg(WalletFragment arg0) {
        return arg0.zzfj;
    }

    static MaskedWalletRequest zzh(WalletFragment arg0) {
        return arg0.zzfk;
    }

    static MaskedWallet zzi(WalletFragment arg0) {
        return arg0.zzfl;
    }

    static Boolean zzj(WalletFragment arg0) {
        return arg0.zzfm;
    }
}

