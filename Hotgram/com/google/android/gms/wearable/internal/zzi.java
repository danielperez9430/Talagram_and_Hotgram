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

@Class(creator="AmsEntityUpdateParcelableCreator") @Reserved(value={1}) public final class zzi extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getValue", id=4) private final String value;
    @Field(getter="getEntityId", id=2) private byte zzbd;
    @Field(getter="getAttributeId", id=3) private final byte zzbe;

    static {
        zzi.CREATOR = new zzj();
    }

    @Constructor public zzi(@Param(id=2) byte arg1, @Param(id=3) byte arg2, @Param(id=4) String arg3) {
        super();
        this.zzbd = arg1;
        this.zzbe = arg2;
        this.value = arg3;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzi)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.zzbd != ((zzi)arg5).zzbd) {
                return 0;
            }
            else if(this.zzbe != ((zzi)arg5).zzbe) {
                return 0;
            }
            else if(!this.value.equals(((zzi)arg5).value)) {
                return 0;
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return ((this.zzbd + 31) * 31 + this.zzbe) * 31 + this.value.hashCode();
    }

    public final String toString() {
        byte v0 = this.zzbd;
        byte v1 = this.zzbe;
        String v2 = this.value;
        StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 73);
        v4.append("AmsEntityUpdateParcelable{, mEntityId=");
        v4.append(v0);
        v4.append(", mAttributeId=");
        v4.append(v1);
        v4.append(", mValue=\'");
        v4.append(v2);
        v4.append('\'');
        v4.append('}');
        return v4.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeByte(arg4, 2, this.zzbd);
        SafeParcelWriter.writeByte(arg4, 3, this.zzbe);
        SafeParcelWriter.writeString(arg4, 4, this.value, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

