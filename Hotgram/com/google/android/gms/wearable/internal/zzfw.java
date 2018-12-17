package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@Class(creator="RemoveListenerRequestCreator") public final class zzfw extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int versionCode;
    @Field(getter="getListenerAsBinder", id=2, type="android.os.IBinder") private final zzem zzaz;

    static {
        zzfw.CREATOR = new zzfx();
    }

    public zzfw(zzem arg2) {
        super();
        this.versionCode = 1;
        this.zzaz = arg2;
    }

    @Constructor zzfw(@Param(id=1) int arg2, @Param(id=2) IBinder arg3) {
        super();
        this.versionCode = arg2;
        zzem v2 = null;
        if(arg3 != null) {
            if(arg3 == null) {
            }
            else {
                IInterface v2_1 = arg3.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
                if((v2_1 instanceof zzem)) {
                }
                else {
                    zzeo v2_2 = new zzeo(arg3);
                }
            }

            this.zzaz = v2;
            return;
        }

        this.zzaz = v2;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.versionCode);
        IBinder v0 = this.zzaz == null ? null : this.zzaz.asBinder();
        SafeParcelWriter.writeIBinder(arg4, 2, v0, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

