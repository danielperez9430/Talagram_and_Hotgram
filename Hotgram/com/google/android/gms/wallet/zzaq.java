package com.google.android.gms.wallet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.wallet.zzaf;

final class zzaq extends AbstractClientBuilder {
    zzaq() {
        super();
    }

    public final Client buildClient(Context arg12, Looper arg13, ClientSettings arg14, Object arg15, ConnectionCallbacks arg16, OnConnectionFailedListener arg17) {
        WalletOptions v0_1;
        Object v0 = arg15;
        if(v0 != null) {
        }
        else {
            v0_1 = new WalletOptions(null);
        }

        return new zzaf(arg12, arg13, arg14, arg16, arg17, v0_1.environment, v0_1.theme, v0_1.zzet);
    }
}

