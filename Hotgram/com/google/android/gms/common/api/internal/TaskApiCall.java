package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk public abstract class TaskApiCall {
    @KeepForSdk public class Builder {
        private Feature[] zzlz;
        private boolean zzma;
        private BiConsumer zzmb;

        Builder(zzce arg1) {
            this();
        }

        private Builder() {
            super();
            this.zzma = true;
        }

        @KeepForSdk public TaskApiCall build() {
            if(this.zzmb != null) {
                return new zzcf(this, this.zzlz, this.zzma);
            }

            throw new IllegalArgumentException("execute parameter required");
        }

        @KeepForSdk public Builder execute(BiConsumer arg1) {
            this.zzmb = arg1;
            return this;
        }

        @KeepForSdk public Builder setAutoResolveMissingFeatures(boolean arg1) {
            this.zzma = arg1;
            return this;
        }

        @KeepForSdk public Builder setFeatures(Feature[] arg1) {
            this.zzlz = arg1;
            return this;
        }

        static BiConsumer zza(Builder arg0) {
            return arg0.zzmb;
        }
    }

    private final Feature[] zzlz;
    private final boolean zzma;

    @KeepForSdk @Deprecated public TaskApiCall() {
        super();
        this.zzlz = null;
        this.zzma = false;
    }

    @KeepForSdk private TaskApiCall(Feature[] arg1, boolean arg2) {
        super();
        this.zzlz = arg1;
        this.zzma = arg2;
    }

    TaskApiCall(Feature[] arg1, boolean arg2, zzce arg3) {
        this(arg1, arg2);
    }

    @KeepForSdk public static Builder builder() {
        return new Builder(null);
    }

    @KeepForSdk protected abstract void doExecute(AnyClient arg1, TaskCompletionSource arg2);

    @KeepForSdk public boolean shouldAutoResolveMissingFeatures() {
        return this.zzma;
    }

    public final Feature[] zzca() {
        return this.zzlz;
    }
}

