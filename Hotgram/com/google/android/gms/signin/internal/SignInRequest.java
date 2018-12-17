package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="SignInRequestCreator") public class SignInRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getResolveAccountRequest", id=2) private final ResolveAccountRequest zzadt;
    @VersionField(id=1) private final int zzal;

    static {
        SignInRequest.CREATOR = new SignInRequestCreator();
    }

    @Constructor SignInRequest(@Param(id=1) int arg1, @Param(id=2) ResolveAccountRequest arg2) {
        super();
        this.zzal = arg1;
        this.zzadt = arg2;
    }

    public SignInRequest(ResolveAccountRequest arg2) {
        this(1, arg2);
    }

    public ResolveAccountRequest getResolveAccountRequest() {
        return this.zzadt;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getResolveAccountRequest(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

