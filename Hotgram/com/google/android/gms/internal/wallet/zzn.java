package com.google.android.gms.internal.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public interface zzn extends IInterface {
    int getState();

    void initialize(WalletFragmentInitParams arg1);

    void onActivityResult(int arg1, int arg2, Intent arg3);

    void onCreate(Bundle arg1);

    IObjectWrapper onCreateView(IObjectWrapper arg1, IObjectWrapper arg2, Bundle arg3);

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle arg1);

    void onStart();

    void onStop();

    void setEnabled(boolean arg1);

    void updateMaskedWallet(MaskedWallet arg1);

    void updateMaskedWalletRequest(MaskedWalletRequest arg1);

    void zza(IObjectWrapper arg1, WalletFragmentOptions arg2, Bundle arg3);
}

