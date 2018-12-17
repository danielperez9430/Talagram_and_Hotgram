package com.google.android.gms.internal.location;

import android.app.PendingIntent;
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
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzx;
import com.google.android.gms.location.zzy;

@Class(creator="LocationRequestUpdateDataCreator") @Reserved(value={1000}) public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="null", id=4) private PendingIntent zzbv;
    @Field(defaultValueUnchecked="LocationRequestUpdateData.OPERATION_ADD", id=1) private int zzcg;
    @Field(defaultValueUnchecked="null", getter="getFusedLocationProviderCallbackBinder", id=6, type="android.os.IBinder") private zzaj zzcj;
    @Field(defaultValueUnchecked="null", id=2) private zzbd zzdl;
    @Field(defaultValueUnchecked="null", getter="getLocationListenerBinder", id=3, type="android.os.IBinder") private zzx zzdm;
    @Field(defaultValueUnchecked="null", getter="getLocationCallbackBinder", id=5, type="android.os.IBinder") private zzu zzdn;

    static {
        zzbf.CREATOR = new zzbg();
    }

    @Constructor zzbf(@Param(id=1) int arg1, @Param(id=2) zzbd arg2, @Param(id=3) IBinder arg3, @Param(id=4) PendingIntent arg4, @Param(id=5) IBinder arg5, @Param(id=6) IBinder arg6) {
        super();
        this.zzcg = arg1;
        this.zzdl = arg2;
        zzaj v1 = null;
        zzx v2 = arg3 == null ? ((zzx)v1) : zzy.zzc(arg3);
        this.zzdm = v2;
        this.zzbv = arg4;
        zzu v2_1 = arg5 == null ? ((zzu)v1) : zzv.zzb(arg5);
        this.zzdn = v2_1;
        if(arg6 == null) {
        }
        else if(arg6 == null) {
        }
        else {
            IInterface v1_1 = arg6.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            if((v1_1 instanceof zzaj)) {
            }
            else {
                zzal v1_2 = new zzal(arg6);
            }
        }

        this.zzcj = v1;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeInt(arg6, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(arg6, 2, this.zzdl, arg7, false);
        IBinder v3 = null;
        IBinder v1 = this.zzdm == null ? v3 : this.zzdm.asBinder();
        SafeParcelWriter.writeIBinder(arg6, 3, v1, false);
        SafeParcelWriter.writeParcelable(arg6, 4, this.zzbv, arg7, false);
        arg7 = 5;
        v1 = this.zzdn == null ? v3 : this.zzdn.asBinder();
        SafeParcelWriter.writeIBinder(arg6, arg7, v1, false);
        arg7 = 6;
        if(this.zzcj == null) {
        }
        else {
            v3 = this.zzcj.asBinder();
        }

        SafeParcelWriter.writeIBinder(arg6, arg7, v3, false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }

    public static zzbf zza(zzx arg8, zzaj arg9) {
        zzbf v7 = null;
        IBinder v3 = arg8.asBinder();
        IBinder v8 = arg9 != null ? arg9.asBinder() : null;
        IBinder v6 = v8;
        this(2, null, v3, null, null, v6);
        return v7;
    }

    public static zzbf zza(zzu arg8, zzaj arg9) {
        zzbf v7 = null;
        IBinder v5 = arg8.asBinder();
        IBinder v8 = arg9 != null ? arg9.asBinder() : null;
        IBinder v6 = v8;
        this(2, null, null, null, v5, v6);
        return v7;
    }
}

