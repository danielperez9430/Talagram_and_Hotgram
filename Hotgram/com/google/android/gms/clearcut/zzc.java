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

@Class(creator="CollectForDebugParcelableCreator") public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValue="false", id=1) private final boolean zzad;
    @Field(id=3) private final long zzae;
    @Field(id=2) private final long zzaf;

    static {
        zzc.CREATOR = new zzd();
    }

    @Constructor public zzc(@Param(id=1) boolean arg1, @Param(id=3) long arg2, @Param(id=2) long arg4) {
        super();
        this.zzad = arg1;
        this.zzae = arg2;
        this.zzaf = arg4;
    }

    public final boolean equals(Object arg8) {
        if(this == (((zzc)arg8))) {
            return 1;
        }

        if(((arg8 instanceof zzc)) && this.zzad == ((zzc)arg8).zzad && this.zzae == ((zzc)arg8).zzae && this.zzaf == ((zzc)arg8).zzaf) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zzad), Long.valueOf(this.zzae), Long.valueOf(this.zzaf)});
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder("CollectForDebugParcelable[skipPersistentStorage: ");
        v0.append(this.zzad);
        v0.append(",collectForDebugStartTimeMillis: ");
        v0.append(this.zzae);
        v0.append(",collectForDebugExpiryTimeMillis: ");
        v0.append(this.zzaf);
        v0.append("]");
        return v0.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBoolean(arg4, 1, this.zzad);
        SafeParcelWriter.writeLong(arg4, 2, this.zzaf);
        SafeParcelWriter.writeLong(arg4, 3, this.zzae);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

