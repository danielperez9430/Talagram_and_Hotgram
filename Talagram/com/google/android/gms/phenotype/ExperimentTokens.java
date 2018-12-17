package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeepForSdk @Class(creator="ExperimentTokensCreator") @Reserved(value={1}) public class ExperimentTokens extends AbstractSafeParcelable {
    interface zza {
    }

    @KeepForSdk public static final Parcelable$Creator CREATOR;
    private static final zza zzaa;
    private static final byte[][] zzn;
    private static final ExperimentTokens zzo;
    @Field(id=2) private final String zzp;
    @Field(id=3) private final byte[] zzq;
    @Field(id=4) private final byte[][] zzr;
    @Field(id=5) private final byte[][] zzs;
    @Field(id=6) private final byte[][] zzt;
    @Field(id=7) private final byte[][] zzu;
    @Field(id=8) private final int[] zzv;
    @Field(id=9) private final byte[][] zzw;
    private static final zza zzx;
    private static final zza zzy;
    private static final zza zzz;

    static {
        ExperimentTokens.CREATOR = new zzh();
        ExperimentTokens.zzn = new byte[0][];
        ExperimentTokens.zzo = new ExperimentTokens("", null, ExperimentTokens.zzn, ExperimentTokens.zzn, ExperimentTokens.zzn, ExperimentTokens.zzn, null, null);
        ExperimentTokens.zzx = new zzd();
        ExperimentTokens.zzy = new zze();
        ExperimentTokens.zzz = new zzf();
        ExperimentTokens.zzaa = new zzg();
    }

    @Constructor public ExperimentTokens(@Param(id=2) String arg1, @Param(id=3) byte[] arg2, @Param(id=4) byte[][] arg3, @Param(id=5) byte[][] arg4, @Param(id=6) byte[][] arg5, @Param(id=7) byte[][] arg6, @Param(id=8) int[] arg7, @Param(id=9) byte[][] arg8) {
        super();
        this.zzp = arg1;
        this.zzq = arg2;
        this.zzr = arg3;
        this.zzs = arg4;
        this.zzt = arg5;
        this.zzu = arg6;
        this.zzv = arg7;
        this.zzw = arg8;
    }

    public boolean equals(Object arg4) {
        if(((arg4 instanceof ExperimentTokens)) && (zzn.equals(this.zzp, ((ExperimentTokens)arg4).zzp)) && (Arrays.equals(this.zzq, ((ExperimentTokens)arg4).zzq)) && (zzn.equals(ExperimentTokens.zza(this.zzr), ExperimentTokens.zza(((ExperimentTokens)arg4).zzr))) && (zzn.equals(ExperimentTokens.zza(this.zzs), ExperimentTokens.zza(((ExperimentTokens)arg4).zzs))) && (zzn.equals(ExperimentTokens.zza(this.zzt), ExperimentTokens.zza(((ExperimentTokens)arg4).zzt))) && (zzn.equals(ExperimentTokens.zza(this.zzu), ExperimentTokens.zza(((ExperimentTokens)arg4).zzu))) && (zzn.equals(ExperimentTokens.zza(this.zzv), ExperimentTokens.zza(((ExperimentTokens)arg4).zzv))) && (zzn.equals(ExperimentTokens.zza(this.zzw), ExperimentTokens.zza(((ExperimentTokens)arg4).zzw)))) {
            return 1;
        }

        return 0;
    }

    public String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder("ExperimentTokens");
        v0.append("(");
        if(this.zzp == null) {
            v1 = "null";
        }
        else {
            v1 = this.zzp;
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 2);
            v3.append("\'");
            v3.append(v1);
            v3.append("\'");
            v1 = v3.toString();
        }

        v0.append(v1);
        v0.append(", ");
        byte[] v2 = this.zzq;
        v0.append("direct");
        v0.append("=");
        if(v2 == null) {
            v1 = "null";
        }
        else {
            v0.append("\'");
            v0.append(Base64.encodeToString(v2, 3));
            v1 = "\'";
        }

        v0.append(v1);
        v0.append(", ");
        ExperimentTokens.zza(v0, "GAIA", this.zzr);
        v0.append(", ");
        ExperimentTokens.zza(v0, "PSEUDO", this.zzs);
        v0.append(", ");
        ExperimentTokens.zza(v0, "ALWAYS", this.zzt);
        v0.append(", ");
        ExperimentTokens.zza(v0, "OTHER", this.zzu);
        v0.append(", ");
        int[] v2_1 = this.zzv;
        v0.append("weak");
        v0.append("=");
        if(v2_1 == null) {
            v1 = "null";
        }
        else {
            v0.append("(");
            int v1_1 = v2_1.length;
            int v4 = 0;
            int v5;
            for(v5 = 1; v4 < v1_1; v5 = 0) {
                int v6 = v2_1[v4];
                if(v5 == 0) {
                    v0.append(", ");
                }

                v0.append(v6);
                ++v4;
            }

            v1 = ")";
        }

        v0.append(v1);
        v0.append(", ");
        ExperimentTokens.zza(v0, "directs", this.zzw);
        v0.append(")");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzp, false);
        SafeParcelWriter.writeByteArray(arg4, 3, this.zzq, false);
        SafeParcelWriter.writeByteArrayArray(arg4, 4, this.zzr, false);
        SafeParcelWriter.writeByteArrayArray(arg4, 5, this.zzs, false);
        SafeParcelWriter.writeByteArrayArray(arg4, 6, this.zzt, false);
        SafeParcelWriter.writeByteArrayArray(arg4, 7, this.zzu, false);
        SafeParcelWriter.writeIntArray(arg4, 8, this.zzv, false);
        SafeParcelWriter.writeByteArrayArray(arg4, 9, this.zzw, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    private static List zza(int[] arg4) {
        if(arg4 == null) {
            return Collections.emptyList();
        }

        ArrayList v0 = new ArrayList(arg4.length);
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((List)v0).add(Integer.valueOf(arg4[v2]));
        }

        Collections.sort(((List)v0));
        return ((List)v0);
    }

    private static List zza(byte[][] arg5) {
        if(arg5 == null) {
            return Collections.emptyList();
        }

        ArrayList v0 = new ArrayList(arg5.length);
        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((List)v0).add(Base64.encodeToString(arg5[v2], 3));
        }

        Collections.sort(((List)v0));
        return ((List)v0);
    }

    private static void zza(StringBuilder arg4, String arg5, byte[][] arg6) {
        arg4.append(arg5);
        arg4.append("=");
        if(arg6 == null) {
            arg5 = "null";
        }
        else {
            arg4.append("(");
            int v5 = arg6.length;
            int v1 = 0;
            int v2;
            for(v2 = 1; v1 < v5; v2 = 0) {
                byte[] v3 = arg6[v1];
                if(v2 == 0) {
                    arg4.append(", ");
                }

                arg4.append("\'");
                arg4.append(Base64.encodeToString(v3, 3));
                arg4.append("\'");
                ++v1;
            }

            arg5 = ")";
        }

        arg4.append(arg5);
    }
}

