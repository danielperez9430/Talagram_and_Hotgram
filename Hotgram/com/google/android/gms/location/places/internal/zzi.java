package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzi implements GeoDataApi {
    public zzi() {
        super();
    }

    public final PendingResult addPlace(GoogleApiClient arg3, AddPlaceRequest arg4) {
        Preconditions.checkNotNull(arg4, "userAddedPlace == null");
        return arg3.execute(new zzj(this, Places.GEO_DATA_API, arg3, arg4));
    }

    public final PendingResult getAutocompletePredictions(GoogleApiClient arg9, String arg10, LatLngBounds arg11, AutocompleteFilter arg12) {
        return arg9.enqueue(new zzo(this, Places.GEO_DATA_API, arg9, arg10, arg11, arg12));
    }

    public final PendingResult getPlaceById(GoogleApiClient arg8, String[] arg9) {
        boolean v2 = arg9 != null ? true : false;
        Preconditions.checkArgument(v2, "placeIds == null");
        v2 = arg9.length > 0 ? true : false;
        Preconditions.checkArgument(v2, "placeIds is empty");
        int v2_1 = arg9.length;
        int v3;
        for(v3 = 0; v3 < v2_1; ++v3) {
            String v4 = arg9[v3];
            boolean v5 = v4 != null ? true : false;
            Preconditions.checkArgument(v5, "placeId == null");
            Preconditions.checkArgument(v4.isEmpty() ^ 1, "placeId is empty");
        }

        return arg8.enqueue(new zzm(this, Places.GEO_DATA_API, arg8, arg9));
    }

    public final PendingResult getPlacePhotos(GoogleApiClient arg3, String arg4) {
        Preconditions.checkNotNull(arg4, "placeId == null");
        Preconditions.checkArgument(arg4.isEmpty() ^ 1, "placeId is empty");
        return arg3.enqueue(new zzk(this, Places.GEO_DATA_API, arg3, arg4));
    }

    public final PendingResult zzb(GoogleApiClient arg10, String arg11, LatLngBounds arg12, int arg13, AutocompleteFilter arg14) {
        return arg10.enqueue(new zzn(this, Places.GEO_DATA_API, arg10, arg11, arg12, arg13, arg14));
    }

    public final PendingResult zzb(GoogleApiClient arg9, PlacePhotoMetadata arg10, int arg11, int arg12) {
        Preconditions.checkNotNull(arg10, "photo == null");
        boolean v0 = false;
        boolean v2 = arg11 > 0 ? true : false;
        Preconditions.checkArgument(v2, "width <= 0");
        if(arg12 > 0) {
            v0 = true;
        }

        Preconditions.checkArgument(v0, "height <= 0");
        Object v10 = arg10.freeze();
        String v4 = ((zzaq)v10).zzah();
        int v7 = ((zzaq)v10).getIndex();
        Preconditions.checkNotNull(v4, "fifeUrl == null");
        return arg9.enqueue(new zzl(this, Places.GEO_DATA_API, arg9, v4, arg11, arg12, v7));
    }
}

