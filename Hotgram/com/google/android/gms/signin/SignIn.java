package com.google.android.gms.signin;

import android.os.Bundle;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class SignIn {
    public class SignInOptionsInternal implements HasOptions {
        private final Bundle zzada;

        private SignInOptionsInternal(Bundle arg1) {
            super();
            if(arg1 != null) {
            }
            else {
                arg1 = new Bundle();
            }

            this.zzada = arg1;
        }

        public static SignInOptionsInternal create(Bundle arg1) {
            return new SignInOptionsInternal(arg1);
        }

        public Bundle getSignInOptionsBundle() {
            return this.zzada;
        }
    }

    public static final Api API;
    public static final AbstractClientBuilder CLIENT_BUILDER;
    public static final ClientKey CLIENT_KEY;
    public static final Api INTERNAL_API;
    public static final ClientKey INTERNAL_CLIENT_KEY;
    public static final Scope SCOPE_EMAIL;
    public static final Scope SCOPE_PROFILE;
    private static final AbstractClientBuilder zzacz;

    static {
        SignIn.CLIENT_KEY = new ClientKey();
        SignIn.INTERNAL_CLIENT_KEY = new ClientKey();
        SignIn.CLIENT_BUILDER = new zza();
        SignIn.zzacz = new zzb();
        SignIn.SCOPE_PROFILE = new Scope("profile");
        SignIn.SCOPE_EMAIL = new Scope("email");
        SignIn.API = new Api("SignIn.API", SignIn.CLIENT_BUILDER, SignIn.CLIENT_KEY);
        SignIn.INTERNAL_API = new Api("SignIn.INTERNAL_API", SignIn.zzacz, SignIn.INTERNAL_CLIENT_KEY);
    }

    private SignIn() {
        super();
    }
}

