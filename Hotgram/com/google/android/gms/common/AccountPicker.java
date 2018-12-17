package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;

public final class AccountPicker {
    public static final int CUSTOM_THEME_ACCOUNT_CHIPS = 2;
    public static final int CUSTOM_THEME_GAMES = 1;
    public static final int CUSTOM_THEME_NONE = 0;
    public static final String EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING = "authTokenType";
    public static final String EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE = "addAccountOptions";
    public static final String EXTRA_ADD_ACCOUNT_REQUIRED_FEATURES_STRING_ARRAY = "addAccountRequiredFeatures";
    public static final String EXTRA_ALLOWABLE_ACCOUNTS_ARRAYLIST = "allowableAccounts";
    public static final String EXTRA_ALLOWABLE_ACCOUNT_TYPES_STRING_ARRAY = "allowableAccountTypes";
    public static final String EXTRA_ALWAYS_PROMPT_FOR_ACCOUNT = "alwaysPromptForAccount";
    public static final String EXTRA_DESCRIPTION_TEXT_OVERRIDE = "descriptionTextOverride";
    public static final String EXTRA_HOSTED_DOMAIN_FILTER = "hostedDomainFilter";
    public static final String EXTRA_IS_ACCOUNT_CHIPS_ACCOUNT_PICKER = "pickedFromAccountChips";
    public static final String EXTRA_OVERRIDE_CUSTOM_THEME = "overrideCustomTheme";
    public static final String EXTRA_OVERRIDE_THEME = "overrideTheme";
    public static final String EXTRA_REAL_CLIENT_PACKAGE = "realClientPackage";
    public static final String EXTRA_SELECTED_ACCOUNT = "selectedAccount";
    public static final String EXTRA_SET_GMS_CORE_ACCOUNT = "setGmsCoreAccount";
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_LIGHT = 1;

    private AccountPicker() {
        super();
    }

    public static Intent newChooseAccountIntent(Account arg9, ArrayList arg10, String[] arg11, boolean arg12, String arg13, String arg14, String[] arg15, Bundle arg16) {
        return AccountPicker.newChooseAccountIntent(arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, false);
    }

    public static Intent newChooseAccountIntent(Account arg11, ArrayList arg12, String[] arg13, boolean arg14, String arg15, String arg16, String[] arg17, Bundle arg18, boolean arg19) {
        return AccountPicker.newChooseAccountIntent(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, 0, 0);
    }

    public static Intent newChooseAccountIntent(Account arg13, ArrayList arg14, String[] arg15, boolean arg16, String arg17, String arg18, String[] arg19, Bundle arg20, boolean arg21, int arg22, int arg23) {
        return AccountPicker.newChooseAccountIntent(arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, null, false);
    }

    public static Intent newChooseAccountIntent(Account arg3, ArrayList arg4, String[] arg5, boolean arg6, String arg7, String arg8, String[] arg9, Bundle arg10, boolean arg11, int arg12, int arg13, String arg14, boolean arg15) {
        Intent v0 = new Intent();
        if(!arg15) {
            boolean v1 = arg14 == null ? true : false;
            Preconditions.checkArgument(v1, "We only support hostedDomain filter for account chip styled account picker");
        }

        String v15 = arg15 ? "com.google.android.gms.common.account.CHOOSE_ACCOUNT_USERTILE" : "com.google.android.gms.common.account.CHOOSE_ACCOUNT";
        v0.setAction(v15);
        v0.setPackage("com.google.android.gms");
        v0.putExtra("allowableAccounts", ((Serializable)arg4));
        v0.putExtra("allowableAccountTypes", arg5);
        v0.putExtra("addAccountOptions", arg10);
        v0.putExtra("selectedAccount", ((Parcelable)arg3));
        v0.putExtra("alwaysPromptForAccount", arg6);
        v0.putExtra("descriptionTextOverride", arg7);
        v0.putExtra("authTokenType", arg8);
        v0.putExtra("addAccountRequiredFeatures", arg9);
        v0.putExtra("setGmsCoreAccount", arg11);
        v0.putExtra("overrideTheme", arg12);
        v0.putExtra("overrideCustomTheme", arg13);
        v0.putExtra("hostedDomainFilter", arg14);
        return v0;
    }
}

