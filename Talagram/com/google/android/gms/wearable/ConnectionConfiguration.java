package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="ConnectionConfigurationCreator") @Reserved(value={1}) public class ConnectionConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getName", id=2) private final String name;
    @Field(getter="getType", id=4) private final int type;
    @Field(getter="getAddress", id=3) private final String zzi;
    @Field(getter="getRole", id=5) private final int zzj;
    @Field(getter="isEnabled", id=6) private final boolean zzk;
    @Field(getter="isConnected", id=7) private volatile boolean zzl;
    @Field(getter="getPeerNodeId", id=8) private volatile String zzm;
    @Field(getter="getBtlePriority", id=9) private boolean zzn;
    @Field(getter="getNodeId", id=10) private String zzo;

    static {
        ConnectionConfiguration.CREATOR = new zzg();
    }

    @Constructor ConnectionConfiguration(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) int arg3, @Param(id=5) int arg4, @Param(id=6) boolean arg5, @Param(id=7) boolean arg6, @Param(id=8) String arg7, @Param(id=9) boolean arg8, @Param(id=10) String arg9) {
        super();
        this.name = arg1;
        this.zzi = arg2;
        this.type = arg3;
        this.zzj = arg4;
        this.zzk = arg5;
        this.zzl = arg6;
        this.zzm = arg7;
        this.zzn = arg8;
        this.zzo = arg9;
    }

    public boolean equals(Object arg4) {
        if(!(arg4 instanceof ConnectionConfiguration)) {
            return 0;
        }

        if((Objects.equal(this.name, ((ConnectionConfiguration)arg4).name)) && (Objects.equal(this.zzi, ((ConnectionConfiguration)arg4).zzi)) && (Objects.equal(Integer.valueOf(this.type), Integer.valueOf(((ConnectionConfiguration)arg4).type))) && (Objects.equal(Integer.valueOf(this.zzj), Integer.valueOf(((ConnectionConfiguration)arg4).zzj))) && (Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(((ConnectionConfiguration)arg4).zzk))) && (Objects.equal(Boolean.valueOf(this.zzn), Boolean.valueOf(((ConnectionConfiguration)arg4).zzn)))) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.name, this.zzi, Integer.valueOf(this.type), Integer.valueOf(this.zzj), Boolean.valueOf(this.zzk), Boolean.valueOf(this.zzn)});
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("ConnectionConfiguration[ ");
        String v1 = "mName=";
        String v2 = String.valueOf(this.name);
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        v0.append(v1);
        v1 = ", mAddress=";
        v2 = String.valueOf(this.zzi);
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        v0.append(v1);
        int v1_1 = this.type;
        StringBuilder v2_1 = new StringBuilder(19);
        v2_1.append(", mType=");
        v2_1.append(v1_1);
        v0.append(v2_1.toString());
        v1_1 = this.zzj;
        v2_1 = new StringBuilder(19);
        v2_1.append(", mRole=");
        v2_1.append(v1_1);
        v0.append(v2_1.toString());
        boolean v1_2 = this.zzk;
        StringBuilder v3 = new StringBuilder(16);
        v3.append(", mEnabled=");
        v3.append(v1_2);
        v0.append(v3.toString());
        v1_2 = this.zzl;
        v3 = new StringBuilder(20);
        v3.append(", mIsConnected=");
        v3.append(v1_2);
        v0.append(v3.toString());
        v1 = ", mPeerNodeId=";
        v2 = String.valueOf(this.zzm);
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        v0.append(v1);
        v1_2 = this.zzn;
        v3 = new StringBuilder(21);
        v3.append(", mBtlePriority=");
        v3.append(v1_2);
        v0.append(v3.toString());
        v1 = ", mNodeId=";
        v2 = String.valueOf(this.zzo);
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        v0.append(v1);
        v0.append("]");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.name, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzi, false);
        SafeParcelWriter.writeInt(arg4, 4, this.type);
        SafeParcelWriter.writeInt(arg4, 5, this.zzj);
        SafeParcelWriter.writeBoolean(arg4, 6, this.zzk);
        SafeParcelWriter.writeBoolean(arg4, 7, this.zzl);
        SafeParcelWriter.writeString(arg4, 8, this.zzm, false);
        SafeParcelWriter.writeBoolean(arg4, 9, this.zzn);
        SafeParcelWriter.writeString(arg4, 10, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

