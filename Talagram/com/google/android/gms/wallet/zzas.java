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

@Class(creator="WebPaymentDataCreator") @Reserved(value={1}) public final class zzas extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzew;

    static {
        zzas.CREATOR = new zzat();
    }

    @Constructor zzas(@Param(id=2) String arg1) {
        super();
        this.zzew = arg1;
    }

    private zzas() {
        super();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzew, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

