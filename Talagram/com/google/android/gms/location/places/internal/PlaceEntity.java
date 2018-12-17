package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

@Class(creator="PlaceEntityCreator") @Reserved(value={1000, 2, 3, 12, 13, 16, 18}) public final class PlaceEntity extends AbstractSafeParcelable implements ReflectedParcelable, Place {
    public final class zzb {
        private LatLng latLng;
        private String name;
        private String zzdp;
        private String zzdr;
        private Uri zzds;
        private String zzgf;
        private float zzgg;
        private LatLngBounds zzgh;
        private boolean zzgj;
        private float zzgk;
        private int zzgl;
        private List zzgm;
        private zzam zzgn;
        private String zzgp;
        private List zzgq;
        private zzah zzgr;

        public zzb() {
            super();
            this.zzgl = -1;
            this.zzgk = -1f;
        }

        public final PlaceEntity zzag() {
            return new PlaceEntity(this.zzgf, this.zzgq, this.name, this.zzdp, this.zzdr, this.zzgm, this.latLng, this.zzgg, this.zzgh, null, this.zzds, this.zzgj, this.zzgk, this.zzgl, this.zzgn, this.zzgr, this.zzgp);
        }

        public final zzb zzb(float arg1) {
            this.zzgg = arg1;
            return this;
        }

        public final zzb zzb(Uri arg1) {
            this.zzds = arg1;
            return this;
        }

        public final zzb zzb(zzah arg1) {
            this.zzgr = arg1;
            return this;
        }

        public final zzb zzb(zzam arg1) {
            this.zzgn = arg1;
            return this;
        }

        public final zzb zzb(LatLng arg1) {
            this.latLng = arg1;
            return this;
        }

        public final zzb zzb(LatLngBounds arg1) {
            this.zzgh = arg1;
            return this;
        }

        public final zzb zzb(boolean arg1) {
            this.zzgj = arg1;
            return this;
        }

        public final zzb zzc(float arg1) {
            this.zzgk = arg1;
            return this;
        }

        public final zzb zzc(String arg1) {
            this.zzgf = arg1;
            return this;
        }

        public final zzb zzc(List arg1) {
            this.zzgq = arg1;
            return this;
        }

        public final zzb zzd(String arg1) {
            this.name = arg1;
            return this;
        }

        public final zzb zzd(List arg1) {
            this.zzgm = arg1;
            return this;
        }

        public final zzb zze(String arg1) {
            this.zzdp = arg1;
            return this;
        }

        public final zzb zzf(int arg1) {
            this.zzgl = arg1;
            return this;
        }

        public final zzb zzf(String arg1) {
            this.zzdr = arg1;
            return this;
        }

