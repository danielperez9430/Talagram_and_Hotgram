package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

public final class Address {
    public final class AddressOptions implements HasOptions {
        public final int theme;

        public AddressOptions() {
            super();
            this.theme = 0;
        }

        public AddressOptions(int arg1) {
            super();
            this.theme = arg1;
        }
    }

    abstract class zza extends ApiMethodImpl {
        public zza(GoogleApiClient arg2) {
            super(Address.API, arg2);
        }

        public Result createFailedResult(Status arg1) {
            return arg1;
        }
    }

    public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;

    static {
        Address.CLIENT_KEY = new ClientKey();
        Address.CLIENT_BUILDER = new com.google.android.gms.identity.intents.zza();
        Address.API = new Api("Address.API", Address.CLIENT_BUILDER, Address.CLIENT_KEY);
    }

    public Address() {
        super();
    }

    public static void requestUserAddress(GoogleApiClient arg1, UserAddressRequest arg2, int arg3) {
        arg1.enqueue(new zzb(arg1, arg2, arg3));
    }
}

