package com.google.android.gms.common.oob;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting public class SignUp {
    public static final String ACTION_OOB_SIGN_UP = "com.google.android.gms.common.oob.OOB_SIGN_UP";
    public static final String EXTRAS_CALLING_APP_DESCRIPTION = "com.google.android.gms.common.oob.EXTRAS_APP_DESCRIPTION";
    public static final String EXTRAS_CLIENT_CALLING_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_CLIENT_CALLING_APP_PACKAGE";
    public static final String EXTRAS_PROMO_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE";
    public static final String EXTRAS_PROMO_APP_TEXT = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT";
    public static final String EXTRA_ACCOUNT_NAME = "com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME";
    public static final String EXTRA_BACK_BUTTON_NAME = "com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME";
    public static final String EXTRA_GPSRC = "com.google.android.gms.plus.GPSRC";
    public static final String EXTRA_OVERRIDE_THEME = "com.google.android.gms.plus.OVERRIDE_THEME";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = null;
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_FULL = 1;
    public static final int THEME_SETUP_WIZARD = 2;

    static {
        SignUp.GOOGLE_PLUS_REQUIRED_FEATURES = BaseGmsClient.GOOGLE_PLUS_REQUIRED_FEATURES;
    }

    private SignUp() {
        super();
    }

    public static AccountManagerFuture checkSignUpState(Context arg6, String arg7, String[] arg8, AccountManagerCallback arg9, Handler arg10) {
        Preconditions.checkArgument(TextUtils.isEmpty(((CharSequence)arg7)) ^ 1, "The accountName is required");
        int v0 = 0;
        boolean v2 = arg8 != null ? true : false;
        Preconditions.checkArgument(v2, "The requiredFeatures parameter is required");
        AccountManager v6 = AccountManager.get(arg6);
        Account[] v2_1 = v6.getAccountsByType("com.google");
        int v3 = v2_1.length;
        int v4 = 0;
        while(v0 < v3) {
            if(arg7.equals(v2_1[v0].name)) {
                v4 = 1;
            }

            ++v0;
        }

        if(v4 != 0) {
            return v6.hasFeatures(new Account(arg7, "com.google"), arg8, arg9, arg10);
        }

        throw new IllegalStateException("Given account does not exist on the device");
    }

    public static boolean isSignedUpBlocking(Context arg1, String arg2, String[] arg3) {
        return SignUp.checkSignUpState(arg1, arg2, arg3, null, null).getResult().booleanValue();
    }

    public static Intent newSignUpIntent(String arg1) {
        return SignUp.newSignUpIntent(arg1, null);
    }

    public static Intent newSignUpIntent(String arg2, String arg3) {
        Intent v0 = new Intent();
        v0.setPackage("com.google.android.gms");
        v0.setAction("com.google.android.gms.common.oob.OOB_SIGN_UP");
        v0.putExtra("com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME", arg2);
        v0.putExtra("com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME", arg3);
        return v0;
    }

    public static Intent newSignUpIntent(String arg2, String arg3, String arg4, String arg5) {
        Intent v0 = new Intent();
        v0.setPackage("com.google.android.gms");
        v0.setAction("com.google.android.gms.common.oob.OOB_SIGN_UP");
        v0.putExtra("com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME", arg2);
        v0.putExtra("com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME", arg3);
        v0.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE", arg4);
        v0.putExtra("com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT", arg5);
        return v0;
    }
}

