package com.google.android.gms.maps;

import android.graphics.Bitmap;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzbt;

final class zzr extends zzbt {
    zzr(GoogleMap arg1, SnapshotReadyCallback arg2) {
        this.zzz = arg2;
        super();
    }

    public final void onSnapshotReady(Bitmap arg2) {
        this.zzz.onSnapshotReady(arg2);
    }

    public final void zzb(IObjectWrapper arg2) {
        this.zzz.onSnapshotReady(ObjectWrapper.unwrap(arg2));
    }
}

