package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="StreetViewSourceCreator") @Reserved(value={1}) public final class StreetViewSource extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final StreetViewSource DEFAULT = null;
    public static final StreetViewSource OUTDOOR = null;
    private static final String TAG = "StreetViewSource";
    @Field(getter="getType", id=2) private final int type;

    static {
        StreetViewSource.CREATOR = new zzq();
        StreetViewSource.DEFAULT = new StreetViewSource(0);
        StreetViewSource.OUTDOOR = new StreetViewSource(1);
    }

    @Constructor public StreetViewSource(@Param(id=2) int arg1) {
        super();
        this.type = arg1;
    }

    public final boolean equals(Object arg4) {
        if(this == (((StreetViewSource)arg4))) {
            return 1;
        }

        if(!(arg4 instanceof StreetViewSource)) {
            return 0;
        }

        if(this.type == ((StreetViewSource)arg4).type) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.type)});
    }

    public final String toString() {
        String v0;
        switch(this.type) {
            case 0: {
                v0 = "DEFAULT";
                break;
            }
            case 1: {
                v0 = "OUTDOOR";
                break;
            }
            default: {
                v0 = String.format("UNKNOWN(%s)", Integer.valueOf(this.type));
                break;
            }
        }

        return String.format("StreetViewSource:%s", v0);
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 2, this.type);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

