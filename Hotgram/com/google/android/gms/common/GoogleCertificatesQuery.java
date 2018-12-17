package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData$Stub;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

@Class(creator="GoogleCertificatesQueryCreator") public class GoogleCertificatesQuery extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getCallingPackage", id=1) private final String zzbh;
    @Field(getter="getCallingCertificateBinder", id=2, type="android.os.IBinder") @Nullable private final CertData zzbi;
    @Field(getter="getAllowTestKeys", id=3) private final boolean zzbj;

    static {
        GoogleCertificatesQuery.CREATOR = new GoogleCertificatesQueryCreator();
    }

    GoogleCertificatesQuery(String arg1, @Nullable CertData arg2, boolean arg3) {
        super();
        this.zzbh = arg1;
        this.zzbi = arg2;
        this.zzbj = arg3;
    }

    @Constructor GoogleCertificatesQuery(@Param(id=1) String arg1, @Param(id=2) @Nullable IBinder arg2, @Param(id=3) boolean arg3) {
        super();
        this.zzbh = arg1;
        this.zzbi = GoogleCertificatesQuery.zza(arg2);
        this.zzbj = arg3;
    }

    public boolean getAllowTestKeys() {
        return this.zzbj;
    }

    @Nullable public IBinder getCallingCertificateBinder() {
        if(this.zzbi == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            return null;
        }

        return this.zzbi.asBinder();
    }

    public String getCallingPackage() {
        return this.zzbh;
    }

    @Nullable public CertData getCertificate() {
        return this.zzbi;
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.getCallingPackage(), false);
        SafeParcelWriter.writeIBinder(arg4, 2, this.getCallingCertificateBinder(), false);
        SafeParcelWriter.writeBoolean(arg4, 3, this.getAllowTestKeys());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    @Nullable private static CertData zza(@Nullable IBinder arg3) {
        IObjectWrapper v3_1;
        CertData v0 = null;
        if(arg3 == null) {
            return v0;
        }

        try {
            v3_1 = Stub.asInterface(arg3).getBytesWrapped();
            if(v3_1 != null) {
                goto label_8;
            }
        }
        catch(RemoteException v3) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", ((Throwable)v3));
            return v0;
        }

        Object v3_2 = v0;
        goto label_9;
    label_8:
        v3_2 = ObjectWrapper.unwrap(v3_1);
    label_9:
        if(v3_2 != null) {
            zzb v0_1 = new zzb(((byte[])v3_2));
        }
        else {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
        }

        return v0;
    }
}

