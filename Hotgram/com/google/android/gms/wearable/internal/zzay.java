package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;

@Class(creator="ChannelImplCreator") @Reserved(value={1}) public final class zzay extends AbstractSafeParcelable implements Channel, com.google.android.gms.wearable.ChannelClient$Channel {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getToken", id=2) private final String zzce;
    @Field(getter="getPath", id=4) private final String zzcl;
    @Field(getter="getNodeId", id=3) private final String zzo;

    static {
        zzay.CREATOR = new zzbi();
    }

    @Constructor public zzay(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3) {
        super();
        this.zzce = Preconditions.checkNotNull(arg1);
        this.zzo = Preconditions.checkNotNull(arg2);
        this.zzcl = Preconditions.checkNotNull(arg3);
    }

    public final PendingResult addListener(GoogleApiClient arg4, ChannelListener arg5) {
        return zzb.zza(arg4, new zzbf(this.zzce, new IntentFilter[]{zzgj.zzc("com.google.android.gms.wearable.CHANNEL_EVENT")}), arg5);
    }

    public final PendingResult close(GoogleApiClient arg2) {
        return arg2.enqueue(new zzaz(this, arg2));
    }

    public final PendingResult close(GoogleApiClient arg2, int arg3) {
        return arg2.enqueue(new zzba(this, arg2, arg3));
    }

    public final boolean equals(Object arg5) {
        if((((zzay)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzay)) {
            return 0;
        }

        if((this.zzce.equals(((zzay)arg5).zzce)) && (Objects.equal(((zzay)arg5).zzo, this.zzo)) && (Objects.equal(((zzay)arg5).zzcl, this.zzcl))) {
            return 1;
        }

        return 0;
    }

    public final PendingResult getInputStream(GoogleApiClient arg2) {
        return arg2.enqueue(new zzbb(this, arg2));
    }

    public final String getNodeId() {
        return this.zzo;
    }

    public final PendingResult getOutputStream(GoogleApiClient arg2) {
        return arg2.enqueue(new zzbc(this, arg2));
    }

    public final String getPath() {
        return this.zzcl;
    }

    public final int hashCode() {
        return this.zzce.hashCode();
    }

    public final PendingResult receiveFile(GoogleApiClient arg2, Uri arg3, boolean arg4) {
        Preconditions.checkNotNull(arg2, "client is null");
        Preconditions.checkNotNull(arg3, "uri is null");
        return arg2.enqueue(new zzbd(this, arg2, arg3, arg4));
    }

    public final PendingResult removeListener(GoogleApiClient arg3, ChannelListener arg4) {
        Preconditions.checkNotNull(arg3, "client is null");
        Preconditions.checkNotNull(arg4, "listener is null");
        return arg3.enqueue(new zzan(arg3, arg4, this.zzce));
    }

    public final PendingResult sendFile(GoogleApiClient arg8, Uri arg9) {
        return this.sendFile(arg8, arg9, 0, -1);
    }

    public final PendingResult sendFile(GoogleApiClient arg16, Uri arg17, long arg18, long arg20) {
        GoogleApiClient v8 = arg16;
        Preconditions.checkNotNull(v8, "client is null");
        Preconditions.checkNotNull(this.zzce, "token is null");
        Preconditions.checkNotNull(arg17, "uri is null");
        long v0 = 0;
        boolean v2 = Long.compare(arg18, v0) >= 0 ? true : false;
        Preconditions.checkArgument(v2, "startOffset is negative: %s", new Object[]{Long.valueOf(arg18)});
        boolean v0_1 = arg20 >= v0 || arg20 == -1 ? true : false;
        Preconditions.checkArgument(v0_1, "invalid length: %s", new Object[]{Long.valueOf(arg20)});
        return v8.enqueue(new zzbe(this, arg16, arg17, arg18, arg20));
    }

    public final String toString() {
        String v2;
        char[] v0 = this.zzce.toCharArray();
        int v1 = v0.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v1) {
            v4 += v0[v3];
            ++v3;
        }

        String v0_1 = this.zzce.trim();
        v1 = v0_1.length();
        if(v1 > 25) {
            v2 = v0_1.substring(0, 10);
            v0_1 = v0_1.substring(v1 - 10, v1);
            StringBuilder v3_1 = new StringBuilder(String.valueOf(v2).length() + 16 + String.valueOf(v0_1).length());
            v3_1.append(v2);
            v3_1.append("...");
            v3_1.append(v0_1);
            v3_1.append("::");
            v3_1.append(v4);
            v0_1 = v3_1.toString();
        }

        String v1_1 = this.zzo;
        v2 = this.zzcl;
        StringBuilder v4_1 = new StringBuilder(String.valueOf(v0_1).length() + 31 + String.valueOf(v1_1).length() + String.valueOf(v2).length());
        v4_1.append("Channel{token=");
        v4_1.append(v0_1);
        v4_1.append(", nodeId=");
        v4_1.append(v1_1);
        v4_1.append(", path=");
        v4_1.append(v2);
        v4_1.append("}");
        return v4_1.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzce, false);
        SafeParcelWriter.writeString(arg4, 3, this.getNodeId(), false);
        SafeParcelWriter.writeString(arg4, 4, this.getPath(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    static String zza(zzay arg0) {
        return arg0.zzce;
    }

    public final String zzc() {
        return this.zzce;
    }
}

