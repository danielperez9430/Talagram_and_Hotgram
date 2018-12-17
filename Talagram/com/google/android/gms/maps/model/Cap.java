package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;

@Class(creator="CapCreator") @Reserved(value={1}) public class Cap extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    private static final String TAG = "Cap";
    @Field(getter="getWrappedBitmapDescriptorImplBinder", id=3, type="android.os.IBinder") private final BitmapDescriptor bitmapDescriptor;
    @Field(getter="getType", id=2) private final int type;
    @Field(getter="getBitmapRefWidth", id=4) private final Float zzcm;

    static {
        Cap.CREATOR = new zzb();
    }

    @Constructor Cap(@Param(id=2) int arg2, @Param(id=3) IBinder arg3, @Param(id=4) Float arg4) {
        BitmapDescriptor v3 = arg3 == null ? null : new BitmapDescriptor(Stub.asInterface(arg3));
        this(arg2, v3, arg4);
    }

    protected Cap(int arg2) {
        this(arg2, null, null);
    }

    private Cap(int arg7, BitmapDescriptor arg8, Float arg9) {
        boolean v2_1;
        super();
        int v2 = arg9 == null || arg9.floatValue() <= 0f ? 0 : 1;
        int v3 = 3;
        if(arg7 == v3) {
            if(arg8 != null && v2 != 0) {
                goto label_17;
            }

            v2_1 = false;
        }
        else {
        label_17:
            v2_1 = true;
        }

        Preconditions.checkArgument(v2_1, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(arg7), arg8, arg9));
        this.type = arg7;
        this.bitmapDescriptor = arg8;
        this.zzcm = arg9;
    }

    protected Cap(BitmapDescriptor arg2, float arg3) {
        this(3, arg2, Float.valueOf(arg3));
    }

    public boolean equals(Object arg5) {
        if(this == (((Cap)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Cap)) {
            return 0;
        }

        if(this.type == ((Cap)arg5).type && (Objects.equal(this.bitmapDescriptor, ((Cap)arg5).bitmapDescriptor)) && (Objects.equal(this.zzcm, ((Cap)arg5).zzcm))) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.type), this.bitmapDescriptor, this.zzcm});
    }

    public String toString() {
        int v0 = this.type;
        StringBuilder v1 = new StringBuilder(23);
        v1.append("[Cap: type=");
        v1.append(v0);
        v1.append("]");
        return v1.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.type);
        IBinder v0 = this.bitmapDescriptor == null ? null : this.bitmapDescriptor.zza().asBinder();
        SafeParcelWriter.writeIBinder(arg4, 3, v0, false);
        SafeParcelWriter.writeFloatObject(arg4, 4, this.zzcm, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    final Cap zzg() {
        switch(this.type) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                goto label_13;
            }
        }

        String v0 = Cap.TAG;
        int v1 = this.type;
        StringBuilder v3 = new StringBuilder(29);
        v3.append("Unknown Cap type: ");
        v3.append(v1);
        Log.w(v0, v3.toString());
        return this;
    label_19:
        return new RoundCap();
    label_22:
        return new SquareCap();
    label_25:
        return new ButtCap();
    label_13:
        return new CustomCap(this.bitmapDescriptor, this.zzcm.floatValue());
    }
}

