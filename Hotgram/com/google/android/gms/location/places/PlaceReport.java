package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator="PlaceReportCreator") public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getTag", id=3) private final String tag;
    @VersionField(id=1) private final int versionCode;
    @Field(getter="getPlaceId", id=2) private final String zza;
    @Field(getter="getSource", id=4) private final String zzb;

    static {
        PlaceReport.CREATOR = new zza();
    }

    @Constructor PlaceReport(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) String arg3, @Param(id=4) String arg4) {
        super();
        this.versionCode = arg1;
        this.zza = arg2;
        this.tag = arg3;
        this.zzb = arg4;
    }

    @VisibleForTesting public static PlaceReport create(String arg4, String arg5) {
        int v1;
        String v0 = "unknown";
        Preconditions.checkNotNull(arg4);
        Preconditions.checkNotEmpty(arg5);
        Preconditions.checkNotEmpty(v0);
        boolean v2 = false;
        switch(v0.hashCode()) {
            case -1436706272: {
                if(!v0.equals("inferredGeofencing")) {
                    goto label_39;
                }

                v1 = 2;
                break;
            }
            case -1194968642: {
                if(!v0.equals("userReported")) {
                    goto label_39;
                }

                v1 = 1;
                break;
            }
            case -284840886: {
                if(!v0.equals("unknown")) {
                    goto label_39;
                }

                v1 = 0;
                break;
            }
            case -262743844: {
                if(!v0.equals("inferredReverseGeocoding")) {
                    goto label_39;
                }

                v1 = 4;
                break;
            }
            case 1164924125: {
                if(v0.equals("inferredSnappedToRoad")) {
                    v1 = 5;
                    goto label_40;
                }

            label_39:
                v1 = -1;
                break;
            }
            case 1287171955: {
                if(!v0.equals("inferredRadioSignals")) {
                    goto label_39;
                }

                v1 = 3;
                break;
            }
            default: {
                goto label_39;
            }
        }

    label_40:
        switch(v1) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                v2 = true;
                break;
            }
            default: {
                break;
            }
        }

        Preconditions.checkArgument(v2, "Invalid source");
        return new PlaceReport(1, arg4, arg5, v0);
    }

    public boolean equals(Object arg4) {
        if(!(arg4 instanceof PlaceReport)) {
            return 0;
        }

        if((Objects.equal(this.zza, ((PlaceReport)arg4).zza)) && (Objects.equal(this.tag, ((PlaceReport)arg4).tag)) && (Objects.equal(this.zzb, ((PlaceReport)arg4).zzb))) {
            return 1;
        }

        return 0;
    }

    public String getPlaceId() {
        return this.zza;
    }

    public String getTag() {
        return this.tag;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.tag, this.zzb});
    }

    public String toString() {
        ToStringHelper v0 = Objects.toStringHelper(this);
        v0.add("placeId", this.zza);
        v0.add("tag", this.tag);
        if(!"unknown".equals(this.zzb)) {
            v0.add("source", this.zzb);
        }

        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.versionCode);
        SafeParcelWriter.writeString(arg4, 2, this.getPlaceId(), false);
        SafeParcelWriter.writeString(arg4, 3, this.getTag(), false);
        SafeParcelWriter.writeString(arg4, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

