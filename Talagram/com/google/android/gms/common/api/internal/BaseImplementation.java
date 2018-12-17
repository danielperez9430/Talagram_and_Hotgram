package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api$SimpleClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

@KeepForSdk public class BaseImplementation {
    @KeepForSdk public abstract class ApiMethodImpl extends BasePendingResult implements ResultHolder {
        @KeepForSdk private final Api mApi;
        @KeepForSdk private final AnyClientKey mClientKey;

        @KeepForSdk @Deprecated protected ApiMethodImpl(AnyClientKey arg2, GoogleApiClient arg3) {
            super(Preconditions.checkNotNull(arg3, "GoogleApiClient must not be null"));
            this.mClientKey = Preconditions.checkNotNull(arg2);
            this.mApi = null;
        }

        @KeepForSdk protected ApiMethodImpl(Api arg2, GoogleApiClient arg3) {
            super(Preconditions.checkNotNull(arg3, "GoogleApiClient must not be null"));
            Preconditions.checkNotNull(arg2, "Api must not be null");
            this.mClientKey = arg2.getClientKey();
            this.mApi = arg2;
        }

        @KeepForSdk protected ApiMethodImpl(CallbackHandler arg1) {
            super(arg1);
            this.mClientKey = null;
            this.mApi = null;
        }

        @KeepForSdk protected abstract void doExecute(AnyClient arg1);

        @KeepForSdk public final Api getApi() {
            return this.mApi;
        }

        @KeepForSdk public final AnyClientKey getClientKey() {
            return this.mClientKey;
        }

        @KeepForSdk protected void onSetFailedResult(Result arg1) {
        }

        @KeepForSdk public final void run(AnyClient arg2) {
            SimpleClient v2;
            if((arg2 instanceof SimpleClientAdapter)) {
                v2 = ((SimpleClientAdapter)arg2).getClient();
            }

            try {
                this.doExecute(((AnyClient)v2));
                return;
            }
            catch(RemoteException v2_1) {
                this.setFailedResult(v2_1);
                return;
            }
            catch(DeadObjectException v2_2) {
                this.setFailedResult(((RemoteException)v2_2));
                throw v2_2;
            }
        }

        @KeepForSdk private void setFailedResult(RemoteException arg4) {
            this.setFailedResult(new Status(8, arg4.getLocalizedMessage(), null));
        }

        @KeepForSdk public final void setFailedResult(Status arg3) {
            Preconditions.checkArgument(arg3.isSuccess() ^ 1, "Failed result must not be success");
            Result v3 = ((BasePendingResult)this).createFailedResult(arg3);
            ((BasePendingResult)this).setResult(v3);
            this.onSetFailedResult(v3);
        }

        @KeepForSdk public void setResult(Object arg1) {
            super.setResult(((Result)arg1));
        }
    }

    @KeepForSdk public interface ResultHolder {
        @KeepForSdk void setFailedResult(Status arg1);

        @KeepForSdk void setResult(Object arg1);
    }

    public BaseImplementation() {
        super();
    }
}

