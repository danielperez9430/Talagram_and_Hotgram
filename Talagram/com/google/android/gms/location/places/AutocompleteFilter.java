package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Class(creator="AutocompleteFilterCreator") public class AutocompleteFilter extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        private boolean zzdt;
        private String zzdv;
        private int zzdw;

        public Builder() {
            super();
            this.zzdt = false;
            this.zzdw = 0;
            this.zzdv = "";
        }

        public final AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(new Integer[]{Integer.valueOf(this.zzdw)}), this.zzdv);
        }

        public final Builder setCountry(String arg1) {
            this.zzdv = arg1;
            return this;
        }

        public final Builder setTypeFilter(int arg1) {
            this.zzdw = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final int TYPE_FILTER_ADDRESS = 2;
    public static final int TYPE_FILTER_CITIES = 5;
    public static final int TYPE_FILTER_ESTABLISHMENT = 34;
    public static final int TYPE_FILTER_GEOCODE = 1007;
    public static final int TYPE_FILTER_NONE = 0;
    public static final int TYPE_FILTER_REGIONS = 4;
    @VersionField(id=1000) private final int versionCode;
    @Field(id=1) private final boolean zzdt;
    @Field(id=2) private final List zzdu;
    @Field(id=3) private final String zzdv;
    private final int zzdw;

    static {
        AutocompleteFilter.CREATOR = new zzd();
    }

    @Constructor AutocompleteFilter(@Param(id=1000) int arg1, @Param(id=1) boolean arg2, @Param(id=2) List arg3, @Param(id=3) String arg4) {
        super();
        this.versionCode = arg1;
        this.zzdu = arg3;
        arg1 = arg3 == null || (((Collection)arg3).isEmpty()) ? 0 : ((Collection)arg3).iterator().next().intValue();
        this.zzdw = arg1;
        this.zzdv = arg4;
        if(this.versionCode <= 0) {
            this.zzdt = (((int)arg2)) ^ 1;
            return;
        }

        this.zzdt = arg2;
    }

    public boolean equals(Object arg5) {
        if(this == (((AutocompleteFilter)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof AutocompleteFilter)) {
            return 0;
        }

        if(this.zzdw == ((AutocompleteFilter)arg5).zzdw && this.zzdt == ((AutocompleteFilter)arg5).zzdt && this.zzdv == ((AutocompleteFilter)arg5).zzdv) {
            return 1;
        }

        return 0;
    }

    public int getTypeFilter() {
        return this.zzdw;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zzdt), Integer.valueOf(this.zzdw), this.zzdv});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("includeQueryPredictions", Boolean.valueOf(this.zzdt)).add("typeFilter", Integer.valueOf(this.zzdw)).add("country", this.zzdv).toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBoolean(arg4, 1, this.zzdt);
        SafeParcelWriter.writeIntegerList(arg4, 2, this.zzdu, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzdv, false);
        SafeParcelWriter.writeInt(arg4, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

