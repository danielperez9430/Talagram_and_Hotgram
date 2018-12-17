package com.google.android.gms.common.images.internal;

import android.support.v4.f.g;
import com.google.android.gms.common.internal.Objects;

public final class PostProcessedResourceCache extends g {
    public final class PostProcessedResource {
        public final int postProcessingFlags;
        public final int resId;

        public PostProcessedResource(int arg1, int arg2) {
            super();
            this.resId = arg1;
            this.postProcessingFlags = arg2;
        }

        public final boolean equals(Object arg5) {
            if(!(arg5 instanceof PostProcessedResource)) {
                return 0;
            }

            if(this == (((PostProcessedResource)arg5))) {
                return 1;
            }

            if(((PostProcessedResource)arg5).resId == this.resId && ((PostProcessedResource)arg5).postProcessingFlags == this.postProcessingFlags) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{Integer.valueOf(this.resId), Integer.valueOf(this.postProcessingFlags)});
        }
    }

    public PostProcessedResourceCache() {
        super(10);
    }
}

