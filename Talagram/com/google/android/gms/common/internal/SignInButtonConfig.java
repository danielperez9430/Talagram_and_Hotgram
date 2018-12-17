package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="SignInButtonConfigCreator") public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getScopes", id=4) @Deprecated private final Scope[] zzqw;
    @Field(getter="getButtonSize", id=2) private final int zzux;
    @Field(getter="getColorScheme", id=3) private final int zzuy;

    static {
        SignInButtonConfig.CREATOR = new SignInButtonConfigCreator();
    }

    @Constructor SignInButtonConfig(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) int arg3, @Param(id=4) Scope[] arg4) {
        super();
        this.zzal = arg1;
        this.zzux = arg2;
        this.zzuy = arg3;
        this.zzqw = arg4;
    }

    public SignInButtonConfig(int arg2, int arg3, Scope[] arg4) {
        this(1, arg2, arg3, null);
    }

    public int getButtonSize() {
        return this.zzux;
    }

    public int getColorScheme() {
        return this.zzuy;
    }

    @Deprecated public Scope[] getScopes() {
        return this.zzqw;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeInt(arg5, 2, this.getButtonSize());
        SafeParcelWriter.writeInt(arg5, 3, this.getColorScheme());
        SafeParcelWriter.writeTypedArray(arg5, 4, this.getScopes(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

