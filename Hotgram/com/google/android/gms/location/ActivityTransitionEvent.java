package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="ActivityTransitionEventCreator") @Reserved(value={1000}) public class ActivityTransitionEvent extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getActivityType", id=1) private final int zzi;
    @Field(getter="getTransitionType", id=2) private final int zzj;
    @Field(getter="getElapsedRealTimeNanos", id=3) private final long zzk;

    static {
        ActivityTransitionEvent.CREATOR = new zzd();
    }

    @Constructor public ActivityTransitionEvent(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) long arg3) {
        super();
        DetectedActivity.zzb(arg1);
        ActivityTransition.zza(arg2);
        this.zzi = arg1;
        this.zzj = arg2;
        this.zzk = arg3;
    }

    public boolean equals(Object arg8) {
        if(this == (((ActivityTransitionEvent)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof ActivityTransitionEvent)) {
            return 0;
        }

        if(this.zzi == ((ActivityTransitionEvent)arg8).zzi && this.zzj == ((ActivityTransitionEvent)arg8).zzj && this.zzk == ((ActivityTransitionEvent)arg8).zzk) {
            return 1;
        }

        return 0;
    }

    public int getActivityType() {
        return this.zzi;
    }

    public long getElapsedRealTimeNanos() {
        return this.zzk;
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzi), Integer.valueOf(this.zzj), Long.valueOf(this.zzk)});
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        int v1 = this.zzi;
        StringBuilder v2 = new StringBuilder(24);
        v2.append("ActivityType ");
        v2.append(v1);
        v0.append(v2.toString());
        v0.append(" ");
        v1 = this.zzj;
        v2 = new StringBuilder(26);
        v2.append("TransitionType ");
        v2.append(v1);
        v0.append(v2.toString());
        v0.append(" ");
        long v1_1 = this.zzk;
        StringBuilder v3 = new StringBuilder(41);
        v3.append("ElapsedRealTimeNanos ");
        v3.append(v1_1);
        v0.append(v3.toString());
        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.getActivityType());
        SafeParcelWriter.writeInt(arg4, 2, this.getTransitionType());
        SafeParcelWriter.writeLong(arg4, 3, this.getElapsedRealTimeNanos());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

