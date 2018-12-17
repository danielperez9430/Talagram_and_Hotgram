package com.google.android.gms.location;

import android.app.PendingIntent;
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
import java.util.Collections;
import java.util.List;

@Class(creator="RemoveGeofencingRequestCreator") @Reserved(value={1000}) public final class zzal extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValue="", getter="getTag", id=3) private final String tag;
    @Field(getter="getGeofenceIds", id=1) private final List zzbu;
    @Field(getter="getPendingIntent", id=2) private final PendingIntent zzbv;

    static {
        zzal.CREATOR = new zzam();
    }

    @Constructor zzal(@Param(id=1) List arg1, @Param(id=2) PendingIntent arg2, @Param(id=3) String arg3) {
        super();
        arg1 = arg1 == null ? Collections.emptyList() : Collections.unmodifiableList(arg1);
        this.zzbu = arg1;
        this.zzbv = arg2;
        this.tag = arg3;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeStringList(arg5, 1, this.zzbu, false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzbv, arg6, false);
        SafeParcelWriter.writeString(arg5, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public static zzal zza(PendingIntent arg3) {
        Preconditions.checkNotNull(arg3, "PendingIntent can not be null.");
        return new zzal(null, arg3, "");
    }

    public static zzal zza(List arg3) {
        Preconditions.checkNotNull(arg3, "geofence can\'t be null.");
        Preconditions.checkArgument(arg3.isEmpty() ^ 1, "Geofences must contains at least one id.");
        return new zzal(arg3, null, "");
    }
}

