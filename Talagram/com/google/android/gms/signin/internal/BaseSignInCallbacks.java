package com.google.android.gms.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public class BaseSignInCallbacks extends Stub {
    public BaseSignInCallbacks() {
        super();
    }

    public void onAuthAccountComplete(ConnectionResult arg1, AuthAccountResult arg2) {
    }

    public void onGetCurrentAccountComplete(Status arg1, GoogleSignInAccount arg2) {
    }

    public void onRecordConsentComplete(Status arg1) {
    }

    public void onSaveAccountToSessionStoreComplete(Status arg1) {
    }

    public void onSignInComplete(SignInResponse arg1) {
    }
}

