package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Class(creator="LocationSettingsRequestCreator") @Reserved(value={1000}) public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public final class Builder {
        private boolean zzbh;
        private boolean zzbi;
        private zzae zzbj;
        private final ArrayList zzbk;

        public Builder() {
            super();
            this.zzbk = new ArrayList();
            this.zzbh = false;
            this.zzbi = false;
            this.zzbj = null;
        }

        public final Builder addAllLocationRequests(Collection arg3) {
            Iterator v3 = arg3.iterator();
            while(v3.hasNext()) {
                Object v0 = v3.next();
                if(v0 == null) {
                    continue;
                }

                this.zzbk.add(v0);
            }

            return this;
        }

        public final Builder addLocationRequest(LocationRequest arg2) {
            if(arg2 != null) {
                this.zzbk.add(arg2);
            }

            return this;
        }

        public final LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.zzbk, this.zzbh, this.zzbi, null);
        }

        public final Builder setAlwaysShow(boolean arg1) {
            this.zzbh = arg1;
            return this;
        }

        public final Builder setNeedBle(boolean arg1) {
            this.zzbi = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(getter="getLocationRequests", id=1) private final List zzbg;
    @Field(defaultValue="false", getter="alwaysShow", id=2) private final boolean zzbh;
    @Field(getter="needBle", id=3) private final boolean zzbi;
    @Field(getter="getConfiguration", id=5) private zzae zzbj;

    static {
        LocationSettingsRequest.CREATOR = new zzag();
    }

    @Constructor LocationSettingsRequest(@Param(id=1) List arg1, @Param(id=2) boolean arg2, @Param(id=3) boolean arg3, @Param(id=5) zzae arg4) {
        super();
        this.zzbg = arg1;
        this.zzbh = arg2;
        this.zzbi = arg3;
        this.zzbj = arg4;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedList(arg5, 1, Collections.unmodifiableList(this.zzbg), false);
        SafeParcelWriter.writeBoolean(arg5, 2, this.zzbh);
        SafeParcelWriter.writeBoolean(arg5, 3, this.zzbi);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzbj, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

