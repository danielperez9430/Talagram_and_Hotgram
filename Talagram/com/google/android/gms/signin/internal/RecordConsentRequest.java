package com.google.android.gms.signin.internal;

import android.accounts.Account;
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

@Class(creator="RecordConsentRequestCreator") public class RecordConsentRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getScopesToConsent", id=3) private final Scope[] zzadr;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getAccount", id=2) private final Account zzs;
    @Field(getter="getServerClientId", id=4) private final String zzw;

    static {
        RecordConsentRequest.CREATOR = new RecordConsentRequestCreator();
    }

    @Constructor RecordConsentRequest(@Param(id=1) int arg1, @Param(id=2) Account arg2, @Param(id=3) Scope[] arg3, @Param(id=4) String arg4) {
        super();
        this.zzal = arg1;
        this.zzs = arg2;
        this.zzadr = arg3;
        this.zzw = arg4;
    }

    public RecordConsentRequest(Account arg2, Scope[] arg3, String arg4) {
        this(1, arg2, arg3, arg4);
    }

    public Account getAccount() {
        return this.zzs;
    }

    public Scope[] getScopesToConsent() {
        return this.zzadr;
    }

    public String getServerClientId() {
        return this.zzw;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getAccount(), arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 3, this.getScopesToConsent(), arg6, false);
        SafeParcelWriter.writeString(arg5, 4, this.getServerClientId(), false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

