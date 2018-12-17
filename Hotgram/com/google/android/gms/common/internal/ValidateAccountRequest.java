package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="ValidateAccountRequestCreator") @Deprecated public class ValidateAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;

    static {
        ValidateAccountRequest.CREATOR = new ValidateAccountRequestCreator();
    }

    @Constructor ValidateAccountRequest(@Param(id=1) int arg1) {
        super();
        this.zzal = arg1;
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.zzal);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

