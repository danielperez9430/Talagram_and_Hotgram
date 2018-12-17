package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public interface zzu extends IInterface {
    void zzb(AddPlaceRequest arg1, zzat arg2, zzy arg3);

    void zzb(String arg1, int arg2, int arg3, int arg4, zzat arg5, zzw arg6);

    void zzb(String arg1, zzat arg2, zzw arg3);

    void zzb(String arg1, LatLngBounds arg2, int arg3, AutocompleteFilter arg4, zzat arg5, zzy arg6);

    void zzb(List arg1, zzat arg2, zzy arg3);
}

