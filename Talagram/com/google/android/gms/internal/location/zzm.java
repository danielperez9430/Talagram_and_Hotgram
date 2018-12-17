package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

@Class(creator="DeviceOrientationRequestInternalCreator") public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="null", id=3) private String tag;
    @VisibleForTesting static final List zzcd;
    static final zzj zzce;
    @Field(defaultValueUnchecked="DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id=1) private zzj zzcf;
    @Field(defaultValueUnchecked="DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id=2) private List zzm;

    static {
        zzm.zzcd = Collections.emptyList();
        zzm.zzce = new zzj();
        zzm.CREATOR = new zzn();
    }

    @Constructor zzm(@Param(id=1) zzj arg1, @Param(id=2) List arg2, @Param(id=3) String arg3) {
        super();
        this.zzcf = arg1;
        this.zzm = arg2;
        this.tag = arg3;
    }

    public final boolean equals(Object arg4) {
        if(!(arg4 instanceof zzm)) {
            return 0;
        }

        if((Objects.equal(this.zzcf, ((zzm)arg4).zzcf)) && (Objects.equal(this.zzm, ((zzm)arg4).zzm)) && (Objects.equal(this.tag, ((zzm)arg4).tag))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzcf.hashCode();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.zzcf, arg6, false);
        SafeParcelWriter.writeTypedList(arg5, 2, this.zzm, false);
        SafeParcelWriter.writeString(arg5, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

