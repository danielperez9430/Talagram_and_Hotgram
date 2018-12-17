package com.google.android.gms.internal.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class zzp extends zza implements zzn {
    zzp(IBinder arg2) {
        super(arg2, "com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
    }

    public final int getState() {
        Parcel v0 = ((zza)this).transactAndReadException(13, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }

    public final void initialize(WalletFragmentInitParams arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(10, v0);
    }

    public final void onActivityResult(int arg2, int arg3, Intent arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        v0.writeInt(arg3);
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(9, v0);
    }

    public final void onCreate(Bundle arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(2, v0);
    }

    public final IObjectWrapper onCreateView(IObjectWrapper arg2, IObjectWrapper arg3, Bundle arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v2 = ((zza)this).transactAndReadException(3, v0);
        arg3 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return arg3;
    }

    public final void onPause() {
        ((zza)this).transactAndReadExceptionReturnVoid(6, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onResume() {
        ((zza)this).transactAndReadExceptionReturnVoid(5, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onSaveInstanceState(Bundle arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg3));
        v0 = ((zza)this).transactAndReadException(8, v0);
        if(v0.readInt() != 0) {
            arg3.readFromParcel(v0);
        }

        v0.recycle();
    }

    public final void onStart() {
        ((zza)this).transactAndReadExceptionReturnVoid(4, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void onStop() {
        ((zza)this).transactAndReadExceptionReturnVoid(7, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setEnabled(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.writeBoolean(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final void updateMaskedWallet(MaskedWallet arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(14, v0);
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(11, v0);
    }

    public final void zza(IObjectWrapper arg2, WalletFragmentOptions arg3, Bundle arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(1, v0);
    }
}

