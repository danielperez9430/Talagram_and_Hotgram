package com.google.android.gms.wearable;

import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.internal.DataItemAssetParcelable;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Class(creator="PutDataRequestCreator") @Reserved(value={1}) @VisibleForTesting public class PutDataRequest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR = null;
    public static final String WEAR_URI_SCHEME = "wear";
    @Field(getter="getData", id=5) private byte[] data;
    @Field(getter="getUri", id=2) private final Uri uri;
    private static final long zzt;
    private static final Random zzu;
    @Field(getter="getAssetsInternal", id=4) private final Bundle zzv;
    @Field(getter="getSyncDeadline", id=6) private long zzw;

    static {
        PutDataRequest.CREATOR = new zzh();
        PutDataRequest.zzt = TimeUnit.MINUTES.toMillis(30);
        PutDataRequest.zzu = new SecureRandom();
    }

    @Constructor PutDataRequest(@Param(id=2) Uri arg1, @Param(id=4) Bundle arg2, @Param(id=5) byte[] arg3, @Param(id=6) long arg4) {
        super();
        this.uri = arg1;
        this.zzv = arg2;
        this.zzv.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        this.data = arg3;
        this.zzw = arg4;
    }

    private PutDataRequest(Uri arg7) {
        this(arg7, new Bundle(), null, PutDataRequest.zzt);
    }

    public static PutDataRequest create(String arg1) {
        Asserts.checkNotNull(arg1, "path must not be null");
        return PutDataRequest.zza(PutDataRequest.zza(arg1));
    }

    public static PutDataRequest createFromDataItem(DataItem arg4) {
        Asserts.checkNotNull(arg4, "source must not be null");
        PutDataRequest v0 = PutDataRequest.zza(arg4.getUri());
        Iterator v1 = arg4.getAssets().entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(((Map$Entry)v2).getValue().getId() == null) {
                String v0_1 = "Cannot create an asset for a put request without a digest: ";
                String v1_1 = String.valueOf(((Map$Entry)v2).getKey());
                v0_1 = v1_1.length() != 0 ? v0_1.concat(v1_1) : new String(v0_1);
                throw new IllegalStateException(v0_1);
            }

            v0.putAsset(((Map$Entry)v2).getKey(), Asset.createFromRef(((Map$Entry)v2).getValue().getId()));
        }

        v0.setData(arg4.getData());
        return v0;
    }

    public static PutDataRequest createWithAutoAppendedId(String arg3) {
        Asserts.checkNotNull(arg3, "pathPrefix must not be null");
        StringBuilder v0 = new StringBuilder(arg3);
        if(!arg3.endsWith("/")) {
            v0.append("/");
        }

        v0.append("PN");
        v0.append(PutDataRequest.zzu.nextLong());
        return new PutDataRequest(PutDataRequest.zza(v0.toString()));
    }

    @VisibleForTesting public Asset getAsset(String arg2) {
        Asserts.checkNotNull(arg2, "key must not be null");
        return this.zzv.getParcelable(arg2);
    }

    public Map getAssets() {
        HashMap v0 = new HashMap();
        Iterator v1 = this.zzv.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            v0.put(v2, this.zzv.getParcelable(((String)v2)));
        }

        return Collections.unmodifiableMap(((Map)v0));
    }

    @VisibleForTesting public byte[] getData() {
        return this.data;
    }

    public Uri getUri() {
        return this.uri;
    }

    public boolean hasAsset(String arg2) {
        Asserts.checkNotNull(arg2, "key must not be null");
        return this.zzv.containsKey(arg2);
    }

    public boolean isUrgent() {
        if(this.zzw == 0) {
            return 1;
        }

        return 0;
    }

    public PutDataRequest putAsset(String arg2, Asset arg3) {
        Preconditions.checkNotNull(arg2);
        Preconditions.checkNotNull(arg3);
        this.zzv.putParcelable(arg2, ((Parcelable)arg3));
        return this;
    }

    public PutDataRequest removeAsset(String arg2) {
        Asserts.checkNotNull(arg2, "key must not be null");
        this.zzv.remove(arg2);
        return this;
    }

    public PutDataRequest setData(byte[] arg1) {
        this.data = arg1;
        return this;
    }

    public PutDataRequest setUrgent() {
        this.zzw = 0;
        return this;
    }

    public String toString() {
        return this.toString(Log.isLoggable("DataMap", 3));
    }

    public String toString(boolean arg6) {
        String v6;
        Integer v1_1;
        String v1;
        StringBuilder v0 = new StringBuilder("PutDataRequest[");
        if(this.data == null) {
            v1 = "null";
        }
        else {
            v1_1 = Integer.valueOf(this.data.length);
        }

        v1 = String.valueOf(v1_1);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 7);
        v3.append("dataSz=");
        v3.append(v1);
        v0.append(v3.toString());
        int v1_2 = this.zzv.size();
        v3 = new StringBuilder(23);
        v3.append(", numAssets=");
        v3.append(v1_2);
        v0.append(v3.toString());
        v1 = String.valueOf(this.uri);
        v3 = new StringBuilder(String.valueOf(v1).length() + 6);
        v3.append(", uri=");
        v3.append(v1);
        v0.append(v3.toString());
        long v1_3 = this.zzw;
        StringBuilder v4 = new StringBuilder(35);
        v4.append(", syncDeadline=");
        v4.append(v1_3);
        v0.append(v4.toString());
        if(!arg6) {
            v6 = "]";
        }
        else {
            v0.append("]\n  assets: ");
            Iterator v6_1 = this.zzv.keySet().iterator();
            while(v6_1.hasNext()) {
                Object v1_4 = v6_1.next();
                String v2 = String.valueOf(this.zzv.getParcelable(((String)v1_4)));
                v4 = new StringBuilder(String.valueOf(v1_4).length() + 7 + String.valueOf(v2).length());
                v4.append("\n    ");
                v4.append(((String)v1_4));
                v4.append(": ");
                v4.append(v2);
                v0.append(v4.toString());
            }

            v6 = "\n  ]";
        }

        v0.append(v6);
        return v0.toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        Asserts.checkNotNull(arg5, "dest must not be null");
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getUri(), arg6, false);
        SafeParcelWriter.writeBundle(arg5, 4, this.zzv, false);
        SafeParcelWriter.writeByteArray(arg5, 5, this.getData(), false);
        SafeParcelWriter.writeLong(arg5, 6, this.zzw);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public static PutDataRequest zza(Uri arg1) {
        Asserts.checkNotNull(arg1, "uri must not be null");
        return new PutDataRequest(arg1);
    }

    private static Uri zza(String arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg2))) {
            if(arg2.startsWith("/")) {
                if(!arg2.startsWith("//")) {
                    return new Uri$Builder().scheme("wear").path(arg2).build();
                }

                throw new IllegalArgumentException("A path must start with a single / .");
            }

            throw new IllegalArgumentException("A path must start with a single / .");
        }

        throw new IllegalArgumentException("An empty path was supplied.");
    }
}

