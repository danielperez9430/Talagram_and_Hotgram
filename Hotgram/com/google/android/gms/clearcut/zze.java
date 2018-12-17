package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Class(creator="LogEventParcelableCreator") @Reserved(value={1}) public final class zze extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    public final zzha zzaa;
    @Field(id=2) public zzr zzag;
    @Field(id=3) public byte[] zzah;
    @Field(id=4) private int[] zzai;
    @Field(id=5) private String[] zzaj;
    @Field(id=6) private int[] zzak;
    @Field(id=7) private byte[][] zzal;
    @Field(id=9) private ExperimentTokens[] zzam;
    public final zzb zzan;
    public final zzb zzt;
    @Field(defaultValue="true", id=8) private boolean zzz;

    static {
        zze.CREATOR = new zzf();
    }

    public zze(zzr arg1, zzha arg2, zzb arg3, zzb arg4, int[] arg5, String[] arg6, int[] arg7, byte[][] arg8, ExperimentTokens[] arg9, boolean arg10) {
        super();
        this.zzag = arg1;
        this.zzaa = arg2;
        this.zzt = arg3;
        this.zzan = null;
        this.zzai = arg5;
        this.zzaj = null;
        this.zzak = arg7;
        this.zzal = null;
        this.zzam = null;
        this.zzz = arg10;
    }

    @Constructor zze(@Param(id=2) zzr arg1, @Param(id=3) byte[] arg2, @Param(id=4) int[] arg3, @Param(id=5) String[] arg4, @Param(id=6) int[] arg5, @Param(id=7) byte[][] arg6, @Param(id=8) boolean arg7, @Param(id=9) ExperimentTokens[] arg8) {
        super();
        this.zzag = arg1;
        this.zzah = arg2;
        this.zzai = arg3;
        this.zzaj = arg4;
        this.zzaa = null;
        this.zzt = null;
        this.zzan = null;
        this.zzak = arg5;
        this.zzal = arg6;
        this.zzam = arg8;
        this.zzz = arg7;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zze)arg5))) {
            return 1;
        }

        if(((arg5 instanceof zze)) && (Objects.equal(this.zzag, ((zze)arg5).zzag)) && (Arrays.equals(this.zzah, ((zze)arg5).zzah)) && (Arrays.equals(this.zzai, ((zze)arg5).zzai)) && (Arrays.equals(this.zzaj, ((zze)arg5).zzaj)) && (Objects.equal(this.zzaa, ((zze)arg5).zzaa)) && (Objects.equal(this.zzt, ((zze)arg5).zzt)) && (Objects.equal(this.zzan, ((zze)arg5).zzan)) && (Arrays.equals(this.zzak, ((zze)arg5).zzak)) && (Arrays.deepEquals(this.zzal, ((zze)arg5).zzal)) && (Arrays.equals(this.zzam, ((zze)arg5).zzam)) && this.zzz == ((zze)arg5).zzz) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzag, this.zzah, this.zzai, this.zzaj, this.zzaa, this.zzt, this.zzan, this.zzak, this.zzal, this.zzam, Boolean.valueOf(this.zzz)});
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder("LogEventParcelable[");
        v0.append(this.zzag);
        v0.append(", LogEventBytes: ");
        String v1 = this.zzah == null ? null : new String(this.zzah);
        v0.append(v1);
        v0.append(", TestCodes: ");
        v0.append(Arrays.toString(this.zzai));
        v0.append(", MendelPackages: ");
        v0.append(Arrays.toString(this.zzaj));
        v0.append(", LogEvent: ");
        v0.append(this.zzaa);
        v0.append(", ExtensionProducer: ");
        v0.append(this.zzt);
        v0.append(", VeProducer: ");
        v0.append(this.zzan);
        v0.append(", ExperimentIDs: ");
        v0.append(Arrays.toString(this.zzak));
        v0.append(", ExperimentTokens: ");
        v0.append(Arrays.toString(this.zzal));
        v0.append(", ExperimentTokensParcelables: ");
        v0.append(Arrays.toString(this.zzam));
        v0.append(", AddPhenotypeExperimentTokens: ");
        v0.append(this.zzz);
        v0.append("]");
        return v0.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzag, arg6, false);
        SafeParcelWriter.writeByteArray(arg5, 3, this.zzah, false);
        SafeParcelWriter.writeIntArray(arg5, 4, this.zzai, false);
        SafeParcelWriter.writeStringArray(arg5, 5, this.zzaj, false);
        SafeParcelWriter.writeIntArray(arg5, 6, this.zzak, false);
        SafeParcelWriter.writeByteArrayArray(arg5, 7, this.zzal, false);
        SafeParcelWriter.writeBoolean(arg5, 8, this.zzz);
        SafeParcelWriter.writeTypedArray(arg5, 9, this.zzam, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

