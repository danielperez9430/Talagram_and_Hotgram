package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;

@Class(creator="MarkerOptionsCreator") @Reserved(value={1}) public final class MarkerOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValue="1.0f", getter="getAlpha", id=14) private float alpha;
    @Field(getter="getPosition", id=2) private LatLng position;
    @Field(getter="getZIndex", id=15) private float zzcr;
    @Field(getter="isVisible", id=9) private boolean zzcs;
    @Field(getter="getAnchorU", id=6) private float zzda;
    @Field(getter="getAnchorV", id=7) private float zzdb;
    @Field(getter="getTitle", id=3) private String zzdm;
    @Field(getter="getSnippet", id=4) private String zzdn;
    @Field(getter="getWrappedIconDescriptorImplBinder", id=5, type="android.os.IBinder") private BitmapDescriptor zzdo;
    @Field(getter="isDraggable", id=8) private boolean zzdp;
    @Field(getter="isFlat", id=10) private boolean zzdq;
    @Field(getter="getRotation", id=11) private float zzdr;
    @Field(defaultValue="0.5f", getter="getInfoWindowAnchorU", id=12) private float zzds;
    @Field(getter="getInfoWindowAnchorV", id=13) private float zzdt;

    static {
        MarkerOptions.CREATOR = new zzh();
    }

    @Constructor MarkerOptions(@Param(id=2) LatLng arg5, @Param(id=3) String arg6, @Param(id=4) String arg7, @Param(id=5) IBinder arg8, @Param(id=6) float arg9, @Param(id=7) float arg10, @Param(id=8) boolean arg11, @Param(id=9) boolean arg12, @Param(id=10) boolean arg13, @Param(id=11) float arg14, @Param(id=12) float arg15, @Param(id=13) float arg16, @Param(id=14) float arg17, @Param(id=15) float arg18) {
        MarkerOptions v0 = this;
        super();
        v0.zzda = 0.5f;
        v0.zzdb = 1f;
        v0.zzcs = true;
        v0.zzdq = false;
        v0.zzdr = 0f;
        v0.zzds = 0.5f;
        v0.zzdt = 0f;
        v0.alpha = 1f;
        v0.position = arg5;
        v0.zzdm = arg6;
        v0.zzdn = arg7;
        v0.zzdo = arg8 == null ? null : new BitmapDescriptor(Stub.asInterface(arg8));
        float v1 = arg9;
        v0.zzda = v1;
        v0.zzdb = arg10;
        v0.zzdp = arg11;
        v0.zzcs = arg12;
        v0.zzdq = arg13;
        v0.zzdr = arg14;
        v0.zzds = arg15;
        v0.zzdt = arg16;
        v0.alpha = arg17;
        v0.zzcr = arg18;
    }

    public MarkerOptions() {
        super();
        this.zzda = 0.5f;
        this.zzdb = 1f;
        this.zzcs = true;
        this.zzdq = false;
        this.zzdr = 0f;
        this.zzds = 0.5f;
        this.zzdt = 0f;
        this.alpha = 1f;
    }

    public final MarkerOptions alpha(float arg1) {
        this.alpha = arg1;
        return this;
    }

    public final MarkerOptions anchor(float arg1, float arg2) {
        this.zzda = arg1;
        this.zzdb = arg2;
        return this;
    }

    public final MarkerOptions draggable(boolean arg1) {
        this.zzdp = arg1;
        return this;
    }

    public final MarkerOptions flat(boolean arg1) {
        this.zzdq = arg1;
        return this;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzdo;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzds;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzdt;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final float getRotation() {
        return this.zzdr;
    }

    public final String getSnippet() {
        return this.zzdn;
    }

    public final String getTitle() {
        return this.zzdm;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final MarkerOptions icon(BitmapDescriptor arg1) {
        this.zzdo = arg1;
        return this;
    }

    public final MarkerOptions infoWindowAnchor(float arg1, float arg2) {
        this.zzds = arg1;
        this.zzdt = arg2;
        return this;
    }

    public final boolean isDraggable() {
        return this.zzdp;
    }

    public final boolean isFlat() {
        return this.zzdq;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final MarkerOptions position(LatLng arg2) {
        if(arg2 != null) {
            this.position = arg2;
            return this;
        }

        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    public final MarkerOptions rotation(float arg1) {
        this.zzdr = arg1;
        return this;
    }

    public final MarkerOptions snippet(String arg1) {
        this.zzdn = arg1;
        return this;
    }

    public final MarkerOptions title(String arg1) {
        this.zzdm = arg1;
        return this;
    }

    public final MarkerOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getPosition(), arg6, false);
        SafeParcelWriter.writeString(arg5, 3, this.getTitle(), false);
        SafeParcelWriter.writeString(arg5, 4, this.getSnippet(), false);
        IBinder v6 = this.zzdo == null ? null : this.zzdo.zza().asBinder();
        SafeParcelWriter.writeIBinder(arg5, 5, v6, false);
        SafeParcelWriter.writeFloat(arg5, 6, this.getAnchorU());
        SafeParcelWriter.writeFloat(arg5, 7, this.getAnchorV());
        SafeParcelWriter.writeBoolean(arg5, 8, this.isDraggable());
        SafeParcelWriter.writeBoolean(arg5, 9, this.isVisible());
        SafeParcelWriter.writeBoolean(arg5, 10, this.isFlat());
        SafeParcelWriter.writeFloat(arg5, 11, this.getRotation());
        SafeParcelWriter.writeFloat(arg5, 12, this.getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(arg5, 13, this.getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(arg5, 14, this.getAlpha());
        SafeParcelWriter.writeFloat(arg5, 15, this.getZIndex());
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final MarkerOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }
}

