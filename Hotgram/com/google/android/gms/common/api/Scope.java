package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@KeepForSdk @Class(creator="ScopeCreator") public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getScopeUri", id=2) private final String zzdp;

    static {
        Scope.CREATOR = new zzd();
    }

    public Scope(String arg2) {
        this(1, arg2);
    }

    @Constructor Scope(@Param(id=1) int arg2, @Param(id=2) String arg3) {
        super();
        Preconditions.checkNotEmpty(arg3, "scopeUri must not be null or empty");
        this.zzal = arg2;
        this.zzdp = arg3;
    }

    public final boolean equals(Object arg2) {
        if(this == (((Scope)arg2))) {
            return 1;
        }

        if(!(arg2 instanceof Scope)) {
            return 0;
        }

        return this.zzdp.equals(((Scope)arg2).zzdp);
    }

    @KeepForSdk public final String getScopeUri() {
        return this.zzdp;
    }

    public final int hashCode() {
        return this.zzdp.hashCode();
    }

    public final String toString() {
        return this.zzdp;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.zzal);
        SafeParcelWriter.writeString(arg4, 2, this.getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

