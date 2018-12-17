package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
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

@Class(creator="OfferWalletObjectCreator") public final class OfferWalletObject extends AbstractSafeParcelable {
    public final class Builder {
        private zza zzbr;

        Builder(OfferWalletObject arg1, zzaa arg2) {
            this(arg1);
        }

        private Builder(OfferWalletObject arg1) {
            this.zzds = arg1;
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

        public final OfferWalletObject build() {
            this.zzds.zzbk = this.zzbr.zzf();
            return this.zzds;
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

        public final Builder setClassId(String arg2) {
            this.zzbr.zzb(arg2);
            return this;
        }

        public final Builder setId(String arg2) {
            this.zzbr.zza(arg2);
            this.zzds.zzcf = arg2;
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

        public final Builder setRedemptionCode(String arg2) {
            this.zzds.zzdr = arg2;
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
    @VersionField(getter="getVersionCode", id=1) private final int versionCode;
    @Field(id=4) CommonWalletObject zzbk;
    @Field(id=2) String zzcf;
    @Field(id=3) String zzdr;

    static {
        OfferWalletObject.CREATOR = new zzab();
    }

    @Constructor OfferWalletObject(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) String arg3, @Param(id=4) CommonWalletObject arg4) {
        super();
        this.versionCode = arg1;
        this.zzdr = arg3;
        if(arg1 < 3) {
            this.zzbk = CommonWalletObject.zze().zza(arg2).zzf();
            return;
        }

        this.zzbk = arg4;
    }

    OfferWalletObject() {
        super();
        this.versionCode = 3;
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

    public final String getClassId() {
        return this.zzbk.getClassId();
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

    public final String getRedemptionCode() {
        return this.zzdr;
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

    public final int getVersionCode() {
        return this.versionCode;
    }

    public static Builder newBuilder() {
        return new Builder(new OfferWalletObject(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.getVersionCode());
        SafeParcelWriter.writeString(arg5, 2, this.zzcf, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzdr, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzbk, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

