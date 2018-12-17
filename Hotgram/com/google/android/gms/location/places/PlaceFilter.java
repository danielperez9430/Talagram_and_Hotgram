package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Class(creator="PlaceFilterCreator") @Reserved(value={1000, 2, 5}) public final class PlaceFilter extends zzb {
    @Deprecated public final class com.google.android.gms.location.places.PlaceFilter$zzb {
        private boolean zzed;
        private Collection zzej;
        private Collection zzek;
        private String[] zzel;

        com.google.android.gms.location.places.PlaceFilter$zzb(zzh arg1) {
            this();
        }

        private com.google.android.gms.location.places.PlaceFilter$zzb() {
            super();
            this.zzej = null;
            this.zzed = false;
            this.zzek = null;
            this.zzel = null;
        }
    }

    public static final Parcelable$Creator CREATOR;
    private static final PlaceFilter zzeb;
    @Field(id=1) private final List zzec;
    @Field(id=3) private final boolean zzed;
    @Field(id=4) private final List zzee;
    @Field(id=6) private final List zzef;
    private final Set zzeg;
    private final Set zzeh;
    private final Set zzei;

    static {
        PlaceFilter.CREATOR = new zzi();
        PlaceFilter.zzeb = new PlaceFilter();
    }

    @Constructor PlaceFilter(@Param(id=1) List arg1, @Param(id=3) boolean arg2, @Param(id=6) List arg3, @Param(id=4) List arg4) {
        super();
        this.zzec = arg1;
        this.zzed = arg2;
        this.zzee = arg4;
        this.zzef = arg3;
        this.zzeg = PlaceFilter.zzb(this.zzec);
        this.zzeh = PlaceFilter.zzb(this.zzee);
        this.zzei = PlaceFilter.zzb(this.zzef);
    }

    public PlaceFilter() {
        this(false, null);
    }

    public PlaceFilter(boolean arg2, Collection arg3) {
        this(null, arg2, arg3, null);
    }

    private PlaceFilter(Collection arg1, boolean arg2, Collection arg3, Collection arg4) {
        this(PlaceFilter.zzf(null), arg2, PlaceFilter.zzf(arg3), PlaceFilter.zzf(null));
    }

    public final boolean equals(Object arg5) {
        if(this == (((PlaceFilter)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof PlaceFilter)) {
            return 0;
        }

        if((this.zzeg.equals(((PlaceFilter)arg5).zzeg)) && this.zzed == ((PlaceFilter)arg5).zzed && (this.zzeh.equals(((PlaceFilter)arg5).zzeh)) && (this.zzei.equals(((PlaceFilter)arg5).zzei))) {
            return 1;
        }

        return 0;
    }

    public final Set getPlaceIds() {
        return this.zzei;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzeg, Boolean.valueOf(this.zzed), this.zzeh, this.zzei});
    }

    public final boolean isRestrictedToPlacesOpenNow() {
        return this.zzed;
    }

    public final String toString() {
        ToStringHelper v0 = Objects.toStringHelper(this);
        if(!this.zzeg.isEmpty()) {
            v0.add("types", this.zzeg);
        }

        v0.add("requireOpenNow", Boolean.valueOf(this.zzed));
        if(!this.zzei.isEmpty()) {
            v0.add("placeIds", this.zzei);
        }

        if(!this.zzeh.isEmpty()) {
            v0.add("requestedUserDataTypes", this.zzeh);
        }

        return v0.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeIntegerList(arg4, 1, this.zzec, false);
        SafeParcelWriter.writeBoolean(arg4, 3, this.zzed);
        SafeParcelWriter.writeTypedList(arg4, 4, this.zzee, false);
        SafeParcelWriter.writeStringList(arg4, 6, this.zzef, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    @Deprecated public static PlaceFilter zzz() {
        new com.google.android.gms.location.places.PlaceFilter$zzb(null);
        return new PlaceFilter(null, false, null, null);
    }
}

