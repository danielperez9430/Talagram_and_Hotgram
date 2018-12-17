package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.List;

@Class(creator="FetchConfigIpcRequestCreator") @Reserved(value={1}) public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getPackageName", id=2) private final String mPackageName;
    @Field(getter="getAnalyticsUserProperties", id=10) private final List zzaa;
    @Field(getter="getSdkVersion", id=9) private final int zzh;
    @Field(getter="getFetchedConfigAgeSeconds", id=12) private final int zzi;
    @Field(getter="getActiveConfigAgeSeconds", id=11) private final int zzj;
    @Field(getter="getCacheExpirationInSeconds", id=3) private final long zzu;
    @Field(getter="getCustomVariablesHolder", id=4) private final DataHolder zzv;
    @Field(getter="getGmpProjectId", id=5) private final String zzw;
    @Field(getter="getAppInstanceId", id=6) private final String zzx;
    @Field(getter="getAppInstanceIdToken", id=7) private final String zzy;
    @Field(getter="getRegisteredHiddenNamespaces", id=8) private final List zzz;

    static {
        zzab.CREATOR = new zzac();
    }

    @Constructor public zzab(@Param(id=2) String arg1, @Param(id=3) long arg2, @Param(id=4) DataHolder arg4, @Param(id=5) String arg5, @Param(id=6) String arg6, @Param(id=7) String arg7, @Param(id=8) List arg8, @Param(id=9) int arg9, @Param(id=10) List arg10, @Param(id=11) int arg11, @Param(id=12) int arg12) {
        super();
        this.mPackageName = arg1;
        this.zzu = arg2;
        this.zzv = arg4;
        this.zzw = arg5;
        this.zzx = arg6;
        this.zzy = arg7;
        this.zzz = arg8;
        this.zzh = arg9;
        this.zzaa = arg10;
        this.zzj = arg11;
        this.zzi = arg12;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeString(arg6, 2, this.mPackageName, false);
        SafeParcelWriter.writeLong(arg6, 3, this.zzu);
        SafeParcelWriter.writeParcelable(arg6, 4, this.zzv, arg7, false);
        SafeParcelWriter.writeString(arg6, 5, this.zzw, false);
        SafeParcelWriter.writeString(arg6, 6, this.zzx, false);
        SafeParcelWriter.writeString(arg6, 7, this.zzy, false);
        SafeParcelWriter.writeStringList(arg6, 8, this.zzz, false);
        SafeParcelWriter.writeInt(arg6, 9, this.zzh);
        SafeParcelWriter.writeTypedList(arg6, 10, this.zzaa, false);
        SafeParcelWriter.writeInt(arg6, 11, this.zzj);
        SafeParcelWriter.writeInt(arg6, 12, this.zzi);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }
}

