package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.location.places.internal.zzaa;
import com.google.android.gms.location.places.internal.zzaf;
import com.google.android.gms.location.places.internal.zzi;
import com.google.android.gms.location.places.internal.zzr;

public class Places {
    public static final Api GEO_DATA_API;
    public static final GeoDataApi GeoDataApi;
    public static final Api PLACE_DETECTION_API;
    public static final PlaceDetectionApi PlaceDetectionApi;
    private static final ClientKey zzev;
    private static final ClientKey zzew;

    static {
        Places.zzev = new ClientKey();
        Places.zzew = new ClientKey();
        Places.GEO_DATA_API = new Api("Places.GEO_DATA_API", new zzr(), Places.zzev);
        Places.PLACE_DETECTION_API = new Api("Places.PLACE_DETECTION_API", new zzaf(), Places.zzew);
        Places.GeoDataApi = new zzi();
        Places.PlaceDetectionApi = new zzaa();
    }

    private Places() {
        super();
    }

    public static GeoDataClient getGeoDataClient(Activity arg1) {
        return Places.getGeoDataClient(arg1, null);
    }

    @Deprecated public static GeoDataClient getGeoDataClient(Activity arg1, PlacesOptions arg2) {
        if(arg2 != null) {
        }
        else {
            arg2 = new Builder().build();
        }

        return new GeoDataClient(arg1, arg2);
    }

    public static GeoDataClient getGeoDataClient(Context arg1) {
        return Places.getGeoDataClient(arg1, null);
    }

    @Deprecated public static GeoDataClient getGeoDataClient(Context arg1, PlacesOptions arg2) {
        if(arg2 != null) {
        }
        else {
            arg2 = new Builder().build();
        }

        return new GeoDataClient(arg1, arg2);
    }

    public static PlaceDetectionClient getPlaceDetectionClient(Activity arg1) {
        return Places.getPlaceDetectionClient(arg1, null);
    }

    @Deprecated public static PlaceDetectionClient getPlaceDetectionClient(Activity arg1, PlacesOptions arg2) {
        if(arg2 != null) {
        }
        else {
            arg2 = new Builder().build();
        }

        return new PlaceDetectionClient(arg1, arg2);
    }

    public static PlaceDetectionClient getPlaceDetectionClient(Context arg1) {
        return Places.getPlaceDetectionClient(arg1, null);
    }

    @Deprecated public static PlaceDetectionClient getPlaceDetectionClient(Context arg1, PlacesOptions arg2) {
        if(arg2 != null) {
        }
        else {
            arg2 = new Builder().build();
        }

        return new PlaceDetectionClient(arg1, arg2);
    }
}

