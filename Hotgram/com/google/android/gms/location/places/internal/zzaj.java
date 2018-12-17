package com.google.android.gms.location.places.internal;

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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

@Class(creator="PlaceLikelihoodEntityCreator") @Reserved(value={1000}) public final class zzaj extends AbstractSafeParcelable implements PlaceLikelihood {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private final PlaceEntity zzgs;
    @Field(id=2) private final float zzgt;

    static {
        zzaj.CREATOR = new zzak();
    }

    @Constructor zzaj(@Param(id=1) PlaceEntity arg1, @Param(id=2) float arg2) {
        super();
        this.zzgs = arg1;
        this.zzgt = arg2;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzaj)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzaj)) {
            return 0;
        }

        if((this.zzgs.equals(((zzaj)arg5).zzgs)) && this.zzgt == ((zzaj)arg5).zzgt) {
            return 1;
        }

        return 0;
    }

    public final Object freeze() {
        return this;
    }

    public final float getLikelihood() {
        return this.zzgt;
    }

    public final Place getPlace() {
        return this.zzgs;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzgs, Float.valueOf(this.zzgt)});
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("place", this.zzgs).add("likelihood", Float.valueOf(this.zzgt)).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.zzgs, arg6, false);
        SafeParcelWriter.writeFloat(arg5, 2, this.zzgt);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

