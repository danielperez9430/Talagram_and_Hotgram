package org.telegram.messenger;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AuthenticatorService extends Service {
    class Authenticator extends AbstractAccountAuthenticator {
        private final Context context;

        public Authenticator(Context arg1) {
            super(arg1);
            this.context = arg1;
        }

        public Bundle addAccount(AccountAuthenticatorResponse arg1, String arg2, String arg3, String[] arg4, Bundle arg5) {
            return null;
        }

        public Bundle confirmCredentials(AccountAuthenticatorResponse arg1, Account arg2, Bundle arg3) {
            return null;
        }

        public Bundle editProperties(AccountAuthenticatorResponse arg1, String arg2) {
            return null;
        }

        public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse arg1, Account arg2) {
            return super.getAccountRemovalAllowed(arg1, arg2);
        }

        public Bundle getAuthToken(AccountAuthenticatorResponse arg1, Account arg2, String arg3, Bundle arg4) {
            return null;
        }

        public String getAuthTokenLabel(String arg1) {
            return null;
        }

        public Bundle hasFeatures(AccountAuthenticatorResponse arg1, Account arg2, String[] arg3) {
            return null;
        }

        public Bundle updateCredentials(AccountAuthenticatorResponse arg1, Account arg2, String arg3, Bundle arg4) {
            return null;
        }
    }

    private static Authenticator authenticator;

    static {
    }

    public AuthenticatorService() {
        super();
    }

    protected Authenticator getAuthenticator() {
        if(AuthenticatorService.authenticator == null) {
            AuthenticatorService.authenticator = new Authenticator(((Context)this));
        }

        return AuthenticatorService.authenticator;
    }

    public IBinder onBind(Intent arg2) {
        if(arg2.getAction().equals("android.accounts.AccountAuthenticator")) {
            return this.getAuthenticator().getIBinder();
        }

        return null;
    }
}

