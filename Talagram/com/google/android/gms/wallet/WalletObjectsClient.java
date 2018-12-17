package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

public class WalletObjectsClient extends GoogleApi {
    WalletObjectsClient(Activity arg3, WalletOptions arg4) {
        super(arg3, Wallet.API, ((ApiOptions)arg4), Settings.DEFAULT_SETTINGS);
    }

    public Task createWalletObjects(CreateWalletObjectsRequest arg2) {
        return this.doWrite(new zzar(this, arg2));
    }
}

