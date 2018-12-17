package com.google.android.gms.wallet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated public interface Payments {
    void changeMaskedWallet(GoogleApiClient arg1, String arg2, String arg3, int arg4);

    @Deprecated void checkForPreAuthorization(GoogleApiClient arg1, int arg2);

    @Deprecated PendingResult isReadyToPay(GoogleApiClient arg1);

    PendingResult isReadyToPay(GoogleApiClient arg1, IsReadyToPayRequest arg2);

    void loadFullWallet(GoogleApiClient arg1, FullWalletRequest arg2, int arg3);

    void loadMaskedWallet(GoogleApiClient arg1, MaskedWalletRequest arg2, int arg3);
}

