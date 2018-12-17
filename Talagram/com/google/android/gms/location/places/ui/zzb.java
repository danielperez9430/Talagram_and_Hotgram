package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

class zzb {
    public static final int RESULT_ERROR = 2;

    zzb() {
        super();
    }

    public static Place getPlace(Context arg1, Intent arg2) {
        Preconditions.checkNotNull(arg2, "intent must not be null");
        Preconditions.checkNotNull(arg1, "context must not be null");
        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "selected_place", PlaceEntity.CREATOR);
    }

    public static Status getStatus(Context arg1, Intent arg2) {
        Preconditions.checkNotNull(arg2, "intent must not be null");
        Preconditions.checkNotNull(arg1, "context must not be null");
        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "status", Status.CREATOR);
    }
}

