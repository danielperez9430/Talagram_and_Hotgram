package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

@Class(creator="GetAllCapabilitiesResponseCreator") @Reserved(value={1}) public final class zzdi extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final int statusCode;
    @Field(id=3) @VisibleForTesting public final List zzdp;

    static {
        zzdi.CREATOR = new zzdj();
    }

    @Constructor public zzdi(@Param(id=2) int arg1, @Param(id=3) List arg2) {
        super();
        this.statusCode = arg1;
        this.zzdp = arg2;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.statusCode);
        SafeParcelWriter.writeTypedList(arg4, 3, this.zzdp, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

