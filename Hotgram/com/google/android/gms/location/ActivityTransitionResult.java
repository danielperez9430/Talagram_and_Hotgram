package com.google.android.gms.location;

import android.content.Intent;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

@Class(creator="ActivityTransitionResultCreator") @Reserved(value={1000}) public class ActivityTransitionResult extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getTransitionEvents", id=1) private final List zzn;

    static {
        ActivityTransitionResult.CREATOR = new zzg();
    }

    @Constructor public ActivityTransitionResult(@Param(id=1) List arg8) {
        super();
        Preconditions.checkNotNull(arg8, "transitionEvents list can\'t be null.");
        if(!arg8.isEmpty()) {
            int v1;
            for(v1 = 1; v1 < arg8.size(); ++v1) {
                boolean v2 = arg8.get(v1).getElapsedRealTimeNanos() >= arg8.get(v1 - 1).getElapsedRealTimeNanos() ? true : false;
                Preconditions.checkArgument(v2);
            }
        }

        this.zzn = Collections.unmodifiableList(arg8);
    }

    public boolean equals(Object arg3) {
        if(this == (((ActivityTransitionResult)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.zzn.equals(((ActivityTransitionResult)arg3).zzn);
            }
        }

        return 0;
    }

    public static ActivityTransitionResult extractResult(Intent arg2) {
        if(!ActivityTransitionResult.hasResult(arg2)) {
            return null;
        }

        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT", ActivityTransitionResult.CREATOR);
    }

    public List getTransitionEvents() {
        return this.zzn;
    }

    public static boolean hasResult(Intent arg1) {
        if(arg1 == null) {
            return 0;
        }

        return arg1.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT");
    }

    public int hashCode() {
        return this.zzn.hashCode();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 1, this.getTransitionEvents(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

