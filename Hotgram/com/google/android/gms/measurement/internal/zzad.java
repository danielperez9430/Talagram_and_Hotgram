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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="EventParcelCreator") @Reserved(value={1}) public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final String name;
    @Field(id=4) public final String origin;
    @Field(id=3) public final zzaa zzaid;
    @Field(id=5) public final long zzaip;

    static {
        zzad.CREATOR = new zzae();
    }

    @Constructor public zzad(@Param(id=2) String arg1, @Param(id=3) zzaa arg2, @Param(id=4) String arg3, @Param(id=5) long arg4) {
        super();
        this.name = arg1;
        this.zzaid = arg2;
        this.origin = arg3;
        this.zzaip = arg4;
    }

    zzad(zzad arg2, long arg3) {
        super();
        Preconditions.checkNotNull(arg2);
        this.name = arg2.name;
        this.zzaid = arg2.zzaid;
        this.origin = arg2.origin;
        this.zzaip = arg3;
    }

    public final String toString() {
        String v0 = this.origin;
        String v1 = this.name;
        String v2 = String.valueOf(this.zzaid);
        StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 21 + String.valueOf(v1).length() + String.valueOf(v2).length());
        v4.append("origin=");
        v4.append(v0);
        v4.append(",name=");
        v4.append(v1);
        v4.append(",params=");
        v4.append(v2);
        return v4.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.name, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzaid, arg6, false);
        SafeParcelWriter.writeString(arg5, 4, this.origin, false);
        SafeParcelWriter.writeLong(arg5, 5, this.zzaip);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

