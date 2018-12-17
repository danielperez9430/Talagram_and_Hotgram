package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.h;
import android.support.v4.app.l;
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
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zzn;
import com.google.android.gms.internal.wallet.zzr;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.R$string;

public final class SupportWalletFragment extends Fragment {
    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment arg1, int arg2, int arg3, Bundle arg4);
    }

    final class zza extends zzr {
        private OnStateChangedListener zzfn;
        private final SupportWalletFragment zzfo;

        zza(SupportWalletFragment arg1) {
            super();
            this.zzfo = arg1;
        }

        public final void zza(OnStateChangedListener arg1) {
            this.zzfn = arg1;
        }

        public final void zza(int arg3, int arg4, Bundle arg5) {
            if(this.zzfn != null) {
                this.zzfn.onStateChanged(this.zzfo, arg3, arg4, arg5);
            }
        }
    }

    final class zzb implements LifecycleDelegate {
        private final zzn zzfp;

        private zzb(zzn arg1) {
            super();
            this.zzfp = arg1;
        }

        zzb(zzn arg1, com.google.android.gms.wallet.fragment.zza arg2) {
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
        zzc(SupportWalletFragment arg1, com.google.android.gms.wallet.fragment.zza arg2) {
            this(arg1);
        }

        private zzc(SupportWalletFragment arg1) {
            this.zzfq = arg1;
            super();
        }

        protected final void createDelegate(OnDelegateCreatedListener arg5) {
            com.google.android.gms.wallet.fragment.zza v3;
            h v0 = SupportWalletFragment.zza(this.zzfq).getActivity();
            if(SupportWalletFragment.zzb(this.zzfq) == null && (SupportWalletFragment.zzc(this.zzfq)) && v0 != null) {
                try {
                    v3 = null;
                    SupportWalletFragment.zza(this.zzfq, new zzb(zzam.zza(((Activity)v0), SupportWalletFragment.zzd(this.zzfq), SupportWalletFragment.zze(this.zzfq), SupportWalletFragment.zzf(this.zzfq)), v3));
                    SupportWalletFragment.zza(this.zzfq, ((WalletFragmentOptions)v3));
                }
                catch(GooglePlayServicesNotAvailableException ) {
                    return;
                }

                arg5.onDelegateCreated(SupportWalletFragment.zzb(this.zzfq));
                if(SupportWalletFragment.zzg(this.zzfq) != null) {
                    zzb.zza(SupportWalletFragment.zzb(this.zzfq), SupportWalletFragment.zzg(this.zzfq));
                    SupportWalletFragment.zza(this.zzfq, ((WalletFragmentInitParams)v3));
                }

                if(SupportWalletFragment.zzh(this.zzfq) != null) {
                    zzb.zza(SupportWalletFragment.zzb(this.zzfq), SupportWalletFragment.zzh(this.zzfq));
                    SupportWalletFragment.zza(this.zzfq, ((MaskedWalletRequest)v3));
                }

                if(SupportWalletFragment.zzi(this.zzfq) != null) {
                    zzb.zza(SupportWalletFragment.zzb(this.zzfq), SupportWalletFragment.zzi(this.zzfq));
                    SupportWalletFragment.zza(this.zzfq, ((MaskedWallet)v3));
                }

                if(SupportWalletFragment.zzj(this.zzfq) == null) {
                    return;
                }

                zzb.zza(SupportWalletFragment.zzb(this.zzfq), SupportWalletFragment.zzj(this.zzfq).booleanValue());
                SupportWalletFragment.zza(this.zzfq, ((Boolean)v3));
            }
        }

        protected final void handleGooglePlayUnavailable(FrameLayout arg7) {
            Button v0 = new Button(SupportWalletFragment.zza(this.zzfq).getActivity());
            v0.setText(string.wallet_buy_button_place_holder);
            int v2 = -2;
            int v3 = -1;
            if(SupportWalletFragment.zze(this.zzfq) != null) {
                WalletFragmentStyle v1 = SupportWalletFragment.zze(this.zzfq).getFragmentStyle();
                if(v1 != null) {
                    DisplayMetrics v4 = SupportWalletFragment.zza(this.zzfq).getResources().getDisplayMetrics();
                    v3 = v1.zza("buyButtonWidth", v4, v3);
                    v2 = v1.zza("buyButtonHeight", v4, v2);
                }
            }

            v0.setLayoutParams(new ViewGroup$LayoutParams(v3, v2));
            v0.setOnClickListener(((View$OnClickListener)this));
            arg7.addView(((View)v0));
        }

        public final void onClick(View arg3) {
            h v3 = SupportWalletFragment.zza(this.zzfq).getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(((Context)v3), 12451000), ((Activity)v3), -1);
        }
    }

    private final Fragment fragment;
    private zzb zzfd;
    private boolean zzfe;
    private final SupportFragmentWrapper zzff;
    private final zzc zzfg;
    private zza zzfh;
    private WalletFragmentOptions zzfi;
    private WalletFragmentInitParams zzfj;
    private MaskedWalletRequest zzfk;
    private MaskedWallet zzfl;
    private Boolean zzfm;

    public SupportWalletFragment() {
        super();
        this.zzfe = false;
        this.zzff = SupportFragmentWrapper.wrap(((Fragment)this));
        this.zzfg = new zzc(this, null);
        this.zzfh = new zza(this);
        this.fragment = ((Fragment)this);
    }

    public final int getState() {
        if(this.zzfd != null) {
            return zzb.zza(this.zzfd);
        }

        return 0;
    }

    public final void initialize(WalletFragmentInitParams arg2) {
        if(this.zzfd != null) {
            zzb.zza(this.zzfd, arg2);
            this.zzfj = null;
            return;
        }

        if(this.zzfj == null) {
            this.zzfj = arg2;
            if(this.zzfk != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }

            if(this.zzfl == null) {
                return;
            }

            Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            return;
        }
        else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions arg3) {
        SupportWalletFragment v0 = new SupportWalletFragment();
        Bundle v1 = new Bundle();
        v1.putParcelable("extraWalletFragmentOptions", ((Parcelable)arg3));
        v0.fragment.setArguments(v1);
        return v0;
    }

    public final void onActivityResult(int arg2, int arg3, Intent arg4) {
        super.onActivityResult(arg2, arg3, arg4);
        if(this.zzfd != null) {
            zzb.zza(this.zzfd, arg2, arg3, arg4);
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
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
            if(this.fragment.getArguments() == null) {
                goto label_50;
            }

            v0 = this.fragment.getArguments().getParcelable("extraWalletFragmentOptions");
            if(v0 == null) {
                goto label_50;
            }

            ((WalletFragmentOptions)v0).zza(this.fragment.getActivity());
            this.zzfi = ((WalletFragmentOptions)v0);
        }

    label_50:
        this.zzfe = true;
        this.zzfg.onCreate(arg4);
    }

    public final View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        return this.zzfg.onCreateView(arg2, arg3, arg4);
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
        this.zzfg.onInflate(arg3, v4, arg5);
    }

    public final void onPause() {
        super.onPause();
        this.zzfg.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.zzfg.onResume();
        l v0 = this.fragment.getActivity().d();
        Fragment v1 = v0.a("GooglePlayServicesErrorDialog");
        if(v1 != null) {
            v0.a().a(v1).c();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.fragment.getActivity(), 12451000), this.fragment.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle arg4) {
        super.onSaveInstanceState(arg4);
        arg4.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfg.onSaveInstanceState(arg4);
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
        this.zzfg.onStart();
    }

    public final void onStop() {
        super.onStop();
        this.zzfg.onStop();
    }

    public final void setEnabled(boolean arg2) {
        Boolean v2;
        if(this.zzfd != null) {
            zzb.zza(this.zzfd, arg2);
            v2 = null;
        }
        else {
            v2 = Boolean.valueOf(arg2);
        }

        this.zzfm = v2;
    }

    public final void setOnStateChangedListener(OnStateChangedListener arg2) {
        this.zzfh.zza(arg2);
    }

    public final void updateMaskedWallet(MaskedWallet arg2) {
        if(this.zzfd != null) {
            zzb.zza(this.zzfd, arg2);
            this.zzfl = null;
            return;
        }

        this.zzfl = arg2;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest arg2) {
        if(this.zzfd != null) {
            zzb.zza(this.zzfd, arg2);
            this.zzfk = null;
            return;
        }

        this.zzfk = arg2;
    }

    static Fragment zza(SupportWalletFragment arg0) {
        return arg0.fragment;
    }

    static MaskedWallet zza(SupportWalletFragment arg0, MaskedWallet arg1) {
        arg0.zzfl = null;
        return null;
    }

    static MaskedWalletRequest zza(SupportWalletFragment arg0, MaskedWalletRequest arg1) {
        arg0.zzfk = null;
        return null;
    }

    static zzb zza(SupportWalletFragment arg0, zzb arg1) {
        arg0.zzfd = arg1;
        return arg1;
    }

    static WalletFragmentInitParams zza(SupportWalletFragment arg0, WalletFragmentInitParams arg1) {
        arg0.zzfj = null;
        return null;
    }

    static WalletFragmentOptions zza(SupportWalletFragment arg0, WalletFragmentOptions arg1) {
        arg0.zzfi = null;
        return null;
    }

    static Boolean zza(SupportWalletFragment arg0, Boolean arg1) {
        arg0.zzfm = null;
        return null;
    }

    static zzb zzb(SupportWalletFragment arg0) {
        return arg0.zzfd;
    }

    static boolean zzc(SupportWalletFragment arg0) {
        return arg0.zzfe;
    }

    static SupportFragmentWrapper zzd(SupportWalletFragment arg0) {
        return arg0.zzff;
    }

    static WalletFragmentOptions zze(SupportWalletFragment arg0) {
        return arg0.zzfi;
    }

    static zza zzf(SupportWalletFragment arg0) {
        return arg0.zzfh;
    }

    static WalletFragmentInitParams zzg(SupportWalletFragment arg0) {
        return arg0.zzfj;
    }

    static MaskedWalletRequest zzh(SupportWalletFragment arg0) {
        return arg0.zzfk;
    }

    static MaskedWallet zzi(SupportWalletFragment arg0) {
        return arg0.zzfl;
    }

    static Boolean zzj(SupportWalletFragment arg0) {
        return arg0.zzfm;
    }
}

