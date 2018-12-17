package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

@Class(creator="AddPlaceRequestCreator") @Reserved(value={1000}) @Deprecated public class AddPlaceRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getLatLng", id=2) private final LatLng latLng;
    @Field(getter="getName", id=1) private final String name;
    @Field(getter="getAddress", id=3) private final String zzdp;
    @Field(getter="getPlaceTypes", id=4) private final List zzdq;
    @Field(getter="getPhoneNumber", id=5) private final String zzdr;
    @Field(getter="getWebsiteUri", id=6) private final Uri zzds;

    static {
        AddPlaceRequest.CREATOR = new zzc();
    }

    @Constructor public AddPlaceRequest(@Param(id=1) String arg1, @Param(id=2) LatLng arg2, @Param(id=3) String arg3, @Param(id=4) List arg4, @Param(id=5) String arg5, @Param(id=6) Uri arg6) {
        super();
        this.name = Preconditions.checkNotEmpty(arg1);
        this.latLng = Preconditions.checkNotNull(arg2);
        this.zzdp = Preconditions.checkNotEmpty(arg3);
        this.zzdq = new ArrayList(Preconditions.checkNotNull(arg4));
        boolean v2 = true;
        Preconditions.checkArgument(this.zzdq.isEmpty() ^ 1, "At least one place type should be provided.");
        if(TextUtils.isEmpty(((CharSequence)arg5))) {
            if(arg6 != null) {
            }
            else {
                v2 = false;
            }
        }

        Preconditions.checkArgument(v2, "One of phone number or URI should be provided.");
        this.zzdr = arg5;
        this.zzds = arg6;
    }

    public AddPlaceRequest(String arg8, LatLng arg9, String arg10, List arg11, Uri arg12) {
        this(arg8, arg9, arg10, arg11, null, Preconditions.checkNotNull(arg12));
    }

    public AddPlaceRequest(String arg8, LatLng arg9, String arg10, List arg11, String arg12) {
        this(arg8, arg9, arg10, arg11, Preconditions.checkNotEmpty(arg12), null);
    }

    public String getAddress() {
        return this.zzdp;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.zzdr;
    }

    public List getPlaceTypes() {
        return this.zzdq;
    }

    public Uri getWebsiteUri() {
        return this.zzds;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("latLng", this.latLng).add("address", this.zzdp).add("placeTypes", this.zzdq).add("phoneNumer", this.zzdr).add("websiteUri", this.zzds).toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 1, this.getName(), false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getLatLng(), arg6, false);
        SafeParcelWriter.writeString(arg5, 3, this.getAddress(), false);
        SafeParcelWriter.writeIntegerList(arg5, 4, this.getPlaceTypes(), false);
        SafeParcelWriter.writeString(arg5, 5, this.getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.getWebsiteUri(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

