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

@Class(creator="UserAttributeParcelCreator") public final class zzfh extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final String name;
    @Field(id=7) public final String origin;
    @Field(id=1) private final int versionCode;
    @Field(id=6) private final String zzamp;
    @Field(id=3) public final long zzaue;
    @Field(id=4) private final Long zzauf;
    @Field(id=5) private final Float zzaug;
    @Field(id=8) private final Double zzauh;

    static {
        zzfh.CREATOR = new zzfi();
    }

    @Constructor zzfh(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) long arg3, @Param(id=4) Long arg5, @Param(id=5) Float arg6, @Param(id=6) String arg7, @Param(id=7) String arg8, @Param(id=8) Double arg9) {
        Double v2_1;
        super();
        this.versionCode = arg1;
        this.name = arg2;
        this.zzaue = arg3;
        this.zzauf = arg5;
        Float v2 = null;
        this.zzaug = v2;
        if(arg1 == 1) {
            if(arg6 != null) {
                v2_1 = Double.valueOf(arg6.doubleValue());
            }

            this.zzauh = v2_1;
        }
        else {
            this.zzauh = arg9;
        }

        this.zzamp = arg7;
        this.origin = arg8;
    }

    zzfh(String arg2, long arg3, Object arg5, String arg6) {
        super();
        Preconditions.checkNotEmpty(arg2);
        this.versionCode = 2;
        this.name = arg2;
        this.zzaue = arg3;
        this.origin = arg6;
        Long v2 = null;
        if(arg5 == null) {
            this.zzauf = v2;
            this.zzaug = ((Float)v2);
            this.zzauh = ((Double)v2);
            this.zzamp = ((String)v2);
            return;
        }

        if((arg5 instanceof Long)) {
            this.zzauf = ((Long)arg5);
            this.zzaug = ((Float)v2);
            this.zzauh = ((Double)v2);
            this.zzamp = ((String)v2);
            return;
        }

        if((arg5 instanceof String)) {
            this.zzauf = v2;
            this.zzaug = ((Float)v2);
            this.zzauh = ((Double)v2);
            this.zzamp = ((String)arg5);
            return;
        }

        if((arg5 instanceof Double)) {
            this.zzauf = v2;
            this.zzaug = ((Float)v2);
            this.zzauh = ((Double)arg5);
            this.zzamp = ((String)v2);
            return;
        }

        throw new IllegalArgumentException("User attribute given of un-supported type");
    }

    zzfh(zzfj arg7) {
        this(arg7.name, arg7.zzaue, arg7.value, arg7.origin);
    }

    public final Object getValue() {
        if(this.zzauf != null) {
            return this.zzauf;
        }

        if(this.zzauh != null) {
            return this.zzauh;
        }

        if(this.zzamp != null) {
            return this.zzamp;
        }

        return null;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.versionCode);
        SafeParcelWriter.writeString(arg5, 2, this.name, false);
        SafeParcelWriter.writeLong(arg5, 3, this.zzaue);
        SafeParcelWriter.writeLongObject(arg5, 4, this.zzauf, false);
        SafeParcelWriter.writeFloatObject(arg5, 5, null, false);
        SafeParcelWriter.writeString(arg5, 6, this.zzamp, false);
        SafeParcelWriter.writeString(arg5, 7, this.origin, false);
        SafeParcelWriter.writeDoubleObject(arg5, 8, this.zzauh, false);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

