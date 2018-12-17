package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="ResolveAccountResponseCreator") public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getConnectionResult", id=3) private ConnectionResult zzeu;
    @Field(getter="getSaveDefaultAccount", id=4) private boolean zzhs;
    @Field(id=2) private IBinder zzqv;
    @Field(getter="isFromCrossClientAuth", id=5) private boolean zzuv;

    static {
        ResolveAccountResponse.CREATOR = new ResolveAccountResponseCreator();
    }

    @Constructor ResolveAccountResponse(@Param(id=1) int arg1, @Param(id=2) IBinder arg2, @Param(id=3) ConnectionResult arg3, @Param(id=4) boolean arg4, @Param(id=5) boolean arg5) {
        super();
        this.zzal = arg1;
        this.zzqv = arg2;
        this.zzeu = arg3;
        this.zzhs = arg4;
        this.zzuv = arg5;
    }

    public ResolveAccountResponse(int arg3) {
        this(new ConnectionResult(arg3, null));
    }

    public ResolveAccountResponse(ConnectionResult arg7) {
        this(1, null, arg7, false, false);
    }

    public boolean equals(Object arg5) {
        if(this == (((ResolveAccountResponse)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof ResolveAccountResponse)) {
            return 0;
        }

        if((this.zzeu.equals(((ResolveAccountResponse)arg5).zzeu)) && (this.getAccountAccessor().equals(((ResolveAccountResponse)arg5).getAccountAccessor()))) {
            return 1;
        }

        return 0;
    }

    public IAccountAccessor getAccountAccessor() {
        return Stub.asInterface(this.zzqv);
    }

    public ConnectionResult getConnectionResult() {
        return this.zzeu;
    }

    public boolean getSaveDefaultAccount() {
        return this.zzhs;
    }

    public boolean isFromCrossClientAuth() {
        return this.zzuv;
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor arg1) {
        IBinder v1 = arg1 == null ? null : arg1.asBinder();
        this.zzqv = v1;
        return this;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean arg1) {
        this.zzuv = arg1;
        return this;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean arg1) {
        this.zzhs = arg1;
        return this;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeIBinder(arg5, 2, this.zzqv, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.getConnectionResult(), arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 4, this.getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(arg5, 5, this.isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

