package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zze;

public final class BitmapDescriptorFactory {
    public static final float HUE_AZURE = 0f;
    public static final float HUE_BLUE = 0f;
    public static final float HUE_CYAN = 0f;
    public static final float HUE_GREEN = 0f;
    public static final float HUE_MAGENTA = 0f;
    public static final float HUE_ORANGE = 0f;
    public static final float HUE_RED = 0f;
    public static final float HUE_ROSE = 0f;
    public static final float HUE_VIOLET = 0f;
    public static final float HUE_YELLOW = 0f;
    private static zze zzcl;

    private BitmapDescriptorFactory() {
        super();
    }

    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zzh());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public static BitmapDescriptor defaultMarker(float arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zza(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static BitmapDescriptor fromAsset(String arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zza(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zza(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static BitmapDescriptor fromFile(String arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zzb(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static BitmapDescriptor fromPath(String arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zzc(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static BitmapDescriptor fromResource(int arg2) {
        try {
            return new BitmapDescriptor(BitmapDescriptorFactory.zzf().zza(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public static void zza(zze arg1) {
        if(BitmapDescriptorFactory.zzcl != null) {
            return;
        }

        BitmapDescriptorFactory.zzcl = Preconditions.checkNotNull(arg1);
    }

    private static zze zzf() {
        return Preconditions.checkNotNull(BitmapDescriptorFactory.zzcl, "IBitmapDescriptorFactory is not initialized");
    }
}

