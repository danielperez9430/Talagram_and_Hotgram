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

@Class(creator="ExceptionalHoursCreator") @Reserved(value={1000}) public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=4) private final int endYear;
    @Field(id=1) private final int startYear;
    @Field(id=2) private final int zzgy;
    @Field(id=3) private final int zzgz;
    @Field(id=5) private final int zzha;
    @Field(id=6) private final int zzhb;
    @Field(id=7) private final List zzhc;

    static {
        zzao.CREATOR = new zzg();
    }

    @Constructor zzao(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) int arg3, @Param(id=4) int arg4, @Param(id=5) int arg5, @Param(id=6) int arg6, @Param(id=7) List arg7) {
        super();
        this.startYear = arg1;
        this.zzgy = arg2;
        this.zzgz = arg3;
        this.endYear = arg4;
        this.zzha = arg5;
        this.zzhb = arg6;
        this.zzhc = Collections.unmodifiableList(arg7);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.startYear);
        SafeParcelWriter.writeInt(arg4, 2, this.zzgy);
        SafeParcelWriter.writeInt(arg4, 3, this.zzgz);
        SafeParcelWriter.writeInt(arg4, 4, this.endYear);
        SafeParcelWriter.writeInt(arg4, 5, this.zzha);
        SafeParcelWriter.writeInt(arg4, 6, this.zzhb);
        SafeParcelWriter.writeTypedList(arg4, 7, this.zzhc, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

