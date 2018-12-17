package com.google.android.gms.internal.location;

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
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzs;

@Class(creator="DeviceOrientationRequestUpdateDataCreator") public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="DeviceOrientationRequestUpdateData.OPERATION_ADD", id=1) private int zzcg;
    @Field(defaultValueUnchecked="null", id=2) private zzm zzch;
    @Field(defaultValueUnchecked="null", getter="getDeviceOrientationListenerBinder", id=3, type="android.os.IBinder") private zzr zzci;
    @Field(defaultValueUnchecked="null", getter="getFusedLocationProviderCallbackBinder", id=4, type="android.os.IBinder") private zzaj zzcj;

    static {
        zzo.CREATOR = new zzp();
    }

    @Constructor zzo(@Param(id=1) int arg1, @Param(id=2) zzm arg2, @Param(id=3) IBinder arg3, @Param(id=4) IBinder arg4) {
        super();
        this.zzcg = arg1;
        this.zzch = arg2;
        zzaj v1 = null;
        zzr v2 = arg3 == null ? ((zzr)v1) : zzs.zza(arg3);
        this.zzci = v2;
        if(arg4 == null) {
        }
        else if(arg4 == null) {
        }
        else {
            IInterface v1_1 = arg4.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            if((v1_1 instanceof zzaj)) {
            }
            else {
                zzal v1_2 = new zzal(arg4);
            }
        }

        this.zzcj = v1;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzch, arg6, false);
        IBinder v1 = null;
        IBinder v6 = this.zzci == null ? v1 : this.zzci.asBinder();
        SafeParcelWriter.writeIBinder(arg5, 3, v6, false);
        arg6 = 4;
        if(this.zzcj == null) {
        }
        else {
            v1 = this.zzcj.asBinder();
        }

        SafeParcelWriter.writeIBinder(arg5, arg6, v1, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

