package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="AuthAccountResultCreator") public class AuthAccountResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getConnectionResultCode", id=2) private int zzadn;
    @Field(getter="getRawAuthResolutionIntent", id=3) private Intent zzado;
    @VersionField(id=1) private final int zzal;

    static {
        AuthAccountResult.CREATOR = new AuthAccountResultCreator();
    }

    @Constructor AuthAccountResult(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) Intent arg3) {
        super();
        this.zzal = arg1;
        this.zzadn = arg2;
        this.zzado = arg3;
    }

    public AuthAccountResult() {
        this(0, null);
    }

    public AuthAccountResult(int arg2, Intent arg3) {
        this(2, arg2, arg3);
    }

    public int getConnectionResultCode() {
        return this.zzadn;
    }

    public Intent getRawAuthResolutionIntent() {
        return this.zzado;
    }

    public Status getStatus() {
        if(this.zzadn == 0) {
            return Status.RESULT_SUCCESS;
        }

        return Status.RESULT_CANCELED;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeInt(arg5, 2, this.getConnectionResultCode());
        SafeParcelWriter.writeParcelable(arg5, 3, this.getRawAuthResolutionIntent(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

