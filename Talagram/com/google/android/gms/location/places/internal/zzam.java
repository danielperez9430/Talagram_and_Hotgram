package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Collections;
import java.util.List;

@Class(creator="PlaceOpeningHoursEntityCreator") @Reserved(value={1000}) public final class zzam extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private final List zzgu;
    @Field(id=2) private final List zzgv;

    static {
        zzam.CREATOR = new zzap();
    }

    @Constructor zzam(@Param(id=1) List arg1, @Param(id=2) List arg2) {
        super();
        this.zzgu = Collections.unmodifiableList(arg1);
        this.zzgv = Collections.unmodifiableList(arg2);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 1, this.zzgu, false);
        SafeParcelWriter.writeTypedList(arg4, 2, this.zzgv, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

