package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator="CheckServerAuthResultCreator") public class CheckServerAuthResult extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final boolean zzadp;
    @Field(id=3) private final List zzadq;
    @VersionField(id=1) private final int zzal;

    static {
        CheckServerAuthResult.CREATOR = new CheckServerAuthResultCreator();
    }

    @Constructor CheckServerAuthResult(@Param(id=1) int arg1, @Param(id=2) boolean arg2, @Param(id=3) List arg3) {
        super();
        this.zzal = arg1;
        this.zzadp = arg2;
        this.zzadq = arg3;
    }

    public CheckServerAuthResult(boolean arg2, Set arg3) {
        List v3 = arg3 == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(((Collection)arg3)));
        this(1, arg2, v3);
    }

    public Set getAdditionalScopes() {
        return new HashSet(this.zzadq);
    }

    public boolean isNewAuthCodeRequired() {
        return this.zzadp;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.zzal);
        SafeParcelWriter.writeBoolean(arg4, 2, this.zzadp);
        SafeParcelWriter.writeTypedList(arg4, 3, this.zzadq, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

