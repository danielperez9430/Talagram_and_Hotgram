package com.google.android.gms.wallet;

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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator="CreateWalletObjectsRequestCreator") @Reserved(value={1}) public final class CreateWalletObjectsRequest extends AbstractSafeParcelable {
    public final class Builder {
        Builder(CreateWalletObjectsRequest arg1, zzi arg2) {
            this(arg1);
        }

        private Builder(CreateWalletObjectsRequest arg1) {
            this.zzaw = arg1;
            super();
        }

        public final CreateWalletObjectsRequest build() {
            boolean v1 = false;
            int v0 = this.zzaw.zzau == null ? 0 : 1;
            int v3 = this.zzaw.zzas == null ? 0 : 1;
            v0 += v3;
            v3 = this.zzaw.zzat == null ? 0 : 1;
            if(v0 + v3 == 1) {
                v1 = true;
            }

            Preconditions.checkState(v1, "CreateWalletObjectsRequest must have exactly one Wallet Object");
            return this.zzaw;
        }

        public final Builder setCreateMode(int arg2) {
            this.zzaw.zzav = arg2;
            return this;
        }

        public final Builder setGiftCardWalletObject(GiftCardWalletObject arg2) {
            this.zzaw.zzau = arg2;
            return this;
        }

        public final Builder setLoyaltyWalletObject(LoyaltyWalletObject arg2) {
            this.zzaw.zzas = arg2;
            return this;
        }

        public final Builder setOfferWalletObject(OfferWalletObject arg2) {
            this.zzaw.zzat = arg2;
            return this;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface CreateMode {
    }

    public static final Parcelable$Creator CREATOR = null;
    public static final int REQUEST_IMMEDIATE_SAVE = 1;
    public static final int SHOW_SAVE_PROMPT;
    @Field(id=2) LoyaltyWalletObject zzas;
    @Field(id=3) OfferWalletObject zzat;
    @Field(id=4) GiftCardWalletObject zzau;
    @Field(id=5) int zzav;

    static {
        CreateWalletObjectsRequest.CREATOR = new zzj();
    }

    @Constructor CreateWalletObjectsRequest(@Param(id=2) LoyaltyWalletObject arg1, @Param(id=3) OfferWalletObject arg2, @Param(id=4) GiftCardWalletObject arg3, @Param(id=5) int arg4) {
        super();
        this.zzas = arg1;
        this.zzat = arg2;
        this.zzau = arg3;
        this.zzav = arg4;
    }

    CreateWalletObjectsRequest() {
        super();
    }

    @Deprecated public CreateWalletObjectsRequest(GiftCardWalletObject arg1) {
        super();
        this.zzau = arg1;
    }

    @Deprecated public CreateWalletObjectsRequest(LoyaltyWalletObject arg1) {
        super();
        this.zzas = arg1;
    }

    @Deprecated public CreateWalletObjectsRequest(OfferWalletObject arg1) {
        super();
        this.zzat = arg1;
    }

    public final int getCreateMode() {
        return this.zzav;
    }

    public final GiftCardWalletObject getGiftCardWalletObject() {
        return this.zzau;
    }

    public final LoyaltyWalletObject getLoyaltyWalletObject() {
        return this.zzas;
    }

    public final OfferWalletObject getOfferWalletObject() {
        return this.zzat;
    }

    public static Builder newBuilder() {
        return new Builder(new CreateWalletObjectsRequest(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzas, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzat, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzau, arg6, false);
        SafeParcelWriter.writeInt(arg5, 5, this.zzav);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

