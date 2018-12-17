package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Class(creator="ActivityRecognitionResultCreator") @Reserved(value={1000}) public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=5) private Bundle extras;
    @Field(id=1) private List zze;
    @Field(id=2) private long zzf;
    @Field(id=3) private long zzg;
    @Field(id=4) private int zzh;

    static {
        ActivityRecognitionResult.CREATOR = new zzb();
    }

    @Constructor public ActivityRecognitionResult(@Param(id=1) List arg6, @Param(id=2) long arg7, @Param(id=3) long arg9, @Param(id=4) int arg11, @Param(id=5) Bundle arg12) {
        super();
        boolean v0 = true;
        boolean v2 = arg6 == null || arg6.size() <= 0 ? false : true;
        Preconditions.checkArgument(v2, "Must have at least 1 detected activity");
        long v2_1 = 0;
        if(arg7 <= v2_1 || arg9 <= v2_1) {
            v0 = false;
        }
        else {
        }

        Preconditions.checkArgument(v0, "Must set times");
        this.zze = arg6;
        this.zzf = arg7;
        this.zzg = arg9;
        this.zzh = arg11;
        this.extras = arg12;
    }

    @VisibleForTesting public ActivityRecognitionResult(DetectedActivity arg9, long arg10, long arg12) {
        this(arg9, arg10, arg12, 0, null);
    }

    private ActivityRecognitionResult(DetectedActivity arg9, long arg10, long arg12, int arg14, Bundle arg15) {
        this(Collections.singletonList(arg9), arg10, arg12, 0, null);
    }

    public ActivityRecognitionResult(List arg9, long arg10, long arg12) {
        this(arg9, arg10, arg12, 0, null);
    }

    public boolean equals(Object arg8) {
        if(this == (((ActivityRecognitionResult)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.zzf == ((ActivityRecognitionResult)arg8).zzf && this.zzg == ((ActivityRecognitionResult)arg8).zzg && this.zzh == ((ActivityRecognitionResult)arg8).zzh && (Objects.equal(this.zze, ((ActivityRecognitionResult)arg8).zze)) && (ActivityRecognitionResult.zza(this.extras, ((ActivityRecognitionResult)arg8).extras))) {
                return 1;
            }
        }

        return 0;
    }

    public static ActivityRecognitionResult extractResult(Intent arg3) {
        SafeParcelable v0_1;
        Object v0;
        ActivityRecognitionResult v1 = null;
        if(ActivityRecognitionResult.hasResult(arg3)) {
            v0 = arg3.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
            if((v0 instanceof byte[])) {
                v0_1 = SafeParcelableSerializer.deserializeFromBytes(((byte[])v0), ActivityRecognitionResult.CREATOR);
            }
            else if((v0 instanceof ActivityRecognitionResult)) {
            }
            else {
                goto label_14;
            }
        }
        else {
        label_14:
            v0 = v1;
        }

        if(v0_1 != null) {
            return ((ActivityRecognitionResult)v0_1);
        }

        List v3 = ActivityRecognitionResult.zza(arg3);
        if(v3 != null) {
            if(v3.isEmpty()) {
            }
            else {
                return v3.get(v3.size() - 1);
            }
        }

        return v1;
    }

    public int getActivityConfidence(int arg4) {
        Object v1;
        Iterator v0 = this.zze.iterator();
        do {
            if(!v0.hasNext()) {
                return 0;
            }

            v1 = v0.next();
        }
        while(((DetectedActivity)v1).getType() != arg4);

        return ((DetectedActivity)v1).getConfidence();
    }

    public long getElapsedRealtimeMillis() {
        return this.zzg;
    }

    public DetectedActivity getMostProbableActivity() {
        return this.zze.get(0);
    }

    public List getProbableActivities() {
        return this.zze;
    }

    public long getTime() {
        return this.zzf;
    }

    public static boolean hasResult(Intent arg3) {
        if(arg3 == null) {
            return 0;
        }

        boolean v1 = arg3 == null ? false : arg3.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
        if(v1) {
            return 1;
        }

        List v3 = ActivityRecognitionResult.zza(arg3);
        if(v3 != null && !v3.isEmpty()) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzf), Long.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zze, this.extras});
    }

    public String toString() {
        String v0 = String.valueOf(this.zze);
        long v1 = this.zzf;
        long v3 = this.zzg;
        StringBuilder v6 = new StringBuilder(String.valueOf(v0).length() + 124);
        v6.append("ActivityRecognitionResult [probableActivities=");
        v6.append(v0);
        v6.append(", timeMillis=");
        v6.append(v1);
        v6.append(", elapsedRealtimeMillis=");
        v6.append(v3);
        v6.append("]");
        return v6.toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedList(arg5, 1, this.zze, false);
        SafeParcelWriter.writeLong(arg5, 2, this.zzf);
        SafeParcelWriter.writeLong(arg5, 3, this.zzg);
        SafeParcelWriter.writeInt(arg5, 4, this.zzh);
        SafeParcelWriter.writeBundle(arg5, 5, this.extras, false);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }

    private static List zza(Intent arg2) {
        boolean v0 = arg2 == null ? false : arg2.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST");
        if(!v0) {
            return null;
        }

        return SafeParcelableSerializer.deserializeIterableFromIntentExtra(arg2, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", ActivityRecognitionResult.CREATOR);
    }

    private static boolean zza(Bundle arg5, Bundle arg6) {
        if(arg5 == null && arg6 == null) {
            return 1;
        }

        if(arg5 == null && arg6 != null || arg5 != null && arg6 == null) {
            return 0;
        }

        if(arg5.size() != arg6.size()) {
            return 0;
        }

        Iterator v2 = arg5.keySet().iterator();
        do {
        label_16:
            if(!v2.hasNext()) {
                return 1;
            }

            Object v3 = v2.next();
            if(!arg6.containsKey(((String)v3))) {
                return 0;
            }

            if(arg5.get(((String)v3)) == null) {
                if(arg6.get(((String)v3)) == null) {
                    goto label_16;
                }

                return 0;
            }

            if((arg5.get(((String)v3)) instanceof Bundle)) {
                if(ActivityRecognitionResult.zza(arg5.getBundle(((String)v3)), arg6.getBundle(((String)v3)))) {
                    goto label_16;
                }

                return 0;
            }
        }
        while(arg5.get(((String)v3)).equals(arg6.get(((String)v3))));

        return 0;
    }
}

