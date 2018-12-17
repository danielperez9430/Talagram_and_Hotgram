package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GeoDataClient extends GoogleApi {
    @Retention(value=RetentionPolicy.SOURCE) @public interface BoundsMode {
        public static final int BIAS = 1;
        public static final int STRICT = 2;

    }

    GeoDataClient(Activity arg3, PlacesOptions arg4) {
        super(arg3, Places.GEO_DATA_API, ((ApiOptions)arg4), new ApiExceptionMapper());
    }

    GeoDataClient(Context arg3, PlacesOptions arg4) {
        super(arg3, Places.GEO_DATA_API, ((ApiOptions)arg4), new ApiExceptionMapper());
    }

    @Deprecated public Task addPlace(AddPlaceRequest arg3) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.addPlace(this.asGoogleApiClient(), arg3), new PlaceBufferResponse());
    }

    public Task getAutocompletePredictions(String arg8, LatLngBounds arg9, int arg10, AutocompleteFilter arg11) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.zzb(this.asGoogleApiClient(), arg8, arg9, arg10, arg11), new AutocompletePredictionBufferResponse());
    }

    public Task getAutocompletePredictions(String arg2, LatLngBounds arg3, AutocompleteFilter arg4) {
        return this.getAutocompletePredictions(arg2, arg3, 1, arg4);
    }

    public Task getPhoto(PlacePhotoMetadata arg3) {
        return this.getScaledPhoto(arg3, arg3.getMaxWidth(), arg3.getMaxHeight());
    }

    public Task getPlaceById(String[] arg3) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.getPlaceById(this.asGoogleApiClient(), arg3), new PlaceBufferResponse());
    }

    public Task getPlacePhotos(String arg3) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.getPlacePhotos(this.asGoogleApiClient(), arg3), new PlacePhotoMetadataResponse());
    }

    public Task getScaledPhoto(PlacePhotoMetadata arg3, int arg4, int arg5) {
        return PendingResultUtil.toResponseTask(Places.GeoDataApi.zzb(this.asGoogleApiClient(), arg3, arg4, arg5), new PlacePhotoResponse());
    }
}

