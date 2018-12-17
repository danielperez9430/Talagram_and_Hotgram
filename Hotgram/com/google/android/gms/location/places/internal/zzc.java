package com.google.android.gms.location.places.internal;

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

@Class(creator="SubstringEntityCreator") @Reserved(value={1000}) public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) final int length;
    @Field(id=1) final int offset;

    static {
        zzc.CREATOR = new zzaw();
    }

    @Constructor public zzc(@Param(id=1) int arg1, @Param(id=2) int arg2) {
        super();
        this.offset = arg1;
        this.length = arg2;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzc)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzc)) {
            return 0;
        }

        if((Objects.equal(Integer.valueOf(this.offset), Integer.valueOf(((zzc)arg5).offset))) && (Objects.equal(Integer.valueOf(this.length), Integer.valueOf(((zzc)arg5).length)))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.offset), Integer.valueOf(this.length)});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("offset", Integer.valueOf(this.offset)).add("length", Integer.valueOf(this.length)).toString();
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.offset);
        SafeParcelWriter.writeInt(arg3, 2, this.length);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

