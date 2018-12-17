package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="ConditionalUserPropertyParcelCreator") public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=6) public boolean active;
    @Field(id=5) public long creationTimestamp;
    @Field(id=3) public String origin;
    @Field(id=2) public String packageName;
    @Field(id=11) public long timeToLive;
    @Field(id=7) public String triggerEventName;
    @Field(id=9) public long triggerTimeout;
    @Field(id=4) public zzfh zzahb;
    @Field(id=8) public zzad zzahc;
    @Field(id=10) public zzad zzahd;
    @Field(id=12) public zzad zzahe;

    static {
        zzl.CREATOR = new zzm();
    }

    @Constructor zzl(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) zzfh arg3, @Param(id=5) long arg4, @Param(id=6) boolean arg6, @Param(id=7) String arg7, @Param(id=8) zzad arg8, @Param(id=9) long arg9, @Param(id=10) zzad arg11, @Param(id=11) long arg12, @Param(id=12) zzad arg14) {
        super();
        this.packageName = arg1;
        this.origin = arg2;
        this.zzahb = arg3;
        this.creationTimestamp = arg4;
        this.active = arg6;
        this.triggerEventName = arg7;
        this.zzahc = arg8;
        this.triggerTimeout = arg9;
        this.zzahd = arg11;
        this.timeToLive = arg12;
        this.zzahe = arg14;
    }

    zzl(zzl arg3) {
        super();
        Preconditions.checkNotNull(arg3);
        this.packageName = arg3.packageName;
        this.origin = arg3.origin;
        this.zzahb = arg3.zzahb;
        this.creationTimestamp = arg3.creationTimestamp;
        this.active = arg3.active;
        this.triggerEventName = arg3.triggerEventName;
        this.zzahc = arg3.zzahc;
        this.triggerTimeout = arg3.triggerTimeout;
        this.zzahd = arg3.zzahd;
        this.timeToLive = arg3.timeToLive;
        this.zzahe = arg3.zzahe;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeString(arg6, 2, this.packageName, false);
        SafeParcelWriter.writeString(arg6, 3, this.origin, false);
        SafeParcelWriter.writeParcelable(arg6, 4, this.zzahb, arg7, false);
        SafeParcelWriter.writeLong(arg6, 5, this.creationTimestamp);
        SafeParcelWriter.writeBoolean(arg6, 6, this.active);
        SafeParcelWriter.writeString(arg6, 7, this.triggerEventName, false);
        SafeParcelWriter.writeParcelable(arg6, 8, this.zzahc, arg7, false);
        SafeParcelWriter.writeLong(arg6, 9, this.triggerTimeout);
        SafeParcelWriter.writeParcelable(arg6, 10, this.zzahd, arg7, false);
        SafeParcelWriter.writeLong(arg6, 11, this.timeToLive);
        SafeParcelWriter.writeParcelable(arg6, 12, this.zzahe, arg7, false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }
}

