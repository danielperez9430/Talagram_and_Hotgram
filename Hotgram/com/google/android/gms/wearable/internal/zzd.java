package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import javax.annotation.Nullable;

@Class(creator="AddListenerRequestCreator") @Reserved(value={1}) public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getListenerAsBinder", id=2, type="android.os.IBinder") private final zzem zzaz;
    @Field(id=3) private final IntentFilter[] zzba;
    @Field(id=4) @Nullable private final String zzbb;
    @Field(id=5) @Nullable private final String zzbc;

    static {
        zzd.CREATOR = new zze();
    }

    @Constructor zzd(@Param(id=2) IBinder arg3, @Param(id=3) IntentFilter[] arg4, @Param(id=4) @Nullable String arg5, @Param(id=5) @Nullable String arg6) {
        IInterface v0_1;
        super();
        zzem v0 = null;
        if(arg3 != null) {
            if(arg3 == null) {
            }
            else {
                v0_1 = arg3.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
                if((v0_1 instanceof zzem)) {
                }
                else {
                    zzeo v0_2 = new zzeo(arg3);
                }
            }
        }

        this.zzaz = ((zzem)v0_1);
        this.zzba = arg4;
        this.zzbb = arg5;
        this.zzbc = arg6;
    }

    public zzd(zzhk arg2) {
        super();
        this.zzaz = ((zzem)arg2);
        this.zzba = arg2.zze();
        this.zzbb = arg2.zzf();
        this.zzbc = null;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        IBinder v1 = this.zzaz == null ? null : this.zzaz.asBinder();
        SafeParcelWriter.writeIBinder(arg5, 2, v1, false);
        SafeParcelWriter.writeTypedArray(arg5, 3, this.zzba, arg6, false);
        SafeParcelWriter.writeString(arg5, 4, this.zzbb, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzbc, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

