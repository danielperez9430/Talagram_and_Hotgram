package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Iterator;

@Class(creator="EventParamsCreator") @Reserved(value={1}) public final class zzaa extends AbstractSafeParcelable implements Iterable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="z", id=2) private final Bundle zzaim;

    static {
        zzaa.CREATOR = new zzac();
    }

    @Constructor zzaa(@Param(id=2) Bundle arg1) {
        super();
        this.zzaim = arg1;
    }

    final Object get(String arg2) {
        return this.zzaim.get(arg2);
    }

    final Long getLong(String arg3) {
        return Long.valueOf(this.zzaim.getLong(arg3));
    }

    final String getString(String arg2) {
        return this.zzaim.getString(arg2);
    }

    public final Iterator iterator() {
        return new zzab(this);
    }

    public final int size() {
        return this.zzaim.size();
    }

    public final String toString() {
        return this.zzaim.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBundle(arg4, 2, this.zziv(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    static Bundle zza(zzaa arg0) {
        return arg0.zzaim;
    }

    final Double zzbq(String arg3) {
        return Double.valueOf(this.zzaim.getDouble(arg3));
    }

    public final Bundle zziv() {
        return new Bundle(this.zzaim);
    }
}

