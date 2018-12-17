package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="SignInResponseCreator") public class SignInResponse extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getResolveAccountResponse", id=3) private final ResolveAccountResponse zzadu;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getConnectionResult", id=2) private final ConnectionResult zzeu;

    static {
        SignInResponse.CREATOR = new SignInResponseCreator();
    }

    @Constructor SignInResponse(@Param(id=1) int arg1, @Param(id=2) ConnectionResult arg2, @Param(id=3) ResolveAccountResponse arg3) {
        super();
        this.zzal = arg1;
        this.zzeu = arg2;
        this.zzadu = arg3;
    }

    public SignInResponse(int arg3) {
        this(new ConnectionResult(arg3, null), null);
    }

    public SignInResponse(ConnectionResult arg2, ResolveAccountResponse arg3) {
        this(1, arg2, arg3);
    }

    public ConnectionResult getConnectionResult() {
        return this.zzeu;
    }

    public ResolveAccountResponse getResolveAccountResponse() {
        return this.zzadu;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getConnectionResult(), arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.getResolveAccountResponse(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

