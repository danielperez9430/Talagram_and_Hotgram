package com.google.android.gms.location.places.ui;

import android.view.View$OnClickListener;
import android.view.View;

final class zzd implements View$OnClickListener {
    zzd(PlaceAutocompleteFragment arg1) {
        this.zzhu = arg1;
        super();
    }

    public final void onClick(View arg1) {
        if(!PlaceAutocompleteFragment.zzb(this.zzhu)) {
            PlaceAutocompleteFragment.zzc(this.zzhu);
        }
    }
}

