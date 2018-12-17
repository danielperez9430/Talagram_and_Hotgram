package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator="WebImageCreator") public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @VersionField(id=1) private final int zzal;
    @Field(getter="getWidth", id=3) private final int zzps;
    @Field(getter="getHeight", id=4) private final int zzpt;
    @Field(getter="getUrl", id=2) private final Uri zzpu;

    static {
        WebImage.CREATOR = new WebImageCreator();
    }

    @Constructor WebImage(@Param(id=1) int arg1, @Param(id=2) Uri arg2, @Param(id=3) int arg3, @Param(id=4) int arg4) {
        super();
        this.zzal = arg1;
        this.zzpu = arg2;
        this.zzps = arg3;
        this.zzpt = arg4;
    }

    public WebImage(Uri arg2) {
        this(arg2, 0, 0);
    }

    public WebImage(Uri arg2, int arg3, int arg4) {
        this(1, arg2, arg3, arg4);
        if(arg2 != null) {
            if(arg3 >= 0 && arg4 >= 0) {
                return;
            }

            throw new IllegalArgumentException("width and height must not be negative");
        }

        throw new IllegalArgumentException("url cannot be null");
    }

    public WebImage(JSONObject arg5) {
        this(WebImage.zza(arg5), arg5.optInt("width", 0), arg5.optInt("height", 0));
    }

    public final boolean equals(Object arg5) {
        if(this == (((WebImage)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(!(arg5 instanceof WebImage)) {
            }
            else if((Objects.equal(this.zzpu, ((WebImage)arg5).zzpu)) && this.zzps == ((WebImage)arg5).zzps && this.zzpt == ((WebImage)arg5).zzpt) {
                return 1;
            }
        }

        return 0;
    }

    public final int getHeight() {
        return this.zzpt;
    }

    public final Uri getUrl() {
        return this.zzpu;
    }

    public final int getWidth() {
        return this.zzps;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzpu, Integer.valueOf(this.zzps), Integer.valueOf(this.zzpt)});
    }

    public final JSONObject toJson() {
        JSONObject v0 = new JSONObject();
        try {
            v0.put("url", this.zzpu.toString());
            v0.put("width", this.zzps);
            v0.put("height", this.zzpt);
            return v0;
        }
        catch(JSONException ) {
            return v0;
        }
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.zzps), Integer.valueOf(this.zzpt), this.zzpu.toString());
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getUrl(), arg6, false);
        SafeParcelWriter.writeInt(arg5, 3, this.getWidth());
        SafeParcelWriter.writeInt(arg5, 4, this.getHeight());
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    private static Uri zza(JSONObject arg1) {
        Uri v1;
        if(arg1.has("url")) {
            try {
                v1 = Uri.parse(arg1.getString("url"));
            }
            catch(JSONException ) {
            label_7:
                v1 = null;
            }
        }
        else {
            goto label_7;
        }

        return v1;
    }
}

