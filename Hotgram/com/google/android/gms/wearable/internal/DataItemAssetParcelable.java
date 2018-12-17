package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wearable.DataItemAsset;

@KeepName @Class(creator="DataItemAssetParcelableCreator") @Reserved(value={1}) public class DataItemAssetParcelable extends AbstractSafeParcelable implements ReflectedParcelable, DataItemAsset {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getId", id=2) private final String zzdm;
    @Field(getter="getDataItemKey", id=3) private final String zzdn;

    static {
        DataItemAssetParcelable.CREATOR = new zzda();
    }

    @Constructor DataItemAssetParcelable(@Param(id=2) String arg1, @Param(id=3) String arg2) {
        super();
        this.zzdm = arg1;
        this.zzdn = arg2;
    }

    public DataItemAssetParcelable(DataItemAsset arg2) {
        super();
        this.zzdm = Preconditions.checkNotNull(arg2.getId());
        this.zzdn = Preconditions.checkNotNull(arg2.getDataItemKey());
    }

    public Object freeze() {
        return this;
    }

    public String getDataItemKey() {
        return this.zzdn;
    }

    public String getId() {
        return this.zzdm;
    }

    public boolean isDataValid() {
        return 1;
    }

    public String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append("DataItemAssetParcelable[");
        v0.append("@");
        v0.append(Integer.toHexString(this.hashCode()));
        if(this.zzdm == null) {
            v1 = ",noid";
        }
        else {
            v0.append(",");
            v1 = this.zzdm;
        }

        v0.append(v1);
        v0.append(", key=");
        v0.append(this.zzdn);
        v0.append("]");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.getId(), false);
        SafeParcelWriter.writeString(arg4, 3, this.getDataItemKey(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

