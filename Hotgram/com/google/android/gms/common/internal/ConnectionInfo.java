package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="ConnectionInfoCreator") public class ConnectionInfo extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private Bundle zzsf;
    @Field(id=2) private Feature[] zzsg;

    static {
        ConnectionInfo.CREATOR = new ConnectionInfoCreator();
    }

    @Constructor ConnectionInfo(@Param(id=1) Bundle arg1, @Param(id=2) Feature[] arg2) {
        super();
        this.zzsf = arg1;
        this.zzsg = arg2;
    }

    public ConnectionInfo() {
        super();
    }

    public Feature[] getAvailableFeatures() {
        return this.zzsg;
    }

    public Bundle getResolutionBundle() {
        return this.zzsf;
    }

    public ConnectionInfo setAvailableFeatures(Feature[] arg1) {
        this.zzsg = arg1;
        return this;
    }

    public ConnectionInfo setResolutionBundle(Bundle arg1) {
        this.zzsf = arg1;
        return this;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeBundle(arg5, 1, this.zzsf, false);
        SafeParcelWriter.writeTypedArray(arg5, 2, this.zzsg, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

