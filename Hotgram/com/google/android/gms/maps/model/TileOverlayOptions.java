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
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

@Class(creator="TileOverlayOptionsCreator") @Reserved(value={1}) public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getZIndex", id=4) private float zzcr;
    @Field(getter="isVisible", id=3) private boolean zzcs;
    @Field(getter="getTransparency", id=6) private float zzcz;
    @Field(getter="getTileProviderDelegate", id=2, type="android.os.IBinder") private zzaf zzeh;
    private TileProvider zzei;
    @Field(defaultValue="true", getter="getFadeIn", id=5) private boolean zzej;

    static {
        TileOverlayOptions.CREATOR = new zzu();
    }

    @Constructor TileOverlayOptions(@Param(id=2) IBinder arg2, @Param(id=3) boolean arg3, @Param(id=4) float arg4, @Param(id=5) boolean arg5, @Param(id=6) float arg6) {
        zzs v2_1;
        super();
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0f;
        this.zzeh = zzag.zzk(arg2);
        if(this.zzeh == null) {
            TileProvider v2 = null;
        }
        else {
            v2_1 = new zzs(this);
        }

        this.zzei = ((TileProvider)v2_1);
        this.zzcs = arg3;
        this.zzcr = arg4;
        this.zzej = arg5;
        this.zzcz = arg6;
    }

    public TileOverlayOptions() {
        super();
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0f;
    }

    public final TileOverlayOptions fadeIn(boolean arg1) {
        this.zzej = arg1;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzej;
    }

    public final TileProvider getTileProvider() {
        return this.zzei;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final TileOverlayOptions tileProvider(TileProvider arg2) {
        zzaf v2;
        this.zzei = arg2;
        if(this.zzei == null) {
            v2 = null;
        }
        else {
            zzt v2_1 = new zzt(this, arg2);
        }

        this.zzeh = v2;
        return this;
    }

    public final TileOverlayOptions transparency(float arg3) {
        boolean v0 = arg3 < 0f || arg3 > 1f ? false : true;
        Preconditions.checkArgument(v0, "Transparency must be in the range [0..1]");
        this.zzcz = arg3;
        return this;
    }

    public final TileOverlayOptions visible(boolean arg1) {
        this.zzcs = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeIBinder(arg4, 2, this.zzeh.asBinder(), false);
        SafeParcelWriter.writeBoolean(arg4, 3, this.isVisible());
        SafeParcelWriter.writeFloat(arg4, 4, this.getZIndex());
        SafeParcelWriter.writeBoolean(arg4, 5, this.getFadeIn());
        SafeParcelWriter.writeFloat(arg4, 6, this.getTransparency());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public final TileOverlayOptions zIndex(float arg1) {
        this.zzcr = arg1;
        return this;
    }

    static zzaf zza(TileOverlayOptions arg0) {
        return arg0.zzeh;
    }
}

