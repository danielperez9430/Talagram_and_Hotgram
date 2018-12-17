package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.signin.internal.ISignInCallbacks;

public interface SignInClient extends Client {
    void clearAccountFromSessionStore();

    void connect();

    void saveDefaultAccount(IAccountAccessor arg1, boolean arg2);

    void signIn(ISignInCallbacks arg1);
}

