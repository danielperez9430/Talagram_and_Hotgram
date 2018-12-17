package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension$TypeId;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="GoogleSignInOptionsExtensionCreator") public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getBundle", id=3) private Bundle mBundle;
    @VersionField(id=1) private final int versionCode;
    @Field(getter="getType", id=2) private int zzac;

    static {
        GoogleSignInOptionsExtensionParcelable.CREATOR = new GoogleSignInOptionsExtensionCreator();
    }

    @Constructor GoogleSignInOptionsExtensionParcelable(@Param(id=1) int arg1, @TypeId @Param(id=2) int arg2, @Param(id=3) Bundle arg3) {
        super();
        this.versionCode = arg1;
        this.zzac = arg2;
        this.mBundle = arg3;
    }

    public GoogleSignInOptionsExtensionParcelable(GoogleSignInOptionsExtension arg3) {
        this(1, arg3.getExtensionType(), arg3.toBundle());
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    @TypeId public int getType() {
        return this.zzac;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.versionCode);
        SafeParcelWriter.writeInt(arg4, 2, this.getType());
        SafeParcelWriter.writeBundle(arg4, 3, this.getBundle(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

