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

@Class(creator="TimeIntervalCreator") @Reserved(value={1}) public final class TimeInterval extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private long zzhd;
    @Field(id=3) private long zzhe;

    static {
        TimeInterval.CREATOR = new zzk();
    }

    @Constructor public TimeInterval(@Param(id=2) long arg1, @Param(id=3) long arg3) {
        super();
        this.zzhd = arg1;
        this.zzhe = arg3;
    }

    TimeInterval() {
        super();
    }

    public final long getEndTimestamp() {
        return this.zzhe;
    }

    public final long getStartTimestamp() {
        return this.zzhd;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeLong(arg4, 2, this.zzhd);
        SafeParcelWriter.writeLong(arg4, 3, this.zzhe);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

