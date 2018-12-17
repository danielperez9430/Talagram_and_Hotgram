package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzg extends zza implements zze {
    zzg(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    public final IObjectWrapper zza(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(5, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zza(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zza(Bitmap arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(6, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zza(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(2, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zzb(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(3, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zzc(String arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(7, v0);
        IObjectWrapper v0_1 = Stub.asInterface(v2.readStrongBinder());
        v2.recycle();
        return v0_1;
    }

    public final IObjectWrapper zzh() {
        Parcel v0 = ((zza)this).transactAndReadException(4, ((zza)this).obtainAndWriteInterfaceToken());
        IObjectWrapper v1 = Stub.asInterface(v0.readStrongBinder());
        v0.recycle();
        return v1;
    }
}

