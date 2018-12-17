package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="NetworkLocationStatusCreator") public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="LocationAvailability.STATUS_UNKNOWN", id=2) private final int zzar;
    @Field(defaultValueUnchecked="LocationAvailability.STATUS_UNKNOWN", id=1) private final int zzas;
    @Field(defaultValueUnchecked="NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", id=4) private final long zzat;
    @Field(defaultValueUnchecked="NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", id=3) private final long zzbt;

    static {
        zzaj.CREATOR = new zzak();
    }

    @Constructor zzaj(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) long arg3, @Param(id=4) long arg5) {
        super();
        this.zzas = arg1;
        this.zzar = arg2;
        this.zzbt = arg3;
        this.zzat = arg5;
    }

    public final boolean equals(Object arg8) {
        if(this == (((zzaj)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.zzas == ((zzaj)arg8).zzas && this.zzar == ((zzaj)arg8).zzar && this.zzbt == ((zzaj)arg8).zzbt && this.zzat == ((zzaj)arg8).zzat) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), Long.valueOf(this.zzbt)});
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder("NetworkLocationStatus:");
        v0.append(" Wifi status: ");
        v0.append(this.zzas);
        v0.append(" Cell status: ");
        v0.append(this.zzar);
        v0.append(" elapsed time NS: ");
        v0.append(this.zzat);
        v0.append(" system time ms: ");
        v0.append(this.zzbt);
        return v0.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.zzas);
        SafeParcelWriter.writeInt(arg4, 2, this.zzar);
        SafeParcelWriter.writeLong(arg4, 3, this.zzbt);
        SafeParcelWriter.writeLong(arg4, 4, this.zzat);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

