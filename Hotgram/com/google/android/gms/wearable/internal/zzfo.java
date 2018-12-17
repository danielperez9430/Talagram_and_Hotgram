package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wearable.Node;

@Class(creator="NodeParcelableCreator") @Reserved(value={1}) public final class zzfo extends AbstractSafeParcelable implements Node {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getDisplayName", id=3) private final String zzbk;
    @Field(getter="getId", id=2) private final String zzdm;
    @Field(getter="getHopCount", id=4) private final int zzen;
    @Field(getter="isNearby", id=5) private final boolean zzeo;

    static {
        zzfo.CREATOR = new zzfp();
    }

    @Constructor public zzfo(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) int arg3, @Param(id=5) boolean arg4) {
        super();
        this.zzdm = arg1;
        this.zzbk = arg2;
        this.zzen = arg3;
        this.zzeo = arg4;
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof zzfo)) {
            return 0;
        }

        return ((zzfo)arg2).zzdm.equals(this.zzdm);
    }

    public final String getDisplayName() {
        return this.zzbk;
    }

    public final String getId() {
        return this.zzdm;
    }

    public final int hashCode() {
        return this.zzdm.hashCode();
    }

    public final boolean isNearby() {
        return this.zzeo;
    }

    public final String toString() {
        String v0 = this.zzbk;
        String v1 = this.zzdm;
        int v2 = this.zzen;
        boolean v3 = this.zzeo;
        StringBuilder v5 = new StringBuilder(String.valueOf(v0).length() + 45 + String.valueOf(v1).length());
        v5.append("Node{");
        v5.append(v0);
        v5.append(", id=");
        v5.append(v1);
        v5.append(", hops=");
        v5.append(v2);
        v5.append(", isNearby=");
        v5.append(v3);
        v5.append("}");
        return v5.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.getId(), false);
        SafeParcelWriter.writeString(arg4, 3, this.getDisplayName(), false);
        SafeParcelWriter.writeInt(arg4, 4, this.zzen);
        SafeParcelWriter.writeBoolean(arg4, 5, this.isNearby());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

