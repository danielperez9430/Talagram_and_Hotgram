package com.google.android.gms.wallet;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

public class PaymentsClient extends GoogleApi {
    PaymentsClient(Activity arg3, WalletOptions arg4) {
        super(arg3, Wallet.API, ((ApiOptions)arg4), Settings.DEFAULT_SETTINGS);
    }

    PaymentsClient(Context arg3, WalletOptions arg4) {
        super(arg3, Wallet.API, ((ApiOptions)arg4), Settings.DEFAULT_SETTINGS);
    }

    public Task isReadyToPay(IsReadyToPayRequest arg2) {
        return this.doRead(new zzaj(this, arg2));
    }

    public Task loadPaymentData(PaymentDataRequest arg2) {
        return this.doWrite(new zzak(this, arg2));
    }
}

