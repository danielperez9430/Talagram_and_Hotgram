package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.zzas;

public interface zzw extends IInterface {
    void zza(int arg1, Bundle arg2);

    void zza(int arg1, FullWallet arg2, Bundle arg3);

    void zza(int arg1, MaskedWallet arg2, Bundle arg3);

    void zza(int arg1, boolean arg2, Bundle arg3);

    void zza(Status arg1, Bundle arg2);

    void zza(Status arg1, zzh arg2, Bundle arg3);

    void zza(Status arg1, zzj arg2, Bundle arg3);

    void zza(Status arg1, zzl arg2, Bundle arg3);

    void zza(Status arg1, PaymentData arg2, Bundle arg3);

    void zza(Status arg1, zzas arg2, Bundle arg3);

    void zza(Status arg1, boolean arg2, Bundle arg3);

    void zzb(int arg1, boolean arg2, Bundle arg3);

    void zzb(Status arg1, Bundle arg2);

    void zzc(Status arg1, Bundle arg2);
}

