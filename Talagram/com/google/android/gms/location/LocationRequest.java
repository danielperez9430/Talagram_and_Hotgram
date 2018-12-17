package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator="LocationRequestCreator") @Reserved(value={1000}) public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_PRIORITY", id=1) private int priority;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_EXPIRE_AT", id=5) private long zzaf;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_INTERVAL", id=2) private long zzaw;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_FASTEST_INTERVAL", id=3) private long zzax;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_EXPLICIT_FASTEST_INTERVAL", id=4) private boolean zzay;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_SMALLEST_DISPLACEMENT", id=7) private float zzaz;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_MAX_WAIT_TIME", id=8) private long zzba;
    @Field(defaultValueUnchecked="LocationRequest.DEFAULT_NUM_UPDATES", id=6) private int zzx;

    static {
        LocationRequest.CREATOR = new zzab();
    }

    @Constructor LocationRequest(@Param(id=1) int arg1, @Param(id=2) long arg2, @Param(id=3) long arg4, @Param(id=4) boolean arg6, @Param(id=5) long arg7, @Param(id=6) int arg9, @Param(id=7) float arg10, @Param(id=8) long arg11) {
        super();
        this.priority = arg1;
        this.zzaw = arg2;
        this.zzax = arg4;
        this.zzay = arg6;
        this.zzaf = arg7;
        this.zzx = arg9;
        this.zzaz = arg10;
        this.zzba = arg11;
    }

    public LocationRequest() {
        super();
        this.priority = 102;
        this.zzaw = 3600000;
        this.zzax = 600000;
        this.zzay = false;
        this.zzaf = 9223372036854775807L;
        this.zzx = 2147483647;
        this.zzaz = 0f;
        this.zzba = 0;
    }

    @VisibleForTesting public static LocationRequest create() {
        return new LocationRequest();
    }

    public final boolean equals(Object arg8) {
        if(this == (((LocationRequest)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof LocationRequest)) {
            return 0;
        }

        if(this.priority == ((LocationRequest)arg8).priority && this.zzaw == ((LocationRequest)arg8).zzaw && this.zzax == ((LocationRequest)arg8).zzax && this.zzay == ((LocationRequest)arg8).zzay && this.zzaf == ((LocationRequest)arg8).zzaf && this.zzx == ((LocationRequest)arg8).zzx && this.zzaz == ((LocationRequest)arg8).zzaz && this.getMaxWaitTime() == ((LocationRequest)arg8).getMaxWaitTime()) {
            return 1;
        }

        return 0;
    }

    public final long getExpirationTime() {
        return this.zzaf;
    }

    public final long getFastestInterval() {
        return this.zzax;
    }

    public final long getInterval() {
        return this.zzaw;
    }

    public final long getMaxWaitTime() {
        long v0 = this.zzba;
        if(v0 < this.zzaw) {
            v0 = this.zzaw;
        }

        return v0;
    }

    public final int getNumUpdates() {
        return this.zzx;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final float getSmallestDisplacement() {
        return this.zzaz;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.priority), Long.valueOf(this.zzaw), Float.valueOf(this.zzaz), Long.valueOf(this.zzba)});
    }

    public final boolean isFastestIntervalExplicitlySet() {
        return this.zzay;
    }

    public final LocationRequest setExpirationDuration(long arg8) {
        long v0 = SystemClock.elapsedRealtime();
        long v2 = 9223372036854775807L;
        this.zzaf = arg8 > v2 - v0 ? v2 : arg8 + v0;
        v0 = 0;
        if(this.zzaf < v0) {
            this.zzaf = v0;
        }

        return this;
    }

    @VisibleForTesting public final LocationRequest setExpirationTime(long arg4) {
        this.zzaf = arg4;
        long v0 = 0;
        if(this.zzaf < v0) {
            this.zzaf = v0;
        }

        return this;
    }

    public final LocationRequest setFastestInterval(long arg2) {
        LocationRequest.zza(arg2);
        this.zzay = true;
        this.zzax = arg2;
        return this;
    }

    public final LocationRequest setInterval(long arg3) {
        LocationRequest.zza(arg3);
        this.zzaw = arg3;
        if(!this.zzay) {
            double v3 = ((double)this.zzaw);
            Double.isNaN(v3);
            this.zzax = ((long)(v3 / 6));
        }

        return this;
    }

    @VisibleForTesting public final LocationRequest setMaxWaitTime(long arg1) {
        LocationRequest.zza(arg1);
        this.zzba = arg1;
        return this;
    }

    @VisibleForTesting public final LocationRequest setNumUpdates(int arg4) {
        if(arg4 > 0) {
            this.zzx = arg4;
            return this;
        }

        StringBuilder v2 = new StringBuilder(31);
        v2.append("invalid numUpdates: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    }

    @VisibleForTesting public final LocationRequest setPriority(int arg4) {
        switch(arg4) {
            case 100: 
            case 102: 
            case 104: 
            case 105: {
                goto label_11;
            }
        }

        StringBuilder v2 = new StringBuilder(28);
        v2.append("invalid quality: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    label_11:
        this.priority = arg4;
        return this;
    }

    @VisibleForTesting public final LocationRequest setSmallestDisplacement(float arg4) {
        if(arg4 >= 0f) {
            this.zzaz = arg4;
            return this;
        }

        StringBuilder v2 = new StringBuilder(37);
        v2.append("invalid displacement: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    }

    public final String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append("Request[");
        switch(this.priority) {
            case 100: {
                v1 = "PRIORITY_HIGH_ACCURACY";
                break;
            }
            case 102: {
                v1 = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            }
            case 104: {
                v1 = "PRIORITY_LOW_POWER";
                break;
            }
            case 105: {
                v1 = "PRIORITY_NO_POWER";
                break;
            }
            default: {
                v1 = "???";
                break;
            }
        }

        v0.append(v1);
        if(this.priority != 105) {
            v0.append(" requested=");
            v0.append(this.zzaw);
            v0.append("ms");
        }

        v0.append(" fastest=");
        v0.append(this.zzax);
        v0.append("ms");
        if(this.zzba > this.zzaw) {
            v0.append(" maxWait=");
            v0.append(this.zzba);
            v0.append("ms");
        }

        if(this.zzaz > 0f) {
            v0.append(" smallestDisplacement=");
            v0.append(this.zzaz);
            v0.append("m");
        }

        if(this.zzaf != 9223372036854775807L) {
            long v1_1 = this.zzaf - SystemClock.elapsedRealtime();
            v0.append(" expireIn=");
            v0.append(v1_1);
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
        SafeParcelWriter.writeInt(arg4, 1, this.priority);
        SafeParcelWriter.writeLong(arg4, 2, this.zzaw);
        SafeParcelWriter.writeLong(arg4, 3, this.zzax);
        SafeParcelWriter.writeBoolean(arg4, 4, this.zzay);
        SafeParcelWriter.writeLong(arg4, 5, this.zzaf);
        SafeParcelWriter.writeInt(arg4, 6, this.zzx);
        SafeParcelWriter.writeFloat(arg4, 7, this.zzaz);
        SafeParcelWriter.writeLong(arg4, 8, this.zzba);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    private static void zza(long arg3) {
        if(arg3 >= 0) {
            return;
        }

        StringBuilder v2 = new StringBuilder(38);
        v2.append("invalid interval: ");
        v2.append(arg3);
        throw new IllegalArgumentException(v2.toString());
    }
}

