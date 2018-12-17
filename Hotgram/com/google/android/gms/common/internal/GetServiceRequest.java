package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.util.Collection;

@Class(creator="GetServiceRequestCreator") @Reserved(value={9}) public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int version;
    @Field(id=2) private final int zzst;
    @Field(id=3) private int zzsu;
    @Field(id=4) private String zzsv;
    @Field(id=5) private IBinder zzsw;
    @Field(id=6) private Scope[] zzsx;
    @Field(id=7) private Bundle zzsy;
    @Field(id=8) private Account zzsz;
    @Field(id=10) private Feature[] zzta;
    @Field(id=11) private Feature[] zztb;
    @Field(id=12) private boolean zztc;

    static {
        GetServiceRequest.CREATOR = new GetServiceRequestCreator();
    }

    public GetServiceRequest(int arg2) {
        super();
        this.version = 4;
        this.zzsu = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzst = arg2;
        this.zztc = true;
    }

    @Constructor GetServiceRequest(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) int arg3, @Param(id=4) String arg4, @Param(id=5) IBinder arg5, @Param(id=6) Scope[] arg6, @Param(id=7) Bundle arg7, @Param(id=8) Account arg8, @Param(id=10) Feature[] arg9, @Param(id=11) Feature[] arg10, @Param(id=12) boolean arg11) {
        super();
        this.version = arg1;
        this.zzst = arg2;
        this.zzsu = arg3;
        this.zzsv = "com.google.android.gms".equals(arg4) ? "com.google.android.gms" : arg4;
        if(arg1 < 2) {
            this.zzsz = GetServiceRequest.zzb(arg5);
        }
        else {
            this.zzsw = arg5;
            this.zzsz = arg8;
        }

        this.zzsx = arg6;
        this.zzsy = arg7;
        this.zzta = arg9;
        this.zztb = arg10;
        this.zztc = arg11;
    }

    public Account getAuthenticatedAccount() {
        return GetServiceRequest.zzb(this.zzsw);
    }

    public String getCallingPackage() {
        return this.zzsv;
    }

    public Feature[] getClientApiFeatures() {
        return this.zztb;
    }

    public int getClientLibraryVersion() {
        return this.zzsu;
    }

    public Account getClientRequestedAccount() {
        return this.zzsz;
    }

    public Feature[] getClientRequiredFeatures() {
        return this.zzta;
    }

    public static Parcelable$Creator getCreator() {
        return GetServiceRequest.CREATOR;
    }

    public Bundle getExtraArgs() {
        return this.zzsy;
    }

    public Scope[] getScopes() {
        return this.zzsx;
    }

    public int getServiceId() {
        return this.zzst;
    }

    public boolean isRequestingConnectionInfo() {
        return this.zztc;
    }

    public GetServiceRequest setAuthenticatedAccount(IAccountAccessor arg1) {
        if(arg1 != null) {
            this.zzsw = arg1.asBinder();
        }

        return this;
    }

    public GetServiceRequest setCallingPackage(String arg1) {
        this.zzsv = arg1;
        return this;
    }

    public GetServiceRequest setClientApiFeatures(Feature[] arg1) {
        this.zztb = arg1;
        return this;
    }

    public GetServiceRequest setClientLibraryVersion(int arg1) {
        this.zzsu = arg1;
        return this;
    }

    public GetServiceRequest setClientRequestedAccount(Account arg1) {
        this.zzsz = arg1;
        return this;
    }

    public GetServiceRequest setClientRequiredFeatures(Feature[] arg1) {
        this.zzta = arg1;
        return this;
    }

    public GetServiceRequest setExtraArgs(Bundle arg1) {
        this.zzsy = arg1;
        return this;
    }

    public GetServiceRequest setRequestingConnectionInfo(boolean arg1) {
        this.zztc = arg1;
        return this;
    }

    public GetServiceRequest setScopes(Collection arg2) {
        this.zzsx = arg2.toArray(new Scope[arg2.size()]);
        return this;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.version);
        SafeParcelWriter.writeInt(arg5, 2, this.zzst);
        SafeParcelWriter.writeInt(arg5, 3, this.zzsu);
        SafeParcelWriter.writeString(arg5, 4, this.zzsv, false);
        SafeParcelWriter.writeIBinder(arg5, 5, this.zzsw, false);
        SafeParcelWriter.writeTypedArray(arg5, 6, this.zzsx, arg6, false);
        SafeParcelWriter.writeBundle(arg5, 7, this.zzsy, false);
        SafeParcelWriter.writeParcelable(arg5, 8, this.zzsz, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 10, this.zzta, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 11, this.zztb, arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 12, this.zztc);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    private static Account zzb(IBinder arg0) {
        Account v0 = arg0 != null ? AccountAccessor.getAccountBinderSafe(Stub.asInterface(arg0)) : null;
        return v0;
    }
}

