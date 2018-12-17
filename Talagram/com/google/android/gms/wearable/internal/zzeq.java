package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.internal.wearable.zza;
import com.google.android.gms.internal.wearable.zzc;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzeq extends zza implements zzep {
    zzeq(IBinder arg2) {
        super(arg2, "com.google.android.gms.wearable.internal.IWearableService");
    }

    public final void zza(zzek arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(8, v0);
    }

    public final void zza(zzek arg2, int arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeInt(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(43, v0);
    }

    public final void zza(zzek arg2, Uri arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(7, v0);
    }

    public final void zza(zzek arg2, Uri arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        v0.writeInt(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(40, v0);
    }

    public final void zza(zzek arg2, Asset arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final void zza(zzek arg2, PutDataRequest arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final void zza(zzek arg2, zzd arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(16, v0);
    }

    public final void zza(zzek arg2, zzei arg3, String arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        v0.writeString(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(34, v0);
    }

    public final void zza(zzek arg2, zzfw arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactAndReadExceptionReturnVoid(17, v0);
    }

    public final void zza(zzek arg2, String arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(46, v0);
    }

    public final void zza(zzek arg2, String arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        v0.writeInt(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(42, v0);
    }

    public final void zza(zzek arg2, String arg3, ParcelFileDescriptor arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        zzc.zza(v0, ((Parcelable)arg4));
        ((zza)this).transactAndReadExceptionReturnVoid(38, v0);
    }

    public final void zza(zzek arg2, String arg3, ParcelFileDescriptor arg4, long arg5, long arg7) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        zzc.zza(v0, ((Parcelable)arg4));
        v0.writeLong(arg5);
        v0.writeLong(arg7);
        ((zza)this).transactAndReadExceptionReturnVoid(39, v0);
    }

    public final void zza(zzek arg2, String arg3, String arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        v0.writeString(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(31, v0);
    }

    public final void zza(zzek arg2, String arg3, String arg4, byte[] arg5) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        v0.writeString(arg4);
        v0.writeByteArray(arg5);
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final void zzb(zzek arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(14, v0);
    }

    public final void zzb(zzek arg2, Uri arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        v0.writeInt(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(41, v0);
    }

    public final void zzb(zzek arg2, zzei arg3, String arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        v0.writeString(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(35, v0);
    }

    public final void zzb(zzek arg2, String arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(47, v0);
    }

    public final void zzb(zzek arg2, String arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        v0.writeInt(arg4);
        ((zza)this).transactAndReadExceptionReturnVoid(33, v0);
    }

    public final void zzc(zzek arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        ((zza)this).transactAndReadExceptionReturnVoid(15, v0);
    }

    public final void zzc(zzek arg2, String arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        v0.writeString(arg3);
        ((zza)this).transactAndReadExceptionReturnVoid(32, v0);
    }
}

