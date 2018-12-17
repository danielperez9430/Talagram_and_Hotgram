package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject$zza;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="GiftCardWalletObjectCreator") @Reserved(value={1}) public final class GiftCardWalletObject extends AbstractSafeParcelable {
    public final class Builder {
        private zza zzbr;

        Builder(GiftCardWalletObject arg1, zzn arg2) {
            this(arg1);
        }

        private Builder(GiftCardWalletObject arg1) {
            this.zzbs = arg1;
            super();
            this.zzbr = CommonWalletObject.zze();
        }

        public final Builder addImageModuleDataMainImageUri(UriData arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection arg2) {
            this.zzbr.zzd(arg2);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRow(LabelValueRow arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection arg2) {
            this.zzbr.zzc(arg2);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData arg2) {
            this.zzbr.zzb(arg2);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection arg2) {
            this.zzbr.zzf(arg2);
            return this;
        }

        public final Builder addLocation(LatLng arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addLocations(Collection arg2) {
            this.zzbr.zzb(arg2);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addMessages(Collection arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder addTextModulesData(Collection arg2) {
            this.zzbr.zze(arg2);
            return this;
        }

        public final GiftCardWalletObject build() {
            Preconditions.checkArgument(TextUtils.isEmpty(this.zzbs.zzbl) ^ 1, "Card number is required.");
            this.zzbs.zzbk = this.zzbr.zzf();
            Preconditions.checkArgument(TextUtils.isEmpty(this.zzbs.zzbk.getName()) ^ 1, "Card name is required.");
            Preconditions.checkArgument(TextUtils.isEmpty(this.zzbs.zzbk.getIssuerName()) ^ 1, "Card issuer name is required.");
            return this.zzbs;
        }

        public final Builder setBalanceCurrencyCode(String arg2) {
            this.zzbs.zzbo = arg2;
            return this;
        }

        public final Builder setBalanceMicros(long arg2) {
            this.zzbs.zzbn = arg2;
            return this;
        }

        public final Builder setBalanceUpdateTime(long arg2) {
            this.zzbs.zzbp = arg2;
            return this;
        }

        public final Builder setBarcodeAlternateText(String arg2) {
            this.zzbr.zze(arg2);
            return this;
        }

        @Deprecated public final Builder setBarcodeLabel(String arg2) {
            this.zzbr.zzh(arg2);
            return this;
        }

        public final Builder setBarcodeType(String arg2) {
            this.zzbr.zzf(arg2);
            return this;
        }

        public final Builder setBarcodeValue(String arg2) {
            this.zzbr.zzg(arg2);
            return this;
        }

        @Deprecated public final Builder setCardIdentifier(String arg2) {
            this.zzbs.zzbm = arg2;
            return this;
        }

        public final Builder setCardNumber(String arg2) {
            this.zzbs.zzbl = arg2;
            return this;
        }

        public final Builder setClassId(String arg2) {
            this.zzbr.zzb(arg2);
            return this;
        }

        public final Builder setEventNumber(String arg2) {
            this.zzbs.zzbq = arg2;
            return this;
        }

        public final Builder setId(String arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        @Deprecated public final Builder setInfoModuleDataHexBackgroundColor(String arg2) {
            this.zzbr.zzj(arg2);
            return this;
        }

        @Deprecated public final Builder setInfoModuleDataHexFontColor(String arg2) {
            this.zzbr.zzi(arg2);
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean arg2) {
            this.zzbr.zza(arg2);
            return this;
        }

        public final Builder setIssuerName(String arg2) {
            this.zzbr.zzd(arg2);
            return this;
        }

        public final Builder setPin(String arg2) {
            this.zzbs.pin = arg2;
            return this;
        }

        public final Builder setState(int arg2) {
            this.zzbr.zzc(arg2);
            return this;
        }

        public final Builder setTitle(String arg2) {
            this.zzbr.zzc(arg2);
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval arg2) {
            this.zzbr.zza(arg2);
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=4) String pin;
    @Field(id=2) CommonWalletObject zzbk;
    @Field(id=3) String zzbl;
    @Field(id=5) @Deprecated String zzbm;
    @Field(id=6) long zzbn;
    @Field(id=7) String zzbo;
    @Field(id=8) long zzbp;
    @Field(id=9) String zzbq;

    static {
        GiftCardWalletObject.CREATOR = new zzo();
    }

    @Constructor GiftCardWalletObject(@Param(id=2) CommonWalletObject arg2, @Param(id=3) String arg3, @Param(id=4) String arg4, @Param(id=5) String arg5, @Param(id=6) long arg6, @Param(id=7) String arg8, @Param(id=8) long arg9, @Param(id=9) String arg11) {
        super();
        this.zzbk = CommonWalletObject.zze().zzf();
        this.zzbk = arg2;
        this.zzbl = arg3;
        this.pin = arg4;
        this.zzbn = arg6;
        this.zzbo = arg8;
        this.zzbp = arg9;
        this.zzbq = arg11;
        this.zzbm = arg5;
    }

    GiftCardWalletObject() {
        super();
        this.zzbk = CommonWalletObject.zze().zzf();
    }

    public final String getBalanceCurrencyCode() {
        return this.zzbo;
    }

    public final long getBalanceMicros() {
        return this.zzbn;
    }

    public final long getBalanceUpdateTime() {
        return this.zzbp;
    }

    public final String getBarcodeAlternateText() {
        return this.zzbk.getBarcodeAlternateText();
    }

    @Deprecated public final String getBarcodeLabel() {
        return this.zzbk.getBarcodeLabel();
    }

    public final String getBarcodeType() {
        return this.zzbk.getBarcodeType();
    }

    public final String getBarcodeValue() {
        return this.zzbk.getBarcodeValue();
    }

    @Deprecated public final String getCardIdentifier() {
        return this.zzbm;
    }

    public final String getCardNumber() {
        return this.zzbl;
    }

    public final String getClassId() {
        return this.zzbk.getClassId();
    }

    public final String getEventNumber() {
        return this.zzbq;
    }

    public final String getId() {
        return this.zzbk.getId();
    }

    public final ArrayList getImageModuleDataMainImageUris() {
        return this.zzbk.getImageModuleDataMainImageUris();
    }

    @Deprecated public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzbk.getInfoModuleDataHexBackgroundColor();
    }

    @Deprecated public final String getInfoModuleDataHexFontColor() {
        return this.zzbk.getInfoModuleDataHexFontColor();
    }

    public final ArrayList getInfoModuleDataLabelValueRows() {
        return this.zzbk.getInfoModuleDataLabelValueRows();
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzbk.getInfoModuleDataShowLastUpdateTime();
    }

    public final String getIssuerName() {
        return this.zzbk.getIssuerName();
    }

    public final ArrayList getLinksModuleDataUris() {
        return this.zzbk.getLinksModuleDataUris();
    }

    public final ArrayList getLocations() {
        return this.zzbk.getLocations();
    }

    public final ArrayList getMessages() {
        return this.zzbk.getMessages();
    }

    public final String getPin() {
        return this.pin;
    }

    public final int getState() {
        return this.zzbk.getState();
    }

    public final ArrayList getTextModulesData() {
        return this.zzbk.getTextModulesData();
    }

    public final String getTitle() {
        return this.zzbk.getName();
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzbk.getValidTimeInterval();
    }

    public static Builder newBuilder() {
        return new Builder(new GiftCardWalletObject(), null);
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeParcelable(arg6, 2, this.zzbk, arg7, false);
        SafeParcelWriter.writeString(arg6, 3, this.zzbl, false);
        SafeParcelWriter.writeString(arg6, 4, this.pin, false);
        SafeParcelWriter.writeString(arg6, 5, this.zzbm, false);
        SafeParcelWriter.writeLong(arg6, 6, this.zzbn);
        SafeParcelWriter.writeString(arg6, 7, this.zzbo, false);
        SafeParcelWriter.writeLong(arg6, 8, this.zzbp);
        SafeParcelWriter.writeString(arg6, 9, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }
}

