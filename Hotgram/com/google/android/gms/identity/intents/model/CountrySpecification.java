package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="CountrySpecificationCreator") @Reserved(value={1}) public class CountrySpecification extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzk;

    static {
        CountrySpecification.CREATOR = new zza();
    }

    @Constructor public CountrySpecification(@Param(id=2) String arg1) {
        super();
        this.zzk = arg1;
    }

    public String getCountryCode() {
        return this.zzk;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

