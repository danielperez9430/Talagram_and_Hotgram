package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.List;

@Class(creator="CircleOptionsCreator") @Reserved(value={1}) public final class CircleOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getFillColor", id=6) private int fillColor;
    @Field(getter="getStrokeColor", id=5) private int strokeColor;
    @Field(getter="getCenter", id=2) private LatLng zzco;
    @Field(getter="getRadius", id=3) private double zzcp;
    @Field(getter="getStrokeWidth", id=4) private float zzcq;
    @Field(getter="getZIndex", id=7) private float zzcr;
    @Field(getter="isVisible", id=8) private boolean zzcs;
    @Field(getter="isClickable", id=9) private boolean zzct;
    @Field(getter="getStrokePattern", id=10) private List zzcu;

    static {
        CircleOptions.CREATOR = new zzc();
    }

    @Constructor CircleOptions(@Param(id=2) LatLng arg4, @Param(id=3) double arg5, @Param(id=4) float arg7, @Param(id=5) int arg8, @Param(id=6) int arg9, @Param(id=7) float arg10, @Param(id=8) boolean arg11, @Param(id=9) boolean arg12, @Param(id=10) List arg13) {
        super();
        this.zzco = null;
        this.zzcp = 0;
        this.zzcq = 10f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzct = false;
        this.zzcu = null;
        this.zzco = arg4;
        this.zzcp = arg5;
        this.zzcq = arg7;
        this.strokeColor = arg8;
        this.fillColor = arg9;
        this.zzcr = arg10;
        this.zzcs = arg11;
        this.zzct = arg12;
        this.zzcu = arg13;
    }

    public CircleOptions() {
        super();
        this.zzco = null;
        this.zzcp = 0;
        this.zzcq = 10f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzct = false;
        this.zzcu = null;
    }

    public final CircleOptions center(LatLng arg1) {
        this.zzco = arg1;
        return this;
    }

    public final CircleOptions clickable(boolean arg1) {
        this.zzct = arg1;
        return this;
    }

    public final CircleOptions fillColor(int arg1) {
        this.fillColor = arg1;
        return this;
    }

    public final LatLng getCenter() {
        return this.zzco;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final double getRadius() {
        return this.zzcp;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final List getStrokePattern() {
        return this.zzcu;
    }

    public final float getStrokeWidth() {
        return this.zzcq;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final CircleOptions radius(double arg1) {
        this.zzcp = arg1;
        return this;
    }

    public final CircleOptions strokeColor(int arg1) {
        this.strokeColor = arg1;
        return this;
    }

    public final CircleOptions strokePattern(List arg1) {
        this.zzcu = arg1;
        return this;
    }

    public final CircleOptions strokeWidth(float arg1) {
        this.zzcq = arg1;
        return this;
    }

    public final CircleOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeParcelable(arg6, 2, this.getCenter(), arg7, false);
        SafeParcelWriter.writeDouble(arg6, 3, this.getRadius());
        SafeParcelWriter.writeFloat(arg6, 4, this.getStrokeWidth());
        SafeParcelWriter.writeInt(arg6, 5, this.getStrokeColor());
        SafeParcelWriter.writeInt(arg6, 6, this.getFillColor());
        SafeParcelWriter.writeFloat(arg6, 7, this.getZIndex());
        SafeParcelWriter.writeBoolean(arg6, 8, this.isVisible());
        SafeParcelWriter.writeBoolean(arg6, 9, this.isClickable());
        SafeParcelWriter.writeTypedList(arg6, 10, this.getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }

    public final CircleOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }
}

