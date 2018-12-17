package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;

@Class(creator="GroundOverlayOptionsCreator") @Reserved(value={1}) public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final float NO_DIMENSION = 0f;
    @Field(getter="getBearing", id=7) private float bearing;
    @Field(getter="getHeight", id=5) private float height;
    @Field(getter="getWidth", id=4) private float width;
    @Field(getter="getZIndex", id=8) private float zzcr;
    @Field(getter="isVisible", id=9) private boolean zzcs;
    @Field(getter="isClickable", id=13) private boolean zzct;
    @Field(getter="getWrappedImageDescriptorImplBinder", id=2, type="android.os.IBinder") private BitmapDescriptor zzcw;
    @Field(getter="getLocation", id=3) private LatLng zzcx;
    @Field(getter="getBounds", id=6) private LatLngBounds zzcy;
    @Field(getter="getTransparency", id=10) private float zzcz;
    @Field(getter="getAnchorU", id=11) private float zzda;
    @Field(getter="getAnchorV", id=12) private float zzdb;

    static {
        GroundOverlayOptions.CREATOR = new zzd();
    }

    @Constructor GroundOverlayOptions(@Param(id=2) IBinder arg2, @Param(id=3) LatLng arg3, @Param(id=4) float arg4, @Param(id=5) float arg5, @Param(id=6) LatLngBounds arg6, @Param(id=7) float arg7, @Param(id=8) float arg8, @Param(id=9) boolean arg9, @Param(id=10) float arg10, @Param(id=11) float arg11, @Param(id=12) float arg12, @Param(id=13) boolean arg13) {
        super();
        this.zzcs = true;
        this.zzcz = 0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
        this.zzcw = new BitmapDescriptor(Stub.asInterface(arg2));
        this.zzcx = arg3;
        this.width = arg4;
        this.height = arg5;
        this.zzcy = arg6;
        this.bearing = arg7;
        this.zzcr = arg8;
        this.zzcs = arg9;
        this.zzcz = arg10;
        this.zzda = arg11;
        this.zzdb = arg12;
        this.zzct = arg13;
    }

    public GroundOverlayOptions() {
        super();
        this.zzcs = true;
        this.zzcz = 0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
    }

    public final GroundOverlayOptions anchor(float arg1, float arg2) {
        this.zzda = arg1;
        this.zzdb = arg2;
        return this;
    }

    public final GroundOverlayOptions bearing(float arg2) {
        this.bearing = (arg2 % 360f + 360f) % 360f;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean arg1) {
        this.zzct = arg1;
        return this;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final LatLngBounds getBounds() {
        return this.zzcy;
    }

    public final float getHeight() {
        return this.height;
    }

    public final BitmapDescriptor getImage() {
        return this.zzcw;
    }

    public final LatLng getLocation() {
        return this.zzcx;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final GroundOverlayOptions image(BitmapDescriptor arg2) {
        Preconditions.checkNotNull(arg2, "imageDescriptor must not be null");
        this.zzcw = arg2;
        return this;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final GroundOverlayOptions position(LatLng arg5, float arg6) {
        boolean v1 = false;
        boolean v0 = this.zzcy == null ? true : false;
        Preconditions.checkState(v0, "Position has already been set using positionFromBounds");
        v0 = arg5 != null ? true : false;
        Preconditions.checkArgument(v0, "Location must be specified");
        if(arg6 >= 0f) {
            v1 = true;
        }

        Preconditions.checkArgument(v1, "Width must be non-negative");
        return this.zza(arg5, arg6, -1f);
    }

    public final GroundOverlayOptions position(LatLng arg6, float arg7, float arg8) {
        boolean v1 = false;
        boolean v0 = this.zzcy == null ? true : false;
        Preconditions.checkState(v0, "Position has already been set using positionFromBounds");
        v0 = arg6 != null ? true : false;
        Preconditions.checkArgument(v0, "Location must be specified");
        boolean v3 = arg7 >= 0f ? true : false;
        Preconditions.checkArgument(v3, "Width must be non-negative");
        if(arg8 >= 0f) {
            v1 = true;
        }

        Preconditions.checkArgument(v1, "Height must be non-negative");
        return this.zza(arg6, arg7, arg8);
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds arg5) {
        boolean v0 = this.zzcx == null ? true : false;
        String v1 = String.valueOf(this.zzcx);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 46);
        v3.append("Position has already been set using position: ");
        v3.append(v1);
        Preconditions.checkState(v0, v3.toString());
        this.zzcy = arg5;
        return this;
    }

    public final GroundOverlayOptions transparency(float arg3) {
        boolean v0 = arg3 < 0f || arg3 > 1f ? false : true;
        Preconditions.checkArgument(v0, "Transparency must be in the range [0..1]");
        this.zzcz = arg3;
        return this;
    }

    public final GroundOverlayOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeIBinder(arg5, 2, this.zzcw.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.getLocation(), arg6, false);
        SafeParcelWriter.writeFloat(arg5, 4, this.getWidth());
        SafeParcelWriter.writeFloat(arg5, 5, this.getHeight());
        SafeParcelWriter.writeParcelable(arg5, 6, this.getBounds(), arg6, false);
        SafeParcelWriter.writeFloat(arg5, 7, this.getBearing());
        SafeParcelWriter.writeFloat(arg5, 8, this.getZIndex());
        SafeParcelWriter.writeBoolean(arg5, 9, this.isVisible());
        SafeParcelWriter.writeFloat(arg5, 10, this.getTransparency());
        SafeParcelWriter.writeFloat(arg5, 11, this.getAnchorU());
        SafeParcelWriter.writeFloat(arg5, 12, this.getAnchorV());
        SafeParcelWriter.writeBoolean(arg5, 13, this.isClickable());
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final GroundOverlayOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }

    private final GroundOverlayOptions zza(LatLng arg1, float arg2, float arg3) {
        this.zzcx = arg1;
        this.width = arg2;
        this.height = arg3;
        return this;
    }
}

