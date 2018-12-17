package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.AttributeSet;
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
import com.google.android.gms.maps.model.CameraPosition$Builder;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

@Class(creator="GoogleMapOptionsCreator") @Reserved(value={1}) public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getMapType", id=4) private int mapType;
    @Field(defaultValue="-1", getter="getZOrderOnTopForParcel", id=2, type="byte") private Boolean zzaj;
    @Field(defaultValue="-1", getter="getUseViewLifecycleInFragmentForParcel", id=3, type="byte") private Boolean zzak;
    @Field(getter="getCamera", id=5) private CameraPosition zzal;
    @Field(defaultValue="-1", getter="getZoomControlsEnabledForParcel", id=6, type="byte") private Boolean zzam;
    @Field(defaultValue="-1", getter="getCompassEnabledForParcel", id=7, type="byte") private Boolean zzan;
    @Field(defaultValue="-1", getter="getScrollGesturesEnabledForParcel", id=8, type="byte") private Boolean zzao;
    @Field(defaultValue="-1", getter="getZoomGesturesEnabledForParcel", id=9, type="byte") private Boolean zzap;
    @Field(defaultValue="-1", getter="getTiltGesturesEnabledForParcel", id=10, type="byte") private Boolean zzaq;
    @Field(defaultValue="-1", getter="getRotateGesturesEnabledForParcel", id=11, type="byte") private Boolean zzar;
    @Field(defaultValue="-1", getter="getLiteModeForParcel", id=12, type="byte") private Boolean zzas;
    @Field(defaultValue="-1", getter="getMapToolbarEnabledForParcel", id=14, type="byte") private Boolean zzat;
    @Field(defaultValue="-1", getter="getAmbientEnabledForParcel", id=15, type="byte") private Boolean zzau;
    @Field(getter="getMinZoomPreference", id=16) private Float zzav;
    @Field(getter="getMaxZoomPreference", id=17) private Float zzaw;
    @Field(getter="getLatLngBoundsForCameraTarget", id=18) private LatLngBounds zzax;

    static {
        GoogleMapOptions.CREATOR = new zzaa();
    }

    @Constructor GoogleMapOptions(@Param(id=2) byte arg3, @Param(id=3) byte arg4, @Param(id=4) int arg5, @Param(id=5) CameraPosition arg6, @Param(id=6) byte arg7, @Param(id=7) byte arg8, @Param(id=8) byte arg9, @Param(id=9) byte arg10, @Param(id=10) byte arg11, @Param(id=11) byte arg12, @Param(id=12) byte arg13, @Param(id=14) byte arg14, @Param(id=15) byte arg15, @Param(id=16) Float arg16, @Param(id=17) Float arg17, @Param(id=18) LatLngBounds arg18) {
        super();
        this.mapType = -1;
        this.zzav = null;
        this.zzaw = null;
        this.zzax = null;
        this.zzaj = zza.zza(arg3);
        this.zzak = zza.zza(arg4);
        this.mapType = arg5;
        this.zzal = arg6;
        this.zzam = zza.zza(arg7);
        this.zzan = zza.zza(arg8);
        this.zzao = zza.zza(arg9);
        this.zzap = zza.zza(arg10);
        this.zzaq = zza.zza(arg11);
        this.zzar = zza.zza(arg12);
        this.zzas = zza.zza(arg13);
        this.zzat = zza.zza(arg14);
        this.zzau = zza.zza(arg15);
        this.zzav = arg16;
        this.zzaw = arg17;
        this.zzax = arg18;
    }

    public GoogleMapOptions() {
        super();
        this.mapType = -1;
        this.zzav = null;
        this.zzaw = null;
        this.zzax = null;
    }

    public final GoogleMapOptions ambientEnabled(boolean arg1) {
        this.zzau = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions camera(CameraPosition arg1) {
        this.zzal = arg1;
        return this;
    }

    public final GoogleMapOptions compassEnabled(boolean arg1) {
        this.zzan = Boolean.valueOf(arg1);
        return this;
    }

    public static GoogleMapOptions createFromAttributes(Context arg5, AttributeSet arg6) {
        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                TypedArray v0 = arg5.getResources().obtainAttributes(arg6, styleable.MapAttrs);
                GoogleMapOptions v1 = new GoogleMapOptions();
                if(v0.hasValue(styleable.MapAttrs_mapType)) {
                    v1.mapType(v0.getInt(styleable.MapAttrs_mapType, -1));
                }

                if(v0.hasValue(styleable.MapAttrs_zOrderOnTop)) {
                    v1.zOrderOnTop(v0.getBoolean(styleable.MapAttrs_zOrderOnTop, false));
                }

                if(v0.hasValue(styleable.MapAttrs_useViewLifecycle)) {
                    v1.useViewLifecycleInFragment(v0.getBoolean(styleable.MapAttrs_useViewLifecycle, false));
                }

                if(v0.hasValue(styleable.MapAttrs_uiCompass)) {
                    v1.compassEnabled(v0.getBoolean(styleable.MapAttrs_uiCompass, true));
                }

                if(v0.hasValue(styleable.MapAttrs_uiRotateGestures)) {
                    v1.rotateGesturesEnabled(v0.getBoolean(styleable.MapAttrs_uiRotateGestures, true));
                }

                if(v0.hasValue(styleable.MapAttrs_uiScrollGestures)) {
                    v1.scrollGesturesEnabled(v0.getBoolean(styleable.MapAttrs_uiScrollGestures, true));
                }

                if(v0.hasValue(styleable.MapAttrs_uiTiltGestures)) {
                    v1.tiltGesturesEnabled(v0.getBoolean(styleable.MapAttrs_uiTiltGestures, true));
                }

                if(v0.hasValue(styleable.MapAttrs_uiZoomGestures)) {
                    v1.zoomGesturesEnabled(v0.getBoolean(styleable.MapAttrs_uiZoomGestures, true));
                }

                if(v0.hasValue(styleable.MapAttrs_uiZoomControls)) {
                    v1.zoomControlsEnabled(v0.getBoolean(styleable.MapAttrs_uiZoomControls, true));
                }

                if(v0.hasValue(styleable.MapAttrs_liteMode)) {
                    v1.liteMode(v0.getBoolean(styleable.MapAttrs_liteMode, false));
                }

                if(v0.hasValue(styleable.MapAttrs_uiMapToolbar)) {
                    v1.mapToolbarEnabled(v0.getBoolean(styleable.MapAttrs_uiMapToolbar, true));
                }

                if(v0.hasValue(styleable.MapAttrs_ambientEnabled)) {
                    v1.ambientEnabled(v0.getBoolean(styleable.MapAttrs_ambientEnabled, false));
                }

                if(v0.hasValue(styleable.MapAttrs_cameraMinZoomPreference)) {
                    v1.minZoomPreference(v0.getFloat(styleable.MapAttrs_cameraMinZoomPreference, -Infinityf));
                }

                if(v0.hasValue(styleable.MapAttrs_cameraMinZoomPreference)) {
                    v1.maxZoomPreference(v0.getFloat(styleable.MapAttrs_cameraMaxZoomPreference, Infinityf));
                }

                v1.latLngBoundsForCameraTarget(GoogleMapOptions.zza(arg5, arg6));
                v1.camera(GoogleMapOptions.zzb(arg5, arg6));
                v0.recycle();
                return v1;
            }
        }

        return null;
    }

    public final Boolean getAmbientEnabled() {
        return this.zzau;
    }

    public final CameraPosition getCamera() {
        return this.zzal;
    }

    public final Boolean getCompassEnabled() {
        return this.zzan;
    }

    public final LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzax;
    }

    public final Boolean getLiteMode() {
        return this.zzas;
    }

    public final Boolean getMapToolbarEnabled() {
        return this.zzat;
    }

    public final int getMapType() {
        return this.mapType;
    }

    public final Float getMaxZoomPreference() {
        return this.zzaw;
    }

    public final Float getMinZoomPreference() {
        return this.zzav;
    }

    public final Boolean getRotateGesturesEnabled() {
        return this.zzar;
    }

    public final Boolean getScrollGesturesEnabled() {
        return this.zzao;
    }

    public final Boolean getTiltGesturesEnabled() {
        return this.zzaq;
    }

    public final Boolean getUseViewLifecycleInFragment() {
        return this.zzak;
    }

    public final Boolean getZOrderOnTop() {
        return this.zzaj;
    }

    public final Boolean getZoomControlsEnabled() {
        return this.zzam;
    }

    public final Boolean getZoomGesturesEnabled() {
        return this.zzap;
    }

    public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds arg1) {
        this.zzax = arg1;
        return this;
    }

    public final GoogleMapOptions liteMode(boolean arg1) {
        this.zzas = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions mapToolbarEnabled(boolean arg1) {
        this.zzat = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions mapType(int arg1) {
        this.mapType = arg1;
        return this;
    }

    public final GoogleMapOptions maxZoomPreference(float arg1) {
        this.zzaw = Float.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions minZoomPreference(float arg1) {
        this.zzav = Float.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions rotateGesturesEnabled(boolean arg1) {
        this.zzar = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions scrollGesturesEnabled(boolean arg1) {
        this.zzao = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions tiltGesturesEnabled(boolean arg1) {
        this.zzaq = Boolean.valueOf(arg1);
        return this;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.mapType)).add("LiteMode", this.zzas).add("Camera", this.zzal).add("CompassEnabled", this.zzan).add("ZoomControlsEnabled", this.zzam).add("ScrollGesturesEnabled", this.zzao).add("ZoomGesturesEnabled", this.zzap).add("TiltGesturesEnabled", this.zzaq).add("RotateGesturesEnabled", this.zzar).add("MapToolbarEnabled", this.zzat).add("AmbientEnabled", this.zzau).add("MinZoomPreference", this.zzav).add("MaxZoomPreference", this.zzaw).add("LatLngBoundsForCameraTarget", this.zzax).add("ZOrderOnTop", this.zzaj).add("UseViewLifecycleInFragment", this.zzak).toString();
    }

    public final GoogleMapOptions useViewLifecycleInFragment(boolean arg1) {
        this.zzak = Boolean.valueOf(arg1);
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeByte(arg5, 2, zza.zza(this.zzaj));
        SafeParcelWriter.writeByte(arg5, 3, zza.zza(this.zzak));
        SafeParcelWriter.writeInt(arg5, 4, this.getMapType());
        SafeParcelWriter.writeParcelable(arg5, 5, this.getCamera(), arg6, false);
        SafeParcelWriter.writeByte(arg5, 6, zza.zza(this.zzam));
        SafeParcelWriter.writeByte(arg5, 7, zza.zza(this.zzan));
        SafeParcelWriter.writeByte(arg5, 8, zza.zza(this.zzao));
        SafeParcelWriter.writeByte(arg5, 9, zza.zza(this.zzap));
        SafeParcelWriter.writeByte(arg5, 10, zza.zza(this.zzaq));
        SafeParcelWriter.writeByte(arg5, 11, zza.zza(this.zzar));
        SafeParcelWriter.writeByte(arg5, 12, zza.zza(this.zzas));
        SafeParcelWriter.writeByte(arg5, 14, zza.zza(this.zzat));
        SafeParcelWriter.writeByte(arg5, 15, zza.zza(this.zzau));
        SafeParcelWriter.writeFloatObject(arg5, 16, this.getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(arg5, 17, this.getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(arg5, 18, this.getLatLngBoundsForCameraTarget(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final GoogleMapOptions zOrderOnTop(boolean arg1) {
        this.zzaj = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions zoomControlsEnabled(boolean arg1) {
        this.zzam = Boolean.valueOf(arg1);
        return this;
    }

    public final GoogleMapOptions zoomGesturesEnabled(boolean arg1) {
        this.zzap = Boolean.valueOf(arg1);
        return this;
    }

    public static LatLngBounds zza(Context arg8, AttributeSet arg9) {
        LatLngBounds v0 = null;
        if(arg8 != null) {
            if(arg9 == null) {
            }
            else {
                TypedArray v8 = arg8.getResources().obtainAttributes(arg9, styleable.MapAttrs);
                Float v9 = v8.hasValue(styleable.MapAttrs_latLngBoundsSouthWestLatitude) ? Float.valueOf(v8.getFloat(styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0f)) : ((Float)v0);
                Float v2 = v8.hasValue(styleable.MapAttrs_latLngBoundsSouthWestLongitude) ? Float.valueOf(v8.getFloat(styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0f)) : ((Float)v0);
                Float v3 = v8.hasValue(styleable.MapAttrs_latLngBoundsNorthEastLatitude) ? Float.valueOf(v8.getFloat(styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0f)) : ((Float)v0);
                Float v1 = v8.hasValue(styleable.MapAttrs_latLngBoundsNorthEastLongitude) ? Float.valueOf(v8.getFloat(styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0f)) : ((Float)v0);
                v8.recycle();
                if(v9 == null) {
                    return v0;
                }

                if(v2 == null) {
                    return v0;
                }

                if(v3 == null) {
                    return v0;
                }

                if(v1 == null) {
                    return v0;
                }

                v0 = new LatLngBounds(new LatLng(((double)v9.floatValue()), ((double)v2.floatValue())), new LatLng(((double)v3.floatValue()), ((double)v1.floatValue())));
            }
        }

        return v0;
    }

    public static CameraPosition zzb(Context arg7, AttributeSet arg8) {
        if(arg7 != null) {
            if(arg8 == null) {
            }
            else {
                TypedArray v7 = arg7.getResources().obtainAttributes(arg8, styleable.MapAttrs);
                float v8 = v7.hasValue(styleable.MapAttrs_cameraTargetLat) ? v7.getFloat(styleable.MapAttrs_cameraTargetLat, 0f) : 0f;
                float v1 = v7.hasValue(styleable.MapAttrs_cameraTargetLng) ? v7.getFloat(styleable.MapAttrs_cameraTargetLng, 0f) : 0f;
                LatLng v2 = new LatLng(((double)v8), ((double)v1));
                Builder v8_1 = CameraPosition.builder();
                v8_1.target(v2);
                if(v7.hasValue(styleable.MapAttrs_cameraZoom)) {
                    v8_1.zoom(v7.getFloat(styleable.MapAttrs_cameraZoom, 0f));
                }

                if(v7.hasValue(styleable.MapAttrs_cameraBearing)) {
                    v8_1.bearing(v7.getFloat(styleable.MapAttrs_cameraBearing, 0f));
                }

                if(v7.hasValue(styleable.MapAttrs_cameraTilt)) {
                    v8_1.tilt(v7.getFloat(styleable.MapAttrs_cameraTilt, 0f));
                }

                v7.recycle();
                return v8_1.build();
            }
        }

        return null;
    }
}

