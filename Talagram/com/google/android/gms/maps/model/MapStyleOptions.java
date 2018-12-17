package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;

@Class(creator="MapStyleOptionsCreator") @Reserved(value={1}) public final class MapStyleOptions extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    private static final String TAG = "MapStyleOptions";
    @Field(getter="getJson", id=2) private String zzdk;

    static {
        MapStyleOptions.CREATOR = new zzg();
    }

    @Constructor public MapStyleOptions(@Param(id=2) String arg1) {
        super();
        this.zzdk = arg1;
    }

    public static MapStyleOptions loadRawResourceStyle(Context arg3, int arg4) {
        InputStream v3 = arg3.getResources().openRawResource(arg4);
        try {
            return new MapStyleOptions(new String(IOUtils.readInputStreamFully(v3), "UTF-8"));
        }
        catch(IOException v3_1) {
            String v3_2 = String.valueOf(v3_1);
            StringBuilder v2 = new StringBuilder(String.valueOf(v3_2).length() + 37);
            v2.append("Failed to read resource ");
            v2.append(arg4);
            v2.append(": ");
            v2.append(v3_2);
            throw new Resources$NotFoundException(v2.toString());
        }
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzdk, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

