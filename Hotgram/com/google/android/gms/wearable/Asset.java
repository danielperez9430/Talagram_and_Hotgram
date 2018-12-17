package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;

@Class(creator="AssetCreator") @Reserved(value={1}) @VisibleForTesting public class Asset extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getData", id=2) private byte[] data;
    @Field(id=5) private Uri uri;
    @Field(getter="getDigest", id=3) private String zze;
    @Field(id=4) private ParcelFileDescriptor zzf;

    static {
        Asset.CREATOR = new zze();
    }

    @Constructor Asset(@Param(id=2) byte[] arg1, @Param(id=3) String arg2, @Param(id=4) ParcelFileDescriptor arg3, @Param(id=5) Uri arg4) {
        super();
        this.data = arg1;
        this.zze = arg2;
        this.zzf = arg3;
        this.uri = arg4;
    }

    @VisibleForTesting public static Asset createFromBytes(byte[] arg2) {
        Asserts.checkNotNull(arg2);
        return new Asset(arg2, null, null, null);
    }

    @VisibleForTesting public static Asset createFromFd(ParcelFileDescriptor arg2) {
        Asserts.checkNotNull(arg2);
        return new Asset(null, null, arg2, null);
    }

    public static Asset createFromRef(String arg2) {
        Asserts.checkNotNull(arg2);
        return new Asset(null, arg2, null, null);
    }

    @VisibleForTesting public static Asset createFromUri(Uri arg2) {
        Asserts.checkNotNull(arg2);
        return new Asset(null, null, null, arg2);
    }

    public boolean equals(Object arg5) {
        if(this == (((Asset)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof Asset)) {
            return 0;
        }

        if((Arrays.equals(this.data, ((Asset)arg5).data)) && (Objects.equal(this.zze, ((Asset)arg5).zze)) && (Objects.equal(this.zzf, ((Asset)arg5).zzf)) && (Objects.equal(this.uri, ((Asset)arg5).uri))) {
            return 1;
        }

        return 0;
    }

    public final byte[] getData() {
        return this.data;
    }

    public String getDigest() {
        return this.zze;
    }

    public ParcelFileDescriptor getFd() {
        return this.zzf;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{this.data, this.zze, this.zzf, this.uri});
    }

    public String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append("Asset[@");
        v0.append(Integer.toHexString(this.hashCode()));
        if(this.zze == null) {
            v1 = ", nodigest";
        }
        else {
            v0.append(", ");
            v1 = this.zze;
        }

        v0.append(v1);
        if(this.data != null) {
            v0.append(", size=");
            v0.append(this.data.length);
        }

        if(this.zzf != null) {
            v0.append(", fd=");
            v0.append(this.zzf);
        }

        if(this.uri != null) {
            v0.append(", uri=");
            v0.append(this.uri);
        }

        v0.append("]");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        Asserts.checkNotNull(arg5);
        arg6 |= 1;
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeByteArray(arg5, 2, this.data, false);
        SafeParcelWriter.writeString(arg5, 3, this.getDigest(), false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzf, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 5, this.uri, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

