package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@Class(creator="AuthAccountRequestCreator") public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(id=2) @Deprecated private final IBinder zzqv;
    @Field(id=3) private final Scope[] zzqw;
    @Field(id=4) private Integer zzqx;
    @Field(id=5) private Integer zzqy;
    @Field(id=6, type="android.accounts.Account") private Account zzs;

    static {
        AuthAccountRequest.CREATOR = new AuthAccountRequestCreator();
    }

    @Constructor AuthAccountRequest(@Param(id=1) int arg1, @Param(id=2) IBinder arg2, @Param(id=3) Scope[] arg3, @Param(id=4) Integer arg4, @Param(id=5) Integer arg5, @Param(id=6) Account arg6) {
        super();
        this.zzal = arg1;
        this.zzqv = arg2;
        this.zzqw = arg3;
        this.zzqx = arg4;
        this.zzqy = arg5;
        this.zzs = arg6;
    }

    public AuthAccountRequest(Account arg8, Set arg9) {
        this(3, null, arg9.toArray(new Scope[arg9.size()]), null, null, Preconditions.checkNotNull(arg8));
    }

    @Deprecated public AuthAccountRequest(IAccountAccessor arg8, Set arg9) {
        this(3, arg8.asBinder(), arg9.toArray(new Scope[arg9.size()]), null, null, null);
    }

    public Account getAccount() {
        Account v0;
        if(this.zzs != null) {
            v0 = this.zzs;
        }
        else if(this.zzqv != null) {
            v0 = AccountAccessor.getAccountBinderSafe(Stub.asInterface(this.zzqv));
        }
        else {
            v0 = null;
        }

        return v0;
    }

    @Nullable public Integer getOauthPolicy() {
        return this.zzqx;
    }

    @Nullable public Integer getPolicyAction() {
        return this.zzqy;
    }

    public Set getScopes() {
        return new HashSet(Arrays.asList(this.zzqw));
    }

    public AuthAccountRequest setOauthPolicy(@Nullable Integer arg1) {
        this.zzqx = arg1;
        return this;
    }

    public AuthAccountRequest setPolicyAction(@Nullable Integer arg1) {
        this.zzqy = arg1;
        return this;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeIBinder(arg5, 2, this.zzqv, false);
        SafeParcelWriter.writeTypedArray(arg5, 3, this.zzqw, arg6, false);
        SafeParcelWriter.writeIntegerObject(arg5, 4, this.zzqx, false);
        SafeParcelWriter.writeIntegerObject(arg5, 5, this.zzqy, false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.zzs, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

