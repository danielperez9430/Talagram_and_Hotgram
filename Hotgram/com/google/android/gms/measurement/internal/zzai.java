package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzs;
import java.util.ArrayList;
import java.util.List;

public final class zzai extends zzq implements zzag {
    zzai(IBinder arg2) {
        super(arg2, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final List zza(zzh arg2, boolean arg3) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        zzs.writeBoolean(v0, arg3);
        Parcel v2 = ((zzq)this).transactAndReadException(7, v0);
        ArrayList v3 = v2.createTypedArrayList(zzfh.CREATOR);
        v2.recycle();
        return ((List)v3);
    }

    public final List zza(String arg2, String arg3, zzh arg4) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeString(arg3);
        zzs.zza(v0, ((Parcelable)arg4));
        Parcel v2 = ((zzq)this).transactAndReadException(16, v0);
        ArrayList v3 = v2.createTypedArrayList(zzl.CREATOR);
        v2.recycle();
        return ((List)v3);
    }

    public final List zza(String arg2, String arg3, String arg4, boolean arg5) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeString(arg3);
        v0.writeString(arg4);
        zzs.writeBoolean(v0, arg5);
        Parcel v2 = ((zzq)this).transactAndReadException(15, v0);
        ArrayList v3 = v2.createTypedArrayList(zzfh.CREATOR);
        v2.recycle();
        return ((List)v3);
    }

    public final List zza(String arg2, String arg3, boolean arg4, zzh arg5) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeString(arg3);
        zzs.writeBoolean(v0, arg4);
        zzs.zza(v0, ((Parcelable)arg5));
        Parcel v2 = ((zzq)this).transactAndReadException(14, v0);
        ArrayList v3 = v2.createTypedArrayList(zzfh.CREATOR);
        v2.recycle();
        return ((List)v3);
    }

    public final void zza(long arg2, String arg4, String arg5, String arg6) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        v0.writeLong(arg2);
        v0.writeString(arg4);
        v0.writeString(arg5);
        v0.writeString(arg6);
        ((zzq)this).transactAndReadExceptionReturnVoid(10, v0);
    }

    public final void zza(zzad arg2, zzh arg3) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        zzs.zza(v0, ((Parcelable)arg3));
        ((zzq)this).transactAndReadExceptionReturnVoid(1, v0);
    }

    public final void zza(zzad arg2, String arg3, String arg4) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        v0.writeString(arg3);
        v0.writeString(arg4);
        ((zzq)this).transactAndReadExceptionReturnVoid(5, v0);
    }

    public final void zza(zzfh arg2, zzh arg3) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        zzs.zza(v0, ((Parcelable)arg3));
        ((zzq)this).transactAndReadExceptionReturnVoid(2, v0);
    }

    public final void zza(zzh arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        ((zzq)this).transactAndReadExceptionReturnVoid(4, v0);
    }

    public final void zza(zzl arg2, zzh arg3) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        zzs.zza(v0, ((Parcelable)arg3));
        ((zzq)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final byte[] zza(zzad arg2, String arg3) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        v0.writeString(arg3);
        Parcel v2 = ((zzq)this).transactAndReadException(9, v0);
        byte[] v3 = v2.createByteArray();
        v2.recycle();
        return v3;
    }

    public final void zzb(zzh arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        ((zzq)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final void zzb(zzl arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        ((zzq)this).transactAndReadExceptionReturnVoid(13, v0);
    }

    public final String zzc(zzh arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        Parcel v2 = ((zzq)this).transactAndReadException(11, v0);
        String v0_1 = v2.readString();
        v2.recycle();
        return v0_1;
    }

    public final void zzd(zzh arg2) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        zzs.zza(v0, ((Parcelable)arg2));
        ((zzq)this).transactAndReadExceptionReturnVoid(18, v0);
    }

    public final List zze(String arg2, String arg3, String arg4) {
        Parcel v0 = ((zzq)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeString(arg3);
        v0.writeString(arg4);
        Parcel v2 = ((zzq)this).transactAndReadException(17, v0);
        ArrayList v3 = v2.createTypedArrayList(zzl.CREATOR);
        v2.recycle();
        return ((List)v3);
    }
}

