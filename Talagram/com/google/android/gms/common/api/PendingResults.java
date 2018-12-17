package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk public final class PendingResults {
    final class zza extends BasePendingResult {
        private final Result zzdl;

        public zza(Result arg2) {
            super(Looper.getMainLooper());
            this.zzdl = arg2;
        }

        protected final Result createFailedResult(Status arg2) {
            if(arg2.getStatusCode() == this.zzdl.getStatus().getStatusCode()) {
                return this.zzdl;
            }

            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    final class zzb extends BasePendingResult {
        private final Result zzdm;

        public zzb(GoogleApiClient arg1, Result arg2) {
            super(arg1);
            this.zzdm = arg2;
        }

        protected final Result createFailedResult(Status arg1) {
            return this.zzdm;
        }
    }

    final class zzc extends BasePendingResult {
        public zzc(GoogleApiClient arg1) {
            super(arg1);
        }

        protected final Result createFailedResult(Status arg2) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    @KeepForSdk private PendingResults() {
        super();
    }

    public static PendingResult canceledPendingResult() {
        StatusPendingResult v0 = new StatusPendingResult(Looper.getMainLooper());
        ((PendingResult)v0).cancel();
        return ((PendingResult)v0);
    }

    public static PendingResult canceledPendingResult(Result arg2) {
        Preconditions.checkNotNull(arg2, "Result must not be null");
        boolean v0 = arg2.getStatus().getStatusCode() == 16 ? true : false;
        Preconditions.checkArgument(v0, "Status code must be CommonStatusCodes.CANCELED");
        zza v0_1 = new zza(arg2);
        ((PendingResult)v0_1).cancel();
        return ((PendingResult)v0_1);
    }

    @KeepForSdk public static PendingResult immediateFailedResult(Result arg2, GoogleApiClient arg3) {
        Preconditions.checkNotNull(arg2, "Result must not be null");
        Preconditions.checkArgument(arg2.getStatus().isSuccess() ^ 1, "Status code must not be SUCCESS");
        zzb v0 = new zzb(arg3, arg2);
        ((BasePendingResult)v0).setResult(arg2);
        return ((PendingResult)v0);
    }

    @KeepForSdk public static PendingResult immediatePendingResult(Status arg1, GoogleApiClient arg2) {
        Preconditions.checkNotNull(arg1, "Result must not be null");
        StatusPendingResult v0 = new StatusPendingResult(arg2);
        ((BasePendingResult)v0).setResult(((Result)arg1));
        return ((PendingResult)v0);
    }

    @KeepForSdk public static OptionalPendingResult immediatePendingResult(Result arg2) {
        Preconditions.checkNotNull(arg2, "Result must not be null");
        zzc v0 = new zzc(null);
        ((BasePendingResult)v0).setResult(arg2);
        return new OptionalPendingResultImpl(((PendingResult)v0));
    }

    @KeepForSdk public static OptionalPendingResult immediatePendingResult(Result arg1, GoogleApiClient arg2) {
        Preconditions.checkNotNull(arg1, "Result must not be null");
        zzc v0 = new zzc(arg2);
        ((BasePendingResult)v0).setResult(arg1);
        return new OptionalPendingResultImpl(((PendingResult)v0));
    }

    @KeepForSdk public static PendingResult immediatePendingResult(Status arg2) {
        Preconditions.checkNotNull(arg2, "Result must not be null");
        StatusPendingResult v0 = new StatusPendingResult(Looper.getMainLooper());
        ((BasePendingResult)v0).setResult(((Result)arg2));
        return ((PendingResult)v0);
    }
}

