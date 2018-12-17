package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="LabelValueRowCreator") @Reserved(value={1}) public final class LabelValueRow extends AbstractSafeParcelable {
    public final class Builder {
        Builder(LabelValueRow arg1, zzd arg2) {
            this(arg1);
        }

        private Builder(LabelValueRow arg1) {
            this.zzgs = arg1;
            super();
        }

        public final Builder addColumn(LabelValue arg2) {
            this.zzgs.zzgr.add(arg2);
            return this;
        }

        public final Builder addColumns(Collection arg2) {
            this.zzgs.zzgr.addAll(arg2);
            return this;
        }

        public final LabelValueRow build() {
            return this.zzgs;
        }

        @Deprecated public final Builder setHexBackgroundColor(String arg2) {
            this.zzgs.zzgq = arg2;
            return this;
        }

        @Deprecated public final Builder setHexFontColor(String arg2) {
            this.zzgs.zzgp = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) @Deprecated String zzgp;
    @Field(id=3) @Deprecated String zzgq;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=4) ArrayList zzgr;

    static {
        LabelValueRow.CREATOR = new zze();
    }

    @Constructor LabelValueRow(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) ArrayList arg3) {
        super();
        this.zzgp = arg1;
        this.zzgq = arg2;
        this.zzgr = arg3;
    }

    LabelValueRow() {
        super();
        this.zzgr = ArrayUtils.newArrayList();
    }

    public final ArrayList getColumns() {
        return this.zzgr;
    }

    @Deprecated public final String getHexBackgroundColor() {
        return this.zzgq;
    }

    @Deprecated public final String getHexFontColor() {
        return this.zzgp;
    }

    public static Builder newBuilder() {
        return new Builder(new LabelValueRow(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzgp, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzgq, false);
        SafeParcelWriter.writeTypedList(arg4, 4, this.zzgr, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

