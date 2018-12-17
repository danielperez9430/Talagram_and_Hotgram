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

@Class(creator="ProxyCardCreator") @Reserved(value={1}) @Deprecated public final class ProxyCard extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzej;
    @Field(id=3) private String zzek;
    @Field(id=4) private int zzel;
    @Field(id=5) private int zzem;

    static {
        ProxyCard.CREATOR = new zzal();
    }

    @Constructor public ProxyCard(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) int arg3, @Param(id=5) int arg4) {
        super();
        this.zzej = arg1;
        this.zzek = arg2;
        this.zzel = arg3;
        this.zzem = arg4;
    }

    public final String getCvn() {
        return this.zzek;
    }

    public final int getExpirationMonth() {
        return this.zzel;
    }

    public final int getExpirationYear() {
        return this.zzem;
    }

    public final String getPan() {
        return this.zzej;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzej, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzek, false);
        SafeParcelWriter.writeInt(arg4, 4, this.zzel);
        SafeParcelWriter.writeInt(arg4, 5, this.zzem);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

