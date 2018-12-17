package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk @Class(creator="StatusCreator") public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @KeepForSdk public static final Status RESULT_CANCELED;
    @KeepForSdk public static final Status RESULT_DEAD_CLIENT;
    @KeepForSdk public static final Status RESULT_INTERNAL_ERROR;
    @KeepForSdk public static final Status RESULT_INTERRUPTED;
    @KeepForSdk @VisibleForTesting public static final Status RESULT_SUCCESS;
    @KeepForSdk public static final Status RESULT_TIMEOUT;
    @VersionField(id=1000) private final int zzal;
    @Field(getter="getStatusCode", id=1) private final int zzam;
    @Field(getter="getPendingIntent", id=3) private final PendingIntent zzan;
    @Field(getter="getStatusMessage", id=2) private final String zzao;
    private static final Status zzdq;

    static {
        Status.RESULT_SUCCESS = new Status(0);
        Status.RESULT_INTERRUPTED = new Status(14);
        Status.RESULT_INTERNAL_ERROR = new Status(8);
        Status.RESULT_TIMEOUT = new Status(15);
        Status.RESULT_CANCELED = new Status(16);
        Status.zzdq = new Status(17);
        Status.RESULT_DEAD_CLIENT = new Status(18);
        Status.CREATOR = new zze();
    }

    @KeepForSdk public Status(int arg2, String arg3, PendingIntent arg4) {
        this(1, arg2, arg3, arg4);
    }

    @KeepForSdk public Status(int arg3, String arg4) {
        this(1, arg3, arg4, null);
    }

    @KeepForSdk @Constructor Status(@Param(id=1000) int arg1, @Param(id=1) int arg2, @Param(id=2) String arg3, @Param(id=3) PendingIntent arg4) {
        super();
        this.zzal = arg1;
        this.zzam = arg2;
        this.zzao = arg3;
        this.zzan = arg4;
    }

    @KeepForSdk public Status(int arg2) {
        this(arg2, null);
    }

    public final boolean equals(Object arg4) {
        if(!(arg4 instanceof Status)) {
            return 0;
        }

        if(this.zzal == ((Status)arg4).zzal && this.zzam == ((Status)arg4).zzam && (Objects.equal(this.zzao, ((Status)arg4).zzao)) && (Objects.equal(this.zzan, ((Status)arg4).zzan))) {
            return 1;
        }

        return 0;
    }

    public final PendingIntent getResolution() {
        return this.zzan;
    }

    @KeepForSdk public final Status getStatus() {
        return this;
    }

    public final int getStatusCode() {
        return this.zzam;
    }

    public final String getStatusMessage() {
        return this.zzao;
    }

    @VisibleForTesting public final boolean hasResolution() {
        if(this.zzan != null) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzal), Integer.valueOf(this.zzam), this.zzao, this.zzan});
    }

    public final boolean isCanceled() {
        if(this.zzam == 16) {
            return 1;
        }

        return 0;
    }

    public final boolean isInterrupted() {
        if(this.zzam == 14) {
            return 1;
        }

        return 0;
    }

    public final boolean isSuccess() {
        if(this.zzam <= 0) {
            return 1;
        }

        return 0;
    }

    public final void startResolutionForResult(Activity arg9, int arg10) {
        if(!this.hasResolution()) {
            return;
        }

        arg9.startIntentSenderForResult(this.zzan.getIntentSender(), arg10, null, 0, 0, 0);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", this.zzp()).add("resolution", this.zzan).toString();
    }

    @KeepForSdk public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.getStatusCode());
        SafeParcelWriter.writeString(arg5, 2, this.getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzan, arg6, false);
        SafeParcelWriter.writeInt(arg5, 1000, this.zzal);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final String zzp() {
        if(this.zzao != null) {
            return this.zzao;
        }

        return CommonStatusCodes.getStatusCodeString(this.zzam);
    }
}

