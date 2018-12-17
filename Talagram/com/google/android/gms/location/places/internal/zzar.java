package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlacePhotoMetadata;

public final class zzar extends zzav implements PlacePhotoMetadata {
    private final String zzhd;

    public zzar(DataHolder arg1, int arg2) {
        super(arg1, arg2);
        this.zzhd = this.getString("photo_fife_url");
    }

    public final Object freeze() {
        return new zzaq(this.zzhd, this.getMaxWidth(), this.getMaxHeight(), this.getAttributions(), this.mDataRow);
    }

    public final CharSequence getAttributions() {
        return ((zzav)this).zzc("photo_attributions", null);
    }

    public final int getMaxHeight() {
        return ((zzav)this).zzc("photo_max_height", 0);
    }

    public final int getMaxWidth() {
        return ((zzav)this).zzc("photo_max_width", 0);
    }

    public final PendingResult getPhoto(GoogleApiClient arg3) {
        return this.getScaledPhoto(arg3, this.getMaxWidth(), this.getMaxHeight());
    }

    public final PendingResult getScaledPhoto(GoogleApiClient arg2, int arg3, int arg4) {
        return this.freeze().getScaledPhoto(arg2, arg3, arg4);
    }
}

