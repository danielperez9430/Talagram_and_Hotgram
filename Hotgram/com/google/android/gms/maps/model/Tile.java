package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="TileCreator") @Reserved(value={1}) public final class Tile extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=4) public final byte[] data;
    @Field(id=3) public final int height;
    @Field(id=2) public final int width;

    static {
        Tile.CREATOR = new zzr();
    }

    @Constructor public Tile(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) byte[] arg3) {
        super();
        this.width = arg1;
        this.height = arg2;
        this.data = arg3;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.width);
        SafeParcelWriter.writeInt(arg4, 3, this.height);
        SafeParcelWriter.writeByteArray(arg4, 4, this.data, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

