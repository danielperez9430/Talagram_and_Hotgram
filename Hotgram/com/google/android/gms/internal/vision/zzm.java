package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.vision.Frame;

@Class(creator="FrameMetadataParcelCreator") @Reserved(value={1}) public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) public int height;
    @Field(id=4) private int id;
    @Field(id=6) public int rotation;
    @Field(id=2) public int width;
    @Field(id=5) private long zzat;

    static {
        zzm.CREATOR = new zzn();
    }

    @Constructor public zzm(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) long arg4, @Param(id=6) int arg6) {
        super();
        this.width = arg1;
        this.height = arg2;
        this.id = arg3;
        this.zzat = arg4;
        this.rotation = arg6;
    }

    public zzm() {
        super();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.width);
        SafeParcelWriter.writeInt(arg4, 3, this.height);
        SafeParcelWriter.writeInt(arg4, 4, this.id);
        SafeParcelWriter.writeLong(arg4, 5, this.zzat);
        SafeParcelWriter.writeInt(arg4, 6, this.rotation);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public static zzm zzc(Frame arg3) {
        zzm v0 = new zzm();
        v0.width = arg3.getMetadata().getWidth();
        v0.height = arg3.getMetadata().getHeight();
        v0.rotation = arg3.getMetadata().getRotation();
        v0.id = arg3.getMetadata().getId();
        v0.zzat = arg3.getMetadata().getTimestampMillis();
        return v0;
    }
}

