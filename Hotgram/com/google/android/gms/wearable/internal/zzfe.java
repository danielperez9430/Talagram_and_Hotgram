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
import com.google.android.gms.wearable.MessageEvent;

@Class(creator="MessageEventParcelableCreator") @Reserved(value={1}) public final class zzfe extends AbstractSafeParcelable implements MessageEvent {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getData", id=4) private final byte[] data;
    @Field(getter="getPath", id=3) private final String zzcl;
    @Field(getter="getRequestId", id=2) private final int zzeh;
    @Field(getter="getSourceNodeId", id=5) private final String zzek;

    static {
        zzfe.CREATOR = new zzff();
    }

    @Constructor public zzfe(@Param(id=2) int arg1, @Param(id=3) String arg2, @Param(id=4) byte[] arg3, @Param(id=5) String arg4) {
        super();
        this.zzeh = arg1;
        this.zzcl = arg2;
        this.data = arg3;
        this.zzek = arg4;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String getPath() {
        return this.zzcl;
    }

    public final int getRequestId() {
        return this.zzeh;
    }

    public final String getSourceNodeId() {
        return this.zzek;
    }

    public final String toString() {
        Integer v2_1;
        String v2;
        int v0 = this.zzeh;
        String v1 = this.zzcl;
        if(this.data == null) {
            v2 = "null";
        }
        else {
            v2_1 = Integer.valueOf(this.data.length);
        }

        v2 = String.valueOf(v2_1);
        StringBuilder v4 = new StringBuilder(String.valueOf(v1).length() + 43 + String.valueOf(v2).length());
        v4.append("MessageEventParcelable[");
        v4.append(v0);
        v4.append(",");
        v4.append(v1);
        v4.append(", size=");
        v4.append(v2);
        v4.append("]");
        return v4.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.getRequestId());
        SafeParcelWriter.writeString(arg4, 3, this.getPath(), false);
        SafeParcelWriter.writeByteArray(arg4, 4, this.getData(), false);
        SafeParcelWriter.writeString(arg4, 5, this.getSourceNodeId(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

