package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;

@Class(creator="ChannelEventParcelableCreator") @Reserved(value={1}) public final class zzaw extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private final int type;
    @Field(id=5) private final int zzcj;
    @Field(id=2) private final zzay zzck;
    @Field(id=4) private final int zzg;

    static {
        zzaw.CREATOR = new zzax();
    }

    @Constructor public zzaw(@Param(id=2) zzay arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) int arg4) {
        super();
        this.zzck = arg1;
        this.type = arg2;
        this.zzg = arg3;
        this.zzcj = arg4;
    }

    public final String toString() {
        String v2_1;
        String v1_1;
        String v0 = String.valueOf(this.zzck);
        int v1 = this.type;
        switch(v1) {
            case 1: {
                v1_1 = "CHANNEL_OPENED";
                break;
            }
            case 2: {
                v1_1 = "CHANNEL_CLOSED";
                break;
            }
            case 3: {
                v1_1 = "INPUT_CLOSED";
                break;
            }
            case 4: {
                v1_1 = "OUTPUT_CLOSED";
                break;
            }
            default: {
                v1_1 = Integer.toString(v1);
                break;
            }
        }

        int v2 = this.zzg;
        switch(v2) {
            case 0: {
                v2_1 = "CLOSE_REASON_NORMAL";
                break;
            }
            case 1: {
                v2_1 = "CLOSE_REASON_DISCONNECTED";
                break;
            }
            case 2: {
                v2_1 = "CLOSE_REASON_REMOTE_CLOSE";
                break;
            }
            case 3: {
                v2_1 = "CLOSE_REASON_LOCAL_CLOSE";
                break;
            }
            default: {
                v2_1 = Integer.toString(v2);
                break;
            }
        }

        int v3 = this.zzcj;
        StringBuilder v5 = new StringBuilder(String.valueOf(v0).length() + 81 + String.valueOf(v1_1).length() + String.valueOf(v2_1).length());
        v5.append("ChannelEventParcelable[, channel=");
        v5.append(v0);
        v5.append(", type=");
        v5.append(v1_1);
        v5.append(", closeReason=");
        v5.append(v2_1);
        v5.append(", appErrorCode=");
        v5.append(v3);
        v5.append("]");
        return v5.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzck, arg6, false);
        SafeParcelWriter.writeInt(arg5, 3, this.type);
        SafeParcelWriter.writeInt(arg5, 4, this.zzg);
        SafeParcelWriter.writeInt(arg5, 5, this.zzcj);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final void zza(ChannelListener arg4) {
        switch(this.type) {
            case 1: {
                goto label_28;
            }
            case 2: {
                goto label_23;
            }
            case 3: {
                goto label_18;
            }
            case 4: {
                goto label_13;
            }
        }

        int v0 = this.type;
        StringBuilder v2 = new StringBuilder(25);
        v2.append("Unknown type: ");
        v2.append(v0);
        Log.w("ChannelEventParcelable", v2.toString());
        return;
    label_18:
        arg4.onInputClosed(this.zzck, this.zzg, this.zzcj);
        return;
    label_23:
        arg4.onChannelClosed(this.zzck, this.zzg, this.zzcj);
        return;
    label_28:
        arg4.onChannelOpened(this.zzck);
        return;
    label_13:
        arg4.onOutputClosed(this.zzck, this.zzg, this.zzcj);
    }
}

