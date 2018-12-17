package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzf extends zza implements zze {
    zzf(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.internal.ICreator");
    }

    public final IMapViewDelegate zza(IObjectWrapper arg3, GoogleMapOptions arg4) {
        IInterface v4_2;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v3 = ((zza)this).transactAndReadException(3, v0);
        IBinder v4 = v3.readStrongBinder();
        if(v4 == null) {
            IMapViewDelegate v4_1 = null;
        }
        else {
            IInterface v0_1 = v4.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            if((v0_1 instanceof IMapViewDelegate)) {
                v4_2 = v0_1;
            }
            else {
                zzk v4_3 = new zzk(v4);
            }
        }

        v3.recycle();
        return ((IMapViewDelegate)v4_2);
    }

    public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper arg3, StreetViewPanoramaOptions arg4) {
        IStreetViewPanoramaViewDelegate v4_1;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v3 = ((zza)this).transactAndReadException(7, v0);
        IBinder v4 = v3.readStrongBinder();
        if(v4 == null) {
            v4_1 = null;
        }
        else {
            IInterface v0_1 = v4.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            if((v0_1 instanceof IStreetViewPanoramaViewDelegate)) {
                IInterface v4_2 = v0_1;
            }
            else {
                zzbw v4_3 = new zzbw(v4);
            }
        }

        v3.recycle();
        return v4_1;
    }

    public final void zza(IObjectWrapper arg2, int arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeInt(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final IMapFragmentDelegate zzc(IObjectWrapper arg4) {
        zzj v0_4;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg4));
        Parcel v4 = ((zza)this).transactAndReadException(2, v0);
        IBinder v0_1 = v4.readStrongBinder();
        if(v0_1 == null) {
            IMapFragmentDelegate v0_2 = null;
        }
        else {
            IInterface v1 = v0_1.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            if((v1 instanceof IMapFragmentDelegate)) {
                IInterface v0_3 = v1;
            }
            else {
                v0_4 = new zzj(v0_1);
            }
        }

        v4.recycle();
        return ((IMapFragmentDelegate)v0_4);
    }

    public final ICameraUpdateFactoryDelegate zzd() {
        IInterface v1_2;
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        IBinder v1 = v0.readStrongBinder();
        if(v1 == null) {
            ICameraUpdateFactoryDelegate v1_1 = null;
        }
        else {
            IInterface v2 = v1.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            if((v2 instanceof ICameraUpdateFactoryDelegate)) {
                v1_2 = v2;
            }
            else {
                zzb v1_3 = new zzb(v1);
            }
        }

        v0.recycle();
        return ((ICameraUpdateFactoryDelegate)v1_2);
    }

    public final IStreetViewPanoramaFragmentDelegate zzd(IObjectWrapper arg4) {
        IInterface v0_3;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg4));
        Parcel v4 = ((zza)this).transactAndReadException(8, v0);
        IBinder v0_1 = v4.readStrongBinder();
        if(v0_1 == null) {
            IStreetViewPanoramaFragmentDelegate v0_2 = null;
        }
        else {
            IInterface v1 = v0_1.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
            if((v1 instanceof IStreetViewPanoramaFragmentDelegate)) {
                v0_3 = v1;
            }
            else {
                zzbv v0_4 = new zzbv(v0_1);
            }
        }

        v4.recycle();
        return ((IStreetViewPanoramaFragmentDelegate)v0_3);
    }

    public final com.google.android.gms.internal.maps.zze zze() {
        Parcel v0 = ((zza)this).transactAndReadException(5, ((zza)this).obtainAndWriteInterfaceToken());
        com.google.android.gms.internal.maps.zze v1 = com.google.android.gms.internal.maps.zzf.zzb(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }
}

