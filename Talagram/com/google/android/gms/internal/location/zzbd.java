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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

@Class(creator="LocationRequestInternalCreator") @Reserved(value={1000, 2, 3, 4}) public final class zzbd extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="null", id=10) private String moduleId;
    @Field(defaultValueUnchecked="null", id=6) private String tag;
    static final List zzcd;
    @Field(defaultValueUnchecked="null", id=1) private LocationRequest zzdg;
    @Field(defaultValueUnchecked="LocationRequestInternal.DEFAULT_HIDE_FROM_APP_OPS", id=7) private boolean zzdh;
    @Field(defaultValueUnchecked="LocationRequestInternal.DEFAULT_FORCE_COARSE_LOCATION", id=8) private boolean zzdi;
    @Field(defaultValueUnchecked="LocationRequestInternal.DEFAULT_EXEMPT_FROM_THROTTLE", id=9) private boolean zzdj;
    private boolean zzdk;
    @Field(defaultValueUnchecked="LocationRequestInternal.DEFAULT_CLIENTS", id=5) private List zzm;

    static {
        zzbd.zzcd = Collections.emptyList();
        zzbd.CREATOR = new zzbe();
    }

    @Constructor zzbd(@Param(id=1) LocationRequest arg2, @Param(id=5) List arg3, @Param(id=6) String arg4, @Param(id=7) boolean arg5, @Param(id=8) boolean arg6, @Param(id=9) boolean arg7, @Param(id=10) String arg8) {
        super();
        this.zzdk = true;
        this.zzdg = arg2;
        this.zzm = arg3;
        this.tag = arg4;
        this.zzdh = arg5;
        this.zzdi = arg6;
        this.zzdj = arg7;
        this.moduleId = arg8;
    }

    public final boolean equals(Object arg4) {
        if(!(arg4 instanceof zzbd)) {
            return 0;
        }

        if((Objects.equal(this.zzdg, ((zzbd)arg4).zzdg)) && (Objects.equal(this.zzm, ((zzbd)arg4).zzm)) && (Objects.equal(this.tag, ((zzbd)arg4).tag)) && this.zzdh == ((zzbd)arg4).zzdh && this.zzdi == ((zzbd)arg4).zzdi && this.zzdj == ((zzbd)arg4).zzdj && (Objects.equal(this.moduleId, ((zzbd)arg4).moduleId))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzdg.hashCode();
    }

    public final String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.zzdg);
        if(this.tag != null) {
            v0.append(" tag=");
            v0.append(this.tag);
        }

        if(this.moduleId != null) {
            v0.append(" moduleId=");
            v0.append(this.moduleId);
        }

        v0.append(" hideAppOps=");
        v0.append(this.zzdh);
        v0.append(" clients=");
        v0.append(this.zzm);
        v0.append(" forceCoarseLocation=");
        v0.append(this.zzdi);
        if(this.zzdj) {
            v0.append(" exemptFromBackgroundThrottle");
        }

        return v0.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.zzdg, arg6, false);
        SafeParcelWriter.writeTypedList(arg5, 5, this.zzm, false);
        SafeParcelWriter.writeString(arg5, 6, this.tag, false);
        SafeParcelWriter.writeBoolean(arg5, 7, this.zzdh);
        SafeParcelWriter.writeBoolean(arg5, 8, this.zzdi);
        SafeParcelWriter.writeBoolean(arg5, 9, this.zzdj);
        SafeParcelWriter.writeString(arg5, 10, this.moduleId, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    @Deprecated public static zzbd zza(LocationRequest arg9) {
        return new zzbd(arg9, zzbd.zzcd, null, false, false, false, null);
    }
}

