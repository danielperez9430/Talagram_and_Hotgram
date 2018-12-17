package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="AppMetadataCreator") @Reserved(value={1, 20}) public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final String packageName;
    @Field(id=6) public final long zzadt;
    @Field(id=3) public final String zzafx;
    @Field(id=12) public final String zzafz;
    @Field(defaultValueUnchecked="Integer.MIN_VALUE", id=11) public final long zzagd;
    @Field(id=5) public final String zzage;
    @Field(id=7) public final long zzagf;
    @Field(defaultValue="true", id=9) public final boolean zzagg;
    @Field(id=13) public final long zzagh;
    @Field(defaultValue="true", id=16) public final boolean zzagi;
    @Field(defaultValue="true", id=17) public final boolean zzagj;
    @Field(id=19) public final String zzagk;
    @Field(id=8) public final String zzagv;
    @Field(id=10) public final boolean zzagw;
    @Field(id=14) public final long zzagx;
    @Field(id=15) public final int zzagy;
    @Field(id=18) public final boolean zzagz;
    @Field(id=4) public final String zzts;

    static {
        zzh.CREATOR = new zzi();
    }

    @Constructor zzh(@Param(id=2) String arg4, @Param(id=3) String arg5, @Param(id=4) String arg6, @Param(id=5) String arg7, @Param(id=6) long arg8, @Param(id=7) long arg10, @Param(id=8) String arg12, @Param(id=9) boolean arg13, @Param(id=10) boolean arg14, @Param(id=11) long arg15, @Param(id=12) String arg17, @Param(id=13) long arg18, @Param(id=14) long arg20, @Param(id=15) int arg22, @Param(id=16) boolean arg23, @Param(id=17) boolean arg24, @Param(id=18) boolean arg25, @Param(id=19) String arg26) {
        super();
        this.packageName = arg4;
        this.zzafx = arg5;
        this.zzts = arg6;
        this.zzagd = arg15;
        this.zzage = arg7;
        this.zzadt = arg8;
        this.zzagf = arg10;
        this.zzagv = arg12;
        this.zzagg = arg13;
        this.zzagw = arg14;
        this.zzafz = arg17;
        this.zzagh = arg18;
        this.zzagx = arg20;
        this.zzagy = arg22;
        this.zzagi = arg23;
        this.zzagj = arg24;
        this.zzagz = arg25;
        this.zzagk = arg26;
    }

    zzh(String arg4, String arg5, String arg6, long arg7, String arg9, long arg10, long arg12, String arg14, boolean arg15, boolean arg16, String arg17, long arg18, long arg20, int arg22, boolean arg23, boolean arg24, boolean arg25, String arg26) {
        zzh v0 = this;
        super();
        Preconditions.checkNotEmpty(arg4);
        v0.packageName = arg4;
        String v1 = TextUtils.isEmpty(((CharSequence)arg5)) ? null : arg5;
        v0.zzafx = v1;
        v0.zzts = arg6;
        v0.zzagd = arg7;
        v0.zzage = arg9;
        v0.zzadt = arg10;
        v0.zzagf = arg12;
        v0.zzagv = arg14;
        v0.zzagg = arg15;
        v0.zzagw = arg16;
        v0.zzafz = arg17;
        v0.zzagh = arg18;
        v0.zzagx = arg20;
        v0.zzagy = arg22;
        v0.zzagi = arg23;
        v0.zzagj = arg24;
        v0.zzagz = arg25;
        v0.zzagk = arg26;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.packageName, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzafx, false);
        SafeParcelWriter.writeString(arg5, 4, this.zzts, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzage, false);
        SafeParcelWriter.writeLong(arg5, 6, this.zzadt);
        SafeParcelWriter.writeLong(arg5, 7, this.zzagf);
        SafeParcelWriter.writeString(arg5, 8, this.zzagv, false);
        SafeParcelWriter.writeBoolean(arg5, 9, this.zzagg);
        SafeParcelWriter.writeBoolean(arg5, 10, this.zzagw);
        SafeParcelWriter.writeLong(arg5, 11, this.zzagd);
        SafeParcelWriter.writeString(arg5, 12, this.zzafz, false);
        SafeParcelWriter.writeLong(arg5, 13, this.zzagh);
        SafeParcelWriter.writeLong(arg5, 14, this.zzagx);
        SafeParcelWriter.writeInt(arg5, 15, this.zzagy);
        SafeParcelWriter.writeBoolean(arg5, 16, this.zzagi);
        SafeParcelWriter.writeBoolean(arg5, 17, this.zzagj);
        SafeParcelWriter.writeBoolean(arg5, 18, this.zzagz);
        SafeParcelWriter.writeString(arg5, 19, this.zzagk, false);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

