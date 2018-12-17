package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="CustomVariableCreator") @Reserved(value={1}) public final class zzz extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getName", id=2) private final String mName;
    @Field(getter="getValue", id=3) private final String mValue;

    static {
        zzz.CREATOR = new zzaa();
    }

    @Constructor public zzz(@Param(id=2) String arg1, @Param(id=3) String arg2) {
        super();
        this.mName = arg1;
        this.mValue = arg2;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.mName, false);
        SafeParcelWriter.writeString(arg4, 3, this.mValue, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

