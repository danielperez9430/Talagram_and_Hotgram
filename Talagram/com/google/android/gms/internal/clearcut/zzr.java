package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PlayLoggerContextCreator") @Reserved(value={1}) public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final String packageName;
    @Field(defaultValue="true", id=7) private final boolean zzay;
    @Field(id=10) private final int zzaz;
    @Field(id=3) private final int zzi;
    @Field(id=8) public final String zzj;
    @Field(id=4) public final int zzk;
    @Field(id=5) private final String zzl;
    @Field(id=6) private final String zzm;
    @Field(id=9) private final boolean zzn;

    static {
        zzr.CREATOR = new zzs();
    }

    public zzr(String arg1, int arg2, int arg3, String arg4, String arg5, String arg6, boolean arg7, zzb arg8) {
        super();
        this.packageName = Preconditions.checkNotNull(arg1);
        this.zzi = arg2;
        this.zzk = arg3;
        this.zzj = arg4;
        this.zzl = arg5;
        this.zzm = arg6;
        this.zzay = (((int)arg7)) ^ 1;
        this.zzn = arg7;
        this.zzaz = arg8.zzc();
    }

    @Constructor public zzr(@Param(id=2) String arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) String arg4, @Param(id=6) String arg5, @Param(id=7) boolean arg6, @Param(id=8) String arg7, @Param(id=9) boolean arg8, @Param(id=10) int arg9) {
        super();
        this.packageName = arg1;
        this.zzi = arg2;
        this.zzk = arg3;
        this.zzl = arg4;
        this.zzm = arg5;
        this.zzay = arg6;
        this.zzj = arg7;
        this.zzn = arg8;
        this.zzaz = arg9;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzr)arg5))) {
            return 1;
        }

        if(((arg5 instanceof zzr)) && (Objects.equal(this.packageName, ((zzr)arg5).packageName)) && this.zzi == ((zzr)arg5).zzi && this.zzk == ((zzr)arg5).zzk && (Objects.equal(this.zzj, ((zzr)arg5).zzj)) && (Objects.equal(this.zzl, ((zzr)arg5).zzl)) && (Objects.equal(this.zzm, ((zzr)arg5).zzm)) && this.zzay == ((zzr)arg5).zzay && this.zzn == ((zzr)arg5).zzn && this.zzaz == ((zzr)arg5).zzaz) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.packageName, Integer.valueOf(this.zzi), Integer.valueOf(this.zzk), this.zzj, this.zzl, this.zzm, Boolean.valueOf(this.zzay), Boolean.valueOf(this.zzn), Integer.valueOf(this.zzaz)});
    }

    public final String toString() {
        return "PlayLoggerContext[" + "package=" + this.packageName + ',' + "packageVersionCode=" + this.zzi + ',' + "logSource=" + this.zzk + ',' + "logSourceName=" + this.zzj + ',' + "uploadAccount=" + this.zzl + ',' + "loggingId=" + this.zzm + ',' + "logAndroidId=" + this.zzay + ',' + "isAnonymous=" + this.zzn + ',' + "qosTier=" + this.zzaz + "]";
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.packageName, false);
        SafeParcelWriter.writeInt(arg4, 3, this.zzi);
        SafeParcelWriter.writeInt(arg4, 4, this.zzk);
        SafeParcelWriter.writeString(arg4, 5, this.zzl, false);
        SafeParcelWriter.writeString(arg4, 6, this.zzm, false);
        SafeParcelWriter.writeBoolean(arg4, 7, this.zzay);
        SafeParcelWriter.writeString(arg4, 8, this.zzj, false);
        SafeParcelWriter.writeBoolean(arg4, 9, this.zzn);
        SafeParcelWriter.writeInt(arg4, 10, this.zzaz);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

