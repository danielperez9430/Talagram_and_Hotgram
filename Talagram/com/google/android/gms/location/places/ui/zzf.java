package com.google.android.gms.location.places.ui;

import android.view.View$OnClickListener;
import android.view.View;

final class zzf implements View$OnClickListener {
    zzf(SupportPlaceAutocompleteFragment arg1) {
        this.zzhv = arg1;
        super();
    }

    public final void onClick(View arg1) {
        if(!SupportPlaceAutocompleteFragment.zzb(this.zzhv)) {
            SupportPlaceAutocompleteFragment.zzc(this.zzhv);
        }
    }
}

