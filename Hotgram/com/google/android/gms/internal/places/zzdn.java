package com.google.android.gms.internal.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.List;

@Class(creator="PlaceUserDataCreator") @Reserved(value={1000}) @Deprecated public final class zzdn extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getPlaceId", id=2) private final String placeId;
    @Field(getter="getUserAccountName", id=1) private final String zzcx;
    @Field(getter="getPlaceAliases", id=6) private final List zzhm;

    static {
        zzdn.CREATOR = new zzdp();
    }

    @Constructor zzdn(@Param(id=1) String arg1, @Param(id=2) String arg2, @Param(id=6) List arg3) {
        super();
        this.zzcx = arg1;
        this.placeId = arg2;
        this.zzhm = arg3;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzdn)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzdn)) {
            return 0;
        }

        if((this.zzcx.equals(((zzdn)arg5).zzcx)) && (this.placeId.equals(((zzdn)arg5).placeId)) && (this.zzhm.equals(((zzdn)arg5).zzhm))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzcx, this.placeId, this.zzhm});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("accountName", this.zzcx).add("placeId", this.placeId).add("placeAliases", this.zzhm).toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.zzcx, false);
        SafeParcelWriter.writeString(arg4, 2, this.placeId, false);
        SafeParcelWriter.writeTypedList(arg4, 6, this.zzhm, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

