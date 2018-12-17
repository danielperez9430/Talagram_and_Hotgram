package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="DeviceOrientationRequestCreator") public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id=1) private boolean zzt;
    @Field(defaultValueUnchecked="DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id=2) private long zzu;
    @Field(defaultValueUnchecked="DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id=3) private float zzv;
    @Field(defaultValueUnchecked="DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id=4) private long zzw;
    @Field(defaultValueUnchecked="DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id=5) private int zzx;

    static {
        zzj.CREATOR = new zzk();
    }

    @Constructor zzj(@Param(id=1) boolean arg1, @Param(id=2) long arg2, @Param(id=3) float arg4, @Param(id=4) long arg5, @Param(id=5) int arg7) {
        super();
        this.zzt = arg1;
        this.zzu = arg2;
        this.zzv = arg4;
        this.zzw = arg5;
        this.zzx = arg7;
    }

    public zzj() {
        this(true, 50, 0f, 9223372036854775807L, 2147483647);
    }

    public final boolean equals(Object arg8) {
        if(this == (((zzj)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof zzj)) {
            return 0;
        }

        if(this.zzt == ((zzj)arg8).zzt && this.zzu == ((zzj)arg8).zzu && Float.compare(this.zzv, ((zzj)arg8).zzv) == 0 && this.zzw == ((zzj)arg8).zzw && this.zzx == ((zzj)arg8).zzx) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zzt), Long.valueOf(this.zzu), Float.valueOf(this.zzv), Long.valueOf(this.zzw), Integer.valueOf(this.zzx)});
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("DeviceOrientationRequest[mShouldUseMag=");
        v0.append(this.zzt);
        v0.append(" mMinimumSamplingPeriodMs=");
        v0.append(this.zzu);
        v0.append(" mSmallestAngleChangeRadians=");
        v0.append(this.zzv);
        if(this.zzw != 9223372036854775807L) {
            long v1 = this.zzw - SystemClock.elapsedRealtime();
            v0.append(" expireIn=");
            v0.append(v1);
            v0.append("ms");
        }

        if(this.zzx != 2147483647) {
            v0.append(" num=");
            v0.append(this.zzx);
        }

        v0.append(']');
        return v0.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBoolean(arg4, 1, this.zzt);
        SafeParcelWriter.writeLong(arg4, 2, this.zzu);
        SafeParcelWriter.writeFloat(arg4, 3, this.zzv);
        SafeParcelWriter.writeLong(arg4, 4, this.zzw);
        SafeParcelWriter.writeInt(arg4, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

