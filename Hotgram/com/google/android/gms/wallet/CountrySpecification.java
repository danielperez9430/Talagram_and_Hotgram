package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="CountrySpecificationCreator") @Reserved(value={1}) @Deprecated public class CountrySpecification extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzi;

    static {
        CountrySpecification.CREATOR = new zzh();
    }

    @Constructor public CountrySpecification(@Param(id=2) String arg1) {
        super();
        this.zzi = arg1;
    }

    public String getCountryCode() {
        return this.zzi;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

