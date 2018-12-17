package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@KeepForSdk @Class(creator="ConfigurationCreator") @Reserved(value={1}) public class Configuration extends AbstractSafeParcelable implements Comparable {
    @KeepForSdk public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final int zzc;
    @Field(id=3) private final zzi[] zzd;
    @Field(id=4) private final String[] zze;
    private final Map zzf;

    static {
        Configuration.CREATOR = new zzc();
    }

    @Constructor public Configuration(@Param(id=2) int arg5, @Param(id=3) zzi[] arg6, @Param(id=4) String[] arg7) {
        super();
        this.zzc = arg5;
        this.zzd = arg6;
        this.zzf = new TreeMap();
        arg5 = arg6.length;
        int v0;
        for(v0 = 0; v0 < arg5; ++v0) {
            this.zzf.put(arg6[v0].name, arg6[v0]);
        }

        this.zze = arg7;
        if(this.zze != null) {
            Arrays.sort(this.zze);
        }
    }

    public int compareTo(Object arg2) {
        return this.zzc - ((Configuration)arg2).zzc;
    }

    public boolean equals(Object arg4) {
        if(((arg4 instanceof Configuration)) && this.zzc == ((Configuration)arg4).zzc && (zzn.equals(this.zzf, ((Configuration)arg4).zzf)) && (Arrays.equals(this.zze, ((Configuration)arg4).zze))) {
            return 1;
        }

        return 0;
    }

    public String toString() {
        int v3;
        String[] v1_1;
        StringBuilder v0 = new StringBuilder("Configuration(");
        v0.append(this.zzc);
        v0.append(", ");
        v0.append("(");
        Iterator v1 = this.zzf.values().iterator();
        while(v1.hasNext()) {
            v0.append(v1.next());
            v0.append(", ");
        }

        v0.append(")");
        v0.append(", ");
        v0.append("(");
        if(this.zze != null) {
            v1_1 = this.zze;
            int v2 = v1_1.length;
            v3 = 0;
            goto label_30;
        }
        else {
            v0.append("null");
            goto label_39;
        label_30:
            while(v3 < v2) {
                v0.append(v1_1[v3]);
                v0.append(", ");
                ++v3;
            }
        }

    label_39:
        v0.append(")");
        v0.append(")");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.zzc);
        SafeParcelWriter.writeTypedArray(arg5, 3, this.zzd, arg6, false);
        SafeParcelWriter.writeStringArray(arg5, 4, this.zze, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

