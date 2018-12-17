package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator="CardInfoCreator") public final class CardInfo extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private String zzae;
    @Field(id=2) private String zzaf;
    @Field(id=3) private String zzag;
    @Field(id=4) private int zzah;
    @Field(id=5) private UserAddress zzai;

    static {
        CardInfo.CREATOR = new zzc();
    }

    @Constructor CardInfo(@Param(id=1) String arg1, @Param(id=2) String arg2, @Param(id=3) String arg3, @Param(id=4) int arg4, @Param(id=5) UserAddress arg5) {
        super();
        this.zzae = arg1;
        this.zzaf = arg2;
        this.zzag = arg3;
        this.zzah = arg4;
        this.zzai = arg5;
    }

    private CardInfo() {
        super();
    }

    public final UserAddress getBillingAddress() {
        return this.zzai;
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

    public final String getCardDescription() {
        return this.zzae;
    }

    public final String getCardDetails() {
        return this.zzag;
    }

    public final String getCardNetwork() {
        return this.zzaf;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 1, this.zzae, false);
        SafeParcelWriter.writeString(arg5, 2, this.zzaf, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzag, false);
        SafeParcelWriter.writeInt(arg5, 4, this.zzah);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzai, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

