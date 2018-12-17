package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

public interface zzs extends IInterface {
    void zza(Bundle arg1, zzw arg2);

    void zza(CreateWalletObjectsRequest arg1, Bundle arg2, zzw arg3);

    void zza(FullWalletRequest arg1, Bundle arg2, zzw arg3);

    void zza(IsReadyToPayRequest arg1, Bundle arg2, zzw arg3);

    void zza(MaskedWalletRequest arg1, Bundle arg2, zzw arg3);

    void zza(PaymentDataRequest arg1, Bundle arg2, zzw arg3);

    void zza(String arg1, String arg2, Bundle arg3, zzw arg4);
}

