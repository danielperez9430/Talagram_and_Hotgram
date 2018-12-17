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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Class(creator="PolygonOptionsCreator") @Reserved(value={1}) public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getFillColor", id=6) private int fillColor;
    @Field(getter="getStrokeColor", id=5) private int strokeColor;
    @Field(getter="getStrokeWidth", id=4) private float zzcq;
    @Field(getter="getZIndex", id=7) private float zzcr;
    @Field(getter="isVisible", id=8) private boolean zzcs;
    @Field(getter="isClickable", id=10) private boolean zzct;
    @Field(getter="getStrokePattern", id=12) private List zzcu;
    @Field(getter="getPoints", id=2) private final List zzdw;
    @Field(getter="getHolesForParcel", id=3, type="java.util.List") private final List zzdx;
    @Field(getter="isGeodesic", id=9) private boolean zzdy;
    @Field(getter="getStrokeJointType", id=11) private int zzdz;

    static {
        PolygonOptions.CREATOR = new zzk();
    }

    @Constructor PolygonOptions(@Param(id=2) List arg3, @Param(id=3) List arg4, @Param(id=4) float arg5, @Param(id=5) int arg6, @Param(id=6) int arg7, @Param(id=7) float arg8, @Param(id=8) boolean arg9, @Param(id=9) boolean arg10, @Param(id=10) boolean arg11, @Param(id=11) int arg12, @Param(id=12) List arg13) {
        super();
        this.zzcq = 10f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzdz = 0;
        this.zzcu = null;
        this.zzdw = arg3;
        this.zzdx = arg4;
        this.zzcq = arg5;
        this.strokeColor = arg6;
        this.fillColor = arg7;
        this.zzcr = arg8;
        this.zzcs = arg9;
        this.zzdy = arg10;
        this.zzct = arg11;
        this.zzdz = arg12;
        this.zzcu = arg13;
    }

    public PolygonOptions() {
        super();
        this.zzcq = 10f;
        this.strokeColor = -16777216;
        this.fillColor = 0;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzdz = 0;
        this.zzcu = null;
        this.zzdw = new ArrayList();
        this.zzdx = new ArrayList();
    }

    public final PolygonOptions add(LatLng arg2) {
        this.zzdw.add(arg2);
        return this;
    }

    public final PolygonOptions add(LatLng[] arg2) {
        this.zzdw.addAll(Arrays.asList(((Object[])arg2)));
        return this;
    }

    public final PolygonOptions addAll(Iterable arg3) {
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            this.zzdw.add(v3.next());
        }

        return this;
    }

    public final PolygonOptions addHole(Iterable arg3) {
        ArrayList v0 = new ArrayList();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            v0.add(v3.next());
        }

        this.zzdx.add(v0);
        return this;
    }

    public final PolygonOptions clickable(boolean arg1) {
        this.zzct = arg1;
        return this;
    }

    public final PolygonOptions fillColor(int arg1) {
        this.fillColor = arg1;
        return this;
    }

    public final PolygonOptions geodesic(boolean arg1) {
        this.zzdy = arg1;
        return this;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final List getHoles() {
        return this.zzdx;
    }

    public final List getPoints() {
        return this.zzdw;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final int getStrokeJointType() {
        return this.zzdz;
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

    public final boolean isGeodesic() {
        return this.zzdy;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final PolygonOptions strokeColor(int arg1) {
        this.strokeColor = arg1;
        return this;
    }

    public final PolygonOptions strokeJointType(int arg1) {
        this.zzdz = arg1;
        return this;
    }

    public final PolygonOptions strokePattern(List arg1) {
        this.zzcu = arg1;
        return this;
    }

    public final PolygonOptions strokeWidth(float arg1) {
        this.zzcq = arg1;
        return this;
    }

    public final PolygonOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 2, this.getPoints(), false);
        SafeParcelWriter.writeList(arg4, 3, this.zzdx, false);
        SafeParcelWriter.writeFloat(arg4, 4, this.getStrokeWidth());
        SafeParcelWriter.writeInt(arg4, 5, this.getStrokeColor());
        SafeParcelWriter.writeInt(arg4, 6, this.getFillColor());
        SafeParcelWriter.writeFloat(arg4, 7, this.getZIndex());
        SafeParcelWriter.writeBoolean(arg4, 8, this.isVisible());
        SafeParcelWriter.writeBoolean(arg4, 9, this.isGeodesic());
        SafeParcelWriter.writeBoolean(arg4, 10, this.isClickable());
        SafeParcelWriter.writeInt(arg4, 11, this.getStrokeJointType());
        SafeParcelWriter.writeTypedList(arg4, 12, this.getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public final PolygonOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }
}

