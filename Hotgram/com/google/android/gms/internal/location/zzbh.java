package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@Class(creator="ParcelableGeofenceCreator") @Reserved(value={1000}) @VisibleForTesting public final class zzbh extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getRequestId", id=1) private final String zzad;
    @Field(getter="getTransitionTypes", id=7) private final int zzae;
    @Field(getter="getType", id=3) private final short zzag;
    @Field(getter="getLatitude", id=4) private final double zzah;
    @Field(getter="getLongitude", id=5) private final double zzai;
    @Field(getter="getRadius", id=6) private final float zzaj;
    @Field(defaultValue="0", getter="getNotificationResponsiveness", id=8) private final int zzak;
    @Field(defaultValue="-1", getter="getLoiteringDelay", id=9) private final int zzal;
    @Field(getter="getExpirationTime", id=2) private final long zzdo;

    static {
        zzbh.CREATOR = new zzbi();
    }

    @Constructor public zzbh(@Param(id=1) String arg4, @Param(id=7) int arg5, @Param(id=3) short arg6, @Param(id=4) double arg7, @Param(id=5) double arg9, @Param(id=6) float arg11, @Param(id=2) long arg12, @Param(id=8) int arg14, @Param(id=9) int arg15) {
        StringBuilder v6;
        super();
        if(arg4 != null) {
            if(arg4.length() > 100) {
            }
            else if(arg11 > 0f) {
                if(arg7 <= 90 && arg7 >= -90) {
                    if(arg9 <= 180 && arg9 >= -180) {
                        int v0 = arg5 & 7;
                        if(v0 != 0) {
                            this.zzag = arg6;
                            this.zzad = arg4;
                            this.zzah = arg7;
                            this.zzai = arg9;
                            this.zzaj = arg11;
                            this.zzdo = arg12;
                            this.zzae = v0;
                            this.zzak = arg14;
                            this.zzal = arg15;
                            return;
                        }
                        else {
                            StringBuilder v7 = new StringBuilder(46);
                            v7.append("No supported transition specified: ");
                            v7.append(arg5);
                            throw new IllegalArgumentException(v7.toString());
                        }
                    }

                    v6 = new StringBuilder(43);
                    v6.append("invalid longitude: ");
                    v6.append(arg9);
                    throw new IllegalArgumentException(v6.toString());
                }

                v6 = new StringBuilder(42);
                v6.append("invalid latitude: ");
                v6.append(arg7);
                throw new IllegalArgumentException(v6.toString());
            }
            else {
                v6 = new StringBuilder(31);
                v6.append("invalid radius: ");
                v6.append(arg11);
                throw new IllegalArgumentException(v6.toString());
            }
        }

        String v6_1 = "requestId is null or too long: ";
        arg4 = String.valueOf(arg4);
        arg4 = arg4.length() != 0 ? v6_1.concat(arg4) : new String(v6_1);
        throw new IllegalArgumentException(arg4);
    }

    public final boolean equals(Object arg8) {
        if(this == (((zzbh)arg8))) {
            return 1;
        }

        if(arg8 == null) {
            return 0;
        }

        if(!(arg8 instanceof zzbh)) {
            return 0;
        }

        if(this.zzaj != ((zzbh)arg8).zzaj) {
            return 0;
        }

        if(this.zzah != ((zzbh)arg8).zzah) {
            return 0;
        }

        if(this.zzai != ((zzbh)arg8).zzai) {
            return 0;
        }

        if(this.zzag != ((zzbh)arg8).zzag) {
            return 0;
        }

        return 1;
    }

    public final String getRequestId() {
        return this.zzad;
    }

    public final int hashCode() {
        long v0 = Double.doubleToLongBits(this.zzah);
        long v3 = Double.doubleToLongBits(this.zzai);
        return (((((((int)(v0 ^ v0 >>> 32))) + 31) * 31 + (((int)(v3 >>> 32 ^ v3)))) * 31 + Float.floatToIntBits(this.zzaj)) * 31 + this.zzag) * 31 + this.zzae;
    }

    public final String toString() {
        Object v3;
        Locale v0 = Locale.US;
        String v1 = "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]";
        Object[] v2 = new Object[9];
        if(this.zzag != 1) {
            v3 = null;
        }
        else {
            String v3_1 = "CIRCLE";
        }

        v2[0] = v3;
        v2[1] = this.zzad.replaceAll("\\p{C}", "?");
        v2[2] = Integer.valueOf(this.zzae);
        v2[3] = Double.valueOf(this.zzah);
        v2[4] = Double.valueOf(this.zzai);
        v2[5] = Float.valueOf(this.zzaj);
        v2[6] = Integer.valueOf(this.zzak / 1000);
        v2[7] = Integer.valueOf(this.zzal);
        v2[8] = Long.valueOf(this.zzdo);
        return String.format(v0, v1, v2);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.getRequestId(), false);
        SafeParcelWriter.writeLong(arg4, 2, this.zzdo);
        SafeParcelWriter.writeShort(arg4, 3, this.zzag);
        SafeParcelWriter.writeDouble(arg4, 4, this.zzah);
        SafeParcelWriter.writeDouble(arg4, 5, this.zzai);
        SafeParcelWriter.writeFloat(arg4, 6, this.zzaj);
        SafeParcelWriter.writeInt(arg4, 7, this.zzae);
        SafeParcelWriter.writeInt(arg4, 8, this.zzak);
        SafeParcelWriter.writeInt(arg4, 9, this.zzal);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public static zzbh zza(byte[] arg3) {
        Parcel v0 = Parcel.obtain();
        v0.unmarshall(arg3, 0, arg3.length);
        v0.setDataPosition(0);
        Object v3 = zzbh.CREATOR.createFromParcel(v0);
        v0.recycle();
        return ((zzbh)v3);
    }
}

