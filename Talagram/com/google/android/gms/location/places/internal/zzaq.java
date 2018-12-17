package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.Places;

public final class zzaq implements PlacePhotoMetadata {
    private final int index;
    private final int maxHeight;
    private final int maxWidth;
    private final String zzhd;
    private final CharSequence zzhe;

    public zzaq(String arg1, int arg2, int arg3, CharSequence arg4, int arg5) {
        super();
        this.zzhd = arg1;
        this.maxWidth = arg2;
        this.maxHeight = arg3;
        this.zzhe = arg4;
        this.index = arg5;
    }

    public final boolean equals(Object arg5) {
        if((((zzaq)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzaq)) {
            return 0;
        }

        if(((zzaq)arg5).maxWidth == this.maxWidth && ((zzaq)arg5).maxHeight == this.maxHeight && (Objects.equal(((zzaq)arg5).zzhd, this.zzhd)) && (Objects.equal(((zzaq)arg5).zzhe, this.zzhe))) {
            return 1;
        }

        return 0;
    }

    public final Object freeze() {
        return this;
    }

    public final CharSequence getAttributions() {
        return this.zzhe;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getMaxHeight() {
        return this.maxHeight;
    }

    public final int getMaxWidth() {
        return this.maxWidth;
    }

    public final PendingResult getPhoto(GoogleApiClient arg3) {
        return this.getScaledPhoto(arg3, this.getMaxWidth(), this.getMaxHeight());
    }

    public final PendingResult getScaledPhoto(GoogleApiClient arg2, int arg3, int arg4) {
        return Places.GeoDataApi.zzb(arg2, ((PlacePhotoMetadata)this), arg3, arg4);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.maxWidth), Integer.valueOf(this.maxHeight), this.zzhd, this.zzhe});
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final String zzah() {
        return this.zzhd;
    }
}

