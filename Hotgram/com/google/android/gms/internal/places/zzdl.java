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

@Class(creator="PlaceAliasCreator") @Reserved(value={1000}) @Deprecated public final class zzdl extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    private static final zzdl zzhj;
    private static final zzdl zzhk;
    @Field(getter="getAlias", id=1) private final String zzhl;

    static {
        zzdl.CREATOR = new zzdm();
        zzdl.zzhj = new zzdl("Home");
        zzdl.zzhk = new zzdl("Work");
    }

    @Constructor zzdl(@Param(id=1) String arg1) {
        super();
        this.zzhl = arg1;
    }

    public final boolean equals(Object arg2) {
        if(this == (((zzdl)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof zzdl)) {
            return 0;
        }

        return Objects.equal(this.zzhl, ((zzdl)arg2).zzhl);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzhl});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("alias", this.zzhl).toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.zzhl, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

