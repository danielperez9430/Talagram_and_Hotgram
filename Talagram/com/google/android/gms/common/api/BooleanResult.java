package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk public class BooleanResult implements Result {
    private final Status mStatus;
    private final boolean zzck;

    @KeepForSdk public BooleanResult(Status arg2, boolean arg3) {
        super();
        this.mStatus = Preconditions.checkNotNull(arg2, "Status must not be null");
        this.zzck = arg3;
    }

    @KeepForSdk public final boolean equals(Object arg5) {
        if((((BooleanResult)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof BooleanResult)) {
            return 0;
        }

        if((this.mStatus.equals(((BooleanResult)arg5).mStatus)) && this.zzck == ((BooleanResult)arg5).zzck) {
            return 1;
        }

        return 0;
    }

    @KeepForSdk public Status getStatus() {
        return this.mStatus;
    }

    @KeepForSdk public boolean getValue() {
        return this.zzck;
    }

    @KeepForSdk public final int hashCode() {
        return (this.mStatus.hashCode() + 527) * 31 + this.zzck;
    }
}

