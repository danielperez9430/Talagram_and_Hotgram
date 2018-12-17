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
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="LoyaltyWalletObjectCreator") @Reserved(value={1}) public final class LoyaltyWalletObject extends AbstractSafeParcelable {
    public final class Builder {
        Builder(LoyaltyWalletObject arg1, zzu arg2) {
            this(arg1);
        }

        private Builder(LoyaltyWalletObject arg1) {
            this.zzda = arg1;
            super();
        }

        public final Builder addImageModuleDataMainImageUri(UriData arg2) {
            this.zzda.zzcw.add(arg2);
            return this;
        }

        public final Builder addImageModuleDataMainImageUris(Collection arg2) {
            this.zzda.zzcw.addAll(arg2);
            return this;
        }

        public final Builder addInfoModuleDataLabeValueRow(LabelValueRow arg2) {
            this.zzda.zzcu.add(arg2);
            return this;
        }

        public final Builder addInfoModuleDataLabelValueRows(Collection arg2) {
            this.zzda.zzcu.addAll(arg2);
            return this;
        }

        public final Builder addLinksModuleDataUri(UriData arg2) {
            this.zzda.zzcy.add(arg2);
            return this;
        }

        public final Builder addLinksModuleDataUris(Collection arg2) {
            this.zzda.zzcy.addAll(arg2);
            return this;
        }

        public final Builder addLocation(LatLng arg2) {
            this.zzda.zzcr.add(arg2);
            return this;
        }

        public final Builder addLocations(Collection arg2) {
            this.zzda.zzcr.addAll(arg2);
            return this;
        }

        public final Builder addMessage(WalletObjectMessage arg2) {
            this.zzda.zzcp.add(arg2);
            return this;
        }

        public final Builder addMessages(Collection arg2) {
            this.zzda.zzcp.addAll(arg2);
            return this;
        }

        public final Builder addTextModuleData(TextModuleData arg2) {
            this.zzda.zzcx.add(arg2);
            return this;
        }

        public final Builder addTextModulesData(Collection arg2) {
            this.zzda.zzcx.addAll(arg2);
            return this;
        }

        public final LoyaltyWalletObject build() {
            return this.zzda;
        }

        public final Builder setAccountId(String arg2) {
            this.zzda.zzcg = arg2;
            return this;
        }

        public final Builder setAccountName(String arg2) {
            this.zzda.zzcj = arg2;
            return this;
        }

        public final Builder setBarcodeAlternateText(String arg2) {
            this.zzda.zzck = arg2;
            return this;
        }

        @Deprecated public final Builder setBarcodeLabel(String arg2) {
            this.zzda.zzcn = arg2;
            return this;
        }

        public final Builder setBarcodeType(String arg2) {
            this.zzda.zzcl = arg2;
            return this;
        }

        public final Builder setBarcodeValue(String arg2) {
            this.zzda.zzcm = arg2;
            return this;
        }

        public final Builder setClassId(String arg2) {
            this.zzda.zzco = arg2;
            return this;
        }

        public final Builder setId(String arg2) {
            this.zzda.zzcf = arg2;
            return this;
        }

        @Deprecated public final Builder setInfoModuleDataHexBackgroundColor(String arg2) {
            this.zzda.zzct = arg2;
            return this;
        }

        @Deprecated public final Builder setInfoModuleDataHexFontColor(String arg2) {
            this.zzda.zzcs = arg2;
            return this;
        }

        public final Builder setInfoModuleDataShowLastUpdateTime(boolean arg2) {
            this.zzda.zzcv = arg2;
            return this;
        }

        public final Builder setIssuerName(String arg2) {
            this.zzda.zzch = arg2;
            return this;
        }

        public final Builder setLoyaltyPoints(LoyaltyPoints arg2) {
            this.zzda.zzcz = arg2;
            return this;
        }

        public final Builder setProgramName(String arg2) {
            this.zzda.zzci = arg2;
            return this;
        }

        public final Builder setState(int arg2) {
            this.zzda.state = arg2;
            return this;
        }

        public final Builder setValidTimeInterval(TimeInterval arg2) {
            this.zzda.zzcq = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=12) int state;
    @Field(id=2) String zzcf;
    @Field(id=3) String zzcg;
    @Field(id=4) String zzch;
    @Field(id=5) String zzci;
    @Field(id=6) String zzcj;
    @Field(id=7) String zzck;
    @Field(id=8) String zzcl;
    @Field(id=9) String zzcm;
    @Field(id=10) @Deprecated String zzcn;
    @Field(id=11) String zzco;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=13) ArrayList zzcp;
    @Field(id=14) TimeInterval zzcq;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=15) ArrayList zzcr;
    @Field(id=16) @Deprecated String zzcs;
    @Field(id=17) @Deprecated String zzct;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=18) ArrayList zzcu;
    @Field(id=19) boolean zzcv;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=20) ArrayList zzcw;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=21) ArrayList zzcx;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=22) ArrayList zzcy;
    @Field(id=23) LoyaltyPoints zzcz;

    static {
        LoyaltyWalletObject.CREATOR = new zzv();
    }

    @Constructor LoyaltyWalletObject(@Param(id=2) String arg3, @Param(id=3) String arg4, @Param(id=4) String arg5, @Param(id=5) String arg6, @Param(id=6) String arg7, @Param(id=7) String arg8, @Param(id=8) String arg9, @Param(id=9) String arg10, @Param(id=10) String arg11, @Param(id=11) String arg12, @Param(id=12) int arg13, @Param(id=13) ArrayList arg14, @Param(id=14) TimeInterval arg15, @Param(id=15) ArrayList arg16, @Param(id=16) String arg17, @Param(id=17) String arg18, @Param(id=18) ArrayList arg19, @Param(id=19) boolean arg20, @Param(id=20) ArrayList arg21, @Param(id=21) ArrayList arg22, @Param(id=22) ArrayList arg23, @Param(id=23) LoyaltyPoints arg24) {
        super();
        this.zzcf = arg3;
        this.zzcg = arg4;
        this.zzch = arg5;
        this.zzci = arg6;
        this.zzcj = arg7;
        this.zzck = arg8;
        this.zzcl = arg9;
        this.zzcm = arg10;
        this.zzcn = arg11;
        this.zzco = arg12;
        this.state = arg13;
        this.zzcp = arg14;
        this.zzcq = arg15;
        this.zzcr = arg16;
        this.zzcs = arg17;
        this.zzct = arg18;
        this.zzcu = arg19;
        this.zzcv = arg20;
        this.zzcw = arg21;
        this.zzcx = arg22;
        this.zzcy = arg23;
        this.zzcz = arg24;
    }

    LoyaltyWalletObject() {
        super();
        this.zzcp = ArrayUtils.newArrayList();
        this.zzcr = ArrayUtils.newArrayList();
        this.zzcu = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
        this.zzcy = ArrayUtils.newArrayList();
    }

    public final String getAccountId() {
        return this.zzcg;
    }

    public final String getAccountName() {
        return this.zzcj;
    }

    public final String getBarcodeAlternateText() {
        return this.zzck;
    }

    @Deprecated public final String getBarcodeLabel() {
        return this.zzcn;
    }

    public final String getBarcodeType() {
        return this.zzcl;
    }

    public final String getBarcodeValue() {
        return this.zzcm;
    }

    public final String getClassId() {
        return this.zzco;
    }

    public final String getId() {
        return this.zzcf;
    }

    public final ArrayList getImageModuleDataMainImageUris() {
        return this.zzcw;
    }

    @Deprecated public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzct;
    }

    @Deprecated public final String getInfoModuleDataHexFontColor() {
        return this.zzcs;
    }

    public final ArrayList getInfoModuleDataLabelValueRows() {
        return this.zzcu;
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzcv;
    }

    public final String getIssuerName() {
        return this.zzch;
    }

    public final ArrayList getLinksModuleDataUris() {
        return this.zzcy;
    }

    public final ArrayList getLocations() {
        return this.zzcr;
    }

    public final LoyaltyPoints getLoyaltyPoints() {
        return this.zzcz;
    }

    public final ArrayList getMessages() {
        return this.zzcp;
    }

    public final String getProgramName() {
        return this.zzci;
    }

    public final int getState() {
        return this.state;
    }

    public final ArrayList getTextModulesData() {
        return this.zzcx;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzcq;
    }

    public static Builder newBuilder() {
        return new Builder(new LoyaltyWalletObject(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzcf, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzcg, false);
        SafeParcelWriter.writeString(arg5, 4, this.zzch, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzci, false);
        SafeParcelWriter.writeString(arg5, 6, this.zzcj, false);
        SafeParcelWriter.writeString(arg5, 7, this.zzck, false);
        SafeParcelWriter.writeString(arg5, 8, this.zzcl, false);
        SafeParcelWriter.writeString(arg5, 9, this.zzcm, false);
        SafeParcelWriter.writeString(arg5, 10, this.zzcn, false);
        SafeParcelWriter.writeString(arg5, 11, this.zzco, false);
        SafeParcelWriter.writeInt(arg5, 12, this.state);
        SafeParcelWriter.writeTypedList(arg5, 13, this.zzcp, false);
        SafeParcelWriter.writeParcelable(arg5, 14, this.zzcq, arg6, false);
        SafeParcelWriter.writeTypedList(arg5, 15, this.zzcr, false);
        SafeParcelWriter.writeString(arg5, 16, this.zzcs, false);
        SafeParcelWriter.writeString(arg5, 17, this.zzct, false);
        SafeParcelWriter.writeTypedList(arg5, 18, this.zzcu, false);
        SafeParcelWriter.writeBoolean(arg5, 19, this.zzcv);
        SafeParcelWriter.writeTypedList(arg5, 20, this.zzcw, false);
        SafeParcelWriter.writeTypedList(arg5, 21, this.zzcx, false);
        SafeParcelWriter.writeTypedList(arg5, 22, this.zzcy, false);
        SafeParcelWriter.writeParcelable(arg5, 23, this.zzcz, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

