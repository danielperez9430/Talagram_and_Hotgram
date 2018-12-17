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

@Class(creator="TextModuleDataCreator") @Reserved(value={1}) public final class TextModuleData extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzhb;
    @Field(id=3) private String zzhc;

    static {
        TextModuleData.CREATOR = new zzj();
    }

    @Constructor public TextModuleData(@Param(id=2) String arg1, @Param(id=3) String arg2) {
        super();
        this.zzhb = arg1;
        this.zzhc = arg2;
    }

    TextModuleData() {
        super();
    }

    public final String getBody() {
        return this.zzhc;
    }

    public final String getHeader() {
        return this.zzhb;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzhb, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzhc, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

