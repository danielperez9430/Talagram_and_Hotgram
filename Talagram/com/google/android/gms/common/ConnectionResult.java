package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="ConnectionResultCreator") public final class ConnectionResult extends AbstractSafeParcelable {
    public static final int API_UNAVAILABLE = 16;
    public static final int API_VERSION_UPDATE_REQUIRED = 21;
    public static final int CANCELED = 13;
    public static final Parcelable$Creator CREATOR = null;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int RESTRICTED_PROFILE = 20;
    public static final ConnectionResult RESULT_SUCCESS = null;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 19;
    public static final int SERVICE_UPDATING = 18;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_FAILED = 17;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    public static final int UNFINISHED = 99;
    public static final int UNKNOWN = -1;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getErrorCode", id=2) private final int zzam;
    @Field(getter="getResolution", id=3) private final PendingIntent zzan;
    @Field(getter="getErrorMessage", id=4) private final String zzao;

    static {
        ConnectionResult.RESULT_SUCCESS = new ConnectionResult(0);
        ConnectionResult.CREATOR = new ConnectionResultCreator();
    }

    @Constructor ConnectionResult(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) PendingIntent arg3, @Param(id=4) String arg4) {
        super();
        this.zzal = arg1;
        this.zzam = arg2;
        this.zzan = arg3;
        this.zzao = arg4;
    }

    public ConnectionResult(int arg2, PendingIntent arg3) {
        this(arg2, arg3, null);
    }

    public ConnectionResult(int arg2) {
        this(arg2, null, null);
    }

    public ConnectionResult(int arg2, PendingIntent arg3, String arg4) {
        this(1, arg2, arg3, arg4);
    }

    public final boolean equals(Object arg5) {
        if((((ConnectionResult)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof ConnectionResult)) {
            return 0;
        }

        if(this.zzam == ((ConnectionResult)arg5).zzam && (Objects.equal(this.zzan, ((ConnectionResult)arg5).zzan)) && (Objects.equal(this.zzao, ((ConnectionResult)arg5).zzao))) {
            return 1;
        }

        return 0;
    }

    public final int getErrorCode() {
        return this.zzam;
    }

    public final String getErrorMessage() {
        return this.zzao;
    }

    public final PendingIntent getResolution() {
        return this.zzan;
    }

    public final boolean hasResolution() {
        if(this.zzam != 0 && this.zzan != null) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzam), this.zzan, this.zzao});
    }

    public final boolean isSuccess() {
        if(this.zzam == 0) {
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
        return Objects.toStringHelper(this).add("statusCode", ConnectionResult.zza(this.zzam)).add("resolution", this.zzan).add("message", this.zzao).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeInt(arg5, 2, this.getErrorCode());
        SafeParcelWriter.writeParcelable(arg5, 3, this.getResolution(), arg6, false);
        SafeParcelWriter.writeString(arg5, 4, this.getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    static String zza(int arg2) {
        if(arg2 == 99) {
            return "UNFINISHED";
        }

        if(arg2 == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }

        switch(arg2) {
            case -1: {
                return "UNKNOWN";
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
        }

        switch(arg2) {
            case 13: {
                return "CANCELED";
            }
            case 14: {
                return "TIMEOUT";
            }
            case 15: {
                return "INTERRUPTED";
            }
            case 16: {
                return "API_UNAVAILABLE";
            }
            case 17: {
                return "SIGN_IN_FAILED";
            }
            case 18: {
                return "SERVICE_UPDATING";
            }
            case 19: {
                return "SERVICE_MISSING_PERMISSION";
            }
            case 20: {
                return "RESTRICTED_PROFILE";
            }
            case 21: {
                return "API_VERSION_UPDATE_REQUIRED";
            }
        }

        StringBuilder v1 = new StringBuilder(31);
        v1.append("UNKNOWN_ERROR_CODE(");
        v1.append(arg2);
        v1.append(")");
        return v1.toString();
    }
}

