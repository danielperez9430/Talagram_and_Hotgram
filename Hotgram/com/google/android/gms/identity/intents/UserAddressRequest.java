package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Class(creator="UserAddressRequestCreator") @Reserved(value={1}) public final class UserAddressRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(UserAddressRequest arg1, zzc arg2) {
            this(arg1);
        }

        private Builder(UserAddressRequest arg1) {
            this.zzg = arg1;
            super();
        }

        public final Builder addAllowedCountrySpecification(CountrySpecification arg3) {
            if(this.zzg.zzf == null) {
                this.zzg.zzf = new ArrayList();
            }

            this.zzg.zzf.add(arg3);
            return this;
        }

        public final Builder addAllowedCountrySpecifications(Collection arg3) {
            if(this.zzg.zzf == null) {
                this.zzg.zzf = new ArrayList();
            }

            this.zzg.zzf.addAll(arg3);
            return this;
        }

        public final UserAddressRequest build() {
            if(this.zzg.zzf != null) {
                this.zzg.zzf = Collections.unmodifiableList(this.zzg.zzf);
            }

            return this.zzg;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) List zzf;

    static {
        UserAddressRequest.CREATOR = new zzd();
    }

    @Constructor UserAddressRequest(@Param(id=2) List arg1) {
        super();
        this.zzf = arg1;
    }

    UserAddressRequest() {
        super();
    }

    public static Builder newBuilder() {
        return new Builder(new UserAddressRequest(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 2, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

