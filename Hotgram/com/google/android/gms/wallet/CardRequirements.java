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
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="CardRequirementsCreator") public final class CardRequirements extends AbstractSafeParcelable {
    public final class Builder {
        Builder(CardRequirements arg1, zzd arg2) {
            this(arg1);
        }

        private Builder(CardRequirements arg1) {
            this.zzan = arg1;
            super();
        }

        public final Builder addAllowedCardNetwork(int arg3) {
            if(this.zzan.zzaj == null) {
                this.zzan.zzaj = new ArrayList();
            }

            this.zzan.zzaj.add(Integer.valueOf(arg3));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection arg3) {
            boolean v0 = arg3 == null || (arg3.isEmpty()) ? false : true;
            Preconditions.checkArgument(v0, "allowedCardNetworks can\'t be null or empty! You must provide a valid value from WalletConstants.CardNetwork.");
            if(this.zzan.zzaj == null) {
                this.zzan.zzaj = new ArrayList();
            }

            this.zzan.zzaj.addAll(arg3);
            return this;
        }

        public final CardRequirements build() {
            Preconditions.checkNotNull(this.zzan.zzaj, "Allowed card networks must be non-empty! You can set it through addAllowedCardNetwork() or addAllowedCardNetworks() in the CardRequirements Builder.");
            return this.zzan;
        }

        public final Builder setAllowPrepaidCards(boolean arg2) {
            this.zzan.zzak = arg2;
            return this;
        }

        public final Builder setBillingAddressFormat(int arg2) {
            this.zzan.zzam = arg2;
            return this;
        }

        public final Builder setBillingAddressRequired(boolean arg2) {
            this.zzan.zzal = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=1) ArrayList zzaj;
    @Field(defaultValue="true", id=2) boolean zzak;
    @Field(id=3) boolean zzal;
    @Field(id=4) int zzam;

    static {
        CardRequirements.CREATOR = new zze();
    }

    @Constructor CardRequirements(@Param(id=1) ArrayList arg1, @Param(id=2) boolean arg2, @Param(id=3) boolean arg3, @Param(id=4) int arg4) {
        super();
        this.zzaj = arg1;
        this.zzak = arg2;
        this.zzal = arg3;
        this.zzam = arg4;
    }

    private CardRequirements() {
        super();
        this.zzak = true;
    }

    public final boolean allowPrepaidCards() {
        return this.zzak;
    }

    public final ArrayList getAllowedCardNetworks() {
        return this.zzaj;
    }

    public final int getBillingAddressFormat() {
        return this.zzam;
    }

    public final boolean isBillingAddressRequired() {
        return this.zzal;
    }

    public static Builder newBuilder() {
        return new Builder(new CardRequirements(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeIntegerList(arg4, 1, this.zzaj, false);
        SafeParcelWriter.writeBoolean(arg4, 2, this.zzak);
        SafeParcelWriter.writeBoolean(arg4, 3, this.zzal);
        SafeParcelWriter.writeInt(arg4, 4, this.zzam);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

