package com.google.android.gms.maps;

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
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

@Class(creator="StreetViewPanoramaOptionsCreator") @Reserved(value={1}) public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getPanoramaId", id=3) private String panoId;
    @Field(getter="getPosition", id=4) private LatLng position;
    @Field(getter="getUseViewLifecycleInFragmentForParcel", id=10, type="byte") private Boolean zzak;
    @Field(getter="getZoomGesturesEnabledForParcel", id=7, type="byte") private Boolean zzap;
    @Field(getter="getStreetViewPanoramaCamera", id=2) private StreetViewPanoramaCamera zzbw;
    @Field(getter="getRadius", id=5) private Integer zzbx;
    @Field(getter="getUserNavigationEnabledForParcel", id=6, type="byte") private Boolean zzby;
    @Field(getter="getPanningGesturesEnabledForParcel", id=8, type="byte") private Boolean zzbz;
    @Field(getter="getStreetNamesEnabledForParcel", id=9, type="byte") private Boolean zzca;
    @Field(getter="getSource", id=11) private StreetViewSource zzcb;

    static {
        StreetViewPanoramaOptions.CREATOR = new zzai();
    }

    @Constructor StreetViewPanoramaOptions(@Param(id=2) StreetViewPanoramaCamera arg3, @Param(id=3) String arg4, @Param(id=4) LatLng arg5, @Param(id=5) Integer arg6, @Param(id=6) byte arg7, @Param(id=7) byte arg8, @Param(id=8) byte arg9, @Param(id=9) byte arg10, @Param(id=10) byte arg11, @Param(id=11) StreetViewSource arg12) {
        super();
        this.zzby = Boolean.valueOf(true);
        this.zzap = Boolean.valueOf(true);
        this.zzbz = Boolean.valueOf(true);
        this.zzca = Boolean.valueOf(true);
        this.zzcb = StreetViewSource.DEFAULT;
        this.zzbw = arg3;
        this.position = arg5;
        this.zzbx = arg6;
        this.panoId = arg4;
        this.zzby = zza.zza(arg7);
        this.zzap = zza.zza(arg8);
        this.zzbz = zza.zza(arg9);
        this.zzca = zza.zza(arg10);
        this.zzak = zza.zza(arg11);
        this.zzcb = arg12;
    }

    public StreetViewPanoramaOptions() {
        super();
        this.zzby = Boolean.valueOf(true);
        this.zzap = Boolean.valueOf(true);
        this.zzbz = Boolean.valueOf(true);
        this.zzca = Boolean.valueOf(true);
        this.zzcb = StreetViewSource.DEFAULT;
    }

    public final Boolean getPanningGesturesEnabled() {
        return this.zzbz;
    }

    public final String getPanoramaId() {
        return this.panoId;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final Integer getRadius() {
        return this.zzbx;
    }

    public final StreetViewSource getSource() {
        return this.zzcb;
    }

    public final Boolean getStreetNamesEnabled() {
        return this.zzca;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzbw;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final Boolean getUserNavigationEnabled() {
        return this.zzby;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final StreetViewPanoramaOptions panningGesturesEnabled(boolean arg1) {
        this.zzbz = Boolean.valueOf(arg1);
        return this;
    }

    public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera arg1) {
        this.zzbw = arg1;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaId(String arg1) {
        this.panoId = arg1;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng arg1) {
        this.position = arg1;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng arg1, StreetViewSource arg2) {
        this.position = arg1;
        this.zzcb = arg2;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng arg1, Integer arg2) {
        this.position = arg1;
        this.zzbx = arg2;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng arg1, Integer arg2, StreetViewSource arg3) {
        this.position = arg1;
        this.zzbx = arg2;
        this.zzcb = arg3;
        return this;
    }

    public final StreetViewPanoramaOptions streetNamesEnabled(boolean arg1) {
        this.zzca = Boolean.valueOf(arg1);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", this.panoId).add("Position", this.position).add("Radius", this.zzbx).add("Source", this.zzcb).add("StreetViewPanoramaCamera", this.zzbw).add("UserNavigationEnabled", this.zzby).add("ZoomGesturesEnabled", this.zzap).add("PanningGesturesEnabled", this.zzbz).add("StreetNamesEnabled", this.zzca).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean arg1) {
        this.zzak = Boolean.valueOf(arg1);
        return this;
    }

    public final StreetViewPanoramaOptions userNavigationEnabled(boolean arg1) {
        this.zzby = Boolean.valueOf(arg1);
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getStreetViewPanoramaCamera(), arg6, false);
        SafeParcelWriter.writeString(arg5, 3, this.getPanoramaId(), false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.getPosition(), arg6, false);
        SafeParcelWriter.writeIntegerObject(arg5, 5, this.getRadius(), false);
        SafeParcelWriter.writeByte(arg5, 6, zza.zza(this.zzby));
        SafeParcelWriter.writeByte(arg5, 7, zza.zza(this.zzap));
        SafeParcelWriter.writeByte(arg5, 8, zza.zza(this.zzbz));
        SafeParcelWriter.writeByte(arg5, 9, zza.zza(this.zzca));
        SafeParcelWriter.writeByte(arg5, 10, zza.zza(this.zzak));
        SafeParcelWriter.writeParcelable(arg5, 11, this.getSource(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean arg1) {
        this.zzap = Boolean.valueOf(arg1);
        return this;
    }
}

