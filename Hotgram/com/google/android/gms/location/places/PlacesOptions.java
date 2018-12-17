package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.common.internal.Objects;
import java.util.Locale;

public final class PlacesOptions implements Optional {
    public class Builder {
        private int zzfe;

        public Builder() {
            super();
            this.zzfe = 0;
        }

        public PlacesOptions build() {
            return new PlacesOptions(this, null);
        }
    }

    private final Locale locale;
    public final String zzcx;
    public final String zzfc;
    public final String zzfd;
    public final int zzfe;

    private PlacesOptions(Builder arg2) {
        super();
        this.zzfc = null;
        this.zzfd = null;
        this.zzfe = 0;
        this.zzcx = null;
        this.locale = null;
    }

    PlacesOptions(Builder arg1, zzn arg2) {
        this(arg1);
    }

    public final boolean equals(Object arg4) {
        if((arg4 instanceof PlacesOptions)) {
            arg4 = null;
            if((Objects.equal(arg4, arg4)) && (Objects.equal(arg4, arg4)) && (Objects.equal(Integer.valueOf(0), Integer.valueOf(0))) && (Objects.equal(arg4, arg4)) && (Objects.equal(arg4, arg4))) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{null, null, Integer.valueOf(0), null, null});
    }
}

