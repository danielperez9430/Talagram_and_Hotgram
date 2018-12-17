package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Locale;

@Class(creator="PlacesParamsCreator") @Reserved(value={1000, 5}) public final class zzat extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private final String zzcx;
    @Field(id=4) private final String zzfd;
    @Field(id=7) private final int zzfe;
    private static final zzat zzhf;
    @Field(id=1) private final String zzhg;
    @Field(id=2) private final String zzhh;
    @Field(id=6) private final int zzhi;

    static {
        zzat.zzhf = new zzat("com.google.android.gms", Locale.getDefault(), null);
        zzat.CREATOR = new zzau();
    }

    @Constructor public zzat(@Param(id=1) String arg1, @Param(id=2) String arg2, @Param(id=3) String arg3, @Param(id=4) String arg4, @Param(id=6) int arg5, @Param(id=7) int arg6) {
        super();
        this.zzhg = arg1;
        this.zzhh = arg2;
        this.zzcx = arg3;
        this.zzfd = arg4;
        this.zzhi = arg5;
        this.zzfe = arg6;
    }

    private zzat(String arg8, Locale arg9, String arg10) {
        this(arg8, arg9.toString(), null, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
    }

    public zzat(String arg8, Locale arg9, String arg10, String arg11, int arg12) {
        this(arg8, arg9.toString(), arg10, arg11, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, arg12);
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzat)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(!(arg5 instanceof zzat)) {
            }
            else if(this.zzhi == ((zzat)arg5).zzhi && this.zzfe == ((zzat)arg5).zzfe && (this.zzhh.equals(((zzat)arg5).zzhh)) && (this.zzhg.equals(((zzat)arg5).zzhg)) && (Objects.equal(this.zzcx, ((zzat)arg5).zzcx)) && (Objects.equal(this.zzfd, ((zzat)arg5).zzfd))) {
                return 1;
            }
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzhg, this.zzhh, this.zzcx, this.zzfd, Integer.valueOf(this.zzhi), Integer.valueOf(this.zzfe)});
    }

    @SuppressLint(value={"DefaultLocale"}) public final String toString() {
        return Objects.toStringHelper(this).add("clientPackageName", this.zzhg).add("locale", this.zzhh).add("accountName", this.zzcx).add("gCoreClientName", this.zzfd).toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.zzhg, false);
        SafeParcelWriter.writeString(arg4, 2, this.zzhh, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzcx, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzfd, false);
        SafeParcelWriter.writeInt(arg4, 6, this.zzhi);
        SafeParcelWriter.writeInt(arg4, 7, this.zzfe);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

