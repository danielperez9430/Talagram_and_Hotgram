package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
    private static ICameraUpdateFactoryDelegate zzf;

    private CameraUpdateFactory() {
        super();
    }

    public static CameraUpdate newCameraPosition(CameraPosition arg2) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newCameraPosition(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate newLatLng(LatLng arg2) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLng(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds arg2, int arg3) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLngBounds(arg2, arg3));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds arg2, int arg3, int arg4, int arg5) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLngBoundsWithSize(arg2, arg3, arg4, arg5));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate newLatLngZoom(LatLng arg2, float arg3) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().newLatLngZoom(arg2, arg3));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate scrollBy(float arg2, float arg3) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().scrollBy(arg2, arg3));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate zoomBy(float arg2) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().zoomBy(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static CameraUpdate zoomBy(float arg3, Point arg4) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().zoomByWithFocus(arg3, arg4.x, arg4.y));
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().zoomIn());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().zoomOut());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public static CameraUpdate zoomTo(float arg2) {
        try {
            return new CameraUpdate(CameraUpdateFactory.zzb().zoomTo(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static void zza(ICameraUpdateFactoryDelegate arg0) {
        CameraUpdateFactory.zzf = Preconditions.checkNotNull(arg0);
    }

    private static ICameraUpdateFactoryDelegate zzb() {
        return Preconditions.checkNotNull(CameraUpdateFactory.zzf, "CameraUpdateFactory is not initialized");
    }
}

