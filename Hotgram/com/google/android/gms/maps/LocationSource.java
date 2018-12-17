package com.google.android.gms.maps;

import android.location.Location;

public interface LocationSource {
    public interface OnLocationChangedListener {
        void onLocationChanged(Location arg1);
    }

    void activate(OnLocationChangedListener arg1);

    void deactivate();
}

