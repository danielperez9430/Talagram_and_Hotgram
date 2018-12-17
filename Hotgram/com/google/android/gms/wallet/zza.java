package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="AddressCreator") @Reserved(value={1}) @Deprecated public final class zza extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String name;
    @Field(id=3) private String zzf;
    @Field(id=4) private String zzg;
    @Field(id=5) private String zzh;
    @Field(id=6) private String zzi;
    @Field(id=7) private String zzj;
    @Field(id=8) private String zzk;
    @Field(id=9) private String zzl;
    @Field(id=10) private String zzm;
    @Field(id=11) private boolean zzn;
    @Field(id=12) private String zzo;

    static {
        zza.CREATOR = new zzb();
    }

    @Constructor zza(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) String arg6, @Param(id=8) String arg7, @Param(id=9) String arg8, @Param(id=10) String arg9, @Param(id=11) boolean arg10, @Param(id=12) String arg11) {
        super();
        this.name = arg1;
        this.zzf = arg2;
        this.zzg = arg3;
        this.zzh = arg4;
        this.zzi = arg5;
        this.zzj = arg6;
        this.zzk = arg7;
        this.zzl = arg8;
        this.zzm = arg9;
        this.zzn = arg10;
        this.zzo = arg11;
    }

    zza() {
        super();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.name, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzf, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzg, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzh, false);
        SafeParcelWriter.writeString(arg4, 6, this.zzi, false);
        SafeParcelWriter.writeString(arg4, 7, this.zzj, false);
        SafeParcelWriter.writeString(arg4, 8, this.zzk, false);
        SafeParcelWriter.writeString(arg4, 9, this.zzl, false);
        SafeParcelWriter.writeString(arg4, 10, this.zzm, false);
        SafeParcelWriter.writeBoolean(arg4, 11, this.zzn);
        SafeParcelWriter.writeString(arg4, 12, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

