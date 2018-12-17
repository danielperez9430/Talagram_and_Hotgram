package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator="ActivityTransitionCreator") @Reserved(value={1000}) public class ActivityTransition extends AbstractSafeParcelable {
    public class Builder {
        private int zzi;
        private int zzj;

        public Builder() {
            super();
            this.zzi = -1;
            this.zzj = -1;
        }

        public ActivityTransition build() {
            boolean v1 = false;
            int v3 = -1;
            boolean v0 = this.zzi != v3 ? true : false;
            Preconditions.checkState(v0, "Activity type not set.");
            if(this.zzj != v3) {
                v1 = true;
            }

            Preconditions.checkState(v1, "Activity transition type not set.");
            return new ActivityTransition(this.zzi, this.zzj);
        }

        public Builder setActivityTransition(int arg1) {
            ActivityTransition.zza(arg1);
            this.zzj = arg1;
            return this;
        }

        public Builder setActivityType(int arg1) {
            DetectedActivity.zzb(arg1);
            this.zzi = arg1;
            return this;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface SupportedActivityTransition {
    }

    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getActivityType", id=1) private final int zzi;
    @Field(getter="getTransitionType", id=2) private final int zzj;

    static {
        ActivityTransition.CREATOR = new zzc();
    }

    @Constructor ActivityTransition(@Param(id=1) int arg1, @Param(id=2) int arg2) {
        super();
        this.zzi = arg1;
        this.zzj = arg2;
    }

    public boolean equals(Object arg5) {
        if(this == (((ActivityTransition)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof ActivityTransition)) {
            return 0;
        }

        if(this.zzi == ((ActivityTransition)arg5).zzi && this.zzj == ((ActivityTransition)arg5).zzj) {
            return 1;
        }

        return 0;
    }

    public int getActivityType() {
        return this.zzi;
    }

    public int getTransitionType() {
        return this.zzj;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzi), Integer.valueOf(this.zzj)});
    }

    public String toString() {
        int v0 = this.zzi;
        int v1 = this.zzj;
        StringBuilder v2 = new StringBuilder(75);
        v2.append("ActivityTransition [mActivityType=");
        v2.append(v0);
        v2.append(", mTransitionType=");
        v2.append(v1);
        v2.append(']');
        return v2.toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.getActivityType());
        SafeParcelWriter.writeInt(arg3, 2, this.getTransitionType());
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }

    public static void zza(int arg3) {
        boolean v0 = true;
        if(arg3 < 0 || arg3 > 1) {
            v0 = false;
        }
        else {
        }

        StringBuilder v2 = new StringBuilder(41);
        v2.append("Transition type ");
        v2.append(arg3);
        v2.append(" is not valid.");
        Preconditions.checkArgument(v0, v2.toString());
    }
}

