package com.google.android.gms.internal.vision;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="RecognitionOptionsCreator") @Reserved(value={1}) public final class zzz extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final Rect zzdr;

    static {
        zzz.CREATOR = new zzaa();
    }

    @Constructor public zzz(@Param(id=2) Rect arg1) {
        super();
        this.zzdr = arg1;
    }

    public zzz() {
        super();
        this.zzdr = new Rect();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzdr, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

