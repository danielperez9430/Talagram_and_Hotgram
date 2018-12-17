package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbh;

@VisibleForTesting public interface Geofence {
    @VisibleForTesting public final class Builder {
        private String zzad;
        private int zzae;
        private long zzaf;
        private short zzag;
        private double zzah;
        private double zzai;
        private float zzaj;
        private int zzak;
        private int zzal;

        public Builder() {
            super();
            this.zzad = null;
            this.zzae = 0;
            this.zzaf = -9223372036854775808L;
            this.zzag = -1;
            this.zzak = 0;
            this.zzal = -1;
        }

        public final Geofence build() {
            if(this.zzad != null) {
                if(this.zzae != 0) {
                    if((this.zzae & 4) != 0) {
                        if(this.zzal >= 0) {
                        }
                        else {
                            throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
                        }
                    }

                    if(this.zzaf != -9223372036854775808L) {
                        if(this.zzag != -1) {
                            if(this.zzak >= 0) {
                                return new zzbh(this.zzad, this.zzae, 1, this.zzah, this.zzai, this.zzaj, this.zzaf, this.zzak, this.zzal);
                            }

                            throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                        }

                        throw new IllegalArgumentException("Geofence region not set.");
                    }

                    throw new IllegalArgumentException("Expiration not set.");
                }

                throw new IllegalArgumentException("Transitions types not set.");
            }

            throw new IllegalArgumentException("Request ID not set.");
        }

        public final Builder setCircularRegion(double arg2, double arg4, float arg6) {
            this.zzag = 1;
            this.zzah = arg2;
            this.zzai = arg4;
            this.zzaj = arg6;
            return this;
        }

        public final Builder setExpirationDuration(long arg4) {
            this.zzaf = arg4 < 0 ? -1 : SystemClock.elapsedRealtime() + arg4;
            return this;
        }

        public final Builder setLoiteringDelay(int arg1) {
            this.zzal = arg1;
            return this;
        }

        public final Builder setNotificationResponsiveness(int arg1) {
            this.zzak = arg1;
            return this;
        }

        public final Builder setRequestId(String arg1) {
            this.zzad = arg1;
            return this;
        }

        public final Builder setTransitionTypes(int arg1) {
            this.zzae = arg1;
            return this;
        }
    }

    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    String getRequestId();
}

