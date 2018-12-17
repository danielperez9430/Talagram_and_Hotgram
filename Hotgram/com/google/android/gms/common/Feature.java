package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;

@Class(creator="FeatureCreator") public class Feature extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getName", id=1) private final String name;
    @Field(getter="getOldVersion", id=2) @Deprecated private final int zzaq;
    @Field(defaultValue="-1", getter="getVersion", id=3) private final long zzar;

    static {
        Feature.CREATOR = new FeatureCreator();
    }

    @Constructor public Feature(@Param(id=1) String arg1, @Param(id=2) int arg2, @Param(id=3) long arg3) {
        super();
        this.name = arg1;
        this.zzaq = arg2;
        this.zzar = arg3;
    }

    public Feature(String arg1, long arg2) {
        super();
        this.name = arg1;
        this.zzar = arg2;
        this.zzaq = -1;
    }

    public boolean equals(Object arg7) {
        if((arg7 instanceof Feature)) {
            if(this.getName() == null || !this.getName().equals(((Feature)arg7).getName())) {
                if(this.getName() != null) {
                }
                else if(((Feature)arg7).getName() == null) {
                    goto label_13;
                }

                return 0;
            }

        label_13:
            if(this.getVersion() != ((Feature)arg7).getVersion()) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public String getName() {
        return this.name;
    }

    public long getVersion() {
        if(this.zzar == -1) {
            return ((long)this.zzaq);
        }

        return this.zzar;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.getName(), Long.valueOf(this.getVersion())});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.getName()).add("version", Long.valueOf(this.getVersion())).toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.getName(), false);
        SafeParcelWriter.writeInt(arg4, 2, this.zzaq);
        SafeParcelWriter.writeLong(arg4, 3, this.getVersion());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

