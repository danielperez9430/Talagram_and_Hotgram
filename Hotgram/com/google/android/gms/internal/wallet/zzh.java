package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="GetBuyFlowInitializationTokenResponseCreator") @Reserved(value={1}) public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private byte[] zzex;

    static {
        zzh.CREATOR = new zzi();
    }

    @Constructor public zzh(@Param(id=2) byte[] arg1) {
        super();
        this.zzex = arg1;
    }

    zzh() {
        this(new byte[0]);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeByteArray(arg4, 2, this.zzex, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

