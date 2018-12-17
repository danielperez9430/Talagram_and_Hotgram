package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator="InstrumentInfoCreator") @Reserved(value={1}) public final class InstrumentInfo extends AbstractSafeParcelable {
    @Retention(value=RetentionPolicy.SOURCE) @public interface CardClass {
    }

    public static final int CARD_CLASS_CREDIT = 1;
    public static final int CARD_CLASS_DEBIT = 2;
    public static final int CARD_CLASS_PREPAID = 3;
    public static final int CARD_CLASS_UNKNOWN;
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getCardClass", id=4) private int zzah;
    @Field(getter="getInstrumentType", id=2) private String zzbt;
    @Field(getter="getInstrumentDetails", id=3) private String zzbu;

    static {
        InstrumentInfo.CREATOR = new zzp();
    }

    @Constructor public InstrumentInfo(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) int arg3) {
        super();
        this.zzbt = arg1;
        this.zzbu = arg2;
        this.zzah = arg3;
    }

    private InstrumentInfo() {
        super();
    }

    public final int getCardClass() {
        switch(this.zzah) {
            case 1: 
            case 2: 
            case 3: {
                goto label_4;
            }
        }

        return 0;
    label_4:
        return this.zzah;
    }

    public final String getInstrumentDetails() {
        return this.zzbu;
    }

    public final String getInstrumentType() {
        return this.zzbt;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.getInstrumentType(), false);
        SafeParcelWriter.writeString(arg4, 3, this.getInstrumentDetails(), false);
        SafeParcelWriter.writeInt(arg4, 4, this.getCardClass());
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

