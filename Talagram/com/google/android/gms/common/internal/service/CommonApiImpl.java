package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

public final class CommonApiImpl implements CommonApi {
    final class zza extends BaseCommonServiceCallbacks {
        private final ResultHolder mResultHolder;

        public zza(ResultHolder arg1) {
            super();
            this.mResultHolder = arg1;
        }

        public final void onDefaultAccountCleared(int arg3) {
            this.mResultHolder.setResult(new Status(arg3));
        }
    }

    public CommonApiImpl() {
        super();
    }

    public final PendingResult clearDefaultAccount(GoogleApiClient arg2) {
        return arg2.execute(new zzb(this, arg2));
    }
}

