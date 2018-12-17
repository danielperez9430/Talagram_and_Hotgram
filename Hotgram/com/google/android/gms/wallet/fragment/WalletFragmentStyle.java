package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wallet.R$style;
import com.google.android.gms.wallet.R$styleable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Class(creator="WalletFragmentStyleCreator") @Reserved(value={1}) public final class WalletFragmentStyle extends AbstractSafeParcelable {
    @Retention(value=RetentionPolicy.SOURCE) @public interface BuyButtonAppearance {
        public static final int ANDROID_PAY_DARK = 4;
        public static final int ANDROID_PAY_LIGHT = 5;
        public static final int ANDROID_PAY_LIGHT_WITH_BORDER = 6;
        @Deprecated public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated public static final int GOOGLE_WALLET_GRAYSCALE = 2;
        @Deprecated public static final int GOOGLE_WALLET_MONOCHROME = 3;

    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface BuyButtonText {
        public static final int BUY_WITH = 5;
        public static final int DONATE_WITH = 7;
        public static final int LOGO_ONLY = 6;

    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Dimension {
        public static final int MATCH_PARENT = -1;
        public static final int UNIT_DIP = 1;
        public static final int UNIT_IN = 4;
        public static final int UNIT_MM = 5;
        public static final int UNIT_PT = 3;
        public static final int UNIT_PX = 0;
        public static final int UNIT_SP = 2;
        public static final int WRAP_CONTENT = -2;

    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface LogoImageType {
        public static final int ANDROID_PAY = 3;
        @Deprecated public static final int GOOGLE_WALLET_CLASSIC = 1;
        @Deprecated public static final int GOOGLE_WALLET_MONOCHROME = 2;

    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private Bundle zzgd;
    @Field(id=3) private int zzge;

    static {
        WalletFragmentStyle.CREATOR = new zzg();
    }

    @Constructor WalletFragmentStyle(@Param(id=2) Bundle arg1, @Param(id=3) int arg2) {
        super();
        this.zzgd = arg1;
        this.zzge = arg2;
    }

    public WalletFragmentStyle() {
        super();
        this.zzgd = new Bundle();
        this.zzgd.putInt("buyButtonAppearanceDefault", 4);
        this.zzgd.putInt("maskedWalletDetailsLogoImageTypeDefault", 3);
    }

    public final WalletFragmentStyle setBuyButtonAppearance(int arg3) {
        this.zzgd.putInt("buyButtonAppearance", arg3);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int arg5) {
        this.zzgd.putLong("buyButtonHeight", WalletFragmentStyle.zza(arg5));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonHeight(int arg3, float arg4) {
        this.zzgd.putLong("buyButtonHeight", WalletFragmentStyle.zza(arg3, arg4));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonText(int arg3) {
        this.zzgd.putInt("buyButtonText", arg3);
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int arg5) {
        this.zzgd.putLong("buyButtonWidth", WalletFragmentStyle.zza(arg5));
        return this;
    }

    public final WalletFragmentStyle setBuyButtonWidth(int arg3, float arg4) {
        this.zzgd.putLong("buyButtonWidth", WalletFragmentStyle.zza(arg3, arg4));
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int arg3) {
        this.zzgd.remove("maskedWalletDetailsBackgroundResource");
        this.zzgd.putInt("maskedWalletDetailsBackgroundColor", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int arg3) {
        this.zzgd.remove("maskedWalletDetailsBackgroundColor");
        this.zzgd.putInt("maskedWalletDetailsBackgroundResource", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int arg3) {
        this.zzgd.remove("maskedWalletDetailsButtonBackgroundResource");
        this.zzgd.putInt("maskedWalletDetailsButtonBackgroundColor", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int arg3) {
        this.zzgd.remove("maskedWalletDetailsButtonBackgroundColor");
        this.zzgd.putInt("maskedWalletDetailsButtonBackgroundResource", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int arg3) {
        this.zzgd.putInt("maskedWalletDetailsButtonTextAppearance", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int arg3) {
        this.zzgd.putInt("maskedWalletDetailsHeaderTextAppearance", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int arg3) {
        this.zzgd.putInt("maskedWalletDetailsLogoImageType", arg3);
        return this;
    }

    @Deprecated public final WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int arg3) {
        this.zzgd.putInt("maskedWalletDetailsLogoTextColor", arg3);
        return this;
    }

    public final WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int arg3) {
        this.zzgd.putInt("maskedWalletDetailsTextAppearance", arg3);
        return this;
    }

    public final WalletFragmentStyle setStyleResourceId(int arg1) {
        this.zzge = arg1;
        return this;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBundle(arg4, 2, this.zzgd, false);
        SafeParcelWriter.writeInt(arg4, 3, this.zzge);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public final int zza(String arg5, DisplayMetrics arg6, int arg7) {
        if(!this.zzgd.containsKey(arg5)) {
            return arg7;
        }

        long v0 = this.zzgd.getLong(arg5);
        int v5 = ((int)(v0 >>> 32));
        arg7 = ((int)v0);
        switch(v5) {
            case 0: {
                goto label_34;
            }
            case 1: {
                goto label_32;
            }
            case 2: {
                goto label_30;
            }
            case 3: {
                goto label_28;
            }
            case 4: {
                goto label_26;
            }
            case 5: {
                goto label_24;
            }
        }

        switch(v5) {
            case 128: {
                goto label_22;
            }
            case 129: {
                return arg7;
            }
        }

        StringBuilder v0_1 = new StringBuilder(36);
        v0_1.append("Unexpected unit or type: ");
        v0_1.append(v5);
        throw new IllegalStateException(v0_1.toString());
        return arg7;
    label_22:
        return TypedValue.complexToDimensionPixelSize(arg7, arg6);
    label_34:
        v5 = 0;
        goto label_35;
    label_24:
        v5 = 5;
        goto label_35;
    label_26:
        v5 = 4;
        goto label_35;
    label_28:
        v5 = 3;
        goto label_35;
    label_30:
        v5 = 2;
        goto label_35;
    label_32:
        v5 = 1;
    label_35:
        return Math.round(TypedValue.applyDimension(v5, Float.intBitsToFloat(arg7), arg6));
    }

    public final void zza(Context arg4) {
        int v0 = this.zzge <= 0 ? style.WalletFragmentDefaultStyle : this.zzge;
        TypedArray v4 = arg4.obtainStyledAttributes(v0, styleable.WalletFragmentStyle);
        this.zza(v4, styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        this.zza(v4, styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        this.zzb(v4, styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
        this.zzb(v4, styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        this.zzb(v4, styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        this.zzb(v4, styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        this.zza(v4, styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        this.zzb(v4, styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        this.zza(v4, styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        this.zzb(v4, styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        this.zzb(v4, styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        v4.recycle();
    }

    private static long zza(int arg3) {
        if(arg3 < 0) {
            if(arg3 != -1) {
                if(arg3 == -2) {
                }
                else {
                    StringBuilder v2 = new StringBuilder(39);
                    v2.append("Unexpected dimension value: ");
                    v2.append(arg3);
                    throw new IllegalArgumentException(v2.toString());
                }
            }

            return WalletFragmentStyle.zzc(129, arg3);
        }

        return WalletFragmentStyle.zza(0, ((float)arg3));
    }

    private static long zza(int arg2, float arg3) {
        switch(arg2) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                goto label_11;
            }
        }

        StringBuilder v1 = new StringBuilder(30);
        v1.append("Unrecognized unit: ");
        v1.append(arg2);
        throw new IllegalArgumentException(v1.toString());
    label_11:
        return WalletFragmentStyle.zzc(arg2, Float.floatToIntBits(arg3));
    }

    private final void zza(TypedArray arg3, int arg4, String arg5) {
        long v0_1;
        if(this.zzgd.containsKey(arg5)) {
            return;
        }

        TypedValue v3 = arg3.peekValue(arg4);
        if(v3 != null) {
            Bundle v4 = this.zzgd;
            int v0 = v3.type;
            if(v0 == 5) {
                v0_1 = WalletFragmentStyle.zzc(128, v3.data);
            }
            else if(v0 == 16) {
                v0_1 = WalletFragmentStyle.zza(v3.data);
            }
            else {
                int v3_1 = v3.type;
                StringBuilder v0_2 = new StringBuilder(38);
                v0_2.append("Unexpected dimension type: ");
                v0_2.append(v3_1);
                throw new IllegalArgumentException(v0_2.toString());
            }

            v4.putLong(arg5, v0_1);
        }
    }

    private final void zza(TypedArray arg2, int arg3, String arg4, String arg5) {
        if(!this.zzgd.containsKey(arg4)) {
            if(this.zzgd.containsKey(arg5)) {
            }
            else {
                TypedValue v2 = arg2.peekValue(arg3);
                if(v2 != null) {
                    if(v2.type >= 28 && v2.type <= 31) {
                        this.zzgd.putInt(arg4, v2.data);
                        return;
                    }

                    this.zzgd.putInt(arg5, v2.resourceId);
                }
            }
        }
    }

    private final void zzb(TypedArray arg2, int arg3, String arg4) {
        if(this.zzgd.containsKey(arg4)) {
            return;
        }

        TypedValue v2 = arg2.peekValue(arg3);
        if(v2 != null) {
            this.zzgd.putInt(arg4, v2.data);
        }
    }

    private static long zzc(int arg4, int arg5) {
        return (((long)arg5)) & 4294967295L | (((long)arg4)) << 32;
    }
}

