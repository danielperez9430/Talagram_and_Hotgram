package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

@Class(creator="AutocompletePredictionEntityCreator") @Reserved(value={1000}) public final class zzb extends AbstractSafeParcelable implements AutocompletePrediction {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final String placeId;
    @Field(id=3) private final List zzdq;
    private static final List zzfk;
    @Field(id=1) private final String zzfl;
    @Field(id=4) private final List zzfm;
    @Field(id=5) private final int zzfn;
    @Field(id=6) private final String zzfo;
    @Field(id=7) private final List zzfp;
    @Field(id=8) private final String zzfq;
    @Field(id=9) private final List zzfr;

    static {
        zzb.CREATOR = new zzd();
        zzb.zzfk = Collections.emptyList();
    }

    @Constructor zzb(@Param(id=2) String arg1, @Param(id=3) List arg2, @Param(id=5) int arg3, @Param(id=1) String arg4, @Param(id=4) List arg5, @Param(id=6) String arg6, @Param(id=7) List arg7, @Param(id=8) String arg8, @Param(id=9) List arg9) {
        super();
        this.placeId = arg1;
        this.zzdq = arg2;
        this.zzfn = arg3;
        this.zzfl = arg4;
        this.zzfm = arg5;
        this.zzfo = arg6;
        this.zzfp = arg7;
        this.zzfq = arg8;
        this.zzfr = arg9;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzb)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzb)) {
            return 0;
        }

        if((Objects.equal(this.placeId, ((zzb)arg5).placeId)) && (Objects.equal(this.zzdq, ((zzb)arg5).zzdq)) && (Objects.equal(Integer.valueOf(this.zzfn), Integer.valueOf(((zzb)arg5).zzfn))) && (Objects.equal(this.zzfl, ((zzb)arg5).zzfl)) && (Objects.equal(this.zzfm, ((zzb)arg5).zzfm)) && (Objects.equal(this.zzfo, ((zzb)arg5).zzfo)) && (Objects.equal(this.zzfp, ((zzb)arg5).zzfp)) && (Objects.equal(this.zzfq, ((zzb)arg5).zzfq)) && (Objects.equal(this.zzfr, ((zzb)arg5).zzfr))) {
            return 1;
        }

        return 0;
    }

    public final Object freeze() {
        return this;
    }

    public final CharSequence getFullText(CharacterStyle arg3) {
        return zzh.zzb(this.zzfl, this.zzfm, arg3);
    }

    public final String getPlaceId() {
        return this.placeId;
    }

    public final List getPlaceTypes() {
        return this.zzdq;
    }

    public final CharSequence getPrimaryText(CharacterStyle arg3) {
        return zzh.zzb(this.zzfo, this.zzfp, arg3);
    }

    public final CharSequence getSecondaryText(CharacterStyle arg3) {
        return zzh.zzb(this.zzfq, this.zzfr, arg3);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.placeId, this.zzdq, Integer.valueOf(this.zzfn), this.zzfl, this.zzfm, this.zzfo, this.zzfp, this.zzfq, this.zzfr});
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("placeId", this.placeId).add("placeTypes", this.zzdq).add("fullText", this.zzfl).add("fullTextMatchedSubstrings", this.zzfm).add("primaryText", this.zzfo).add("primaryTextMatchedSubstrings", this.zzfp).add("secondaryText", this.zzfq).add("secondaryTextMatchedSubstrings", this.zzfr).toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.zzfl, false);
        SafeParcelWriter.writeString(arg4, 2, this.placeId, false);
        SafeParcelWriter.writeIntegerList(arg4, 3, this.zzdq, false);
        SafeParcelWriter.writeTypedList(arg4, 4, this.zzfm, false);
        SafeParcelWriter.writeInt(arg4, 5, this.zzfn);
        SafeParcelWriter.writeString(arg4, 6, this.zzfo, false);
        SafeParcelWriter.writeTypedList(arg4, 7, this.zzfp, false);
        SafeParcelWriter.writeString(arg4, 8, this.zzfq, false);
        SafeParcelWriter.writeTypedList(arg4, 9, this.zzfr, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

