package com.google.android.gms.wearable.internal;

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
import com.google.android.gms.wearable.CapabilityInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;

@Class(creator="CapabilityInfoParcelableCreator") @Reserved(value={1}) public final class zzah extends AbstractSafeParcelable implements CapabilityInfo {
    public static final Parcelable$Creator CREATOR;
    private final Object lock;
    @Field(getter="getName", id=2) private final String name;
    @GuardedBy(value="lock") private Set zzbt;
    @Field(getter="getNodeParcelables", id=3) private final List zzca;

    static {
        zzah.CREATOR = new zzai();
    }

    @Constructor public zzah(@Param(id=2) String arg2, @Param(id=3) List arg3) {
        super();
        this.lock = new Object();
        this.name = arg2;
        this.zzca = arg3;
        this.zzbt = null;
        Preconditions.checkNotNull(this.name);
        Preconditions.checkNotNull(this.zzca);
    }

    public final boolean equals(Object arg5) {
        if(this == (((zzah)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.name != null) {
                    if(!this.name.equals(((zzah)arg5).name)) {
                        return 0;
                    }
                }
                else if(((zzah)arg5).name != null) {
                    return 0;
                }

                if(this.zzca != null) {
                    if(!this.zzca.equals(((zzah)arg5).zzca)) {
                        return 0;
                    }
                }
                else if(((zzah)arg5).zzca != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public final String getName() {
        return this.name;
    }

    public final Set getNodes() {
        Object v0 = this.lock;
        __monitor_enter(v0);
        try {
            if(this.zzbt == null) {
                this.zzbt = new HashSet(this.zzca);
            }

            __monitor_exit(v0);
            return this.zzbt;
        label_12:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_12;
        }

        throw v1;
    }

    public final int hashCode() {
        int v1 = 0;
        int v0 = this.name != null ? this.name.hashCode() : 0;
        v0 = (v0 + 31) * 31;
        if(this.zzca != null) {
            v1 = this.zzca.hashCode();
        }

        return v0 + v1;
    }

    public final String toString() {
        String v0 = this.name;
        String v1 = String.valueOf(this.zzca);
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 18 + String.valueOf(v1).length());
        v3.append("CapabilityInfo{");
        v3.append(v0);
        v3.append(", ");
        v3.append(v1);
        v3.append("}");
        return v3.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.getName(), false);
        SafeParcelWriter.writeTypedList(arg4, 3, this.zzca, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

