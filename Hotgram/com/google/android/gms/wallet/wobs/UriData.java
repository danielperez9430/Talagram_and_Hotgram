package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="UriDataCreator") @Reserved(value={1}) public final class UriData extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private String description;
    @Field(id=2) private String zzhf;

    static {
        UriData.CREATOR = new zzl();
    }

    @Constructor public UriData(@Param(id=2) String arg1, @Param(id=3) String arg2) {
        super();
        this.zzhf = arg1;
        this.description = arg2;
    }

    UriData() {
        super();
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getUri() {
        return this.zzhf;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzhf, false);
        SafeParcelWriter.writeString(arg4, 3, this.description, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

