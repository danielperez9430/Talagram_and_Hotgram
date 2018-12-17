package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;

public class LocationSettingsResponse extends Response {
    public LocationSettingsResponse() {
        super();
    }

    public LocationSettingsStates getLocationSettingsStates() {
        return this.getResult().getLocationSettingsStates();
    }
}

