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

@Class(creator="LabelValueCreator") @Reserved(value={1}) public final class LabelValue extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String label;
    @Field(id=3) private String value;

    static {
        LabelValue.CREATOR = new zzc();
    }

    @Constructor public LabelValue(@Param(id=2) String arg1, @Param(id=3) String arg2) {
        super();
        this.label = arg1;
        this.value = arg2;
    }

    LabelValue() {
        super();
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getValue() {
        return this.value;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.label, false);
        SafeParcelWriter.writeString(arg4, 3, this.value, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

