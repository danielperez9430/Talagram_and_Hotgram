package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Set;

@Class(creator="UserDataTypeCreator") @Reserved(value={1000}) public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private final String type;
    private static final zzo zzff;
    private static final zzo zzfg;
    private static final zzo zzfh;
    private static final Set zzfi;
    @Field(id=2) private final int zzfj;

    static {
        zzo.zzff = zzo.zzb("test_type", 1);
        zzo.zzfg = zzo.zzb("labeled_place", 6);
        zzo.zzfh = zzo.zzb("here_content", 7);
        zzo.zzfi = CollectionUtils.setOf(zzo.zzff, zzo.zzfg, zzo.zzfh);
        zzo.CREATOR = new zzp();
    }

    @Constructor zzo(@Param(id=1) String arg1, @Param(id=2) int arg2) {
        super();
        Preconditions.checkNotEmpty(arg1);
        this.type = arg1;
        this.zzfj = arg2;
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzo)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof zzo)) {
            return 0;
        }

        if((this.type.equals(((zzo)arg5).type)) && this.zzfj == ((zzo)arg5).zzfj) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return this.type.hashCode();
    }

    public final String toString() {
        return this.type;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.type, false);
        SafeParcelWriter.writeInt(arg4, 2, this.zzfj);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    private static zzo zzb(String arg1, int arg2) {
        return new zzo(arg1, arg2);
    }
}