        public final zzb zzg(String arg1) {
            this.zzgp = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(getter="getLatLng", id=4) private final LatLng latLng;
    private Locale locale;
    @Field(getter="getName", id=19) private final String name;
    @Field(getter="getAddress", id=14) private final String zzdp;
    @Field(getter="getPlaceTypes", id=20) private final List zzdq;
    @Field(getter="getPhoneNumber", id=15) private final String zzdr;
    @Field(getter="getWebsiteUri", id=8) private final Uri zzds;
    @Field(getter="getId", id=1) private final String zzgf;
    @Field(getter="getLevelNumber", id=5) private final float zzgg;
    @Field(getter="getViewport", id=6) private final LatLngBounds zzgh;
    @Field(getter="getTimeZoneId", id=7) private final String zzgi;
    @Field(getter="isPermanentlyClosed", id=9) private final boolean zzgj;
    @Field(getter="getRating", id=10) private final float zzgk;
    @Field(getter="getPriceLevel", id=11) private final int zzgl;
    @Field(getter="getAttributionsList", id=17) private final List zzgm;
    @Field(getter="getPlaceOpeningHours", id=21) private final zzam zzgn;
    @Field(getter="getExtendedDetails", id=22) private final zzah zzgo;
    @Field(getter="getAdrAddress", id=23) private final String zzgp;

    static {
        PlaceEntity.CREATOR = new zzag();
    }

    @Constructor PlaceEntity(@Param(id=1) String arg3, @Param(id=20) List arg4, @Param(id=19) String arg5, @Param(id=14) String arg6, @Param(id=15) String arg7, @Param(id=17) List arg8, @Param(id=4) LatLng arg9, @Param(id=5) float arg10, @Param(id=6) LatLngBounds arg11, @Param(id=7) String arg12, @Param(id=8) Uri arg13, @Param(id=9) boolean arg14, @Param(id=10) float arg15, @Param(id=11) int arg16, @Param(id=21) zzam arg17, @Param(id=22) zzah arg18, @Param(id=23) String arg19) {
        PlaceEntity v0 = this;
        super();
        v0.zzgf = arg3;
        v0.zzdq = Collections.unmodifiableList(arg4);
        v0.name = arg5;
        v0.zzdp = arg6;
        v0.zzdr = arg7;
        List v1 = arg8 != null ? arg8 : Collections.emptyList();
        v0.zzgm = v1;
        v0.latLng = arg9;
        v0.zzgg = arg10;
        v0.zzgh = arg11;
        String v1_1 = arg12 != null ? arg12 : "UTC";
        v0.zzgi = v1_1;
        v0.zzds = arg13;
        v0.zzgj = arg14;
        v0.zzgk = arg15;
        v0.zzgl = arg16;
        v0.locale = null;
        v0.zzgn = arg17;
        v0.zzgo = arg18;
        v0.zzgp = arg19;
    }

    public final boolean equals(Object arg5) {
        if(this == (((PlaceEntity)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof PlaceEntity)) {
            return 0;
        }

        if((this.zzgf.equals(((PlaceEntity)arg5).zzgf)) && (Objects.equal(this.locale, ((PlaceEntity)arg5).locale))) {
            return 1;
        }

        return 0;
    }

    public final Object freeze() {
        return this;
    }

    public final CharSequence getAddress() {
        return this.zzdp;
    }

    @Nullable public final CharSequence getAttributions() {
        return zzh.zzg(this.zzgm);
    }

    @VisibleForTesting public final String getId() {
        return this.zzgf;
    }

    public final LatLng getLatLng() {
        return this.latLng;
    }

    public final Locale getLocale() {
        return this.locale;
    }

    public final CharSequence getName() {
        return this.name;
    }

    public final CharSequence getPhoneNumber() {
        return this.zzdr;
    }

    public final List getPlaceTypes() {
        return this.zzdq;
    }

    public final int getPriceLevel() {
        return this.zzgl;
    }

    public final float getRating() {
        return this.zzgk;
    }

    public final LatLngBounds getViewport() {
        return this.zzgh;
    }

    public final Uri getWebsiteUri() {
        return this.zzds;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzgf, this.locale});
    }

    public final boolean isDataValid() {
        return 1;
    }

    @VisibleForTesting public final void setLocale(Locale arg1) {
        this.locale = arg1;
    }

    @SuppressLint(value={"DefaultLocale"}) public final String toString() {
        return Objects.toStringHelper(this).add("id", this.zzgf).add("placeTypes", this.zzdq).add("locale", this.locale).add("name", this.name).add("address", this.zzdp).add("phoneNumber", this.zzdr).add("latlng", this.latLng).add("viewport", this.zzgh).add("websiteUri", this.zzds).add("isPermanentlyClosed", Boolean.valueOf(this.zzgj)).add("priceLevel", Integer.valueOf(this.zzgl)).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 1, this.getId(), false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.getLatLng(), arg6, false);
        SafeParcelWriter.writeFloat(arg5, 5, this.zzgg);
        SafeParcelWriter.writeParcelable(arg5, 6, this.getViewport(), arg6, false);
        SafeParcelWriter.writeString(arg5, 7, this.zzgi, false);
        SafeParcelWriter.writeParcelable(arg5, 8, this.getWebsiteUri(), arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 9, this.zzgj);
        SafeParcelWriter.writeFloat(arg5, 10, this.getRating());
        SafeParcelWriter.writeInt(arg5, 11, this.getPriceLevel());
        SafeParcelWriter.writeString(arg5, 14, this.getAddress(), false);
        SafeParcelWriter.writeString(arg5, 15, this.getPhoneNumber(), false);
        SafeParcelWriter.writeStringList(arg5, 17, this.zzgm, false);
        SafeParcelWriter.writeString(arg5, 19, this.getName(), false);
        SafeParcelWriter.writeIntegerList(arg5, 20, this.getPlaceTypes(), false);
        SafeParcelWriter.writeParcelable(arg5, 21, this.zzgn, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 22, this.zzgo, arg6, false);
        SafeParcelWriter.writeString(arg5, 23, this.zzgp, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

