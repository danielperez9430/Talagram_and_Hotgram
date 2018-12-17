package com.google.android.gms.maps.model;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Class(creator="PolylineOptionsCreator") @Reserved(value={1}) public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getColor", id=4) private int color;
    @Field(getter="getWidth", id=3) private float width;
    @Field(getter="getZIndex", id=5) private float zzcr;
    @Field(getter="isVisible", id=6) private boolean zzcs;
    @Field(getter="isClickable", id=8) private boolean zzct;
    @Field(getter="getPoints", id=2) private final List zzdw;
    @Field(getter="isGeodesic", id=7) private boolean zzdy;
    @Field(getter="getStartCap", id=9) private Cap zzeb;
    @Field(getter="getEndCap", id=10) private Cap zzec;
    @Field(getter="getJointType", id=11) private int zzed;
    @Field(getter="getPattern", id=12) private List zzee;

    static {
        PolylineOptions.CREATOR = new zzl();
    }

    @Constructor PolylineOptions(@Param(id=2) List arg3, @Param(id=3) float arg4, @Param(id=4) int arg5, @Param(id=5) float arg6, @Param(id=6) boolean arg7, @Param(id=7) boolean arg8, @Param(id=8) boolean arg9, @Param(id=9) Cap arg10, @Param(id=10) Cap arg11, @Param(id=11) int arg12, @Param(id=12) List arg13) {
        super();
        this.width = 10f;
        this.color = -16777216;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = arg3;
        this.width = arg4;
        this.color = arg5;
        this.zzcr = arg6;
        this.zzcs = arg7;
        this.zzdy = arg8;
        this.zzct = arg9;
        if(arg10 != null) {
            this.zzeb = arg10;
        }

        if(arg11 != null) {
            this.zzec = arg11;
        }

        this.zzed = arg12;
        this.zzee = arg13;
    }

    public PolylineOptions() {
        super();
        this.width = 10f;
        this.color = -16777216;
        this.zzcr = 0f;
        this.zzcs = true;
        this.zzdy = false;
        this.zzct = false;
        this.zzeb = new ButtCap();
        this.zzec = new ButtCap();
        this.zzed = 0;
        this.zzee = null;
        this.zzdw = new ArrayList();
    }

    public final PolylineOptions add(LatLng arg2) {
        this.zzdw.add(arg2);
        return this;
    }

    public final PolylineOptions add(LatLng[] arg2) {
        this.zzdw.addAll(Arrays.asList(((Object[])arg2)));
        return this;
    }

    public final PolylineOptions addAll(Iterable arg3) {
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            this.zzdw.add(v3.next());
        }

        return this;
    }

    public final PolylineOptions clickable(boolean arg1) {
        this.zzct = arg1;
        return this;
    }

    public final PolylineOptions color(int arg1) {
        this.color = arg1;
        return this;
    }

    public final PolylineOptions endCap(Cap arg2) {
        this.zzec = Preconditions.checkNotNull(arg2, "endCap must not be null");
        return this;
    }

    public final PolylineOptions geodesic(boolean arg1) {
        this.zzdy = arg1;
        return this;
    }

    public final int getColor() {
        return this.color;
    }

    public final Cap getEndCap() {
        return this.zzec;
    }

    public final int getJointType() {
        return this.zzed;
    }

    public final List getPattern() {
        return this.zzee;
    }

    public final List getPoints() {
        return this.zzdw;
    }

    public final Cap getStartCap() {
        return this.zzeb;
    }

    public final float getWidth() {
        return this.width;
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

    public final PolylineOptions jointType(int arg1) {
        this.zzed = arg1;
        return this;
    }

    public final PolylineOptions pattern(List arg1) {
        this.zzee = arg1;
        return this;
    }

    public final PolylineOptions startCap(Cap arg2) {
        this.zzeb = Preconditions.checkNotNull(arg2, "startCap must not be null");
        return this;
    }

    public final PolylineOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final PolylineOptions width(float arg1) {
        this.width = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedList(arg5, 2, this.getPoints(), false);
        SafeParcelWriter.writeFloat(arg5, 3, this.getWidth());
        SafeParcelWriter.writeInt(arg5, 4, this.getColor());
        SafeParcelWriter.writeFloat(arg5, 5, this.getZIndex());
        SafeParcelWriter.writeBoolean(arg5, 6, this.isVisible());
        SafeParcelWriter.writeBoolean(arg5, 7, this.isGeodesic());
        SafeParcelWriter.writeBoolean(arg5, 8, this.isClickable());
        SafeParcelWriter.writeParcelable(arg5, 9, this.getStartCap(), arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 10, this.getEndCap(), arg6, false);
        SafeParcelWriter.writeInt(arg5, 11, this.getJointType());
        SafeParcelWriter.writeTypedList(arg5, 12, this.getPattern(), false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final PolylineOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }
}

