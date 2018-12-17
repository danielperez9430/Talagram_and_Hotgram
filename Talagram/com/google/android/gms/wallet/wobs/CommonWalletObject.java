package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;

@KeepName @Class(creator="CommonWalletObjectCreator") @Reserved(value={1}) public class CommonWalletObject extends AbstractSafeParcelable {
    public final class zza {
        zza(CommonWalletObject arg1, com.google.android.gms.wallet.wobs.zza arg2) {
            this(arg1);
        }

        private zza(CommonWalletObject arg1) {
            this.zzgo = arg1;
            super();
        }

        public final zza zza(UriData arg2) {
            this.zzgo.zzcw.add(arg2);
            return this;
        }

        public final zza zza(LabelValueRow arg2) {
            this.zzgo.zzcu.add(arg2);
            return this;
        }

        public final zza zza(LatLng arg2) {
            this.zzgo.zzcr.add(arg2);
            return this;
        }

        public final zza zza(WalletObjectMessage arg2) {
            this.zzgo.zzcp.add(arg2);
            return this;
        }

        public final zza zza(Collection arg2) {
            this.zzgo.zzcp.addAll(arg2);
            return this;
        }

        public final zza zza(TextModuleData arg2) {
            this.zzgo.zzcx.add(arg2);
            return this;
        }

        public final zza zza(String arg2) {
            this.zzgo.zzcf = arg2;
            return this;
        }

        public final zza zza(boolean arg2) {
            this.zzgo.zzcv = arg2;
            return this;
        }

        public final zza zza(TimeInterval arg2) {
            this.zzgo.zzcq = arg2;
            return this;
        }

        public final zza zzb(UriData arg2) {
            this.zzgo.zzcy.add(arg2);
            return this;
        }

        public final zza zzb(Collection arg2) {
            this.zzgo.zzcr.addAll(arg2);
            return this;
        }

        public final zza zzb(String arg2) {
            this.zzgo.zzco = arg2;
            return this;
        }

        public final zza zzc(Collection arg2) {
            this.zzgo.zzcu.addAll(arg2);
            return this;
        }

        public final zza zzc(int arg2) {
            this.zzgo.state = arg2;
            return this;
        }

        public final zza zzc(String arg2) {
            this.zzgo.name = arg2;
            return this;
        }

        public final zza zzd(Collection arg2) {
            this.zzgo.zzcw.addAll(arg2);
            return this;
        }

        public final zza zzd(String arg2) {
            this.zzgo.zzch = arg2;
            return this;
        }

        public final zza zze(Collection arg2) {
            this.zzgo.zzcx.addAll(arg2);
            return this;
        }

        public final zza zze(String arg2) {
            this.zzgo.zzck = arg2;
            return this;
        }

        public final CommonWalletObject zzf() {
            return this.zzgo;
        }

        public final zza zzf(Collection arg2) {
            this.zzgo.zzcy.addAll(arg2);
            return this;
        }

        public final zza zzf(String arg2) {
            this.zzgo.zzcl = arg2;
            return this;
        }

        public final zza zzg(String arg2) {
            this.zzgo.zzcm = arg2;
            return this;
        }

        @Deprecated public final zza zzh(String arg2) {
            this.zzgo.zzcn = arg2;
            return this;
        }

        @Deprecated public final zza zzi(String arg2) {
            this.zzgo.zzcs = arg2;
            return this;
        }

        @Deprecated public final zza zzj(String arg2) {
            this.zzgo.zzct = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=4) String name;
    @Field(id=10) int state;
    @Field(id=2) String zzcf;
    @Field(id=5) String zzch;
    @Field(id=6) String zzck;
    @Field(id=7) String zzcl;
    @Field(id=8) String zzcm;
    @Field(id=9) @Deprecated String zzcn;
    @Field(id=3) String zzco;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=11) ArrayList zzcp;
    @Field(id=12) TimeInterval zzcq;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=13) ArrayList zzcr;
    @Field(id=14) @Deprecated String zzcs;
    @Field(id=15) @Deprecated String zzct;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=16) ArrayList zzcu;
    @Field(id=17) boolean zzcv;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=18) ArrayList zzcw;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=19) ArrayList zzcx;
    @Field(defaultValueUnchecked="com.google.android.gms.common.util.ArrayUtils.newArrayList()", id=20) ArrayList zzcy;

    static {
        CommonWalletObject.CREATOR = new zzb();
    }

    @Constructor CommonWalletObject(@Param(id=2) String arg3, @Param(id=3) String arg4, @Param(id=4) String arg5, @Param(id=5) String arg6, @Param(id=6) String arg7, @Param(id=7) String arg8, @Param(id=8) String arg9, @Param(id=9) String arg10, @Param(id=10) int arg11, @Param(id=11) ArrayList arg12, @Param(id=12) TimeInterval arg13, @Param(id=13) ArrayList arg14, @Param(id=14) String arg15, @Param(id=15) String arg16, @Param(id=16) ArrayList arg17, @Param(id=17) boolean arg18, @Param(id=18) ArrayList arg19, @Param(id=19) ArrayList arg20, @Param(id=20) ArrayList arg21) {
        super();
        this.zzcf = arg3;
        this.zzco = arg4;
        this.name = arg5;
        this.zzch = arg6;
        this.zzck = arg7;
        this.zzcl = arg8;
        this.zzcm = arg9;
        this.zzcn = arg10;
        this.state = arg11;
        this.zzcp = arg12;
        this.zzcq = arg13;
        this.zzcr = arg14;
        this.zzcs = arg15;
        this.zzct = arg16;
        this.zzcu = arg17;
        this.zzcv = arg18;
        this.zzcw = arg19;
        this.zzcx = arg20;
        this.zzcy = arg21;
    }

    CommonWalletObject() {
        super();
        this.zzcp = ArrayUtils.newArrayList();
        this.zzcr = ArrayUtils.newArrayList();
        this.zzcu = ArrayUtils.newArrayList();
        this.zzcw = ArrayUtils.newArrayList();
        this.zzcx = ArrayUtils.newArrayList();
        this.zzcy = ArrayUtils.newArrayList();
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

    public final ArrayList getMessages() {
        return this.zzcp;
    }

    public final String getName() {
        return this.name;
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

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzcf, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzco, false);
        SafeParcelWriter.writeString(arg5, 4, this.name, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzch, false);
        SafeParcelWriter.writeString(arg5, 6, this.zzck, false);
        SafeParcelWriter.writeString(arg5, 7, this.zzcl, false);
        SafeParcelWriter.writeString(arg5, 8, this.zzcm, false);
        SafeParcelWriter.writeString(arg5, 9, this.zzcn, false);
        SafeParcelWriter.writeInt(arg5, 10, this.state);
        SafeParcelWriter.writeTypedList(arg5, 11, this.zzcp, false);
        SafeParcelWriter.writeParcelable(arg5, 12, this.zzcq, arg6, false);
        SafeParcelWriter.writeTypedList(arg5, 13, this.zzcr, false);
        SafeParcelWriter.writeString(arg5, 14, this.zzcs, false);
        SafeParcelWriter.writeString(arg5, 15, this.zzct, false);
        SafeParcelWriter.writeTypedList(arg5, 16, this.zzcu, false);
        SafeParcelWriter.writeBoolean(arg5, 17, this.zzcv);
        SafeParcelWriter.writeTypedList(arg5, 18, this.zzcw, false);
        SafeParcelWriter.writeTypedList(arg5, 19, this.zzcx, false);
        SafeParcelWriter.writeTypedList(arg5, 20, this.zzcy, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public static zza zze() {
        return new zza(new CommonWalletObject(), null);
    }
}

